import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GroceryStore {
	//main class that outputs the total time needed to check out all the customers 
	//with the number of registers inputed

	RegisterListCollection registerListCollection = null;
	private static Queue<Customer> customerInLine = new LinkedList<Customer>();
	
	public GroceryStore(RegisterListCollection registerListCollection) {
		this.registerListCollection = registerListCollection;
	}
	
	public RegisterListCollection getRegisterListCollection() {
		return registerListCollection;
	}
	
	public static Queue<Customer> getCustomerInLine(){
		return customerInLine;
	}
	
	private static int calculateTotalTime(RegisterListCollection registerListCollection, GroceryStore gs) {
		// TODO Auto-generated method stub
		
		int t = 1;
		//set time to 1 when first customer arrives at the register
		
		
		while(!customerInLine.isEmpty() || registerListCollection.isInUse()) {
			//when the customer line is not empty, or when the register is in use
			int i = 0;
			//set index i to 0 for iteration
			List<Customer> sameTimeArrivalCustomers = new ArrayList<Customer>();
			//retrieve a list of customers that arrive at the check out line at the same time
			
			GroceryStoreProcessor.getSameTimeCustomers(sameTimeArrivalCustomers,t);
			Collections.sort(sameTimeArrivalCustomers);
			registerListCollection.helpCustomer(sameTimeArrivalCustomers);
			
			while (i < registerListCollection.getRegisters().size()) {
				
				Queue<Customer> c = registerListCollection.getRegisters().get(i).getCustomers();
				//get customer list of each register
				
				if (i == registerListCollection.getRegisters().size() - 1) {
					//if the customer ends up at the last register, 
					// it will take 2n minutes to check out
					GroceryStoreProcessor.useTrainee(c);
				}  
				else {
					//else use regular cashier to check out
					GroceryStoreProcessor.useRegular(c);
				}
				i++;
			}
			t++;
		}
		return t;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException, NullPointerException {
		GroceryStore gs = GroceryStoreProcessor.initialize(args);
		RegisterListCollection registerListCollection = gs.getRegisterListCollection();
		int timeTaken = calculateTotalTime(registerListCollection, gs);
		System.out.println("Checkout complete. Total time taken (in minutes): " + timeTaken); 
	}

}
