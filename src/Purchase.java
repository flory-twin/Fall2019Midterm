import java.math.BigDecimal;
import java.util.Scanner;

public class Purchase {
	
	//This is called from POSapp.main
	//prompt user for form of payment 
	//call one of the forms of payment
	//Queries for specific information 
	//return payment information at end of purchase 
	public static String  purchaseOptions(BigDecimal money){
		Scanner scan = new Scanner(System.in);
		
		int userPaymentType = Validator.getInt(scan,
				"How will you be paying today? /n 1 for Cash /n 2 for Check /n 3 for Credit Card", 1,3);
		switch(userPaymentType) {
		
		case 1: 
			return Cash.purchaseCash(scan, money);
		case 2:
			return Check.purchaseCheck(scan, money);
		case 3:
			return CreditCard.purchaseCreditCard(scan, money);
			
		}
		 
			
		
		
		return null;
		
	}
}
