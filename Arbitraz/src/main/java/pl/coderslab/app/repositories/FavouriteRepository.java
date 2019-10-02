package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {
}
