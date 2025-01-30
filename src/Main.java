import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pharmacy newpharmacy = Json.createPharmacy();
        assert newpharmacy != null;
        newpharmacy.saveData();

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
                    passOrderMenu(scanner, newpharmacy);
                    break;
                case 4:
                    System.out.println("Merci d'avoir utilisé notre pharmacie ! À bientôt.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Consulter l'inventaire");
        System.out.println("2. Rechercher un médicament");
        System.out.println("3. Passer une commande");
        System.out.println("4. Quitter");
        System.out.print("Votre choix : ");
    }

    public static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void searchProductMenu(Scanner scanner, Pharmacy newpharmacy) {
        System.out.print("Entrez le nom du médicament à rechercher : ");
        String productName = scanner.nextLine();
        Product product = newpharmacy.searchProduct(productName);

        if (product != null) {
            product.printAttributes();
            System.out.println("Quantité en stock : " + newpharmacy.quantites.getOrDefault(product, 0));
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

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
                commande.ajouterProduit(product, quantity, newpharmacy);
            } else {
                System.out.println("Produit non trouvé.");
            }

            System.out.print("Souhaitez-vous ajouter un autre médicament à la commande ? (1 pour oui, 2 pour non) : ");
            moreProducts = scanner.nextLine().equals("1");
        }

        commande.afficherRécapitulatif();
        commande.traiterCommande();
    }
}

abstract class Commande {
    protected List<CommandeProduit> orderedProducts = new ArrayList<>();
    protected List<Integer> orderedQuantities = new ArrayList<>();

    public abstract void traiterCommande();

    public void ajouterProduit(Product product, int quantity, Pharmacy pharmacy) {
        int stockDisponible = pharmacy.quantites.getOrDefault(product, 0);
        int stockMinimum = 5;

        if (stockDisponible - quantity >= stockMinimum) {
            orderedProducts.add(new CommandeProduit(product, quantity));
            orderedQuantities.add(quantity);
            pharmacy.quantites.put(product, stockDisponible - quantity);
            System.out.println("Médicament ajouté à la commande.");

            // Vérification de la quantité critique après la commande
            if (pharmacy.quantites.get(product) < stockMinimum) {
                System.out.println("ALERTE : Le produit " + product.name + " a une quantité critique en stock (" + pharmacy.quantites.get(product) + " restant(s)).");
            }
        } else {
            System.out.println("Impossible de commander cette quantité. Il doit rester au moins 5 articles en stock.");
        }
    }

    public void afficherRécapitulatif() {
        System.out.println("Récapitulatif de votre commande :");
        for (int i = 0; i < orderedProducts.size(); i++) {
            System.out.println("- " + orderedQuantities.get(i) + " " + orderedProducts.get(i).getProduct().name);
        }
    }
}

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

class CommandePrioritaire extends Commande {
    @Override
    public void traiterCommande() {
        System.out.println("Commande prioritaire en cours de traitement...");
    }
}

class CommandeStandard extends Commande {
    @Override
    public void traiterCommande() {
        System.out.println("Commande standard en cours de traitement...");
    }
}
