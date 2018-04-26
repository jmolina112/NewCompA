package model;



import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite{
	
	private String comName;
	private Point loc;
	private Point size;
	private Image turnOn;
	private Image turnOff;
	private boolean isOn;
	private Point labelText;
	private Point nameTextLocation;
	private String nameLabel;
	
	private Image currentImage;
	
	public Sprite(String string, int positionX, int positionY, int width, int height) {
		
		this.comName = string;
		this.loc = new Point(positionX,positionY);
		this.size = new Point(width,height);
		this.labelText = new Point(this.loc.x,this.loc.y-10);
		this.nameTextLocation = new Point(this.loc.x+40, this.loc.y-10);
		
		try {
			turnOn = new Image(new FileInputStream("Resources/COMP_ON.png"));
			turnOff = new Image(new FileInputStream("Resources/COMP_OFF.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.currentImage = this.turnOff;
		this.isOn = false;
	}
	
	public void setNameLabel(int x, int y) {
		this.nameTextLocation.x += x;
		this.nameTextLocation.y += y;
	}
	
	public void setNameLabel(String f) {
		this.nameLabel = f;
	}
	
	public String getNameLabel() {
		return this.nameLabel;
	}
	
	public Point getNameTextLocation() {
		return this.nameTextLocation;
	}
	
	
	public Point getLabelTextLoc() {
		return this.labelText;
	}
	
	public boolean isOn() {
		return this.isOn;
	}
	
	public String getName() {
		return this.comName;
	}
	
	public void toggleImage() {
		
		if(this.isOn) {
			this.currentImage = this.turnOff;
			this.isOn = false;
		}else {
			this.currentImage = this.turnOn;
			this.isOn = true;
		}
		
	}
	
	public Image getCurrentImage() {
		return this.currentImage;
	}
	
	public Point getLoc() {
		return this.loc;
	}
	
	public Point getSize() {
		return this.size;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*private String name;
	
	*//**Image file for sprite*//*
	private Image image;
	
	*//**Horizontal position of sprite*//*
	private double positionX;
	*//**Vertical position of sprite*//*
    private double positionY;
    *//**Width of the image*//*
    private double width;
    *//**height of the image*//*
    private double height;
    
    private int RED_X;
    private int RED_Y;
    
	public Sprite(String string, double positionX, double positionY, double width, double height) {
		this.name = string;
		this.setImage(0);
		this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        RED_X = (int)positionX+10;
        RED_Y = (int)(positionY+(this.height/2));
	}

	*//**Draw the sprite on the graphics context at its current position.*//*
	public void render(GraphicsContext gc){
	
        gc.drawImage(image, positionX, positionY, width, height);
    	
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.YELLOW);
		gc.strokeText(this.name, positionX, positionY);
		redText(gc);
    }

    *//**Sets width of sprite to the value provided.*//*
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void redText(GraphicsContext gc) {
		int componentNumber = SystemModel.getComponentNumber(this.name);
		char lastChar = this.name.charAt(this.name.length()-1);
		
		gc.setStroke(Color.RED);
		
		
		final String[] redLabelArray = {"Add/Sub", "And", "Or", "XOR", "Not"};
		switch(componentNumber) {
		//8-1 MUX labels
		case 118:
		case 112:
		case 113:
			gc.strokeText("8-1 MUX", positionX+10, positionY+(this.height/2));
			break;
		//4-1 MUX labels
		case 115:
		case 116:
			gc.strokeText("4-1 MUX", RED_X, RED_Y);
			break;
		//Add labels
		case 105:
		case 106:
			gc.strokeText("Add", RED_X, RED_Y);
			break;
		case 100:
		case 101:
		case 102:
		case 103: 
		case 104:
			if(lastChar != 'M')
				gc.strokeText(redLabelArray[componentNumber%100], RED_X, RED_Y);
			break;
		default:
			break;
		}		
	}
	
	public int getComponentNumber(String name) {
		*//**Parse the Int from Sprite's name*//*
		char lastChar = this.name.charAt(this.name.length()-1);
		
		if(lastChar == 'A' || lastChar == 'B' || lastChar == 'M') 
			return Integer.parseInt(this.name.substring(1, this.name.length()-1));
		else
			return Integer.parseInt(this.name.substring(1));
	}
	
	public void simpleConnection(GraphicsContext gc, Sprite other) {
		
		double aPort = this.positionX+this.width;
		double bPort = other.positionX;
		double yLine = this.getPositionY()+this.getHeight()/2;
		double bLine = other.positionY + other.getHeight()/2;
		gc.setStroke(Color.BLUE);
		gc.strokeLine(aPort, yLine, bPort, bLine);
	}
	*//**Get the current width of the sprite.*//*
	public double getWidth() {
		return width;
	}

	*//**Sets height of sprite to the value provided.*//*
	public void setHeight(double height) {
		this.height = height;
	}

	*//**Get the current width of the sprite.*//*
	public double getHeight() {
		return height;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public void setPosition(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setImage(int i) {
		if(i == 0){
			this.image = this.turnOff;
		}else {
			this.image = this.turnOn;
		}
	}

	public String getName() {
		return this.name;
	}*/
}