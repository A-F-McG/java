package Rooms;


import java.util.HashMap;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Inventory {
		
	
	//HashMap storing items with key as their names and stores their images
	private HashMap <String, Image> items = new HashMap<String, Image>();
	
	//Observable list which stores all of the available item names
	private ObservableList<String> objectsInInventory =  FXCollections.observableArrayList();
	
	
	public Inventory() {
		
		//Create items by putting their names and images in the items hashmap
		items.put("blue room wall hanging", new Image("Rooms/blueRoom.jpg"));
		items.put("pink room wall hanging", new Image("Rooms/pinkRoom.jpg"));
		items.put("pane room wall hanging", new Image("Rooms/paneRoom.jpg"));
		items.put("tile room wall hanging", new Image("Rooms/tileRoom.jpg"));
		items.put("white room wall hanging", new Image("Rooms/whiteROOM.jpg"));
		items.put("garden wall hanging", new Image("Rooms/garden.jpg"));
		
		//Add each item to the objectsInInventory observable list. These need to be the same as the item names in 
		//the items Hashmap so that when an item is selected from comboBox, it can return the corresponding image
		objectsInInventory.add("blue room wall hanging");
		objectsInInventory.add("pink room wall hanging");
		objectsInInventory.add("pane room wall hanging");
		objectsInInventory.add("tile room wall hanging");
		objectsInInventory.add("white room wall hanging");
		objectsInInventory.add("garden wall hanging");
	}

	
	/**
	 * Gets the image from the items HashMap using the item name
	 * 
	 * - called in Model class
	 * @param string
	 * @return
	 */
	public Image getObjectImage(String string) {
		return items.get(string);
	}
	
	
	/**
	 * returns the observable list of item names
	 * 
	 * - called in Model class
	 * @return
	 */
	public ObservableList<String> objectsIn(){
		return objectsInInventory;
	}
	
	
}
