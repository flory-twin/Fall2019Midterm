import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Kevin Flory, Amber Dostert, Adam Graff
 */
public class POSApp {
	/**
	 * This main method shows the user the initial menu.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean quitChoice = false;

		System.out.println("Welcome to our Cat'fe!");
		System.out.println("Please adopt as many cats as you like.");
		System.out.println("Please note the associated adoption fee.");
		System.out
				.println("You may back-order multiple cloned versions of the cat(s) of your choice for the same fee.");

		// Imports a spreadsheet of inventory using the readCatsFromCSV.
		ShoppingCart cart = new ShoppingCart();
		cart.readCatsFromCSV("Cats.csv");

		// While the user hasn't quit, while loop presents the choices for the next
		// step.
		do {
			System.out.println("Please choose one of the following options (#):");
			int userChoice = Validator.getInt(scan, " 1. View list of all cats.\n 2. View your kitty cart.\n 3. Quit.");
			switch (userChoice) {

			case 1:
				listCats(cart, scan);
				break;

			case 2:
				quitChoice = viewCart(cart, scan);
				break;

			case 3:
			default:
				quitChoice = true;
			}

		} while (!quitChoice);

	}

	// This method aks the user for payment method and returns the payment
	// information.
	public static String paymentChoice(Scanner scan, BigDecimal money) {
		System.out.println("How would you like to pay?");
		int userPaymentChoice = Validator.getInt(scan, " 1. Credit card \n 2. Check \n 3. Cash ", 1, 3);
		if (userPaymentChoice == 1) {
			return CreditCard.purchaseCreditCard(scan);
		} else if (userPaymentChoice == 2) {
			return Check.purchaseCheck(scan);
		} else {
			return Cash.purchaseCash(scan, money);
		}
	}

	// This method displays the list of cats and shows the details of a specific
	// cat.
	public static void listCats(ShoppingCart cart, Scanner scan) {
		System.out.println("==================================================");
		System.out.println(cart.getNameList());
		CatsProduct c = cart.promptForCat(scan);
		System.out.println(c.pretty());
		addCatToCart(cart, scan, c);
	}

	// This method adds the user selected cats to the shopping cart.
	public static void addCatToCart(ShoppingCart cart, Scanner scan, CatsProduct c) {

		String userChoice = Validator.getStringMatchingRegex(scan,
				"Would you like to add this cat to your cart? (y/n): ", "[Yy]|[nN]");
		if (userChoice.equalsIgnoreCase("y")) {
			int howMany = Validator.getInt(scan, "How many?");
			for (int i = 0; i < howMany; i++) {
				cart.addItem(c);
			}
		} else if (userChoice.equalsIgnoreCase("n")) {

		}

		Validator.getString(scan, "Press any key to continue.");

	}

	// Allows the user to view the cart and check out.
	public static boolean viewCart(ShoppingCart cart, Scanner scan) {
		System.out.println("==================================================");
		System.out.println(cart);
		int userChoice = Validator.getInt(scan, " 1. Check out \n 2. Main menu \n 3. Quit ", 1, 3);
		if (userChoice == 1) {

		printReceipt(cart, scan);	
			return true;
		} else if (userChoice == 3) {
			return true;
		} else if (userChoice == 2) {
			return false;
		} else {
			return false;
		}

	}
	
	// Prints the receipt and formats the details. 
	public static void printReceipt(ShoppingCart cart, Scanner scan) {
		String paymentInfo = paymentChoice(scan, cart.calcTotalBeforeTax());

		System.out.println("==================================================");
		System.out.println("YOUR RECEIPT:");
		System.out.println(cart);
		System.out.println("--------------------------------------------------");
		System.out.printf("Subtotal: $%.2f\n", cart.calcTotalBeforeTax());
		System.out.printf("Sales tax: $%.2f\n", cart.calculateSalesTax());
		System.out.printf("Grand total: $%.2f\n ", cart.calculateTotal());
		System.out.println(paymentInfo);
		System.out.println("**Thank you for you purchase! Cat-ch you later!**");
	}
}
