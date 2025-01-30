import java.util.List;

public interface Stockable {

    void addProduct(Produit stocks);
    void showProduits();

    List<Produit> getProduits();
}
