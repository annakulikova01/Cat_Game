import java.awt.Image;
import java.util.HashMap;


import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Item extends JLabel{

		private String name; 
		
		private ImageIcon image;
		
		private int value;
		
		private int x;
		
		private int y;
		
		private double newImageSizeScale;
		
		private Boolean visibility;
		
		private static HashMap<String, ImageIcon> nameToItem = new HashMap<>();
		
		
		
		Item(String name, String filename, int value, int x, int y, double newImageSizeScale, Boolean visibility){
			this.name = name;
			image = new ImageIcon(filename);
			this.value = value;
			this.x = x;
			this.y = y;
			this.newImageSizeScale = newImageSizeScale;
			this.visibility = visibility;
			
			nameToItem.put(name, image);
			
			ImageIcon icon = (Item.getItemImage(name));
			
			int newIconWidth  = (int) Math.round((icon.getIconWidth()) * newImageSizeScale);
			int newIconHeight = (int) Math.round((icon.getIconHeight()) * newImageSizeScale);
			
			Image imageOld = icon.getImage();
			Image imageResized = imageOld.getScaledInstance(newIconWidth, newIconHeight, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageResized);
			
			
			this.setBounds(x, y, newIconWidth, newIconHeight);
			this.setIcon(icon);
			this.setVisible(visibility);
			
		}
		
		
		
		public String getName() {
			return this.name;
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
		
		
		
}
