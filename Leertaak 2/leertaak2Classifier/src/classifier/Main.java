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
    public static void main(String[] args) {
        new TreeBuilder();
    }

    public void someAssingtment()
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
