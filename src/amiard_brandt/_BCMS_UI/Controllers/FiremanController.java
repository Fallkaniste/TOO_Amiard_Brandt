package amiard_brandt._BCMS_UI.Controllers;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.pauware.pauware_engine._Exception.Statechart_exception;

import amiard_brandt._BCMS_UI.BCMS_UI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class FiremanController implements Initializable {
	
    @FXML
    private ListView<String> availableVehicles;
    @FXML
    private ListView<String> dispatchedVehicles;
    @FXML
    private Label nbVehiclesLabel;
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		try 
		{		
			nbVehiclesLabel.setText(""+DescriptionController.nbNeeded);
			ObservableList<String> list=FXCollections.observableArrayList ();
			
			for (String s : BCMS_UI.bCMS.get_fire_trucks()) 
			{
			    list.add(s);
			}
			availableVehicles.setItems(list);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
 
    public void dispatchButton(ActionEvent event)
	{
		ObservableList<String> selectedItems = availableVehicles.getSelectionModel().getSelectedItems();
		dispatchedVehicles.getItems().addAll(selectedItems);
		availableVehicles.getItems().removeAll(selectedItems);
	}
    
    public void undispatchButton(ActionEvent event)
	{
		ObservableList<String> selectedItems = dispatchedVehicles.getSelectionModel().getSelectedItems();
		availableVehicles.getItems().addAll(selectedItems);
		dispatchedVehicles.getItems().removeAll(selectedItems);
	}
    
    public void validateButton(ActionEvent event)
	{
		try 
		{
			if (dispatchedVehicles.getItems().size()==DescriptionController.nbNeeded)
			{
				String show;
				for(int i=0; i<dispatchedVehicles.getItems().size(); i++)
				{
					BCMS_UI.bCMS.dispatch_fire_truck(dispatchedVehicles.getItems().get(i)); 
					show=dispatchedVehicles.getItems().get(i);
					System.out.println("	DISPATCHED: "+show);
					
					Parent root = FXMLLoader.load(getClass().getResource("../road.fxml"));
					 Scene policeman  = new Scene(root);
					 BCMS_UI.stage.setScene(policeman);
				}
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("You must dispatch the amount of vehicles you specified before");
                alert.showAndWait();
			}
			
		}
    	catch (SQLException |IOException e) 
    	{
    		e.printStackTrace();
		}
	}
}
