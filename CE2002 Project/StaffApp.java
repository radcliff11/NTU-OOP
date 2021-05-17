package assignment;

import java.util.Scanner;
/**
 * Movie Staff Admin Menu
 * The StaffApp program implements an application that allows admin staffs to make changes to databases and also view
 * the Top 5 Movies list by customer rating or by total sales.
 * @author Ong Hock Rong
 * @version 1.0
 * @since 1.0
 */
public class StaffApp {
	/**
	 * This is the main method which make use of the above created methods.
	 * Admin staff will not be able to use any movie admin functions unless he/she is logged in.
	 * @param args Unused.
	 */
	public static void main(String[] args)
	{
		/**
		 * Represents the choice chosen from the displayed menus.
		 */
		int choice=0;
		
		System.out.println("Console Movie Booking System (Admin Mode)");
		System.out.println("=========================================");
		
		StaffManager staffMgr = new StaffManager();
		staffMgr.loginPage();
		staffMgr.printStaffMainMenu();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your selection:");
		choice = sc.nextInt();
		while (choice != 8)
		{
			sc.nextLine();
			if (choice == 1)
			{		
				staffMgr.configTicketPrices();	                
			}
			else if (choice == 2)
			{
				staffMgr.configHolidayDates();
			}
			else if (choice == 3)
			{
				staffMgr.addNewMovie();
			}
			else if (choice == 4)
			{
				staffMgr.movie_EditRemove();
			}
			else if (choice == 5)
			{
				staffMgr.listTop5();
			}
			else if (choice == 6)
			{
				staffMgr.allocateShowTime();
			}
			else if(choice == 7)
			{
				staffMgr.addNewUser();
			}
			else
			{
				System.out.println("Invalid choice");
			}
			staffMgr.printStaffMainMenu();
			System.out.println("Please enter your selection:");
			choice = sc.nextInt();
		}
		sc.close();
	}
}