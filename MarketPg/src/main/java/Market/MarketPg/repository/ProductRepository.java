package Market.MarketPg.repository;

import Market.MarketPg.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
