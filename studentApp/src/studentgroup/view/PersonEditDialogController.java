package studentgroup.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studentgroup.model.Person;
import studentgroup.util.DateUtil;

/**
* Окно для изменения информации о студенте.
*/
public class PersonEditDialogController {
	 @FXML
	 private TextField firstNameField;
	 @FXML
	 private TextField lastNameField;
	 @FXML
	 private TextField groupCodeField;
	 @FXML
	 private TextField birthdayField;
	 @FXML
	 private TextField placeNumField;
	 @FXML
	 private TextField averageStoreField;
	 @FXML
	 private TextField cityOfLiveField;
	 
	 private Stage dialogStage;
	 private Person person;
	 private boolean okClicked = false;
	 
	 /**
	 * Инициализирует класс-контроллер. Этот метод вызывается автоматически
	 * после того, как fxml-файл будет загружен.
	 */
	 @FXML
	 private void initialize() {
	 }
	 
	 /**
	 * Устанавливает сцену для этого окна.
	 *
	 * @param dialogStage
	 */
	 public void setDialogStage(Stage dialogStage) {
		 this.dialogStage = dialogStage;
	 }
	 /**
	 * Задаёт адресата, информацию о котором будем менять.
	 *
	 * @param person
	 */
	 public void setPerson(Person person) {
		 this.person = person;
		 firstNameField.setText(person.getFirstName());
		 lastNameField.setText(person.getLastName());
		 groupCodeField.setText(Integer.toString(person.getGroupCode()));
		 birthdayField.setText(DateUtil.format(person.getBirthday()));
		 birthdayField.setPromptText("dd.mm.yyyy");
		 cityOfLiveField.setText(person.getCityName());
		 placeNumField.setText(Integer.toString(person.getPlaceNumber()));
		 averageStoreField.setText(Double.toString(person.getAverGrade()));
	 }
	 
	 /**
	 * Returns true, если пользователь кликнул OK, в другом случае false.
	 *
	 * @return
	 */
	 public boolean isOkClicked() {
		 return okClicked;
	 }
	 /**
	 * Вызывается, когда пользователь кликнул по кнопке OK.
	 */
	 @FXML
	 private void handleOk() {
		 if (isInputValid()) {
			 person.setFirstName(firstNameField.getText());
			 person.setLastName(lastNameField.getText());
			 person.setGroupCode(Integer.parseInt(groupCodeField.getText()));
			 person.setBirthday(DateUtil.parse(birthdayField.getText()));
			 person.setCityName(cityOfLiveField.getText());
			 person.setPlaceNumber(Integer.parseInt(placeNumField.getText()));
			 person.setAverGrade(Double.parseDouble(averageStoreField.getText()));
			 
			 okClicked = true;
			 dialogStage.close();
		 }
	 }
	 
	 /**
	 * Вызывается, когда пользователь кликнул по кнопке Cancel.
	 */
	 @FXML
	 private void handleCancel() {
		 dialogStage.close();
	 }
	 /**
	 * Проверяет пользовательский ввод в текстовых полях.
	 *
	 * @return true, если пользовательский ввод корректен
	 */
	 private boolean isInputValid() {
		 String errorMessage = "";
		 if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			 errorMessage += "No valid first name!\n";
		 }
		 if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			 errorMessage += "No valid last name!\n";
		 }
		 if (groupCodeField.getText() == null || groupCodeField.getText().length() == 0) {
			 errorMessage += "No valid postal code!\n";
		 } else {
		 // пытаемся преобразовать номер группы в int.
		 try {
			 Integer.parseInt(groupCodeField.getText());
		 } catch (NumberFormatException e) {
			 errorMessage += "No valid group code (must be an integer)!\n";
		 }
		 }
	 
		 ////////////////////////////////////////////////////////////////////
		 
		 ///////////////////////////////////////////////////////////////////
		 
		 if (cityOfLiveField.getText() == null || cityOfLiveField.getText().length() == 0) {
			 errorMessage += "No valid City Of Live!\n";
		 }
		 
		 if (placeNumField.getText() == null || placeNumField.getText().length() == 0) {
			 errorMessage += "No valid place in group!\n";
		 } else {
			 // пытаемся преобразовать номер группы в int.
			 try {
				 Integer.parseInt(placeNumField.getText());
			 } catch (NumberFormatException e) {
				 errorMessage += "No valid place in group (must be an integer)!\n";
			 }
		 }
		 
		 if (averageStoreField.getText() == null || averageStoreField.getText().length() == 0) {
			 errorMessage += "No valid Average Store!\n";
		 } else {
			 // пытаемся преобразовать номер группы в int.
			 try {
				 Double.parseDouble(averageStoreField.getText());
			 } catch (NumberFormatException e) {
				 errorMessage += "No valid Average Store (must be an double)!\n";
			 }
		 }
		 
		 /////////////////////////////////////////////////////////////////////
		
		 //////////////////////////////////////////////////////////////////////
		 
		 if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			 errorMessage += "No valid birthday!\n";
		 } else {
			 if (!DateUtil.validDate(birthdayField.getText())) {
				 errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			 }
		 }
		 if (errorMessage.length() == 0) {
			 return true;
		 } else {
			 // Показываем сообщение об ошибке.
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.initOwner(dialogStage);
			 alert.setTitle("Invalid Fields");
			 alert.setHeaderText("Please correct invalid fields");
			 alert.setContentText(errorMessage);
			
			 alert.showAndWait();
			
			 return false;
		 }
	 }
}