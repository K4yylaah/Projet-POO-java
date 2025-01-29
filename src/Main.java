import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        List<Product> productList = new ArrayList<>();

        try (FileReader reader = new FileReader("stocks_pharma.json")) {
            // Lire le fichier JSON
            Object object = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;

            // Accéder aux produits
            JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
            JSONArray produits = (JSONArray) pharmacie.get("produits");

            for (Object produitObj : produits) {
                JSONObject produit = (JSONObject) produitObj;
                String categorie = (String) produit.get("categorie");
                String sousCategorie = (String) produit.get("sousCategorie");

                JSONArray produitsDetails = (JSONArray) produit.get("produits");
                for (Object produitDetailObj : produitsDetails) {
                    JSONObject produitDetail = (JSONObject) produitDetailObj;
                    Long id = (Long) produitDetail.get("id");
                    String nomProduit = (String) produitDetail.get("nom");
                    Double prixProduit = (Double) produitDetail.get("prix");
                    Long quantiteStock = (Long) produitDetail.get("quantiteStock");
                    String description = (String) produitDetail.get("description");
                    // Création de l'objet Product
                    Product product = new Product(id, nomProduit,categorie,sousCategorie, prixProduit, quantiteStock.intValue(), description);
                    productList.add(product);
                }
            }

            // Affichage des produits créés
            for (Product p : productList) {
                System.out.println(p.name);
            }

        } catch (IOException | ParseException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}