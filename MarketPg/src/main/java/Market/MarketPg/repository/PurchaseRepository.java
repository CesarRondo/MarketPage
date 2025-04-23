package Market.MarketPg.repository;

import Market.MarketPg.model.Purchase;
import Market.MarketPg.model.Session;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    @Query("SELECT * FROM PURCHASES WHERE USER_ID = :user_id")
    public Optional<List<Purchase>> findByUser(@Param("user_id") Integer user_id);


}
