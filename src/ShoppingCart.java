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

public class ShoppingCart {

	private ArrayList<CatsProduct> inventory;
	private HashMap<CatsProduct, Integer> cart;

	public ShoppingCart() {
		super();

		inventory = new ArrayList<>();
		cart = new HashMap<>();

	}


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
	//Main method used for testing
	public static void main(String[] args) {
		ShoppingCart thisCartInstance = new ShoppingCart();

		thisCartInstance.readCatsFromCSV("Cats.csv");

		CatsProduct catTest = new CatsProduct("Russian Blue", "Voika", "Silvery-blue cat.", new BigDecimal(600));
		thisCartInstance.addItem(catTest);

		thisCartInstance.addItem(catTest);
		thisCartInstance.addItem(new CatsProduct("Persian", "Maluka", "Pure white with floof", new BigDecimal(300)));
		System.out.println(thisCartInstance.cart);

		BigDecimal subtotal = thisCartInstance.calcTotalBeforeTax();
		System.out.println("Total before tax: " + subtotal);
		System.out.println("Tax: " + thisCartInstance.calculateSalesTax());
		System.out.println("Total: " + thisCartInstance.calculateTotal());
		
	}

	
	public BigDecimal calcTotalBeforeTax() {

		BigDecimal subTotal = new BigDecimal(0.0);

		for (CatsProduct entry : cart.keySet()) {

			subTotal = subTotal.add((entry.getPrice().multiply(new BigDecimal(cart.get(entry)))));
		}
		return subTotal;
	}
	
	public BigDecimal calculateSalesTax() {
	   return new BigDecimal(0.07).multiply(calcTotalBeforeTax());
	}
	
	public BigDecimal calculateTotal()
	{
	   BigDecimal totalBeforeTax = calcTotalBeforeTax();
	   return (totalBeforeTax.add(
	         calculateSalesTax()));
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

	//Formats all the cats in the cart as a table 
	public String toString() {
		String rowFormatString = " %2d %-10s %-20s $%.2f\n";
		String headerFormatString = "%3s %-10s %-20s %6s\n";
		
		String headers = String.format(
				headerFormatString,  
				"No.", "Cat Name", "Cat Type", "Adoption Fee");
		String catRows = "";
		for (CatsProduct c : cart.keySet()) {
			catRows += String.format(
					rowFormatString,
					cart.get(c), c.getName(), c.getCategory(), c.getPrice());
		}
		
		return headers + catRows;
	}
	
	public CatsProduct promptForCat(Scanner scan) {
		int userNum = Validator.getInt(scan, 
				"Choose a number 1-" + inventory.size() + " to view from our fine selection of cats: ", 
				1, 
				inventory.size());
		
		return inventory.get(userNum - 1);
	}
	
	//Gets list of cat names from the inventory 
	public String getNameList() {
		String rowFormatString = " %2d. %-15s\n";
		String headerFormatString = "    %-15s\n";
		
		String headers = String.format(
				headerFormatString,  
				"Cat Name");
		String catRows = "";
		
		int ctr = 1;
		for (CatsProduct c : inventory) {
			catRows += String.format(
					rowFormatString,
					ctr, c.getName());
			ctr++;
		}
		
		return headers + catRows;
	}
}
