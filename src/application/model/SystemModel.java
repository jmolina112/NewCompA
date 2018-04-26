package application.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class SystemModel {

	private GraphicsContext emulator;
	private static ArrayList<Sprite> componentList = new ArrayList<Sprite>();
	private Iterator<Sprite> componentIter = componentList.iterator();

	private HashMap<String, int[]> componentMap= new HashMap<String, int[]>();

	private final String DATA_PATH = "Resources/OpcodeOutput.txt";
    private Slide slide1;
    /**
     * Constructor
     */
	public SystemModel(){
		/*
		//CPU
		Sprite U500 = new CPUModel("U500",50,225,180,150);
		componentList.add(U500);
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
		*/
        loadSlide1();
	}

	private void loadSlide1(){

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

        final int ADDER_X = 400, ADDER_Y = 300;
        final int CPU_X = 50, CPU_Y = 200;
        final int U115_X = 600, U115_Y = 50;
        final int MUX4to1_H = 150, MUX4to1_W = 75;
        final int U116_Y = 300;
        //TODO: Have object classes contain a width and height variable based on class name/type
        Sprite U500 = new CPUModel("U500", CPU_X, CPU_Y, 200, 250);
        componentList.add(U500);
        Sprite U105 = new Adder("U105", ADDER_X, ADDER_Y, 120, 80, 4);
        componentList.add(U105);
        Sprite U106 = new Adder("U106", ADDER_X,ADDER_Y+100,120,80, 4);
        componentList.add(U106);
        Sprite U115 = new MUX4to1("U115", U115_X, U115_Y, MUX4to1_W, MUX4to1_H);
        componentList.add(U115);
        Sprite U15 =  new Register16("U15",U115_X+200, U115_Y+50,120,45);
        componentList.add(U15);
        Sprite U116 = new MUX4to1("U116",U115_X+400, U116_Y, MUX4to1_W, MUX4to1_H);
        componentList.add(U116);
        //U10.simpleConnection(emulator, U112);
        //SystemModelDummy();


    }

    private void loadSlide2(){
        //2-4 DEMUX for register selection
        Sprite U114 = new CPUModel("U114",600,225,130,80);
        componentList.add(U114);
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
    }

	/**We'll probably have to incorporate a map instead
	 * of an ArrayList. It makes retrieving objects
	 * quicker and more efficent.
	 */
	private void SystemModelDummy() {
		int num = 0;

		//U201 - U209
		while(num < 10) {
			for(int y = 600; y <= 800; y=y+100) {
				for(int x = 550; x >= 250; x=x-100) {
					int[] intArray = {x, y, 70, 45};
					componentMap.put("U"+String.valueOf(200+num), intArray);
					num+=1;
				}
			}
		}

		//U210 - 211
		for(int lastY = 600; lastY < 900; lastY=lastY+100) {
			int[] intArray = {150, lastY, 70, 45};
			componentMap.put("U2"+num, intArray);
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
			Scanner scan = new Scanner(new File(DATA_PATH));
			int countIndex = 0; // will be increamented after each
			int ramNameIndex = 0;
			String comName = "U20"+ramNameIndex;

			while(scan.hasNextLine()) {
				String[] token = scan.nextLine().split(" ");

				for(int index = 0; index < token.length;index++) { // every element in file

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
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("");
			e.printStackTrace();
		}
	}



	/**Animation loop and runtime which handles updates to the game screen.
	 * @return */
	public void beginDisplay(GraphicsContext emulator){
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
				emulator.setFill(Color.BLACK);
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


				//drawLines();
                slide1 = new Slide("CPU", componentList, emulator);
                slide1.display();
				//testing turn off
				//setComponentOff("U500");
			}
		}.start();
	}
    /**
     * Given a name String, returns the Sprite object from
     * componentList with that name
     * @param name
     * @return
     */
    protected  static Sprite getComponent(String name) {

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

		for(int index = 0; index < this.componentList.size(); index++) {
			if(this.componentList.get(index).getName().equals(comName)) {
				return (RAM)this.componentList.get(index);
			}
		}
		return null;
	}

	public Slide getSlide1(){
	    return this.slide1;
    }
	public ArrayList<Sprite> getComponentList(){
		return this.componentList;
	}
}
