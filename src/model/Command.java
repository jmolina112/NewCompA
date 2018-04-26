package model;
import model.OpCode.CommandType;

// Wrapper class
public class Command {

	private CommandType currentType;
	private String opCodeBinary;

	private int lineType;
	private int start;
	private String xdst;
	private String xdstHex;
	private String xdrcHex;
	private boolean isValueDst;
	private boolean isValueSrc;
	private boolean isJump;
	private int lineIndex = 0;
	private boolean isNot = false;
	private String jump;
	private boolean isNOP;

	//private String oneStart;
	private String twoStart;

	private String[] oneStart;

	private String hexStringToReturn;

	private boolean isFourBytes;
	private boolean isOneCommand;
	private String xsrc;
	private String xSrcHex;
	private String xCT;
	private String xReg;
	private String xReg2;
	private String xRegHex;
	private String xReg2Hex;
	private boolean isDollar;

	public Command() {
		this.opCodeBinary = "";
		this.jump = "";
		this.isJump = false;
		this.currentType = CommandType.NOP;
		this.lineType = -1;
		this.xCT = "";
		this.xdst = "";
		this.xReg = "";
		this.xReg2 = "";
		this.xReg2Hex  = "";
		this.xReg2Hex = "";
		this.xSrcHex = "";
		this.oneStart = new String[2];
		//this.oneStart = "";
		this.xsrc = "";
		this.start = 0;
		this.isDollar = false;
		this.isValueDst = false;
		this.isValueSrc = false;
		this.isFourBytes = false;
		this.isOneCommand = false;
		this.isNOP = false;
		this.hexStringToReturn = "";
		this.isNot = false;
	}

	public void setStartIndex(int st) {
		this.start = st;
	}
	public int getStartIndex() {
		return this.start;
	}

	public void setOneStart(String st, String st2) { // for jum, and NOP
		this.oneStart[0] = st;
		this.oneStart[1] = st2;
		if(st2 == null) {
			this.isNOP = true;
		}
		this.isOneCommand = true;
	}

	public void setXdst(String dst, boolean isDollar, int lineType) { //first
		this.lineIndex = lineType;
		if(isDollar) {
		this.xdstHex = turnHex(dst,0);
		}else {
			// register
			this.setRegister(dst, true);
		}


		this.xdst = dst;
	}

	public void setNot(String ct, boolean isDollar, boolean doRun, String dst,int lineType) {

		if(doRun) {
			this.xCT = ct;
			this.isNot = true;
			setXdst(dst, isDollar , lineType);
			//this.isOneCommand = false;
		}

	}


	public boolean getIsNop() {
		return this.isNOP;
	}

	public boolean getIsOneCommand() {
		return this.isOneCommand;
	}

	public void setLineType(int typeAdd) {
		int st = this.start;
		this.lineType = typeAdd;
		st += typeAdd;
		this.setXCT(st+"");
	}

	public void setCommandType(CommandType com) {
		this.currentType = com;
	}

	public CommandType getCommandType() {
		return this.currentType;
	}

	public void setXdst(String dst, boolean isDollar) { //first

		if(isDollar) {
		this.xdstHex = turnHex(dst,0);
		this.lineIndex = 1;
		}else {
			// register
			this.setRegister(dst, true);
		}


		this.xdst = dst;
	}

	public String turnHex(String sec,int reg) {

		// there is a $ in it.
		char[] token = sec.toCharArray();
		int count = 0;
		int start = 0;
		String hex = "";
		boolean progressForward = false;
		// need to save everything after the $MN  and turn it into hex xMN

		for(int index = 0 ; index < token.length; index++) {
			if(token[index] == '$') {
				progressForward = true;
				this.isDollar = true;
				start = index;
			}else if(progressForward == true && token[index] != '[' && token[index] != ']') {
				count++;
			}
		}


		// doing it with 2 bytes in mind
		if(count == 2) {
			hex =  "" + token[start+1] + token[start+2];
		}else if(count == 4) {
			hex += "" +token[start+1] + token[start+2] + " " + token[start+3] + token[start+4];
			this.isFourBytes = true;
		}
		if(reg ==0) {
			this.isValueDst = true;
		}else if(reg == 1)
		{
			this.isValueSrc = true;
		}

		// doing it with 4 bytes in mind

		return hex;
	}

