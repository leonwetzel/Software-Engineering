package classifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 23-2-2016.
 */
public class opdracht15 {
    Map<Item, String> trainingsSet = new HashMap<Item, String>();
    Map<String, FeatureType> features = new HashMap<String, FeatureType>();

    public opdracht15()
    {
        processText();
    }

    private void processLine(String newNode)
    {

    }

    private Item createItem(String ac, String abs){
        Feature[] featureValues = new Feature[]{
                new Feature("AC",ac,yn),
                new Feature("ABS",abs,yn)
        };

        return new Item("car", featureValues);
    }


    private void processText()
    {
        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("C:\\testing.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
