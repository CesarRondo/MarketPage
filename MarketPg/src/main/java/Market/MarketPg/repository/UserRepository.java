package Market.MarketPg.repository;

import Market.MarketPg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT * FROM USERS WHERE USERNAME = :username")
    Optional<User> findByUsername(@Param("username") String username);

}