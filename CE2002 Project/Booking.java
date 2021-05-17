package assignment;


/**
 * Represents a Seat.
 * @author Ng Joshua Jeremiah
 * @author Ong Hock Rong
 * @version 1.0
 * @since   1.0
 */
public class Booking {
	/**
	 * Represents the booking's email.
	 */
	private String email;
	/**
	 * Represents the booking's mobile.
	 */
	private int mobile;
	/**
	 * Represents the booking's transaction Id.
	 */
	private String transactionID;
	/**
	 * Represents the booking's movie title.
	 */
	private String movieTitle;
	/**
	 * Represents the booking's movie date.
	 */
	private String movieDate;
	/**
	 * Represents the booking's cineplex name.
	 */
	private String cineplexName;
	/**
	 * Represents the booking's cinema number.
	 */
	private int cinemasNo;
	/**
	 * Represents the booking's location.
	 */
	private String location;
	/**
	 * Represents the booking's movie time.
	 */
	private int movieTime;
	/**
	 * Represents the booking's total price.
	 */
	private double totalPrice;
	/**
	 * Represents the booking's film rate.
	 */
	private filmRate fRate;
	/**
	 * Represents the booking's number of tickets.
	 */
	private int noOfTix;
	/**
	 * Represents the booking's seats.
	 */
	private String seats;
	
	/**
	 * Creates a booking with specified email, mobile, transaction Id, movie title, movie date, cineplex name, cinema number, location, movie time, total price, film rate, number of tickets and seats.
	 * @param email The booking's email.
	 * @param mobile The booking's mobile.
	 * @param transactionID The booking's transaction Id.
	 * @param movieTitle The booking's movie title.
	 * @param movieDate The booking movie date.
	 * @param cineplexName The booking's cineplex name.
	 * @param cinemasNo The booking's cinema number.
	 * @param location The booking's location.
	 * @param movieTime The booking's movie time.
	 * @param totalPrice The booking's total price.
	 * @param fRate The booking's film rate.
	 * @param noOfTix The booking's number of tickets.
	 * @param seats The booking's seats.
	 */
	public Booking(String email, int mobile, String transactionID, String movieTitle, String movieDate, String cineplexName, int cinemasNo, String location, int movieTime, double totalPrice, filmRate fRate, int noOfTix, String seats)
	{
		this.email = email;
		this.mobile = mobile;
		this.transactionID = transactionID;
		this.movieTitle = movieTitle;
		this.movieDate = movieDate;
		this.cineplexName = cineplexName;
		this.cinemasNo = cinemasNo;
		this.location = location;
		this.movieTime = movieTime;
		this.totalPrice = totalPrice;
		this.fRate=fRate;
		this.noOfTix=noOfTix;
		this.seats=seats;
	}
	/**
	 * This method is used to print the booking's/Transaction's details.
	 */
	public void printTransaction()
	{
		System.out.println("Transaction: " + transactionID+ " complete.");
		System.out.println("Cineplex: " + cineplexName + ", " + location);
		System.out.println("Movie: " + movieTitle + " (" + fRate + ")");
		System.out.println("Cinema no: " + cinemasNo + "    Time: " + movieTime);
		System.out.println("Date: " + movieDate + "   " + seats);
		System.out.println("Total Price: $" + totalPrice);
	}
	/**
	 * Gets the booking's email.
	 * @return A String representing the booking's email.
	 */
	public String getemail() {return email;}
	/**
	 * Gets the booking's mobile.
	 * @return An integer representing the booking's mobile.
	 */
	public int getmobile() {return mobile;}
	/**
	 * Gets the booking's transaction Id. 
	 * @return A String representing the booking's transaction Id.
	 */
	public String gettransactionID() {return transactionID;}
	/**
	 * Gets the booking's movie title.
	 * @return A String representing the booking's movie title.
	 */
	public String getmovieTitle() {return movieTitle;}
	/**
	 * Gets the booking's movie date.
	 * @return A String representing the booking's movie date.
	 */
	public String getmovieDate() {return movieDate;}
	/**
	 * Gets the booking's cineplex name.
	 * @return A String representing the booking's cineplex name.
	 */
	public String getcineplexName() {return cineplexName;}
	/**
	 * Gets the booking's cinema number.
	 * @return An integer representing the booking's cinema number.
	 */
	public int getcinemasNo() {return cinemasNo;}
	/**
	 * Gets the booking's location.
	 * @return A String representing the booking's location.
	 */
	public String getlocation() {return location;}
	/**
	 * Gets the booking's movie time.
	 * @return An integer representing the booking's movie time.
	 */
	public int getmovieTime() {return movieTime;}
	/**
	 * Gets the booking's total price.
	 * @return A double representing the booking's total price.
	 */
	public double gettotalPrice() {return totalPrice;}
	/**
	 * Gets the booking's film rate.
	 * @return An enum representing film rate.
	 */
	public filmRate getfRate() {return fRate;}
	/**
	 * Gets the booking's number of tickets.
	 * @return An integer representing the booking's number of tickets.
	 */
	public int getnoOfTix() {return noOfTix;}
	/**
	 * Gets the booking's seats.
	 * @return A String representing the booking's seats.
	 */
	public String getseats() {return seats;}
	/**
	 * Sets the booking's title.
	 * @param title A String containing the booking's title.
	 */
	public void setTitle(String title)
	{
		movieTitle = title;
	}
	/**
	 * Sets the booking's film rate.
	 * @param rate An enum containg the booking's film rate.
	 */
	public void setfRate(filmRate rate) 
	{
		fRate = rate;
	}
}
