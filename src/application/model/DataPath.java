package application.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DataPath {
	boolean isOn;
	double beginX, beginY;
	double endX, endY;
	
	
	Sprite beginSprite, endSprite;
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public DataPath(Sprite a, Sprite b) {
		
		this.beginX = a.getPositionX()+a.getWidth();
		this.endX = b.getPositionX();
		this.beginY = a.getPositionY()+a.getHeight()/2;
		this.endY = b.getPositionY() + b.getHeight()/2;
		
		beginSprite = a;
		endSprite = b;
	}
	
	
	public void drawPath(GraphicsContext canvas, Color color) {
		canvas.setFill(color);
		canvas.strokeLine(beginX, beginY, endX, endY);
	}
	
	
}
