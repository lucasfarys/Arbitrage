package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.exchangeModel.Bitbay;

public interface BitbayRepository extends JpaRepository<Bitbay,Long> {
}