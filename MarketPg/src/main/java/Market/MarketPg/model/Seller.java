//package Market.MarketPg.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
//
//@Table("SELLERS")
//public class Seller {
//    @Id
//    private Integer id;
//    @Column
//    private String name;
//    @Column
//    private Country country;
//
//    public Seller(Integer id, String name, Country country) {
//        this.id = id;
//        this.name = name;
//        this.country = country;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Country getCountry() {
//        return country;
//    }
//
//    public static Seller newSeller(String name, String country) {
//        return new Seller(null, name, Country.valueOf(country.toUpperCase()));
//    }
//}
