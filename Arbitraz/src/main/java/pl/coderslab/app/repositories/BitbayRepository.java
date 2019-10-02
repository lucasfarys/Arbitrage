package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.app.model.exchangeModel.Bitbay;

import java.util.List;


public interface BitbayRepository extends JpaRepository<Bitbay,Long> {
}