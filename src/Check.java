import java.math.BigDecimal;
import java.util.Scanner;

public class Check extends Purchase {


	@Override
	public String purchase(Scanner scan, BigDecimal totalCost) {

		int userCheck = Validator.getInt(scan, "What is your check number?");
		return "Check number " + userCheck;
	}
}