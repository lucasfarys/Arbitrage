package pl.coderslab.app.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.model.ExchangeCoin;
import pl.coderslab.app.repositories.ExchangeCoinRepository;

@Service
@Transactional
public class ExchangeCoinService {
    private ExchangeCoinRepository exchangeCoinRepository;

    public ExchangeCoinService(ExchangeCoinRepository exchangeCoinRepository) {
        this.exchangeCoinRepository = exchangeCoinRepository;
    }

    public void save(ExchangeCoin exchangeCoin){
        exchangeCoinRepository.save(exchangeCoin);
    }
}
