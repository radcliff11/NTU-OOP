package assignment;

import java.util.ArrayList;

/**
 * Represents a Showtime.
 * @author Ng Joshua Jeremiah
 * @author Ong Hock Rong
 * @author Marvin Winson
 * @version 1.0
 * @since   1.0
 */
public class ShowTimes {
	/**
	 * Represents the showtime's title.
	 */
	private String title;
	/**
	 * Represents the showtime's status.
	 */
	private showStatus status;
	/**
	 * Represents the showtime's theater/cinema.
	 */
	private int theater;
	/**
	 * Represents the showtime's cineplex.
	 */
	private String cineplex;
	/**
	 * Represents the showtime's date.
	 */
	private String date;
	/**
	 * Represents the showtime's air time.
	 */
	private int airTime;
	/**
	 * Represents the showtime's film rate.
	 */
	private filmRate filmRate;
	/**
	 * Represents the showtime's class type.
	 */
	private classType cType;
	/**
	 * Represents the showtime's seat arrangement.
	 */
	private String arrangement;
	/**
	 * Represents the separator used for formatting.
	 */
	private String SEPARATOR = ",";
	/**
	 * Creates a showtime with specified title, status, theater/cinema, cineplex name, date, air time, film rate and class type.
	 * @param title The showtime's title.
	 * @param status The showtime's staus.
	 * @param theater The showtime's theater/cinema.
	 * @param cinexplexName The showtime's cineplex name.
	 * @param date The showtime's date.
	 * @param airTime The showtime's air time.
	 * @param fRate The showtime's film rate.
	 * @param cType The showtime's class type.
	 */
	public ShowTimes(String title, showStatus status, int theater, String cinexplexName, String date, int airTime, filmRate fRate, classType cType)
	{
		this.title = title;
		this.status = status;
		this.theater= theater;
		this.cineplex = cinexplexName;
		this.date = date;
		this.airTime = airTime;
		this.cType = cType;
		this.filmRate = fRate;
	}
	/**
	 * Gets the showtime's title.
	 * @return A String representing the showtime's title.
	 */
	public String getTitle() { return title;}
	/**
	 * Gets the showtime's status.
	 * @return A enum representing the showtime's status.
	 */
	public showStatus getShowStatus() { return status;}
	/**
	 * Gets the showtime's theater number.
	 * @return An integer representing the showtime's theater number.
	 */
	public int getTheaterNo() {return theater;}
	/**
	 * Gets the showtime's cineplex.
	 * @return A String representing the showtime's cineplex.
	 */
	public String getCineplex() { return cineplex;}
	/**
	 * Gets the showtime's air time.
	 * @return An integer representing the showtime's air time.
	 */
	public int getAirTime() { return airTime;}
	/**
	 * Gets the showtime's date.
	 * @return A String representing the showtime's date.
	 */
	public String getDate() { return date;}
	/**
	 * Gets the showtime's film rate.
	 * @return An enum representing the showtime's film rate.
	 */
	public filmRate getFilmRate() { return filmRate;}
	/**
	 * Gets the showtime's class type.
	 * @return An enum representing the showtime's class type.
	 */
	public classType getclassType() { return cType;}
	/**
	 * Sets the showtime's film rate.
	 * @param rate An enum containing the showtime's film rate.
	 */
	public void setFilmRate(filmRate rate) 
	{ 
		filmRate = rate;
	}
	/**
	 * Sets the showtime's title.
	 * @param title A String containing the showtime's title.
	 */
	public void setTitle(String title) 
	{ 
		this.title = title;
	}
	/**
	 * Sets the showtime's status.
	 * @param state A String containing the showtime's status.
	 */
	public void setShowStatus(String state)
	{ 
		this.status = showStatus.valueOf(state);
	}
	/**
	 * Sets the showtime's status.
	 * @param st An enum containing the showtime's status.
	 */
	public void setShowStatus(showStatus st)
	{ 
		status = st;
	}
	/**
	 * Sets the showtime's theater number. 
	 * @param n An integer containing the showtime's theater number.
	 */
	public void setTheaterNo(int n) 
	{
		this.theater = n; 
	}
	/**
	 * Sets the showtime's cineplex.
	 * @param name A String containing the showtime's cineplex name.
	 */
	public void setCineplex(String name) 
	{ 
		this.cineplex = name;
	}
	/**
	 * Gets the showtime's seating arrangement.
	 * @return A String representing the showtime's seating arrangement.
	 */
	public String getArrangement() { return arrangement; }
	/**
	 * Sets the showtime's seating arrangement.
	 * @param arrangement A String containing the showtime's seating arrangement.
	 */
	public void setArrangement(String arrangement){ this.arrangement = arrangement;}
	/**
	 * This method updates the seating arrangement based on what seats are being inputed.
	 * Selected seats will be marked as occupied.
	 * @param select A List of String containing seats that are selected by the customer. 
	 */
	public void updateArrangement(ArrayList<String> select) {
		String result ="";
		int length = this.arrangement.indexOf(SEPARATOR);
		char row = 'A';
		boolean joined;

		for(String line: arrangement.split(String.valueOf(SEPARATOR))){
			for(int i=0; i<length; i++){

				joined = false;
				for(String e:select){
					if(e.charAt(0) == row && Integer.parseInt(e.substring(1))-1 == i){
						result = result + "1";
						joined = true;
						break;
					}
				}
				if(!joined){
					result = result+line.charAt(i);
				}

			}
			result = result+SEPARATOR;
			row +=1;
		}
		this.arrangement=result;
	}
	/**
	 * This method checks the validity of the inputed seat.
	 * @param seatNo A String containing the seat to be checked.
	 * @return A Boolean representing the validity of the seat.
	 */
	public boolean checkSeat(String seatNo){
        int row = seatNo.charAt(0)-65;
        int col = Integer.parseInt(seatNo.substring(1));
        return !(this.arrangement.split(SEPARATOR)[row].charAt(col-1) == '1');

    }
	/**
	 * This method gets how many seats in the theater are not occupied.
	 * @return A integer representing the number of unoccupied seats.
	 */
	public int getRemainingSeats()
	{
        int count=0;
        for(String line: this.arrangement.split(SEPARATOR)){
            for(char e: line.toCharArray()){
                if(e=='0'){
                    count++;
                }
            }
        }
        return count;
    }

}
