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

public class PolicemanController implements Initializable {
	
	@FXML
    private Button dispatchButton;
    @FXML
    private Button undispatchButton;
    @FXML
    private Button validateButton;
    @FXML
    private TextField nbVehiculesTextField;
    @FXML
    private ListView<String> availableVehiculesListView;
    @FXML
    private ListView<String> dispatchedVehiculesListView;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
    	System.out.println("test") ;
		try 
		{
			ObservableList<String> list= FXCollections.observableArrayList();
			
			for (String s : BCMS_UI.bCMS.get_police_vehicles()) 
			{
			    list.add(s);
			}
			System.out.println(list) ;
			availableVehiculesListView.setItems(list);
			availableVehiculesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
 
    public void dispatchButton(ActionEvent event)
	{
		ObservableList<String> selectedItems = availableVehiculesListView.getSelectionModel().getSelectedItems();
	
		dispatchedVehiculesListView.getItems().addAll(selectedItems);

		availableVehiculesListView.getItems().removeAll(selectedItems);

	}
    
    public void undispatchButton(ActionEvent event)
	{
		ObservableList<String> selectedItems2 = dispatchedVehiculesListView.getSelectionModel().getSelectedItems();
	
		availableVehiculesListView.getItems().addAll(selectedItems2);

		dispatchedVehiculesListView.getItems().removeAll(selectedItems2);
	}
}
