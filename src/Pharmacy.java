import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
public class Pharmacy implements Serializable, java.io.Serializable {
    public String name;
    public String address;
    public List<Product> products;

    public Pharmacy(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }
    public List triMedicaments(){
        List<Product> names = new ArrayList<>();
        for (Product p : products){
            names.add(p);
        }
        String temp;
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {

                // to compare one string with other strings
                if (names.get(i).name.compareTo(names.get(j).name) > 0) {
                    // swapping
                    temp = names.get(i).name;
                    names.get(i).name =  names.get(j).name;
                    names.get(j).name = temp;
                }
            }
        }
        return names;
    }
    void checkInventory(){
        List<Product> names = this.triMedicaments();
        for(Product p : names){
            p.printAttributes();
        }
    }
    public Product searchProduct(String productName) {

        List<Product> names = this.triMedicaments();

        int left = 0;
        int right = names.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = names.get(mid);
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


    @Override
    public void saveData(){
        try {
            Object o = (Object) this;
            FileOutputStream fileOut = new FileOutputStream("pharmacySave.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
        } catch (Exception a) {
            System.out.println(a);
        }

    }
    public void loadData(){
        try
        {
            FileInputStream fileIn = new FileInputStream("pharmacySave.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             Pharmacy pharmacy = (Pharmacy) in.readObject();
             this.name = pharmacy.name;
             this.address = pharmacy.address;
             this.products = pharmacy.products;
            in.close();
            fileIn.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
