import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stock stock = new Stock();

        while (true) {
            System.out.println("\n1. Ajouter un produit");
            System.out.println("2. Afficher le stock");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    try {
                        System.out.print("Nom du produit : ");
                        String nom = scanner.nextLine();

                        System.out.print("Catégorie : ");
                        String categorie = scanner.nextLine();

                        System.out.print("Prix : ");
                        double prix = scanner.nextDouble();

                        System.out.print("Quantité : ");
                        int quantite = scanner.nextInt();

                        Produit produit = new Produit(nom, categorie, prix, quantite);
                        stock.addProduct(produit);
                    } catch (Exception e) {
                        System.out.println("Erreur : " + e.getMessage());
                        scanner.nextLine(); // Pour éviter une boucle infinie si erreur
                    }
                    break;

                case 2:
                    stock.showProduits();
                    break;

                case 3:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }
}
