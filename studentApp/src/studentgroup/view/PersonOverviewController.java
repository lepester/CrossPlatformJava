package studentgroup.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import studentgroup.MainApp;
import studentgroup.model.Person;

import studentgroup.util.DateUtil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class PersonOverviewController {
	 @FXML
	 private TableView<Person> personTable;
	 @FXML
	 private TableColumn<Person, String> firstNameColumn;
	 @FXML
	 private TableColumn<Person, String> lastNameColumn;
	 @FXML
	 private Label firstNameLabel;
	 @FXML
	 private Label lastNameLabel;
	 @FXML
	 private Label groupCodeLabel;
	 @FXML
	 private Label birthdayLabel;
	 @FXML
	 private Label placeNumberLabel;
	 @FXML
	 private Label averageGradeLabel;
	 @FXML
	 private Label cityLabel;
	 
	 // Ссылка на главное приложение.
	 private MainApp mainApp;
	 /**
	 * Конструктор.
	 * Конструктор вызывается раньше метода initialize().
	 */
	 public PersonOverviewController() {
	 }
	 /**
	 * Инициализация класса-контроллера. Этот метод вызывается автоматически
	 * после того, как fxml-файл будет загружен.
	 */
	 /*@FXML
	 private void initialize() {
	 // Инициализация таблицы студентов с двумя столбцами.
	 firstNameColumn.setCellValueFactory(cellData ->
	cellData.getValue().firstNameProperty());
	 lastNameColumn.setCellValueFactory(cellData ->
	cellData.getValue().lastNameProperty());
	 }*/
	 
	 
	 @FXML
	 private void initialize() {
		 // Инициализация таблицы адресатов с двумя столбцами.
		 firstNameColumn.setCellValueFactory(
		 cellData -> cellData.getValue().firstNameProperty());
		 lastNameColumn.setCellValueFactory(
		 cellData -> cellData.getValue().lastNameProperty());
		 
		 // Очистка дополнительной информации о студенте.
		 showPersonDetails(null);
		 
		 // Слушаем изменения выбора, и при изменении отображаем
		 // дополнительную информацию о студенте.
		 personTable.getSelectionModel().selectedItemProperty().addListener(
		 (observable, oldValue, newValue) -> showPersonDetails(newValue));
	 }
	
	 
	 /**
	  * Вызывается, когда пользователь кликает по кнопке New...
	  * Открывает диалоговое окно с дополнительной информацией нового студента.
	  */
	  @FXML
	  private void handleNewPerson() {
		  Person tempPerson = new Person();
		  boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		  if (okClicked) {
			  mainApp.getPersonData().add(tempPerson);
		  }
	  }
	  
	  
	  /**
	  * Вызывается, когда пользователь кликает по кнопка Edit...
	  * Открывает диалоговое окно для изменения выбранного студента.
	  */
	  @FXML
	  private void handleEditPerson() {
		  Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		  if (selectedPerson != null) {
			  boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
		  if (okClicked) {
			  showPersonDetails(selectedPerson);
		  }
		  } else {
			  // Ничего не выбрано.
			  Alert alert = new Alert(AlertType.WARNING);
			  alert.initOwner(mainApp.getPrimaryStage());
			  alert.setTitle("No Selection");
			  alert.setHeaderText("No Person Selected");
			  alert.setContentText("Please select a person in the table.");
			  alert.showAndWait();
		  }
	  }
	
	 
	 /**
	 * Вызывается главным приложением, которое даёт на себя ссылку.
	 *
	 * @param mainApp
	 */
	 public void setMainApp(MainApp mainApp) {
		 this.mainApp = mainApp;
		 
		 // Добавление в таблицу данных из наблюдаемого списка
		 personTable.setItems(mainApp.getPersonData());
	 }
	 
	 
	 /**
	  * Заполняет все текстовые поля, отображая подробности о студенте.
	  * Если указанный студент = null, то все текстовые поля очищаются.
	  *
	  * @param person — студент типа Person или null
	  */
	  private void showPersonDetails(Person person) {
		  if (person != null) {
			  // Заполняем метки информацией из объекта person.
			  firstNameLabel.setText(person.getFirstName());
			  lastNameLabel.setText(person.getLastName());
			  groupCodeLabel.setText(Integer.toString(person.getGroupCode()));
			  // TODO: Нам нужен способ для перевода дня рождения в тип String!
			  // birthdayLabel.setText(...);
			  
			  birthdayLabel.setText(DateUtil.format(person.getBirthday()));
			  placeNumberLabel.setText(Integer.toString(person.getPlaceNumber()));
			  averageGradeLabel.setText(Double.toString(person.getAverGrade()));;
			  cityLabel.setText(person.getCityName());;

		  } else {
			  // Если Person = null, то убираем весь текст.
			  firstNameLabel.setText("");
			  lastNameLabel.setText("");
			  groupCodeLabel.setText("");
			  birthdayLabel.setText("");
			  placeNumberLabel.setText("");
			  averageGradeLabel.setText("");
			  cityLabel.setText("");
		  }
	  }

	   @FXML
	   private void handleDeletePerson() {
		   int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		   if (selectedIndex >= 0) {
			   Alert alertInfo = new Alert(AlertType.INFORMATION);
			   alertInfo.initOwner(mainApp.getPrimaryStage());
			   alertInfo.setTitle("Delete student");
			   alertInfo.setHeaderText("You delete this student");
			   alertInfo.setContentText("Are you sure?");
			   
			   Optional<ButtonType> result = alertInfo.showAndWait();
			    if (result.isPresent() &&  result.get()  == ButtonType.OK){
			        System.out.println("Ok button is pressed");
			    } else{
			        System.out.println("Cancel button was pressed");
			    }
			   
			   if(result.get()== ButtonType.OK) {
				   personTable.getItems().remove(selectedIndex);
			   }
			   else if(result.get()==null || result.get() == ButtonType.CANCEL) {
					Alert alertno = new Alert(AlertType.WARNING);
					alertno.initOwner(mainApp.getPrimaryStage());
					alertno.setTitle("No Selection");
					alertno.setHeaderText("No Person Selected");
					alertno.setContentText("Please select a person in the table.");
					alertno.showAndWait();
			   }
		   } 
		   else {
			   // Ничего не выбрано.
			   Alert alert = new Alert(AlertType.WARNING);
			   alert.initOwner(mainApp.getPrimaryStage());
			   alert.setTitle("No Selection");
			   alert.setHeaderText("No Person Selected");
			   alert.setContentText("Please select a person in the table.");
			   alert.showAndWait();
		   }
	   }
}

