public class Product{
    Long id;
    String category;
    String subcategory;
    String name;
    double price;
    int quantityStock;
    String description;
    public Product(Long id,String name,  String category,String subcategory, double price, int quantityStock, String description){
        this.id = id;
        this.name = name;
        this.subcategory = category;
        this.category = subcategory;
        this.price = price;
        this.quantityStock = quantityStock;
        this.description = description;
    }

    public void printAttributs(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Subcategory: " + subcategory);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantityStock);
        System.out.println("Description: " + description);
    }
}
