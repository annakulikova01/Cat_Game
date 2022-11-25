
import java.awt.Color;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI implements Observer {
	
	private Cat cat;

	private Cat_MUD cm;
	
	public GUI(Cat cat, Cat_MUD cm) {
		
		this.cat = cat;
		this.cm = cm;
		
	}
	
	//creates a JButton on the left side of the screen
	JButton toRoomButton1 = new JButton();
	
	//creates a JButton on the right side of the screen
	JButton toRoomButton2 = new JButton();
	
	//creates a JLabel that will display autolook text and observe after the player makes a change, such as moving to a new room or picking up and object
	JLabel output = new JLabel();
	
	// 
	JLabel owner1PicPlay;
	
	JLabel owner2PicPlay;
	
	//creates a method that changes the text of the button on the left so that it corresponds to the first exit of the new room
	public void changeButton1Text() {
		toRoomButton1.setText(cat.getRoomButton1Name());
	}
	
	//creates a method that changes the text of the button on the right so that it corresponds to the second exit of the new room
	public void changeButton2Text() {
		toRoomButton2.setText(cat.getRoomButton2Name());
	}
	
	//updates the text in the output JLabel
	public void changeOutputText() {
		output.setText(cat.autoLookText());
	}
	
	//gets the text from the button
	public String getButtonText(JButton button) {
		String text = button.getText();
		return text;
	}
	
	
	
	public void setNotVisible(JLabel label) {
		label.setVisible(false);
	}
	
	public void setVisible(JLabel label) {
		label.setVisible(true);
	}
	
	public void run() {
		
		JFrame window = new JFrame("Untitled Cat Game");
		
		//creates a layered panel that will contain the home page which fits the whole frame
		JLayeredPane layeredPaneHome = new JLayeredPane();
		layeredPaneHome.setBounds(0, 0, 1000, 1000);
		
		//creates a background that will be colored green
		JLabel bg = new JLabel();
		ImageIcon greenbg = new ImageIcon("Images/greenBG.jpg");
		Image bgOld = greenbg.getImage();
		Image bgResized = bgOld.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
		greenbg = new ImageIcon(bgResized);
		bg.setIcon(greenbg);
		bg.setBounds(0, 0, 1000, 1000);
		
		//adds the background on the deepest layer of the home page layered pane
		layeredPaneHome.add(bg, Integer.valueOf(0));
		
		//creates a JLabel that contains an image of the title of the game, which is resized, and placed at a specific place in the layered pane 
		JLabel catGame = new JLabel();
		ImageIcon catGameTitle = new ImageIcon("Images/Untitled_Cat_Game.png");
		Image catGameOld = catGameTitle.getImage();
		Image catGameResized = catGameOld.getScaledInstance(750, 100, java.awt.Image.SCALE_SMOOTH);
		catGameTitle = new ImageIcon(catGameResized);
		catGame.setIcon(catGameTitle);
		catGame.setBounds(125, 25, 750, 100);
		
		//adds the title image to a layer above the background
		layeredPaneHome.add(catGame, Integer.valueOf(1));
		
		
		
		//creates a JLabel that contains a picture of the cat, and places it within the layered pane
		JLabel catPic = new JLabel();
		ImageIcon catHomePic = new ImageIcon("Images/cat.jpg");
		//Image catPicOld = catHomePic.getImage();
		//Image catPicResized = catPicOld.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
		//catHomePic = new ImageIcon(catPicResized);
		catPic.setIcon(catHomePic);
		//catPic.setText("Untitled Cat Game");
		//catPic.setHorizontalTextPosition(JLabel.CENTER);
		//catPic.setVerticalTextPosition(JLabel.TOP);
		//catPic.setIconTextGap(25);
		catPic.setForeground(Color.WHITE);
		catPic.setVerticalAlignment(SwingConstants.CENTER);
		catPic.setHorizontalAlignment(SwingConstants.CENTER);
		catPic.setBounds(250, 150, 500, 400);
		
		//adds the picture of the cat above the background layer
		layeredPaneHome.add(catPic, Integer.valueOf(1));
		
		
		
		
		//creates a new layered pane for the gameplay screen, which will be situated below the home page at the
		//beginning of the game so that it is not seen upon launching
		JLayeredPane layeredPanePlay = new JLayeredPane();
		layeredPanePlay.setBounds(0, 0, 1000, 1000);
		
		//adds a JLabel that contains an image of the room that the cat is placed in at the start of the game
		JLabel currentRoomPicture = new JLabel();
		currentRoomPicture.setBounds(0, 0, 1000, 750);
		currentRoomPicture.setIcon(cat.getRoomImage());
		
		//adds the image of the current room to the background layer
		layeredPanePlay.add(currentRoomPicture, Integer.valueOf(0));
		
		
		//creates a JLabel that contains an image of the cat, and places it at a layer above the image of the room
		//so that it appears that the cat is in the room
		JLabel catPicPlay = new JLabel();
		catPicPlay.setBounds(375,515, 250, 250);
		ImageIcon catPlayPic = new ImageIcon("Images/cat.png");
		Image catPlayOld = catPlayPic.getImage();
		Image catPlayResized = catPlayOld.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		catPlayPic = new ImageIcon(catPlayResized);
		catPicPlay.setIcon(catPlayPic);
		
		layeredPanePlay.add(catPicPlay, Integer.valueOf(1));
		
		owner1PicPlay = new JLabel();
		owner1PicPlay.setBounds(25, 150, 157, 400);
		ImageIcon owner1PlayPic = new ImageIcon("Images/owner1.png");
		Image owner1PlayOld = owner1PlayPic.getImage();
		Image owner1PlayResized = owner1PlayOld.getScaledInstance(157, 400, java.awt.Image.SCALE_SMOOTH);
		owner1PlayPic = new ImageIcon(owner1PlayResized);
		owner1PicPlay.setIcon(owner1PlayPic);
		owner1PicPlay.setVisible(false);
		
		
		
		layeredPanePlay.add(owner1PicPlay, Integer.valueOf(1));
		
		owner2PicPlay = new JLabel();
		owner2PicPlay.setBounds(818, 150, 157, 400);
		ImageIcon owner2PlayPic = new ImageIcon("Images/owner2.png");
		Image owner2PlayOld = owner2PlayPic.getImage();
		Image owner2PlayResized = owner2PlayOld.getScaledInstance(157, 400, java.awt.Image.SCALE_SMOOTH);
		owner2PlayPic = new ImageIcon(owner2PlayResized);
		owner2PicPlay.setIcon(owner2PlayPic);
		owner2PicPlay.setVisible(false);
		
		layeredPanePlay.add(owner2PicPlay, Integer.valueOf(1));
		
		
		//creates a JLabel that lists the commands that the player can type in to the input box, and adds it to the background layer
		JLabel commands = new JLabel("Commands: grab <it>, bat <it>,"
    			+ "drop <it>, meow, glare, charm, and observe");
		commands.setBackground(new Color(216, 191, 216));
		commands.setOpaque(true);
		commands.setBounds(0, 750, 700, 85);
		commands.setVerticalAlignment(SwingConstants.TOP);
		
		layeredPanePlay.add(commands, Integer.valueOf(0));
		
		//positions and styles the output JLabel, and sets its text to the autolookText of the current room the cat is in 
		//at the start of the game, and adds it to the background layer of the gameplay screen
		output.setBackground(new Color(216, 191, 216));
		output.setOpaque(true);
		output.setBounds(0, 835, 1000, 164);
		output.setText(cat.autoLookText());
		output.setVerticalAlignment(SwingConstants.TOP);
		
		layeredPanePlay.add(output, Integer.valueOf(0));
		
		//creates an input text field that interprets the commands of the user and executes the corresponding methods, and adds it to the background layer
		JTextField input = new JTextField();
		input.setBounds(700, 750, 300, 85);
		input.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent evt) {
	    		  JTextField input = (JTextField)evt.getSource();
	    		  String command;
	    		  String item;
	    		  Scanner s = new Scanner(input.getText());
	    		  command = s.next();
	    		  
	    		  switch (command) {
	        		
	        		case "exit":
	        			break;
	        			
	        		case "observe":
	        			output.setText(cat.observe());
	        			break;
	        		/*	
	        		case "bat":
	        			item = s.next();
	        			if (!cat.batItem(item))
	        				System.out.println("Couldn't bat at " + item);
	        			break;
	        		*/
	        		case "grab":
	        			item = s.next();
	        			if (!cat.getItem(item))
	        				output.setText("Couldn't get " + item);
	        			else output.setText(cat.autoLookText());
	        			break;
	        			
	        		case "drop":
	        			item = s.next();
	        			if (!cat.dropItem(item))
	        				output.setText("Couldn't drop " + item);
	        			else output.setText(cat.autoLookText());
	        			break;
	        		
	        		case "meow":
	        			output.setText("meow");
	        			break;
	        		/*
	        		case "glare":
	        			System.out.println("3:<");
	        			if (!cat.glare())
	        				System.out.println("There's no one around to glare at.");
	        			break;
	        		*/
	        		/*	
	        		case "charm":
	        			System.out.println(":3");
	        			if (!cat.charm())
	        				System.out.println("There's no one around to charm.");
	        			break;
	        		*/	
	        		default:
	        			output.setText("Unrecognized command");
	        			break;
	        			}
	 	
	        		}
	    	  
	      });
		
		layeredPanePlay.add(input, Integer.valueOf(0));
		
		//creates a button upon which, when clicked, terminates the program
		JButton exitButton = new JButton();
		ImageIcon exit = new ImageIcon("Images/exitButton.png");
		Image exitButtonOld = exit.getImage();
		Image exitButtonResized = exitButtonOld.getScaledInstance(180, 100, java.awt.Image.SCALE_SMOOTH);
		exit = new ImageIcon(exitButtonResized);
		exitButton.setIcon(exit);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setBorderPainted(false);
		exitButton.setBounds(800, 0, 180, 100);
		//exitButton.addActionListener(e -> window.dispose());
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.stopRunning();
				window.dispose();
			}
		});
		
		layeredPanePlay.add(exitButton, Integer.valueOf(1));
		
		
		//styles the buttons so that the text isnt visible and is set only for identification purposes, and makes the border and content areas
		//of the buttons transparent so that only the images set for the buttons are visible. Then, the buttons are added to the gameplay layered
		//pane above the background layer
		toRoomButton1.setBounds(25,600,200,85);
		toRoomButton1.setText(cat.getRoomButton1Name());
		ImageIcon exitButton1Pic = cat.getRoomButton1Image();
		Image exitButton1Old = exitButton1Pic.getImage();
		Image exitButton1Resized = exitButton1Old.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton1 = new ImageIcon(exitButton1Resized);
		toRoomButton1.setIcon(resizedExitButton1);
		toRoomButton1.setContentAreaFilled(false);
		toRoomButton1.setFocusPainted(false);
		toRoomButton1.setBorderPainted(false);
		toRoomButton1.addActionListener(new To_Room_ButtonListener(cat, toRoomButton1, toRoomButton2, output, currentRoomPicture));
		
		layeredPanePlay.add(toRoomButton1, Integer.valueOf(1));
		
		
		
		toRoomButton2.setBounds(775,600,200,85);
		toRoomButton2.setText(cat.getRoomButton2Name());
		ImageIcon exitButton2Pic = cat.getRoomButton2Image();
		Image exitButton2Old = exitButton2Pic.getImage();
		Image exitButton2Resized = exitButton2Old.getScaledInstance(200,85, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedExitButton2 = new ImageIcon(exitButton2Resized);
		toRoomButton2.setIcon(resizedExitButton2);
		toRoomButton2.setContentAreaFilled(false);
		toRoomButton2.setFocusPainted(false);
		toRoomButton2.setBorderPainted(false);
		toRoomButton2.addActionListener(new To_Room_ButtonListener(cat, toRoomButton1, toRoomButton2, output, currentRoomPicture));
		
		layeredPanePlay.add(toRoomButton2, Integer.valueOf(1));
		
		//creates another layered pane which will contain the home page layered pane and gameplay layered pane so that the two screens can be toggled from using a button
		JLayeredPane superPane = new JLayeredPane();
		superPane.setBounds(0, 0, 1000, 1400);

		//creates the play button which will change the layers of the home page and gameplay page so that, once clicked, the gameplay page is on top
		JButton playButton = new JButton();
		ImageIcon play = new ImageIcon("Images/startButton.png");
		Image playButtonOld = play.getImage();
		Image playButtonResized = playButtonOld.getScaledInstance(275, 150, java.awt.Image.SCALE_SMOOTH);
		play = new ImageIcon(playButtonResized);
		playButton.setIcon(play);
		playButton.setContentAreaFilled(false);
		playButton.setFocusPainted(false);
		playButton.setBorderPainted(false);
		playButton.addActionListener(new ButtonListener(layeredPaneHome, layeredPanePlay, superPane));
		playButton.setBounds(310, 600, 325, 150);
		
		layeredPaneHome.add(playButton, Integer.valueOf(1));
		
		superPane.add(layeredPaneHome, Integer.valueOf(1));
		superPane.add(layeredPanePlay, Integer.valueOf(0));
	            
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//sets the layour manager to null so that each component can be manually positioned
		window.setLayout(null);
		window.setSize(1000, 1000);
		window.add(superPane);
		window.setResizable(false);
		window.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (cat.getInRoomWithMob1().equals(true)) {
			owner1PicPlay.setVisible(true);
		}
		if (cat.getInRoomWithMob1().equals(false)){
			owner1PicPlay.setVisible(false);
		}
		if (cat.getInRoomWithMob2().equals(true)) {
			owner2PicPlay.setVisible(true);
		}
		if (cat.getInRoomWithMob2().equals(false)){
			owner2PicPlay.setVisible(false);
		}
		
	}
	
}
