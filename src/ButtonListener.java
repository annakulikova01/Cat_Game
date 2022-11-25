import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;

public class ButtonListener implements ActionListener {
	
	private JLayeredPane layeredPaneHome;
	private JLayeredPane layeredPanePlay;
	private JLayeredPane superPane;

	
	public ButtonListener(JLayeredPane layeredPaneHome, JLayeredPane layeredPanePlay, JLayeredPane superPane) {
		this.layeredPaneHome = layeredPaneHome;
		this.layeredPanePlay = layeredPanePlay;
		this.superPane = superPane;
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		superPane.setLayer(layeredPaneHome, 0);
		superPane.setLayer(layeredPanePlay, 1);

	}

}
