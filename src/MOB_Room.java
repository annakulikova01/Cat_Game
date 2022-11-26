
public class MOB_Room {
	
	private String mobName;
	
	private Room currentRoom;
	
	public MOB_Room(String mobName, Room currentRoom) {
		this.mobName = mobName;
		this.currentRoom = currentRoom;
	}

	public String getMobName() {
		return this.mobName;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public void setMobName(String mobName) {
		this.mobName = mobName;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	

}
