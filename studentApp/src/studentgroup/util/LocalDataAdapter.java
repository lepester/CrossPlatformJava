package studentgroup.util;

import java.time.LocalDate; 
import javax.xml.bind.annotation.adapters.XmlAdapter;

/** 
* Адаптер (для JAXB) для преобразования между типом LocalDate и строковым 
* представлением даты в стандарте ISO 8601, например как '2019-12-22'. 
*/ 
public class LocalDataAdapter extends XmlAdapter<String, LocalDate> { 
	 @Override 
	 public LocalDate unmarshal(String v) throws Exception { 
		 return LocalDate.parse(v); 
	 }
	 
	 @Override 
	 public String marshal(LocalDate v) throws Exception { 
		 return v.toString(); 
	 } 
}
