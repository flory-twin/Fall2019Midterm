import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
      thisCartInstance.readCatsFromCSV("Cats.csv");
      System.out.println(thisCartInstance.inventory);
   }
}
