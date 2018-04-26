package application.model;

import java.util.ArrayList;

import application.controller.MainCon;

public class CPUModel extends Sprite{

	private String[] opcode = new String[4];
	private String length;
	private String offset;
	private String instruction;
	private String[] controlSelect;
	private int instPointer;
	private SystemModel cpuSystem = MainCon.getSystem();
	//ben's here


	//mine here
	public String readOpcode(){
		String ramSelect;
		int  memSelect;
		cpuSystem = MainCon.getSystem();
		System.out.println(cpuSystem.getComponentList());
		ArrayList<Sprite> componentList = cpuSystem.getComponentList();
		RAM component = null;
		EPROM component2 = null;
		String opcode = null;

		//get string at instPointer parse chip and offset (decode)
		ramSelect = Integer.toBinaryString(instPointer).substring(0, 3);
		memSelect = Integer.decode(Integer.toBinaryString(instPointer).substring(4, Integer.toBinaryString(instPointer).length()));
		
		int componentNumber = Integer.parseUnsignedInt(ramSelect);
		ramSelect = "U" + String.valueOf(200+componentNumber);
		//get the memory chip this address is on
		/*switch(Integer.parseUnsignedInt(ramSelect)){
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
				//should never get here. Ought to throw out of bounds error before ever getting here.

		}*/

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
		return opcode;
	}

