import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Dog_Room {
	
	private String nameDog;
	private String descriptionDog;
	private ImageIcon imageDog;
	
	public Dog_Room(String aNameDog, String aDescriptionDog, String filenameDog){
		nameDog = aNameDog;
		descriptionDog = aDescriptionDog;
		imageDog = new ImageIcon(filenameDog);
		
	}

	private ArrayList<Dog_Item> dogitems = new ArrayList<>();
	
	private ArrayList<Dog_Exit> dogexits = new ArrayList<>();
	
	public Dog_Room goDog(String exit) {
		for (Dog_Exit e : dogexits) {
			if (e.getDirectionDog().equals(exit)) {
				return e.getToRoomDog();
			}
		}
		return null;
	}
	
	Random random = new Random();
	
	public Dog_Room goRandomExitDog() {
		int randomExit = random.nextInt(2);
		Dog_Exit newExit = dogexits.get(randomExit);
		return newExit.getToRoomDog();
		
	}
	
	public String getRoomNameDog() {
		return nameDog;
	}
	
	public ImageIcon getImageDog() {
		return imageDog;
	}
	public String roomDescriptionDog() {
		return descriptionDog;
	}
	public Dog_Item giveItemDog(Dog_Item itemClicked) {
		dogitems.remove(itemClicked);
		return itemClicked;
	}
	
	public void addItemDog(Dog_Item it) {
		dogitems.add(it);
	}
	
	public void addExitDog(Dog_Exit toRoom) {
		dogexits.add(toRoom);
	}
	
	public String getExitsListDog() {
			String ret = "";
			for (Dog_Exit e : dogexits) {
				ret = ret + e.getDirectionDog() + " ";
			}
			return ret;
		}
		
	public ArrayList<Dog_Exit> getExitsDog() {
		return dogexits;
	}
	
	public ArrayList<Dog_Item> getItemsDog() {
		return dogitems;
	}
	public String getRoomItemsListDog() {
			String ret = "";
			for (Dog_Item i : dogitems) {
				ret = ret + i.getName() + " ";
		}
		return ret;
	}		
}
