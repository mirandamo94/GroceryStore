
/*
 * Customer class
 * 
 * Two types of customers arrive at the registers:
a. Customer Type A always chooses the register with the shortest line 
	(fewest number of customers in line).
b. Customer Type B looks at the last customer in each line, 
	and always chooses to be behind the customer with the 
	fewest number of items left to check out, regardless of
  	how many other customers are in the line or how many items they have. 
  	Customer Type B will always choose an empty line 
  	before a line with any customers in it.
  	
Customers just finishing checking out do not count as being in line 
(for either kind of Customer). 
If two or more customers arrive at the same time, 
those with fewer items choose registers before those with more, 
and if they have the same number of items then type A's choose before type B's.
 */

public class Customer implements Comparable<Customer>{

	private boolean isHelped; //if the customer is currently being helped at register
	private Type customerType; //type of customer, A or B
	private int arrivalTime; //arrival time of the customer
	private Integer itemNum; //number of items that customer has
	
	public Customer (Integer itemNum, Type customerType, int arrivalTime) {
		//constructor
		this.itemNum = itemNum;
		this.customerType = customerType;
		this.arrivalTime = arrivalTime;
	}
	
	//getters and setters
	public boolean isBeingHelped() {
		return isHelped;
	}
	
	public void setIsBeingHelped(boolean isHelped) {
		this.isHelped = isHelped;
	}
	
	public int getItemNum() {
		return itemNum;
	}
	
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	public Type getType() {
		return customerType;
	}
	
	public void setType(Type customerType) {
		this.customerType = customerType;
	}
	
	public Integer itemsInCart() {
		return --this.itemNum;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	@Override
	public int compareTo(Customer c) {
		// TODO Auto-generated method stub
		int comp  = 0;
		comp  = this.itemNum.compareTo(c.itemNum);
		//compare number of items they have
		if (comp == 0) {
			comp = this.customerType.compareTo(c.customerType);
			//if number of items in cart are the same, compare customer type
			}
		return comp;

	}
}