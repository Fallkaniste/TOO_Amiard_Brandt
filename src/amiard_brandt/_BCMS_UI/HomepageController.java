package amiard_brandt._BCMS_UI;

import java.io.IOException;

import com.pauware.pauware_engine._Exception.Statechart_exception;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class HomepageController
{
	@FXML
    private Button PButton;
    @FXML
    private Button FButton;    
    
    @FXML
    private void firemanButton(ActionEvent event) {  
        try {
				BCMS_UI.bCMS.FSC_connection_request();
				Parent root = FXMLLoader.load(getClass().getResource("description.fxml"));
				Scene fireman  = new Scene(root);
				BCMS_UI.stage.setScene(fireman);
				  //accueil.setVisible(false);
        }		
		catch (Statechart_exception | IOException e ) 
			{
				e.printStackTrace();
			}
    }
    
    @FXML
    private void policemanButton(ActionEvent event) {  
        try {

				BCMS_UI.bCMS.PSC_connection_request();
				Parent root = FXMLLoader.load(getClass().getResource("description.fxml"));
				Scene description  = new Scene(root);
				BCMS_UI.stage.setScene(description);
        }		
		catch (Statechart_exception | IOException e) 
			{
				e.printStackTrace();
			}
    }
   
}
