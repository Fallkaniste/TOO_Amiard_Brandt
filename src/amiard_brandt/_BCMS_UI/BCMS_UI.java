package amiard_brandt._BCMS_UI;


import java.io.IOException;

import com.FranckBarbier.Java._BCMS.BCMS;
import com.pauware.pauware_engine._Exception.Statechart_exception;

//import amiard_brandt._BCMS_UI.Utilities.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class BCMS_UI extends Application 
{
	public static BCMS bCMS;
	public static Stage stage;
	public static Scene homeScene;
	public static boolean type;
	//public static Service currentService;
	
	@Override
	 public void start(Stage stage) 
	 {	
		try 
		{
			bCMS = new BCMS();
			bCMS.start();
			
			BCMS_UI.type = true; 			
			//UI
			BCMS_UI.stage=stage;
			Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
			BCMS_UI.homeScene = new Scene(root);
			BCMS_UI.stage.setScene(homeScene);
			BCMS_UI.stage.setTitle("Barbados Crisis Management System");
			BCMS_UI.stage.show();
			BCMS_UI.stage.setResizable(false);
		}
		catch (Statechart_exception | IOException e) 
		{
			e.printStackTrace();	
		}

	
	 }

	public static void main(String args[]) 
	{	
		Application.launch();
    }
}


