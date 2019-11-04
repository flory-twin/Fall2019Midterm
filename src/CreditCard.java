import java.math.BigDecimal;
import java.util.Scanner;

public class CreditCard extends Purchase {

	//Prompts user for CC num, Cvv, and expiration date and returns a substring to only show last 4
	//digits of users CC num.
	public static String purchaseCreditCard(Scanner scan ) {

		String userCCNum = Validator.getStringMatchingRegex(scan, "What is your credit card number? (nnnnnnnnnnnnnnnn)", "\\d{16}");
		int userCvv = Validator.getInt(scan, "What is your CVV?");
		String userExp = Validator.getString(scan, "What is your experation date?");
		return "Card **** **** **** " + userCCNum.substring(11,15);
	}
}
