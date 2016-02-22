package classifier;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

/**
 * Created by Leon Wetzel
 * Date of creation 22-2-2016, 14:18
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
public class CompanyTree {
    private static DefaultMutableTreeNode root;

    public static void main(String[] args) {
        new CompanyTree();
    }

    /**
     * Constructs the company tree.
     */
    public CompanyTree() {
        populateTree();
        print(root.postorderEnumeration());
        print(root.preorderEnumeration());
        print(root.breadthFirstEnumeration());
    }

    /**
     * Populates the tree.
     */
    public static void populateTree() {
        root = new DefaultMutableTreeNode(new Node("person"));
        DefaultMutableTreeNode employee = new DefaultMutableTreeNode("employee");
        DefaultMutableTreeNode customer = new DefaultMutableTreeNode("customer");
        root.add(employee);
        root.add(customer);

        employee.add(new DefaultMutableTreeNode("sales_rep"));
        employee.add(new DefaultMutableTreeNode("engineer"));

        DefaultMutableTreeNode us_customer = new DefaultMutableTreeNode("us_customer");
        customer.add(us_customer);
        customer.add(new DefaultMutableTreeNode("not_us_customer"));

        us_customer.add(new DefaultMutableTreeNode("local_customers"));
        us_customer.add(new DefaultMutableTreeNode("regional_customers"));
    }

    /**
     * Prints the items in specific order
     */
    private static void print(Enumeration enumeration) {
        DefaultMutableTreeNode node;
        int i = 1;
        System.out.println("Next enumeration: ");
        while(enumeration.hasMoreElements()) {
            node = (DefaultMutableTreeNode)enumeration.nextElement();
            System.out.println(i + ": " + node.getUserObject().toString());
            i++;
        }
    }
}
