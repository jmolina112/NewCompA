package model;

import java.util.ArrayList;

import controller.MainCon;

public class CPUModel extends Sprite{

	private String[] opcode = new String[4];
	private ArrayList<String> componentOrder = new ArrayList<String>();
	private String length;
	private String offset;
	private String instruction;
	private String[] controlSelect;
	private int instPointer;
	
	private int[] reg = new int[4];
	//private SystemModel system = MainCon.getSystem();
	
	private ArrayList<RAM> ramSticks;
	private int indexCounter;
	private int ramSelect;
	private boolean isStart;
	private boolean isDone;
	
	private ArrayList<Adder> logic;


	public CPUModel(String string, int locationX, int locationY, int width, int height, ArrayList<RAM> ramS){
		super(string, locationX, locationY, width, height);
		this.ramSticks = ramS;
		this.indexCounter = 0;
		this.ramSelect = 0;
		this.logic = new ArrayList<Adder>();
		//this.readOpcode();
		this.isStart = true;
		for(int i: this.reg) {
			i = 0;
		}
		this.isDone = false;
		while(this.isDone == false) {
			doOperation("");
		}
		
		/*for(int index = 0;index < this.componentOrder.size(); index++) {
			System.out.println("index: " + index + ", value: "+ this.componentOrder.get(index));
		}*/
		
	}
	
	public ArrayList<String> getComponenetList(){
		return this.componentOrder;
	}
	
	public String getOpCodes() {
		if(isStart == true) { // for the start
			indexCounter++;
			//this.isStart = false;
			return this.ramSticks.get(ramSelect).getFullMemeory()[0];
		}
		
		if(this.isDone == false) {
			for(int index = indexCounter; index < this.ramSticks.get(ramSelect).getFullMemeory().length; index++) {
				if(this.ramSticks.get(ramSelect).getFullMemeory()[indexCounter].equals("DONE")) {
					this.isDone = true;
					System.out.println("\n isDone\n");
					break;
				}
				else if(this.ramSticks.get(ramSelect).getFullMemeory()[indexCounter].equals("NEXT")){
					indexCounter++;
					return this.ramSticks.get(ramSelect).getFullMemeory()[indexCounter]; // return the next start token;
				}
				
				if(indexCounter >= this.ramSticks.get(ramSelect).getFullMemeory().length-1){
					indexCounter = 0;
					ramSelect++;
				}
				
				indexCounter++;
			}
		}
		
		return "DONE";
	}
	
	public int[] getRegLogic(int i){
		return this.logic.get(i).getReg();
	}
	
	public String getReg(char regType) {
		String reg = "";
		
		switch(regType) {
		case '0':
			return "U10";
		case '4':
			return "U11";
		case '8':
			return "U12";
		case 'c':
		case 'C':
			return "U13";
		}
		
		
		return reg;
	}
	
	public int getRegister(String name) {
		if(name.equalsIgnoreCase("U10")) {
			return 0;
		}else if(name.equalsIgnoreCase("U11")) {
			return 1;
		}else if(name.equalsIgnoreCase("U12")) {
			return 2;
		}else if(name.equalsIgnoreCase("U13")) {
			return 3;
		}
		
		return -1; // error checking
	}
	
	public void setReg(int type,String reg,boolean isValid) {
		
		String[] token  = reg.split(" ");
		
		// mov
		System.out.println("token 1: " + token[0]+ " token 2:" + token[1]);
		if(isValid) {
			switch(type) { 
			case 0: // reg,reg;
				this.reg[getRegister(token[0])] += this.reg[getRegister(token[1])];
				break;
			case 1: // reg, $HH
					this.reg[getRegister(token[0])] = (int)Long.parseLong(token[1],16); // Hex -> int
				break;
			case 2: // reg, $HHHH
					this.reg[getRegister(token[0])] = (int)Long.parseLong(token[1],16);
				break;
			case 3:// $HHHH, reg
					this.reg[getRegister(token[1])] = (int)Long.parseLong(token[0],16);
				break;
			}
		}
	}
	
