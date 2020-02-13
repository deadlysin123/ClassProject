package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;




    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue < ? extends Worker> ov, Worker old_val, Worker new_val)->
                {
                    //this method print out values of selected item from the listview to the textfields as well as the checkbox
                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();

                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                }
                );

        //Generate some dummy employees for the listview with the for loop and some generic staff/faculty
        ObservableList<Employee> items = employeeListView.getItems();
        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";

        items.add(staff1);
        items.add(faculty1);

    }

    //Add new employee based on user inputs
    public void add(ActionEvent actionEvent) {
        ObservableList<Employee> empInfo = employeeListView.getItems();
        Employee newEmployee = new Employee();
        newEmployee.firstName = firstNameTextField.getText();
        newEmployee.lastName = lastNameTextField.getText();

        //check to see if the check box is selected then add that value to the new employee information if it is
        if(isActiveCheckBox.isSelected())
        {
            newEmployee.hire();
        }

        //add the new employee information into the list empInfo then set that list into the employee listview
        empInfo.add(newEmployee);
        employeeListView.setItems(empInfo);
    }

    //Delete the selected employee
    public void delete(ActionEvent actionEvent) {

        //remove the selected employee from the listview
        Worker employee = employeeListView.getSelectionModel().getSelectedItem();
        employeeListView.getItems().remove(employee);

    }

    //Clear text fields and check boxes
    public void clear(ActionEvent actionEvent) {

        firstNameTextField.clear();
        lastNameTextField.clear();
        isActiveCheckBox.setSelected(false);
    }

}
