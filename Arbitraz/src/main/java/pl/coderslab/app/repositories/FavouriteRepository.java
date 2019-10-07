package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.Favourite;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {
    List<Favourite> findAllByLogin(String login);
    Favourite findFirstByLoginOrderByIdDesc(String login);
}
