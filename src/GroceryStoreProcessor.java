import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

public class GroceryStoreProcessor {
	//processes the customers based on type and assign customers to registers
	//based on number of items, and customer type
	
	public static Queue<Customer> customerLine = GroceryStore.getCustomerInLine();
	private static BufferedReader br;

	public static GroceryStore initialize(String[] args) throws FileNotFoundException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		GroceryStore gs = null;
		RegisterListCollection rl = null;
		
		String currline = "";
		br = new BufferedReader( new FileReader(new File("groceryStoreInput.txt")));
		String firstLine = br.readLine();
		int registerNum  = Integer.parseInt(firstLine);
		//read the first line from text file and assign that to registerNum 
		//checker: System.out.println(registerNum);
		
		rl = new RegisterListCollection(registerNum);
		//puts the number of register into register list
		
		while ((currline = br.readLine()) != null){
			//read from second line to the end, create customer profiles
			//System.out.println(currline);
			Customer c = createCustomer(currline);
			customerLine.offer(c); //insert customer to line
			
	}
		gs = new GroceryStore(rl);
		//create new grocery store with the register list
		return gs;
	}

	public static Customer createCustomer(String currline) {
		//create customers based on the input of text file
		//parsing integers and characters into proper fields.
		// TODO Auto-generated method stub
		String[] items = currline.split(" ");
		
		if(items[0].equals("A")) {
			System.out.println("New Customer detected. type A, arrival time: "
								+Integer.parseInt(items[1])+", number of items: "+ Integer.parseInt(items[2]));
			return new Customer(Integer.parseInt(items[2]), Type.A, Integer.parseInt(items[1]));
		}
		else if(items[0].equals("B")) {
			System.out.println("New Customer detected. type B, arrival time: "
					+Integer.parseInt(items[1])+", number of items: "+ Integer.parseInt(items[2]));
			return new Customer(Integer.parseInt(items[2]), Type.B, Integer.parseInt(items[1]));
		}
		else {
			System.out.println("No valid customer type detected");
			System.exit(-1);
			return null;
			}// if its neither A or B
		
	}

	public static void getSameTimeCustomers(List<Customer> sameTimeArrivalCustomers, int t) {
		// TODO Auto-generated method stub
		Customer c = customerLine.peek(); 
		//look at the last element/customer of the customer line
		
		while (c != null && c.getArrivalTime() == t) {
			//if there is a customer with the same arrival time
			
			sameTimeArrivalCustomers.add(customerLine.poll());
			//add to the same arrival customer list
			
			c = customerLine.peek();
			//check the last customer again until there are no more customers
		}
	}

	public static void useTrainee(Queue<Customer> c) {
		// TODO Auto-generated method stub
		Customer c1 = c.peek(); //look at the last customer
		
		if (c1 != null) {
			
			if (!c1.isBeingHelped()) { 
				c1.setIsBeingHelped(true);
				}
			else if (c1.isBeingHelped()) {
				
				if (c1.itemsInCart() == 0) {
					c.poll();
					} //delete customer when no items in cart
				
				else {
					c1.setIsBeingHelped(false);
				} //use this to run through the if statement again
				//to implement the double time to complete check out
			}
			
		}
	}

	public static void useRegular(Queue<Customer> c) {
		// TODO Auto-generated method stub
		Customer c1 = c.peek();
		if (c1 != null && c1.itemsInCart() == 0) {
			c.poll();
			//System.out.println("check out by regular cashier complete");
			//delete customer after there are no more items in cart
			}
	}

}
