import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Amoxicilline", "Médicaments", "Antibiotiques", 5.99, 120, "Antibiotique à large spectre pour les infections bactériennes."));
        productList.add(new Product(2L, "Azithromycine", "Médicaments", "Antibiotiques", 7.49, 50, "Traitement des infections respiratoires et ORL."));
        productList.add(new Product(3L, "Paracétamol", "Médicaments", "Antalgiques", 1.99, 300, "Traitement de la douleur et de la fièvre."));
        productList.add(new Product(4L, "Ibuprofène", "Médicaments", "Antalgiques", 3.49, 200, "Anti-inflammatoire non stéroïdien."));
        productList.add(new Product(5L, "Ciprofloxacine", "Médicaments", "Antibiotiques", 8.29, 75, "Antibiotique utilisé pour les infections urinaires."));


        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du produit à rechercher : ");
        String productName = scanner.nextLine();


        Product foundProduct = ProductSearch.searchProduct(productList, productName);


        if (foundProduct != null) {
            System.out.println("Produit trouvé :");
            System.out.println("Nom : " + foundProduct.name);
            System.out.println("Prix : " + foundProduct.price + " €");
            System.out.println("Quantité en stock : " + foundProduct.quantityStock);
            System.out.println("Description : " + foundProduct.description);
        } else {
            System.out.println("Produit non trouvé.");
        }
    }
}
