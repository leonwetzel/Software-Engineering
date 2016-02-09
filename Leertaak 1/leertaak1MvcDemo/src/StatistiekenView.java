import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatistiekenView extends JPanel implements ActionListener
{
    private JTextField steenRoodVeld = new JTextField();
    DobbelsteenModel d;

    public StatistiekenView()
    {
        this.setLayout(new FlowLayout());
        this.add(steenRoodVeld);
    }

    public void actionPerformed( ActionEvent e )
    {
        d = (DobbelsteenModel) e.getSource();
        steenRoodVeld.setText(""+d.getWaarde());
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(50,50);
    }
}