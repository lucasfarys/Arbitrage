package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.exchangeModel.Bittrex;

public interface BittrexRepository extends JpaRepository<Bittrex,Long> {
}