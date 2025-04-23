package Market.MarketPg.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("USERS")
public class User {
    @Id
    private Integer id;

    private String username;

//    Integer balance;

    public User(Integer id,String username){
        this.id=id;
        this.username = username;
//        this.balance=balance;
    }

    public static User newUser(String username){
        return new User(null,username);
    }

    public User() {}


    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }


}
