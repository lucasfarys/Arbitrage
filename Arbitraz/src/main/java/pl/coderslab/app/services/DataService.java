package pl.coderslab.app.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.ExchangeCoin;
import pl.coderslab.app.model.Favourite;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class DataService {
    private ExchangeService exchangeService;
    private DataCoinService dataCoinService;
    private FavouriteService favouriteService;

    public DataService(ExchangeService exchangeService, DataCoinService dataCoinService, FavouriteService favouriteService) {
        this.exchangeService = exchangeService;
        this.dataCoinService = dataCoinService;
        this.favouriteService = favouriteService;
    }

    @Scheduled(fixedRate = 3600000)
    public void getAndSaveData() {
        List<Exchange> exchanges = exchangeService.getExchanges();
        exchanges.forEach(e -> dataCoinService.saveDataCoins(getDataCoins(e)));
        List<Favourite> favourites = favouriteService.finadAll();
        compareCoins(favourites,exchanges);
    }
    private List<DataCoinDTO> getDataCoins(Exchange exchange) {
        List<DataCoinDTO> dataCoinsDTO = new ArrayList<>();
        List<ExchangeCoin> exchangeCoins = exchange.getExchangeCoins();

        for (ExchangeCoin ex : exchangeCoins) {
            dataCoinsDTO.add(getData(ex, exchange));
        }
        return dataCoinsDTO;
    }
    private DataCoinDTO getData(ExchangeCoin ex, Exchange exchange) {
        DataCoinDTO dataCoinDTO = new DataCoinDTO();
        JSONObject jsonObject = getJson(ex,exchange);
        dataCoinDTO.setExchangeCoin(ex);
        // for Bittrex
        if(jsonObject.isNull("ask")) {
            dataCoinDTO.setAsk(jsonObject.getJSONObject("result").getDouble("Ask"));
            dataCoinDTO.setBid(jsonObject.getJSONObject("result").getDouble("Bid"));
        }
        // for HitBTC
        else if("HitBTC".equals(exchange.getName())){
            dataCoinDTO.setAsk(jsonObject.getDouble("ask"));
            dataCoinDTO.setBid(jsonObject.getDouble("bid"));
        }
        else{
            dataCoinDTO.setAsk(jsonObject.getDouble("ask"));
            dataCoinDTO.setBid(jsonObject.getDouble("bid"));
        }
        return dataCoinDTO;
    }
    public JSONObject getJson (ExchangeCoin ex, Exchange exchange){
        String coinName = ex.getUniqueName();
        BufferedReader reader = null;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        if("HitBTC".equals(exchange.getName())){
            coinName ="";
        }
        try {
            URL url = new URL(exchange.getAddressUrlPrefix() + coinName +
                    exchange.getAddressUrlSuffix());
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);


            // for HitBTC
            if(exchange.getName().equals("HitBTC")){
                jsonArray = new JSONArray(buffer.toString());
                for (int i = 0; i < jsonArray.length() - 1; i++) {
                    if(jsonArray.getJSONObject(i).getString("symbol").equals(ex.getUniqueName())){
                        jsonObject = jsonArray.getJSONObject(i);
                    }
                }
            }else{
                jsonObject = new JSONObject(buffer.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
    private void compareCoins(List<Favourite> favourites, List<Exchange> exchanges) {
        favourites.forEach(f->{
            Double coinValueFirst = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(f.getExchangeFirst().getId(),
                    f.getCoin().getId()).getAsk();
            Double coinValueSecond = dataCoinService.getFirstDataCoinByExchangeIdAndCoinId(f.getExchangeSecond().getId(),
                    f.getCoin().getId()).getBid();

            if((coinValueFirst/coinValueSecond-1)*100>2){
                String msg = "Powiadomienie o mozliwości zyskownej transakcji:\n " +
                        f.getExchangeFirst().getName() + " | " + f.getExchangeSecond().getName() + " | " +
                        f.getCoin().getCoinName() + " | \n" + String.format("%.8f",coinValueFirst) + " | " +
                        f.getCoin().getCoinName() + " | " + String.format("%.8f",coinValueSecond) + " | " +
                        f.getCoin().getCoinName();
                String subject ="Okazja " + f.getCoin().getCoinName() +
                        " | " + f.getExchangeFirst().getName() + " | " + f.getExchangeSecond().getName();



                //chwilowo wyłączony
//                sendEmail(msg,subject);
            }
        });
    }

    public JSONObject getJson(String exchangeFirstName, String exchangeSecondName, Long coinId) {
        Exchange exchangeFirst = exchangeService.getExchangeByName(exchangeFirstName);
        Exchange exchangeSecond = exchangeService.getExchangeByName(exchangeSecondName);

        JSONObject json = new JSONObject();
        List<DataCoin> dataCoinsFirst = dataCoinService.getFirst24DataCoinByExchangeIdAndCoinId(
                exchangeFirst.getId(),coinId);
        List<DataCoin> dataCoinsSecond = dataCoinService.getFirst24DataCoinByExchangeIdAndCoinId(
                exchangeSecond.getId(),coinId);

        List<Double> dataFirst = new ArrayList<>();
        List<Double> dataSecond = new ArrayList<>();
        List<Double> dataDifference = new ArrayList<>();
        List<Integer> date = new ArrayList<>();

        dataCoinsFirst.forEach(d->{
            dataFirst.add(d.getAsk());
            date.add(d.getCreated().getHour());
        });
        dataCoinsSecond.forEach(d->{dataSecond.add(d.getBid());
        });

        Collections.reverse(dataFirst);
        Collections.reverse(dataSecond);
        Collections.reverse(date);

        for (int i = 0; i < dataFirst.size(); i++) {
            dataDifference.add(Math.abs(dataSecond.get(i)/(dataFirst.get(i))-1)*100);
        }
        json.put("chartFirst",dataFirst);
        json.put("chartSecond",dataSecond);
        json.put("date",date);
        json.put("nameFirst", exchangeFirst.getName());
        json.put("nameSecond", exchangeSecond.getName());
        json.put("chartDifference",dataDifference);
        return json;
    }
    private void sendEmail(String msg, String subject) {

        String to = "lukaszfarys@gmail.com";//change accordingly

        String from = "lukaszfarys@gmail.com";//change accordingly
        final String username = "lukaszfarys@gmail.com";//change accordingly
        final String password = "xxx";//change accordingly

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject(subject);

            message.setText(msg);

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
