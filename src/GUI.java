
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
public class GUI extends Observable implements Observer, LineListener {
	
	private Cat cat;

	private Cat_MUD cm;
	
	private Boolean inventoryBoxFull = false;
	
	private Boolean canCharm;
	
	private Boolean canCharmOwner1;
	
	private Boolean canCharmOwner2;
	
	private Boolean canTake;
	
	private int catPoints = 0;
	
	private Boolean playCompleted;
	
	private Timer gameTimer;
	
	private Timer heartsTimer;
	
	private int timeLeft = 300;
	
	private final int timerDelay = 1000; 
	
	private final int timerPeriod = 1000;
	
	int heartsLeft = 5;
	
	private ArrayList<Item> itemLabels;
	
	JLabel owner1PicPlay;
	
	JLabel owner2PicPlay;
	
	JButton inventoryBox = new JButton();
	
	JButton holdingButton = new JButton();
	
	JLayeredPane layeredPanePlay;
	
	final JLabel heartsLabel = new JLabel();
	
	//creates a JButton on the left side of the screen
	JButton toRoomButton1 = new JButton();
		
	//creates a JButton on the right side of the screen
	JButton toRoomButton2 = new JButton();
		
	//creates a JLabel that will display autolook text and observe after the player makes a change, such as moving to a new room or picking up and object
	JLabel output = new JLabel();
	
	
	public GUI(Cat cat, Cat_MUD cm, Boolean canCharm, Boolean canCharmOwner1, Boolean canCharmOwner2, Boolean canTake) {
		
		this.cat = cat;
		this.cm = cm;
		this.canCharm = canCharm;
		this.canCharmOwner1 = canCharmOwner1;
		this.canCharmOwner2 = canCharmOwner2;
		this.canTake = canTake;
	 
		this.itemLabels = cm.getItemLabels();
		
	}
	
	
	public Boolean getInventoryBoxFull() {
		return inventoryBoxFull;
	}
	
