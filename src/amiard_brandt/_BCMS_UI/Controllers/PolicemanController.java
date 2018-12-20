package amiard_brandt._BCMS_UI.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private ListView<String> availableVehiclesListView;
    @FXML
    private ListView<String> dispatchedVehiclesListView;

    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	System.out.println("ajout") ;
		try
		{
			ObservableList<String> list=FXCollections.observableArrayList ();
			for (String s : BCMS_UI.bCMS.get_police_vehicles())
			{
			    list.add(s);
			}
			availableVehiclesListView.setItems(list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

    public void dispatchButton(ActionEvent event)
	{
		ObservableList<String> selectedItems = availableVehiclesListView.getSelectionModel().getSelectedItems();
		dispatchedVehiclesListView.getItems().addAll(selectedItems);
		availableVehiclesListView.getItems().removeAll(selectedItems);
	}

    public void undispatchButton(ActionEvent event)
	{
		ObservableList<String> selectedItems = dispatchedVehiclesListView.getSelectionModel().getSelectedItems();
		availableVehiclesListView.getItems().addAll(selectedItems);
		dispatchedVehiclesListView.getItems().removeAll(selectedItems);
	}

    public void validateButton(ActionEvent event)
	{
    	System.out.println("test");

		try
		{
			for(int i=0; i<dispatchedVehiclesListView.getItems().size(); i++)
			{
				BCMS_UI.bCMS.dispatch_police_vehicle(dispatchedVehiclesListView.getItems().get(i));
			}

			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("../policeman_control.fxml"));
			Scene policeman_control = new Scene(root);
			BCMS_UI.stage.setScene(policeman_control);
		}
    	catch (SQLException | IOException e)
    	{
    		e.printStackTrace();
		}
	}
}
