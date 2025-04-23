package Market.MarketPg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PASSWORDS")
public class Password {
    @Id
    private Integer password_id;
    private String password;
    private Integer id;
    public Password() {}

    public Password(Integer password_id, String password, Integer id) {
        this.password_id = password_id;
        this.password = password;
        this.id = id;
    }
    public static Password newPassword(String passwordStr, Integer id) {
        return new Password(null, passwordStr, id);
    }

    public Integer getPassword_id() {
        return password_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword_id(Integer id) {
        this.password_id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
