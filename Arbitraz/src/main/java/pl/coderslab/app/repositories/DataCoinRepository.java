package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.app.model.Coin;
import pl.coderslab.app.model.DataCoin;
import pl.coderslab.app.model.Exchange;

import java.util.List;

public interface DataCoinRepository extends JpaRepository<DataCoin,Long> {
    List<DataCoin> findAllByExchangeCoinExchangeId(Long id);
    List<DataCoin> findAllByExchangeCoinExchangeIdAndExchangeCoinCoinId(Long exchangeId, Long coinId);
    List<DataCoin> findFirst24AllByExchangeCoinExchangeIdAndExchangeCoinCoinIdOrderByCreatedDesc(Long exchangeId, Long coinId);
    DataCoin findFirstAllByExchangeCoinExchangeIdAndExchangeCoinCoinIdOrderByCreatedDesc(Long exchangeId, Long coinId);
    List<DataCoin> findFirst24AllByOrderByCreatedDesc();

    @Query(nativeQuery = true,value = "SELECT value from dataCoins where id=?")
    Double test(Long id);

    DataCoin findFirstByExchangeCoinIdOrderByCreatedDesc(Long id);

}