package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.shape.Path;

/**
 *
 * @author Jacinto Molina
 *
 */
public class OpCode {
	enum CommandType {Mov,Addc,Subb, Cmp,Not,And, Or,Xor,Jmp,NOP};
	ArrayList<Command> listOfCommand = new ArrayList<Command>();
	private String fileName;
	private boolean isNot;

	public OpCode(String f) {
		this.fileName = f;
		this.readFileOpCode(this.fileName);
		this.isNot = false;
	}

	public void getlineType(String dst, String src) {

		// four types for all commands, mov, add, sub, and, or, xor, Cmp, addc, subb
		// 0. Register ,Register
		// 1. Register, $HH <- whatever the fuck that is.
		// 2. Register, [$MMMM]
		// 3. [$MMMM], Register

		if(this.listOfCommand.get(this.listOfCommand.size()-1).getIsOneCommand() == false && src != null) {
			boolean isDstDoll = false;
			boolean isSrcDoll = false;
			int lineType = 0;

			char[] tokDst = dst.toCharArray();
			char[] tokSrc = src.toCharArray();

			for(char c: tokDst) {
				if(c == '$') {
					isDstDoll = true;
				}
			}

			for(char c: tokSrc) {
				if(c == '$') {
					isSrcDoll = true;
				}
			}

			this.listOfCommand.get(this.listOfCommand.size()-1).setXdst(dst, isDstDoll);
			this.listOfCommand.get(this.listOfCommand.size()-1).setXsrc(src, isSrcDoll);

			if(isDstDoll == false && isSrcDoll == false) {
				lineType = 0;
			}else if(isDstDoll == false && isSrcDoll == true) {
				if(this.listOfCommand.get(this.listOfCommand.size()-1).getisFourBytes()) {
					lineType = 2;
				}else {
					lineType = 1;
				}
			}else if(isDstDoll == true && isSrcDoll == false) {
				lineType = 3;
			}


			this.listOfCommand.get(this.listOfCommand.size()-1).setLineType(lineType);
		}else if(this.listOfCommand.get(this.listOfCommand.size()-1).getIsNop() == false) {
				boolean isDstDoll = false;
				int lineType = 0;

				char[] tokDst = dst.toCharArray();

				for(char c: tokDst) {
					if(c == '$') {
						isDstDoll = true;
					}
				}

				if(isDstDoll == false) {
					lineType = 0;
				}else if(isDstDoll == true) {
					lineType = 1;
				}

			this.listOfCommand.get(this.listOfCommand.size()-1).setXdst(dst, isDstDoll, lineType);
			if(isDstDoll == true && this.isNot == true) {
				//System.out.println("is Not method");
				this.listOfCommand.get(this.listOfCommand.size()-1).setNot("43", isDstDoll, true, dst, 0);
				///System.out.println("is Not method");
			}else if(isDstDoll == false && this.isNot == true) {
				this.listOfCommand.get(this.listOfCommand.size()-1).setNot("40", isDstDoll, true, dst, 3);
			}
		}
		//three types for single command Not
		// 1. Register
		// 2. [$MMMM]
	}

	public int getCommandType(String startOrgins) {
		char com = startOrgins.charAt(0);

		switch(com) {
		case'M':
		case 'm':
				this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Mov);
				this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(80);
			break;
		case'A':
		case 'a':
				if(startOrgins.equalsIgnoreCase("And")) {
					this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.And);
					this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(50);
				}else {
					this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Addc);
					this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(10);
				}
			break;
		case'S':
		case 's':
			this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Subb);
			this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(20);
			break;
		case'O':
		case 'o':
			this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Or);
			this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(60);
			break;
		case'X':
		case 'x':
			this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Xor);
			this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(70);
			break;
		case'C':
		case 'c':
			this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Cmp);
			this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(30);
			break;
		case'N':
		case 'n':
			if(startOrgins.equalsIgnoreCase("Not")) {
				this.isNot = true;
				this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.Not);
				this.listOfCommand.get(this.listOfCommand.size()-1).setStartIndex(40);
				//
			}else {
				this.listOfCommand.get(this.listOfCommand.size()-1).setCommandType(CommandType.NOP);
				this.listOfCommand.get(this.listOfCommand.size()-1).setOneStart("E0", null);
			}

			break;
		case'J':
		case 'j':


			switch(startOrgins.charAt(1)) {
			case 'l':
			case 'L':
				this.listOfCommand.get(this.listOfCommand.size()-1).setXJump("D6");


				break;
			case 'h':
			case 'H':
				this.listOfCommand.get(this.listOfCommand.size()-1).setXJump("D7");

				break;
			case 'e':
			case 'E':
				this.listOfCommand.get(this.listOfCommand.size()-1).setXJump("D8");

				break;
			case 'n':
			case 'N':
				this.listOfCommand.get(this.listOfCommand.size()-1).setXJump("D9");

				break;
			case 'm':
			case 'M':
				if(startOrgins.charAt(2) == 'i' || startOrgins.charAt(2) == 'I') {
					this.listOfCommand.get(this.listOfCommand.size()-1).setXJump("DA");
				}else { // regular jump
					this.listOfCommand.get(this.listOfCommand.size()-1).setOneStart("B8", "B9");
				}

				break;
			case 'p':
			case 'P':
				this.listOfCommand.get(this.listOfCommand.size()-1).setXJump("DB");
				break;
			}


			// jumps

			break;

		}


		return 0;

	}

	public ArrayList<Command> getListOfCommands(){
		return this.listOfCommand;
	}

	public void readFileOpCode(String FileName) {

		try {
			System.out.println("File is: " + FileName);
			Scanner scan = new Scanner(new File("Resources\\"+FileName));
			//scan.useDelimiter();
			while(scan.hasNextLine()) {

				this.listOfCommand.add(new Command()); // adding new Command
				String[] token = scan.nextLine().split(",| ");

				if(token.length == 3) {
					getCommandType(token[0]);
					getlineType(token[1], token[2]);
				}else if(token.length == 2) {
					// do logic for one register
					getCommandType(token[0]);
					getlineType(token[1],null);
				}else {
					getCommandType(token[0]);
				}
			}

			scan.close();
		// write to file.
			try {
				//Path file = Path.(new File("OpcodeOutput.txt"));
				PrintWriter out = new PrintWriter("Resources\\OpcodeOutput.txt"); // Resource//

				for(Command c: this.listOfCommand) {
					//System.out.println("In wrtie: " + c.toString());
					out.println(c.toString());
				}
				out.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// read out to file
	}



}
