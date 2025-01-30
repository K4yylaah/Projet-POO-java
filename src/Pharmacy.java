import java.io.*;
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
            quantites.put(p, p.quantityStock); // Correction : Prendre la quantité réelle du JSON
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
            System.out.println("Quantité en stock: " + quantites.getOrDefault(p, 0)); // Affiche la quantité actuelle
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
        try (FileOutputStream fileOut = new FileOutputStream("pharmacySave.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (Exception a) {
            System.out.println("Erreur lors de la sauvegarde : " + a.getMessage());
        }
    }

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
