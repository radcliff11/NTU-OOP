package assignment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* This manager class acts a control for the review, movie, showtime and booking entity classes.
* @author  Ng Joshua Jeremiah
* @author Marvin Winson
* @version 1.0
* @since   1.0
*/
public class CustomerManager {
	/**
	 * Represents the final integer variable used to offset when printing the theater layout.
	 */
	private final int SPACING = 0;
    /**
     * Represents the final integer variable used to control spaces between seats in the theater layout.
     */
    private final int AISLE = 4;
    /**
     * Creates a ConnectDB object to use methods in connectDB class.
     */
    ConnectDB custdb = new ConnectDB();
    /**
     * Initiates a property object that reads the configuration file for the database.
     */
    Properties prop= custdb.db_Init();
    /**
     * Creates a scanner object to use methods in the scanner class.
     */
    Scanner sc = new Scanner(System.in);
    /**
     * A String that represents the price rate database.
     */
    private final String PRICERATE_DB = prop.getProperty("DB.pricerate");
    /**
     * A String that represents the booking database.
     */
    private final String BOOKING_DB = prop.getProperty("DB.booking");
    /**
     * A String that represents the cineplex database.
     */
    private final String CINEPLEX_DB = prop.getProperty("DB.cineplex");
    /**
     * A String that represents the movielist databaase.
     */
    private final String MOVIELIST_DB = prop.getProperty("DB.movie");
    /**
     * A String that represents the showtime database.
     */
    private final String SHOWTIME_DB = prop.getProperty("DB.showtime");
    /**
     * A String that represents the holiday database.
     */
    private final String HOLIDAY_DB = prop.getProperty("DB.holiday");
    /**
     * A String that represents the customer database.
     */
    private final String CUSTOMER_DB = prop.getProperty("DB.customer");


