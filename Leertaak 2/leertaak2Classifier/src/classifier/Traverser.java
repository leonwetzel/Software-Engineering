package classifier;

import java.util.Stack;

/**
 * Created by Leon Wetzel
 * Date of creation 18-2-2016, 13:21
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
public class Traverser {
    private Node root;

    public Traverser(Node root) {
        populateTree(root);
    }

    /**
     * Constructs the tree
     * @param root The root node of the tree.
     */
    private void populateTree(Node root) {
        this.root = root;
        Node virgil = new Node("Virgil");
        Node ilse = new Node("Ilse");
        root.addChild("Left", virgil);
        root.addChild("Right", ilse);

        Node sander = new Node("Sander");
        Node justus = new Node("Justus");
        virgil.addChild("Left", sander);
        virgil.addChild("Right", justus);

        ilse.addChild("Left", new Node("Shanna"));
        ilse.addChild("Right", new Node("Bart"));
    }

    /**
     * Traverses the tree in order.
     * Keys used in the node's map are 'Left' and 'Right', which allows
     * dynamic access to the node's children.
     */
    public void inorderTraversing() throws NoRootException {
        if (root == null) {
            throw new NoRootException("Boom bevat geen wortel!");
        }

        Stack<Node> stack = new Stack<>();
        Node node = root;

        while (node != null) {
            stack.push(node);
            node = node.follow("Left");
        }

        int i = 0;
        while (stack.size() > 0) {
            i++;
            node = stack.pop();
            System.out.println(i + ": " + node.getLabel());

            if (node.follow("Right") != null) {
                node = node.follow("Right");
                while (node != null) {
                    stack.push(node);
                    node = node.follow("Left");
                }
            }
        }
    }

    public void preoderTraversing() {
    /*
        // PREORDER
        current = root; // Begin bij de root
        while ( current is not NULL or stack is nonempty )
        if ( current is not NULL ){
            // zelf aanvullen, 3 regels
        }
        else {
            // zelf aanvullen, 2 regels
        }
    */
    }
}
