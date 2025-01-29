import java.util.List;
public class Pharmacy {
    String name;
    String address;
    List<Product> products;

    public Pharmacy(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }

}
