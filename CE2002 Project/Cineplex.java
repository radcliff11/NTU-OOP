package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Seat.
 * @author Ng Joshua Jeremiah
 * @author Ong Hock Rong
 * @version 1.0
 * @since   1.0
 */
public class Cineplex {
	/**
	 * Represents the cineplex's name.
	 */
	private String name;
	/**
	 * Represents the cineplex's location.
	 */
	private String location;
	/**
	 * Represents the list of cinemas in the cineplex.
	 */
	private List<Cinemas> cinema;
	/**
	 * Creates a cineplex with specified name and location.
	 * @param name The cineplex's name.
	 * @param location The cineplex's location.
	 */
	public Cineplex(String name, String location)
	{
		this.name = name;
		this.location = location;
	}
	/**
	 * Gets the cineplex's name.
	 * @return A String representing the cineplex's name.
	 */
	public String getName() {return name;}
	/**
	 * Gets the cineplex's location.
	 * @return A String representing the cineplex's location. 
	 */
	public String getLocation() {return location;}
	/**
	 * Gets the list of cinemas in the cineplex.
	 * @return A list representing the cinemas in the cineplex.
	 */
	public List<Cinemas> getCinemas() {return cinema;}
	/**
	 * Adds a cinema into the list of cinemas in the cineplex.
	 * @param c A cinema object representing the cinema to be added.
	 */
	public void addCinemas(Cinemas c) 
	{
		if(cinema==null) cinema = new ArrayList<Cinemas>();
		cinema.add(c);
	}
}