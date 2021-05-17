package assignment;

import java.text.ParseException;
import java.util.Scanner;


/**
* The Cinema Booking program implements an application that allows the user to book a movie, write a review, view movie details, view top 5 movies and view transaction history.
* @author  Ng Joshua Jeremiah
* @author Marvin Winson
* @version 1.0
* @since   1.0
*/
public class CustomerApp {
    
	/**
	 * This is the main method that makes use of the above methods and allows the user to choose from the main menu.
	 * @param args Unused.
	 * @throws ParseException Constructs a ParseException with the specified detail message and offset.
	 */
	public static void main(String[] args) throws ParseException {
		
		int choice;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Console Movie Booking System");
		System.out.println("============================");
		CustomerManager cm = new CustomerManager();
		
		choice = cm.printMainMenu();
		
		while (choice!=6)
		{
			if (choice==1)
			{
				cm.mainbooking();
			}
			else if (choice==2)
			{
				cm.writeReview();
			}
			else if (choice==3)
			{
				cm.printMovieDetails();
			}
			else if (choice==4)
			{
				cm.printTop5Movies();
			}
			else if (choice==5)
			{
				cm.printBooking();
			}
			else
			{
				System.out.println("Invalid choice");
			}
			
			choice = cm.printMainMenu();
		}
		
		sc.close();
	}
	
}


