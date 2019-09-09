import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class RegisterListCollection {

	private List<Register> rl = new ArrayList<Register>();
	
	public RegisterListCollection(int registerNum) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < registerNum; i++) {
			//adding new registers to the list of registers
			rl.add(new Register(i));
		}
	}

	public boolean isInUse() {
		//checking to see if the register is in use with customers, returns true if it is
		//else returns false
		// TODO Auto-generated method stub
		for (Register r: rl) {
			if(r.getCustomers().size() != 0) {
				return true;
				//if there are customers at the register
			}
		}
		return false;
	}

	public List<Register> getRegisters() {
		//returns a list of registers
		// TODO Auto-generated method stub
		return rl;
	}
	
	public void helpCustomer(List<Customer> cl) {
		//help customers based on what type of customer
		//assign registers based on customer type
		
		for (Customer c: cl) {
			if (c.getType().equals(Type.A)) {
				Register shortestLine = getShortestLine();
				shortestLine.getCustomers().offer(c);
				//insert customer into the short line if its type A
			}
			else if(c.getType().equals(Type.B)) {
				Register leastItems = getLeastItems();
				leastItems.getCustomers().offer(c);
				//or put customer with the register that has least amount of items lined up
			}
			else {System.out.println("Invalid customer type");}
		}
	}

	public Register getLeastItems() {
		//looks at the last customer of each register, or an empty register
		// TODO Auto-generated method stub
		
		Map<Customer,Register> map = new HashMap<Customer,Register>();
		//create hash map with customer and register as key, value pairs
		
		List<Register> rl = new ArrayList<Register>();
		List<Customer> items = new ArrayList<Customer>();
		
		for(Register r: rl) {
			if(r.getCustomers().size()==0) {
				//if a register is empty
				rl.add(r);
				}
			else {
				Customer last = getLastCustomer(r.getCustomers());
				//get the number of items of the last customer in a line
				items.add(last);
				map.put(last,r);
				}
		}
		if(rl.size()>0) {
			Collections.sort(rl);
			return rl.get(0);
			//returns if list is sorted
		}
		else {
			Collections.sort(items);
			return map.get(items.get(0));
			//or returns the register which its last customer 
			//has least amount of items
		}
		
	}

	private Customer getLastCustomer(Queue<Customer> customers) {
		// TODO Auto-generated method stub
		//iterate through the list of customers
		//return the last customer in the list
		Customer last = null;
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			last = iterator.next();
		}
		return last;
	}

	public Register getShortestLine() {
		//sort through list of customer lines at the register
		//returns the register with shortest line and assign to type A customers
		// TODO Auto-generated method stub
		
		List<Register> sortedRL = new ArrayList<Register>();
		for(Register r: rl) {
			sortedRL.add(r);
		}
		Collections.sort(sortedRL);
		return sortedRL.get(0);
	}
}
