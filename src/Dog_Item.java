import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Dog_Item extends JButton{

		private String nameDog; 
		
		private Dog_GUI doggui;
		
		private Dog dog;
		
		private ImageIcon imageDog;
		
		private int valueDog;
		
		private int xOrigDog;
		
		private int yOrigDog;
		
		private int xNewDog;
		
		private int yNewDog;
		
		private double newImageSizeScaleDog;
		
		private Boolean visibilityDog;
		
		private ImageIcon holdingImageDog;
		
		private Dog_Room originalRoomDog;
		
		private Boolean wasMovedDog;
		
		int iconWidthDog;
		
		int iconHeightDog;
		
		private static HashMap<String, ImageIcon> nameToItem = new HashMap<>();
		
		
		
		Dog_Item(String nameDog, Dog_GUI doggui, String filenameDog, int valueDog, int xOrigDog, int yOrigDog, int xNewDog, int yNewDog, double newImageSizeScaleDog, Boolean visibilityDog, String holdingfilenameDog, Dog_Room originalRoomDog, Boolean wasMovedDog){
			this.nameDog = nameDog;
			this.doggui= doggui;
			imageDog = new ImageIcon(filenameDog);
			this.valueDog = valueDog;
			this.xOrigDog = xOrigDog;
			this.yOrigDog = yOrigDog;
			this.xNewDog = xNewDog;
			this.yNewDog = yNewDog;
			this.newImageSizeScaleDog = newImageSizeScaleDog;
			this.visibilityDog = visibilityDog;
			holdingImageDog = new ImageIcon(holdingfilenameDog);
			this.originalRoomDog = originalRoomDog;
			this.wasMovedDog = wasMovedDog;
			
			nameToItem.put(nameDog, imageDog);
			
			
			ImageIcon iconDog = (Dog_Item.getItemImageDog(nameDog));
			
			int newIconWidthDog  = (int) Math.round((iconDog.getIconWidth()) * newImageSizeScaleDog);
			int newIconHeightDog = (int) Math.round((iconDog.getIconHeight()) * newImageSizeScaleDog);
			iconWidthDog = newIconWidthDog;
			iconHeightDog = newIconHeightDog;
			
			Image imageOldDog = iconDog.getImage();
			Image imageResizedDog = imageOldDog.getScaledInstance(newIconWidthDog, newIconHeightDog, java.awt.Image.SCALE_SMOOTH);
			iconDog = new ImageIcon(imageResizedDog);
			
			
			this.setBounds(xNewDog, yNewDog, newIconWidthDog, newIconHeightDog);
			this.setIcon(iconDog);
			this.setContentAreaFilled(false);
			this.setFocusPainted(false);
			this.setBorderPainted(false);
			this.setVisible(visibilityDog);
			
			
			
			
		}
		
		
		public int getIconWidthDog() {
			return iconWidthDog;
		}
		
		public int getIconHeightDog() {
			return iconHeightDog;
		}
		public String getNameDog() {
			return this.nameDog;
		}
		
		public Boolean getWasMovedDog() {
			return this.wasMovedDog;
		}
		
		public void setWasMovedDog(Boolean input) {
			wasMovedDog = input;
		}
		public static ImageIcon getItemImageDog(String id) {
			ImageIcon image = nameToItem.get(id);
			return image;
		}
		
		public ImageIcon getImageDog() {
			return imageDog;
		}
		
		public int getValueDog() {
			return valueDog;
		}

		public ImageIcon getInventoryImageDog() {
			return holdingImageDog;
		}

		public void setVisibilityDog(Boolean visibility) {
			this.visibilityDog = visibility;
		}

		public Boolean getVisibilityDog() {
			return this.visibilityDog;
		}

		public Dog_Room getOriginalRoomDog() {
			return this.originalRoomDog;
		}
		
		public void setXNewDog(int number) {
			xNewDog = number;
		}
		
		public void setYNewDog(int number) {
			yNewDog = number;
		}
		
		public int getXOrigDog() {
			return this.xOrigDog;
		}
		
		public int getYOrigDog() {
			return this.yOrigDog;
		}
		
		public int getXNewDog() {
			return this.xNewDog;
		}
		
		public int getYNewDog() {
			return this.yNewDog;
		}
		
}

