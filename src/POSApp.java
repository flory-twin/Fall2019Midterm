
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POSApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		int userNum = 0;
		boolean quitChoice = false;
		System.out.println("Welcome to our Cat'fe!");
		System.out.println("Please adopt as many cats as you like.");
		System.out.println("Please note the associated adoption fee.");
		System.out
				.println("You may back-order multiple cloned versions of the cat(s) of your choice for the same fee.");

		ShoppingCart cart = new ShoppingCart();
		cart.readCatsFromCSV("Cats.csv");

		do {
			System.out.println("Please choose one of the following options (#):");
			int userChoice = Validator.getInt(scan, " 1. View list of all cats.\n 2. View your kitty cart.\n 3. Quit.");
			switch (userChoice) {
			case 1:
				viewCatListAndMaybeAddToCart(cart, scan);
				break;
			case 2:
				quitChoice = viewCartAndMaybeCheckout(cart, scan);
				break;
			case 3:
			default:
				quitChoice = true;	
			}

		} while (!quitChoice);

	}

	public static void paymentChoice (Scanner scan, BigDecimal money) {
		int userPaymentChoice = Validator.getInt(scan,
				" 1. Credit card \n 2. Check \n 3. Cash ", 1,3);
		if (userPaymentChoice == 1 ) {
			CreditCard.purchaseCreditCard(scan);
		}
		if (userPaymentChoice == 2 ) {
			Check.purchaseCheck(scan);
		}
		else if (userPaymentChoice == 3 ) {
			Cash.purchaseCash(scan, money);
		}
	}
	
	
	public static void viewCatListAndMaybeAddToCart(ShoppingCart cart, Scanner scan) {
		System.out.println("==================================================");
		System.out.println(cart.getNameList());
		CatsProduct c = cart.promptForCat(scan);
		System.out.println(c.pretty());

		String userChoice = Validator.getStringMatchingRegex(scan,
				"Would you like to add this cat to your cart? (y/n): ", "[Yy]|[nN]");
		if (userChoice.equalsIgnoreCase("y")) {
			cart.addItem(c);
		} else if (userChoice.equalsIgnoreCase("n")) {

		}

		Validator.getString(scan, "Press any key to continue.");
		
		
		
	}
	
	public static boolean viewCartAndMaybeCheckout(ShoppingCart cart, Scanner scan) {
		System.out.println("==================================================");
		System.out.println(cart);
		int userChoice = Validator.getInt(scan,
				" 1. Check out \n 2. Main menu \n 3. Quit ", 1,3);
		if (userChoice == 1) {
			paymentChoice(scan, cart.calcTotalBeforeTax());
			System.out.printf("Subtotal: %.2f\n", cart.calcTotalBeforeTax());
			System.out.printf("Sales tax: %.2f\n", cart.calculateSalesTax());
			System.out.printf("Grand total: %.2f\n ", cart.calculateTotal());
			System.out.println("Thank you for you purchase! Cat-ch you later!");
			return true;
		}
		else if (userChoice == 3) {
			return true;
		}
		else if (userChoice == 2) {
			return false;
		}
		else {
			return false;
		}

		
	}
}
