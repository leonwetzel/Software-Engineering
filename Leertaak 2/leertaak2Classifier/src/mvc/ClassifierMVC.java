package mvc;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class ClassifierMVC extends JApplet {
    private ClassifierModel model;
    private ClassifierController controller;
    private ClassifierView view;

    /**
     * Setup the application
     */
    @Override
    public void init() {
        view = new ClassifierView();
        getContentPane().add(view, BorderLayout.NORTH);

        model = new ClassifierModel();
        controller = new ClassifierController(model);

        getContentPane().add(controller, BorderLayout.CENTER);
        model.addActionListener(view);
    }
}
