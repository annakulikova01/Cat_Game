import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Item_Listener implements ActionListener{

	private Cat cat;
	
	private GUI gui;
	
	private JButton inventoryBox;
	
	private JButton holdingButton;
	
	
	
	public Item_Listener(Cat cat, GUI gui, JButton inventoryBox, JButton holdingButton) {
		this.cat = cat;
		this.gui = gui;
		this.inventoryBox = inventoryBox;
		this.holdingButton = holdingButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gui.getInventoryBoxFull().equals(false)){
			Item buttonClicked = (Item) e.getSource();
			if (buttonClicked.getVisibility().equals(true)) {
			inventoryBox.setIcon(buttonClicked.getInventoryImage());
			inventoryBox.setVisible(true);
			cat.getItem(buttonClicked);
			buttonClicked.setVisible(false);
			buttonClicked.setVisibility(false);
			holdingButton.setVisible(true);
			gui.setInventoryBoxFull(true);
			}
		}
	}

}
