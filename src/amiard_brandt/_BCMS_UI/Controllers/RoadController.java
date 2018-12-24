package amiard_brandt._BCMS_UI.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import javafx.scene.control.Label;
import amiard_brandt._BCMS_UI.BCMS_UI;
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
	 private Label textLabel5;
	 @FXML
	 private Label textLabel6;
	 @FXML
	 private Separator toremove;
	 @FXML
	 private Separator toremove2;
	 @FXML
	 public void confirmButton(ActionEvent event)
	 {
		 try {	 	
			 	String newStatus = statusVehicles.getValue();
				String selectedVehicle = dispatchedVehicles.getValue();
				if(newStatus=="Arrived")
				{
					if(BCMS_UI.type)
					{
						BCMS_UI.bCMS.police_vehicle_arrived(selectedVehicle);		
						
					}
					else
					{
						BCMS_UI.bCMS.fire_truck_arrived(selectedVehicle);
					}
					System.out.println("	ARRIVED: "+selectedVehicle);
					newVehicles.remove(selectedVehicle);
					dispatchedVehicles.setItems(newVehicles);
				}
				else if (newStatus=="Blocked")
				{
					if(BCMS_UI.type)
					{
						BCMS_UI.bCMS.police_vehicle_blocked(selectedVehicle);
					}
					else
					{
						BCMS_UI.bCMS.fire_truck_blocked(selectedVehicle);
					}
					System.out.println("	BLOCKED: "+selectedVehicle);
						
				}
				else if(newStatus=="ReDispatched")
				{
					if(BCMS_UI.type)
					{
						BCMS_UI.bCMS.police_vehicle_dispatched(selectedVehicle);						
					}
					else
					{
						BCMS_UI.bCMS.fire_truck_dispatched(selectedVehicle);
					}
					System.out.println("	DISPATCHED: "+selectedVehicle);
				}
				
				if (newVehicles.isEmpty())
				{
					Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Alert over");
	                alert.setHeaderText("All units and vehicles arrived, the alert is over");
	                alert.showAndWait();
	                timer.cancel();
	                try {
	                	BCMS_UI.bCMS.close();
	                    
	                    BCMS_UI.bCMS.stop();

					} catch (Statechart_exception e) {
						e.printStackTrace();
					}
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("../homepage.fxml"));
						Scene backToStart = new Scene(root);
		    			BCMS_UI.stage.setScene(backToStart);
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			
				}
		 }
		 catch (Statechart_exception e) 
			{
				e.printStackTrace();	
			}
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
	 
	 public ObservableList<String> newVehicles = FXCollections.observableArrayList();
	 
	 public void yesButton(ActionEvent event)
	 {
		 
		 try {
	    		if (BCMS_UI.type)
		    	{
	    			BCMS_UI.bCMS.FSC_agrees_about_police_vehicle_route();
	    			System.out.println("	ROUTE ACCEPTED");
		    	}
	    		else
	    		{
	    			BCMS_UI.bCMS.FSC_agrees_about_fire_truck_route();
	    			System.out.println("	ROUTE ACCEPTED");
	    		}  			
	    	}
	    	catch (Statechart_exception e ) 
			{
				e.printStackTrace();
			}
		 
		 yesButton.setDisable(true);
		 noButton.setDisable(true);
		 
		 textLabel1.setVisible(true);
		 textLabel2.setVisible(true);
		 textLabel3.setVisible(true);
		 textLabel4.setVisible(true);
		 textLabel5.setVisible(true);
		 textLabel6.setVisible(true);
		 secLabel.setVisible(true);
		 minLabel.setVisible(true);
		 dispatchedVehicles.setVisible(true);
		 statusVehicles.setVisible(true);
		 confirmButton.setVisible(true);
		 toremove.setVisible(true);
		 toremove2.setVisible(true);
		 
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
	    				try {
	    					timer.cancel();
		    				String show;
		    				for(int i=0; i<dispatchedVehicles.getItems().size(); i++)
		    				{					

		    					
		    					try {
		    						if (BCMS_UI.type)
		    						{
		    							BCMS_UI.bCMS.police_vehicle_arrived(dispatchedVehicles.getItems().get(i));	 
		    						}
		    						else
		    						{
		    							BCMS_UI.bCMS.fire_truck_arrived(dispatchedVehicles.getItems().get(i));
		    						}
								} catch (Statechart_exception e) {
									e.printStackTrace();
								}
		    					show=dispatchedVehicles.getItems().get(i);
		    					System.out.println("	ARRIVED: "+show);
		    					

		    					 Parent root = FXMLLoader.load(getClass().getResource("../road.fxml"));
		    					 Scene policeman  = new Scene(root);
		    					 BCMS_UI.stage.setScene(policeman);
		    				}
		    				
		    				Alert alert = new Alert(AlertType.INFORMATION);
			                alert.setTitle("Alert over");
			                alert.setHeaderText("All units and vehicles arrived, the alert is over");
			                alert.showAndWait();
			                	
			                
			                try {
			                	BCMS_UI.bCMS.close();
			                    
			                    BCMS_UI.bCMS.stop();

							} catch (Statechart_exception e) {
								e.printStackTrace();
							}
								Parent root;
								root = FXMLLoader.load(getClass().getResource("../homepage.fxml"));
								Scene backToStart = new Scene(root);
				    			BCMS_UI.stage.setScene(backToStart);
	    				}
	    				catch ( IOException e) 
	    				{
	    					e.printStackTrace();	
	    				}			

	    			}				    	    			
	    		  }	    		
	    		}, new Date(), 1000);     
	    	
		 	
		 	for(int i = 0; i<PolicemanController.selectedVehicles.size();i++)
		 	{
		 		newVehicles.add(PolicemanController.selectedVehicles.get(i));
		 	}
		 	
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
	    	try {
	    		if (BCMS_UI.type)
		    	{
	    			BCMS_UI.bCMS.FSC_disagrees_about_police_vehicle_route();
	    			System.out.println("	ROUTE DENIED");
		    		BCMS_UI.bCMS.route_for_police_vehicles();
		    	}
	    		else
	    		{
	    			BCMS_UI.bCMS.FSC_disagrees_about_fire_truck_route();
	    			System.out.println("	ROUTE DENIED");
	    			BCMS_UI.bCMS.route_for_fire_trucks();
	    		}  			
	    	}
	    	catch (Statechart_exception e ) 
			{
				e.printStackTrace();
			}
	 }
	
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	textLabel1.setVisible(false);
		 textLabel2.setVisible(false);
		 textLabel3.setVisible(false);
		 textLabel4.setVisible(false);
		 textLabel5.setVisible(false);
		 textLabel6.setVisible(false);
		 secLabel.setVisible(false);
		 minLabel.setVisible(false);
		 dispatchedVehicles.setVisible(false);
		 statusVehicles.setVisible(false);
		 confirmButton.setVisible(false);
		 toremove.setVisible(false);
		 toremove2.setVisible(false);
      	
    	dispatchedVehicles.setItems(PolicemanController.selectedVehicles);
    	dispatchedVehicles.setValue(PolicemanController.selectedVehicles.get(0));
    	
    	statusVehicles.setItems(FXCollections.observableArrayList("Arrived", "Blocked", "ReDispatched"));
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
    	
    	try {
    		if (BCMS_UI.type)
	    	{
	    		BCMS_UI.bCMS.route_for_police_vehicles();
	    	}
    		else
    		{
    			BCMS_UI.bCMS.route_for_fire_trucks();
    		}  			
    	}
    	catch (Statechart_exception e ) 
		{
			e.printStackTrace();
		}
  
	}
    
    public void setLabel(String test)
    {
    	kmLabel.setText(test);
    }

	


}
