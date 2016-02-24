package mvc;

import classifier.TreeBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leon Wetzel
 * Date of creation 24-2-2016, 14:38
 * |
 * Authors: Leon Wetzel
 * |
 * Version: 1.0
 * Package: mvc
 * Class:
 * Description:
 * |
 * |
 * Changelog:
 * 1.0:
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * View for input field
 * @author leonw_000
 *
 */
public class ClassifierView extends JPanel implements ActionListener {
    private JTextField inputField = new JTextField(50);
    private TreeBuilder tree = new TreeBuilder();
    private ClassifierModel model;

    public ClassifierView() {
        this.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();
        for(String item : tree.getCategories()) {
            JRadioButton button = new JRadioButton(item);
            button.setActionCommand(item);
            group.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
