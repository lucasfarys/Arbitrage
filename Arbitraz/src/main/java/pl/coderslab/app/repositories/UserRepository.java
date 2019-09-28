package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    long countByEmail(String email);

    User getByEmail(String email);
}