import java.math.BigDecimal;
import java.util.Scanner;

public abstract class Purchase {
	
	//This is called from POSapp.main
	//prompt user for form of payment 
	//call one of the forms of payment
	//Queries for specific information 
	//return payment information at end of purchase 
	
	
	public static String  purchaseOptions(Scanner scan, BigDecimal money){
		int userPaymentType = Validator.getInt(scan,
				"How will you be paying today? \n 1. Cash \n 2. Check \n 3. Credit Card", 1,3);
		
		Purchase purchaseType;
		switch(userPaymentType) {
		case 1: 
			Purchase cashName = new Cash();
			return cashName.purchase(scan, money);
		case 2:
			 Purchase checkName = new Check();
			return checkName.purchase(scan, money);
		case 3:
			Purchase CCName = new CreditCard();
			return CCName.purchase(scan, money);
			
		}
		 
		return null;
		
	}
	
	public abstract String purchase (Scanner scan, BigDecimal totalCost);
	
		
	
}
