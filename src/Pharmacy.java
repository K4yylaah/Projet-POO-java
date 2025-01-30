import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
public class Pharmacy implements Serializable, java.io.Serializable {
    String name;
    String address;
    List<Product> products;

    public Pharmacy(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }
    void checkInventory(){
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
        for(Product p : names){
            p.printAttributes();
        }
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
