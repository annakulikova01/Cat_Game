import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Dog_To_Room_Button_Listener implements ActionListener{
	private Dog dog;
	private Dog_GUI doggui;
	private Dog_Room currentRoom;
	private JButton dogToRoomButton1;
	private JButton dogToRoomButton2;
	private JLabel dogCurrentRoomPicture;
	
	public Dog_To_Room_Button_Listener(Dog dog, Dog_GUI doggui, Dog_Room currentRoom, JButton dogToRoomButton1, JButton dogToRoomButton2, JLabel dogCurrentRoomPicture) {
		this.dog = dog;
		this.currentRoom = currentRoom;
		this.dogToRoomButton1 = dogToRoomButton1;
		this.dogToRoomButton2 = dogToRoomButton2;
		this.dogCurrentRoomPicture = dogCurrentRoomPicture;
	}
	
	public void resetRoomItemsVisbility() {
		ArrayList<Dog_Item> currentRoomItems = dog.getCurrentRoomItemsList();
		for (Dog_Item i : currentRoomItems) {
			i.setVisible(false);
			i.setVisibilityDog(false);
	    }
	}
	
	public void makeRoomItemsVisible() {
		ArrayList<Dog_Item> currentRoomItems = dog.getCurrentRoomItemsList();
		for (Dog_Item i : currentRoomItems) {
			i.setVisible(true);
			i.setVisibilityDog(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton dogbuttonClicked = (JButton) e.getSource();
		
		//gets the invisible text of that particular button, so that it serves as an identifier
		String dogbuttonText = dogbuttonClicked.getText();
		int dogindexOfFirstSpace = dogbuttonText.indexOf(" ");
		String dogexit = dogbuttonText.substring(dogindexOfFirstSpace + 1);
		this.resetRoomItemsVisbility();
		//changes the room based on the text of the button
		dog.goDog(dogexit);
		currentRoom = dog.getDogCurrentRoom();
		this.makeRoomItemsVisible();
		
		
		ImageIcon roomPicDog = dog.getDogRoomImage();
		Image roomPicOldDog = roomPicDog.getImage();
		Image roomPicResizedDog = roomPicOldDog.getScaledInstance(1000, 750, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedRoomPicDog = new ImageIcon(roomPicResizedDog);
		dogCurrentRoomPicture.setIcon(resizedRoomPicDog);
		dogToRoomButton1.setText(dog.getDogRoomButton1Name());
		dogToRoomButton2.setText(dog.getDogRoomButton2Name());
		ImageIcon exitButton1PicDog = dog.getDogRoomButton1Image();
		Image exitButton1OldDog = exitButton1PicDog.getImage();
		Image exitButton1ResizedDog = exitButton1OldDog.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton1Dog = new ImageIcon(exitButton1ResizedDog);
		dogToRoomButton1.setIcon(resizedExitButton1Dog);
		ImageIcon exitButton2Pic = dog.getDogRoomButton2Image();
		Image exitButton2Old = exitButton2Pic.getImage();
		Image exitButton2Resized = exitButton2Old.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton2 = new ImageIcon(exitButton2Resized);
		dogToRoomButton2.setIcon(resizedExitButton2);
		dogToRoomButton1.setContentAreaFilled(false);
		dogToRoomButton1.setFocusPainted(false);
		dogToRoomButton1.setBorderPainted(false);
		dogToRoomButton2.setContentAreaFilled(false);
		dogToRoomButton2.setFocusPainted(false);
		dogToRoomButton2.setBorderPainted(false);
	}

}
