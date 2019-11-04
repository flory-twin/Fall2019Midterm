import java.math.BigDecimal;
import java.util.Scanner;

public class CreditCard extends Purchase {

	public static String purchaseCreditCard(Scanner scan ) {

		String userCCNum = Validator.getStringMatchingRegex(scan, "What is your credit card number? (nnnnnnnnnnnnnnnn)", "\\d{16}");
		int userCvv = Validator.getInt(scan, "What is your CVV");
		String userExp = Validator.getString(scan, "What is your experation date?");
		return "Card **** **** **** " + userCCNum.substring(11,15);
	}
}
