import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json {
    public static Pharmacy createPharmacy() {
        JSONParser parser = new JSONParser();
        List<Product> productList = new ArrayList<>();
        try (FileReader reader = new FileReader("stocks_pharma.json")) {
            Object object = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;

            JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
            JSONArray produits = (JSONArray) pharmacie.get("produits");
            String pharmacieName = (String) pharmacie.get("nom");
            String address = (String) pharmacie.get("adresse");

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
                    Long quantiteStock = (Long) produitDetail.get("quantiteStock"); // Assurer que c'est bien lu
                    String description = (String) produitDetail.get("description");

                    Product product = new Product(id, nomProduit, categorie, sousCategorie, prixProduit, quantiteStock.intValue(), description);
                    productList.add(product);
                }
            }
            return new Pharmacy(pharmacieName, address, productList);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
