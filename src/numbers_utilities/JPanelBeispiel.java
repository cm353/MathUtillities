package numbers_utilities;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
 
public class JPanelBeispiel
{
    // main-Methode
    public static void main(String[] args)
    {
        // Erzeugung eines neuen Dialoges
        JDialog meinJDialog = new JDialog();
        meinJDialog.setTitle("JPanel Beispiel");
        meinJDialog.setSize(450,300);
 
        JPanel panel = new JPanel();
        // Hier setzen wir die Hintergrundfarbe unseres JPanels auf rot

        // Hier fügen wir unserem Dialog unser JPanel hinzu
        meinJDialog.add(panel);
        // Wir lassen unseren Dialog anzeigen
        meinJDialog.setVisible(true);
    }
}