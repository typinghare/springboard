package us.jameschan.springboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.jameschan.springboard.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
