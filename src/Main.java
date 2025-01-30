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

        while (true) {

            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    newpharmacy.checkInventory();
                    break;
                case 2:
                    searchProductMenu(scanner, newpharmacy);
                    break;
                case 3:
                    System.out.println("Merci d'avoir utilisé notre pharmacie ! À bientôt.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n=== MENU Pharmacie Cergy===");
        System.out.println("1. Afficher l'inventaire de la pharmacie");
        System.out.println("2. Rechercher un produit");
        System.out.println("3. Quitter");
        System.out.print("Votre choix : ");
    }

    public static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Veuillez entrer un numéro valide : ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void searchProductMenu(Scanner scanner, Pharmacy newpharmacy) {
        System.out.print("Entrez le nom du produit à rechercher : ");
        scanner.nextLine();
        String productName = scanner.nextLine();

        Product product = newpharmacy.searchProduct(productName);

        if (product != null) {
            product.printAttributes();
        } else {
            System.out.println("Produit non trouvé.");
        }
    }
}
