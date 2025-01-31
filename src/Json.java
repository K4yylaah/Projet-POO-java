import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for parsing a JSON file containing pharmacy data
 * and converting it into a Pharmacy object.
 */
public class Json {
    /**
     * Reads a JSON file ("stocks_pharma.json"), parses its contents,
     * and creates a Pharmacy object containing a list of products.
     *
     * @return A Pharmacy object with parsed data, or null if an error occurs.
     */
    public static Pharmacy createPharmacy() {
        JSONParser parser = new JSONParser();
        List<Product> productList = new ArrayList<>();

        try (FileReader reader = new FileReader("stocks_pharma.json")) {
            // Parse the JSON file into a JSONObject
            Object object = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;

            // Extract the "pharmacie" object from the JSON
            JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
            JSONArray produits = (JSONArray) pharmacie.get("produits");
            String pharmacieName = (String) pharmacie.get("nom");
            String address = (String) pharmacie.get("adresse");

            // Iterate over the product categories
            for (Object produitObj : produits) {
                JSONObject produit = (JSONObject) produitObj;
                String categorie = (String) produit.get("categorie");
                String sousCategorie = (String) produit.get("sousCategorie");

                // Extract product details from each category
                JSONArray produitsDetails = (JSONArray) produit.get("produits");
                for (Object produitDetailObj : produitsDetails) {
                    JSONObject produitDetail = (JSONObject) produitDetailObj;
                    Long id = (Long) produitDetail.get("id");
                    String nomProduit = (String) produitDetail.get("nom");
                    Double prixProduit = (Double) produitDetail.get("prix");
                    Long quantiteStock = (Long) produitDetail.get("quantiteStock");
                    String description = (String) produitDetail.get("description");

                    // Create a new Product object and add it to the list
                    Product product = new Product(id, nomProduit, categorie, sousCategorie, prixProduit, quantiteStock.intValue(), description);
                    productList.add(product);
                }
            }
            // Return a new Pharmacy object with the extracted data
            return new Pharmacy(pharmacieName, address, productList);

        } catch (IOException | ParseException e) {
            // Handle exceptions and print error details
            e.printStackTrace();
            return null;
        }
    }
}
