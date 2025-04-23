package Market.MarketPg.controller;

import Market.MarketPg.model.Password;
import Market.MarketPg.model.Session;
import Market.MarketPg.model.User;
import Market.MarketPg.repository.PasswordRepository;
import Market.MarketPg.repository.SessionRepository;
import Market.MarketPg.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final SessionRepository sessionRepository;

    public UserController(UserRepository userRepository, PasswordRepository passwordRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.sessionRepository = sessionRepository;
        crtUser(new User_password("ADMIN", "admin123"));
        crtUser(new User_password("cesar", "22779089"));

    }

    @PostMapping("/newUser")
    public User crtUser(@RequestBody User_password user_password) {
        User user = User.newUser(user_password.getUsername());
        userRepository.save(user);
        Password password = Password.newPassword(user_password.getPassword(), user.getId());
        passwordRepository.save(password);
        setPassword(password);
        return user;
    }
    public void setPassword(Password password) {
        passwordRepository.save(password);
    }

//    @PostMapping("/UpdateBalance")
//    public User addBalance (@RequestParam Integer id, @RequestParam Integer topUp){
//        User noUser = User.newUser("noUser");
//        Optional<User> user = userRepository.findById(id);
//        user.ifPresent(value -> value.setBalance(value.getBalance()+topUp));
//        return user.orElse(noUser);
//    }
    @GetMapping("/getUser{id}")
    public User getUser(@RequestParam Integer id) {
        //userRepository.findById(id).orElse(null);
        User user = userRepository.findById(id).get();
        return user;
    }
    @PostMapping("/getUserbyName")
    public User getUserbyName(@RequestBody StringHelper name) {
        Optional<User> userOpt=userRepository.findByUsername(name.getContent());
        return userOpt.orElse(new User());
    }

    @PostMapping("/checkPassword")
    public Boolean checkPassword(@RequestBody Id_password id_password) {
        Password password = passwordRepository.findByUserId(id_password.getId()).orElse(null);
        return password != null && password.getPassword().equals(id_password.getPassword());
    }

    @PostMapping("/startSession")
    @ResponseBody
    public Integer startSession(@RequestBody Id_password id_password, HttpSession http) {
        Password password = passwordRepository.findByUserId(id_password.getId()).orElse(null);
        Boolean logIn = password != null && password.getPassword().equals(id_password.getPassword());
        if(logIn){
            Session session = Session.newSession(id_password.getId(), http.getId());
            sessionRepository.save(session);
            return session.getId();
        }
        return 0;
    }
    @PostMapping("/endSession")
    @ResponseBody
    public Boolean endSession(HttpSession http) {
        if(sessionRepository.findByCode(http.getId()).isPresent()){
            sessionRepository.delete(sessionRepository.findByCode(http.getId()).get());
            return true;
        }
        return false;
    }


}
//helper classes
class User_password{
    public User_password() {
    }
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User_password(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Id_password{
    private Integer id;
    private String password;

    public Id_password(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
//helper class to just pass strings to endpoints
class StringHelper{
    private String content;

    public String getContent() {
        return content;
    }

    public StringHelper(String content) {
        this.content = content;
    }
}
