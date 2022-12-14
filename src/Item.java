import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Item extends JButton{

		private String name; 
		
		private GUI gui;
		
		private Cat cat;
		
		private ImageIcon image;
		
		private int value;
		
		private int xOrig;
		
		private int yOrig;
		
		private int xNew;
		
		private int yNew;
		
		private double newImageSizeScale;
		
		private Boolean visibility;
		
		private ImageIcon holdingImage;
		
		private Room originalRoom;
		
		private Boolean wasMoved;
		
		int iconWidth;
		
		int iconHeight;
		
		private static HashMap<String, ImageIcon> nameToItem = new HashMap<>();
		
		
		
		Item(String name, GUI gui, String filename, int value, int xOrig, int yOrig, int xNew, int yNew, double newImageSizeScale, Boolean visibility, String holdingfilename, Room originalRoom, Boolean wasMoved){
			this.name = name;
			this.gui= gui;
			image = new ImageIcon(filename);
			this.value = value;
			this.xOrig = xOrig;
			this.yOrig = yOrig;
			this.xNew = xNew;
			this.yNew = yNew;
			this.newImageSizeScale = newImageSizeScale;
			this.visibility = visibility;
			holdingImage = new ImageIcon(holdingfilename);
			this.originalRoom = originalRoom;
			this.wasMoved = wasMoved;
			
			nameToItem.put(name, image);
			
			
			ImageIcon icon = (Item.getItemImage(name));
			
			int newIconWidth  = (int) Math.round((icon.getIconWidth()) * newImageSizeScale);
			int newIconHeight = (int) Math.round((icon.getIconHeight()) * newImageSizeScale);
			iconWidth = newIconWidth;
			iconHeight = newIconHeight;
			
			Image imageOld = icon.getImage();
			Image imageResized = imageOld.getScaledInstance(newIconWidth, newIconHeight, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageResized);
			
			
			this.setBounds(xNew, yNew, newIconWidth, newIconHeight);
			this.setIcon(icon);
			this.setContentAreaFilled(false);
			this.setFocusPainted(false);
			this.setBorderPainted(false);
			this.setVisible(visibility);
			
			
			
			
		}
		
		
		public int getIconWidth() {
			return iconWidth;
		}
		
		public int getIconHeight() {
			return iconHeight;
		}
		public String getName() {
			return this.name;
		}
		
		public Boolean getWasMoved() {
			return this.wasMoved;
		}
		
		public void setWasMoved(Boolean input) {
			wasMoved = input;
		}
		public static ImageIcon getItemImage(String id) {
			ImageIcon image = nameToItem.get(id);
			return image;
		}
		
		public ImageIcon getImage() {
			return image;
		}
		
		public int getValue() {
			return value;
		}

		public ImageIcon getInventoryImage() {
			return holdingImage;
		}

		public void setVisibility(Boolean visibility) {
			this.visibility = visibility;
		}

		public Boolean getVisibility() {
			return this.visibility;
		}

		public Room getOriginalRoom() {
			return this.originalRoom;
		}
		
		public void setXNew(int number) {
			xNew = number;
		}
		
		public void setYNew(int number) {
			yNew = number;
		}
		
		public int getXOrig() {
			return this.xOrig;
		}
		
		public int getYOrig() {
			return this.yOrig;
		}
		
		public int getXNew() {
			return this.xNew;
		}
		
		public int getYNew() {
			return this.yNew;
		}
		
}