	public String setRegister(String reg, boolean isDst) {

		String temp = "";
		// R0 -> 0000  -> 0
		// R1 -> 0100  -> 4
		// R2 -> 1000  -> 8
		// R3 -> 1100  -> c


		char[] token = reg.toCharArray();

		for(char c: token) {

			if(c != 'R' && c != 'r') {
				temp += c;
			}
		}

		if(temp.length() == 1) {
			if(isDst) { // single character
				this.xRegHex =  getHexForReg(temp.charAt(0));
			}else {
				this.xReg2Hex = getHexForReg(temp.charAt(0));
			}

		}else if (temp.length() == 2){ //
			this.xRegHex = "";
			this.xReg2Hex = "";
			if(isDst) {
				this.xRegHex += getHexForReg(temp.charAt(0));
				this.xRegHex += getHexForReg(temp.charAt(1));
			}else {
				this.xReg2Hex += getHexForReg(temp.charAt(0));
				this.xReg2Hex += getHexForReg(temp.charAt(1));
			}
		}
		return "";
	}

	public String getHexForReg(char c) {
		String temp = "";
		// R0 -> 0000  -> 0
		// R1 -> 0100  -> 4
		// R2 -> 1000  -> 8
		// R3 -> 1100  -> C

		switch(Integer.parseInt(c+"")) { // we would save the binary for this here.
		case 0:
			temp += "0";
			break;
		case 1:
			temp += "4";
			break;
		case 2:
			temp += "8";
			break;
		case 3:
			temp += "C";
			break;
		}

		return temp;
	}

	public boolean getisFourBytes() {
		return this.isFourBytes;
	}

	public void setXsrc(String src, boolean isDollar) { //second

		if(isDollar) {
			this.xSrcHex = turnHex(src,1);
		}else {
			// register.
			setRegister(src,false);
		}
		this.xsrc = src;
	}

	public void setXCT(String ct) {
		this.xCT = ""+ct;
	}


	public String getXdst() {
		return this.xdst;
	}
	public String getXsrc() {
		return this.xsrc;
	}

	public void setXJump(String ct) {
		this.jump = ct;
		this.isOneCommand = true;
		this.isJump = true;
	}

	public String getXCT() {
		return this.xCT;
	}

	public void setlineIndex(int index) {
		this.lineIndex = index;
	}

	public String toString() {


		// going to check
		if(this.isOneCommand == false) {
			switch(this.lineType) {
			case 0:
				// reg, reg
				this.hexStringToReturn = this.xCT + " " + this.xRegHex + this.xReg2Hex;
				break;
			case 1:
				// reg, $XX
				this.hexStringToReturn = this.xCT + " " + this.xRegHex+"0" + " " + this.xSrcHex;
				break;
			case 2:
				// reg, [$XXXX]
				this.hexStringToReturn = this.xCT + " " + this.xRegHex+"0" + " " + this.xSrcHex;
				break;
			case 3:
				// [$XXX], reg
				this.hexStringToReturn = this.xCT + " " + this.xdstHex + " " + this.xReg2Hex+"0";
				break;
			}
			if(this.isNot == true && this.isDollar == true) {
				this.hexStringToReturn = this.xCT + " " + this.xdstHex;
			}else if(this.isNot == true && this.isDollar == false) {
				this.hexStringToReturn = this.xCT + " " + this.xRegHex + "0";
			}
		}else {
			 // NOP and Jum type
				if(this.isNOP == true) {
					this.hexStringToReturn = this.oneStart[this.lineIndex];
				}else if(this.isDollar == true && this.isOneCommand == true && this.isJump == false) { //  jump
					this.hexStringToReturn = this.oneStart[this.lineIndex] + " " + this.xdstHex;
				}else if(this.isDollar == false && this.isOneCommand == true && this.isJump == false){
					this.hexStringToReturn = this.oneStart[this.lineIndex] + " " + this.xRegHex + "0";
				}else if(this.isDollar == true && this.isOneCommand == true && this.isJump == true) {
					//System.out.println("$ In Jump");
					this.hexStringToReturn = this.jump + " " + this.xdstHex;
				}else if(this.isDollar == false && this.isOneCommand == true && this.isJump == true){
					//System.out.println("Reg In Jump");
					this.hexStringToReturn = this.jump + " " + this.xRegHex + "0";
				}
		}

		return this.hexStringToReturn;
	}
}