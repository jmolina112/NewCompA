package model;

import javafx.scene.canvas.GraphicsContext;

public class ProgramLogic {
	private GraphicsContext gDraw;
	private SystemModel sys;
	private int comIndex = 0;
	private int index = 0;
	public ProgramLogic(GraphicsContext g, SystemModel sm) {
		this.gDraw = g;
		this.sys = sm;
		
	}

	
	public int getCurrentCommanIndex() {
		return this.comIndex;
	}
	
	public void update(){
		
		logicUpdate();
		for(int index = 0; index < this.sys.getComponent().size(); index++) {
			this.gDraw.drawImage(this.sys.getComponent().get(index).getCurrentImage(), this.sys.getComponent().get(index).getLoc().x, this.sys.getComponent().get(index).getLoc().y, this.sys.getComponent().get(index).getSize().x, this.sys.getComponent().get(index).getSize().y);
			this.gDraw.strokeText(this.sys.getComponent().get(index).getName(), this.sys.getComponent().get(index).getLabelTextLoc().x, this.sys.getComponent().get(index).getLabelTextLoc().y);
			this.gDraw.strokeText(this.sys.getComponent().get(index).getNameLabel(), this.sys.getComponent().get(index).getNameTextLocation().x, this.sys.getComponent().get(index).getNameTextLocation().y);
		}
	}
	
	public void logicUpdate() {
				
		String[] token = this.sys.getCpuModel().getComponenetList().get(comIndex).split(" ");
		if(this.index >= token.length) {
			comIndex++;
			this.index = 0;
			for(int index = 0; index < this.sys.getComponent().size(); index++) {
				if(this.sys.getComponent().get(index).isOn() == true) {
					this.sys.getComponent().get(index).toggleImage();
				}
			}
		}

		for(int i = 0; i < this.sys.getComponent().size(); i++) {
			if(token[this.index].equals(this.sys.getComponent().get(i).getName())) {
				this.sys.getComponent().get(i).toggleImage();
			}
				
		}
			
		this.index++;
			
	
	}
}
