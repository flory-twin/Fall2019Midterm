import java.math.BigDecimal;
import java.util.Scanner;

public class Check extends Purchase {

		// asks user for check number and returns a string.

	@Override
	public String purchase(Scanner scan, BigDecimal totalCost) {
		//Restricts check number to a maximum of 5 digits long.
		int userCheck = Validator.getInt(scan, "What is your check number (nnnnn)?", 0, 99999);
		return "Check number " + userCheck;
	}
}