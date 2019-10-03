package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.exchangeModel.Bittrex;

import java.util.List;

public interface BittrexRepository extends JpaRepository<Bittrex,Long> {
    List<Bittrex> findFirst24ByOrderByDate();

}