package amiard_brandt._BCMS_UI.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.pauware.pauware_engine._Exception.Statechart_exception;

import amiard_brandt._BCMS_UI.BCMS_UI;
import amiard_brandt._BCMS_UI.HomepageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class DescriptionController implements Initializable {

	@FXML
    private ChoiceBox<String> alertTypes;

	@FXML
    private ChoiceBox<String> availableVehicles;

	 @FXML
	 private Button confirmButton;

	 @FXML
	 public void confirmButton(ActionEvent event)
	 {
		 try {

			 Parent root = FXMLLoader.load(getClass().getResource("../fireman.fxml"));
			 Scene policeman  = new Scene(root);
			 BCMS_UI.stage.setScene(policeman);
			 
			 System.out.println(BCMS_UI.type) ;

			 /*if (HomepageController.a == 2)
			 {
				 System.out.println("test");
				 BCMS_UI.stage.close();

				 Parent root = FXMLLoader.load(getClass().getResource("policeman.fxml"));
				 Scene policeman  = new Scene(root);
				 BCMS_UI.stage.setScene(policeman);
				 String test = availableVehicles.getValue().toString();
				 BCMS_UI.bCMS.state_fire_truck_number(Integer.parseInt(test));
			 }
			 else
			 {
				 Parent root = FXMLLoader.load(getClass().getResource("fireman.fxml"));
				 Scene fireman  = new Scene(root);
				 BCMS_UI.stage.setScene(fireman);
			 }
			 String test = availableVehicles.getValue().toString();
			 BCMS_UI.bCMS.state_fire_truck_number(Integer.parseInt(test));*/
		 }
		 catch (IOException e)
		 {
			 e.printStackTrace();
		 }
	 }

	 @FXML
	 public void dispatchButton(ActionEvent event)
	 {

		 System.out.println("test") ;
	 }

    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	try  {
    		ObservableList<String> types = FXCollections.observableArrayList("arson", "robbery", "threat", "explosion", "hurricane","storm","terrosim","else");
        	alertTypes.setItems(types);
        	alertTypes.setValue("arson");

        	ObservableList<String> nbVehicles = FXCollections.observableArrayList();
        	for (int s= 1; s <=BCMS_UI.bCMS.get_police_vehicles().size() ; s++)
        	{

        		nbVehicles.add(String.valueOf(s));
        	}
        	availableVehicles.setItems(nbVehicles);
        	availableVehicles.setValue("1");
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
	}


}
