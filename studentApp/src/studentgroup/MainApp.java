package studentgroup;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import studentgroup.model.Person;

import studentgroup.view.PersonOverviewController;
import studentgroup.view.RootLayoutController;
import javafx.stage.Modality;
import studentgroup.view.PersonEditDialogController;

import javafx.scene.image.Image;

import java.io.File;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import studentgroup.model.PersonListWrapper;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

/////////////////////////////////////////////////////////////////////////////////
    /**
     * Данные, в виде наблюдаемого списка адресатов.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    /**
     * Конструктор
     */
    public MainApp() {
        // В качестве образца добавляем некоторые данные
        personData.add(new Person("Martin", "Mueller"));
        personData.add(new Person("Guts", "Man"));
        personData.add(new Person("Shierke", "Dirty"));
        personData.add(new Person("Finn", "Divine"));
    }

    /**
     * Возвращает данные в виде наблюдаемого списка студентов.
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    ////////////////////////////////////////////////////////////////////


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("StudentGroupApp");
        initRootLayout();
        showPersonOverview();

        /////////////////////////********/////////////////////
        this.primaryStage.getIcons().add(new Image("file:resources/images/StudentGroup_32.png"));
        ////////////////////////*********////////////////////
    }

    /**
     * Инициализирует корневой макет и пытается загрузить последний открытый
     * файл с данными о студентах.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Пытается загрузить последний открытый файл с данными о студентах.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }

    public void showPersonOverview() {
        try {
            // Загружаем сведения о студентах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Помещаем сведения о студентах в центр корневого макета.
            rootLayout.setCenter(personOverview);

            //Даём контроллеру доступ к главному приложению.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Открывает диалоговое окно для изменения данных указанного студента.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте студента и возвращается значение true.
     *
     * @param person - объект студента, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("file:resources/images/dialog_icon.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём студента в контроллер.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Возвращает preference файла студентов, то есть, последний открытый файл.
     * Этот preference считывается из реестра, специфичного для конкретной
     * операционной системы. Если preference не был найден, то возвращается null.
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Задаёт путь текущему загруженному файлу. Этот путь сохраняется
     * в реестре, специфичном для конкретной операционной системы.
     *
     * @param file - файл или null, чтобы удалить путь
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Обновление заглавия сцены.
            primaryStage.setTitle("StudentGroupApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Обновление заглавия сцены.
            primaryStage.setTitle("StudentGroupApp ");
        }
    }

    /**
     * Загружает информацию о студентах из указанного файла.
     * Текущая информация о студентах будет заменена.
     *
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла и демаршализация.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Сохраняем путь к файлу в реестре.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
    /**
     * Сохраняет текущую информацию о студентах в указанном файле.
     *
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext  .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Обёртываем наши данные о студентах.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Маршаллируем и сохраняем XML в файл.
            m.marshal(wrapper, file);

            // Сохраняем путь к файлу в реестре.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}