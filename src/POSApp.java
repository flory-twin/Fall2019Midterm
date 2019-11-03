
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
			case 2:
				System.out.println(cart);
			}

		} while (true);

	}

	public static void viewCatListAndMaybeAddToCart(ShoppingCart cart, Scanner scan) {
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

}