	public ArrayList<String> doOperation(String opcode){
		char firstDigit = opcode.charAt(0);
		char secondDigit = opcode.charAt(1);
		ArrayList<String> componentOrder = new ArrayList<String>();
		//ben's here
		this.opcode[0] = opcode;

		switch(firstDigit){
			case '1':
				switch(secondDigit){
					case '0':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[2]));
						componentOrder.add(determineRegister(this.opcode[1]));
						componentOrder.add("U112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '1':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						componentOrder.add("U112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '2': //Mov R8, [$MMMM]
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						this.opcode[3] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						//componentOrder.add(ramSelectComponent(opcode[]);
						componentOrder.add("112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '3':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						this.opcode[3] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						//componentOrder.add(ramSelectComponent(opcode[]);
						componentOrder.add("112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						//componentOrder.add(ramSelectComponent(opcode[]);
						break;
					default:
				}
				break;
			case '2':
				switch(secondDigit){
					case '0':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[2]));
						componentOrder.add(determineRegister(this.opcode[1]));
						componentOrder.add("U112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '1':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						componentOrder.add("U112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '2': //Mov R8, [$MMMM]
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						this.opcode[3] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						//componentOrder.add(ramSelectComponent(opcode[]);
						componentOrder.add("112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '3':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						this.opcode[3] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						//componentOrder.add(ramSelectComponent(opcode[]);
						componentOrder.add("112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U111");
						//componentOrder.add(ramSelectComponent(opcode[]);
						break;
						default:
					}
				break;
			case '3':
				switch(secondDigit){
					case '0':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[2]));
						componentOrder.add(determineRegister(this.opcode[1]));
						componentOrder.add("U112");
						componentOrder.add("U113");
						componentOrder.add("U100");
						componentOrder.add("U120");
						componentOrder.add("U110");
						break;
				case '1':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add("U112");
					componentOrder.add("U113");
					componentOrder.add("U100");
					componentOrder.add("U111");
					componentOrder.add("U120");
					componentOrder.add("U110");

					break;
				case '2': //Mov R8, [$MMMM]
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					this.opcode[3] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("112");
					componentOrder.add("U113");
					componentOrder.add("U100");
					componentOrder.add("U120");
					componentOrder.add("U110");
					break;
				case '3':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					this.opcode[3] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("112");
					componentOrder.add("U113");
					componentOrder.add("U100");
					componentOrder.add("U120");
					componentOrder.add("U110");
					break;
			}
			break;
		case '4':
			switch(secondDigit){
				case '0':
					this.opcode[1] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add("U112");
					componentOrder.add("U104");
					componentOrder.add("U11");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;
				case '3':
					this.opcode[1] = readOpcode();
					addInstruction(componentOrder);
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("U112");
					componentOrder.add("U104");
					componentOrder.add("U111");
					//componentOrder.add(ramSelectComponent(opcode[]);
					break;
				default:
			}
			break;
		case '5':
			switch(secondDigit){
				case '0':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add(determineRegister(this.opcode[2]));
					componentOrder.add("U112");
					componentOrder.add("U113");
					componentOrder.add("U101");
					componentOrder.add("U111");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;

					case '1':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						componentOrder.add("U112");
						componentOrder.add("U113");
						componentOrder.add("U101");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '2': //Mov R8, [$MMMM]
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						this.opcode[3] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						//componentOrder.add(ramSelectComponent(opcode[]);
						componentOrder.add("112");
						componentOrder.add("U113");
						componentOrder.add("U101");
						componentOrder.add("U111");
						componentOrder.add("U118A");
						componentOrder.add("U114");
						componentOrder.add(determineRegister(this.opcode[1]));
						break;
					case '3':
						this.opcode[1] = readOpcode();
						this.opcode[2] = readOpcode();
						this.opcode[3] = readOpcode();
						addInstruction(componentOrder);
						componentOrder.add(determineRegister(this.opcode[1]));
						//componentOrder.add(ramSelectComponent(opcode[]);
						componentOrder.add("112");
						componentOrder.add("U113");
						componentOrder.add("U101");
						componentOrder.add("U111");
						//componentOrder.add(ramSelectComponent(opcode[]);
						break;

				default:
					break;
			}
			break;
		case '6':
			switch(secondDigit){
				case '0':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add(determineRegister(this.opcode[2]));
					componentOrder.add("U112");
					componentOrder.add("U113");
					componentOrder.add("U102");
					componentOrder.add("U111");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;

				case '1':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add("U112");
					componentOrder.add("U113");
					componentOrder.add("U102");
					componentOrder.add("U111");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;
				case '2': //Mov R8, [$MMMM]
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					this.opcode[3] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("112");
					componentOrder.add("U113");
					componentOrder.add("U102");
					componentOrder.add("U111");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;
				case '3':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					this.opcode[3] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("112");
					componentOrder.add("U113");
					componentOrder.add("U102");
					componentOrder.add("U111");
					//componentOrder.add(ramSelectComponent(opcode[]);
					break;
			}
			break;
		case '7':
			switch(secondDigit){
			case '0':
				this.opcode[1] = readOpcode();
				this.opcode[2] = readOpcode();
				addInstruction(componentOrder);
				componentOrder.add(determineRegister(this.opcode[1]));
				componentOrder.add(determineRegister(this.opcode[2]));
				componentOrder.add("U112");
				componentOrder.add("U113");
				componentOrder.add("U103");
				componentOrder.add("U111");
				componentOrder.add("U118A");
				componentOrder.add("U114");
				componentOrder.add(determineRegister(this.opcode[1]));
				break;

				case '1':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add("U112");
					componentOrder.add("U113");
					componentOrder.add("U103");
					componentOrder.add("U111");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;
				case '2': //Mov R8, [$MMMM]
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					this.opcode[3] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("112");
					componentOrder.add("U113");
					componentOrder.add("U103");
					componentOrder.add("U111");
					componentOrder.add("U118A");
					componentOrder.add("U114");
					componentOrder.add(determineRegister(this.opcode[1]));
					break;
				case '3':
					this.opcode[1] = readOpcode();
					this.opcode[2] = readOpcode();
					this.opcode[3] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					//componentOrder.add(ramSelectComponent(opcode[]);
					componentOrder.add("112");
					componentOrder.add("U113");
					componentOrder.add("U103");
					componentOrder.add("U111");
					//componentOrder.add(ramSelectComponent(opcode[]);
					break;

				default:
					break;
			}
			break;
		case '8':
			switch(secondDigit){
			case '0':
				this.opcode[1] = readOpcode();
				this.opcode[2] = readOpcode();
				addInstruction(componentOrder);
				componentOrder.add(determineRegister(this.opcode[2]));
				componentOrder.add("U112");
				componentOrder.add("U118A");
				componentOrder.add("U114");
				componentOrder.add(determineRegister(this.opcode[1]));
				break;
			case '1':
				this.opcode[1] = readOpcode();
				this.opcode[2] = readOpcode();
				addInstruction(componentOrder);
				componentOrder.add("U118A");
				componentOrder.add("U114");
				componentOrder.add(determineRegister(this.opcode[1]));
				break;
			case '2': //Mov R8, [$MMMM]
				this.opcode[1] = readOpcode();
				this.opcode[2] = readOpcode();
				opcode = readOpcode();
				addInstruction(componentOrder);
				componentOrder.add("U100M");
				//componentOrder.add(ramSelectComponent(opcode[]);
				componentOrder.add("U118A");
				componentOrder.add("U114");
				componentOrder.add(determineRegister(this.opcode[1]));
				break;
			case '3':
				this.opcode[1] = readOpcode();
				this.opcode[2] = readOpcode();
				opcode = readOpcode();
				addInstruction(componentOrder);
				componentOrder.add(determineRegister(this.opcode[2]));
				componentOrder.add("U112");
				componentOrder.add("U100M");
				//componentOrder.add(ramSelectComponent(opcode));
				break;
			default:
			}
		break;

		case 'B':
			switch(secondDigit){
				case '8':
					this.opcode[1] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add(determineRegister(this.opcode[1]));
					componentOrder.add("U112");
					componentOrder.add("U113");
					componentOrder.add("U115");
					componentOrder.add("U15");
					break;
				case '9':
					this.opcode[1] = readOpcode();
					addInstruction(componentOrder);
					componentOrder.add("112");
					componentOrder.add("113");
					componentOrder.add("U115");
					componentOrder.add("U15");
					break;
				default:
			}
			break;

		case 'D':
			componentOrder.add("U110");
			addInstruction(componentOrder);
			componentOrder.add("U500");
			componentOrder.add("U106");
			componentOrder.add("U115");
			componentOrder.add("U15");
			break;
		case 'E':
			switch(secondDigit){
				case '0':
					opcode = readOpcode();
					addInstruction(componentOrder);
					break;
				default:
			}
			break;
		default:
	}
		return componentOrder;
	}

	//Returns the name of the register
	public String determineRegister(String opcode){
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
	}
	
	public void addInstruction(ArrayList<String> list){
		list.add("U500");
		list.add("U105");
		list.add("U115");
		list.add("U15");
	}

	//mine here
	public String getOpcode(int i) {
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
	}

	public CPUModel(String string, int locationX, int locationY, int width, int height){
		super(string, locationX, locationY, width, height);
	}
}
