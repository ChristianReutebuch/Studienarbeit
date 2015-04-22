package TSP;

public class Node {

	private int posX = 0;
	private int posY = 0;
	private int radius = 0;
	private boolean isStartnode = false;
	private boolean isEndnode = false;
	
	int getPosX() {
		return posX;
	}
	void setPosX( int x ){
		posX = x;
	}
	
	int getPosY(){
		return posY;
	}
	void setPosY( int y ){
		posY = y;
	}
	
	int getRadius(){
		return radius;
	}
	void setRadius( int r ){
		radius = r;
	}
	
	boolean isStartnode(){
		return isStartnode;
	}
	void setStartnode(){
		isStartnode = true;
	}
	
	boolean isEndnode(){
		return isEndnode;
	}
	void setEndnode(){
		isEndnode = true;
	}

}
