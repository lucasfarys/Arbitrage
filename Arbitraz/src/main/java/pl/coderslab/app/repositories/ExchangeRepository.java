package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange,Long> {
    Exchange findAllById(Long id);
    Exchange findAllByName(String name);
}