package application.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Sprite {

	/**Image file for sprite*/
	private int width;
	private int height;
	private int locationX;
	private int locationY;
	private Image on = new Image("file:../GDS/Resources/COMP_ON.png");
	private Image off = new Image("file:../GDS/Resources/COMP_OFF.png");
	private Image image;

	public Sprite(int locationX, int locationY, int width, int height){
		this.setPosition(locationX, locationY);
		this.setWidth(width);
		this.setHeight(height);
		this.turnOn();
	}

	public void turnOn(){
		this.image = on;
	}

	public void trunOff(){
		this.image = off;
	}

	/**Draw the sprite on the graphics context at its current position.*/
	public void render(GraphicsContext gc)
    {
        gc.drawImage(image, locationX, locationY, width, height);
    }

    /**Sets width of sprite to the value provided.*/
	public void setWidth(int width) {
		this.width = width;
	}

	/**Get the current width of the sprite.*/
	public int getWidth(){
		return this.width;
	}

	/**Sets height of sprite to the value provided.*/
	public void setHeight(int height){
		this.height = height;
	}

	/**Get the current width of the sprite.*/
	public int getHeight(){
		return this.height;
	}

	public int getPositionX(){
		return this.locationX;
	}

	public int getPositionY(){
		return this.locationY;
	}

	public void setPosition(int positionX, int positionY){
		this.locationX = positionX;
		this.locationY = positionY;
	}
}
