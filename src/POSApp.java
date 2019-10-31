import java.util.ArrayList;

public class POSApp {

	public static void main(String[] args) {
		/*
		 * Present a menu to the user and let them choose an item (by number or letter).
  - Allow the user to choose a quantity for the item ordered
  - Give the user a line total.
     Validator
- Either through the menu or a separate question, allow them to re-display the menu and to complete the purchase.
    Validator

- GIve the subtotal, sales tax, and grand total.  
     Methods 

- Ask for payment type (cash, credit, check)
   For cash, 
   For check 
   For credit, get the credit card number, expiration, and CVV.

- At the end, display a receipt with all items ordered, the subtotal, the grand total, and appropriate payment information.

- Return to the original menu for a new order.
		 */
		ArrayList<CatsProduct> catsList = new ArrayList<CatsProduct>(); 
		System.out.println("Welcome to our Cat'fe! Please adopt a cat.");
		System.out.println("Choose a number 1-12 to view from our fine selection of cats.");
		
		for (int i=0;i<catsList.size();i++) {
			System.out.println(i);
			
		}
		
	}

}
