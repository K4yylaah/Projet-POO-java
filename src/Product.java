import java.io.Serializable;

/**
 * Represents a product in the pharmacy.
 * This class stores product details such as ID, name, category, subcategory, price, stock quantity, and description.
 * It also provides a method to print the product's attributes.
 */
public class Product implements Serializable {
    Long id;
    String category;
    String subcategory;
    String name;
    double price;
    int quantityStock;
    String description;

    /**
     * Constructs a Product object with the given details.
     *
     * @param id             The unique identifier of the product.
     * @param name           The name of the product.
     * @param category       The category to which the product belongs.
     * @param subcategory    The subcategory of the product.
     * @param price          The price of the product.
     * @param quantityStock  The quantity available in stock.
     * @param description    A brief description of the product.
     */
    public Product(Long id, String name, String category, String subcategory, double price, int quantityStock, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.price = price;
        this.quantityStock = quantityStock;
        this.description = description;
    }

    /**
     * Prints the attributes of the product, displaying all relevant details.
     */
    public void printAttributes() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Subcategory: " + subcategory);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantityStock);
        System.out.println("Description: " + description);
    }
}
