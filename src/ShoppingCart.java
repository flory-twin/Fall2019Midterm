import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/*
 *    readFromCsv()
      calculateSalesTax()
      calculateSubTotal()
      calculateGrandTotal()
      addItemToCart()
      removeItemFromCart()
      displayCart()
      > Rounding (Note)
      
      -ArrayList of -potential- products (the inventory)
      -HashMap<CatsProduct, int> (the actual cart) 
 */
public class ShoppingCart
{

   private ArrayList<CatsProduct> inventory;
   private HashMap<CatsProduct, Integer> cart;
   
   public ShoppingCart() {
      super();
      // TODO Auto-generated constructor stub
      inventory = new ArrayList<>();
      cart = new HashMap<>();
      
      
   }

   //TODO Change back from public to private once testing is done
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
      //     That's a dead giveaway if it's there.
      // Even if there isn't, (1) it will NEVER have moustaches and (2) it will ALWAYS be inside another method.
      thisCartInstance.readCatsFromCSV("Cats.csv");
      System.out.println(thisCartInstance.inventory);
      
   }
   
   // This defines a prison cell; other people call it a "method definition."
   //   It's the only one which can have public, static, or BigDecimal directly in front of the name.
   //   It's also the only one with moustaches.
   //   It can NEVER occur inside another method.
  public BigDecimal calcTotalBeforeTax() { 
	  
	  // All the loops are jumprope/loop-de-loop. 
	  // To extend the torture example...
	  //  * The people holding the rope set the rules! They have two main types:
	  //  * The -normal- for loop is a simple counting game;
	  //      the people holding the rope make everyone who is tortured jump 250 or 500 or 20,000 before 
	  //      you can stop. I guess they do it with whips...it's like a rack in the prison cell.
	  //  * The -normal- while loop is even ruder;
	  //      you have to go until they're satisfied.
	  //      They'll tell you what will satisfy them...but since 
	  //      we're putting different prisoners through the torture, different
	  //      prisoners take different counts to do it. 
	  //      The torturers might play favorites and let some prisoners off light...
	  //  * The ENHANCED for loop is still a simple counting game.
	  //      However, the torturers are not yelling the number at you.
	  //      Instead, they've got an array or a list or something, and they're 
	  //      going through it one by one, and yelling the array content at you.
	  //        You'll still get out in a set number of jumps.
	  //      This looks like:
	  //                  for (Thing nameForTheThing : aBunchOfThings) {
	  //                       yell (nameForTheThing);
	  //                  }
	  //  
	  
	  BigDecimal subTotal = new BigDecimal(0.0);
	  
	  for (CatsProduct entry : cart.keySet()) {
		    System.out.println(entry + " = " + cart.get(entry));
		    subTotal = subTotal.add(
		    		(entry.getPrice().multiply(
		    				new BigDecimal(
		    						cart.get(entry)))));
		}
	return subTotal;
	  
   }
}
