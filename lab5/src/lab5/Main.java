package lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

public class Main {
	    public static void main(String[] args) {
	        String string = "e02fd0e4-00fd-090A-ca30-0d00a0038ba0";
	        Pattern pattern = Pattern.compile("(?i)[a-f\\d]{8}-([a-f\\d]{4}-){3}[a-f\\d]{12}");
	        Matcher matcher = pattern.matcher(string);
	        if (matcher.find()) System.out.print("Y");       
	        else System.out.print("N");
	    }
}
