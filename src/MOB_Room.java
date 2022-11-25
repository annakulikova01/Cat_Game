
public class MOB_Room {
	
	private String mobName;
	
	private Room currentRoom;
	
	public MOB_Room(String mobName, Room currentRoom) {
		this.mobName = mobName;
		this.currentRoom = currentRoom;
	}

	public String getMobName() {
		return mobName;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	

}
