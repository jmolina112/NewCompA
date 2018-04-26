package application.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Set;

/**
 * Generic sprite model implementing motion, rendering, and boundary checking.
 *
 * @author Not Michael Cavazos
 * <p>
 */
public abstract class Sprite {

    private String name;

    protected int inputPortCnt;

    /**
     * Horizontal position of sprite
     */
    private double positionX;
    /**
     * Vertical position of sprite
     */
    private double positionY;
    /**
     * Width of the image
     */
    private double width;
    /**
     * height of the image
     */
    private double height;
    /**
     * Horizontal position of red label
     */
    private int RED_X;
    /**
     * Vertical position of red label
     */
    private int RED_Y;

    private Set<String> inSet = new HashSet<String>();
    private Set<String> outSet = new HashSet<String>();

    private boolean isOn;
    private Color fillColor;

    /**
     * Sprite constructor.
     *
     * @param string    name of the Sprite
     * @param positionX Sprite's X coordinate
     * @param positionY Sprite's Y coordinate
     * @param width     Sprite's width
     * @param height    Sprite's height
     */
    public Sprite(String string, double positionX, double positionY, double width, double height) {
        this.name = string;
        //this.setImage(0);

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;

        RED_X = (int) positionX + 10;
        RED_Y = (int) (positionY + (this.height / 2));

        this.isOn = false;
        setFillColor();
    }

    /**
     * Draw the sprite on the graphics context at its current position.
     */
    public void render(GraphicsContext gc) {
        //Color the Sprite
        gc.setFill(this.fillColor);
        gc.fillRect(positionX, positionY, width, height);

        //Set the labels
        gc.setFont(new Font("Tahoma", 24));
        gc.setFill(Color.YELLOW);
        gc.fillText(this.name, positionX, positionY);

        setRedLabel(gc);
    }


    /**
     * @param gc
     */
    private void setRedLabel(GraphicsContext gc) {
        gc.setFont(new Font("Tahoma", 24));

        int componentNumber = SystemModel.getComponentNumber(this.name);

        //Set labels based on componentNumber
        switch (componentNumber) {
            //8-1 MUX labels
            case 118:
            case 112:
            case 113:
                gc.fillText("8-1\nMUX", positionX + 10, positionY + (this.height / 2));
                break;
            //4-1 MUX labels
            case 115:
            case 116:
                gc.fillText("4-1\nMUX", RED_X, RED_Y);
                break;
            //Add labels

            case 105:
            case 106:
            case 15:
                gc.setFill(Color.BLACK);
                if(componentNumber == 15)
                    gc.fillText("Inst Pointer", RED_X, RED_Y);
                else
                    gc.fillText("Add", RED_X, RED_Y);
                break;
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
                final String[] redLabelArray = {"Add/Sub", "And", "Or", "XOR", "Not"};
                char lastChar = this.name.charAt(this.name.length() - 1);

                if (lastChar != 'M')
                    gc.fillText(redLabelArray[componentNumber % 100], RED_X, RED_Y);
                break;
            default:
                break;
        }
    }

    /**
     * Draws a 'simple' line connection between two Sprites.
     * The line directly connects two Sprites together.
     *
     * @param gc
     * @param other The Sprite this Sprite will connect with
     * @param color
     */
    public void drawSimpleConnection(GraphicsContext gc, Sprite other, Color color) {
        double aPort = this.positionX + this.width;
        double bPort = other.positionX;
        double yLine = this.getPositionY() + this.getHeight() / 2;
        double bLine = other.positionY + other.getHeight() / 2;

        gc.setStroke(color);
        gc.setFill(color);

        if(color == Color.LIGHTBLUE)
            gc.setLineWidth(4);
        else
            gc.setLineWidth(3);

        gc.strokeLine(aPort, yLine, bPort, bLine);
    }

    public boolean activateDataPath(GraphicsContext gc, String destName){
        boolean isFound = false;

        for(String s : outSet){
            if(s.equals(destName)) {
                isFound = true;
                break;
            }
        }
        if(!isFound) return false;
        Sprite destSprite = SystemModel.getComponent(destName);


        drawSimpleConnection(gc, destSprite, Color.BLUE);
        return true;
    }


    private void setFillColor() {
        String className = this.getClass().getSimpleName();

        if (className.equals("Adder"))
            this.fillColor = Color.RED;
        else if (className.equals("MUX4to1"))
            this.fillColor = Color.ORANGE;
        else if (className.equals("CPUModel"))
            this.fillColor = Color.YELLOW;
        else if (className.equals("RAM"))
            this.fillColor = Color.WHITE;
        else
            this.fillColor = Color.PINK;
    }

    /**
     * Sets width of sprite to the value provided.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Get the current width of the sprite.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets height of sprite to the value provided.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the current width of the sprite.
     */
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


    public String getName() {
        return this.name;
    }

    public void addInPath(String inSpriteName) {
        this.inSet.add(inSpriteName);
    }

    public void addOutPath(String outSpriteName) {
        this.outSet.add(outSpriteName);
    }

    protected void setOutSet(Set<String> set) {
        this.outSet.clear();
        this.outSet.addAll(set);
    }
}