package Rooms;

import javafx.scene.image.Image;
import main.Controller;


public class Model {

	private direction currentdirection;
	private Room currentRoom;
	private Inventory inv;
	

	/**Set the controller of the Model class initially to null
	 * 
	 * - in Main, this class and the Model class are created first, then Controller
	 * - then in the controller constructor, it sets this class' controller to itself, so they will be linked
	 */
	private Controller controller = null;

	public Model() {
		
		//Create rooms
		Room start = new Room("start");
		Room purpleRoom = new Room("purpleRoom");
		Room christmasRoom = new Room("christmasRoom");
		Room pinkRoom = new Room("pinkRoom");
		Room squiggleRoom = new Room("squiggleRoom");
		Room lineRoom = new Room("lineRoom");
		
		//Load images for each room
		Image startN = new Image("Rooms/blueRoomNorth.jpg");
		Image startE = new Image("Rooms/blueRoomEast.jpg");
		Image startS = new Image("Rooms/blueRoomSouth.jpg");
		Image startW = new Image("Rooms/blueRoomWest.jpg");
		
		Image purpleRoomN = new Image("Rooms/gardenNorth.jpg");
		Image purpleRoomE= new Image("Rooms/gardenEast.jpg");
		Image purpleRoomS= new Image("Rooms/gardenSouth.jpg");
		Image purpleRoomW = new Image("Rooms/gardenWest.jpg");
		
		Image christmasRoomN = new Image("Rooms/paneRoomNorth.jpg");
		Image christmasRoomE= new Image("Rooms/paneRoomEast.jpg");
		Image christmasRoomS= new Image("Rooms/paneRoomSouth.jpg");
		Image christmasRoomW = new Image("Rooms/paneRoomWest.jpg");

		Image pinkRoomN = new Image("Rooms/pinkRoomNorth.jpg");
		Image pinkRoomE= new Image("Rooms/pinkRoomEast.jpg");
		Image pinkRoomS= new Image("Rooms/pinkRoomSouth.jpg");
		Image pinkRoomW = new Image("Rooms/pinkRoomWest.jpg");
	
		Image squiggleRoomN = new Image("Rooms/tileRoomNorth.jpg");
		Image squiggleRoomE= new Image("Rooms/tileRoomEast.jpg");
		Image squiggleRoomS= new Image("Rooms/tileRoomSouth.jpg");
		Image squiggleRoomW = new Image("Rooms/tileRoomWest.jpg");

		Image lineRoomN = new Image("Rooms/whiteRoomNorth.jpg");
		Image lineRoomE= new Image("Rooms/whiteRoomEast.jpg");
		Image lineRoomS= new Image("Rooms/whiteRoomSouth.jpg");
		Image lineRoomW = new Image("Rooms/whiteRoomWest.jpg");
		
		//set the images for each room
		start.setRoomImages(startN, startE, startS, startW);
		purpleRoom.setRoomImages(purpleRoomN, purpleRoomE, purpleRoomS, purpleRoomW);
		christmasRoom.setRoomImages(christmasRoomN, christmasRoomE, christmasRoomS, christmasRoomW);
		pinkRoom.setRoomImages(pinkRoomN, pinkRoomE, pinkRoomS, pinkRoomW);
		squiggleRoom.setRoomImages(squiggleRoomN, squiggleRoomE, squiggleRoomS, squiggleRoomW);
		lineRoom.setRoomImages(lineRoomN, lineRoomE, lineRoomS, lineRoomW);
		
		//set the exits for each room
		start.setRoomExits(null, christmasRoom, purpleRoom, null);
		purpleRoom.setRoomExits(start, pinkRoom, null, null);
		christmasRoom.setRoomExits(null, null, pinkRoom, start);
		pinkRoom.setRoomExits(christmasRoom, null, squiggleRoom, purpleRoom);
		squiggleRoom.setRoomExits(pinkRoom, lineRoom, null, null);
		lineRoom.setRoomExits(null, null, null, squiggleRoom);
		
		//set the starting direction and room
		currentdirection = direction.NORTH;
		currentRoom = start;
		
		//Instantiate a new inventory so that this Model class has the items
		inv = new Inventory();
		
	}
	
		
		/**
		 * Set the controller of Model to the controller class
		 * 
		 * @param controller
		 * 
		 *  - Controller class calls this method in its constructor
		 */
		public void setController(Controller controller) {
			this.controller = controller;
		}

	
		/**
		 * Sets initial main image and item image and 
		 * 
		 *  - passes the first room image to the controller and tells the controller to tell the view to set it
		 *  - passes the first null item image to the controller and tells the controller to tell the view to set it
		 *  - passes all the items to the controller and the controller will tell the view to set them as items in the
		 *    comboBox
		 */
		public void initialise() {
			controller.set_item_image_view(this.getItemView());
			controller.set_main_view(currentRoom.getImage(currentdirection));
			controller.item_options_in_combobox(inv.objectsIn());
		}
		
		public void turnRight() {
			currentdirection = currentdirection.turnRight();
			controller.set_main_view(currentRoom.getImage(currentdirection));
		}
		
		public void turnLeft() {
			currentdirection = currentdirection.turnLeft();
			controller.set_main_view(currentRoom.getImage(currentdirection));
		}
		
		public void forward() {
			currentRoom = currentRoom.getExit(currentdirection);
			controller.set_main_view(currentRoom.getImage(currentdirection));
			controller.set_item_image_view(this.getItemView());
		}
		
		/**
		 * The controller calls this method to ask whether it is possible to move forward or not
		 */
		public void checkIfForwardNotPossible() {
			if (currentRoom.getExit(currentdirection)==(null)) {
				controller.forwardShouldBeDisallowed(true);
			}
			else {
				controller.forwardShouldBeDisallowed(false);
			}
		}
		
		/**
		 * Returns an image which is used to set the current room image view
		 * @return
		 */
		public Image setView() {
			Image image = currentRoom.getImage(currentdirection);
			return image;
		}
		
	
		/**
		 * sets the object in the current room to the string value (which will be an item from the combo drop down list)
		 * 
		 * passes the new item image to the controller and the controller will tell the view to set it
		 * 
		 * @param string
		 */
		public void putDownItem(String string) {
			currentRoom.setRoomObject(string);
			controller.set_item_image_view(this.getItemView());
		}
		
		
		/**
		 * sets the object in the current room to null
		 * 
		 * passes the new empty item image to the controller and the controller will tell the view to set it
		 * 
		 */
		public void pickObjectUp() {
			currentRoom.setRoomObject(null);
			controller.set_item_image_view(this.getItemView());
		}
	
		/**
		 * returns an image of the current item in the current room (which will be shown in the itemImageView)
		 * 
		 * @return
		 */
		public Image getItemView() {
			String string = currentRoom.getRoomObject();
		
			if (string != null) {
				Image ItemImage = (inv.getObjectImage(string));
				return ItemImage;
			}
			else {
				Image ItemImage = null;
				return ItemImage;
			}
		
			
		}
		


	
	

	
	
}