	public void setMov(int type, String[] token) {
		
		switch(type) { 
		case 0: // reg,reg;
			this.reg[getRegister(token[0])] += this.reg[getRegister(token[1])];
			break;
		case 1: // reg, $HH
				this.reg[getRegister(token[0])] = (int)Long.parseLong(token[1],16); // Hex -> int
			break;
		case 2: // reg, $HHHH
				this.reg[getRegister(token[0])] = (int)Long.parseLong(token[1],16);
			break;
		case 3:// $HHHH, reg
				this.reg[getRegister(token[1])] = (int)Long.parseLong(token[0],16);
			break;
		}
	}
	
	public void andLogic(String[] token, boolean isBothReg) {
		
		
		if(isBothReg) {
			String regBin = Integer.toBinaryString(this.reg[getRegister(token[0])]);
			System.out.println(regBin);
			
			String regBin2 = Integer.toBinaryString(this.reg[getRegister(token[1])]);
			System.out.println(regBin);
		
		}else {
			String regBin = Integer.toBinaryString(this.reg[getRegister(token[0])]);
			System.out.println(regBin);
		
			String strBin = Integer.toBinaryString((int)Long.parseLong(token[1],16));
		}
	}
	
	public void setAnd(int type, String[] tok) {
		switch(type) { 
		case 0: // reg,reg;
			
			//this.reg[getRegister(token[0])] += this.reg[getRegister(token[1])];
			break;
		case 1: // reg, $HH
				//this.reg[getRegister(token[0])] = (int)Long.parseLong(token[1],16); // Hex -> int
			break;
		case 2: // reg, $HHHH
				//this.reg[getRegister(token[0])] = (int)Long.parseLong(token[1],16);
			break;
		case 3:// $HHHH, reg
				//this.reg[getRegister(token[1])] = (int)Long.parseLong(token[0],16);
			break;
		}
		
		
	}
	
	public void binaryLogic(int type, String[] tok) {
		
		switch(type) {
			case 0: // mov
				setMov(type,tok);
				break;
			case 1: //  // AND
				setAnd(type, tok);
				break;
			case 2: // Subb
				break;
			case 3: // 
				break;
			}
		
	}
	
	public String setRegister(int index,int type,boolean isValid) {
		
		String reg = "";
		
		char[] token = this.ramSticks.get(this.ramSelect).getFullMemeory()[index].toCharArray();
		
		
		//System.out.println("index: "+index + " val: " + this.ramSticks.get(this.ramSelect).getFullMemeory()[index]);
		switch(type) {
		case 0: // reg,reg
			reg = getReg(token[0]) + " " + getReg(token[1]);
			this.setReg(0, reg,isValid); // set registers for mov,mov
			break;
		case 1: // reg, $HH
			reg = getReg(token[0]);
			String newR = reg + " "+ this.ramSticks.get(this.ramSelect).getFullMemeory()[index+1];
			this.setReg(1, newR,isValid);
			break;
		case 2: // Reg, [$HHHH]
			reg = getReg(token[0]);
			String newW= reg + " "+ this.ramSticks.get(this.ramSelect).getFullMemeory()[index+1] + this.ramSticks.get(this.ramSelect).getFullMemeory()[index+2];
			this.setReg(2, newW, isValid);
			break;
		case 3:// [$HHHH],reg
			reg = getReg(token[0]);
			String r = this.ramSticks.get(this.ramSelect).getFullMemeory()[index-2] + this.ramSticks.get(this.ramSelect).getFullMemeory()[index-1] + " " + reg;
			this.setReg(3, r, isValid);
			break;
		}
		
		
		
		
		return reg;
	}
	
	
	//mine here
	/*public String readOpcode(){
		
		
		
		
		
		
		
		
		
		String ramSelect;
		int  memSelect;
		//ArrayList<Sprite> componentList = system.getComponentList();
		RAM component = null;
		EPROM component2 = null;
		
		String opcode = null;

		//get string at instPointer parse chip and offset (decode)
		System.out.println(instPointer);
		ramSelect = Integer.toBinaryString(instPointer).substring(0, 3);
		memSelect = Integer.decode(Integer.toBinaryString(instPointer).substring(4, Integer.toBinaryString(instPointer).length()));

		//get the memory chip this address is on
		switch(Integer.parseUnsignedInt(ramSelect)){
			case 0:
				ramSelect = "U200";
				break;
			case 1:
				ramSelect = "U201";
				break;
			case 2:
				ramSelect = "U202";
				break;
			case 3:
				ramSelect = "U203";
				break;
			case 4:
				ramSelect = "U204";
				break;
			case 5:
				ramSelect = "U205";
				break;
			case 6:
				ramSelect = "U206";
				break;
			case 7:
				ramSelect = "U207";
				break;
			case 8:
				ramSelect = "U208";
				break;
			case 9:
				ramSelect = "U209";
				break;
			case 10:
				ramSelect = "U210";
				break;
			case 11:
				ramSelect = "U211";
				break;
			case 12:
				ramSelect = "U212";
				break;
			case 13:
				ramSelect = "U213";
				break;
			case 14:
				//illegal memory access (should not happen, but need error in case someone intramSelect = "U213";
				break;
			case 15:
				ramSelect = "U215";
				break;
			default:
		}
				//should never get here. Ought to throw out of bounds error before ever getting here.
		return opcode;

		}

		//read memory location
		for(int i = 0; i < componentList.size(); i++){
			//get the right Ram chip
			if(componentList.get(i).getName().equals(ramSelect)){
				if (component == null){
					//read the opcode string at that memory location
					component2 = (EPROM) componentList.get(i);
					opcode = component2.getMemoryLocation(memSelect);
				} else {
					//read the opcode string at that memory location
					component = (RAM) componentList.get(i);
					opcode = component.getMemoryLocation(memSelect);
				}
			}
		}
		//return the byte String
		return "";
	}*/
	
	

	

