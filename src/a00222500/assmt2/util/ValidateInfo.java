package a00222500.assmt2.util;

import java.util.regex.*;

public class ValidateInfo
{
	public ValidateInfo(){
	}

	public static boolean validateForm(StringBuffer errorMessages, String[] names, String[] values){

		String email = "^\\w+([\\.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,3})+$";
					
		String phone = "^[(]?(\\d{3})[)]?[-\\s]?(\\d{3})[-\\s]?(\\d{4})$";
		String[][] selectAll= {{"firstName", "First Name"}, {"lastName", "Last Name"}, {"Address", "Address"}, 
			{"City", "City"}, {"Country", "Country"}, {"Code", "Postal Code"}, {"PhoneNumber", "Phone Number"}, {"EMail", "Email"}};
						
        for (int i=0; i< names.length; i++) {
			if (names[i].equals("PhoneNumber") && (values[i]==null || values[i].equals("") || !validateString(values[i],phone))) {
				errorMessages.append("<li>Phone number is blank or is not valid</li>");
			} else if (names[i].equals("EMail") && (values[i]==null || values[i].equals("") || !validateString(values[i],email))) {
                errorMessages.append("<li>Email is blank or is not valid</li>");
			} else {
				for (int j=0; j< selectAll.length -2; j++) {
					 if (names[i].equals(selectAll[j][0]) && (values[i] == null || values[i].equals(""))) {
						 errorMessages.append("<li>" + selectAll[j][1] + " is blank or is not valid</li>");
					 }
				}
			}
        }
		
		if (errorMessages.length() > 0)
			return false;
		else
			return true;
	}

	public static boolean validateString(String text,String pattern){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		return(m.matches());
	}

    public static String displayErrors(String label, String errors){
		return "<h3>" + label + "</h3>\n<ul>\n" + errors + "\n</ul>\n";
	}
}
