package classifier;

import org.omg.CORBA.Object;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 23-2-2016.
 */
public class TreeBuilder {
    Map<Item, String> trainingsSet = new HashMap<>();
    Map<String, FeatureType> features = new HashMap<>();

    // onderstaande is door Leon gemaakt
    FeatureType ys = new FeatureType("YesNo", new String[] {"Yes", "No"});
    DefaultMutableTreeNode root;


    /**
     * Constructs a trebuilder.
     */
    public TreeBuilder()
    {
        processText();
        testTheTree();
    }

    private DefaultMutableTreeNode getRoot()
    {
        return root;
    }

    private void testTheTree()
    {
        Enumeration temp = root.breadthFirstEnumeration();
        System.out.println();
        System.out.println("TESTING");
        while(temp.hasMoreElements())
        {
            DefaultMutableTreeNode temp2 = (DefaultMutableTreeNode)temp.nextElement();
            System.out.println(temp2.getUserObject().toString());


        }

    }

    private void addCategories(Enumeration nodes)
    {

    }
    private void processLine(String node)
    {
        if(root == null){
            root = new DefaultMutableTreeNode(new DefaultMutableTreeNode(node));
        }else
        {
            addNewLevelToTree(node, root.breadthFirstEnumeration());
        }
    }

    private String determineCategorie(int i)
    {
       if(i >= 0 && i <= 51){
        return "low";
         }else if(i >= 52 && i <= 103)
       {
           return "Medium-Low";
       }else if(i >= 104 && i <= 155)
       {
           return "Medium";
       }else if(i >= 156 && i <= 207)
       {
           return "Medium-High";
       }else{
           return "High";
       }

    }

    private void addNewLevelToTree(String node, Enumeration nodes)
    {
        ArrayList<DefaultMutableTreeNode> tobeEdited = new ArrayList<DefaultMutableTreeNode>();
        while (nodes.hasMoreElements()) {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode)nodes.nextElement();
            if(temp.isLeaf())
            {
                tobeEdited.add(temp);
            }
        }
        //Done this way because of this documentation:
        //Modifying the tree by inserting, removing, or moving a node invalidates any enumerations created before the modification.
        for(DefaultMutableTreeNode addingIn: tobeEdited)
        {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(node);
            addingIn.add(newNode);
            DefaultMutableTreeNode newNode2 = new DefaultMutableTreeNode(node);
            addingIn.add(newNode2);
        }
    }

    private Item createItem(String turbo, String enginPower, String sportBumber, String sportRing, String cruissControll, String metalic, String ac, String abs){
        Feature[] featureValues = new Feature[]{
                new Feature("Turbo", turbo, ys),
                new Feature("EnginPower", enginPower, ys),
                new Feature("SportRing", sportRing, ys),
                new Feature("SportBumber", sportBumber, ys),
                new Feature("CruisControll", cruissControll, ys),
                new Feature("Metalic", metalic, ys),
                new Feature("AC",ac, ys),
                new Feature("ABS",abs, ys)
        };
        return new Item("car", featureValues);
    }

    private void addCategorieText()
    {
        BufferedReader br = null;
        try {
            int i = 0;
            String sCurrentLine;
            br = new BufferedReader(new FileReader("C:\\CatText.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                if(sCurrentLine == "Medium-Low" && i == 5)
                {
                    break;
                }

                i++;
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

    private void processText()
    {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("C:\\OptiesText.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                processLine(sCurrentLine);
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
