package main;
import Rooms.Model;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Controller {
	
	
	private Model model;
	private MVCView mvcView;
	
	
	/**
	 * Constructor takes a model and a view argument
	 * 
	 * @param model
	 * @param mcview
	 * 
	 * - takes arguments to set its model and view classes (done in Main)
	 * 
	 * - tells these model and view classes what its controllers are (this class) to link all three classes
	 * 
	 * - tell the view to initialise (view asks the controller to ask the model which images to display first, 
	 *   then the controller relays this to the view
	 */
	public Controller(Model model,MVCView mcview) {
		this.model = model;
		this.mvcView = mcview;
		this.model.setController(this);
		this.mvcView.setController(this);
		this.mvcView.Initialise();
	}
	
	
	/**
	 * Initialises view 
	 * 
	 * - when Controller class is created, it tells the view class to initialise
	 * 
	 * - the view class asks the controller class to ask the model which initial image to show and asks the controller 
	 *   to check the model whether to initially disable the forward button
	 * 
	 * - Model also says all items to put in the drop down options
	 * 
	 * - View class says to initially not show the instruction page
	 */
	public void initialise_view() {
		this.model.initialise();
		
	}
	
	
	/**
	 * Relays between Model and controller to set drop down menu in ComboBox
	 * 
	 * @param listOfItems
	 * 
	 * - called when the Model class is initialised
	 * 
	 *  - Model send controller list of items made
	 *  
	 *  - controller tells view these items so the view can set them in the ComboBox
	 */
	public void item_options_in_combobox(ObservableList<String> listOfItems) {
		this.mvcView.setAllInitialComboboxOptions(listOfItems);
	}
	
	
	
	/**
	 * Movement methods and image update methods
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
	 */
	
	public void call_turn_right() {
		this.model.turnRight();
		
	}
	
	public void call_turn_left() {
		this.model.turnLeft();
	
	}
	
	public void call_move_forward() {
		this.model.forward();
	}
	
	public void set_main_view(Image image) {
		this.mvcView.setImageView(image);
	}
	
	public void set_item_image_view(Image image) {
		this.mvcView.setImageItemView(image);
	}
	
	
	/**
	 * Asks the model to determine if it's possible to go forward
	 * 
	 * - called from the view when a user presses a button (first the view communicates with the other classes to move and
	 *   update the images where appropriate, and then this function is called
	 *   
	 * - once the view asks this method to be called, then this controller tells the model to check whether it's possible
	 *   to go forward if there's an exit
	 *   
	 * - the model then tells the controller to execute the next method, forwardShouldBeDisallowed
	 * 
	 * - if there's no exit, it sets the boolean in the next method to false, and if there is an exit, it sets the boolean
	 *   in the next method to true 
	 */
	public void checkIfForwardPossible() {
		this.model.checkIfForwardNotPossible();
	}
	
	
	/**
	 * Tells the view whether to disable the forward button or not 
	 * 
	 * @param bool
	 * 
	 * - following on from the previous method, the controller tells the view whether to display the forward button
	 * 
	 * - as disabled or not depending on whether there's an exit or not (if the boolean is true or false)
	 */
	public void forwardShouldBeDisallowed(boolean bool) {
		this.mvcView.disableForwardAsOption(bool);
	}
	
	
	/**
	 * Relays between view and model class when a user wants to put down an item in a room
	 * 
	 * @param string
	 * 
	 * - view class tells the controller to call put down item with the value of the item selected in the comboBox as its parameter
	 * 
	 * - the controller calls the putDownItem method from the model with the same parameter passed
	 * 
	 * - then the model sets the item in that room to the item that was selected in the comboBox
	 * 
	 * - the model finds the new image for the itemImageView and tells the controller to tell the view to set it as such
	 */
	public void call_put_down_item(String string) {
		this.model.putDownItem(string);
	}
	
	
	/**
	 * Relays between view and model class when a user wants to pick up an item in a room
	 * 
	 * - view class tells the controller to tell the model class to execute the method pickObjectUp
	 * 
	 * - the model class sets the items in the room to null
	 * 
	 * - it then gets the empty image view and tells the controller to tell the view to set it in the itemImageView
	 */
	public void call_pick_up_item() {
		this.model.pickObjectUp();
	}
	
	

}
