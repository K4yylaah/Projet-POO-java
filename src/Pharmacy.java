import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Pharmacy implements Serializable {
    public String name;
    public String address;
    public List<Product> products;
    public Map<Product, Integer> quantites;

    public Pharmacy(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
        this.quantites = new HashMap<>();


        for (Product p : products) {
            quantites.put(p, 100);
        }
    }


    public List<Product> triMedicaments() {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(p -> p.name));
        return sortedProducts;
    }


    void checkInventory() {
        List<Product> sortedProducts = this.triMedicaments();
        for (Product p : sortedProducts) {
            p.printAttributes();
            System.out.println("Quantité en stock: " + quantites.get(p));
        }
    }


    public Product searchProduct(String productName) {
        List<Product> sortedProducts = this.triMedicaments();
        int left = 0;
        int right = sortedProducts.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = sortedProducts.get(mid);
            int comparison = midProduct.name.compareToIgnoreCase(productName);

            if (comparison == 0) {
                return midProduct;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }


    public void saveData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("pharmacySave.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (Exception a) {
            System.out.println(a);
        }
    }


    public void loadData() {
        try {
            FileInputStream fileIn = new FileInputStream("pharmacySave.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Pharmacy pharmacy = (Pharmacy) in.readObject();
            this.name = pharmacy.name;
            this.address = pharmacy.address;
            this.products = pharmacy.products;
            this.quantites = pharmacy.quantites;
            in.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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
    public static void removeProduct(Scanner scanner, Pharmacy newpharmacy) {
        // Demande le produit que l'utilisateur veut supprimer
        System.out.print("Entrez le nom du produit à supprimer : ");
        scanner.nextLine();
        String productName = scanner.nextLine();
         // Vérifie que le nom est valide
        if (productName != null && !productName.isEmpty()) {
            // Parcours la liste des produits et filtre pour trouver le produit correspondant au nom
            Optional<Product> productToRemove = newpharmacy.products.stream()
                    .filter(p -> p.name.equals(productName))
                    .findFirst();
            if (productToRemove.isPresent()) {
                // Supprime le produit s'il existe
                newpharmacy.products.remove(productToRemove.get());
                System.out.println("Le produit a été supprimé.");
            } else {
                System.out.println("Produit non trouvé.");
            }
        } else {
            System.out.println("La suppression a échoué. Nom de produit invalide.");
        }
    }
    public static void productsOutOfStock(Scanner scanner, Pharmacy newpharmacy) {
        // Création d'une liste de paires (Produit, Quantité)
        scanner.nextLine();
        List<Product> outOfStock = newpharmacy.products.stream()
                .filter(p -> p.quantityStock <= 5 )
                .toList();
        if (!outOfStock.isEmpty()) { // Vérifie que la liste n'est pas vide
            System.out.println("Produits en rupture de stock :");
            outOfStock.forEach(p -> System.out.println("- " + p.name));
            }
        else {
                System.out.println("Aucun produit n'est proche de la rupture de stock.");
            }
        }

    }