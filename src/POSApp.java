import java.util.ArrayList;

public class POSApp {

	public static void main(String[] args) {
		
		ArrayList<CatsProduct> catsList = new ArrayList<CatsProduct>(); 
		
		
		CatsProduct cp = CatsProduct.fromString("Toto,Wannabee Cat,Dog meets potbelly pig meets cat.,5000");
		System.out.println(cp);
	}

}
