package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private Pattern pattern;
    private Matcher matcher;
    public static final String USER_NAME_PATTERN = "^[a-zA-Z0-9]+$";
    public static final String PASS_WORD_PATTERN = "^[a-zA-Z0-9]+$";
    public static final String ROLE_PATTERN = "^(ADMIN|USER)$";
    public static final String NAME_PATTERN = "^[A-Za-z]+(\\s\\|\\s[A-Za-z]+\\s[A-Za-z]+\\s[A-Za-z]+)?$";
    public static final String GENDER_PATTERN = "^(nam|nu|Nam|Nu|Male|Female|male|female)$";
    public static final String BIRTHDAY_PATTERN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String PHONE_PATTERN = "^(?:\\+84|0)[0-9]{9}$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String INT_PATTERN = "^\\d+$";
    public static final String NAME_PRODUCT_PATTERN = "^[A-Za-z0-9\\s]+$";
    public static final String PRICE_PATTERN = "^[1-9]\\d*\\.\\d+$";

    public Validate() {
    }

    public boolean isValidateInputString(String inputString, String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(inputString);
        return matcher.matches();
    }
}
