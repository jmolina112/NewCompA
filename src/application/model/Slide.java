package application.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Slide {
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	private HashMap<String, int[]> spriteMap = new HashMap<String, int[]>();
	private String name;

	GraphicsContext canvas;
	/**
	 *
	 * @param name
	 * @param pList
	 */
	public Slide(String name, ArrayList<Sprite> pList, GraphicsContext gc){
		this.name = name;
		this.spriteList = pList;
		this.canvas = gc;
	}

	//loadSprites
	void loadSpritesInMap(){
		int[] intArray = new int[3];
		for(Sprite s : spriteList){
			spriteMap.put(s.getName(), intArray);
		}
	}

    public void display(){
        //render components
        for(Sprite component : spriteList){
            component.render(canvas);
        }

        drawDataPaths();

    }

    /**
     * Scans in the data pathes for every single Sprite object
     * Calls validComponentData and readLine
     */
    private void drawDataPaths() {
        Scanner scan = null;
        int lineNumber = 0;

        try {
            scan = new Scanner(new File("src/application/data/DataPath.txt"));

            //while a line is has been scanned
            while(scan.hasNextLine()) {
                lineNumber+=1;
                String line = scan.nextLine();

                //continue if empty line or a comment
                if(line == null || line.isEmpty() || line.charAt(0) != 'U')
                    continue;
                else if(validComponentData(line.split(" ")[0], lineNumber))
                    readLine(line, lineNumber);
            }
        }catch(FileNotFoundException e) {
            System.err.println("File not found!");
        }finally {
            if(scan != null)
                scan.close();
        }
    }

    	/*
	public void setComponentOff(String uName){
		for(int i = 0; i< this.componentList.size(); i++){
			if(this.componentList.get(i).getName().equals(uName)){
				componentList.get(i).setImage(0);
			}
		}
	}

	public void setComponentOn(String uName){
		for(int i = 0; i< componentList.size(); i++){
			if(componentList.get(i).getName().equals(uName)){
				componentList.get(i).setImage(1);
			}
		}
	}*/

    public void cll(Label loadLabel) {
        // TODO Auto-generated method stu
    }






    private boolean validComponentData(String componentName, int lineNumber) {
        boolean componExists = componentExists(componentName);
        if(!componExists) {
            String message ="Scanned component: "+ componentName + " doesn't exist!";
            printScanError(message, lineNumber);
        }
        return componExists;
    }

    /**
     * Sample data line:
     * U12 U110 U115
     *
     * U12's outPathSet will consist of U110 and U115
     * @param line
     */
    private void readLine(String line, int lineNumber) {
        String[] tokens = line.split(" ");
        Sprite component = getComponent(tokens[0]);

        Set<String> outPathSet = new HashSet<String>();

        /**draw data pathes onto emulator
         * and populate each Sprite's outPathSet String set
         * for every connection made	*/
        for(String outPathName : tokens) {
            //if this is the first token or the token is a nonexistent Sprite
            if(component.getName().equals(outPathName) || !validComponentData(outPathName, lineNumber))
                continue;

            component.drawSimpleConnection(canvas, getComponent(outPathName), Color.WHITE);
            outPathSet.add(outPathName);
        }

        getComponent(tokens[0]).setOutSet(outPathSet);
    }

    private void printScanError(String message, int lineNumber) {
        System.out.printf("Data Error: %s Line: %d\n", message, lineNumber);
    }


    public boolean componentExists(String name) {
        if(getComponent(name) == null)
            return false;
        else
            return true;
    }

    /**
     * Given a name String, returns the Sprite object from
     * componentList with that name
     * @param name
     * @return
     */
    protected  Sprite getComponent(String name) {

        for(Sprite element : spriteList) {
            if(element.getName().equals(name))
                return element;
        }
        return null;
    }


}