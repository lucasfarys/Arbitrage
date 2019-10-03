package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.exchangeModel.Bitbay;

import java.util.List;


public interface BitbayRepository extends JpaRepository<Bitbay,Long> {
    List<Bitbay> findFirst24ByOrderByIdDesc();
}