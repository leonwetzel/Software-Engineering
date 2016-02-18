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
    private Stack<Node> data = new Stack<>();

    public Traverser(Node root) {
        populateTree(root);
        postorderTraversing();
    }

    private void populateTree(Node root) {
        this.root = root;
        Node virgil = new Node("Virgil");
        Node ilse = new Node("Ilse");
        virgil.addChild("Sander", new Node("Sander"));
        virgil.addChild("Justus", new Node("Justus"));
        ilse.addChild("Shanna", new Node("Shanna"));
        ilse.addChild("Bart", new Node("Bart"));
        root.addChild("Zoon", virgil);
        root.addChild("Buurvrouw", ilse);

    }

    /**
     * Traverses the tree in order.
     */
    public void postorderTraversing() {
        /*
        while(current != null && !data.isEmpty()) {
            if(current != null) {
                System.err.println(current.getLabel());
                Node now = data.peek();
                System.out.println(now.getLabel());
                data.pop();
            } else {
                // aanvullen, 3 regels
                System.out.println("hdkhsjfhsdfhsdkfhsdjfhsd");
            }
        }
        */
        Stack s = new Stack();
        Node node = root;
        while (node != null) {
            s.push(node);
            node = root.getChildren().get("Left");
        }

        while (s.size() > 0)  {
            // Visit the top node
            node = (Node)s.pop();
            System.out.println(node.getLabel());
            // Find the next node
            if (root.getChildren().get("Right") != null)  {
                node = root.getChildren().get("Right");
                // The next node to be visited is the leftmost
                while (node != null)
                {
                    s.push(node);
                    node = root.getChildren().get("Left");
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
