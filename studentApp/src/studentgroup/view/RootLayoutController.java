package studentgroup.view; 

import java.io.File;

import javafx.fxml.FXML; 
import javafx.scene.control.Alert; 
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import studentgroup.MainApp;
import studentgroup.model.Person;

/** 
* Контроллер для корневого макета. Корневой макет предоставляет базовый 
* макет приложения, содержащий строку меню и место, где будут размещены 
* остальные элементы JavaFX. 
*  
* @author Marco Jakob 
*/ 
public class RootLayoutController {
	@FXML
	private TableView<Person> personTable;

	 // Ссылка на главное приложение 
	 private MainApp mainApp; 
	 
	 /** 
	 * Вызывается главным приложением, чтобы оставить ссылку на самого себя. 
	 *  
	 * @param mainApp 
	 */ 
	 public void setMainApp(MainApp mainApp) { 
		 this.mainApp = mainApp; 
	 }
	 
	 /** 
	 * Создаёт пустую базу 
	 */ 
	 @FXML 
	 private void handleNew() { 
		 mainApp.getPersonData().clear(); 
		 mainApp.setPersonFilePath(null); 
	 } 
	 /** 
	 * Открывает FileChooser, чтобы пользователь имел возможность 
	 * выбрать базу для загрузки 
	 */ 
	 @FXML 
	 private void handleOpen() { 
		 FileChooser fileChooser = new FileChooser(); 
		 
		 // Задаём фильтр расширений 
		 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter( 
		 "XML files (*.xml)", "*.xml"); 
		 fileChooser.getExtensionFilters().add(extFilter); 
		 
		 // Показываем диалог загрузки файла 
		 File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		 if (file != null) { 
			 mainApp.loadPersonDataFromFile(file); 
		 } 
	 } 
	 
	 /** 
	 * Сохраняет файл в файл студентов, который в настоящее время открыт. 
	 * Если файл не открыт, то отображается диалог "save as". 
	 */ 
	 @FXML 
	 private void handleSave() { 
		 File personFile = mainApp.getPersonFilePath(); 
		 if (personFile != null) { 
			 mainApp.savePersonDataToFile(personFile); 
		 } else { 
			 handleSaveAs(); 
		 } 
	 }
	 
	 /** 
	 * Открывает FileChooser, чтобы пользователь имел возможность 
	 * выбрать файл, куда будут сохранены данные 
	 */ 
	 @FXML 
	 private void handleSaveAs() { 
		 FileChooser fileChooser = new FileChooser();
		 
		 // Задаём фильтр расширений 
		 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter( 
		 "XML files (*.xml)", "*.xml"); 
		 fileChooser.getExtensionFilters().add(extFilter); 
		 
		 // Показываем диалог сохранения файла 
		 File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage()); 
		 if (file != null) { 
			 // Make sure it has the correct extension 
			 if (!file.getPath().endsWith(".xml")) { 
				 file = new File(file.getPath() + ".xml"); 
			 } 
			 mainApp.savePersonDataToFile(file); 
		 }
	 } 
	 /** 
	 * Открывает диалоговое окно about. 
	 */ 
	 @FXML 
	 private void handleAbout() { 
		 Alert alert = new Alert(AlertType.INFORMATION); 
		 alert.setTitle("StudentGroupApp"); 
		 alert.setHeaderText("About"); 
		 alert.setContentText("Some text"); 
		 alert.showAndWait(); 
	 }

	 
	 /** 
	 * Закрывает приложение 
	 */ 
	 @FXML 
	 private void handleExit() { 
		 System.exit(0); 
	 } 
}