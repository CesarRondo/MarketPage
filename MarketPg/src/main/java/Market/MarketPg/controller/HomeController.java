package Market.MarketPg.controller;

import Market.MarketPg.model.Product;
import Market.MarketPg.model.Purchase;
import Market.MarketPg.model.Session;
import Market.MarketPg.repository.ProductRepository;
import Market.MarketPg.repository.PurchaseRepository;
import Market.MarketPg.repository.SessionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping()
public class HomeController {

    ProductRepository productRepository;
    SessionRepository sessionRepository;
    PurchaseRepository purchaseRepository;
    public HomeController(ProductRepository productRepository, SessionRepository sessionRepository, PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.sessionRepository = sessionRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping("/")
    public String home(Model model,HttpSession session) {

        checkLogIn(model, session.getId());
        List<Product> products = new ArrayList<>();
        for(Product product : productRepository.findAll()) {
            if(product.isVisible()){
                products.add(product);
            }
        }
        model.addAttribute("products", products);
//        // Store products in session
//        session.setAttribute("products", products);

        return "index";
    }

    public Boolean checkLogIn(Model model, String sessionId){

        Optional<Session> currentSession = sessionRepository.findByCode(sessionId);

        if (currentSession.isPresent()) {
            model.addAttribute("loggedIn", true);
            return true;
        }
        else{
            model.addAttribute("loggedIn", false);
            return false;
        }
    }
    public Session checkLogIn(String sessionId){

        Optional<Session> currentSession = sessionRepository.findByCode(sessionId);
        return currentSession.orElse(null);
    }

    public Session sessionLogIn(Model model,String sessionId){
        Optional<Session> currentSession = sessionRepository.findByCode(sessionId);

        if (currentSession.isPresent()) {
            model.addAttribute("loggedIn", true);
            return currentSession.get();
        }
        else{
            model.addAttribute("loggedIn", false);
            return null;
        }

    }

    @GetMapping("/signUpPage")
    public String signUpPage() {
        return "signUpPage";
    }


    @GetMapping("/logInPage")
    public String logInPage() {
        return "logInPage";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable int id, Model model, HttpSession session) {
        checkLogIn(model, session.getId());
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            return "buyPage"; // Loads buyPage.html
        }
        return "redirect:/";
    }
    @GetMapping("/userPage")
    public String userPage(Model model, HttpSession http) {
        //if user is not logged in page for the user cannot be loaded
        Session session = sessionLogIn(model, http.getId());
        if(session != null){

            if(session.getId()==1){
                model.addAttribute("admin", true);
            }

            return "userPage";
        }
        return "redirect:/";
    }

    @GetMapping("/goToPayment")
    public String goToPayment(@RequestParam("itemId") String itemId, @RequestParam("amount") Double amount, Model model) {
        model.addAttribute("itemId", itemId);
        model.addAttribute("amount", amount);

        return "paymentPage";
    }
    @PostMapping("/confirmPurchase")
    @ResponseBody
    public void confirmPurchase(@RequestBody Optional<List<Id_amount>> listOptional, HttpSession http) {
        Session session = checkLogIn(http.getId());
        if(session!=null && listOptional.isPresent()) {
            for(Id_amount id_amount : listOptional.get()) {
                savePurchase(id_amount, session.getId());//replace 1 for session.getId()
            }
        }
    }

    public void savePurchase(Id_amount id_amount, Integer userId) {
        if(productRepository.findById(id_amount.getId()).isPresent()) {
            Product product = productRepository.findById(id_amount.getId()).get();
            purchaseRepository.save(Purchase.newPurchase(userId, id_amount.getId(), id_amount.getAmount(),id_amount.getAmount()*product.getPrice() ));
        }
    }

    @GetMapping("/orderHistory")
    public String orderHistory(Model model, HttpSession http) {

        if(!checkLogIn(model, http.getId())){
            return "redirect:/";
        }

        Session session = checkLogIn(http.getId());
        if(session==null) {
            return "redirect:/";
        }
        Optional<List<Purchase>> purchases = purchaseRepository.findByUser(session.getId());
        ArrayList<PurchaseView> purchaseList = new ArrayList<>();
        if(purchases.isPresent()) {

            for(Purchase purchase : purchases.get()) {
                Product product = productRepository.findById(purchase.getId()).get();
                PurchaseView purchaseView = new PurchaseView(product.getName(), purchase.getAmount(), purchase.getPrice(), purchase.getStatus());
                purchaseList.add(purchaseView);
            }

            //model.addAttribute("purchases", purchases.get());
            model.addAttribute("purchases", purchaseList);
            return "orderHistory";
        }
        return "redirect:/";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model, HttpSession http) {
        Session session = checkLogIn(http.getId());
        if(session!=null && session.getId()==1){
            //model.addAttribute("admin", true);

            List<Product> products = new ArrayList<>();
            for(Product product : productRepository.findAll()) {
                products.add(product);
            }
            model.addAttribute("products", products);


            return "adminPage";
        }
        else if (session!=null){
            return "redirect:/userPage";
        }
        return "redirect:/";
    }


}

//Helper classes to parse JSON or send data to the view

class Id_amount{
    private int id;
    private int amount;

    public Id_amount(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }
}

class PurchaseView{
    private String product_name;
    private Integer amount;
    private Integer price;
    private String status;

    public PurchaseView(String product_name, Integer amount, Integer price, String status) {
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getProduct_name() {
        return product_name;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getPrice() {
        return price;
    }
}