    // option 1: booking
    /**
     * This method allows the user to book a movie.
     * It allows the user to choose fields such as which cineplex, movie, date, showtime, ticket choice, and input their email and phone number.
     * Upon successful booking, A booking/transaction object will be created, and the transaction Id and the details of their booking will be displayed for the user.
     * A Customer object will be created and customer Ids with be generated whenever a new customer makes a booking.
     * @throws ParseException Constructs a ParseException with the specified detail message and offset.
     */
    public void mainbooking() throws ParseException
    {
		String email;
		int mobile;
    	
    	List<Customer> customerlist = custdb.custManager(CUSTOMER_DB);
		List<Cineplex> cineplexlist = custdb.cineplexManager(CINEPLEX_DB);
		List<Movie> movielist = custdb.movieManager(MOVIELIST_DB);
		List<Price> pricelist = custdb.priceManager(PRICERATE_DB);
		List<ShowTimes> showtimelist =custdb.showtimeManager(SHOWTIME_DB);
		List<Booking> bookinglist = custdb.bookingManager(BOOKING_DB);
    	
    	System.out.println("=========Booking System=========");
		//select cineplex
		
		int i=0, j=0, k=0, count=1;
		
		for (i=0; i<cineplexlist.size(); i++)
		{
			System.out.println((i+1) + ". " + cineplexlist.get(i).getName());
		}
		System.out.println("Please select cineplex");
		int cineplexchoice=sc.nextInt();
		
		//select movie
		count=1;
		List<String> printedMovies = new ArrayList<String>();
		for (i=0; i<showtimelist.size(); i++)
		{
			if (!printedMovies.contains(showtimelist.get(i).getTitle()))
			{
				if (showtimelist.get(i).getCineplex().equals(cineplexlist.get(cineplexchoice-1).getName()) && showtimelist.get(i).getShowStatus()!=showStatus.Coming_Soon)
				{
					System.out.println(count + ". " + fix(showtimelist.get(i).getTitle()));
					printedMovies.add(fix(showtimelist.get(i).getTitle()));
					count++;
				}
			}
		}
		
		System.out.println("Please select Movie:");
		int moviechoice = sc.nextInt();
		
		Movie selectedmovie = null;
		
		for (j=0; j<movielist.size();j++)
		{
			if (printedMovies.get(moviechoice-1).equals(movielist.get(j).getTitle()))
			{
				selectedmovie=movielist.get(j);
			}
		}
		
		System.out.println(selectedmovie.getTitle());
		
		//select date and time
		List<String> printedDates = new ArrayList<String>();
		for (i=0; i<showtimelist.size(); i++)
		{
			if (fix(showtimelist.get(i).getTitle()).equals(selectedmovie.getTitle()))
			{
				if (!printedDates.contains(showtimelist.get(i).getDate()))
				{
					System.out.println("Date: " + showtimelist.get(i).getDate());
					System.out.print("Airtime(s): ");
					for (k=0; k<showtimelist.size(); k++)
					{
						if (showtimelist.get(i).getDate().equals(showtimelist.get(k).getDate()) && fix(showtimelist.get(i).getTitle()).equals(fix(showtimelist.get(k).getTitle())))
						{
							System.out.print(showtimelist.get(k).getAirTime() + " ");
						}
					}
					System.out.println();
				}
				printedDates.add(showtimelist.get(i).getDate());
			}
		}
		System.out.println();
		System.out.println("Please select date");
		String datechoice = sc.next();
		System.out.println("Please select time");
		int timechoice = sc.nextInt();
		
		
		for (k=0; k<showtimelist.size(); k++)
		{
			if (fix(showtimelist.get(k).getTitle()).equals(fix(selectedmovie.getTitle())) && showtimelist.get(k).getDate().equals(datechoice) && showtimelist.get(k).getAirTime()==timechoice)
			{
				break;
			}
		}
		
		if (k!=showtimelist.size())
		{
			//select tickets
			int childtix=0, adulttix=0, seniortix=0, totaltix=0;
			boolean valid;
			System.out.println("Remaining Seats: " + showtimelist.get(k).getRemainingSeats());
			do
			{
				valid=true;
				System.out.println("Please enter no of child tickets you wish to purchase:");
				childtix=sc.nextInt();
				System.out.println("Please enter no of adult tickets you wish to purchase:");
				adulttix=sc.nextInt();
				System.out.println("Please enter no of senior citizen tickets you wish to purchase:");
				seniortix=sc.nextInt();
				totaltix=childtix+adulttix+seniortix;
				if (totaltix >= showtimelist.get(k).getRemainingSeats() || childtix<0 || adulttix<0 || seniortix<0)
				{
					System.out.println("Invalid input");
					valid=false;
				}
			} while (!valid);

			float totalprice = calculateTotalPrice(childtix, adulttix, seniortix, pricelist.get(0), showtimelist.get(k).getDate(), showtimelist.get(k).getclassType());
			
			//seat selection
			
			boolean contigseats=true;
			ArrayList<String> selectedseats=null;
			do
			{
				print(showtimelist.get(k).getArrangement());
				
				contigseats=true;
				selectedseats = new ArrayList<String>();
				for (i=0; i<totaltix; i++)
				{
					System.out.println("Please select seats");
					String seat = sc.next();
					if (showtimelist.get(k).checkSeat(seat) && !selectedseats.contains(seat))
					{
						selectedseats.add(seat);
					}
					else
					{
						System.out.println("Invalid Seat. Please choose another.");
						i--;
					}
				}
				Collections.sort(selectedseats);
				
				if (selectedseats.size()>1)
				{
					if (selectedseats.size()==2)
					{
						if (selectedseats.get(0).charAt(0) == selectedseats.get(1).charAt(0) && Integer.parseInt(selectedseats.get(0).substring(1)) == (Integer.parseInt(selectedseats.get(1).substring(1))-2))
						{
							System.out.println("Invalid seat options, cannot have gap in between");
							contigseats=false;
						}
					}
					else
					{
						for (i=0; i<selectedseats.size()-2;i++)
						{
							if (selectedseats.get(i).charAt(0) == selectedseats.get(i+1).charAt(0))
							{
								if (Integer.parseInt(selectedseats.get(i).substring(1)) == (Integer.parseInt(selectedseats.get(i+1).substring(1))-2))
								{
									System.out.println("Invalid seat options, cannot have gap in between");
									contigseats=false;
									break;
								}
							}
						}
					}
				}
			} while (contigseats==false);
			
			showtimelist.get(k).updateArrangement(selectedseats);
			//confirming seats
			print(showtimelist.get(k).getArrangement(), selectedseats);
			custdb.saveShowTime(SHOWTIME_DB, showtimelist);
			
			String seating="";
			for (i=0; i<selectedseats.size();i++)
			{
				seating += selectedseats.get(i) + " ";
			}

			
			String location="";
			for (i=0; i<cineplexlist.size(); i++)
			{
			
				if (cineplexlist.get(i).getName().equals(showtimelist.get(k).getCineplex()))
				{
					location = cineplexlist.get(i).getLocation();
					
				}
			}
			
			//creating booking/transaction
			System.out.println("Please enter email:");
			sc.nextLine();
			email = sc.next();
			System.out.println("Please enter phone no:");
			mobile = sc.nextInt();
			String transID = generateTransactionID(showtimelist.get(k).getTheaterNo(), showtimelist.get(k).getDate(), showtimelist.get(k).getAirTime(), seating);
			
			Booking book = new Booking(email, mobile, transID, fix(showtimelist.get(k).getTitle()), showtimelist.get(k).getDate(), showtimelist.get(k).getCineplex(), showtimelist.get(k).getTheaterNo(), location, showtimelist.get(k).getAirTime(), totalprice, showtimelist.get(k).getFilmRate(), totaltix, seating);
			bookinglist.add(book);
			custdb.saveBooking(BOOKING_DB, bookinglist);
			book.printTransaction();
			
			for (i=0; i<customerlist.size(); i++)
			{
				if (customerlist.get(i).getCustEmail().equals(email) && customerlist.get(i).getMobileNo()==mobile)
				{
					customerlist.get(i).addtransaction(book.gettransactionID());
					break;
				}
			}
			if (i==customerlist.size())
			{
				Customer newcust = new Customer (UUID.randomUUID().toString(),email,mobile);
				newcust.addtransaction(book.gettransactionID());
				customerlist.add(newcust);
				custdb.saveCustomer(CUSTOMER_DB, customerlist);
			}
		}
		else
		{
			System.out.println("Invalid date/time");
		}
    }
    
