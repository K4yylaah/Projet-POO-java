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


        Scanner scanner = new Scanner(System.in);

        while (true) {

            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    newpharmacy.checkInventory();
                    break;
                case 2:
                    Pharmacy.searchProductMenu(scanner, newpharmacy);
                    break;
                case 4:
                    Pharmacy.removeProduct(scanner, newpharmacy);
                    break;
                case 5:
                    Pharmacy.productsOutOfStock(scanner, newpharmacy);
                    break;
                case 6:
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
        System.out.println("3. passer commande");
        System.out.println("4. Retirer un produit");
        System.out.println("5. Afficher les produits en rupture de stock");
        System.out.println("6. Quitter");
        System.out.print("Votre choix : ");
    }

    public static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Veuillez entrer un numéro valide : ");
            scanner.next();
        }
        return scanner.nextInt();
    }


}

