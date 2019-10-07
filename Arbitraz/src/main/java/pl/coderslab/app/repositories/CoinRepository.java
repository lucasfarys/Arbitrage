package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.Coin;
import pl.coderslab.app.model.Exchange;

public interface CoinRepository extends JpaRepository<Coin,Long> {
    Exchange findAllById(Long id);
    Coin findAllByCoinName(String coinName);
}