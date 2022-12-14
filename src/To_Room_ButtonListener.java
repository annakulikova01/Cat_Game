import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class To_Room_ButtonListener implements ActionListener{
	
	private Cat cat;
	private GUI gui;
	private Room currentRoom;
	private JButton toRoomButton1;
	private JButton toRoomButton2;
	private JLabel output;
	private JLabel currentRoomPicture;
	
	public To_Room_ButtonListener(Cat cat, GUI gui, Room currentRoom, JButton toRoomButton1, JButton toRoomButton2, JLabel output, JLabel currentRoomPicture) {
		this.cat = cat;
		this.gui = gui;
		this.currentRoom = currentRoom;
		this.toRoomButton1 = toRoomButton1;
		this.toRoomButton2 = toRoomButton2;
		this.output = output;
		this.currentRoomPicture = currentRoomPicture;
	}
	
	public void resetRoomItemsVisbility() {
		ArrayList<Item> currentRoomItems = cat.getCurrentRoomItemsList();
		for (Item i : currentRoomItems) {
			i.setVisible(false);
			i.setVisibility(false);
	    }
	}
	
	public void makeRoomItemsVisible() {
		ArrayList<Item> currentRoomItems = cat.getCurrentRoomItemsList();
		for (Item i : currentRoomItems) {
			i.setVisible(true);
			i.setVisibility(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//gets the source of which of the two exit buttons on the screen is clicked
		JButton buttonClicked = (JButton) e.getSource();
		
		//gets the invisible text of that particular button, so that it serves as an identifier
		String buttonText = buttonClicked.getText();
		int indexOfFirstSpace = buttonText.indexOf(" ");
		String exit = buttonText.substring(indexOfFirstSpace + 1);
		this.resetRoomItemsVisbility();
		//changes the room based on the text of the button
		cat.go(exit);
		currentRoom = cat.getCurrentRoom();
		this.makeRoomItemsVisible();
		
		//updates the output, image of the room, and exit buttons to correspond to the new room 
		output.setText(cat.autoLookText());
		ImageIcon roomPic = cat.getRoomImage();
		Image roomPicOld = roomPic.getImage();
		Image roomPicResized = roomPicOld.getScaledInstance(1000, 750, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedRoomPic = new ImageIcon(roomPicResized);
		currentRoomPicture.setIcon(resizedRoomPic);
		toRoomButton1.setText(cat.getRoomButton1Name());
		toRoomButton2.setText(cat.getRoomButton2Name());
		ImageIcon exitButton1Pic = cat.getRoomButton1Image();
		Image exitButton1Old = exitButton1Pic.getImage();
		Image exitButton1Resized = exitButton1Old.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton1 = new ImageIcon(exitButton1Resized);
		toRoomButton1.setIcon(resizedExitButton1);
		ImageIcon exitButton2Pic = cat.getRoomButton2Image();
		Image exitButton2Old = exitButton2Pic.getImage();
		Image exitButton2Resized = exitButton2Old.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton2 = new ImageIcon(exitButton2Resized);
		toRoomButton2.setIcon(resizedExitButton2);
		toRoomButton1.setContentAreaFilled(false);
		toRoomButton1.setFocusPainted(false);
		toRoomButton1.setBorderPainted(false);
		toRoomButton2.setContentAreaFilled(false);
		toRoomButton2.setFocusPainted(false);
		toRoomButton2.setBorderPainted(false);
		
	}

}
