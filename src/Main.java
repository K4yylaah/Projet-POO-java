import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>();
        Pharmacy newpharmacy = new Pharmacy("a","b",products);
        newpharmacy.loadData();
        newpharmacy.checkInventory();


    }
}