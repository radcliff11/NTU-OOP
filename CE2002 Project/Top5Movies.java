package assignment;

/**
 * Represents a Review.
 * @author Ng Joshua Jeremiah
 * @version 1.0
 * @since   1.0
 */
public class Top5Movies{
	/**
	 * Represents the top5movies's title.
	 */
	private String title;
	/**
	 * Represents the top5movies's criteria.
	 */
	private double criteria;
	/**
	 * Creates a top5movies with specified title and criteria.
	 * @param title The top5movies's title.
	 * @param criteria The top5movies's criteria.
	 */
	public Top5Movies(String title, double criteria)
	{
		this.title=title;
		this.criteria=criteria;
	}
	/**
	 * Sets the top5movies's title.
	 * @param t A String containing the top5movies's title.
	 */
	public void settitle(String t)
	{
		title=t;
	}
	/**
	 * Sets the top5movies's criteria.
	 * @param c A double containing the top5movies's criteria.
	 */
	public void setcriteria(double c)
	{
		criteria=c;
	}
	/**
	 * Gets the top5movies's title.
	 * @return A String representing the top5movies's title.
	 */
	public String gettitle()
	{
		return title;
	}
	/**
	 * Gets the top5movies's criteria.
	 * @return A double representing the top5movies's criteria.
	 */
	public double getcriteria()
	{
		return criteria;
	}
}
