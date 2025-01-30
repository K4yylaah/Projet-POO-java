import java.util.ArrayList;
import java.util.List;

public class Stock implements Stockable {
    private final List<Produit> produits;

    public Stock() {
        this.produits = new ArrayList<>();
    }

    @Override
    public void addProduct(Produit produit) {
        produits.add(produit);
        System.out.println("Produit ajouté avec succès !");
    }

    @Override
    public void showProduits() {
        if (produits.isEmpty()) {
            System.out.println("Aucun produit en stock.");
        } else {
            System.out.println("\n--- Liste des Produits ---");
            for (Produit p : produits) {
                System.out.println(p);
            }
        }
    }
    @Override
    public List<Produit> getProduits() {
        return produits;
    }
    }



