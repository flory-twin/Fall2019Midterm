import java.math.BigDecimal;
import java.util.Scanner;

public class Check extends Purchase {

	public static String purchaseCheck(Scanner scan) {

		int userCheck = Validator.getInt(scan, "What is your check number?");
		return "Check number " + userCheck;
	}
}