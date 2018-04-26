package application.model;

import application.controller.MainCon;

import java.util.ArrayList;

public class Runtime {

	private SystemModel system = MainCon.getSystem();
	private ArrayList<String> operationList = new ArrayList<String>();
	private ArrayList<Sprite> componentList = system.getComponentList();
	private CPUModel CPU;

	public void doRuntime(){
		
		int i = 0;
		
		//before starting loop, set instruction pointer for CPU
		for(Sprite component : componentList){
			if(component.getName().equals("U500")){
				CPU = (CPUModel)component;
			}
			CPU.setInstPointer(0xfffe);
			//CPU.setImage(1);
			
			while(CPU.readOpcode() != null){
				
				//read memory location
				this.operationList = CPU.doOperation(CPU.readOpcode());
		
				System.out.println("Whats inside "+this.operationList.get(0));
				for(i = 0; i < this.operationList.size();i++){

				}
					
			}
	
		}
	
	}//end of doRuntime
}
