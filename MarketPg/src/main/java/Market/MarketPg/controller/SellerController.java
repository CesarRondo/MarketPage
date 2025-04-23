//package Market.MarketPg.controller;
//
//import Market.MarketPg.model.Country;
//import Market.MarketPg.model.Product;
//import Market.MarketPg.model.Seller;
//import Market.MarketPg.repository.ProductRepository;
//import Market.MarketPg.repository.SellerRepository;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/sellers")
//public class SellerController {
//
//    private final SellerRepository sellerRepository;
//    private final ProductRepository productRepository;
//    public SellerController(SellerRepository sellerRepository, ProductRepository productRepository) {
//        this.sellerRepository = sellerRepository;
//        this.productRepository = productRepository;
//    }
//
//    @PostMapping("/newSeller")
//    public Seller addSeller(@RequestBody String[] sellerData) {
//        Seller seller = Seller.newSeller(sellerData[0], sellerData[1]);
//        sellerRepository.save(seller);
//        return seller;
//    }
//
//    @GetMapping("/getSeller{id}")
//    public Seller getSeller(@RequestParam Integer id) {
//        //sellerRepository.findById(id);
//        //System.out.println(sellerRepository.findById(id).get());
//        Seller seller = sellerRepository.findById(id).orElse(null);
//        return seller;
//    }
//
//    @PostMapping("/{id}/addProduct")
//    public Product addProduct(@PathVariable Integer id, @RequestBody String[] productData) {
//        Seller seller = getSeller(id);
//        Product product = Product.newProduct(seller.getId(),productData[0],Integer.valueOf(productData[1]) );
//        productRepository.save(product) ;
//        return product;
//    }
//
//}
//
////helper class to parse JSON from http requests
