package Controller;

import Database.DB_JobOverviewHandler;
import Model.Day;
import Model.Job;
import Model.Session;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    TableColumn saturdayColumn, sundayColumn, totalPayColumn;

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
        totalPayColumn.setCellValueFactory(new PropertyValueFactory<Day,Double>("totalPay"));

        ObservableList<Day> data = jobOverviewHandler.getDays(Session.getCurrentJob().getJobId());
        tableView.setItems(data);
    }
}
