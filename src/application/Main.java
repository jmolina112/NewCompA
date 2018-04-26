package application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import controller.IntroCon;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.OpCode;
import model.SystemModel;



public class Main extends Application {

	//stages for use in program
	/**Stage for display window*/
	public static Stage displayStage;
	/**Stage for pop-up windows*/
	public static Stage popupStage = new Stage();

	/**Scene for Intro view*/
	public static Scene intro;
	/**Scene for Main Emulator view*/
	public static Scene main;
	/**Scene for Popup Menu view*/
	public static Scene popupMenu;
	/**Scene for Game Options view*/
	public static Scene options;

	/**Initialize program at start. Creates all scenes and sets the main stage to the introView.*/
	public void init(){
		//create the scenes for views
		try {
			Parent introView = FXMLLoader.load(getClass().getResource("/view/IntroView.fxml"));
			intro = new Scene(introView, 640, 480);
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
			main = new Scene(mainView, 1280, 800);
			Parent popupView = FXMLLoader.load(getClass().getResource("/view/PopupView.fxml"));
			popupMenu = new Scene(popupView, 200, 260);
			Parent optionView = FXMLLoader.load(getClass().getResource("/view/OptionView.fxml"));
			options = new Scene(optionView, 640, 480);
			
			
		} catch (IOException e) {
			//if any scene fails, exit with error trace
			e.printStackTrace();
		}

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			//load and set up the pop up stage
			popupStage.setScene(Main.popupMenu);
			popupStage.initStyle(StageStyle.TRANSPARENT);
			popupStage.setAlwaysOnTop(true);

			//load stage with intro scene
			Main.displayStage = primaryStage;
			this.displayStage.setScene(intro);
		} catch(Exception e) {
			//if a stage fails, exit with error trace
			e.printStackTrace();
		}

		//create the OnShown handler for intro scene
		this.displayStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent window) {
		        Platform.runLater(new Runnable() {
		            @Override
		            public void run() {
		                IntroCon.handleWindowShownEvent();
		            }
		        });
		    }
		});

		//display intro
		this.displayStage.show();
	}

	/**Cleans up program before exiting. Currently unused*/
	public void stop(){

	}

	public static void main(String[] args) {
		
		//launch the program
		//SystemModel d = new SystemModel();
		launch(args);
	}

	/**Gets the primary stage.
	 *
	 * @return stage	the main window for the program
	 */
	public static Stage getStage() {
		return displayStage;
	}
}