    //print main menu
    /**
     * This method prints of the main menu for the customer.
     * @return An integer represented the selected choice of the customer.
     */
    public int printMainMenu()
    {
    	System.out.println("1. Do Booking");
		System.out.println("2. Write Review");
		System.out.println("3. Show details of movie");
		System.out.println("4. List top 5 movies");
		System.out.println("5. View Booking History");
		System.out.println("6. Exit");
		System.out.println("Please enter your selection:");
		return sc.nextInt();
    }
    
    
    
    //printing of seats
    /**
     * This method prints the arrangement of the theater.
     * @param arrangement A String representing the seat arrangement of the theater.
     */
    public void print(String arrangement){
        ArrayList<String> selection = new ArrayList<>();
        print(arrangement,selection);
    }
    /**
     * This method prints the arrangement of the theater with the selected seats in a different color.
     * @param arrangement A String representing the seat arrangement of the theater.
     * @param selected A list representing the selected seats by the customer.
     */
    public void print(String arrangement, ArrayList<String> selected)
    {
        String SEPARATOR = ",";
        ArrayList<String> seats = new ArrayList<>(Arrays.asList(arrangement.split(SEPARATOR)));


        formatSelection(selected);

        //Prints Column Numbers
        int ln = seats.get(0).replace("|","").length();
        System.out.print(space(4));
        for(int i=0 ; i< ln; i++) {
            if(i==ln/2) {
                System.out.print(space(AISLE));
            }
            System.out.print((i+1)+space(SPACING+4));
        }

        System.out.println();

        //Prints Each Line
        for(int i = 0; i<seats.size(); i++) {
            System.out.print((char)(i+65)+" ");
            print_line(seats.get(i),(char)(i+65), selected);
        }

        //Prints the Screen
        int width = (SPACING+5)*ln+AISLE-6-SPACING;
        int left = width/2;
        int right = width-left;
        System.out.print(space(2));
        color_text(space(left)+"SCREEN"+space(right),"GREEN");
        System.out.println();

    }
    /**
     * This method selects the format to print the seat arrangement of the theatre.
     * @param select The list the contains the format for the seat arrangement.
     */
    private void formatSelection(ArrayList<String> select){
        int col;

        for(int i=0 ; i<select.size() ; i++) {
            col = Integer.parseInt(select.get(i).substring(1));
            select.set(i,String.format("%c%02d",select.get(i).charAt(0),col));
        }
    }
    /**
     * This method adds spaces. 
     * @param n An integer containing the number of spaces to add.
     * @return A String representing the spaces to add.
     */
    private String space(int n)
    {
        String s = "";
        for(int i=0; i<n ; i++)
        {
            s = s.concat(" ");
        }
        return s;
    }
    /**
     * This method selects the color for the seats.
     * @param s A String containing the seat number.
     * @param color A String to select the color.
     */
    private void color_text(String s,String color)
    {
        switch(color)
        {
            case "RED":
                System.out.print("\033[38;5;15m\033[48;5;9m"+s+"\033[0m");
                break;
            case "WHITE":
                System.out.print("\033[38;5;0m\033[48;5;15m"+s+"\033[0m");
                break;
            case "BLUE":
                System.out.print("\033[38;5;15m\033[48;5;12m"+s+"\033[0m");
                break;
            case "GREEN":
                System.out.print("\033[38;5;0m\033[48;5;10m"+s+"\033[0m");
                break;
            case "YELLOW_TEXT":
                System.out.print("\033[38;5;11m"+s+"\033[0m");
                break;
        }
    }
    /**
     * This methods prints a line of seats.
     * @param in The input string representing the line of seats.
     * @param row The row to print.
     * @param selected The selected seats.
     */
    private void print_line (String in, char row, ArrayList<String> selected) {
        String content;
        Pattern p = Pattern.compile("\\|[0-9]+\\|");
        Matcher m = p.matcher(in);

        int j = 0; // Number of separators found
        boolean between = false;// flag for in between separators

        int ln = in.replace("|", "").length(); // Actual number of columns


        for (int i = 0; i < in.length(); i++) {
            content = String.format("%c%02d",row, i + 1 - j);//Seat num

            //Look for pairs of seat separators
            if(in.charAt(i) == '|') {
                color_text("|","YELLOW_TEXT");
                j++;
                if(!between) {
                    if(m.find()){
                        between = true;
                        continue;
                    }
                    else {
                        System.out.println();
                        throw new IllegalArgumentException("All seat separators need to have pairs");
                    }
                }else {
                    between = false;
                    System.out.print(space(SPACING));
                    if(i+1-j == ln/2 ) {
                        System.out.print(space(AISLE));
                    }
                    continue;
                }
            }

            if (!between) {
                System.out.print(" ");
            }
            if(selected.contains(content)){
                color_text(content, "BLUE");
            }else {
                switch (in.charAt(i)) {
                    case '0':
                        color_text(content, "WHITE");
                        break;
                    case '1':
                        color_text(" X ", "RED");
                        break;
                }
            }

            if(between) {
                if(i+1 != m.end()-1)
                    System.out.print(space(SPACING+2));
            }else {
                System.out.print(space(SPACING+1));
            }

            if(between) {
                if(i+1-j == ln/2 & i+1 != m.end()-1) {
                    System.out.print(space(AISLE));
                }
            }else {
                if(i+1-j == ln/2) {
                    System.out.print(space(AISLE));
                }
            }
        }
        System.out.println("\n");
    }
	
    
    //calculating price
    /**
     * This method checks if the inputed date is a weekend or a holiday.
     * It uses the default class to check for weekend and the holiday list from the database to check for holiday.
     * @param date A String containing the date to be verified.
     * @return A boolean representing the outcome of the check, true = yes, false = no.
     * @throws ParseException Constructs a ParseException with the specified detail message and offset.
     */
	private boolean checkWeekendHol(String date) throws ParseException
	{
		List <String> holidaylist = custdb.holidayManager(HOLIDAY_DB);

		SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		Date dt1=format1.parse(date);
		DateFormat format2=new SimpleDateFormat("EEEE"); 
		String finalDay=format2.format(dt1);
		if (finalDay=="Fri" || finalDay=="Sat" || finalDay=="Sun")
		{
			return true;
		}
		else if (holidaylist.contains(date)==true)
		{
			return true;
		}
		return false;
	}
	/**
	 * This method calculates the total price for the transaction.
	 * Prices for VIP, weekend/holiday and economical are based on rates, percentage increase of the base price.
	 * @param childtix The number of children tickets.
	 * @param adulttix The number of adult tickets.
	 * @param seniortix The number of senior citizen tickets.
	 * @param price A price object containing the prices for each catergory.
	 * @param date The date of purchase.
	 * @param classt The class of the cinema.
	 * @return A float representing the total price of the transaction.
	 * @throws ParseException Constructs a ParseException with the specified detail message and offset.
	 */
	public float calculateTotalPrice(int childtix, int adulttix, int seniortix, Price price, String date, classType classt) throws ParseException
	{
		if (classt==classType.VIP)
		{
			return (childtix*price.getCP()+adulttix*price.getAP()+seniortix*price.getSP())*price.getVR();
		}
		else if (checkWeekendHol(date)==true)
		{
			return (childtix*price.getCP()+adulttix*price.getAP()+seniortix*price.getSP())*price.getWR();
		}
		else if (classt==classType.ECONOMICAL)
		{
			return (childtix*price.getCP()+adulttix*price.getAP()+seniortix*price.getSP())*price.getER();
		}
		else if (classt==classType.NORMAL)
		{
			return (childtix*price.getCP()+adulttix*price.getAP()+seniortix*price.getSP());
		}
		else
		{
			return 0;
		}
		
	}
	
