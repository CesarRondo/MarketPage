package Market.MarketPg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCTS")
public class Product {
    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer visible;

    public Product(Integer id, String name, String description, Integer price, Integer visible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.visible = visible;
    }

    public Product() {
    }

    public Integer getVisible() {
        return visible;
    }
    public Boolean isVisible() {
        return visible==1;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
