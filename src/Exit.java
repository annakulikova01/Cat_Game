import javax.swing.ImageIcon;

public class Exit {

		private String description;
		
		private Room toRoom;
		
		private String direction;
		
		private ImageIcon image;
		
		Exit(String description, Room toRoom, String direction, String filename){
			this.description = description;
			this.toRoom = toRoom;
			this.direction = direction;
			image = new ImageIcon(filename);
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public String getDirection() {
			return this.direction;
		}
		
		public Room getToRoom() {
			return this.toRoom;
		}

		public ImageIcon getButton() {
			return image;
		}
}
