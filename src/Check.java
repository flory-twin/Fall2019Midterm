import java.math.BigDecimal;
import java.util.Scanner;

public class Check extends Purchase {

	//prompts user for check number, and returns a strings for user input
	public static String purchaseCheck(Scanner scan) {

		int userCheck = Validator.getInt(scan, "What is your check number?");
		return "Check number " + userCheck;
	}
}