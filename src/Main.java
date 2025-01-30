import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>();
        Pharmacy newpharmacy = Json.createPharmacy();
        assert newpharmacy != null;
        newpharmacy.saveData();
        Pharmacy newpharmacy2 = new Pharmacy("a","b",products);
        newpharmacy2.loadData();
        newpharmacy2.checkInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du produit à rechercher : ");
        String productName = scanner.nextLine();


        newpharmacy2.searchProduct(productName).printAttributes();
    }
}