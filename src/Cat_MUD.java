import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author akulikov
 *
 */
public class Cat_MUD extends Observable {
	private Boolean isRunning;
	
	private Room catRoom;
	
	public MOB_Room mob1Room;
	
	public MOB_Room mob2Room;
	
	public Cat_MUD(Boolean isRunning)	{
		this.isRunning = isRunning;
		
		
		Room parlor = new Room ("Parlor", "You're in the parlor.", "Images/livingroom.jpeg");
		Room bedroom = new Room ("Bedroom", "You're in the bedroom.", "Images/bedroom.jpg");
		Room kitchen = new Room ("Kitchen", "You're in the kitchen.", "Images/kitchen.png");
		Room bathroom = new Room ("Bathroom", "You're in the bathroom.", "Images/bathroom.jpg");
		//Room vents = new Room ("Vents", "You're in the vents.", "Images/vents.jpg");
		
		Exit toParlor = new Exit ("To parlor", parlor, "parlor", "Images/toParlor.png");
		Exit toBedroom = new Exit ("To bedroom", bedroom, "bedroom", "Images/toBedroom.png");
		Exit toKitchen = new Exit ("To kitchen", kitchen, "kitchen", "Images/toKitchen.png");
		Exit toBathroom = new Exit ("To bathroom", bathroom, "bathroom", "Images/toBathroom.png");
		//Exit toVents = new Exit ("To vents", vents, "vents", "Images/toVents.png");
		
		Cat cat = new Cat (kitchen, this);
		
		Item keys = new Item ("keys");
		Item yarn = new Item ("yarn");
		Item vase = new Item ("vase");
		Item cup = new Item ("cup");
		Item catnip = new Item ("catnip");
		Item tie = new Item ("tie");
		Item ring = new Item ("ring");
		Item phone = new Item ("phone");
		Item medicine = new Item ("medicine");
		Item glasses = new Item ("glasses");
		Item plates = new Item ("plates");
		Item sock = new Item ("sock");
		
		parlor.addItem(catnip);
		//parlor.addItem(vase);
		parlor.addItem(yarn);
		parlor.addExit(toKitchen);
		parlor.addExit(toBedroom);
		//parlor.addExit(toVents);
		
		//kitchen.addItem(plates);
		kitchen.addItem(keys);
		//kitchen.addItem(cup);
		kitchen.addExit(toParlor);
		kitchen.addExit(toBathroom);
		//kitchen.addExit(toVents);
		
		bathroom.addItem(glasses);
		bathroom.addItem(medicine);
		bathroom.addExit(toKitchen);
		bathroom.addExit(toBedroom);
		//bathroom.addExit(toVents);
		
		bedroom.addItem(ring);
		bedroom.addItem(phone);
		bedroom.addItem(tie);
		bedroom.addItem(sock);
		bedroom.addExit(toParlor);
		bedroom.addExit(toBathroom);
		//bedroom.addExit(toVents);
		
		//vents.addExit(toBathroom);
	    //vents.addExit(toBedroom);
		//vents.addExit(toKitchen);
		//vents.addExit(toParlor);
		
		//UI ui = new UI(new Scanner(System.in), cat);
		//ui.run();
		
		
		
		
		
		
		Runnable mob1 = new Mob("1", bedroom, this, 10000L, "Images/owner1.png");
		Runnable mob2 = new Mob("2", bathroom, this, 11000L, "Images/owner2.png");
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		service.execute(mob1);
		service.execute(mob2);
		
		service.shutdown();
		
		this.addObserver(cat);
		
		GUI gui = new GUI(cat, this);
		gui.run();
		
		cat.addObserver(gui);
	}

	public void notifyMobRoomChange(String name, Room currentRoom) {
		// why not just compare cat.currentRoom and mobN.currentRoom?
		if (name.equals("1")) {
			mob1Room.updateMob1Room(currentRoom);
		}
		else if (name.equals("2")) {
			mob2Room.updateMob2Room(currentRoom);
			
		}
		setChanged();
		notifyObservers();
		
	}
	
	
	public void notifyCatRoomChange() {
		setChanged();
		notifyObservers();
	}
	
	public Room getCatRoom() {
		return catRoom;
	}
	public Boolean isRunning() {
		return isRunning;
	}

	public void stopRunning() {
		isRunning = false;
	}
	
	public static void main(String[] args) {
		new Cat_MUD(true);
	}	

}
