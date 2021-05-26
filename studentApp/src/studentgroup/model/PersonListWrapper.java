package studentgroup.model;

import java.util.List; 
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 

/** 
* ¬спомогательный класс дл€ обЄртывани€ списка студентов. 
* »спользуетс€ дл€ сохранени€ списка студентов в XML. 
*  
* @author Marco Jakob 
*/ 
@XmlRootElement(name = "persons") //определ€ет им€ корневого элемента 
public class PersonListWrapper { 
	private List<Person> persons; 
	@XmlElement(name = "person") // необ€зательное им€, его можем задать дл€ элемента 
	public List<Person> getPersons() { 
		 return persons; 
	} 
	public void setPersons(List<Person> persons) { 
		 this.persons = persons; 
	} 
}
