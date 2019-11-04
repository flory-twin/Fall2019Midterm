import java.math.BigDecimal;
import java.util.Scanner;

public class Cash extends Purchase {
	
	public static String purchaseCash(Scanner scan, BigDecimal totalPaid) {
		
		
		double userCash = Validator.getDouble(scan,
		"How much are you paying in cash?");
		
		return "Your change is: " + (totalPaid.subtract(new BigDecimal(userCash))); 
	}

}
