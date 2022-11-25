
public class MOB_Room {
	
	private String mobName;
	
	private Room currentRoom;
	
	private Room mob1Room;
	
	private Room mob2Room;
	
	public MOB_Room() {
		
	}

	public String getMobName() {
		return mobName;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void updateMob1Room(Room currentRoom) {
		mob1Room = currentRoom;
	}
		
	public Room getMob1Room() {
		return mob1Room;
	}
	
	public void updateMob2Room(Room currentRoom) {
		mob2Room = currentRoom;
	}
		
	public Room getMob2Room() {
		return mob2Room;
	}
}
