package model;

public class RAM extends Sprite {

	private String[] memory = new String[0xfff];

	public RAM(String string, int locationX, int locationY, int width, int height){
		super(string, locationX, locationY, width, height);
		this.setNameLabel("RAM");
	}
	public String[] getFullMemeory() {
		return this.memory;
	}
	public String getMemoryLocation(int i){
		return memory[i];
	}

	
	public void setMemoryLoaction(int i, String value){
		memory[i] = value;
	}
}

