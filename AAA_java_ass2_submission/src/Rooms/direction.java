package Rooms;

public enum direction {
	
	
	NORTH, EAST, SOUTH, WEST;
	
	
	public direction turnRight() {
		if (equals(direction.NORTH)) return direction.EAST;
		else if (equals(direction.EAST)) return direction.SOUTH;
		else if (equals(direction.SOUTH)) return direction.WEST;
		else if (equals(direction.WEST)) return direction.NORTH;
		else return null;
		
	}
	
	
	public direction turnLeft() {
		if (equals(direction.NORTH)) return direction.WEST;
		else if (equals(direction.EAST)) return direction.NORTH;
		else if (equals(direction.SOUTH)) return direction.EAST;
		else if (equals(direction.WEST)) return direction.SOUTH;
		else return null;
		
	}
	
}
