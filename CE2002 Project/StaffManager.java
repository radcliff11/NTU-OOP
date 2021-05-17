package assignment;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
/**
 * This manager class acts a control for the movie, showtime and booking classes. 
 * @author Ong Hock Rong
 * @version 1.0
 * @since 1.0
 */
public class StaffManager {
	/**
	 * Create a ConnectDB object that will be used as a link to read and write to databases throughout the program.
	 */
	ConnectDB admin = new ConnectDB();
	/**
	 * Create a Properties object that will be used to get databases filenames from the configuration file.
	 */
	Properties prop= admin.db_Init();
	/**
	 * Gets the filename of the Changelog file and sets as a constant string.
	 */
	private final String CHANGELOG_DB = prop.getProperty("DB.changelog");
	/**
	 * Gets the filename of the PriceRate file and sets as a constant string.
	 */
	private final String PRICERATE_DB = prop.getProperty("DB.pricerate");
	/**
	 * Gets the filename of the Booking file and sets as a constant string.
	 */
	private final String BOOKING_DB = prop.getProperty("DB.booking");
	/**
	 * Gets the filename of the Cineplex file and sets as a constant string. 
	 */
	private final String CINEPLEX_DB = prop.getProperty("DB.cineplex");
	/**
	 * Gets the filename of the Movielist file and sets as a constant string.
	 */
	private final String MOVIELIST_DB = prop.getProperty("DB.movie");
	/**
	 * Gets the filename of the ShowTime file and sets as a constant string. 
	 */
	private final String SHOWTIME_DB = prop.getProperty("DB.showtime");
	/**
	 * Gets the filename of the Holiday2019 file and sets as a constant string.
	 */
	private final String HOLIDAY_DB = prop.getProperty("DB.holiday");
	/**
	 * Gets the filename of the StaffDB file and sets as a constant string.
	 */
	private final String STAFF_DB = prop.getProperty("DB.staff");
	/**
	 * Create a  Scanner object that will be used to get user inputs throughout the program.
	 */
	 Scanner sc = new Scanner(System.in);
	/**
	 * This method is used to print the selection menu for admin staff to change the system settings.
	 */
	public void printStaffMainMenu ()
	{	
		System.out.println("================ Configure system settings ================");
		System.out.println("Please select settings that you want to change:");
		System.out.println("1. Ticket price");
		System.out.println("2. Holiday dates");
		System.out.println("3. Add movies");
		System.out.println("4. Edit/Remove movie");
		System.out.println("5. Select top 5 movies");
		System.out.println("6. Allocation of movies to Cinemas:");
		System.out.println("7. Add new Staff user");
		System.out.println("8. Log Out");
	}
	/**
	 * This method is used to print the selection menu for admin staff to change the prices and rates.
	 */
	private void printTicketPriceSettingsMenu ()
    {
        System.out.println("Please select which ticket price to change");
        System.out.println("1. Senior Citizen Price");
        System.out.println("2. Adult Price");
        System.out.println("3. Child Price");
        System.out.println("4. Weekend Rate");
        System.out.println("5. Economical Rate");
        System.out.println("6. VIP Rate");
    }
	/**
	 * This method is used to print the selection menu for admin staff to add/remove holidays.
	 */
	private void printHolidaySettingsMenu ()
	{
		System.out.println("1. Add holiday");
		System.out.println("2. Remove holiday");
	}
	/**
	 * This method is used to print the details of a movie.
	 * @param m A movie object containing the details of a movie.
	 */
	private void printAttributeList(Movie m)
	{
		System.out.println("************************************************");
		System.out.println("Detail as follow:");
		System.out.println("1. Title: " + m.getTitle());
		System.out.println("2. Status: " + m.getStatus());
		System.out.println("3. Synopsis: " + m.getSynopsis());
		System.out.println("4. Director: " + m.getDirector());
		System.out.println("5. Cast: " + m.getCast());
		System.out.println("6. Film Rating: " + m.getFilmRate());
		System.out.println("7. AirDate: " + m.getAirDate());
		System.out.println("8. Duration: " + m.getDuration());
		System.out.println("************************************************");
	}
	/**
	 * This method is used to print the selection menu for admin staff to edit/remove the selected movie.
	 * @param m A movie object containing the details of a movie.
	 */
	private void printEditRemoveMenu(Movie m)
	{
		System.out.println("Selected Movie: " + m.getTitle());
		System.out.println("Please select the follow-up action: ");
		System.out.println("1. Edit Movie		2. Remove Movie");
	}
	/**
	 * This method is used to print the available list of cineplexes in the database.
	 * @param cineplex A list of cineplex objects containing details of each cineplex.
	 */
	private void printCineplexName(List<Cineplex> cineplex)
	{
		int i=0;
		System.out.println("========= Cineplex =========");
		for (Cineplex c : cineplex)
		{
			System.out.println((++i) + " : " + c.getName());
		}
	}
	/**
	 * This method is used to print the available list of movies in the database.
	 * @param mList A list of movie objects containing details of each movie.
	 */
	private void printMovieList(List<Movie> mList)
	{
		System.out.println("=========== MOVIE DATABASE ==========="); 	// Print all Movies in the DB if exist
		if (mList.isEmpty())
		{
			System.out.println("There are currently no movies.");
		}
		else
		{
			System.out.println("************************************************");
			for(int i=0; i<mList.size();i++)
				System.out.println((i+1) + ". " + mList.get(i).getTitle());
			System.out.println("************************************************");
		}
	}
	/*
	 * This method is used to print all the available cinemas/theaters within each cineplex.
	 */
	private void printCineplexCinemas(List<Cinemas> cinemaNo)
	{
		int i=0;
		System.out.println("========= Theater No =========");
		for (Cinemas c : cinemaNo)
		{
			System.out.println((++i) + ". Theater No " + c.geTheaterNo());
		}
	}
	/**
	 * This method is used to print the details of the show time.
	 * @param sT A showtimes object containing details of the show time.
	 */
	private void printShowTimeDetails(ShowTimes sT)
	{
		System.out.println("************************************************");
		System.out.println("New Show Time added. Details as follow:");
		System.out.println("1. Title: " + sT.getTitle());
		System.out.println("2. Show Status: " + sT.getShowStatus());
		System.out.println("3. Cinexplex: " + sT.getCineplex());
		System.out.println("4. Class Type: " + sT.getclassType());
		System.out.println("5. TheaterNo: " + sT.getTheaterNo());
		System.out.println("6. Air Date: " + sT.getDate());
		System.out.println("7. Air Time: " + sT.getAirTime());
		System.out.println("8. Film Rating: " + sT.getFilmRate());
		System.out.println("************************************************");
	}
	/**
	 * This method is print all the available dates in the database.
	 * @param holiday A list of holidays in string format.
	 */
	private void printHolidayDates(List<String> holiday)
	{
		for(String s: holiday)
			System.out.println(s);
	}