	public void setInventoryBoxFull(Boolean input) {
		inventoryBoxFull = input;
	}
	public void playSound(String filepath) {
		File audioFile = new File(filepath);
		try
		{
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.addLineListener(this);
			audioClip.open(audioStream);
			audioClip.start();
			//while (!playCompleted) {
				//ry {
					//Thread.sleep(1000);
				//} catch (InterruptedException ex) {
					//ex.printStackTrace();
				//}
			//}
			//audioClip.close();
			
		}
		catch(UnsupportedAudioFileException ex){
			System.out.println("The specified audio file is not supported");
			ex.printStackTrace();
		}catch(LineUnavailableException ex){
			System.out.println("Audio line for playing back is unavailable");
			ex.printStackTrace();
		}catch(IOException ex) {
			System.out.println("Error playing the audio file");
			ex.printStackTrace();
		}
	}
	
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		if (type == LineEvent.Type.START) {
			System.out.println("Playback started.");
		}else if (type == LineEvent.Type.STOP) {
			playCompleted = true;
			System.out.println("Playback completed.");
		}
		
	}
	
	public Boolean isInventoryFull() {
		if (inventoryBox.getIcon()==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setInventoryIcon(ImageIcon image) {
		inventoryBox.setIcon(image);
	}
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

	private void setCanCharmOwner1(Boolean value) {
		canCharmOwner1 = value;
	}
	
	private void setCanCharmOwner2(Boolean value) {
		canCharmOwner2 = value;
	}
	
	private void addItemLabels() {
		for (Item i : itemLabels) {
			layeredPanePlay.add(i, Integer.valueOf(1));
			i.addActionListener(new Item_Listener(cat, this, inventoryBox, holdingButton));
			
		}
	}
	
	public Boolean inventoryFull(Boolean input) {
		inventoryBoxFull = input;
		return inventoryBoxFull;
	}
	
	
	private void setupTimer() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                if (timeLeft == 1) {
                    timeLeft--;
                    gameTimer.cancel();
                    
                } else {
                    timeLeft--;
                    //System.out.println("Seconds left: " + timeLeft);
                }
            }
        }, timerDelay, timerPeriod);
    }
	
	private void heartsTimer() {

        Timer heartsTimer = new Timer();
    	
    	final int heartsDelay = 0; 
    	
    	final int heartsPeriod = 1000;
        heartsTimer.scheduleAtFixedRate(new TimerTask() {
        	
            public void run() {
            	
        		
        		
                if (heartsLeft == 1) {
                    heartsLeft--;
                    System.out.println("Timer stopped");
                    heartsLabel.setVisible(false);
                    heartsTimer.cancel();
                    
                } else {
                    heartsLeft--;
                    System.out.println("Seconds left: " + heartsLeft);
                }
            }
        }, heartsDelay, heartsPeriod);
    }
	public void run() throws Exception {
		
		JFrame window = new JFrame("Untitled Cat Game");
		
		canCharm = false;
		setupTimer();
		
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
		layeredPanePlay = new JLayeredPane();
		layeredPanePlay.setBounds(0, 0, 1000, 750);
		
		//adds a JLabel that contains an image of the room that the cat is placed in at the start of the game
		JLabel currentRoomPicture = new JLabel();
		currentRoomPicture.setBounds(0, 0, 1000, 750);
		currentRoomPicture.setIcon(cat.getRoomImage());
		
		//adds the image of the current room to the background layer
		layeredPanePlay.add(currentRoomPicture, Integer.valueOf(0));
		
		this.addItemLabels();
		
		//creates a JLabel that contains an image of the cat, and places it at a layer above the image of the room
		//so that it appears that the cat is in the room
		JButton catPicPlay = new JButton();
		catPicPlay.setBounds(220,515, 250, 250);
		ImageIcon catPlayPic = new ImageIcon("Images/cat.png");
		Image catPlayOld = catPlayPic.getImage();
		Image catPlayResized = catPlayOld.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		catPlayPic = new ImageIcon(catPlayResized);
		catPicPlay.setIcon(catPlayPic);
		catPicPlay.setContentAreaFilled(false);
		catPicPlay.setFocusPainted(false);
		catPicPlay.setBorderPainted(false);
		
		catPicPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canCharmOwner1.equals(false) && canCharmOwner2.equals(false)) {
					playSound("Sound/mixkit-cartoon-kitty-begging-meow-92.wav");
				}
				else {
					heartsLabel.setVisible(true);
					heartsLeft = 5;
					heartsTimer();
					playSound("Sound/purring.wav");
					if (canCharmOwner1.equals(true)) {
						catPoints = catPoints - 1;
						System.out.println("Catpoints: " + catPoints);
					}
					if (canCharmOwner2.equals(true)) {
						catPoints = catPoints - 1;
						System.out.println("Catpoints: " + catPoints);
					}
				}
				//setChanged();
				//notifyObservers();
			}
		});
		
		layeredPanePlay.add(catPicPlay, Integer.valueOf(2));
		
		JLayeredPane heartPane = new JLayeredPane();
		//heartPane.setBackground(Color.BLACK);
		//heartPane.setOpaque(true);
		heartPane.setBounds(10, 10, 900, 500);
		
		JPanel heartPanel = new JPanel();
		heartPanel.setSize(900, 500);
		//heartPanel.setBackground(Color.BLUE);
		//heartPanel.setOpaque(true);
		heartPanel.setLayout(new BorderLayout());
		
		ImageIcon heartsgif = new ImageIcon("Images/hearts2.gif");
		int newIconWidth  = (int) Math.round((heartsgif.getIconWidth()) * .3);
		int newIconHeight = (int) Math.round((heartsgif.getIconHeight()) * .3);
		Image heartsOld = heartsgif.getImage();
		Image heartsResized = heartsOld.getScaledInstance(newIconWidth, newIconHeight, java.awt.Image.SCALE_DEFAULT);
		heartsgif = new ImageIcon(heartsResized);
		heartsLabel.setBounds(350, 450, heartsgif.getIconWidth(), heartsgif.getIconHeight());
		heartsLabel.setIcon(heartsgif);
		heartsLabel.setVisible(false);
		
		layeredPanePlay.add(heartsLabel, Integer.valueOf(3));
		
		
		
		
		owner1PicPlay = new JLabel();
		owner1PicPlay.setBounds(25, 220, 235, 550);
		ImageIcon owner1PlayPic = new ImageIcon("Images/owner1.png");
		Image owner1PlayOld = owner1PlayPic.getImage();
		Image owner1PlayResized = owner1PlayOld.getScaledInstance(235, 550, java.awt.Image.SCALE_SMOOTH);
		owner1PlayPic = new ImageIcon(owner1PlayResized);
		owner1PicPlay.setIcon(owner1PlayPic);
		owner1PicPlay.setVisible(false);
		
		
		
		layeredPanePlay.add(owner1PicPlay, Integer.valueOf(2));
		
		owner2PicPlay = new JLabel();
		owner2PicPlay.setBounds(740, 220, 235, 550);
		ImageIcon owner2PlayPic = new ImageIcon("Images/owner2.png");
		Image owner2PlayOld = owner2PlayPic.getImage();
		Image owner2PlayResized = owner2PlayOld.getScaledInstance(235, 550, java.awt.Image.SCALE_SMOOTH);
		owner2PlayPic = new ImageIcon(owner2PlayResized);
		owner2PicPlay.setIcon(owner2PlayPic);
		owner2PicPlay.setVisible(false);
		
		layeredPanePlay.add(owner2PicPlay, Integer.valueOf(2));
		
		ImageIcon holding = new ImageIcon("Images/holdingbutton.png");
		ImageIcon holdingNew = new ImageIcon("Images/holdingbutton2.png");
		
		int holdingWidth = holding.getIconWidth();
		int holdingHeight = holding.getIconHeight();
		
		Image holdingOld = holdingNew.getImage();
		Image holdingResized = holdingOld.getScaledInstance(holdingWidth, holdingHeight, java.awt.Image.SCALE_SMOOTH);
		holdingNew = new ImageIcon(holdingResized);
		
		//holdingButton = new JLabel();
		holdingButton.setBounds(50, 30, 130, 44);
		holdingButton.setIcon(holdingNew);
		holdingButton.setVisible(false);
			
		
		layeredPanePlay.add(holdingButton, Integer.valueOf(3));
		
		
		
		
		inventoryBox.setBounds(50, 90, 130, 130);
		inventoryBox.setVisible(true);
		inventoryBox.setIcon(null);
		inventoryBox.setContentAreaFilled(false);
		inventoryBox.setFocusPainted(false);
		inventoryBox.setBorderPainted(false);
		Random random = new Random();
		layeredPanePlay.add(inventoryBox, Integer.valueOf(3));
		
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
							int xmin = 100;
							int xmax = 900;
							int x = xmin + (int)(Math.random() * (xmax - xmin));
							int ymin = 100;
							int ymax = 500;
							int y = ymin + (int) (Math.random() * (ymax - ymin));	
							
							i.setVisible(true);
							i.setVisibility(true);
							
							cat.dropItem(i);
							holdingButton.setVisible(false);
							/*
							if (!i.getOriginalRoom().equals(cat.getCurrentRoom())) {
								i.setWasMoved(true);
								catPoints = catPoints + i.getValue();
								System.out.println("Cat points: " + catPoints);
							}
							if (i.getOriginalRoom().equals(cat.getCurrentRoom())) {
								if (i.getWasMoved().equals(true)) {
								catPoints = catPoints - i.getValue();
								System.out.println("Cat points: " + catPoints);
								}
								i.setWasMoved(false);
							}
							*/
							
						}
						
					}
				}
			}
		});
		
		
		
		
		
		
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
		toRoomButton1.addActionListener(new To_Room_ButtonListener(cat, this, cat.getCurrentRoom(), toRoomButton1, toRoomButton2, output, currentRoomPicture));
		
		layeredPanePlay.add(toRoomButton1, Integer.valueOf(3));
		
		
		
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
		toRoomButton2.addActionListener(new To_Room_ButtonListener(cat, this, cat.getCurrentRoom(), toRoomButton1, toRoomButton2, output, currentRoomPicture));
		
		layeredPanePlay.add(toRoomButton2, Integer.valueOf(3));
		
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
		playButton.setBounds(310, 550, 325, 150);
		
		layeredPaneHome.add(playButton, Integer.valueOf(1));
		
		superPane.add(layeredPaneHome, Integer.valueOf(1));
		superPane.add(layeredPanePlay, Integer.valueOf(0));
		//superPane.add(heartPane, Integer.valueOf(3));
	            
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//sets the layour manager to null so that each component can be manually positioned
		window.setLayout(null);
		window.setSize(1000, 750);
		window.add(superPane);
		window.setResizable(false);
		window.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.cat.getInRoomWithMob1().equals(true)) {
			this.owner1PicPlay.setVisible(true);
			setCanCharmOwner1(true);
			canCharm = true;
		}
		if (this.cat.getInRoomWithMob1().equals(false)){
			this.owner1PicPlay.setVisible(false);
			setCanCharmOwner1(false);
		}
		if (this.cat.getInRoomWithMob2().equals(true)) {
			this.owner2PicPlay.setVisible(true);
			setCanCharmOwner2(true);
			canCharm = true;
		}
		if (this.cat.getInRoomWithMob2().equals(false)){
			this.owner2PicPlay.setVisible(false);
			setCanCharmOwner2(false);
		}
		
	}

	

	
}
