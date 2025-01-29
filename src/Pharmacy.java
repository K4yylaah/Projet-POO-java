import java.util.ArrayList;
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
    void checkInventory(){
        List<Product> names = new ArrayList<>();
        for (Product p : products){
            names.add(p);
        }
        String temp;
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {

                // to compare one string with other strings
                if (names.get(i).name.compareTo(names.get(j).name) > 0) {
                    // swapping
                    temp = names.get(i).name;
                    names.get(i).name =  names.get(j).name;
                    names.get(j).name = temp;
                }
            }
        }
        for(Product p : names){
            p.printAttributs();
        }
    }

}
