package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.dto.DataCoinDTO;
import pl.coderslab.app.model.DataCoin;
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

    public Double test(Long id) {
        return dataCoinRepository.test(id);
    }
    public List<DataCoin> getLastAddedCoins(){
        return dataCoinRepository.findFirst24AllByOrderByCreatedDesc();
    }
}
