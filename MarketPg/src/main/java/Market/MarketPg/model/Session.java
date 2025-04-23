package Market.MarketPg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("SESSIONS")
public class Session {
    @Id
    private Integer session_id;
    private String session_code;
    private Integer id;
    public Session(Integer session_id,String session_code, Integer id) {
        this.session_id = session_id;
        this.session_code = session_code;
        this.id = id;
    }
    public static Session newSession(Integer id, String session_code) {
        return new Session(null,session_code,id);
    }
    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_code(String session_code) {
        this.session_code = session_code;
    }

    public String getSession_code() {
        return session_code;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = session_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