	public void doOperation(String opcode){

		
		
		int one = 1;
		int three = 3;
		
		//this.opcode[0] = opcode;
		char[] comToken = this.getOpCodes().toCharArray(); //  get every start
		
		if(isDone == false) {
			if(isStart) {
				one = 0;
				this.isStart = false;
			}else {
				one = 1;
			}
			char firstDigit = comToken[0];
			char secondDigit = comToken[1];
			String line = "";
			switch(firstDigit){
				case '1':
					switch(secondDigit){
						case '0':
							String[] token = setRegister((this.indexCounter+one),0, true).split(" "); // need to increament index counter to match value register.
	//						this.opcode[1] = token[0];
	//						this.opcode[2] = token[1];
							line = "";
							line += addInstruction();
							line += token[1] + " ";
							line += token[0] + " ";
							line += "U112 ";
							line += "U113 ";
							line += "U100 " ;
							line += "U111 " ;
							line +="U118A ";
							line +="U114 ";
							line +=token[0];
							//System.out.println(line);
							componentOrder.add(line);	
							break;
						case '1':
							String[] token1 = setRegister((this.indexCounter+one),1,true).split(" ");
							this.opcode[1] = 
							line ="";
							line += addInstruction();
							line += token1[0] + " ";
							line +="U112 ";
							line +="U113 ";
							line +="U100 ";
							line +="U111 ";
							line +="U118A ";
							line +="U114 ";
							line +=token1[0];
							System.out.println(line);
							componentOrder.add(line);
							break;
						case '2': //Mov R8, [$MMMM]
							String[] token2 = setRegister((this.indexCounter+one),2, true).split(" ");
							line = "";
							line += addInstruction();
							line += token2[0] + " ";
							line += this.ramSticks.get(ramSelect).getName()+ " ";
							line += "112 ";
							line += "U113 ";
							line += "U100 ";
							line += "U111 ";
							line += "U118A ";
							line += "U114 ";
							line += token2[0];
							componentOrder.add(line);
							break;
						case '3':
							String[] token3 = setRegister((this.indexCounter+three),3, true).split(" ");
							line = "";
							line += addInstruction();
							line += token3[token3.length-1]+ " ";
							line += this.ramSticks.get(ramSelect).getName() + " ";
							line += "112 ";
							line +="U113 ";
							line +="U100 ";
							line +="U111 ";
							line += this.ramSticks.get(ramSelect).getName();
							componentOrder.add(line);
							break;
						default:
					}
					break;
				case '2':
					switch(secondDigit){
						case '0':
							String[] token = setRegister((this.indexCounter+one),0, true).split(" ");
							line = "";
							
							line += addInstruction();
							line +=token[1]+ " ";
							line +=token[0]+ " ";
							line +="U112 ";
							line +="U113 ";
							line +="U100 ";
							line +="U111 ";
							line +="U118A ";
							line +="U114 ";
							line +=token[0];
							componentOrder.add(line);
							break;
						case '1':
							String[] token2 = setRegister((this.indexCounter+one),1, true).split(" ");
							line = "";
							line += addInstruction();
							line +=token2[0] + " ";
							line +="U112 ";
							line +="U113 ";
							line +="U100 ";
							line +="U111 ";
							line +="U118A ";
							line +="U114 ";
							line +=token2[0];
							componentOrder.add(line);
							break;
						case '2': //Mov R8, [$MMMM]
							String[] token3 = setRegister((this.indexCounter+one),2, true).split(" ");
							line = "";
							line += addInstruction();
							line += token3[0] + " ";
							line += this.ramSticks.get(ramSelect).getName() + " ";
							line +="112 ";
							line +="U113 ";
							line +="U100 ";
							line +="U111 ";
							line +="U118A ";
							line +="U114 ";
							line +=token3[0];
							componentOrder.add(line);
							break;
						case '3':
							String[] token4 = setRegister((this.indexCounter+three),3,true).split(" ");
							line = "";
							line += addInstruction();
							line +=token4[token4.length-1] + " ";
							line +=this.ramSticks.get(ramSelect).getName() + " ";
							line +="112 ";
							line +="U113 ";
							line +="U100 ";
							line +="U111 ";
							line +=this.ramSticks.get(ramSelect).getName();
							componentOrder.add(line);
							break;
							default:
						}
					break;
				case '3': 
					switch(secondDigit){
						case '0':
							String[] token1 = setRegister((this.indexCounter+one),0,true).split(" ");
							line = "";
							line += addInstruction();
							line  +=token1[1] + " ";
							line  +=token1[0]+ " ";
							line  +="U112 ";
							line  +="U113 ";
							line  +="U100 ";
							line  +="U120 ";
							line  +="U110";
							componentOrder.add(line);
							break;
					case '1':
						String[] token2 = setRegister((this.indexCounter+one),1,true).split(" ");
						line = "";
						line += addInstruction();
						line +=token2[0] + " ";
						line +="U112 ";
						line +="U113 ";
						line +="U100 ";
						line +="U111 ";
						line +="U120 ";
						line +="U110";
						componentOrder.add(line);
	
						break;
					case '2': //Mov R8, [$MMMM]
						String[] token3 = setRegister((this.indexCounter+one),2,true).split(" ");
						line = "";
						line += addInstruction() + " ";
						line +=token3[0] + " ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line +="112 ";
						line +="U113 ";
						line +="U100 ";
						line +="U120 ";
						line +="U110 ";
						componentOrder.add(line);
						break;
					case '3':
						String[] token4 = setRegister((this.indexCounter+three),3,true).split(" ");
						line = "";
						line += addInstruction();
						line += token4[token4.length-1] + " ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line += "112 ";
						line += "U113 ";
						line += "U100 ";
						line += "U120 ";
						line += "U110 ";
						componentOrder.add(line);
						break;
				}
				break;
			case '4':
				switch(secondDigit){ //*****************single command
					case '0':
						String[] token = setRegister((this.indexCounter+one),1,false).split(" ");
						line = "";
						
						line += addInstruction();
						line += token[0] + " ";
						line += "U112 ";
						line += "U104 ";
						line += "U11 ";
						line += "U118A ";
						line += "U114 ";
						line += token[0];
						componentOrder.add(line);
						break;
					case '3':
						line = "";
						line += addInstruction();
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line += "U112 ";
						line += "U104 ";
						line += "U111 ";
						line += this.ramSticks.get(ramSelect).getName();
						componentOrder.add(line);
						break;
					default:
				}
				break;
			case '5':
				switch(secondDigit){
					case '0':
						String[] token = setRegister((this.indexCounter+one),0,true).split(" ");
						line= "";
						line += addInstruction();
						line += token[0] + " ";
						line += token[1] + " ";
						line += "U112 ";
						line += "U113 ";
						line += "U101 ";
						line += "U111 ";
						line += "U118A ";
						line += "U114 ";
						line += token[0] + " ";
						componentOrder.add(line);
						break;
	
						case '1':
							String[] token1 = setRegister((this.indexCounter+one),1,true).split(" ");
							line = "";
							line += addInstruction();
							line += token1[0] + " ";
							line += "U112 ";
							line += "U113 ";
							line += "U101 ";
							line += "U111 ";
							line += "U118A ";
							line += "U114 ";
							line += token1[0];
							componentOrder.add(line);
							break;
						case '2': //Mov R8, [$MMMM]
							String[] token2 = setRegister((this.indexCounter+one),2,true).split(" ");
							line ="";
							line += addInstruction();
							line += token2[0] + " ";
							line += this.ramSticks.get(ramSelect).getName() + " ";
							line += "112 ";
							line += "U113 ";
							line += "U101 ";
							line += "U111 ";
							line += "U118A ";
							line += "U114 ";
							line += token2[0];
							componentOrder.add(line);
							break;
						case '3':
							String[] token3 = setRegister((this.indexCounter+three),3,true).split(" ");
							line = "";
							line += addInstruction();
							line += token3[token3.length-1] + " ";
							line += this.ramSticks.get(ramSelect).getName() + " ";
							line += "112 ";
							line += "U113 ";
							line += "U101 ";
							line += "U111 ";
							line += this.ramSticks.get(ramSelect).getName();
							componentOrder.add(line);
							break;
	
					default:
						break;
				}
				break;
			case '6':
				switch(secondDigit){
					case '0':
						String[] token = setRegister((this.indexCounter+one),0,true).split(" ");
						line = "";
						
						line += addInstruction();
						line += token[0] + " ";
						line += token[1] + " ";
						line += "U112 ";
						line += "U113 ";
						line += "U102 ";
						line += "U111 ";
						line += "U118A ";
						line += "U114 ";
						line += token[0];
						componentOrder.add(line);
						break;
	
					case '1':
						String[] token1 = setRegister((this.indexCounter+one),1,true).split(" ");
						line = "";
						line += addInstruction();
						line += token1[0] + " ";
						line += "U112 ";
						line += "U113";
						line += "U102 ";
						line += "U111 ";
						line += "U118A ";
						line += "U114 ";
						line += token1[0] + " ";
						componentOrder.add(line);
						break;
					case '2': //Mov R8, [$MMMM]
						String[] token2 = setRegister((this.indexCounter+one),2,true).split(" ");
						line = "";
						line += addInstruction();
						line += token2[0] + " ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line += "112 ";
						line += "U113 ";
						line += "U102 ";
						line += "U111 ";
						line += "U118A ";
						line += "U114 ";
						line += token2[0];
						componentOrder.add(line);
						break;
					case '3':
						String[] token3 = setRegister((this.indexCounter+three),3,true).split(" ");
						line = "";
						line += addInstruction();
						line += token3[token3.length-1]+ " ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line += "112 ";
						line += "U113 ";
						line += "U102 ";
						line += "U111 ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						componentOrder.add(line);
						break;
				}
				break;
			case '7':
				switch(secondDigit){
				case '0':
					String[] token = setRegister((this.indexCounter+one),0,true).split(" ");
					line = "";
					line += addInstruction();
					line += token[0] + " ";
					line += token[1] + " ";
					line += "U112 ";
					line += "U113 ";
					line += "U103 ";
					line += "U111 ";
					line += "U118A ";
					line += "U114 ";
					line += token[0];
					componentOrder.add(line);
					break;
	
					case '1':
						String[] token1 = setRegister((this.indexCounter+one),1,true).split(" ");
						line = "";
						line += addInstruction();
						line += token1[0] + " ";
						line += "U112 ";
						line += "U113 ";
						line += "U103 ";
						line += "U111 ";
						line += "U118A ";
						line += "U114 ";
						line += token1[0];
						componentOrder.add(line);
						break;
					case '2': //Mov R8, [$MMMM]
						String[] token2 = setRegister((this.indexCounter+one),2,true).split(" ");
						line = "";
						line += addInstruction();
						line += token2[0]+ " ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line += "112 ";
						line += "U113 ";
						line += "U103 ";
						line += "U111 ";
						line += "U118A ";
						line += "U114 ";
						line += token2[0];
						componentOrder.add(line);
						break;
					case '3':
						String[] token3 = setRegister((this.indexCounter+three),3,true).split(" ");
						line = "";
						line += addInstruction();
						line += token3[token3.length-1] + " ";
						line += this.ramSticks.get(ramSelect).getName() + " ";
						line += "112 ";
						line += "U113 ";
						line += "U103 ";
						line += "U111 ";
						line += this.ramSticks.get(ramSelect).getName();
						componentOrder.add(line);
						break;
					default:
						break;
				}
				break;
			case '8':
				switch(secondDigit){
				case '0':
					String[] token = setRegister((this.indexCounter+one),0,true).split(" ");
	//				this.opcode[1] = token[0];
	//				this.opcode[2] = token[1];
					line = "";
					
					line += addInstruction();
					line += token[1] +" ";
					line += "U112 ";
					line += "U118A ";
					line += "U114 ";
					line += token[0];
					System.out.println(line);
					componentOrder.add(line);
					break;
				case '1':
					String[] token1 = setRegister((this.indexCounter+one),1,true).split(" ");
					line = "";
					line += addInstruction();
					line += "U118A ";
					line += "U114 ";
					line += token1[0];
					componentOrder.add(line);
					break;
				case '2': //Mov R8, [$MMMM]
					String[] token2 = setRegister((this.indexCounter+one),2,true).split(" ");
					line = "";
					
					line += addInstruction();
					line += "U100M ";
					line += this.ramSticks.get(ramSelect).getName() + " ";
					line += "U118A ";
					line += "U114 ";
					line += token2[0];
					componentOrder.add(line);
					break;
				case '3':
					String[] token3 = setRegister((this.indexCounter+three),3,true).split(" ");
					line = "";
				
					line += addInstruction();
					line += token3[token3.length-1] + " ";
					line += "U112 ";
					line += "U100M ";
					line += this.ramSticks.get(ramSelect).getName();
					componentOrder.add(line);
					break;
				default:
				}
			break;
	
			case 'B':
				switch(secondDigit){
					case '8':
						String[] token = setRegister((this.indexCounter+one),1,false).split(" ");
						line = "";
						line += addInstruction();
						line += token[0] + " ";
						line += "U112 ";
						line += "U113 ";
						line += "U115 ";
						line += "U15 ";
						componentOrder.add(line);
						break;
					case '9':
						
						line = "";
						line += addInstruction();
						line += "112 ";
						line += "113 ";
						line += "U115 ";
						line += "U15 ";
						componentOrder.add(line);
						break;
					default:
				}
				break;
	
			case 'D':
				line = "";
				line += "U110 ";
				line += addInstruction();
				line += "U500 ";
				line += "U106 ";
				line += "U115 ";
				line += "U15 ";
				componentOrder.add(line);
				break;
			case 'E':
				switch(secondDigit){
					case '0':
						//opcode = readOpcode();
						line = "";
						line += addInstruction();
						componentOrder.add(line);
						break;
					default:
				}
				break;
		}
			this.logic.add(new Adder(null,0,0,0,0,0));
			this.logic.get(this.logic.size()-1).setReg(this.reg);
			
			
			//System.out.println(componentOrder.get(componentOrder.size()-1));
		}else {
			this.isDone = true;
		}
		//return componentOrder;
	}

	//Returns the name of the register
	/*public String determineRegister(String opcode){
		switch(opcode.charAt(0)){
			case '0':
				return "U10";
			case '4':
				return "U11";
			case '8':
				return "U12";
			case 'C':
				return "U13";
			default:
				return "BROKEN";
		}
	}*/
	public String addInstruction(){
		String add = "";
		add += "U500 ";
		add += "U105 ";
		add += "U115 ";
		add += "U15 ";
		return add;
	}

	//mine here
	/*public String getOpcode(int i) {
		// TODO Auto-generated method stub
		return this.opcode[i];
	}

	public void setOpcode(String text, int i) {
		this.opcode[i] = text;
	}

	public int getInstPointer(){
		return this.instPointer;
	}

	public void setInstPointer(int i){
		this.instPointer = i;
	}*/

	
}
