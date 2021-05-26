package studentgroup.model;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter; 
import studentgroup.util.LocalDataAdapter;
/**
*  ласс-модель дл€ студента (Person).
*/
public class Person {
 private final StringProperty firstName;
 private final StringProperty lastName;
 private final IntegerProperty groupCode;
 private final ObjectProperty<LocalDate> birthday;
 private final IntegerProperty placeInGroup;
 private final StringProperty city;
 private final DoubleProperty averageGrade;
 /**
 *  онструктор по умолчанию.
 */
 public Person() {
 this(null, null);
 }

 /**
 *  онструктор с некоторыми начальными данными.
 *
 * @param firstName
 * @param lastName
 */
 public Person(String firstName, String lastName) {
 this.firstName = new SimpleStringProperty(firstName);
 this.lastName = new SimpleStringProperty(lastName);

 //  акие-то фиктивные начальные данные дл€ удобства тестировани€.
 this.groupCode = new SimpleIntegerProperty(1747);
 this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 2, 21));
 
 //////////////////////////////////////
 
 this.placeInGroup = new SimpleIntegerProperty(1);
 this.city = new SimpleStringProperty("Kharkiv");
 this.averageGrade = new SimpleDoubleProperty(100.0);
 
 /////////////////////////////////////
 
 }

 public String getFirstName() {
 return firstName.get();
 }
 public void setFirstName(String firstName) {
 this.firstName.set(firstName);
 }

 public StringProperty firstNameProperty() {
 return firstName;
 }
 public String getLastName() {
 return lastName.get();
 }
 public void setLastName(String lastName) {
 this.lastName.set(lastName);
 }
 
 public StringProperty lastNameProperty() {
 return lastName;
 }
 public int getGroupCode() {
 return groupCode.get();
 }
 public void setGroupCode(int postalCode) {
 this.groupCode.set(postalCode);
 }

 public IntegerProperty groupCodeProperty() {
 return groupCode;
 }
 /*public LocalDate getBirthday() {
 return birthday.get();
 }*/
 
 @XmlJavaTypeAdapter(LocalDataAdapter.class) 
 public LocalDate getBirthday() { 
 return birthday.get(); 
 }
 
 public void setBirthday(LocalDate birthday) {
 this.birthday.set(birthday);
 }

 public ObjectProperty<LocalDate> birthdayProperty() {
 return birthday;
 }
 ////////////////////////////////////////////////////////////
 public int getPlaceNumber() {
 return placeInGroup.get();
 }
 public void setPlaceNumber(int postalCode) {
 this.placeInGroup.set(postalCode);
 }

 public IntegerProperty placeNumberProperty() {
 return placeInGroup;
 }
 
 
 public double getAverGrade() {
 return averageGrade.get();
 }
 public void setAverGrade(double grades) {
 this.averageGrade.set(grades);
 }

 public DoubleProperty averGradeProperty() {
 return averageGrade;
 }
 
 public String getCityName() {
	 return city.get();
	 }
	 public void setCityName(String cityLive) {
	 this.city.set(cityLive);
	 }
	 
	 public StringProperty cityNameProperty() {
	 return city;
	 }
	 
	 
	 
	
 
}



