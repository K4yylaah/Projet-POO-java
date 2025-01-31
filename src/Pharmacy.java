import java.io.*;
import java.util.*;

/**
 * Represents a pharmacy that contains a list of products and their quantities.
 * This class provides functionalities for sorting, searching, inventory management,
 * and saving/loading pharmacy data.
 */
public class Pharmacy implements Serializable, java.io.Serializable {
    public String name;
    public String address;
    public List<Product> products;
    public Map<Product, Integer> quantites;

    /**
     * Constructs a Pharmacy object with the given name, address, and product list.
     *
     * @param name     The name of the pharmacy.
     * @param address  The address of the pharmacy.
     * @param products The list of products available in the pharmacy.
     */
    public Pharmacy(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
        this.quantites = new HashMap<>();

        // Initialize the quantity map with stock values from products
        for (Product p : products) {
            quantites.put(p, p.quantityStock);
        }
    }

    /**
     * Sorts the list of products in alphabetical order based on their name.
     *
     * @return A sorted list of products.
     */
    public List<Product> triMedicaments() {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(p -> p.name));
        return sortedProducts;
    }

    /**
     * Checks the inventory and prints the details of each product along with its quantity in stock.
     */
    void checkInventory() {
        List<Product> sortedProducts = this.triMedicaments();
        for (Product p : sortedProducts) {
            p.printAttributes();
            System.out.println("Quantité en stock: " + quantites.getOrDefault(p, 0));
        }
    }

    /**
     * Searches for a product by its name using binary search on the sorted product list.
     *
     * @param productName The name of the product to search for.
     * @return The product if found, otherwise null.
     */
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

    /**
     * Saves the pharmacy data to a file using serialization.
     */
    public void saveData() {
        try (FileOutputStream fileOut = new FileOutputStream("pharmacySave.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Loads the pharmacy data from a serialized file and restores the object state.
     */
    public void loadData() {
        try (FileInputStream fileIn = new FileInputStream("pharmacySave.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Pharmacy pharmacy = (Pharmacy) in.readObject();
            this.name = pharmacy.name;
            this.address = pharmacy.address;
            this.products = pharmacy.products;
            this.quantites = pharmacy.quantites;
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }
    }
}
