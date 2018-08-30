package main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MVCView {
	
	//SceneBuilder objects
	@FXML
	private ImageView imageView;
	
	@FXML
	private ImageView itemImageView;
	
	@FXML
	private Button forwardButton;
	
	@FXML
	private MenuBar menubar;
	
	@FXML
	private Menu menu;
	
	@FXML
	private MenuItem menuitem;
	
	@FXML
	private TextArea instructionText;
	
	@FXML
	private ComboBox<String> comboBox;
	
	/**
	 * Set the controller of the MCView class initially to null
	 * 
	 * - in Main, this class and the Model class are created first, then Controller
	 * 
	 * - then in the controller constructor, it sets this class' controller to itself, so they will be linked
	 */
	private Controller controller = null;
	
	
	/**
	 * Set the controller of MCView to the controller class
	 * 
	 * - Controller class uses this method in its constructor
	 * @param controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
	/**
	 * initialise view
	 * 
	 * - set instructions to invisible (the user must click a button to show them)
	 * 
	 * - ask the controller to ask the model which initial image to show
	 * 
	 * - ask the controller to check the model whether to initially disable the forward button
	 */
	public void Initialise() {
		instructionText.setVisible(false);
		controller.initialise_view();
		controller.checkIfForwardPossible();
	}
	
	
	/**
	 * Set main image view (images of rooms)
	 * 
	 * @param image
	 */
	public void setImageView(Image image) {
		imageView.setImage(image);
	}
	
	
	/**
	 * Set small image view (images of items)
	 * 
	 * @param image
	 */
	public void setImageItemView(Image image) {
		itemImageView.setImage(image);
	}
	
	
	/**
	 * Puts all item options in the drop down menu of the ComboBox
	 * 
	 * @param listOfItems
	 * 
	 * - called when the Model is initialised
	 */
	public void setAllInitialComboboxOptions(ObservableList<String> listOfItems) {
		((ComboBox<String>)comboBox).setItems(listOfItems);
	}

	
	/**
	 * Method for disabling the forward button if there's no exit ahead
	 * 
	 * @param bool
	 * 
	 * - Model calls a method to check whether to do this every time a move is made
	 */
	public void disableForwardAsOption(boolean bool) {
		forwardButton.setDisable(bool);
	}
	
	
	/**
	 * Movement methods
	 * 
	 * - called when user clicks a movement button
	 * 
	 * - view tells controller to ask model to move
	 * 
	 * - model updates direction or room accordingly 
	 * 
	 * - model then tells controller to call set the main or item view image methods
	 * 
	 * - the controller tells the view to set the new image
	 * 
	 * - then the view asks the controller to ask the model to check whether it's possible to go forward, and relays
	 *   the message back to the view so that the forward button can be disabled accordingly
	 */
	
	public void turnRight() {
		controller.call_turn_right();
		controller.checkIfForwardPossible();
	}

	public void turnLeft() {
			controller.call_turn_left();
			controller.checkIfForwardPossible();
	}
		
	public void forward() {
	
		controller.call_move_forward();		
		controller.checkIfForwardPossible();
	}
	

	
	/**
	 * When the user presses the button to put down an item, this method is called to display the item image in the imageView
	 * 
	 * - view class tells the controller to call put down item with the value of the item selected in the comboBox as its
  	 *   parameter
  	 *   
  	 *   - the controller calls the putDownItem method from the model with the same parameter passed
  	 *   
  	 *   - then the model sets the item in that room to the item that was selected in the comboBox
  	 *   
  	 *   - the model finds the new image for the itemImageView and tells the controller to tell the view to set it as such
	 */
	public void putDownObject() {
		controller.call_put_down_item(comboBox.getValue());	
	}
	
	
	/**
	 * When the user presses the button to pick up an item in a room, this method is called to remove the item from the room
	 * and display an empty imageView
	 * 
	 * - view class tells the controller to tell the model class to execute the method pickObjectUp
	 * 
	 * - the model class sets the items in the room to null
	 * 
	 * - it then gets the empty image view and tells the controller to tell the view to set it in the itemImageView
	 */
	public void pickObjectUp() {
		controller.call_pick_up_item();
	}
	
	
	/**
	 * Makes instructions visible or not visible when user clicks instruction button in SceneBuilder
	 * 
	 * - does not change any information in the model, it only changes the interface and what the user sees, 
	 *   so does not need to talk to controller or model
	 */
	public void showInstructions() {
		if (!instructionText.isVisible())
		instructionText.setVisible(true);
		
		else if (instructionText.isVisible())
		instructionText.setVisible(false);
	}
	
	
}
	
