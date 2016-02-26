package classifier;

/**
 * Created by Leon Wetzel
 * Date of creation 18-2-2016, 13:47
 * |
 * Authors: Leon Wetzel
 * |
 * Version: 1.0
 * Package: classifier
 * Class:
 * Description:
 * |
 * |
 * Changelog:
 * 1.0:
 */

/**
 * Test enviroment for Traverser
 */
public class Main {
    /*
        This main is used for the Treebuilder;
     */
    public static void main(String[] args) {
        new TreeBuilder();
        //someAssingtment();
    }

    public static void someAssingtment()
    {
        try {
            new Traverser(new Node("Robert")).inorderTraversing();
            System.out.println("");
            new Traverser(new Node("Robert")).preorderTraversing();
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
