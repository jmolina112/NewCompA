package application.model;

public class Adder extends Sprite {

	private int dataSize;
	private String outputValue;
	private String inputA;
	private String inputB;
	private String FlagValue;
	private int carryFlag;
	private int zeroFlag;
	private int overflowFlag;
	private int signFlag;

	public Adder(String string, double locationX, double locationY, double width, double height, int dataSize){
		super(string, locationX, locationY, width, height);
		this.dataSize = dataSize;
	}

	public void addInputs(){
		//add values
		outputValue = Integer.toHexString(Integer.decode(inputA) + Integer.decode(inputB));
		//find flags
		calculateFlags();
		//need to fix output if it is to long
		if(outputValue.length() > dataSize){
			outputValue = outputValue.substring(0, dataSize - 1);
		}
	}

	public void subtractInputs(){
		outputValue = Integer.toHexString(Integer.decode(inputA) - Integer.decode(inputB));
	}

	private void calculateFlags() {
		//if the first character of output is 8 or more, then sign flag set
		if(Integer.decode(String.valueOf(outputValue.charAt(0))) > 7){
			signFlag = 1;
		} else {
			signFlag = 0;
		}
		//if output value is 0, the zero flag is set
		if(Integer.decode(outputValue) == 0){
			zeroFlag = 1;
		} else {
			zeroFlag = 0;
		}
		//if,then overflow flag is set
		/*TODO:
		if(){
			overflowFlag = 1;
		} else {
			overflowFlag = 0;
		}*/
		//if output string is longer than dataSize, then carry flag is set
		if(outputValue.length() > dataSize){
			carryFlag = 1;
		} else {
			carryFlag = 0;
		}
		FlagValue = Integer.toHexString(carryFlag * 1 + zeroFlag * 1 + signFlag * 1 + overflowFlag * 1);
	}

	public void setInputA(String value){
		inputA = value;
	}

	public void setInputB(String value){
		inputB = value;
	}

	public String getOutputValue(){
		return outputValue;
	}
	public String getFlagValue(){
		return FlagValue;
	}
}