	//finalising booking
	/**
	 * Generates A unique transaction Id with details of the cinema name, date of purchase, airtime of movie and seats chosen.
	 * @param cinemaname The cinema name in the transaction.
	 * @param date The date of the transaction.
	 * @param airTime The airtime of the movie.
	 * @param seat The seats chosen.
	 * @return A String representing the unique transaction Id.
	 */
	public String generateTransactionID(int cinemaname, String date, int airTime, String seat)
	{
		char[] dateArr = date.toCharArray();
		return ("c" + cinemaname + seat + dateArr[6] + dateArr[7] + dateArr[8] + dateArr[9] + dateArr[3] + dateArr[4] + dateArr[0] + dateArr[1] + Integer.toString(airTime));
	}
	
	
	//Option 2: Writing Review
	/**
	 * This method allows users to choose a movie and write a review for it.
	 * They can add a rating and comment for their review.
	 * Their customer Id will be tagged to their review.
	 * Users are only allowed to review on a movie if they have booked a movie before.
	 */
	public void writeReview()
	{
		List<Customer> customerlist = custdb.custManager(CUSTOMER_DB);
		List<Movie> movielist = custdb.movieManager(MOVIELIST_DB);
		
		System.out.println("=========Writing Review=========");
		int i,j, mobile;
		String email;
		
		System.out.println("Please enter your email:");
		email = sc.next();
		System.out.println("Please enter your mobile no: ");
		mobile = sc.nextInt();
		
		for (i=0; i<movielist.size(); i++)
		{
			System.out.println((i+1) + ". " + movielist.get(i).getTitle());
		}
			
		System.out.println("Please enter movie to review");
		int reviewChoice = sc.nextInt();
		
		for (j=0; j<customerlist.size();j++)
		{
			if (customerlist.get(j).getCustEmail().equals(email) && customerlist.get(j).getMobileNo()==mobile)
			{
				System.out.println("Please enter rating (0-10):");
				float rating = sc.nextFloat();
				while (rating<0 || rating >10)
				{
					System.out.println("Invalid rating, please try again.");
					System.out.println("Please enter rating (0-10):");
					rating = sc.nextFloat();
				}
				System.out.println("Please enter comments: ");
				sc.nextLine();
				String comment = sc.nextLine();
				
				Review newReview = new Review (rating, comment, customerlist.get(j).getCustID());
				
				System.out.println("Your rating: " + rating);
				System.out.println("Your comment: " + comment);
				System.out.println("Thank you for your feedback!");
				System.out.println("");
				movielist.get(reviewChoice-1).addReview(newReview);
				movielist.get(reviewChoice-1).setAvgRate(movielist.get(reviewChoice-1).getReview());
				custdb.saveMovie(MOVIELIST_DB, movielist);
				break;
			}
		}
		
		
		if (j==customerlist.size())
		{
			System.out.println("You have to book a movie before reviewing.");
			System.out.println("");
		}
	}
	
	
	//Option 3: print movie details
	/**
	 * This method allows the users to choose a movie and view it's details.
	 * Details include title, status, synopsis, director, cast, Age rating, release date, duration, average rating and reviews.
	 */
	public void printMovieDetails()
	{
		List<Movie> movielist = custdb.movieManager(MOVIELIST_DB);
		
		System.out.println("=========View Movie Details=========");
		//view movie details
		for (int i=0; i<movielist.size(); i++)
		{
			System.out.println((i+1) + ". " + movielist.get(i).getTitle());
		}
		System.out.println("Please select movie to view:");
		int moviechoice=sc.nextInt()-1;
		
		System.out.println("Movie Title: " + movielist.get(moviechoice).getTitle());
		System.out.println("Status: " + movielist.get(moviechoice).getStatus());
		System.out.println("Synopsis: " + movielist.get(moviechoice).getSynopsis());
		System.out.println("Director: " + movielist.get(moviechoice).getDirector());
		System.out.println("Cast: " + movielist.get(moviechoice).getCast());
		System.out.println("Age Rating: " + movielist.get(moviechoice).getFilmRate());
		System.out.println("Air Date: " + movielist.get(moviechoice).getAirDate());
		System.out.println("Duration: " + movielist.get(moviechoice).getDuration());
		double avgrate=movielist.get(moviechoice).getAvgRate();
		System.out.println("Avg Rating: " + avgrate);
		System.out.println("========Reviews========");
		for (int i=0; i<movielist.get(moviechoice).getReview().size();i++)
		{
			System.out.println("Rating: " + movielist.get(moviechoice).getReview().get(i).getRating() + ". Comment:" + movielist.get(moviechoice).getReview().get(i).getComment() + ".");
		}
	}
	
