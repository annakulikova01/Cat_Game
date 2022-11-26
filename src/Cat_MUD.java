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
	
	// TODO: track 2 mob rooms, 1 for each mob
	private MOB_Room mob1Room;
	private MOB_Room mob2Room;
	
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
		
		
		
		
		
		// we're already tracking name and room here; is MOB_room necessary? 
		Mob mob1 = new Mob("1", bedroom, this, 10000L, "Images/owner1.png");
		this.mob1Room = new MOB_Room(mob1.getName(), mob1.getRoom());
		Mob mob2 = new Mob("2", bathroom, this, 11000L, "Images/owner2.png");
		this.mob2Room = new MOB_Room(mob2.getName(), mob2.getRoom());
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
		// update this class's mob rooms for the notifyCat method
		if(name.equals("1")) {
			this.mob1Room.setCurrentRoom(currentRoom);
		}
		else {
			this.mob2Room.setCurrentRoom(currentRoom);
		}
		setChanged();
		notifyObservers(new MOB_Room(name, currentRoom));
	}
	
	public void notifyCatRoomChange() {
		setChanged();
		// pass the mob rooms here
		notifyObservers(this.mob1Room);
		setChanged();
		notifyObservers(this.mob2Room);
	}
	
	public Boolean isRunning() {
		return this.isRunning;
	}

	public void stopRunning() {
		this.isRunning = false;
	}
	
	public static void main(String[] args) {
		new Cat_MUD(true);
	}	

}
