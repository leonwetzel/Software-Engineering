package classifier;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 23-2-2016.
 */
public class TreeBuilder {
    Map<Item, String> trainingsSet = new HashMap<>();
    Map<String, FeatureType> features = new HashMap<>();

    // onderstaande is door Leon gemaakt
    FeatureType luxery = new FeatureType("Luxery", new String[] {"Blingbling", "Doekoes"});
    DefaultMutableTreeNode root = new DefaultMutableTreeNode();

    /**
     * Constructs a trebuilder.
     */
    public TreeBuilder()
    {
        processText();
    }

    private void processLine(String newNode)
    {

    }

    private Item createItem(String ac, String abs){
        Feature[] featureValues = new Feature[]{
                new Feature("AC",ac, luxery),
                new Feature("ABS",abs, luxery)
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

    /**
     * By Leon Wetzel.
     * Attempt to solve puzzle.
     */
    private void build() {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            DefaultMutableTreeNode node = root;

            br = new BufferedReader(new FileReader("C:\\testing.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                // doe magie hier!
                node.add(convertStringToNode(sCurrentLine));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * By Leon Wetzel.
     * Attempt to solve puzzle.
     */
    private DefaultMutableTreeNode convertStringToNode(String line) {
        return new DefaultMutableTreeNode(line);
    }
}
