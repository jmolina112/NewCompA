package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import controller.MainCon;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SystemModel {

	private GraphicsContext emulator;
	private ArrayList<Sprite> componentList = new ArrayList<Sprite>();
	private Iterator<Sprite> componentIter = componentList.iterator();
	private String loadText[] = {"loading", "loading.", "loading..", "loading..."};
	private HashMap<String, int[]> componentMap= new HashMap<String, int[]>();
	

	public SystemModel(){
		
		//4-1 MUX for inst pointer source
		Sprite U115 = new MUX4to1("U115",50,25,120,150);
		componentList.add(U115);
		//16 bit register for inst pointer
		Sprite U15 = new Register16("U15",250,75,120,45);
		componentList.add(U15);
		//16 bit adders for inst pointer increment and offset
		Sprite U105 = new Adder("U105",275,200,120,80, 4);
		componentList.add(U105);
		Sprite U106 = new Adder("U106",425,225,120,80, 4);
		componentList.add(U106);
		//4-1 MUX for memory address selection
		Sprite U116 = new MUX4to1("U116",500,25,120,150);
		componentList.add(U116);
		//2-4 DEMUX for register selection
    	
		//8-1 MUXs for register source selection
		Sprite U118A = new MUX8to1("U118A",600,375,150,80);
		componentList.add(U118A);
		Sprite U118B = new MUX8to1("U118B",800,375,150,80);
		componentList.add(U118B);
		//8 bit memory registers
		Sprite U10 = new Register8("U10",775,25,70,45);
		componentList.add(U10);
		Sprite U11 = new Register8("U11",775,100,70,45);
		componentList.add(U11);
		Sprite U12 = new Register8("U12",775,175,70,45);
		componentList.add(U12);
		Sprite U13 = new Register8("U13",775,250,70,45);
		componentList.add(U13);
		//8-1 MUXs for data bus selection
		Sprite U112 = new MUX8to1("U112",900,25,70,150);
		componentList.add(U112);
		Sprite U113 = new MUX8to1("U113",900,200,70,150);
		componentList.add(U113);
		//16 bit adder for stack pointer
		Sprite U107 = new Adder("U107",850,550,120,80,4);
		componentList.add(U107);
		//8 bit logical adder-subtractor
		Sprite U100 = new Adder("U100",1025,50,80,80,2);
		componentList.add(U100);
		//logical operators
		Sprite U101 = new LogicFunc("U101",1025,150,80,80);
		componentList.add(U101);
		Sprite U102 = new LogicFunc("U102",1025,250,80,80);
		componentList.add(U102);
		Sprite U103 = new LogicFunc("U103",1025,350,80,80);
		componentList.add(U103);
		Sprite U104 = new LogicFunc("U104",1025,450,80,80);
		componentList.add(U104);
		//2-1 MUX for stack pointer source
		Sprite U117 = new MUX2to1("U117",1000,550,120,80);
		componentList.add(U117);
		//2-1 MUX for flag register source
		Sprite U120 = new MUX2to1("U120",1150,50,80,80);
		componentList.add(U120);
		//4 bit flag register
		Sprite U110 = new Register4("U110",1250,50,45,45);
		componentList.add(U110);
		//8-1 MUX for ALU source
		Sprite U111 = new MUX8to1("U111",1200,250,80,150);
		componentList.add(U111);
		//16 bit register for stack pointer
		Sprite U14 = new Register16("U14",1150,550,120,45);
		componentList.add(U14);
		//Decoder for memory address
		Sprite U100M = new Decoder("U100M",400,450,150,120);
		componentList.add(U100M);
		//4-1 MUX for memory source
		Sprite U220 = new MUX4to1("U220",200,450,150,80);
		componentList.add(U220);
		//T-Gate for read-write
		Sprite U221 = new TGate("U221",50,450,80,45);
		componentList.add(U221);
		
		
		//4k RAMs
		Sprite U200 = new RAM("U200",550,600,70,45);
		componentList.add(U200);
		Sprite U201 = new RAM("U201",450,600,70,45);
		componentList.add(U201);
		Sprite U202 = new RAM("U202",350,600,70,45);
		componentList.add(U202);
		Sprite U203 = new RAM("U203",250,600,70,45);
		componentList.add(U203);
		Sprite U204 = new RAM("U204",550,700,70,45);
		componentList.add(U204);
		Sprite U205 = new RAM("U205",450,700,70,45);
		componentList.add(U205);
		Sprite U206 = new RAM("U206",350,700,70,45);
		componentList.add(U206);
		Sprite U207 = new RAM("U206",250,700,70,45);
		componentList.add(U207);
		Sprite U208 = new RAM("U207",550,800,70,45);
		componentList.add(U208);
		Sprite U209 = new RAM("U208",450,800,70,45);
		componentList.add(U209);
		Sprite U210 = new RAM("U209",350,800,70,45);
		componentList.add(U210);
		Sprite U211 = new RAM("U211",250,800,70,45);
		componentList.add(U211);
		Sprite U212 = new RAM("U212",150,600,70,45);
		componentList.add(U212);
		Sprite U213 = new RAM("U213",150,700,70,45);
		componentList.add(U213);
		Sprite U215 = new EPROM("U215",150,800,70,45);
		componentList.add(U215);
		this.loadIntoMemory();
		//printTest();
		//CPU
		Sprite U500 = new CPUModel("U500",50,225,180,150,this.getRamList());
		componentList.add(U500);
		
		Sprite U114 = new CPUModel("U114",600,225,130,80, this.getRamList());
    	componentList.add(U114);
		
		//U10.simpleConnection(emulator, U112);
		//SystemModelDummy();
		
		
	
		//printTest();
	}
	
	
	
	/**********Testing**********************************************/
	public void printTest() {
		if(!(this.getRam("U200").getFullMemeory() == null)) {
			for(int index = 0; index < this.getRam("U200").getFullMemeory().length; index++) {
			System.out.println(index + ": " +this.getRam("U200").getFullMemeory()[index]);
			}
		}
	}
	
	public ArrayList<RAM> getRamList(){
		ArrayList<RAM> ramStick= new ArrayList<RAM>();
		
		ramStick.add(this.getRam("U200"));
		ramStick.add(this.getRam("U201"));
		ramStick.add(this.getRam("U202"));
		ramStick.add(this.getRam("U203"));
		ramStick.add(this.getRam("U204"));
		ramStick.add(this.getRam("U205"));
		ramStick.add(this.getRam("U206"));
		ramStick.add(this.getRam("U207"));
		ramStick.add(this.getRam("U208"));
		ramStick.add(this.getRam("U209"));
		ramStick.add(this.getRam("U210"));
		ramStick.add(this.getRam("U211"));
		ramStick.add(this.getRam("U212"));
		ramStick.add(this.getRam("U213"));
		ramStick.add(this.getRam("U214"));
		//ramStick.add(this.getRam("U215"));
		
		
		return ramStick;
	}
	
	
	
	/**We'll probably have to incorporate a map instead
	 * of an ArrayList. It makes retrieving objects
	 * quicker and more efficent.
	 */
	public void SystemModelDummy() {
		int num = 0;
		
		//U201 - U209
		while(num < 10) {
			for(int y = 600; y <= 800; y=y+100) {
				for(int x = 550; x >= 250; x=x-100) {
					String name = "U20"+num;
					int[] intArray = {x, y, 70, 45};
					componentMap.put(name, intArray);
					num+=1;
				}
			}
		}
		
		//U210 - 211
		for(int lastY = 600; lastY < 900; lastY=lastY+100) {
			String name = "U2"+num;
			int[] intArray = {150, lastY, 70, 45};
			componentMap.put(name, intArray);
			num+=1;
		}
		
		//U212 - U215
		for(int j = 600; j < 900; j=j+100) {
			String name = "U2"+num;
			int[] intArray = {150, j, 70, 45};
			componentMap.put(name, intArray);
			num+=1;
		}
		
		
	
	}
	
	public void loadIntoMemory() {
		try {
			Scanner scan = new Scanner(new File("Resources\\OpcodeOutput.txt"));
			int countIndex = 0; // will be increamented after each
			int ramNameIndex = 0;
			String comName = "U20"+ramNameIndex;

			while(scan.hasNextLine()) {
				String[] token = scan.nextLine().split(" ");

				for(int index = 0; index < token.length;index++) { // every element in file
					//.println(getRam(comName).getName());
					getRam(comName).setMemoryLoaction(countIndex, token[index]);
					countIndex++;

					// check here for ram select
					if(countIndex >= this.getRam(comName).getFullMemeory().length-1) {
						ramNameIndex++;
						// update comName
						comName = "U20" + ramNameIndex;
						countIndex = 0;
					}

				}
				getRam(comName).setMemoryLoaction(countIndex,"NEXT");
				countIndex++;
			}
			getRam(comName).setMemoryLoaction(countIndex, "DONE");


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**Animation loop and runtime which handles updates to the game screen.
	 * @return */
	/*public void beginDisplay(GraphicsContext emulator){
		loadIntoMemory();
		this.emulator = emulator;
		new AnimationTimer(){
			int j;
			double lastNanoTime = 0;

			@Override
			public void handle(long arg0) {
				//double lastNanoTime = 0;		//last loop time
				//TODO: this is reseting it to 0 everytime, should be moved outside loop, but
				//don't know how this will affect things so leaving it here for now.


				//calculate clock, step up to 100 milliseconds
				double elapsed = (arg0 - lastNanoTime)/1000000000;
				lastNanoTime = arg0;

				//paint background
				emulator.setFill(Color.DARKGRAY);
				emulator.fillRect(0, 0, 1325, 875);;

				//render DATA
				//render INST
				//render IP inc
				//render IP rel
				//render IP new
				//render IP
				//render OFFSET
				//render SP
				//render MEM address
				//render MEM 1-4

				//render A in
				//render B in
				//render ALU
				//render flags
				//render SP sum
				//render R sp
				//render CS
				//render Clk
				//render REG in
				//render R out
				//render U cs
				//render MEM data

				//render components
				for(Sprite component : componentList){
				//	component.render(emulator);
				}
				//drawLines();
				//testing turn off
				//setComponentOff("U500");
			}
		}.start();
	}
	*/
	public Sprite getComponent(String name) {
		Sprite s = null;
		for(Sprite element : componentList) {
			if(element.getName().equals(name))
				return element;
		}
		return null;
	}
	
	public static int getComponentNumber(String name) {
		char lastChar = name.charAt(name.length()-1);
		
		if(lastChar == 'A' || lastChar == 'B' || lastChar == 'M') 
			return Integer.parseInt(name.substring(1, name.length()-1));
		else
			return Integer.parseInt(name.substring(1));
	}
	/*
	public Sprite getComponentFromMap(String name) {
		
		int[] A= componentMap.get(name);
		int componentNumber = getComponentNumber(name);
		
		if(componentNumber >= 200 && componentNumber <= 213)
			return new RAM(name, A[0], A[1], A[2], A[3]);
		else if(componentNumber >= 10 && componentNumber <= 13)
			return  new Register8(name,A[0],A[1],A[2],A[3]);
		else if(componentNumber >=101 && componentNumber<= 104)
			return new LogicFunc(name, A[0], A[1], A[2], A[3]);
		else if(componentNumber == 117 || componentNumber == 210)
			return new MUX2to1(name, A[0], A[1], A[2], A[3]);
		else if()
		switch(componentNumber) {
		case 118:
		case 112:
		case 113:
			return new MUX8to1(name, A[0], A[1], A[2], A[3]);
			
		}
	}*/
	//This part from Jacinto
		public RAM getRam(String comName) {
			
			//System.out.println(comName);
			//System.out.println(this.componentList.get(0).getName());
			for(int index = 0; index < this.componentList.size(); index++) {
				if(this.componentList.get(index).getName().equalsIgnoreCase(comName)) {
					//System.out.println("in if to out");
					return (RAM)this.componentList.get(index);
				}
			}
			//System.out.println("Returning null");
			return null;
		}
		
		public ArrayList<Sprite> getComponent(){
			return this.componentList;
		}
		
		public CPUModel getCpuModel() {
			CPUModel cp = (CPUModel) getComponent("U500");
			return cp;
		}
	/*public void setComponentOff(String uName){
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
	
	/*public void cll(Label loadLabel) {
		// TODO Auto-generated method stu
	}
	private void drawLines() {
		 Sprite A = getComponent("U10");
		 Sprite U112 = getComponent("U112");
		 Sprite U113 = getComponent("U113");
		 A.simpleConnection(emulator, U112);
		
		 getComponent("U114").simpleConnection(emulator,  A);
		 
		 String[] registerNames = {"U10", "U11", "U12", "U13"};
		 for(String register : registerNames) {
			 Sprite registerSp = getComponent(register);
			 registerSp.simpleConnection(emulator, U112);
			 registerSp.simpleConnection(emulator, getComponent("U113"));
			 getComponent("U114").simpleConnection(emulator, registerSp);
		 }
			 
		 
		 String[] arthNames = {"U100", "U101", "U102", "U103", "U104", "U117"};
		 for(String arth : arthNames) {
			 U112.simpleConnection(emulator, getComponent(arth));
			 U113.simpleConnection(emulator, getComponent(arth));
			 getComponent(arth).simpleConnection(emulator, getComponent("U111"));
		 }
		 
		 Sprite U114 = getComponent("U114");
		 getComponent("U118A").simpleConnection(emulator, U114);
		 getComponent("U118B").simpleConnection(emulator, U114);
			
		 	 
	}*/
	
	public ArrayList<Sprite> getComponentList(){
		return this.componentList;
	}
}
