import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
public class Json

{

    public static void main(Strings args[])

    {



        Object o = new JSONParser().parse(new FileReader(File.json));

        JSONObject j = (JSONObject) o;

        String Name = (String) j.get("Name");

        String College = (String ) j.get("College");



        System.out.println("Name :" + Name);

        System.out.println("College :" +College);

    }

}