package Controller;

import Database.DB_JobOverviewHandler;
import Model.Day;
import Model.Job;
import Model.Session;
import View.CurrentStage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 04/07/2016.
 */
public class Controller_JobOverview implements Initializable
{
    private DB_JobOverviewHandler jobOverviewHandler = new DB_JobOverviewHandler();

    @FXML
    TableView<Day> tableView;

    @FXML
    TableColumn dayColumn, dateColumn, totalHoursColumn, regularHoursColumn, firstOverColumn, secondOverColumn;

    @FXML
    TableColumn saturdayColumn, sundayColumn, afterTaxColumn, totalPayColumn;

    @FXML
    Button backBtn, backBtnLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        dayColumn.setCellValueFactory(new PropertyValueFactory<Day,String>("dayName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Day,String>("dayDate"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<Day,Integer>("hoursTotal"));
        regularHoursColumn.setCellValueFactory(new PropertyValueFactory<Day,Integer>("hoursReg"));
        firstOverColumn.setCellValueFactory(new PropertyValueFactory<Day,Integer>("firstOver"));
        secondOverColumn.setCellValueFactory(new PropertyValueFactory<Day,Integer>("secondOver"));
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<Day,Integer>("saturdayHour"));
        sundayColumn.setCellValueFactory(new PropertyValueFactory<Day,Integer>("sundayHour"));
        afterTaxColumn.setCellValueFactory(new PropertyValueFactory<Day, Double>("afterTax"));
        totalPayColumn.setCellValueFactory(new PropertyValueFactory<Day,Double>("totalPay"));

        ObservableList<Day> data = jobOverviewHandler.getDays(Session.getCurrentJob().getJobId());
        tableView.setItems(data);
    }

    public void changeToPreviousWindow()
    {
        Stage stage = new Stage();
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/View/FXMLJobPage_Window.fxml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

        CurrentStage.getCurrentStage().close();
        CurrentStage.setCurrentStage(stage);
        CurrentStage.showCurrentStage();
    }
}
