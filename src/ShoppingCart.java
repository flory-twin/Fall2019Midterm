import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/*
 *    
   
    
    
      
      displayCart()
      > Rounding (Note)
      
      -ArrayList of -potential- products (the inventory)
      -HashMap<CatsProduct, int> (the actual cart) 
 */
public class ShoppingCart {

	private ArrayList<CatsProduct> inventory;
	private HashMap<CatsProduct, Integer> cart;

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
		inventory = new ArrayList<>();
		cart = new HashMap<>();

	}

	// TODO Change back from public to private once testing is done
	public void readCatsFromCSV(String fileName) {

		// Open the file for reading
		Path path = Paths.get("resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			// One-at-a-time, read each line in the file.
			while (line != null) {
				// For each line,
				// Create a CatsProduct from the line and
				CatsProduct theCatOnThisLine = CatsProduct.fromString(line);
				// Add it to the list.
				inventory.add(theCatOnThisLine);
				// Now read a new line.
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read the file.");

		}

	}
//   calculateSalesTax()
//   calculateSubTotal()
//   calculateGrandTotal()
//   addItemToCart()
//   removeItemFromCart()
//   displayCart()

	public static void main(String[] args) {
		ShoppingCart thisCartInstance = new ShoppingCart();
		// This -uses- a prison cell.
		// Other people call it a "method call" or "calling the method."
		// There might be an equals in front (like int a = scan.nextInt();)
		// That's a dead giveaway if it's there.
		// Even if there isn't, (1) it will NEVER have moustaches and (2) it will ALWAYS
		// be inside another method.
		thisCartInstance.readCatsFromCSV("Cats.csv");
	//	System.out.println(thisCartInstance.inventory);
		CatsProduct catTest = new CatsProduct("Russian Blue", "Voika", "Silvery-blue cat.", new BigDecimal(600));
		thisCartInstance.addItem(catTest);
//		System.out.println(thisCartInstance.cart);
		thisCartInstance.addItem(catTest);
		thisCartInstance.addItem(new CatsProduct("Persian", "Maluka", "Pure white with floof", new BigDecimal(300)));
		System.out.println(thisCartInstance.cart);
//		thisCartInstance.removeItem(catTest);
//		System.out.println(thisCartInstance.cart);
//		thisCartInstance.removeItem(catTest);
//		System.out.println(thisCartInstance.cart);
//		thisCartInstance.removeItem(catTest);
//		System.out.println(thisCartInstance.cart);
		BigDecimal subtotal = thisCartInstance.calcTotalBeforeTax();
		System.out.println("Total before tax: " + subtotal);
		System.out.println("Tax: " + thisCartInstance.calculateSalesTax(subtotal));
		System.out.println("Total: " + thisCartInstance.calculateTotal());
		
	}

	// This defines a prison cell; other people call it a "method definition."
	// It's the only one which can have public, static, or BigDecimal directly in
	// front of the name.
	// It's also the only one with moustaches.
	// It can NEVER occur inside another method.
	public BigDecimal calcTotalBeforeTax() {

		// All the loops are jumprope/loop-de-loop.
		// To extend the torture example...
		// * The people holding the rope set the rules! They have two main types:
		// * The -normal- for loop is a simple counting game;
		// the people holding the rope make everyone who is tortured jump 250 or 500 or
		// 20,000 before
		// you can stop. I guess they do it with whips...it's like a rack in the prison
		// cell.
		// * The -normal- while loop is even ruder;
		// you have to go until they're satisfied.
		// They'll tell you what will satisfy them...but since
		// we're putting different prisoners through the torture, different
		// prisoners take different counts to do it.
		// The torturers might play favorites and let some prisoners off light...
		// * The ENHANCED for loop is still a simple counting game.
		// However, the torturers are not yelling the number at you.
		// Instead, they've got an array or a list or something, and they're
		// going through it one by one, and yelling the array content at you.
		// You'll still get out in a set number of jumps.
		// This looks like:
		// for (Thing nameForTheThing : aBunchOfThings) {
		// yell (nameForTheThing);
		// }
		//

		BigDecimal subTotal = new BigDecimal(0.0);

		for (CatsProduct entry : cart.keySet()) {
			//System.out.println(entry + " = " + cart.get(entry));
			subTotal = subTotal.add((entry.getPrice().multiply(new BigDecimal(cart.get(entry)))));
		}
		return subTotal;
	}
	
	public BigDecimal calculateSalesTax(BigDecimal subtotal) {
	   return new BigDecimal(0.07).multiply(calcTotalBeforeTax());
	}
	
	public BigDecimal calculateTotal()
	{
	   BigDecimal totalBeforeTax = calcTotalBeforeTax();
	   return (totalBeforeTax.add(
	         calculateSalesTax(totalBeforeTax)));
	}
	

	public void removeItem(CatsProduct thingToDelete) {
		if (cart.containsKey(thingToDelete)) {
			int currentCount = cart.get(thingToDelete);
			int newCount = currentCount - 1;
			if (newCount == 0) {
				cart.remove(thingToDelete);

			} else {
				cart.put(thingToDelete, newCount);

			}
		}

		else {

		}

	}
	
	

	public void addItem(CatsProduct thingToAdd) {

		if (cart.containsKey(thingToAdd)) {
			int currentCount = cart.get(thingToAdd);
			int newCount = currentCount + 1;
			cart.put(thingToAdd, newCount);
		}

		else {
			cart.put(thingToAdd, 1);
		}
	}

	public String toString() {
		String rowFormatString = "%15s %10s %100s $%.2f";
		String headerFormatString = "%15s %10s %100s %6s";
		
		String headers = String.format(
				headerFormatString,  
				"Cat Name", "Cat Type", "Cat Description", "Adoption Fee");
		String catRows = "";
		for (CatsProduct c : inventory) {
			catRows += String.format(
					rowFormatString,
					c.getName(), c.getCategory(), c.getDescription(), c.getPrice());
		}
		
		return headers + catRows;
	}
	
	public String promptForCatDetails(Scanner scan) {
		int userNum = Validator.getInt(scan, 
				"Choose a number 1-" + inventory.size() + " to view from our fine selection of cats: ", 
				1, 
				inventory.size());
		
		return inventory.get(userNum - 1).pretty();
	}
	public String getNameList() {
		String rowFormatString = " n%d. %15s";
		String headerFormatString = "   %15s";
		
		String headers = String.format(
				headerFormatString,  
				"Cat Name");
		String catRows = "";
		
		int ctr = 0;
		for (CatsProduct c : inventory) {
			catRows += String.format(
					rowFormatString,
					ctr, c.getName());
			ctr++;
		}
		
		return headers + catRows;
	}
}
