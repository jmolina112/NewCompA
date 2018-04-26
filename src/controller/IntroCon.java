package controller;

import javafx.stage.WindowEvent;

import java.util.concurrent.TimeUnit;

import application.Main;
import model.SystemModel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**Used for the program intro and initialization on starting program. Calls main view.
 *
 * @author Gregory M Pugh
 */
public class IntroCon implements EventHandler<WindowEvent>{

	@FXML
	private Label loadLabel;

	private String loadText[] = {"loading", "loading.", "loading..", "loading..."};

	private SystemModel system;

	/**Setups the initial intro view when program loads.
	 * Sets the progress bar to 0.*/
	public void initialize(){
		this.system = new SystemModel();
	}

	public static void handleWindowShownEvent() {
		for(int i = 0; i < 3; i++){
			try{
				TimeUnit.MILLISECONDS.sleep(300);
				System.out.print(i + "\n");
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		Main.displayStage.setScene(Main.main);

	}

	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
