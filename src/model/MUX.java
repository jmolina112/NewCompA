package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MUX extends Sprite{

	public MUX(String string, int locationX, int locationY, int width, int height){
		super(string, locationX, locationY, width, height);
		this.setNameLabel("Mux");
	}

}
