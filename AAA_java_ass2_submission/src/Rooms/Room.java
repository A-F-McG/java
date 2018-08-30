package Rooms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.image.Image;

//Class containing methods to create new rooms with images and exits
public class Room {
		
	//HashMap with key set as direction storing 4 images of each room
	private HashMap <direction, Image> roomImages = new HashMap<direction, Image>();
	//HashMap with key set as direction storing 4 exits for each room (which can be null)
	private HashMap <direction, Room> exits = new HashMap<direction, Room>();

	//queue which will store just one image (name of image) for each of the rooms
	private Queue<String> itemsInWorld = new LinkedList<String>();

	
	/**
	 * Room constructor
	 * 
	 * @param roomName
	 * 
	 * - initialise new room with parameter roomName
	 * 
	 * - for each room, it creates new HashMaps for the room's images and exits and a new queue to store objects in that room
	 */
	public Room(String roomName){
		roomImages = new HashMap<direction, Image>();
		exits = new HashMap<direction, Room>();
		itemsInWorld = new LinkedList<String>();
	} 
	
	
	/**
	 * Method to set the room images
	 * 
	 * @param imageNorth
	 * @param imageEast
	 * @param imageSouth
	 * @param imageWest
	 */
	public void setRoomImages(Image imageNorth,Image imageEast, Image imageSouth, Image imageWest){
		roomImages.put(direction.NORTH, imageNorth);
		roomImages.put(direction.EAST, imageEast);
		roomImages.put(direction.SOUTH, imageSouth);
		roomImages.put(direction.WEST, imageWest);	                                                                                                                                                                                             
	}

	
	/**
	 * Method to set the room exits
	 * 
	 * @param northExit
	 * @param eastExit
	 * @param southExit
	 * @param westExit
	 */
	public void setRoomExits(Room northExit,Room eastExit, Room southExit, Room westExit){
		exits.put(direction.NORTH, northExit);
		exits.put(direction.EAST, eastExit);
		exits.put(direction.SOUTH, southExit);
		exits.put(direction.WEST, westExit);
	}
	

	/**
	 * returns the room exit of a room based on the current direction
	 * 
	 * @param direction
	 * @return
	 */
	public Room getExit(direction direction) {
		return exits.get(direction);
	}
	
	
	/**
	 * returns image of the room based on the current direction
	 * 
	 * @param direction
	 * @return
	 */
	public Image getImage(direction direction) {
		return roomImages.get(direction);
	}
	
	
	/**
	 * set the room object taking the name of the object as the parameter
	 * 
	 * @param string
	 * 
	 * - arbitrary number of items to select from, but this only allow one item per room
	 * 
	 * - this method takes out the previous item from the room's queue and inserts the new item
	 */
	public void setRoomObject(String string) {
		itemsInWorld.poll();
		itemsInWorld.offer(string);
	}
	
	
	/**
	 * get the name of the current room's object
	 * 
	 * @return
	 */
	public String getRoomObject() {
		return itemsInWorld.peek();
	}
	
}
