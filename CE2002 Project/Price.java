package assignment;
/**
 * 	Represents ticket prices for various age groups and rates.
 * @author Ong Hock Rong
 * @version 1.0
 * @version 1.0
 */
public class Price {
	/**
	 * Represents price for senior citizen.
	 */
	private float senior_Price;
	/**
	 * Represents price for adult.
	 */
	private float adult_Price;
	/**
	 * Represents price for children.
	 */
	private float child_Price;
	/**
	 * Represents weekend & holiday rate.
	 */
	private float weekendHoliday_Rate;
	/**
	 * Represents economical rate.
	 */
	private float econ_Rate;
	/**
	 * Represents VIP rate.
	 */
	private float vip_Rate;
	/**
	 * Create a price object with prices (for senior citizen, adult, children) and rates (for weekend, holidays, economical rate and VIP rate).
	 * @param senior_Price Price for senior citizen.
	 * @param adult_Price Price for adult.
	 * @param child_Price Price for children.
	 * @param weekendHoliday_Rate Rate for weekend and holidays.
	 * @param econ_Rate Rate for economical class.
	 * @param vip_Rate Rate for VIP class.
	 */
	public Price(float senior_Price, float adult_Price, float child_Price, float weekendHoliday_Rate, float econ_Rate, float vip_Rate) {
		this.senior_Price = senior_Price;
		this.adult_Price = adult_Price; 
		this.child_Price = child_Price;
		this.weekendHoliday_Rate = weekendHoliday_Rate; 
		this.econ_Rate = econ_Rate;
		this.vip_Rate = vip_Rate;
	}
	/**
	 * Sets the price for senior citizen.
	 * @param price A float containing the price for senior citizen.
	 */ 
	public void setSP(float price) {senior_Price = price;}
	/**
	 * Sets the price for adult.
	 * @param price A float containing the price for adult.
	 */
	public void setAP(float price) {adult_Price = price;}
	/**
	 * Sets the price for children.
	 * @param price A float containing the price for children.
	 */
	public void setCP(float price) {child_Price = price;}
	/**
	 * Sets the rate for weekends and holidays. 
	 * @param price A float containing the rate for weekends and holidays. 
	 */
	public void setWR(float price) {weekendHoliday_Rate = price;}
	/**
	 * Sets the rate for economical class.
	 * @param price A float containing the rate for economical class.
	 */
	public void setER(float price) {econ_Rate = price;}
	/**
	 * Sets the rate for VIP class.
	 * @param price A float containing the rate for VIP class.
	 */
	public void setVR(float price) {vip_Rate = price;}
	/**
	 * Gets the price for senior citizen.
	 * @return A float representing the price for senior citizen.
	 */
	public float getSP() {return senior_Price;}
	/**
	 * Gets the price for adult.
	 * @return A float representing the price for adult.
	 */
	public float getAP() {return adult_Price;}
	/**
	 * Gets the price for children.
	 * @return A float representing the price for children.
	 */
	public float getCP() {return child_Price;}
	/**
	 * Gets the rate for weekends and holidays.
	 * @return A float representing the rate for weekends and holidays.
	 */
	public float getWR() {return weekendHoliday_Rate;}
	/**
	 * Gets the rate for economical class.
	 * @return A float representing the rate for economical class.
	 */
	public float getER() {return econ_Rate;}
	/**
	 * Gets the rate for VIP class.
	 * @return A float representing the rate for VIP class.
	 */
	public float getVR() {return vip_Rate;}
}
