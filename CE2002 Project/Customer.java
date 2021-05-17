package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Customer.
 * @author Ng Joshua Jeremiah
 * @version 1.0
 * @since   1.0
 */
public class Customer {
	/**
	 * Represents the customer's Id.
	 */
	private String custID;
	/**
	 * Represents the customer's email.
	 */
	private String email;
	/**
	 * Represents the customer's mobile number.
	 */
	private int mobileNo;
	/**
	 * Represents the transaction/booking history of the customer.
	 */
	private List<String> transHist = new ArrayList<String>();
	/**
	 * Creates a customer with specified customer Id, email and mobile number.
	 * @param custID The customer's identity number.
	 * @param email The customer's email.
	 * @param mobileNo The customer's mobile number.
	 */
	public Customer (String custID, String email, int mobileNo)
	{
		this.custID = custID;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	/**
	 * Gets the customer's Id.
	 * @return A String representing the customer's Id.
	 */
	public String getCustID() {return custID;}
	/**
	 * Gets the customer's email.
	 * @return A String representing the customer's email.
	 */
	public String getCustEmail() {return email;}
	/**
	 * Gets the customer's mobile no.
	 * @return An integer representing the customer's mobile number.
	 */
	public int getMobileNo() {return mobileNo;}
	/**
	 * Gets the customer's transaction history.
	 * @return A list of transactions representing the customer's transaction history.
	 */
	public List<String> getTransHist()
	{
		return transHist;
	}
	/**
	 * Adds a transaction to the customer's transaction history.
	 * @param trans A String containing a new transaction Id.
	 */
	public void addtransaction(String trans)
	{
		transHist.add(trans);
	}
	/**
	 * Converts list of String into a singular String for data storage.
	 * @return A String representing the list of transactions.
	 */
	public String list2String()
	{
		String temp = transHist.toString().replaceAll("\\[|\\]", "").replaceAll(",", "#");
		return temp;
	}
}
