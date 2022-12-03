import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JLabel;

/**
 * @author akulikov
 *
 */
public class Cat_MUD extends Observable {
	private Boolean isRunning;
	
	// TODO: track 2 mob rooms, 1 for each mob
	private MOB_Room mob1Room;
	private MOB_Room mob2Room;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	
	
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
		
		Item keys = new Item ("keys", "Images/keys.png", 10, 350, 390, .5, false);
		Item yarn = new Item ("yarn", "Images/yarn.png", 2, 75, 500, .9, false);
		Item vase = new Item ("vase", "Images/vase.png", 3, 580, 135, 1.5, false);
		Item cup = new Item ("cup", "Images/cup.png", 4, 450, 475, .75, true);
		Item catnip = new Item ("catnip", "Images/catnip.png", 0, 100, 400, 1, false);
		Item tie = new Item ("tie", "Images/tie.png", 15, 860, 800, 1, false);
		Item ring = new Item ("ring", "Images/ring.png", 40, 950, 520, .3, false);
		//Item phone = new Item ("phone", "Images/phone.png", 25, false);
		Item medicine = new Item ("medicine", "Images/medicine.png", 30, 400, 320, .75, false);
		Item glasses = new Item ("glasses", "Images/glasses.png", 25, 280, 380, .75, false);
		Item plates = new Item ("plates", "Images/plates.png", 10, 840, 520, 1.1, true);
		Item sock = new Item ("sock", "Images/sock.png", 5, 300, 600, .75, false);

		items.add(keys);
		items.add(yarn);
		items.add(vase);
		items.add(cup);
		items.add(catnip);
		items.add(tie);
		items.add(ring);
		items.add(medicine);
		items.add(glasses);
		items.add(plates);
		items.add(sock);
		
		parlor.addItem(catnip);
		parlor.addItem(keys);
		parlor.addItem(vase);
		parlor.addItem(yarn);
		parlor.addExit(toKitchen);
		parlor.addExit(toBedroom);
		//parlor.addExit(toVents);
		
		kitchen.addItem(plates);
		kitchen.addItem(cup);
		kitchen.addExit(toParlor);
		kitchen.addExit(toBathroom);
		//kitchen.addExit(toVents);
		
		bathroom.addItem(glasses);
		bathroom.addItem(medicine);
		bathroom.addExit(toKitchen);
		bathroom.addExit(toBedroom);
		//bathroom.addExit(toVents);
		
		bedroom.addItem(ring);
		//bedroom.addItem(phone);
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
		
		
		
		
		//creates threads for each mob, giving them a name, placing them in a room, giving them a number of seconds
		//from which a time interval that determines how long they wait in a room for is created in the mob class,
		//and gives them an image
		
		//also initializes mob_Room objects for each mob, giving them their respective names and rooms through the methods created in the mob class
		//that return specific arguments from the constructor
	
		Mob mob1 = new Mob("1", bedroom, this, 10000L, "Images/owner1.png");
		this.mob1Room = new MOB_Room(mob1.getName(), mob1.getRoom());
		Mob mob2 = new Mob("2", bathroom, this, 11000L, "Images/owner2.png");
		this.mob2Room = new MOB_Room(mob2.getName(), mob2.getRoom());
		
		//creates a fixed pool executor service 
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		
		//the service executes each of the threads
		service.execute(mob1);
		service.execute(mob2);
		
		//terminates the pooled threads after all the tasks have finished
		service.shutdown();
		
		//adds cat object's observer to the MUD object
		this.addObserver(cat);
		
		
		
		
		GUI gui = new GUI(cat, this, false, false);
		
		
		gui.run();
		
		//adds the gui object's observer to the cat object
		cat.addObserver(gui);
	}
	
	
	
	public ArrayList<Item> getItemLabels() {
		return items;
	}
	//a method that takes a mob's name and current room passed as arguments every time a mob moves, and depending on the name,
	//changes the current room of a MOB_Room object in order to store an identifying value. Once this is done, the cat's observer
	//gets notified and can use the new values
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
