package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.dto.CoinsListDTO;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Exchange;
import pl.coderslab.app.model.ExchangeCoin;
import pl.coderslab.app.repositories.DataCoinRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DataCoinService {
    private DataCoinRepository dataCoinRepository;


    public DataCoinService(DataCoinRepository dataCoinRepository) {

        this.dataCoinRepository = dataCoinRepository;
    }
    public void saveDataCoins(List<DataCoinDTO> dataCoinsDTO){
        List<DataCoin> dataCoins = new ArrayList<>();
        for (int i = 0; i < dataCoinsDTO.size(); i++) {
            DataCoin dataCoin = new DataCoin();
            dataCoin.setAsk(dataCoinsDTO.get(i).getAsk());
            dataCoin.setBid(dataCoinsDTO.get(i).getBid());
            dataCoin.setExchangeCoin(dataCoinsDTO.get(i).getExchangeCoin());
            dataCoinRepository.save(dataCoin);
        }
    }

    public List<DataCoin> getDataCoinByExchange(Long id) {
        return dataCoinRepository.findAllByExchangeCoinExchangeId(id);
    }


    public List<DataCoin> getDataCoinByExchangeIdAndCoinId(Long exchangeId, Long coinId) {
        return dataCoinRepository.findAllByExchangeCoinExchangeIdAndExchangeCoinCoinId(exchangeId, coinId);
    }
    public List<DataCoin> getFirst24DataCoinByExchangeIdAndCoinId(Long exchangeId, Long coinId) {
        return dataCoinRepository.findFirst24AllByExchangeCoinExchangeIdAndExchangeCoinCoinIdOrderByCreatedDesc(exchangeId, coinId);
    }
    public DataCoin getFirstDataCoinByExchangeIdAndCoinId(Long exchangeId, Long coinId) {
        return dataCoinRepository.findFirstAllByExchangeCoinExchangeIdAndExchangeCoinCoinIdOrderByCreatedDesc(exchangeId, coinId);
    }

    public List<DataCoin> getLastAddedCoins(){
        return dataCoinRepository.findFirst24AllByOrderByCreatedDesc();
    }


    public List<CoinsListDTO> getCoins(List<Exchange> exchanges){
        List<CoinsListDTO> coinList = new ArrayList<>();
        int n = 1;
        for(Exchange exchangeOne:exchanges){
            for(int i = n; i<exchanges.size();i++){
                List<CoinsListDTO> partCoinList = compare(exchangeOne,exchanges.get(i));
                updateCoinList(partCoinList,coinList);
            }
            n++;
        }

        return coinList;
    }

    private List<CoinsListDTO> compare(Exchange exchangeOne, Exchange exchangeSecond) {
        List<CoinsListDTO> coinsListDTO = new ArrayList<>();
        for(ExchangeCoin exchangeCoinOne:exchangeOne.getExchangeCoins()){
            for(ExchangeCoin exchangeCoinSecond:exchangeSecond.getExchangeCoins()){
                if(exchangeCoinOne.getCoin().getId() == exchangeCoinSecond.getCoin().getId()){
                    CoinsListDTO coin = new CoinsListDTO();
                    coin.setNameExOne(exchangeOne.getName());
                    coin.setNameExSecond(exchangeSecond.getName());
                    coin.setValueCoinOne(getDataCoinByExchangeCoinId(exchangeCoinOne.getId()).getBid());
                    coin.setValueCoinSecond(getDataCoinByExchangeCoinId(exchangeCoinSecond.getId()).getBid());
                    coin.setCoinName(exchangeCoinOne.getCoin().getCoinName());
                    coin.setProfit(Math.abs(coin.getValueCoinOne()-coin.getValueCoinSecond())/coin.getValueCoinOne()*100);
                    coinsListDTO.add(coin);
                }
            }
        }
        return coinsListDTO;
    }

    private void updateCoinList(List<CoinsListDTO> partCoinList, List<CoinsListDTO> coinList) {
        partCoinList.forEach(c-> coinList.add(c));
    }
    public DataCoin getDataCoinByExchangeCoinId(Long id){
        return dataCoinRepository.findFirstByExchangeCoinIdOrderByCreatedDesc(id);
    }
}
