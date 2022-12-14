import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.rmi.CORBA.Util;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Dog_GUI extends Observable  {
	
	private Dog dog;
	
	

	
	
	private Cat_MUD cmDog;
	
	private Boolean doginventoryBoxFull = false;
	
	
	
	
	
	private ArrayList<Dog_Item> dogitemLabels;
	
	JLabel dogowner1PicPlay;
	
	JLabel dogowner2PicPlay;
	
	JButton doginventoryBox = new JButton();
	
	JButton dogholdingButton = new JButton();
	
	JLayeredPane doglayeredPanePlay;
	
	
	
	//creates a JButton on the left side of the screen
	JButton dogtoRoomButton1 = new JButton();
		
	//creates a JButton on the right side of the screen
	JButton dogtoRoomButton2 = new JButton();
		
	//creates a JLabel that will display autolook text and observe after the player makes a change, such as moving to a new room or picking up and object
	JLabel dogoutput = new JLabel();
	
	
	public Dog_GUI(Dog dog, Cat_MUD cmDog) {
		
		this.dog = dog;
		
		this.cmDog = cmDog;
		
		
	 
		this.dogitemLabels = cmDog.getDogItemLabels();
		
	}
	
	
	public Boolean getDogInventoryBoxFull() {
		return doginventoryBoxFull;
	}
	
	public void setDogInventoryBoxFull(Boolean input) {
		doginventoryBoxFull = input;
	}
	
	
	public Boolean isDogInventoryFull() {
		if (doginventoryBox.getIcon()==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setDogInventoryIcon(ImageIcon image) {
		doginventoryBox.setIcon(image);
	}
	//creates a method that changes the text of the button on the left so that it corresponds to the first exit of the new room
	public void changeDogButton1Text() {
		dogtoRoomButton1.setText(dog.getDogRoomButton1Name());
	}
	
	//creates a method that changes the text of the button on the right so that it corresponds to the second exit of the new room
	public void changeDogButton2Text() {
		dogtoRoomButton2.setText(dog.getDogRoomButton2Name());
	}
	
	
	
	
	//gets the text from the button
	public String getDogButtonText(JButton button) {
		String text = button.getText();
		return text;
	}

	
	
	private void addDogItemLabels() {
		for (Dog_Item i : dogitemLabels) {
			doglayeredPanePlay.add(i, Integer.valueOf(1));
			//i.addActionListener(new Item_Listener(cat, this, inventoryBox, holdingButton));
			
		}
	}
	
	public Boolean dogInventoryFull(Boolean input) {
		doginventoryBoxFull = input;
		return doginventoryBoxFull;
	}
	
	
	
	
	
	public void run() throws Exception {
		
		JFrame dogWindow = new JFrame("Untitled Cat Game");
		
		
		
		
		//creates a layered panel that will contain the home page which fits the whole frame
		JLayeredPane dogLayeredPaneHome = new JLayeredPane();
		dogLayeredPaneHome.setBounds(0, 0, 1000, 1000);
		
		//creates a background that will be colored green
		JLabel dogbg = new JLabel();
		ImageIcon doggreenbg = new ImageIcon("Images/greenBG.jpg");
		Image dogbgOld = doggreenbg.getImage();
		Image dogbgResized = dogbgOld.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
		doggreenbg = new ImageIcon(dogbgResized);
		dogbg.setIcon(doggreenbg);
		dogbg.setBounds(0, 0, 1000, 1000);
		
		//adds the background on the deepest layer of the home page layered pane
		dogLayeredPaneHome.add(dogbg, Integer.valueOf(0));
		
		//creates a JLabel that contains an image of the title of the game, which is resized, and placed at a specific place in the layered pane 
		JLabel catGameDog = new JLabel();
		ImageIcon catGameTitleDog = new ImageIcon("Images/Untitled_Cat_Game.png");
		Image catGameOldDog = catGameTitleDog.getImage();
		Image catGameResizedDog = catGameOldDog.getScaledInstance(750, 100, java.awt.Image.SCALE_SMOOTH);
		catGameTitleDog = new ImageIcon(catGameResizedDog);
		catGameDog.setIcon(catGameTitleDog);
		catGameDog.setBounds(125, 25, 750, 100);
		
		//adds the title image to a layer above the background
		dogLayeredPaneHome.add(catGameDog, Integer.valueOf(1));
		
		
		
		//creates a JLabel that contains a picture of the cat, and places it within the layered pane
		JLabel catPicDog = new JLabel();
		ImageIcon catHomePicDog = new ImageIcon("Images/cat.jpg");
		//Image catPicOld = catHomePic.getImage();
		//Image catPicResized = catPicOld.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
		//catHomePic = new ImageIcon(catPicResized);
		catPicDog.setIcon(catHomePicDog);
		//catPic.setText("Untitled Cat Game");
		//catPic.setHorizontalTextPosition(JLabel.CENTER);
		//catPic.setVerticalTextPosition(JLabel.TOP);
		//catPic.setIconTextGap(25);
		catPicDog.setForeground(Color.WHITE);
		catPicDog.setVerticalAlignment(SwingConstants.CENTER);
		catPicDog.setHorizontalAlignment(SwingConstants.CENTER);
		catPicDog.setBounds(250, 150, 500, 400);
		
		//adds the picture of the cat above the background layer
		dogLayeredPaneHome.add(catPicDog, Integer.valueOf(1));
		
		
		
		
		//creates a new layered pane for the gameplay screen, which will be situated below the home page at the
		//beginning of the game so that it is not seen upon launching
		doglayeredPanePlay = new JLayeredPane();
		doglayeredPanePlay.setBounds(0, 0, 1000, 750);
		
		//adds a JLabel that contains an image of the room that the cat is placed in at the start of the game
		JLabel dogCurrentRoomPicture = new JLabel();
		dogCurrentRoomPicture.setBounds(0, 0, 1000, 750);
		dogCurrentRoomPicture.setIcon(dog.getDogRoomImage());
		
		//adds the image of the current room to the background layer
		doglayeredPanePlay.add(dogCurrentRoomPicture, Integer.valueOf(0));
		
		this.addDogItemLabels();
		
		//creates a JLabel that contains an image of the cat, and places it at a layer above the image of the room
		//so that it appears that the cat is in the room
		JButton catPicPlayDog = new JButton();
		catPicPlayDog.setBounds(220,515, 250, 250);
		ImageIcon catPlayPicDog = new ImageIcon("Images/cat.png");
		Image catPlayOldDog = catPlayPicDog.getImage();
		Image catPlayResizedDog = catPlayOldDog.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		catPlayPicDog = new ImageIcon(catPlayResizedDog);
		catPicPlayDog.setIcon(catPlayPicDog);
		catPicPlayDog.setContentAreaFilled(false);
		catPicPlayDog.setFocusPainted(false);
		catPicPlayDog.setBorderPainted(false);
		
		
		
		doglayeredPanePlay.add(catPicPlayDog, Integer.valueOf(2));
		
	
		
	
		dogowner1PicPlay = new JLabel();
		dogowner1PicPlay.setBounds(25, 220, 235, 550);
		ImageIcon owner1PlayPic = new ImageIcon("Images/owner1.png");
		Image owner1PlayOld = owner1PlayPic.getImage();
		Image owner1PlayResized = owner1PlayOld.getScaledInstance(235, 550, java.awt.Image.SCALE_SMOOTH);
		owner1PlayPic = new ImageIcon(owner1PlayResized);
		dogowner1PicPlay.setIcon(owner1PlayPic);
		dogowner1PicPlay.setVisible(false);
		
		
		
		doglayeredPanePlay.add(dogowner1PicPlay, Integer.valueOf(2));
		
		dogowner2PicPlay = new JLabel();
		dogowner2PicPlay.setBounds(740, 220, 235, 550);
		ImageIcon owner2PlayPic = new ImageIcon("Images/owner2.png");
		Image owner2PlayOld = owner2PlayPic.getImage();
		Image owner2PlayResized = owner2PlayOld.getScaledInstance(235, 550, java.awt.Image.SCALE_SMOOTH);
		owner2PlayPic = new ImageIcon(owner2PlayResized);
		dogowner2PicPlay.setIcon(owner2PlayPic);
		dogowner2PicPlay.setVisible(false);
		
		doglayeredPanePlay.add(dogowner2PicPlay, Integer.valueOf(2));
		
		ImageIcon holding = new ImageIcon("Images/holdingbutton.png");
		ImageIcon holdingNew = new ImageIcon("Images/holdingbutton2.png");
		
		int holdingWidth = holding.getIconWidth();
		int holdingHeight = holding.getIconHeight();
		
		Image holdingOld = holdingNew.getImage();
		Image holdingResized = holdingOld.getScaledInstance(holdingWidth, holdingHeight, java.awt.Image.SCALE_SMOOTH);
		holdingNew = new ImageIcon(holdingResized);
		
		//holdingButton = new JLabel();
		dogholdingButton.setBounds(50, 30, 130, 44);
		dogholdingButton.setIcon(holdingNew);
		dogholdingButton.setVisible(false);
			
		
		doglayeredPanePlay.add(dogholdingButton, Integer.valueOf(3));
		
		
		
		
		doginventoryBox.setBounds(50, 90, 130, 130);
		doginventoryBox.setVisible(true);
		doginventoryBox.setIcon(null);
		doginventoryBox.setContentAreaFilled(false);
		doginventoryBox.setFocusPainted(false);
		doginventoryBox.setBorderPainted(false);
		Random random = new Random();
		doglayeredPanePlay.add(doginventoryBox, Integer.valueOf(3));
		/*
		inventoryBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inventoryBoxFull.equals(true)) {
					JButton buttonClicked = (JButton) e.getSource();
					
					for (Item i : itemLabels) {
						if (buttonClicked.getIcon().equals(i.getInventoryImage())){
							//inventoryBox.setBounds(500, 500, 130, 130);
							inventoryBox.setVisible(false);
							//inventoryBox.setIcon(null);
							
							inventoryBoxFull = false;
							
							
							
							
							cat.dropItem(i);
							holdingButton.setVisible(false);
							
							if (!i.getOriginalRoom().equals(cat.getCurrentRoom())) {
								int xmin = 100;
								int xmax = 900;
								int x = xmin + (int)(Math.random() * (xmax - xmin));
								int ymin = 100;
								int ymax = 500;
								int y = ymin + (int) (Math.random() * (ymax - ymin));	
								i.setBounds(x, y, i.getIconWidth(), i.getIconHeight());
								i.setVisible(true);
								i.setVisibility(true);
								i.setXNew(x);
								i.setYNew(y);
								i.setWasMoved(true);
							}
							if (i.getOriginalRoom().equals(cat.getCurrentRoom())) {
								i.setBounds(i.getXOrig(), i.getYOrig(), i.getIconWidth(), i.getIconHeight());
								i.setVisible(true);
								i.setVisibility(true);
								i.setXNew(i.getXOrig());
								i.setYNew(i.getYOrig());
								if (i.getWasMoved().equals(true)) {
								}
								i.setWasMoved(false);
							}
							
							
						}
						
					}
				}
			}
		});
		*/
		
		
		
		
		
		//creates a button upon which, when clicked, terminates the program
		JButton exitButtonDog = new JButton();
		ImageIcon exitDog = new ImageIcon("Images/exitButton.png");
		Image exitButtonOldDog = exitDog.getImage();
		Image exitButtonResizedDog = exitButtonOldDog.getScaledInstance(180, 100, java.awt.Image.SCALE_SMOOTH);
		exitDog = new ImageIcon(exitButtonResizedDog);
		exitButtonDog.setIcon(exitDog);
		exitButtonDog.setContentAreaFilled(false);
		exitButtonDog.setFocusPainted(false);
		exitButtonDog.setBorderPainted(false);
		exitButtonDog.setBounds(800, 0, 180, 100);
		//exitButton.addActionListener(e -> window.dispose());
		exitButtonDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmDog.stopRunning();
				dogWindow.dispose();
			}
		});
		
		doglayeredPanePlay.add(exitButtonDog, Integer.valueOf(1));
		
		
		//styles the buttons so that the text isnt visible and is set only for identification purposes, and makes the border and content areas
		//of the buttons transparent so that only the images set for the buttons are visible. Then, the buttons are added to the gameplay layered
		//pane above the background layer
		dogtoRoomButton1.setBounds(25,600,200,85);
		dogtoRoomButton1.setText(dog.getDogRoomButton1Name());
		ImageIcon exitButton1PicDog = dog.getDogRoomButton1Image();
		Image exitButton1OldDog = exitButton1PicDog.getImage();
		Image exitButton1ResizedDog = exitButton1OldDog.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton1Dog = new ImageIcon(exitButton1ResizedDog);
		dogtoRoomButton1.setIcon(resizedExitButton1Dog);
		dogtoRoomButton1.setContentAreaFilled(false);
		dogtoRoomButton1.setFocusPainted(false);
		dogtoRoomButton1.setBorderPainted(false);
		dogtoRoomButton1.addActionListener(new Dog_To_Room_Button_Listener(dog, this, dog.getDogCurrentRoom(), dogtoRoomButton1, dogtoRoomButton2, dogCurrentRoomPicture));
		
		doglayeredPanePlay.add(dogtoRoomButton1, Integer.valueOf(3));
		
		
		
		dogtoRoomButton2.setBounds(775,600,200,85);
		dogtoRoomButton2.setText(dog.getDogRoomButton2Name());
		ImageIcon exitButton2PicDog = dog.getDogRoomButton2Image();
		Image exitButton2OldDog = exitButton2PicDog.getImage();
		Image exitButton2ResizedDog = exitButton2OldDog.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton2Dog = new ImageIcon(exitButton2ResizedDog);
		dogtoRoomButton2.setIcon(resizedExitButton2Dog);
		dogtoRoomButton2.setContentAreaFilled(false);
		dogtoRoomButton2.setFocusPainted(false);
		dogtoRoomButton2.setBorderPainted(false);
		dogtoRoomButton2.addActionListener(new Dog_To_Room_Button_Listener(dog, this, dog.getDogCurrentRoom(), dogtoRoomButton1, dogtoRoomButton2, dogCurrentRoomPicture));
		
		doglayeredPanePlay.add(dogtoRoomButton2, Integer.valueOf(3));
		
		//creates another layered pane which will contain the home page layered pane and gameplay layered pane so that the two screens can be toggled from using a button
		JLayeredPane dogsuperPane = new JLayeredPane();
		dogsuperPane.setBounds(0, 0, 1000, 1400);

		//creates the play button which will change the layers of the home page and gameplay page so that, once clicked, the gameplay page is on top
		JButton dogplayButton = new JButton();
		ImageIcon dogplay = new ImageIcon("Images/startButton.png");
		Image dogplayButtonOld = dogplay.getImage();
		Image dogplayButtonResized = dogplayButtonOld.getScaledInstance(275, 150, java.awt.Image.SCALE_SMOOTH);
		dogplay = new ImageIcon(dogplayButtonResized);
		dogplayButton.setIcon(dogplay);
		dogplayButton.setContentAreaFilled(false);
		dogplayButton.setFocusPainted(false);
		dogplayButton.setBorderPainted(false);
		dogplayButton.addActionListener(new ButtonListener(dogLayeredPaneHome, doglayeredPanePlay, dogsuperPane));
		dogplayButton.setBounds(310, 550, 325, 150);
		
		dogLayeredPaneHome.add(dogplayButton, Integer.valueOf(1));
		
		dogsuperPane.add(dogLayeredPaneHome, Integer.valueOf(1));
		dogsuperPane.add(doglayeredPanePlay, Integer.valueOf(0));
		//superPane.add(heartPane, Integer.valueOf(3));
	            
		
		dogWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//sets the layour manager to null so that each component can be manually positioned
		dogWindow.setLayout(null);
		dogWindow.setSize(1000, 750);
		dogWindow.add(dogsuperPane);
		dogWindow.setResizable(false);
		dogWindow.setVisible(true);
	}
	/*
	@Override
	public void update(Observable o, Object arg) {
		if (this.dog.getInRoomWithMob1().equals(true)) {
			this.owner1PicPlay.setVisible(true);
		}
		if (this.dog.getInRoomWithMob1().equals(false)){
			this.owner1PicPlay.setVisible(false);
			
		}
		if (this.dog.getInRoomWithMob2().equals(true)) {
			this.owner2PicPlay.setVisible(true);
			
		}
		if (this.dog.getInRoomWithMob2().equals(false)){
			this.owner2PicPlay.setVisible(false);
			
		}
		
	}

	*/

	
}

