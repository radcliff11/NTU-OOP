package assignment;

import java.util.ArrayList;
import java.util.List;
/**
 * Representing the cinema/theater.
 * @author Ong Hock Rong
 * @author Ng Joshua Jeremiah
 * @version 1.0
 * @since 1.0
 *
 */
public class Cinemas {
	/**
	 * Represents the cinema's class type.
	 */
	private classType cType;
	/**
	 * Represents the cinema/theater's id.
	 */
	private int theaterNo;
	/**
	 * Represents the sea capacity of the cinema/threater.
	 */
	private int numSeats;
	/**
	 * Represents the list of seats in the cinema/theater, identified by rows and columns. 
	 */
	private List <Seat> seatPos = new ArrayList<Seat>();
	/**
	 * Create a cinema/theater with the specified class type, theater id and seat capacity.
	 * @param cType The cinema/theater's class type.
	 * @param theaterNo The cinema/theater's theater id.
	 * @param numSeats The cinema/theater's seat capacity.
	 */
	public Cinemas(classType cType, int theaterNo, int numSeats)
    {

		this.cType = cType;
        this.theaterNo = theaterNo;
        this.numSeats=numSeats;

    }
	/**
	 * Gets the theater id of the cinema/theater.
	 * @return A integer containing the theater id of the cinema/theater.
	 */
	public int geTheaterNo() {return theaterNo;}
	/**
	 * Gets the class type of the cinema/theater.
	 * @return A enum containing the class type of the cinema/theater.
	 */
	public classType getCType() {return cType;}
	/**
	 * Get the seat capacity of the cinema/theater.
	 * @return A integer containing the seat capacity of the cinema/theater.
	 */
	public int getnumSeats() {return numSeats;}
	/**
	 * Gets the list of seat objects of the cinema/theater.
	 * @return A list of seat objects of the cinema/theater.
	 */
	public List<Seat> getseatPos() {return seatPos;}
	/**
	 * Convert a list of seat objects and its isOccupied status into a singular string for data storage.
	 * @return A string representing the list of seat objects and its isOccupied status.
	 */
	public String seat2String() 
	{
		int i=0;
		StringBuilder sb = new StringBuilder();
		for(Seat s : seatPos)
		{
			sb.append(s.getrow());
			sb.append(s.getno());
			sb.append("%");
			sb.append(s.getOccupied());
			i++;
			if(i < seatPos.size()) sb.append("_");
		}
		String temp = sb.toString();

		return temp;
	}
	/**
	 * Sets the list of seat objects with its attributes.
	 * @param seats A list of seat objects containing its attributes.
	 */
	public void setSeats(List<Seat> seats) {
		this.seatPos = seats;
	}
	/**
	 * Convert a list of seat objects into a singular string based on its isOccupied status. 
	 * @return A string containing all the seats' isOccupied status.
	 */
	public String seat2Arrange()
	{
		char temp = seatPos.get(0).getrow();
		StringBuilder sb = new StringBuilder();
		for(Seat s : seatPos)
		{
			char cur = s.getrow();
			if(cur!=temp)
			{
				sb.append(";");
				temp = cur;
			}
			if(s.getOccupied()) sb.append(1);
			else sb.append(0);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	/**
	 * Adds the seat object to the list of seat objects.
	 * @param s A seat object to be added.
	 */
	public void addSeat(Seat s)
    {
        seatPos.add(s);
    }

}
