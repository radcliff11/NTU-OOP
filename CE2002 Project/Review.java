package assignment;

/**
 * Represents a Review.
 * @author Ng Joshua Jeremiah
 * @version 1.0
 * @since   1.0
 */
public class Review {
	/**
	 * Represents the rating of the review.
	 */
	private double rating;
	/**
	 * Represents the comments that user enters.
	 */
	private String comment;
	/**
	 * Represents the Id of the customer that wrote the review.
	 */
	private String custID;
	/**
	 * Creates a Review with specified rating, comment and customer ID.
	 * @param rating The review's rating.
	 * @param comment The review's comment.
	 * @param custID The Id of the customer that wrote the review.
	 */
	public Review(double rating, String comment, String custID)
	{
		this.rating = rating;
		this.comment = comment;
		this.custID = custID;
	}
	/**
	 * Gets the review's rating.
	 * @return A double representing the review's rating.
	 */
	public double getRating() {return rating;}
	/**
	 * Gets the review's comment.
	 * @return A String representing the review's comment.
	 */
	public String getComment() {return comment.replaceAll(";", ",");}
	/**
	 * Gets the user's customer Id.
	 * @return A String representing the user who wrote the review.
	 */
	public String getUser() {return custID;}
		
}
