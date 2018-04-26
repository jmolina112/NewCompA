package model;

public class EPROM extends Sprite {

	private String[] memory = new String[0xffff];

	public EPROM(String string, int locationX, int locationY, int width, int height){
		super(string, locationX, locationY, width, height);
		memory[0xfffe-1] = "B9";
		memory[0xffff-1] = "00";
		this.setNameLabel("EPROM");
	}

	public String getMemoryLocation(int i){
		return memory[i];
	}
}