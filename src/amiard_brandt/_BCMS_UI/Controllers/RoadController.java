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
import java.text.SimpleDateFormat;
import java.util.*;

import com.FranckBarbier.Java._BCMS.BCMS;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import javafx.scene.control.Label;

import amiard_brandt._BCMS_UI.BCMS_UI;
import amiard_brandt._BCMS_UI.HomepageController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class RoadController implements Initializable {
	
	public static int nbNeeded;
	static int interval;	
	
			//active FXML
	 @FXML
	 private Button confirmButton;
	 
	 @FXML
	 private Button yesButton;
	 
	 @FXML
	 private Button noButton;
	 
	 @FXML
	 private Label kmLabel;
	
	 @FXML
	 private Label duraLabel;
	 
	 @FXML
	 private Label secLabel;
	 
	 @FXML
	 private Label minLabel;
	 
	 @FXML
	 private ChoiceBox<String> dispatchedVehicles;
	 
	 @FXML
	 private ChoiceBox<String> statusVehicles;
	 
	 
	 		//to display stuff
	 @FXML
	 private Label roadLabel;
	 
	 @FXML
	 private Label textLabel1;
	 @FXML
	 private Label textLabel2;
	 @FXML
	 private Label textLabel3;
	 @FXML
	 private Label textLabel4;
	 
	 @FXML
	 public void confirmButton(ActionEvent event)
	 {
		// String test = statusVehicles.getValue();
		 
		 /*try {		
			 String nbneeded = availableVehicles.getValue().toString();
			 nbNeeded = Integer.parseInt(nbneeded);
			 if(BCMS_UI.type)
			 {
				 Parent root = FXMLLoader.load(getClass().getResource("../policeman.fxml"));
				 Scene policeman  = new Scene(root);
				 BCMS_UI.stage.setScene(policeman);
				 String nbpvehicles = availableVehicles.getValue().toString();
				 BCMS_UI.bCMS.state_police_vehicle_number(Integer.parseInt(nbpvehicles));
			 }
			 else
			 {
				 Parent root = FXMLLoader.load(getClass().getResource("../fireman.fxml"));
				 Scene fireman  = new Scene(root);
				 BCMS_UI.stage.setScene(fireman);
				 String nbfvehicles = availableVehicles.getValue().toString();
				 BCMS_UI.bCMS.state_fire_truck_number(Integer.parseInt(nbfvehicles));
			 }
		 }
		 catch (Statechart_exception | IOException e)
		 {
			 e.printStackTrace();
		 }*/
	 }
	
	 public Timer timer;
	 
	 public int minRemain ;
	 public int secRemain ;
	 
	 public void updateSec(long sec)
	 {
		 secLabel.setText(""+secRemain);
	 }
	 
	 public void updateMin(long min)
	 {
		 minLabel.setText(""+minRemain);
		 }
	 
	 public void platform() {
		 Platform.runLater(new Runnable() {
 		    @Override
 		    public void run() {
 		    	updateSec(secRemain);
	    			updateMin(minRemain);
 		    }
 		});
	 }
	 
	 public void yesButton(ActionEvent event)
	 {
		 yesButton.setDisable(true);
		 noButton.setDisable(true);
		 
		 textLabel1.setVisible(true);
		 textLabel2.setVisible(true);
		 textLabel3.setVisible(true);
		 textLabel4.setVisible(true);
		 secLabel.setVisible(true);
		 minLabel.setVisible(true);
		 dispatchedVehicles.setVisible(true);
		 statusVehicles.setVisible(true);
		 confirmButton.setVisible(true);
		 
		 minRemain = Integer.parseInt(duraLabel.getText());
		 secRemain= 0 ;

		  timer = new Timer();   	
	    	timer.schedule(new TimerTask(){		
	    		@Override
	    		public void run() {
	    			platform();
	    			 
	    			if (secRemain==0)
	    			{
	    				secRemain=59;
	    				minRemain--;
	    			}
	    			else
	    				secRemain--;
	    			if(secRemain==0 && minRemain == 0)
	    			{
	    				timer.cancel();
	    				
	    				//TODO C FINI YEAS
	    			}				    	    			
	    			}	    		
	    		}, new Date(), 1000);        	
	 }
	
	 @FXML
	 public void noButton(ActionEvent event)
	 {	
		 roadLabel.setText("The new road is ");
		 int kilometers = (int)(Math.random() * 55 + 1);
	    	int minutes;
	    	int a = (int)(Math.random() * 5 + 1);
	    	int b = (int)(Math.random() * 2 + 1);
	    	if (b==1)
	    	{
	    		minutes = kilometers + a ;
	    	}
	    	else
	    	{
	    		minutes = kilometers - a ;
	    	}
	    	if (minutes <0)
	    	{
	    		minutes=1;
	    	}
	    	kmLabel.setText(""+kilometers);
	    	duraLabel.setText(""+minutes);	    
	 }
	
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	textLabel1.setVisible(false);
		 textLabel2.setVisible(false);
		 textLabel3.setVisible(false);
		 textLabel4.setVisible(false);
		 secLabel.setVisible(false);
		 minLabel.setVisible(false);
		 dispatchedVehicles.setVisible(false);
		 statusVehicles.setVisible(false);
		 confirmButton.setVisible(false);
      	
    	dispatchedVehicles.setItems(PolicemanController.selectedVehicles);
    	dispatchedVehicles.setValue(PolicemanController.selectedVehicles.get(0));
    	
    	statusVehicles.setItems(FXCollections.observableArrayList("Arrived", "Blocked", "Breakdown"));
    	statusVehicles.setValue("Arrived");
    	
    	int kilometers = (int)(Math.random() * 55 + 1);
    	int minutes;
    	int a = (int)(Math.random() * 5 + 1);
    	int b = (int)(Math.random() * 2 + 1);
    	if (b==1)
    	{
    		minutes = kilometers + a ;
    	}
    	else
    	{
    		minutes = kilometers - a ;
    	}
    	kmLabel.setText(""+kilometers);
    	duraLabel.setText(""+minutes);   	
  
	}
    
    public void setLabel(String test)
    {
    	kmLabel.setText(test);
    }

	


}
