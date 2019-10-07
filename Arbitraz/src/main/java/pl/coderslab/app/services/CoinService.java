package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.model.Coin;
import pl.coderslab.app.repositories.CoinRepository;

import java.util.List;

@Service
@Transactional
public class CoinService {
    private CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }
    public List<Coin> findAllCoins(){
        return coinRepository.findAll();
    }
    public Coin getCoinByName(String name){
        return coinRepository.findAllByCoinName(name);
    }
}
