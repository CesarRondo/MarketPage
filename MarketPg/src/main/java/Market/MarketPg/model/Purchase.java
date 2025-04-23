package Market.MarketPg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PURCHASES")
public class Purchase {
    @Id
    private Integer id;
    private Integer user_id;
    private Integer product_id;
    private Integer amount;
    private Integer price;
    private String status;

    public Purchase(Integer id, Integer user_id, Integer product_id, Integer amount, Integer price, String status) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }
    public String toString(){
        return "{id:"+this.id+", user_id:"+this.user_id+", product_id:"+this.product_id+", amount:"+this.amount+", price:"+this.price+", status:"+this.status+"}";
    }
    public static Purchase newPurchase(Integer user_id, Integer product_id, Integer amount, Integer price) {
        return new Purchase(null, user_id,product_id,amount, price, "Delivering");
    }

    public String getStatus() {
        return status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
