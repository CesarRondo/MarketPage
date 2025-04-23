package Market.MarketPg.repository;

import Market.MarketPg.model.Password;
import Market.MarketPg.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordRepository extends CrudRepository<Password,Integer> {
    @Query("SELECT * FROM PASSWORDS WHERE ID = :id")
    Optional<Password> findByUserId(@Param("id") Integer id);
}
