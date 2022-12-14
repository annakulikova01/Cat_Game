import javax.swing.ImageIcon;

public class Dog_Exit {

		private String descriptionDog;
		
		private Dog_Room toRoomDog;
		
		private String directionDog;
		
		private ImageIcon imageDog;
		
		Dog_Exit(String descriptionDog, Dog_Room toRoomDog, String directionDog, String filenameDog){
			this.descriptionDog = descriptionDog;
			this.toRoomDog = toRoomDog;
			this.directionDog = directionDog;
			imageDog = new ImageIcon(filenameDog);
		}
		
		public String getDescriptionDog() {
			return this.descriptionDog;
		}
		
		public String getDirectionDog() {
			return this.directionDog;
		}
		
		public Dog_Room getToRoomDog() {
			return this.toRoomDog;
		}

		public ImageIcon getButtonDog() {
			return imageDog;
		}
}

