package assignment;


/**
 * Represents a Seat.
 * @author Ng Joshua Jeremiah
 * @version 1.0
 * @since   1.0
 */
public class Seat {
	/**
	 * Represents the seat's row.
	 */
	private char row;
	/**
	 * Represents the seat's number.
	 */
	private int no;
	/**
	 * Represents the seat's occupied status.
	 */
	private boolean isOccupied;
/**
 * Creates a seat with specified row and number.
 * Occupied Status is default set to false upon creation.
 * @param row The seat's row.
 * @param no The seat's number.
 */
	public Seat(char row, int no)
	{
		this.row=row;
		this.no=no;
		isOccupied=false;
	}
	/**
	 * Creates a seat with specified name and occupied status.
	 * Splits the string into row, number occupied status using character manipulation.
	 * Sets the row, number and occupied status.
	 * @param s The seat's name, includes row and number, and occupied status.
	 */
	public Seat(String s)
	{
		String[] id = s.split("%");
		this.row = id[0].charAt(0);
		int len = id[0].length();
		String s1 = id[0].substring(1,len); 
		this.no = Integer.parseInt(s1);
		isOccupied= Boolean.getBoolean(id[1]);
	}
	/**
	 * Gets the seat's row.
	 * @return A character representing the seat's row.
	 */
	public char getrow()
	{
		return row;
	}
	/**
	 * Gets the number's number.
	 * @return A integer representing the seat's number.
	 */
	public int getno()
	{
		return no;
	}
	/**
	 * Gets the occupancy status of the seat.
	 * @return A boolean representing the occupancy status of the seat.
	 */
	public boolean getOccupied()
	{
		return isOccupied;
	}
	/**
	 * Sets the row of the seat.
	 * @param row A character containing the seat's row.
	 */
	public void setrow(char row)
	{
		this.row=row;
	}
	/**
	 * Sets the number of the seat.
	 * @param no A integer containing the seat's number.
	 */
	public void setno(int no)
	{
		this.no=no;
	}
	/**
	 * Sets the occupancy status of the seat.
	 * @param occupied A boolean containing the seat's occupancy status.
	 */
	public void setOccupied(boolean occupied)
	{
		isOccupied=occupied;
	}
}
