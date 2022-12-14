import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Dog extends Observable  {
	private Dog_Room currentRoomDog;
	
	private Cat_MUD cmDog;
	
	private Boolean inRoomWithMob1Dog;
	
	private Boolean inRoomWithMob2Dog;

	public ArrayList<Dog_Item> itemsHeldDog = new ArrayList<>();
	
	public Dog(Dog_Room currentRoomDogDog, Cat_MUD cmDog) {
		this.currentRoomDog = currentRoomDog;
		this.cmDog = cmDog;
		this.inRoomWithMob1Dog = false;
		this.inRoomWithMob2Dog = false;
	}
	
	public void getDogItem(Dog_Item itemClicked) {
		Dog_Item it = this.currentRoomDog.giveItemDog(itemClicked);
		itemsHeldDog.add(it);
		
	}
	
	public void dropDogItem(Dog_Item itemClicked) {
		this.itemsHeldDog.remove(itemClicked);
		this.currentRoomDog.addItemDog(itemClicked);
	}
	
	public Dog_Room getDogCurrentRoom() {
		return currentRoomDog;
	}
	
	public Boolean goDog(String exit) {
		Dog_Room r = this.currentRoomDog.goDog(exit);
		if (r == null) {
			return false;
		} else {
			this.currentRoomDog = r;
			
			
			return true;
		}
	}
	
	
	
	public ArrayList<Dog_Item> getCurrentRoomItemsList(){
		ArrayList<Dog_Item> items = this.currentRoomDog.getItemsDog();
		return items;
	}
	
	//gets an array of exit directions for the current room
	public String[] getDogCurrentRoomExitsDirection() {
		String direction1 = "";
		String direction2 = "";
		ArrayList<Dog_Exit> currentRoomExits = this.currentRoomDog.getExitsDog();
		direction1 = currentRoomExits.get(0).getDirectionDog();
		direction2 = currentRoomExits.get(1).getDirectionDog();
		String[] exitDirections = new String[2];
		exitDirections[0] = new String(direction1);
		exitDirections[1] = new String(direction2);
		return exitDirections;
	}
	
	//returns the direction for the first exit in the array
	public String getDogRoomButton1Direction() {
		String[] roomExitDirectionsDog = getDogCurrentRoomExitsDirection();
		String room1DirectionDog = roomExitDirectionsDog[0];
		return room1DirectionDog;
		
	}
	
	//returns the direction for the second exit in the array
	public String getDogRoomButton2Direction() {
		String[] roomExitDirectionsDog = getDogCurrentRoomExitsDirection();
		String room1DirectionDog = roomExitDirectionsDog[1];
		return room1DirectionDog;
		
	}
	
	
	//gets an array of images corresponding to the exits of the current room 
	public ImageIcon[] getDogCurrentRoomExitsImage() {
		ImageIcon image1;
		ImageIcon image2;
		ArrayList<Dog_Exit> currentRoomExits = this.currentRoomDog.getExitsDog();
		image1 = currentRoomExits.get(0).getButtonDog();
		image2 = currentRoomExits.get(1).getButtonDog();
		ImageIcon[] exitImages = new ImageIcon[2];
		exitImages[0] = image1;
		exitImages[1] = image2;
		return exitImages;
		
	}
	//returns the image corresponding to the first exit in the current room's exits
	public ImageIcon getDogRoomButton1Image() {
		ImageIcon[] buttonImages = getDogCurrentRoomExitsImage();
		ImageIcon button1Image = buttonImages[0];
		return button1Image;
	}
	
	//returns the image corresponding to the second exit in the current room's exits
	public ImageIcon getDogRoomButton2Image() {
		ImageIcon[] buttonImages = getDogCurrentRoomExitsImage();
		ImageIcon button2Image = buttonImages[1];
		return button2Image;
	}
	
	
	public String[] getDogCurrentRoomExitsDescription() {
		String exit1 = "";
		String exit2 = "";
		ArrayList<Dog_Exit> currentRoomExits = this.currentRoomDog.getExitsDog();
		exit1 = currentRoomExits.get(0).getDescriptionDog() + "";
		exit2 = currentRoomExits.get(1).getDescriptionDog() + "";
		String[] exitDescriptions = new String[2];
		exitDescriptions[0] = new String(exit1);
		exitDescriptions[1] = new String(exit2);
		return exitDescriptions;
		
	}
	
	public String getDogRoomButton1Name() {
		String[] roomExitDescriptions = getDogCurrentRoomExitsDescription();
		String room1Description = roomExitDescriptions[0];
		return room1Description;
	}
	
	public String getDogRoomButton2Name() {
		String[] roomExitDescriptions = getDogCurrentRoomExitsDescription();
		String room2Description = roomExitDescriptions[1];
		return room2Description;
	}
	
	public String getDogRoomDescription() {
		return this.currentRoomDog.roomDescriptionDog();
	}
	
	public ImageIcon getDogRoomImage() {
		return this.currentRoomDog.getImageDog();
	}


	
}
	
