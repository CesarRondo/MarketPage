package Market.MarketPg.controller;

import Market.MarketPg.model.Product;
import Market.MarketPg.model.Purchase;
import Market.MarketPg.repository.ProductRepository;
import Market.MarketPg.repository.PurchaseRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    public AdminController(ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping("/createProduct")
    public String createProduct(@RequestParam String name, @RequestParam String description, @RequestParam Integer price) {
        Product product = new Product(null, name, description, price, 1);
        productRepository.save(product);
        return "redirect:/adminPage"; // Redirect back to admin page
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id, Model model, HttpSession session) {

        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            return "editPage"; // Loads buyPage.html
        }
        return "redirect:/";
    }

    @PostMapping("updateProduct")
    public String updateProduct(@RequestParam Integer productId, @RequestParam String name, @RequestParam String description, @RequestParam Integer price,@RequestParam int visible) {
        productRepository.save(new Product(productId,name, description, price, visible));
        return "redirect:/admin/editProduct/"+productId.toString();
    }

    @PostMapping("/toggleVisibility")
    public String toggleVisibility(@RequestParam Integer productId, @RequestParam String name, @RequestParam String description, @RequestParam Integer price, @RequestParam int visible) {
        productRepository.save(new Product(productId,name, description, price, visible));
        return "redirect:/admin/editProduct/"+productId.toString();
    }

    @GetMapping("/ordersPage")
    public String ordersPage(Model model) {
        ArrayList<PurchaseViewExtended> purchases = new ArrayList<>();
        for(Purchase purchase : purchaseRepository.findAll()) {
            Product product = productRepository.findById(purchase.getProduct_id()).get();

            purchases.add(new PurchaseViewExtended(purchase.getId(), product.getName(), purchase.getAmount(), purchase.getPrice(), purchase.getStatus()));
        }
        model.addAttribute("purchases", purchases);


        return "allOrders";
    }

    @PostMapping("/confirmDelivery")
    public String confirmDelivery(Model model, @RequestParam("purchaseId") Integer purchaseId){
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase != null && "Delivering".equals(purchase.getStatus())) {
            purchase.setStatus("Delivered");
            purchaseRepository.save(purchase);
        }
        return "redirect:/admin/ordersPage";
    }

}
class PurchaseViewExtended{
    private Integer purchase_id;
    private String product_name;
    private Integer amount;
    private Integer price;
    private String status;

    public PurchaseViewExtended(Integer purchase_id, String product_name, Integer amount, Integer price, String status) {
        this.purchase_id = purchase_id;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }

    public Integer getPurchase_id() {
        return purchase_id;
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