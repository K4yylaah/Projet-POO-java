import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Main method where the pharmacy system starts.
     */
    public static void main(String[] args) {
        // Creating a new Pharmacy object from a JSON file or database
        Pharmacy newpharmacy = Json.createPharmacy();
        assert newpharmacy != null; // Ensure the pharmacy is created successfully
        newpharmacy.saveData(); // Save any data if necessary (e.g., initial setup or configuration)

        Scanner scanner = new Scanner(System.in); // Create scanner for user input

        // Infinite loop to show the menu and process user choices until the user quits
        while (true) {
            displayMenu(); // Display the main menu options
            int choice = getUserChoice(scanner); // Get user choice from input

            // Switch statement to process the user's menu choice
            switch (choice) {
                case 1:
                    newpharmacy.checkInventory(); // Display inventory
                    break;
                case 2:
                    searchProductMenu(scanner, newpharmacy); // Search for a product
                    break;
                case 3:
                    passOrderMenu(scanner, newpharmacy); // Start a new order
                    break;
                case 4:
                    System.out.println("Merci d'avoir utilisé notre pharmacie ! À bientôt.");
                    scanner.close(); // Close scanner resource
                    return; // Exit the program
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    /**
     * Displays the main menu of the pharmacy system.
     */
    public static void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Consulter l'inventaire");
        System.out.println("2. Rechercher un médicament");
        System.out.println("3. Passer une commande");
        System.out.println("4. Quitter");
        System.out.print("Votre choix : ");
    }

    /**
     * Gets the user's choice from the menu. If the input is not valid, it returns -1.
     *
     * @param scanner The scanner object to read the input
     * @return The user's menu choice as an integer
     */
    public static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine()); // Try to parse the input as an integer
        } catch (NumberFormatException e) {
            return -1; // Return -1 if the input is not a valid integer
        }
    }

    /**
     * Displays the search product menu, allowing the user to search for a specific product.
     *
     * @param scanner The scanner object to read the input
     * @param newpharmacy The pharmacy object where the search will be performed
     */
    public static void searchProductMenu(Scanner scanner, Pharmacy newpharmacy) {
        System.out.print("Entrez le nom du médicament à rechercher : ");
        String productName = scanner.nextLine(); // Read the product name
        Product product = newpharmacy.searchProduct(productName);

        if (product != null) {
            product.printAttributes(); // Print the product details if found
            System.out.println("Quantité en stock : " + newpharmacy.quantites.getOrDefault(product, 0)); // Show stock quantity
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    /**
     * Handles the process of passing an order, where the user can choose a product, specify the quantity,
     * and determine whether the order is a priority order or not.
     *
     * @param scanner The scanner object to read the input
     * @param newpharmacy The pharmacy object where the order will be processed
     */
    public static void passOrderMenu(Scanner scanner, Pharmacy newpharmacy) {
        System.out.println("Voulez-vous une commande prioritaire ? (1 pour oui, 2 pour non)");
        String priorityChoice = scanner.nextLine();

        Commande commande = (priorityChoice.equals("1")) ? new CommandePrioritaire() : new CommandeStandard();

        boolean moreProducts = true;
        while (moreProducts) {
            System.out.print("Entrez le nom du médicament : ");
            String productName = scanner.nextLine();

            Product product = newpharmacy.searchProduct(productName);
            if (product != null) {
                System.out.print("Entrez la quantité souhaitée : ");
                int quantity = Integer.parseInt(scanner.nextLine());
                commande.ajouterProduit(product, quantity, newpharmacy); // Add the product to the order
            } else {
                System.out.println("Produit non trouvé.");
            }

            System.out.print("Souhaitez-vous ajouter un autre médicament à la commande ? (1 pour oui, 2 pour non) : ");
            moreProducts = scanner.nextLine().equals("1"); // Loop if the user wants to add more products
        }

        commande.afficherRécapitulatif();
        commande.traiterCommande();
    }
}

// Abstract Commande class that represents a generic order
abstract class Commande {
    protected List<CommandeProduit> orderedProducts = new ArrayList<>();
    protected List<Integer> orderedQuantities = new ArrayList<>();

    /**
     * Abstract method to process the order. Each subclass must implement its own version of this method.
     */
    public abstract void traiterCommande();

    /**
     * Adds a product to the order if the quantity is available in stock.
     *
     * @param product The product to be added to the order
     * @param quantity The quantity of the product to be added
     * @param pharmacy The pharmacy object where the product is being checked and updated
     */
    public void ajouterProduit(Product product, int quantity, Pharmacy pharmacy) {
        int stockDisponible = pharmacy.quantites.getOrDefault(product, 0); // Get the available stock for the product
        int stockMinimum = 5; // Minimum stock required after placing the order

        if (stockDisponible - quantity >= stockMinimum) {
            orderedProducts.add(new CommandeProduit(product, quantity)); // Add product to the order
            orderedQuantities.add(quantity); // Add the quantity to the order
            pharmacy.quantites.put(product, stockDisponible - quantity); // Update the stock in the pharmacy
            System.out.println("Médicament ajouté à la commande.");

            // Alert if stock is below the minimum threshold
            if (pharmacy.quantites.get(product) < stockMinimum) {
                System.out.println("ALERTE : Le produit " + product.name + " a une quantité critique en stock (" + pharmacy.quantites.get(product) + " restant(s)).");
            }
        } else {
            System.out.println("Impossible de commander cette quantité. Il doit rester au moins 5 articles en stock.");
        }
    }

    /**
     * Displays a summary of the order, listing the products and quantities that have been ordered.
     */
    public void afficherRécapitulatif() {
        System.out.println("Récapitulatif de votre commande :");
        for (int i = 0; i < orderedProducts.size(); i++) {
            System.out.println("- " + orderedQuantities.get(i) + " " + orderedProducts.get(i).getProduct().name);
        }
    }
}

// Class representing a product in an order
class CommandeProduit {
    private Product product;
    private int quantity;

    public CommandeProduit(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

// Class for processing a priority order
class CommandePrioritaire extends Commande {
    @Override
    public void traiterCommande() {
        System.out.println("Commande prioritaire en cours de traitement...");
    }
}

// Class for processing a standard order
class CommandeStandard extends Commande {
    @Override
    public void traiterCommande() {
        System.out.println("Commande standard en cours de traitement...");
    }
}
