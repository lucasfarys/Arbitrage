package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.ExchangeCoin;

public interface ExchangeCoinRepository extends JpaRepository<ExchangeCoin,Long> {
}
