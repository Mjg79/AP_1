package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;
import Model.ElementAndBoxAndDirection.Element;

import java.util.Random;

import static Model.ElementAndBoxAndDirection.Direction.north;

public abstract class Animal extends Element {
	 protected int speed;
	 boolean isKilled = false;
	 private boolean isMoved = false;
	 Random random = new Random();
	{
		speed = 1;
		direction = Direction.getRandomDirection();
		x = (random.nextInt(30) + 5);
		y = (random.nextInt(30) + 5);
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	private int getSpeed() {
		return speed;
	}

	public void setIsKilled(boolean check) {
	    isKilled = check;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void moveRandomly(int power) {
		switch(this.getDirection()) {
    	case north:
    		this.y -= this.getSpeed() * power;
    		break;
    	case south:
    		this.y += this.getSpeed() * power;
    		break;
    	case east:
    		this.x += this.getSpeed() * power;
    		break;
    	case west:
    		this.x -= this.getSpeed() * power;
    		break;
    	case northEast:
    		this.y -= this.getSpeed() * power;
    		this.x += this.getSpeed() * power;
    		break;
    	case northWest:
    		this.y -= this.getSpeed() * power;
    		this.x -= this.getSpeed() * power;
    		break;
    	case southEast:
    		this.y += this.getSpeed() * power;
    		this.x += this.getSpeed() * power;
    		break;
    	case southWest:
    		this.y += this.getSpeed() * power;
    		this.x -= this.getSpeed() * power;
    		break;    
    	}
    }

    private void moveWiselyToNorth(double x, double y) {
		if (!isMoved) {
			this.direction = north;
			moveRandomly(3);
			isMoved = true;
		}
	}

	private void moveWiselyToSouth(double x, double y) {
		if (!isMoved) {
			this.direction = Direction.south;
			moveRandomly(3);
			isMoved = true;
		}
	}

	private void moveWiselyToEast(double x, double y) {
		if (!isMoved) {
			this.direction = Direction.east;
			moveRandomly(3);
			isMoved = true;
		}
	}

	private void moveWiselyToWest(double x, double y) {
		if (!isMoved) {
			this.direction = Direction.west;
			moveRandomly(3);
			isMoved = true;
		}
	}

	private void moveWiselyToNorthEast(double x, double y) {
		if (!isMoved) {
	      this.direction = Direction.northEast;
	      moveRandomly(3);
	      isMoved = true;
		}
	}

	private void moveWiselyToSouthEast(double x, double y) {
		if (!isMoved) {
			this.direction = Direction.southEast;
			moveRandomly(3);
			isMoved = true;
		}
	}

	private void moveWiselyToSouthWest(double x, double y) {
		if (!isMoved) {
			this.direction = Direction.southWest;
			moveRandomly(3);
			isMoved = true;
		}
	}

	private void moveWiselyToNorthWest(double x, double y) {
		if (!isMoved) {
			this.direction = Direction.northWest;
			moveRandomly(3);
			isMoved = true;
		}
	}

	public void moveWisely(double x, double y) {
		if (this.x == x && this.y > y)
			moveWiselyToNorth(x, y);
		if (this.x == x && this.y < y)
			moveWiselyToSouth(x, y);
		if (this.y == y && this.x < x)
			moveWiselyToEast(x, y);
		if (this.y == y && this.x > x)
			moveWiselyToWest(x, y);
		if (this.x < x && this.y > y)
			moveWiselyToNorthEast(x, y);
		if (this.x < x && this.y < y)
			moveWiselyToSouthEast(x, y);
		if (this.x > x && this.y < y)
			moveWiselyToSouthWest(x, y);
		if (this.x > x && this.y > y)
			moveWiselyToNorthWest(x, y);
		isMoved = false;
	}


	private void pickUpSuitableDirectionInTheWestBorder() {
    	if(x == 5 && y > 5.1 && y < 34.9) {
    		while(direction.equals(Direction.west) || direction.equals(Direction.northWest) 
    				|| direction.equals(Direction.southWest)) 
    			this.direction = Direction.getRandomDirection();
    	  }	
    }
    
    private void pickUpSuitableDirectionInTheEastBorder() {
    	   if(x == 35 && y > 5.1 && y < 34.999) {
       		while(direction.equals(Direction.east) || direction.equals(Direction.northEast) 
       				|| direction.equals(Direction.southEast)) 
       			this.direction = Direction.getRandomDirection();
       	  }	
       
    }
    
    private void pickUpSuitableDirectionInTheNorthBorder() {
    	if(y == 5 && x > 5.1 && x < 34.9) {
    		while(direction.equals(north) || direction.equals(Direction.northWest)
    				|| direction.equals(Direction.northEast)) 
    			direction = Direction.getRandomDirection();
    	}
    }
    
    private void pickUpSuitableDirectionInTheSouthBorder() {
    	if(y == 35 && x > 5.1 && x < 34.9) {
    		while(direction.equals(Direction.south) || direction.equals(Direction.southEast)
    				|| direction.equals(Direction.southWest))
    			direction = Direction.getRandomDirection();
    	}
    }
    
    private void pickUpSuitableDirectionInTheNorthWestBorder() {
    	if(x == 5 && y == 5) {
    		while(!(direction.equals(Direction.east) || direction.equals(Direction.south)
    				|| direction.equals(Direction.southEast)))
    			direction = Direction.getRandomDirection();
			}
    		
    }
    
    private void pickUpSuitableDirectionInTheNorthEastBorder() {

     	if(x == 5 && y == 35) {
    		while(direction.equals(Direction.west) || direction.equals(Direction.southWest) ||
			       direction.equals(Direction.south) || direction.equals(Direction.southEast) ||
			        direction.equals(Direction.northWest))
    			direction = Direction.getRandomDirection();
			}
   
    }
    
    private void pickUpSuitableDirectionIntheSouthWestBorder() {
    	if(x == 35 && y == 5) {
    		while(direction.equals(Direction.north) || direction.equals(Direction.northWest) ||
			direction.equals(Direction.northEast) || direction.equals(Direction.east) ||
			   direction.equals(Direction.southEast)) {
				this.direction = Direction.getRandomDirection();
				}

    	}
    }

    private void pickUpSuitableDirectionInTheSouthEastBorder() {
    	if(x == 35 && y == 35) {
			while (!(direction.equals(Direction.west) || direction.equals(north)
					|| direction.equals(Direction.northWest)))
				direction = Direction.getRandomDirection();
		}
    }
    
    public void changeDirectionByKnowingCurrentPostition() {
       this.pickUpSuitableDirectionInTheWestBorder();
       this.pickUpSuitableDirectionInTheEastBorder();
       this.pickUpSuitableDirectionInTheNorthBorder();
       this.pickUpSuitableDirectionInTheSouthBorder();
       this.pickUpSuitableDirectionInTheNorthEastBorder();
       this.pickUpSuitableDirectionInTheNorthWestBorder();
       this.pickUpSuitableDirectionInTheSouthEastBorder();
       this.pickUpSuitableDirectionIntheSouthWestBorder();
    }


	@Override
	public boolean upgrade() {
		return false;
	}
}