	/**
	 * This method is used to sort the Map object based on the key values before returning the sorted Map object for 
	 * printing output on the screen.
	 * @param hashmap A Map containing movie titles as string keys and customer rating/total sales as double values  
	 * @return A sorted Map object in descending order by customer ratings or total sales.
	 */
	private  Map<String,Double> sortByValue(Map<String,Double> hashmap) 
    { 
        List<Map.Entry<String,Double> > list = new LinkedList<Map.Entry<String,Double> >(hashmap.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() { 
        	public int compare(Map.Entry<String, Double> e1,Map.Entry<String, Double> e2) 
            { 
                return (e2.getValue()).compareTo(e1.getValue()); 
            } 
        }); 
        Map<String, Double> temp = new LinkedHashMap<String,Double>(); 
        for (Map.Entry<String,Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
	/**
	 * This method is used to update the movie title in the showtimes database and booking databases 
	 * after admin staff edited it in the movielist database.
	 * @param old A string containing the unmodified title of the movie.
	 * @param title A string containing the modified title of the movie.
	 */
	private void updateTitle(String old, String title)
	{
		ConnectDB admin = new ConnectDB();
		List<ShowTimes> sTList = admin.showtimeManager(SHOWTIME_DB);
		for(ShowTimes sT : sTList)
		{
			if(sT.getTitle().equals(old))
			{
				sT.setTitle(title);
			}
		}
		List<Booking> bookList = admin.bookingManager(BOOKING_DB);
		for(Booking bk : bookList)
		{
			if(bk.getmovieTitle().equals(old))
			{
				bk.setTitle(title);
			}
		}
		admin.saveShowTime(SHOWTIME_DB, sTList);
		admin.saveBooking(BOOKING_DB, bookList);
	}
	/**
	 * This method is used to update the status of the movie in the showtimes database after
	 * admin staff updated it in the movielist database.
	 * @param title A string containing the title of the movie that has its status being modified.
	 * @param status A showStatus enum that is used to indicate the status of the movie.
	 */
	private void updateStatus(String title, showStatus status)
	{
		ConnectDB admin = new ConnectDB();
		List<ShowTimes> sTList = admin.showtimeManager(SHOWTIME_DB);
		for(ShowTimes sT : sTList)
		{
			if(sT.getTitle().equals(title))
			{
				sT.setShowStatus(status);
			}
		}
		admin.saveShowTime(SHOWTIME_DB, sTList);
	}
	/**
	 * This method is used to update the film rating of the movie in the showtimes and booking databases after
	 * admin staff modified it in the movielist database.
	 * @param title A string containing the title of the movie being modified.
	 * @param rate A filmRate enum that is used to indicate the film rating of the movie.
	 */
	private void updatefRate(String title, filmRate rate)
	{
		ConnectDB admin = new ConnectDB();
		List<ShowTimes> sTList = admin.showtimeManager(SHOWTIME_DB);
		for(ShowTimes sT : sTList)
		{
			if(sT.getTitle().equals(title))
			{
				sT.setFilmRate(rate);
			}
		}
		List<Booking> bookList = admin.bookingManager(BOOKING_DB);
		for(Booking bk : bookList)
		{
			if(bk.getmovieTitle().equals(title))
			{
				bk.setfRate(rate);
			}
		}
		admin.saveShowTime(SHOWTIME_DB, sTList);
		admin.saveBooking(BOOKING_DB, bookList);
	}
	/**
	 * This method is used to check if there exists a booking entry that matches the movie title being removed.
	 * If it return true, this indicates that one or more existing booking has been made and the movie should not be removed.
	 * @param title
	 * @return true if movie title matches the booked movie title is found, otherwise false.
	 */
	private boolean checkBooking(String title)
	{
		ConnectDB admin = new ConnectDB();
		List<Booking> bookList = admin.bookingManager(BOOKING_DB);
		for(Booking bk : bookList)
		{
			if(bk.getmovieTitle().equals(title))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * This method is used to validate that the string date inputed by user is in the correct format.
	 * @param date A string representing date that user inputs.
	 * @return true if the date format is correct, otherwise false.
	 */
	private boolean validateDate(String date)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try
	    {
	        format.parse(date);
	    }
	    catch (ParseException e)
	    {
	        System.out.println(date+" is Invalid Date format");
	        return false;
	    }
	    return true;
	}
	/**
	 * This method is used to ask admin staff for their userID and password and check against the StaffDB.
	 * The user will not be able to access any movie admin functions for unsuccessful login-in. 
	 */
	public void loginPage()
	{
		ConnectDB admin = new ConnectDB();
		List<Staff> staffDB = admin.staffLogin(STAFF_DB);
		try {
			Console con = System.console();
			if(con == null)
			{
				System.out.print("No console available");
				System.exit(0);
			}
			else
			{
				 String id = con.readLine("Please enter your ID:");
				 char[] password = con.readPassword("Enter your password: ");
				 Staff staffLog = new Staff(id,password);
				 while(!staffLog.getStatus()) 
				 {
					 if (staffLog.checkPassword(staffDB)) 
						 break;
					 else
					 {
						 System.out.println("Incorrect userID or Password, Please try again.");
						 id = con.readLine("Please enter your ID:");
						 password = con.readPassword("Enter your password: ");
						 staffLog.setUserID(id);
						 staffLog.setPassword(password);
					 }
				 }
				 Date d = new Date();
				 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				 String s = "Logged in successfully as " + staffLog.getUserID() + " on " + formatter.format(d);
				 System.out.println(s);
				 admin.updateChanglog(CHANGELOG_DB, s);
			}
		}catch(Exception e)
		{
			System.out.println("Error");
		}
	}
	/**
	 * This method allows admin staff to modify prices for different age groups and rates for weekend/holidays, 
	 * economical class and VIP class. User inputs (Prices and Rates) will be checked to avoid negative and zero inputs.
	 * The new values will be updated once changes has been made.
	 */
	public void configTicketPrices()
	{
		ConnectDB admin = new ConnectDB();
		printTicketPriceSettingsMenu();
		boolean update = false;
		StringBuilder changelog = new StringBuilder();
        System.out.println("Please select which type of price to change");
        List<Price> pl = admin.priceManager(PRICERATE_DB);
        int option = sc.nextInt();
        Price p = pl.get(0);
        switch(option)
        {
        	case 1: System.out.println("Please enter new price for senior citizen: ");		//change price based on age
                    float newSeniorPrice = sc.nextFloat();
                    if (newSeniorPrice <= 0) 
                    {
                    	System.out.println("Invalid input");
                    	break;
                    }
                    else
                    {
                    	changelog.append("Change price of senior citizen from "+ p.getSP() + " to " +newSeniorPrice + "\n");
	                    p.setSP(newSeniorPrice);
	                    update = true;
	                    break;
                    }
                    
        	case 2:	System.out.println("Please enter new price for adult: ");			
                    float newAdultPrice = sc.nextFloat();
                    if (newAdultPrice <= 0)
                    {
                    	System.out.println("Invalid input");
                    	break;
                    }
                    else
                    {
                    	changelog.append("Change price of adult from "+ p.getAP() + " to " +newAdultPrice + "\n");
	                    p.setAP(newAdultPrice);
	                    update = true;
	                    break;
                    }
        	case 3: System.out.println("Please enter new price for children: ");		
                    float newChildPrice = sc.nextFloat();
                    if (newChildPrice <= 0)
                    {
                    	System.out.println("Invalid input");
                    	break;
                    }
                    else
                    {
                    	changelog.append("Change price of children from "+ p.getCP() + " to " +newChildPrice + "\n");
	                    p.setCP(newChildPrice);
	                    update = true;
	                    break;
                    }
        	case 4: System.out.println("Please enter new price for weekends/holidays: ");	 //change price based of holiday/weekend
                    float newWeekendRate = sc.nextFloat();
                    if (newWeekendRate <= 0)
                    {
                    	System.out.println("Invalid input");
                    	break;
                    }
                    else
                    {
                    	changelog.append("Change rate for weekend and holiday from "+ p.getWR() + " to " +newWeekendRate + "\n");
	                    p.setWR(newWeekendRate);
	                    update = true;
	                    break;
                    }
        	case 5: System.out.println("Please enter new price for economical: ");			//change price based on class
                    float newEconRate = sc.nextFloat();
                    if (newEconRate <= 0)
                    {
                    	System.out.println("Invalid input");
                    	break;
                    }
                    else
                    {
                    	changelog.append("Change rate for economical from "+ p.getER() + " to " +newEconRate + "\n");
	                    p.setER(newEconRate);
	                	update = true;
	                    break;
                    }
        	case 6: System.out.println("Please enter new price for VIP");
                    float newVIPRate = sc.nextFloat();
                    if (newVIPRate <= 0)
                    {
                    	System.out.println("Invalid input");
                    	break;
                    }
                    else
                    {
                    	changelog.append("Change rate for VIP from "+ p.getVR() + " to " +newVIPRate + "\n");
	                    p.setVR(newVIPRate);
	                    update = true;
	                    break;
                    }
            default:System.out.println("Invalid choice");
        }
        String cl = changelog.toString();
        if(update) 
        {
        	admin.updatePriceSetting(PRICERATE_DB,pl);
        	admin.updateChanglog(CHANGELOG_DB, cl);
        }
	}
	/**
	 * This method allows admin staff to add/remove holiday dates from the databases.
	 * String dates will be validated by {@link validateDate} method before updating the holiday database.
	 */
	public void configHolidayDates()
	{
		String date;
		StringBuilder changelog = new StringBuilder();
		ConnectDB admin = new ConnectDB();
		printHolidaySettingsMenu();
		List <String> ls = admin.holidayManager(HOLIDAY_DB);
		System.out.println("Please select add/remove of holiday dates: ");
		int option = sc.nextInt();
		if (option == 1)		//add holiday
		{
			System.out.println("Which date do you want to add as a holiday? (dd/mm/yyyy)");
			date = sc.next();
			if(validateDate(date))
			{
				if (ls.contains(date))
				{
					System.out.println(date + " is already a holiday");
				}
				else
				{
					ls.add(date);
					admin.updateHolidayList(HOLIDAY_DB, ls);
					changelog.append("Holiday: "+ date + " has been added" + "\n");
					admin.updateChanglog(CHANGELOG_DB, changelog.toString());
					System.out.println(date + " is added to the holiday list.");
				}
			}
			else
				System.out.println("Incorrect Data Format");
		}
		else if (option == 2) //remove holiday
		{
			System.out.println("Which holiday do you want to remove? (dd/mm/yyyy)");
			date = sc.next();
			if(validateDate(date))
			{
				printHolidayDates(ls);
				if (ls.contains(date))
				{
					int i = ls.indexOf(date);
					ls.remove(i);
					admin.updateHolidayList(HOLIDAY_DB, ls);
					changelog.append("Holiday: "+ date + " has been removed" + "\n");
					admin.updateChanglog(CHANGELOG_DB, changelog.toString());
					System.out.println(date + " has been removed from the holiday list");
				}
				else
				{
					System.out.println(date + " is an invalid date or it cannot be found");
				}
				
			}	
			else
				System.out.println("Incorrect Data Format");
		}
		else
		{
			System.out.println("Invalid choice");
		}
	}
	/**
	 * This method allows staff admin to create a new movie and add to movielist database.
	 * User input string date will be validated by {@link validateDate} method before adding to the database.
	 */
	public void addNewMovie()
	{
		// "," will be replaced with ";"
		ConnectDB admin = new ConnectDB();
		StringBuilder changelog = new StringBuilder();
		System.out.println("Adding Movie: ");
		System.out.println("Please enter movie title to add:");
		String title = sc.nextLine();
		System.out.println("Default movie status : Coming_Soon");
		System.out.println("Please enter movie synopsis to add:");
		String synopsis = sc.nextLine();
		System.out.println("Please enter movie director to add:");
		String director = sc.nextLine();
		System.out.println("Please enter movie cast to add:");
		String cast = sc.nextLine();
		System.out.println("Please enter movie rating to add:");
		System.out.println("Options: G, PG, PG13, NC16, M18, R21");
		String fRate = sc.nextLine();
		String airdate;
		do
		{
			System.out.println("Please enter movie airdate to add:");
			airdate = sc.nextLine();
		}while(!validateDate(airdate));
		int duration;
		do
		{
			System.out.println("Please enter movie duration to add (hours) [1-999]:");
			duration = sc.nextInt();
		}while(duration > 1000 && duration <0);
		Movie m = new Movie(title, synopsis, director, cast, filmRate.valueOf(fRate), airdate, duration);
		admin.addNewMovie(MOVIELIST_DB, m);
		printAttributeList(m);
		changelog.append("Movie: "+ m.getTitle() + " has been added" + "\n");
		admin.updateChanglog(CHANGELOG_DB, changelog.toString());
	}
	/**
	 * This method allows admin staff to modify details of the movie in the movielist database.
	 * Note: Some new changes will print a warning and does not affect details in showtimes and booking databases even
	 * it is being modified in the movielist databases.  
	 */
	public void movie_EditRemove()
	{
		StringBuilder changelog = new StringBuilder();
		ConnectDB admin = new ConnectDB();
		List <Movie> mList = admin.movieManager(MOVIELIST_DB);
		printMovieList(mList);
		if(!mList.isEmpty())
		{
			// Selection of Movie for Follow-up action
			System.out.print("Please select movie by index: ");
			int option = sc.nextInt();
			Movie change = mList.get(option-1);
			printEditRemoveMenu(change);
			option = sc.nextInt();
			if (option == 1)			//Edit Movie
			{
				System.out.println("=========== Current MOVIE ===========");
				printAttributeList(change);
				System.out.println("Please select index to edit Movie Detail (-1 to quit):");
				option = sc.nextInt();
				while(option!=-1)
				{
					sc.nextLine();
					switch(option)
					{
						case 1: System.out.println("Please enter new movie title: ");
								String oldtitle = change.getTitle();
								String title = sc.nextLine();
								changelog.append("Movie Title: "+ change.getTitle() + " change to " + title + "\n");
								change.setTitle(title);
								updateTitle(oldtitle, title);
								break;
						case 2: System.out.println("Please enter new status (by index): ");
								System.out.println("(1) Coming Soon  (2)Preview  (3) Now Showing");
								int status = sc.nextInt();
								changelog.append("Movie Status: "+ change.getStatus() + " change to " + status + "\n");
								change.setStatus(status);
								updateStatus(change.getTitle(),change.getStatus());
								break;
						case 3:	System.out.println("Please enter new synopsis: ");
								String synopsis = sc.nextLine();
								changelog.append("Movie Synopsis: "+ change.getSynopsis() + " change to " + synopsis + "\n");
								change.setSynopsis(synopsis);
								break;
						case 4: System.out.println("Please enter new director: ");
								String director = sc.nextLine();
								changelog.append("Movie Director: "+ change.getDirector() + " change to " + director + "\n");
								change.setDirector(director);
								break;
						case 5: System.out.println("Please enter new cast: ");
								String cast = sc.nextLine();
								changelog.append("Movie cast: "+ change.getCast() + " change to " + cast + "\n");
								change.setCast(cast);
								break;
						case 6: System.out.println("Please enter new film rating (by index): ");
								System.out.println("(1) G  (2) PG  (3) PG13  (4) NC16  (5) M18  (6)R21");
								int fRate = sc.nextInt();
								changelog.append("Movie Film Rate: "+ change.getFilmRate() + " change to " + fRate + "\n");
								change.setfRate(fRate);
								updatefRate(change.getTitle(),change.getFilmRate());
								break;
						case 7: System.out.println("Warning. Any new changes do not affect any existing Show Times & Booking");
								String airDate;
								do
								{
									System.out.println("Please enter new Airdate (dd/mm/yyyy): ");
									airDate = sc.nextLine();
								}while(!validateDate(airDate));
								changelog.append("Movie Air Date: "+ change.getAirDate() + " change to " + airDate + "\n");
								change.setAirDate(airDate);
								break;
						case 8: System.out.println("Warning. Any new changes do not affect any existing Show Times & Booking");
								System.out.println("Please enter new duration: ");
								int duration = sc.nextInt();
								changelog.append("Movie Duration: "+ change.getDuration() + " change to " + duration + "\n");
								change.setDuration(duration);
								break;
						case -1: System.out.println("Return to Main Menu."); break;
						default:System.out.println("Invalid option. Please try again"); break;
					}
					System.out.println("Please select index to edit Movie Detail (-1 to quit):");
					option = sc.nextInt();
				}
				admin.saveMovie(MOVIELIST_DB, mList); //update back to movieDB
				admin.updateChanglog(CHANGELOG_DB, changelog.toString());
			}
			else if (option == 2)
			{
				System.out.println("Warning. Ensure that there is no existing bookings for Movie: " + change.getTitle());
				System.out.println("=========== REMOVE MOVIE ===========");
				if(!checkBooking(change.getTitle()))
				{
					int pos = mList.indexOf(change);
					changelog.append("Movie: "+ change.getTitle() + "has been removed" + "\n");
					mList.remove(pos);
					admin.saveMovie(MOVIELIST_DB, mList);//update back to movieDB
					admin.updateChanglog(CHANGELOG_DB, changelog.toString());
				}
				else
					System.out.println("There is one or more bookings for selected movie. Consider setting the movie status to End of Showing instead" );
			}
		}
	}
	/**
	 * This method allows admin staffs to list down the Top 5 Movies List either by customer ratings or total sales, starting from the highest.
	 */
	public void listTop5()
	{
		ConnectDB admin = new ConnectDB();
		Map<String,Double> top5list = new HashMap <String,Double>();
		System.out.println("View Top 5 Movie by:");
		System.out.println("(1) Total Sales		(2) Rating");
		int option = sc.nextInt();
		if(option == 1)
		{
			List<Booking> bookList = admin.bookingManager(BOOKING_DB);
			for(Booking bk : bookList)
			{
				String bTitle = bk.getmovieTitle();
				if(top5list.containsKey(bTitle))
				{
					double tSales = top5list.get(bTitle) + bk.gettotalPrice();
					top5list.put(bTitle, tSales);
				}
				else
				{
					top5list.put(bTitle, bk.gettotalPrice());
				}
			}
			if(top5list.size() >= 5)
			{
				Map<String,Double> sortList = sortByValue(top5list);
				int count = 0;
				System.out.println("======== Top 5 Ranking (By Total Sales) ========");
				for(Map.Entry<String, Double> entry : sortList.entrySet())
				{
					if(count == 5) break;
					System.out.printf("%2d. %-30s Total Sales: %.2f%n", ++count, entry.getKey(), entry.getValue());
				}
			}
			else
			{
				System.out.println("Insufficient Sales Data to display Top 5 Movies");
			}
		}
		else if(option ==2)
		{
			List<Movie> mList = admin.movieManager(MOVIELIST_DB);
			for(Movie m : mList)
			{
				String bTitle = m.getTitle();
				if(!top5list.containsKey(bTitle))
				{
					top5list.put(bTitle, m.getAvgRate());
				}
			}
			if(top5list.size() >= 5)
			{
				Map<String,Double> sortList = sortByValue(top5list);
				int count = 0;
				System.out.println("======== Top 5 Ranking (By Customer Ratings) ========");
				for(Map.Entry<String, Double> entry : sortList.entrySet())
				{
					if(count == 5) break;
					System.out.printf("%2d. %-30s User Rating: %.1f%n", ++count, entry.getKey(), entry.getValue());
				}
			}
			else
			{
				System.out.println("Insufficient Customer Review Data to display Top 5 Movies");
			}
		}
		else
			System.out.println("Invalid Option");
	}
	/**
	 * This method allows admin staff to add new showtime from the list of available movies in the movielist database.
	 * Checks will be done to ensure there is no overlapping of movies during the same time slot on the same date at the 
	 * same cineplex and theater. Note the movie status shown in the showtimes database will be the same with the one in 
	 * the movielist database. To set/modify the status, please use Edit/Remove movie function in the main menu.
	 */
	public void addShowTime()
	{
		StringBuilder changelog = new StringBuilder();
		ConnectDB admin = new ConnectDB();
		List <Movie> mList = admin.movieManager(MOVIELIST_DB);
		printMovieList(mList);
		int option = sc.nextInt();
		Movie allocate = mList.get(option-1);			//get Movie
		
		List<Cineplex> plexList = admin.cineplexManager(CINEPLEX_DB);
		printCineplexName(plexList);
		option = sc.nextInt();
		Cineplex plex = plexList.get(option-1);		//get Cineplex
		
		List<Cinemas> masList = plex.getCinemas();
		printCineplexCinemas(masList);
		option = sc.nextInt();
		Cinemas mas = masList.get(option-1);			//get Cinemas
		sc.nextLine();
		System.out.println("Please enter the air date (dd/mm/yyyy):");
		String airdate = sc.nextLine();
		System.out.println("Please enter the air time (24-hours):");
		int airtime = sc.nextInt();
		if(validateDate(airdate))
		{
			List<ShowTimes> stList = admin.showtimeManager(SHOWTIME_DB);
			boolean safe = true;
			String addingPlex = plex.getName();
			int addingMas = mas.geTheaterNo();
			int addingEndTime = airtime + allocate.getDuration();
			int shEndTime=0;
			for (ShowTimes sh : stList)
			{
				if(stList.isEmpty()) 
					break;
				else
				{
					if(sh.getCineplex().equals(addingPlex) && sh.getTheaterNo()==addingMas && airdate.equals(sh.getDate()))
					{
						for(Movie m : mList)
						{
							if(sh.getTitle().equals(m.getTitle()))
							{
								shEndTime = sh.getAirTime() + m.getDuration();
								break;
							}
						}
						if((airtime < 1000 || addingEndTime > sh.getAirTime()) && (airtime < shEndTime || addingEndTime > 2200))
						{
							safe=false;
							break;
						}
					}
				}
			}
			if(safe)
			{
				
				String seatArr = mas.seat2Arrange();
				ShowTimes sT = new ShowTimes(allocate.getTitle(),allocate.getStatus(),addingMas,addingPlex,airdate,airtime,allocate.getFilmRate(),mas.getCType());
				sT.setArrangement(seatArr);
				changelog.append("Successful added " + sT.getTitle() + " on" + sT.getDate() + " at " + sT.getAirTime());
				admin.addShowTime(SHOWTIME_DB, sT);
				printShowTimeDetails(sT);
				admin.updateChanglog(CHANGELOG_DB, changelog.toString());
			}
			else
				System.out.println("Failed to add new Showtime");
		}
		else
		{
			System.out.println("Invalid Date");
		}
	}
	/**
	 * This methods allow admin staff to add new staff user to the databases, allowing them to access movie admin functions.
	 */
	public void addNewUser()
	{
		System.out.println("Adding New Staff User...");
		ConnectDB admin = new ConnectDB();
		Console con = System.console();
		String userID = con.readLine("Please enter your ID:");
		char[] password = con.readPassword("Enter your password: ");
		char[] authpassword;
		do
		{
			System.out.println("Enter new userID password: ");
			authpassword = con.readPassword("Enter your password: ");
			
		}while(!Arrays.equals(password, authpassword));
		Staff staff = new Staff(userID,authpassword);
		admin.updateStaff(STAFF_DB, staff);
	}
}