	//Option 4: print top 5
	/**
	 * This method allows users to view the top 5 movies sorted by either average rating or total sales.
	 * The average rating will be shown to them but the total sales will be hidden.
	 */
	public void printTop5Movies()
	{
		System.out.println("=========Top 5 Movies=========");
		System.out.println("1. Sort by Average Rating");
		System.out.println("2. Sort by Average Sales");
		System.out.println("Please select sorting method for Top 5 Movies:");
		int top5choice = sc.nextInt();
		
		Comparator<Top5Movies> comp = new Comparator<Top5Movies>() {
	        public int compare(Top5Movies e1, Top5Movies e2) {
	            if(e1.getcriteria() < e2.getcriteria()) {
	                return 1;
	            } else if (e1.getcriteria() > e2.getcriteria()) {
	                return -1;
	            } else {
	                return 0;
	            }
	        }
	    };
		
		List<Movie> movielist = custdb.movieManager(MOVIELIST_DB);
		List <Top5Movies> top5list = new ArrayList<Top5Movies>();
		List<Booking> bookinglist = custdb.bookingManager(BOOKING_DB);
		
		//average rating
		if (top5choice == 1)
		{
			double avgRate=0;
			for (int i=0; i<movielist.size(); i++)
			{
				avgRate = movielist.get(i).getAvgRate();
				if (movielist.get(i).getReview().size()>1)
				{
					Top5Movies x = new Top5Movies(movielist.get(i).getTitle(),avgRate);
					top5list.add(x);
				}
			}
			
			//printing
			Collections.sort(top5list, comp);
			if (top5list.size()<=5)
			{
				System.out.println("Insufficient Customer Review Data to display Top 5 Movies");
			}
			else
			{
				for (int l=0; l<5; l++)
				{
					System.out.printf("%2d. %-30s User Rating: %.1f%n", (l+1), top5list.get(l).gettitle(), top5list.get(l).getcriteria());
				}
			}
			System.out.println();
			
		}
		//total sales
		else if (top5choice == 2)
		{
			String curMovie="";
			double totalSales=0;
			boolean done=false;
			List<String> visited = new ArrayList<String>();
			for (int i=0; i<bookinglist.size(); i++)
			{
				totalSales=0;
				
				curMovie=bookinglist.get(i).getmovieTitle();
				for (int k=0; k<visited.size();k++)
				{
					if (curMovie==visited.get(k))
					{
						done=true;
					}
				}
				
				if (done==false)
				{
					for (int j=0; j<bookinglist.size(); j++)
					{
						
						if (bookinglist.get(j).getmovieTitle()==curMovie)
						{
							totalSales+=bookinglist.get(j).gettotalPrice();
						}
					}
				}
				done=false;
				visited.add(curMovie);
				Top5Movies x = new Top5Movies(curMovie, totalSales);
				top5list.add(x);
				
			}
			
			//printing
					
			Collections.sort(top5list, comp);
			if (top5list.size()<=5)
			{
				System.out.println("Insufficient Sales Data to display Top 5 Movies");
			}
			else
			{
				for (int l=0; l<5; l++)
				{
					System.out.printf("%2d. %-30s %n", (l+1), top5list.get(l).gettitle());
				}
			}
			System.out.println();
		}
	}
	
