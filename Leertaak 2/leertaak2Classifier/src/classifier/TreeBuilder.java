package classifier;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TreeBuilder {
    Map<Item, String> trainingsSet = new HashMap<>();
    Map<String, FeatureType> features = new HashMap<>();

    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> modules = new ArrayList<>();
    // onderstaande is door Leon gemaakt
    FeatureType ys = new FeatureType("yesno", new String[] {"yes", "no"});

    DefaultMutableTreeNode root;

    /**
     * Constructs a treebuilder.
     */
    public TreeBuilder() {
        processTextToTree();
        addCategorieText();
        classify();
        printTree();
    }

    private DefaultMutableTreeNode getRoot()
    {
        return root;
    }

    private void printTree() {
        Enumeration nodes = root.breadthFirstEnumeration();
        while(nodes.hasMoreElements()) {
            DefaultMutableTreeNode temp2 = (DefaultMutableTreeNode)nodes.nextElement();
        }

    }

    /**
     * Processes input and puts it into the tree.
     * @param node Input string of file.
     */
    private void buildTree(String node)
    {
        if(root == null){
            root = new DefaultMutableTreeNode(new DefaultMutableTreeNode(node));
        } else {
            addNewLevelToTree(node, root.breadthFirstEnumeration());
        }
    }

    private String determineCategorie(int i)
    {
        if(i >= 0 && i <= 51) {
            return "low";
        } else if(i >= 52 && i <= 103) {
            return "Medium-Low";
        } else if(i >= 104 && i <= 155) {
            return "Medium";
        } else if(i >= 156 && i <= 207) {
            return "Medium-High";
        } else {
            return "High";
        }
    }

    private void addNewLevelToTree(String node, Enumeration nodes) {
        ArrayList<DefaultMutableTreeNode> treeNodes = new ArrayList<>();
        while (nodes.hasMoreElements()) {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode)nodes.nextElement();
            if(temp.isLeaf()) {
                treeNodes.add(temp);
            }
        }

        //Done this way because of this documentation:
        //Modifying the tree by inserting, removing, or moving a node invalidates any enumerations created before the modification.
        for(DefaultMutableTreeNode addingIn: treeNodes) {
            System.out.println(node +": added node");
            addingIn.add(new DefaultMutableTreeNode(node));
            addingIn.add(new DefaultMutableTreeNode(node));
        }
    }

    private void addCategorieText() {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("C:\\CatText.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                categories.add(sCurrentLine);
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
     * Classifies items and puts them into category.
     */
    private void classify() {
        ArrayList<DefaultMutableTreeNode> treeNodes = new ArrayList<>();
        Enumeration tree = root.breadthFirstEnumeration();
        while(tree.hasMoreElements()) {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode)tree.nextElement();
            if(temp.isLeaf()) treeNodes.add(temp);
        }
        int i = 0;
        int j = 0;
        for(DefaultMutableTreeNode addingIn : treeNodes) {
            addingIn.add(new DefaultMutableTreeNode(categories.get(i)));
            if(j % 25 == 0 && j != 0 && j < 120) {
                i++;
            }
            j++;
        }
    }

    private void processTextToTree() {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("C:\\OptiesText.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                buildTree(sCurrentLine);
                modules.add(sCurrentLine);
                System.out.println("The "+ sCurrentLine + " tree layer has been made");
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

    public  ArrayList<String> getModules()
    {
        return modules;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}
