package Market.MarketPg.repository;

import Market.MarketPg.model.Session;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Integer> {
    @Query("SELECT * FROM SESSIONS WHERE SESSION_CODE = :code")
    public Optional<Session> findByCode(@Param("code") String code);
}