	//Option 5: print booking
	/**
	 * This method prints the booking history of the user.
	 * They can find their booking history either by using mobile number and email or by using transaction Id.
	 */
	public void printBooking()
	{
		String email;
		int mobile, histchoice;
		List<Booking> bookinglist = custdb.bookingManager(BOOKING_DB);
		
		System.out.println("=========Booking History=========");
		//check booking history
		System.out.println("Search by:");
		System.out.println("1. Mobile no and Email");
		System.out.println("2. Transaction ID");
		
		histchoice=sc.nextInt();
		if (histchoice==1)
		{
			System.out.println("Please enter email:");
			email = sc.next();
			System.out.println("Please enter mobile no:");
			mobile = sc.nextInt();
			
			int i;
			boolean valid=false;
			for (i=0; i<bookinglist.size();i++)
			{
				if (bookinglist.get(i).getemail().equals(email) && bookinglist.get(i).getmobile()==mobile)
				{
					bookinglist.get(i).printTransaction();
					valid=true;
					System.out.println("------------------------------");
				}
			}
			if (valid==false)
			{
				System.out.println("Customer not found");
			}
			
		}
		else if (histchoice==2)
		{
			System.out.println("Please enter transaction ID:");
			sc.nextLine();
			String trans = sc.nextLine();
			int i;
			boolean valid=false;
			for (i=0; i<bookinglist.size();i++)
			{
				if (bookinglist.get(i).gettransactionID().equals(trans))
				{
					bookinglist.get(i).printTransaction();
					valid=true;
					System.out.println("------------------------------");
				}
			}
			if (valid==false)
			{
				System.out.println("Booking not found");
			}
		}
		else
		{
			System.out.println("Invalid choice");
		}
	}

//fixing encryption error
	/**
	 * This method fixes the encryption error for the first movie title.
	 * Those with no extra characters will be passed through without changes.
	 * @param t A String containing the movie title to fix.
	 * @return A String containing the fixed movie title.
	 */
	public String fix(String t)
	{
		if (t.charAt(0)=='ï')
		{
			return t.substring(3);
		}
		else
		{
			return t;
		}
	}
	
}
