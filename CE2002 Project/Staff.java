package assignment;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the Staff.
 * @author Ong Hock Rong
 * @version 1.0
 * @since 1.0
 */
public class Staff {
	/**
	 * Represents login status for the staff. 
	 */
	private boolean isLogin = false;
	/**
	 * Represents login userID for the staff.
	 */
	private String userID;
	/**
	 * Represents login password for the staff.
	 */
	private char[] password;
	/**
	 * Create a staff with specified userID and password.
	 * @param userID	Staff's login id.
	 * @param password	Staff's login password.
	 */
	public Staff(String userID, char[] password)
	{
		this.userID = userID;
		this.password = password;
	}
	/**
	 * Gets the staff's userID.
	 * @return A string representing the staff's userID.
	 */
	public String getUserID(){ return userID;}
	/**
	 * Gets the staff's password.
	 * @return A char array representing the staff's password.
	 */
	public char[] getPassword(){ return password;}
	/**
	 * This method is to validate if user input of id and password match with the database.
	 * @param staffDB List of staffs' login information.
	 * @return A boolean true or false which indicate login status.
	 */
	public boolean checkPassword(List<Staff> staffDB)
	{
		if(staffDB != null)
		{
			for(int i=0; i< staffDB.size();i++)
			{
				String user_db = staffDB.get(i).getUserID();
				char[] pw_db = staffDB.get(i).getPassword();
				if(user_db.equals(userID) && Arrays.equals(pw_db,password))
				{
					isLogin = true;
					break;
				}
				else
					isLogin = false;
			}
		}
		return isLogin;	
	}
	/**
	 * Gets the current login status.
	 * @return A boolean to indicate login status.
	 */
	public boolean getStatus() {return isLogin;} 
	/**
	 * Sets the staff's userID.
	 * @param id A string containing the staff's userID.
	 */
	public void setUserID(String id)
    { 
        userID = id;
    }
	/**
	 * Sets the staff's password.
	 * @param pw A char array containing the staff's password.
	 */
    public void setPassword(char[] pw)
    { 
        password = pw;
    }
}
