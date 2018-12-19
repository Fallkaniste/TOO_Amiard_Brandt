package amiard_brandt._BCMS_UI.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import amiard_brandt._BCMS_UI.BCMS_UI;
//import com.FranckBarbier.Java._BCMS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PolicemanController implements Initializable {
	
	@FXML
    private Button dispatchButton;
    @FXML
    private Button undispatchButton;
    @FXML
    private Button bouton_valider;
    @FXML
    private TextField nbVehiculesTextField;
    @FXML
    private ListView<String> list_dispo;
    @FXML
    private ListView<String> dispatchedVehiculesListView;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
    	System.out.println("test") ;
		try 
		{
			ObservableList<String> list=FXCollections.observableArrayList ();
			
			for (String s : BCMS_UI.bCMS.get_police_vehicles()) 
			{
			    list.add(s);
			}
			System.out.println(list) ;
			list_dispo.setItems(list);
			list_dispo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
    
    
    
    
}
