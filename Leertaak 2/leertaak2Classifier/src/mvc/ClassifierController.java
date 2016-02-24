package mvc;

import classifier.TreeBuilder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClassifierController extends JPanel implements ActionListener {
    private TreeBuilder tree = new TreeBuilder();
    private ClassifierModel model;

    public ClassifierController(ClassifierModel model) {
        this.model = model;

        ButtonGroup group = new ButtonGroup();
        for(String item : tree.getModules()) {
            JRadioButton button = new JRadioButton(item);
            button.setActionCommand(item);
            group.add(button);
            this.add(button);
        }

    }

    /**
     * Catches events and calls corresponding methods.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Setter for size of window.
     * @return Dimension object
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,50);
    }

}

