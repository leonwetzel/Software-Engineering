package mvc;

import classifier.TreeBuilder;

import javax.swing.*;
import java.applet.*;
import java.awt.*;

public class Test extends Applet
{
    private TreeBuilder tree = new TreeBuilder();

    public void init() {
        setLayout(null);
        JCheckBox checkbox;
        int start = 20;
        for(String item : tree.getModules()) {
            checkbox = new JCheckBox(item);
            checkbox.setBounds(start, 120, 100, 30);
            add(checkbox);
            start += 120;
        }

        JButton submit = new JButton("Submit");
        submit.setBounds(20,240,100,30);
        add(submit);
    }

}