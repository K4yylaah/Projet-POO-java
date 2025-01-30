import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>();
        Pharmacy newpharmacy = new Pharmacy("a","b",products);
        newpharmacy.loadData();
        newpharmacy.checkInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du produit à rechercher : ");
        String productName = scanner.nextLine();


        newpharmacy.searchProduct(productName).printAttributes();
    }
}