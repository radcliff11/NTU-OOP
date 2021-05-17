package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Movie.
 * @author Ng Joshua Jeremiah
 * @author Ong Hock Rong
 * @version 1.0
 * @since   1.0
 */

/**
 * Enum representing the movie's show status.
 */
enum showStatus {Coming_Soon, Preview, Now_Showing};
/**
 * Enum representing the movie's film rate.
 */
enum filmRate {G,PG,PG13,NC16, M18, R21};

public class Movie {
	/**
	 * Represents the movie's title.
	 */
	private String title;
	/**
	 * Represents the movie's status.
	 */
	private showStatus status;
	/**
	 * Represents the movie's synopsis.
	 */
	private String synopsis;	
	/**
	 * Represents the movie's director.
	 */
	private String director;		//possible array
	/**
	 * Represents the movie's cast.
	 */
	private String cast;			//possible array
	/**
	 * Represents the movie's average rating.
	 */
	private double avgRate;		//calculate of array object - needed for top 5
	/**
	 * Represents the movie's reviews.
	 */
	private List<Review> review = new ArrayList<Review>();
	/**
	 * Represents the movie's film rating.
	 */
	private filmRate fRate;
	/**
	 * Represents the movie's air date.
	 */
	private String airdate;	//for now, double
	/**
	 * Represents the movie's duration.
	 */
	private int duration;	//in h format
	/**
	 * Creates a movie with specified title, synopsis, director, cast, film rate, air date and duration.
	 * @param title The movie's title.
	 * @param synopsis The movie's synopsis.
	 * @param director The movie's director.
	 * @param cast The movie's cast.
	 * @param fRate The movie's film rate.
	 * @param airdate The movie's air date.
	 * @param duration The movie's duration.
	 */
	public Movie(String title, String synopsis, String director, String cast, filmRate fRate, String airdate, int duration)
	{
		this.title = title;
		status = showStatus.Coming_Soon;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		avgRate=0 ;
		this.fRate = fRate;
		this.airdate = airdate;
		this.duration = duration;
	}
	/**
	 * Gets the movie's title.
	 * Replace ";" with "," as coma is used as next line in csv file.
	 * @return A String representing the movie's title.
	 */
	public String getTitle() {return title.replaceAll(";", ",");}
	/**
	 * Gets the movie's status.
	 * @return An enum representing the movie's status.
	 */
	public showStatus getStatus() {return status;}
	/**
	 * Gets the movie's synopsis.
	 * @return A String representing the movie's synopsis.
	 */
	public String getSynopsis() {return synopsis.replaceAll(";", ",");}
	/**
	 * Gets the movie's director.
	 * @return A String representing the movie's director.
	 */
	public String getDirector() {return director.replaceAll(";", ",");}
	/**
	 * Gets the movie's cast.
	 * @return A String representing the movie's cast.
	 */
	public String getCast() {return cast.replaceAll(";", ",");}
	/**
	 * Gets the movie's film rate.
	 * @return An enum representing the movie's film rate.
	 */
	public filmRate getFilmRate() {return fRate;}
	/**
	 * Gets the movie's average rating.
	 * @return A double representing the movie's average rating.
	 */
	public double getAvgRate() {return avgRate;}
	/**
	 * Gets the movie's air date.
	 * @return A String representing the movie's air date.
	 */
	public String getAirDate() {return airdate;}
	/**
	 * Gets the movie's duration.
	 * @return An integer representing the movie's duration.
	 */
	public int getDuration() {return duration;}//*//
	/**
	 * Sets the movie's title.
	 * @param title A String containing the movie's title.
	 */
	public void setTitle(String title) {this.title = title;}
	/**
	 * Sets the movie's status.
	 * @param state An enum containing the movie's status.
	 */
	public void setStatus(String state)
	{
		this.status = showStatus.valueOf(state);
	}
	/**
	 * Sets the movie's status.
	 * @param state An integer containing the movie's status.
	 */
	public void setStatus(int state) 
	{
		switch(state)
		{
			case 1: this.status = showStatus.Coming_Soon; break;
			case 2: this.status = showStatus.Preview; break;
			case 3: this.status = showStatus.Now_Showing; break;
			default: System.out.println("Invalid input");
		}
	}
	/**
	 * Sets the movie's synopsis.
	 * @param synopsis A String containing the movie's synopsis.
	 */
	public void setSynopsis(String synopsis) {this.synopsis = synopsis;}
	/**
	 * Sets the movie's directors.
	 * @param director A String containing the movie's directors.
	 */
	public void setDirector(String director) {this.director = director;}
	/**
	 * Sets the movie's cast.
	 * @param cast A String containing the movie's cast.
	 */
	public void setCast(String cast) {this.cast = cast;}
	/**
	 * Sets the movie's film rate.
	 * @param fRate An integer containing the movie's film rate.
	 */
	public void setfRate(int fRate) 
	{
		switch(fRate)
		{
			case 1: this.fRate = filmRate.G; break;
			case 2: this.fRate = filmRate.PG; break;
			case 3: this.fRate = filmRate.PG13; break;
			case 4: this.fRate = filmRate.NC16; break;
			case 5: this.fRate = filmRate.M18; break;
			case 6: this.fRate = filmRate.R21; break;
			default: System.out.println("Invalid input");
		}
	}
	/**
	 * Set the movie's film rate.
	 * @param fRate A String containing the movie's film rate.
	 */
	public void setfRate(String fRate)
	{
		this.fRate = filmRate.valueOf(fRate);
	}
	/**
	 * Set the movie's air date.
	 * @param date A String containing the movie's date.
	 */
	public void setAirDate(String date)
	{
		if(date.charAt(2) == '/' && date.charAt(5)== '/' && date.length() == 10)
		{
			this.airdate = date;
		}
		else
			System.out.println("Invalid Input");
	}
	/**
	 * Set the movie's duration.
	 * @param duration An integer containing the movie's duration.
	 */
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	/**
	 * Set the movie's average rating.
	 * Looks through the list of reviews's rating to calculate the movie's average rating.
	 * @param review A list of review objects containing the respective rating of each review.
	 */
	public void setAvgRate(List<Review> review)
	{
		double avgRating = 0;
		if (review != null)
		{
			for(int i=0; i<review.size();i++)
			{
				if(review.size()< 2)
					break;
				avgRating += review.get(i).getRating();
			}
			if (avgRating != 0)
				avgRate = (double) Math.round((avgRating / review.size()) * 10) / 10;
		}
		else
		{
			System.out.println("No user review yet.");
			avgRate = avgRating;
		}
			
	}
	/**
	 * Set the movie's average rating.
	 * @param userRate A String containing the average rating of the movie.
	 */
	public void setAvgRate(String userRate)
	{
		this.avgRate=Double.parseDouble(userRate);
	}
	/**
	 * This method prints the user Id, rating and comments of each review in the movie.
	 */
	public void printReview()
	{
		if(review != null )
		{
			for(int i=0; i < review.size(); i++)
			{
				while(review.get(i) != null)
				{
					System.out.println("UserID: " + review.get(i).getUser() + "/tUser Rating: " + review.get(i).getRating());
					System.out.println("Comment:/n" + review.get(i).getComment());
				}
			}
		}
		else
			System.out.println("No user review yet.");
	}
	/**
	 * Gets the list of review objects in the movie.
	 * @return A list representing the reviews of the movie.
	 */
	public List<Review> getReview()
	{
		return review;
	}
	/**
	 * Adds a review for the movie.
	 * @param x A review object containing the review to be added.
	 */
	public void addReview(Review x)
	{
		review.add(x);
	}
	/**
	 * Sets the movie's reviews.
	 * @param r A list containing the reviews of the movie.
	 */
	public void setReview(List<Review> r)
	{
		this.review = r;
	}
	
	
}
