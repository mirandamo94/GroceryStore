import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*The grocery store always has a single cashier in training. 
 * This cashier is always assigned to the highest numbered register.
 * Regular registers take one minute to process each customer's item. 
 * The register staffed by the cashier in training 
 * takes two minutes for each item. So a customer with n items 
 * at a regular register takes n minutes to check out. 
 * However, if the customer ends up at the last register, 
 * it will take 2n minutes to check out.*/

public class Register implements Comparable<Register> {
	
	
	private Integer i;
	private Queue<Customer> cl = null;
	
	public Register(int i) {
		this.i = i;
		cl = new LinkedList<Customer>();
	}
	
	public Integer getI() {
		return i;
	}
	
	public Queue<Customer> getCustomers(){
		return cl;
	}
	
	
	public static Comparator<Register> comp = new Comparator<Register>() {
		@Override
		public int compare(Register r1, Register r2) {
		//returns the smaller lined register
		// TODO Auto-generated method stub
			Integer size1 = r1.cl.size();
			Integer size2 = r2.cl.size();
		return (size1.compareTo(size2));
		}
	};
	
	@Override
	public int compareTo(Register r) {
		return (this.i.compareTo(r.i));
	}
}
