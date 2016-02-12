import javax.swing.*;
import java.awt.*;

public class DobbelsteenMVC extends JApplet
{
	DobbelsteenModel model;             // model
	TekstView tekstView;              	// view
	DobbelsteenView dobbelsteenView;  	// view
	StatistiekenView statistiekenView;	// view
	DobbelsteenController controller;   // Controller
	
	public void init()
	{
		resize(250,200);
        
		// Maak het model
		model = new DobbelsteenModel();
        
        // Maak de controller en geef hem het model
		controller = new DobbelsteenController(model);
        controller.setBackground(Color.cyan);
        getContentPane().add(controller,BorderLayout.NORTH);
        
        // Maak de views
        dobbelsteenView = new DobbelsteenView(Color.red);
        dobbelsteenView.setBackground(Color.black);
        getContentPane().add(dobbelsteenView,BorderLayout.CENTER);
        
        tekstView = new TekstView();
        tekstView.setBackground(Color.green);
        getContentPane().add(tekstView,BorderLayout.SOUTH);
        
        statistiekenView = new StatistiekenView(model);
        statistiekenView.setBackground(Color.ORANGE);
        getContentPane().add(statistiekenView, BorderLayout.WEST);
        
        // Registreer de views bij het model
        model.addActionListener(tekstView);
        model.addActionListener(dobbelsteenView);
        model.addActionListener(statistiekenView);
        
        // Eerste worp
        model.gooi();
	}
}
