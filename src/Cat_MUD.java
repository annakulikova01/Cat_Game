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
	
	
	private MOB_Room mob1Room;
	private MOB_Room mob2Room;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private ArrayList<Dog_Item> dogItems = new ArrayList<Dog_Item>();
	
	
	
	public Cat_MUD(Boolean isRunning)	{
		this.isRunning = isRunning;
		
		
		Room parlor = new Room ("Parlor", "You're in the parlor.", "Images/livingroom.jpeg");
		Room bedroom = new Room ("Bedroom", "You're in the bedroom.", "Images/bedroom.jpg");
		Room kitchen = new Room ("Kitchen", "You're in the kitchen.", "Images/kitchen.png");
		Room bathroom = new Room ("Bathroom", "You're in the bathroom.", "Images/bathroom.jpg");
		//Room vents = new Room ("Vents", "You're in the vents.", "Images/vents.jpg");
		
		Dog_Room dogparlor = new Dog_Room ("Parlor", "You're in the parlor.", "Images/livingroom.jpeg");
		Dog_Room dogbedroom = new Dog_Room ("Bedroom", "You're in the bedroom.", "Images/bedroom.jpg");
		Dog_Room dogkitchen = new Dog_Room ("Kitchen", "You're in the kitchen.", "Images/kitchen.png");
		Dog_Room dogbathroom = new Dog_Room ("Bathroom", "You're in the bathroom.", "Images/bathroom.jpg");
		//Room vents = new Room ("Vents", "You're in the vents.", "Images/vents.jpg");
		
		Exit toParlor = new Exit ("To parlor", parlor, "parlor", "Images/toParlor.png");
		Exit toBedroom = new Exit ("To bedroom", bedroom, "bedroom", "Images/toBedroom.png");
		Exit toKitchen = new Exit ("To kitchen", kitchen, "kitchen", "Images/toKitchen.png");
		Exit toBathroom = new Exit ("To bathroom", bathroom, "bathroom", "Images/toBathroom.png");
		//Exit toVents = new Exit ("To vents", vents, "vents", "Images/toVents.png");
		
		Dog_Exit dogToParlor = new Dog_Exit ("To parlor", dogparlor, "dogparlor", "Images/toParlor.png");
		Dog_Exit dogToBedroom = new Dog_Exit ("To bedroom", dogbedroom, "dogbedroom", "Images/toBedroom.png");
		Dog_Exit dogToKitchen = new Dog_Exit ("To kitchen", dogkitchen, "dogkitchen", "Images/toKitchen.png");
		Dog_Exit dogToBathroom = new Dog_Exit ("To bathroom", dogbathroom, "dogbathroom", "Images/toBathroom.png");
		
		Cat cat = new Cat (kitchen, this);
		
		Dog dog = new Dog (dogbedroom, this);
		
		GUI gui = new GUI(cat, this, false, false, false, true);
		
		Dog_GUI doggui = new Dog_GUI(dog, this);
		
		
		
		Item keys = new Item ("keys", gui, "Images/keys.png", 10, 350, 390, 350, 390, .5, false, "Images/holdingkeys.png", parlor, false);
		Item yarn = new Item ("yarn", gui, "Images/yarn.png", 2, 75, 500, 75, 500, .9, false, "Images/holdingyarn.png", parlor, false);
		Item vase = new Item ("vase", gui, "Images/vase.png", 3, 580, 135, 580, 135, 1.5, false, "Images/holdingvase.png", parlor, false);
		Item cup = new Item ("cup", gui, "Images/cup.png", 4, 450, 475, 450, 475, .75, true, "Images/holdingcup.png", kitchen, false);
		Item catnip = new Item ("catnip", gui, "Images/catnip.png", 0, 100, 400, 100, 400, 1, false, "Images/holdingcatnip.png", parlor, false);
		Item tie = new Item ("tie", gui, "Images/tie.png", 15, 860, 300, 860, 300, 1, false, "Images/holdingtie.png", bedroom, false);
		Item ring = new Item ("ring", gui, "Images/ring.png", 40, 950, 520, 950, 520, .3, false, "Images/holdingring.png", bedroom, false);
		//Item phone = new Item ("phone", "Images/phone.png", 25, false);
		Item medicine = new Item ("medicine", gui, "Images/medicine.png", 30, 400, 320, 400, 320, .75, false, "Images/holdingmedicine.png", bathroom, false);
		Item glasses = new Item ("glasses", gui, "Images/glasses.png", 25, 280, 380, 280, 380, .75, false, "Images/holdingglasses.png", bathroom, false);
		Item plates = new Item ("plates", gui, "Images/plates.png", 10, 840, 520, 840, 520, 1.1, true, "Images/holdingplates.png", kitchen, false);
		Item sock = new Item ("sock", gui, "Images/sock.png", 5, 300, 600, 300, 600, .75, false, "Images/holdingsock.png", bedroom, false);
		
		Dog_Item dogkeys = new Dog_Item ("dogkeys", doggui, "Images/keys.png", 10, 350, 390, 350, 390, .5, false, "Images/holdingkeys.png", dogparlor, false);
		Dog_Item dogyarn = new Dog_Item ("dogyarn", doggui, "Images/yarn.png", 2, 75, 500, 75, 500, .9, false, "Images/holdingyarn.png", dogparlor, false);
		Dog_Item dogvase = new Dog_Item ("dogvase", doggui, "Images/vase.png", 3, 580, 135, 580, 135, 1.5, false, "Images/holdingvase.png", dogparlor, false);
		Dog_Item dogcup = new Dog_Item ("dogcup", doggui, "Images/cup.png", 4, 450, 475, 450, 475, .75, true, "Images/holdingcup.png", dogkitchen, false);
		Dog_Item dogcatnip = new Dog_Item ("dogcatnip", doggui, "Images/catnip.png", 0, 100, 400, 100, 400, 1, false, "Images/holdingcatnip.png", dogparlor, false);
		Dog_Item dogtie = new Dog_Item ("dogtie", doggui, "Images/tie.png", 15, 860, 300, 860, 300, 1, false, "Images/holdingtie.png", dogbedroom, false);
		Dog_Item dogring = new Dog_Item ("dogring", doggui, "Images/ring.png", 40, 950, 520, 950, 520, .3, false, "Images/holdingring.png", dogbedroom, false);
		//Item phone = new Item ("phone", "Images/phone.png", 25, false);
		Dog_Item dogmedicine = new Dog_Item ("dogmedicine", doggui, "Images/medicine.png", 30, 400, 320, 400, 320, .75, false, "Images/holdingmedicine.png", dogbathroom, false);
		Dog_Item dogglasses = new Dog_Item ("dogglasses", doggui, "Images/glasses.png", 25, 280, 380, 280, 380, .75, false, "Images/holdingglasses.png", dogbathroom, false);
		Dog_Item dogplates = new Dog_Item ("dogplates", doggui, "Images/plates.png", 10, 840, 520, 840, 520, 1.1, true, "Images/holdingplates.png", dogkitchen, false);
		Dog_Item dogsock = new Dog_Item ("dogsock", doggui, "Images/sock.png", 5, 300, 600, 300, 600, .75, false, "Images/holdingsock.png", dogbedroom, false);
		
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
		
		dogItems.add(dogkeys);
		dogItems.add(dogyarn);
		dogItems.add(dogvase);
		dogItems.add(dogcup);
		dogItems.add(dogcatnip);
		dogItems.add(dogtie);
		dogItems.add(dogring);
		dogItems.add(dogmedicine);
		dogItems.add(dogglasses);
		dogItems.add(dogplates);
		dogItems.add(dogsock);
		
		
		
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
		
		dogparlor.addItemDog(dogcatnip);
		dogparlor.addItemDog(dogkeys);
		dogparlor.addItemDog(dogvase);
		dogparlor.addItemDog(dogyarn);
		dogparlor.addExitDog(dogToKitchen);
		dogparlor.addExitDog(dogToBedroom);
		//parlor.addExit(toVents);
		
		dogkitchen.addItemDog(dogplates);
		dogkitchen.addItemDog(dogcup);
		dogkitchen.addExitDog(dogToParlor);
		dogkitchen.addExitDog(dogToBathroom);
		//kitchen.addExit(toVents);
		
		dogbathroom.addItemDog(dogglasses);
		dogbathroom.addItemDog(dogmedicine);
		dogbathroom.addExitDog(dogToKitchen);
		dogbathroom.addExitDog(dogToBedroom);
		//bathroom.addExit(toVents);
		
		dogbedroom.addItemDog(dogring);
		//bedroom.addItem(phone);
		dogbedroom.addItemDog(dogtie);
		dogbedroom.addItemDog(dogsock);
		dogbedroom.addExitDog(dogToParlor);
		dogbedroom.addExitDog(dogToBathroom);
		//bedroom.addExit(toVents);
		
		//vents.addExit(toBathroom);
	    //vents.addExit(toBedroom);
		//vents.addExit(toKitchen);
		//vents.addExit(toParlor);
		
		//creates threads for each mob, giving them a name, placing them in a room, giving them a number of seconds
		//from which a time interval that determines how long they wait in a room for is created in the mob class,
		//and gives them an image
		
		//also initializes mob_Room objects for each mob, giving them their respective names and rooms through the methods created in the mob class
		//that return specific arguments from the constructor
	
		Mob mob1 = new Mob("1", bedroom, this, cat, 10000L, "Images/owner1.png");
		this.mob1Room = new MOB_Room(mob1.getName(), mob1.getRoom());
		Mob mob2 = new Mob("2", bathroom, this, cat, 11000L, "Images/owner2.png");
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
		
		
		
		
		
		
		
		try {
			gui.run();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			doggui.run();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//adds the gui object's observer to the cat object
		cat.addObserver(gui);
		
		
		gui.addObserver(mob2);
		
		gui.addObserver(mob1);
		
		
		
		
		
		
	}
	
	
	
	public ArrayList<Item> getItemLabels() {
		return items;
	}
	
	public ArrayList<Dog_Item> getDogItemLabels() {
		return dogItems;
	}
	//a method that takes a mob's name and current room passed as arguments every time a mob moves, and depending on the name,
	//changes the current room of a MOB_Room object in order to store an identifying value. Once this is done, the cat's observer
	//gets notified and can use the new values
	public void notifyMobRoomChangeCat(String name, Room currentRoom) {
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
