package rk.fireme.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isPhoneValid(String phone)
    {
        if (phone.length() != 13 || !phone.startsWith("+380")) return false;

        String unique = phone.substring(4);

        Pattern p = Pattern.compile("^\\d{9}$");
        Matcher m = p.matcher(unique);

        return (m.matches());
    }

    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
