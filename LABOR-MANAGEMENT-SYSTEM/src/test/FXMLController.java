/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLController implements Initializable {
    @FXML
    private Button bt_adddata;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_address;
    @FXML
    private TextField txt_status;
    @FXML
    private TextField txt_weight;
    @FXML
    private TextField txt_height;
    @FXML
    private TextField txt_phone;
    
    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet rs = null;
    private ObservableList<Worker> data;
    
//    load data
    @FXML
    private TableView<Worker> tableProduct;
    @FXML
    private TableColumn<?,?> column_name ;
    @FXML
    private TableColumn<?,?> column_address ;
     @FXML
    private TableColumn<?,?> column_status ;
    @FXML
    private TableColumn<?,?> column_weight ;
     @FXML
    private TableColumn<?,?> column_height ;
    @FXML
    private TableColumn<?,?> column_phone ;
     
    //TextFieldValidation
    @FXML
    private Label error_name;
    @FXML
    private Label error_address;
    @FXML
    private Label error_status;
    @FXML
    private Label error_weight;
    @FXML
    private Label error_height;
    @FXML
    private Label error_phone;
    
    @FXML
    private void action_adddata(ActionEvent event) throws SQLException {
        boolean isNameEmpty = test.TextFieldValidation.isTextFieldNotEmpty(txt_name,error_name,"name is require");
        boolean isAddressEmpty = test.TextFieldValidation.isTextFieldNotEmpty(txt_address,error_address,"address is require");
        boolean isStatusEmpty = test.TextFieldValidation.isTextFieldNotEmpty(txt_status,error_status,"status is require");
        
       // boolean isPhoneEmpty = test.TextFieldValidation.isTextFieldNotEmpty(txt_phone,error_phone,"phone is requime");
        
        boolean isWeightEmpty = test.TextFieldValidation.istextFieldTypeNumber(txt_weight,error_weight,"weight must be number");
        boolean isHeightEmpty = test.TextFieldValidation.istextFieldTypeNumber(txt_height,error_height,"height must be number");
        boolean isPhoneEmpty = test.TextFieldValidation.istextFieldTypeNumber(txt_phone,error_phone,"phone must be number");
        
      
        if(isNameEmpty&&isAddressEmpty&&isStatusEmpty&&isWeightEmpty&&isHeightEmpty&&isPhoneEmpty  )
        {
        String sql = "INSERT INTO WORKER(db_name,db_address,db_status,db_weight,db_height,db_phone) VALUES(?,?,?,?,?,?)";
        String name = txt_name.getText();
        String address = txt_address.getText();
        String status = txt_status.getText();
        double weight = Double.valueOf(txt_weight.getText());
        double height = Double.valueOf(txt_height.getText());
        String phone = txt_phone.getText();
        try {
            sta = con.prepareStatement(sql);
            sta.setString(1, name);
            sta.setString(2, address);
            sta.setString(3, status);
            sta.setDouble(4, weight);
            sta.setDouble(5, height);
            sta.setString(6, phone);
            int i = sta.executeUpdate();
            if(i==1)
             {
             //System.out.println("data cap nhat thanh cong");
               AlertDialog.display("Info", "Successful data update");
               setCellTable();
               loadData();
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            sta.close();
        }
    }}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = test.DBconnection.getConnect();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadData();
    }    
    private void setCellTable()
    {
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        column_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        column_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        column_height.setCellValueFactory(new PropertyValueFactory<>("height"));
        column_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    private void loadData()
    {
        data.clear();
        try {
            sta = con.prepareStatement("select * from WORKER ");
            rs = sta.executeQuery();
            while(rs.next())
            {
                data.add(new Worker(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6)));
            }
        }       
         catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableProduct.setItems(data);
    }
   
    
}
