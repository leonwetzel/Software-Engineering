package mvc;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassifierModel {
    private ArrayList<ActionListener> actionListenerList = new ArrayList<>();
    private DefaultMutableTreeNode current;
    /**
     * Constructor for a CalculatorModel object.
     */
    public ClassifierModel() {
    }

    public void addModuleOrNot(int yn)
    {
        current = (DefaultMutableTreeNode)current.getChildAt(yn);
    }

    public void addActionListener(ActionListener listener) {
        actionListenerList.add(listener);
    }

    public void removeActionListener(ActionListener listener) {
        if (actionListenerList.contains(listener)) {
            actionListenerList.remove(listener);
        }
    }

    private void processEvent(ActionEvent event) {
        for(ActionListener listeners : actionListenerList) {
            listeners.actionPerformed( event );
        }
    }
}
