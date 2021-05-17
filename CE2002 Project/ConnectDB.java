package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * This is used as a linkage between applications and databases to read, write and sync.
 * @author Ong Hock Rong
 * @version 1.0
 * @since 1.0
 */
public class ConnectDB {
	/**
	 * Represents the separator used for top-level formatting.
	 */
	private static final String SEPARATOR = ",";
	/**
	 * Represents the separator used for second-level formatting.
	 */
    private static final String SEPARATOR2 = "#";
    /**
     * Represents the separator used for third-level formatting. 
     */
    private static final String SEPARATOR3 = "^";
    /**
     * This method is used to read the staff database (CSV format) into a string of staff objects.
     * @param filename A string containing the filename of the staff database.
     * @return A list of staff objects containing all the staffs' userID and password.
     */
	public List<Staff> staffLogin(String filename)
	{
		String line;
		try {
			List<Staff> templist =  new ArrayList<Staff>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null)
			{
				String [] fields = line.split(",");
				if(fields.length > 0)
				{
					//int accessLevel = Integer.parseInt(fields[2]);
					char[] arr = fields[1].toCharArray();
					Staff name = new Staff(fields[0],arr);
					templist.add(name);
				}
			}
			reader.close();
			return templist;
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e)
		{
			System.out.println("Null error");
		}
		return null;
	}
	/**
	 * This method is used to add new staff's userID and password, appending to the end of the staff database (CSV format).
	 * @param filename A string containing the filename of the staff database.
	 * @param staff A staff object containing the staff's userID and password, ready to write back to database.
	 */
	public void updateStaff(String filename, Staff staff)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(staff.getUserID());
		sb.append(SEPARATOR);
		sb.append(staff.getPassword());
		updateOnly(filename,sb.toString());
		System.out.println("Updated DB to " + filename);
	}
	/**
	 * This method is used to read the various prices and rates needed to calculate the ticket price from the database (CSV format).
	 * @param filename A string containing the filename of the price settings.
	 * @return A list of price object containing various prices and rates needed to calculate ticket price.
	 */
	public List<Price> priceManager(String filename)	
	{
		String line;
		try {
			List<Price> templist =  new ArrayList<Price>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			reader.readLine();					//remove heading
			while((line = reader.readLine()) != null)
			{
				String [] fields = line.split(",");
				if(fields.length > 0)
				{
					float newSenior_Price = Float.parseFloat(fields[0]);
					float newAdult_Price = Float.parseFloat(fields[1]);
					float newChild_Price = Float.parseFloat(fields[2]);
					float newWeekendHoliday_Rate = Float.parseFloat(fields[3]);
					float newEcon_Rate = Float.parseFloat(fields[4]);
					float newVIP_Rate = Float.parseFloat(fields[5]);
					Price p = new Price(newSenior_Price,newAdult_Price,newChild_Price,newWeekendHoliday_Rate,newEcon_Rate,newVIP_Rate);
					templist.add(p);
				}
			}
			reader.close();
			return templist;
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e)
		{
			System.out.println("Null error");
		}
		return null;
	}
	/**
	 * This method is used to update changes to the prices and rates by admin staff to the database (CSV format).
	 * @param filename A string containing the filename of the price settings.
	 * @param s A list of price objects containing various prices and rates, ready to write back to database.
	 */
	public void updatePriceSetting(String filename, List<Price> s)
	{
		Price p = s.get(0);
		StringBuilder sb = new StringBuilder();
		sb.append(p.getSP());
		sb.append(SEPARATOR);
		sb.append(p.getAP());
		sb.append(SEPARATOR);
		sb.append(p.getCP());
		sb.append(SEPARATOR);
		sb.append(p.getWR());
		sb.append(SEPARATOR);
		sb.append(p.getER());
		sb.append(SEPARATOR);
		sb.append(p.getVR());
		replaceSelected(filename,sb.toString(), 1);
		System.out.println("Updated PriceRate Setting");
	}
	/**
	 * This method is used to update any changes made to the respective databases for logging purpose, exclusive of showing Top 5 list.
	 * @param filename A string containing filename of the changelog file (Text format).
	 * @param s A string containing changes made in respective sections, exclusive of showing Top 5 list. 
	 */
	public void updateChanglog(String filename, String s)				//Update Change Log
	{
		updateOnly(filename, s);
		System.out.println("Updated Changelog");
	}
	/**
	 * This method is used to read from the cineplex database (CSV format) and construct a list of cineplex objects for further processing.
	 * @param filename A string containing the filename of the cineplex database.
	 * @return A list of cineplex objects that contain its attributes.
	 */
	public List<Cineplex> cineplexManager(String filename)
	{
		String line;
		try {
			List<Cineplex> templist =  new ArrayList<Cineplex>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null)
			{
				String [] fields = line.split(",");
				if(fields.length > 0)
				{
					Cineplex cp = new Cineplex(fields[0],fields[1]);
					String [] cinemasNum = fields[2].split("\\^");		//SEPARATOR3
					for(int i=0; i < cinemasNum.length;i++)
					{
						String [] cinemasInfo = cinemasNum[i].split(SEPARATOR2);	
						classType cType = classType.valueOf(cinemasInfo[0]);
						int threaterNo = Integer.parseInt(cinemasInfo[1]);
						int numSeats = Integer.parseInt(cinemasInfo[2]);
						Cinemas cNo = new Cinemas(cType,threaterNo,numSeats);
						String [] seatInfo = cinemasInfo[3].split("_"); 
						List<Seat> seatPos = new ArrayList<Seat>();
						for(int j=0; j<seatInfo.length; j++)
						{
							Seat st = new Seat(seatInfo[j]);
							seatPos.add(st);
						}
						cNo.setSeats(seatPos);
						cp.addCinemas(cNo);
					}
					templist.add(cp);	
				}
			}
			reader.close();
			return templist;
		}catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e) {
			System.out.println("Null error");
		}
		return null;	
	}
	/**
	 * This method is used to save the updated information back into the cineplex database (CSV format).
	 * @param filename A string containing the filename of the cineplex database.
	 * @param data A list of cineplex objects that are ready to write back to the database.
	 */
	public void saveCineplex(String filename, List<Cineplex> data)
    {
		List<String> temp = new ArrayList<String>();
        for(int i=0; i < data.size();i++)
        {
            StringBuilder st = new StringBuilder();
            Cineplex cplex = data.get(i);
            st.append(cplex.getName());
            st.append(SEPARATOR);
            st.append(cplex.getLocation());
            st.append(SEPARATOR);
                List <Cinemas> cl = cplex.getCinemas();
                StringBuilder sb = new StringBuilder();
                for(int j=0; j < cl.size(); j++)
                {
                    Cinemas c = (Cinemas) cl.get(j);
                    sb.append(c.getCType());
                    sb.append(SEPARATOR2);
                    sb.append(c.geTheaterNo());
                    sb.append(SEPARATOR2);
                    sb.append(c.getnumSeats());
                    sb.append(SEPARATOR2);
                    sb.append(c.seat2String());
                    if(j< cl.size()-1) sb.append(SEPARATOR3);
                }
                st.append(sb);
            temp.add(st.toString());
        }
        write(filename,temp);
		System.out.println("Updated DB to " + filename);
    }
	/**
	 * This method is used to read from the customer database and construct a list of customer objects for further processing. 
	 * @param filename A string containing the filename of the customer database.
	 * @return A list of customer objects containing email, id, mobileNo and transaction ID.
	 */
	public List<Customer> custManager(String filename)
	{
		String line;
		try {
			List<Customer> templist =  new ArrayList<Customer>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) 
			{
				String [] fields = line.split(",");
				if(fields.length > 0) 
				{
					int mobileNo = Integer.parseInt(fields[2]);
					Customer cust = new Customer(fields[0],fields[1],mobileNo);
					String[] transID = fields[3].split(SEPARATOR2);
					for(int i=0; i<transID.length; i++)
					{
						cust.addtransaction(transID[i]);
					}
					templist.add(cust);
				}
			}
			reader.close();
			return templist;
		}catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e) {
			System.out.println("Null error");
		}
		return null;
	}
	/**
	 * This method is used to save the updated information to the customer database (CSV format).
	 * @param filename A string containing the filename of the customer database.
	 * @param data A list of customer objects that are ready to write back to the database.
	 */
	public void saveCustomer(String filename, List<Customer> data)
	{
		List<String> temp = new ArrayList<String>();
        for(int i=0; i < data.size();i++)
        {
            StringBuilder st = new StringBuilder();
            Customer cust = data.get(i);
            st.append(cust.getCustID());
            st.append(SEPARATOR);
            st.append(cust.getCustEmail());
            st.append(SEPARATOR);
            st.append(cust.getMobileNo());
            st.append(SEPARATOR);
            st.append(cust.list2String());
            temp.add(st.toString());
        }
        write(filename,temp);
		System.out.println("Updated DB to " + filename);
	}	
	/**
	 * This method is used to read from holiday database (Text format) and construct a list of string date for further processing.
	 * @param filename A string containing the filename of the holiday database.
	 * @return A string of string dates that is needed to calculate ticket prices.
	 */
	public List<String> holidayManager(String filename)
	{
		String line;
		try {
			List<String> templist =  new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null)
			{
				String [] fields = line.split(",");
				if(fields.length > 0)
				{
					for(int i=0; i < fields.length; i++)
					{
						templist.add(fields[i]);
					}
				}
			}
			reader.close();
			return templist;
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e)
		{
			System.out.println("Null error");
		}
		return null;
	}
	/**
	 * This method is used to update any modified dates back to the holiday database (Text format).
	 * @param filename A string containing the filename of the holiday database.
	 * @param data A list of string dates that are ready to write back to the holiday database.
	 */
	public void updateHolidayList(String filename, List<String> data)
	{
		write("holiday2019.txt", data);
	}
	/**
	 * This method is used to read from the movielist database (CSV format) and construct a list of movie objects for further processing.
	 * @param filename A string containing the filename of the movielist database.
	 * @return A list of movie objects that contain all the available movies and its details.
	 * 
	 */
	public List<Movie> movieManager(String filename)
	{
		String line;
		try {
			List<Movie> templist =  new ArrayList<Movie>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) 
			{
				String [] fields = line.split(",");
				if(fields.length > 0 && !line.trim().isEmpty()) 
				{
					filmRate fRate = filmRate.valueOf(fields[5]);
					int duration = Integer.parseInt(fields[7]);
					Movie m = new Movie(fields[0],fields[2],fields[3],fields[4],fRate,fields[6],duration);
					m.setStatus(fields[1]);
					m.setAvgRate(fields[8]);
					List <Review> review = new ArrayList<Review>();
					if(!fields[9].equals("Empty"))
					{
						String [] rList = fields[9].split("\\^");		//SEPARATOR3
						for(int i=0; i< rList.length;i++)
						{
							String [] rInfo = rList[i].split(SEPARATOR2);
							double rating = Double.parseDouble(rInfo[0]);
							Review r = new Review(rating,rInfo[1],rInfo[2]);
							review.add(r);
						}
					}
					m.setReview(review);
					templist.add(m);
				}
			}
			reader.close();
			return templist;
		}catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e) {
			System.out.println("Null error");
		}
		return null;
	}
	/**
	 * This method is used to update movielist database (CSV format) after the admim staff created an entry (movie object).
	 * @param filename A string containing the filename of the movielist database.
	 * @param m A newly created movie object ready to be updated back to the database.
	 */
	public void addNewMovie(String filename, Movie m)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(m.getTitle().replaceAll(",", ";"));
		sb.append(SEPARATOR);
		sb.append(m.getStatus());
		sb.append(SEPARATOR);
		sb.append(m.getSynopsis().replaceAll(",", ";"));
		sb.append(SEPARATOR);
		sb.append(m.getDirector().replaceAll(",", ";"));
		sb.append(SEPARATOR);
		sb.append(m.getCast().replaceAll(",", ";"));
		sb.append(SEPARATOR);
		sb.append(m.getFilmRate());
		sb.append(SEPARATOR);
		sb.append(m.getAirDate());
		sb.append(SEPARATOR);
		sb.append(m.getDuration());
		sb.append(SEPARATOR);
		sb.append(m.getAvgRate());
		sb.append(SEPARATOR);
		sb.append("Empty");			//No review
		updateOnly(filename,sb.toString());
		System.out.println("Added New Movie");
	}
	/**
	 * This method is used to save modified movie details back to the movielist database (CSV format). 
	 * @param filename A string containing the filename of the movielist database.
	 * @param mList A list of movie objects that are ready to write back to the database.
	 */
	public void saveMovie(String filename, List <Movie> mList)
	{
		List<String> temp = new ArrayList<String>();
        for(int i=0; i < mList.size();i++)
        {
            StringBuilder st = new StringBuilder();
            Movie m = (Movie) mList.get(i);
            st.append(m.getTitle().replaceAll(",", ";"));
            st.append(SEPARATOR);
            st.append(m.getStatus());
            st.append(SEPARATOR);           
            st.append(m.getSynopsis().replaceAll(",", ";"));
            st.append(SEPARATOR);           
            st.append(m.getDirector().replaceAll(",", ";"));
            st.append(SEPARATOR);           
    		st.append(m.getCast().replaceAll(",", ";"));
    		st.append(SEPARATOR);   		
    		st.append(m.getFilmRate());
    		st.append(SEPARATOR);  		
    		st.append(m.getAirDate());
    		st.append(SEPARATOR);   		
    		st.append(m.getDuration());
    		st.append(SEPARATOR);    		
    		st.append(m.getAvgRate());
    		st.append(SEPARATOR);
    		List<Review> rList = m.getReview();
    		StringBuilder sb = new StringBuilder();
    		if (!rList.isEmpty())
    		{
    			for(int j=0; j<rList.size();j++)
        		{
        			Review mReview = (Review) rList.get(j);
        			sb.append(mReview.getRating());
        			sb.append(SEPARATOR2);
        			sb.append(mReview.getComment().replaceAll(",", ";"));
        			sb.append(SEPARATOR2);
        			sb.append(mReview.getUser());
        			if(j < rList.size()-1) sb.append(SEPARATOR3);
        		}
    			st.append(sb);
    		}
    		else
    			st.append("Empty");
            temp.add(st.toString());
        }
        write(filename,temp);
		System.out.println("Updated DB to " + filename);		
	}
	/**
	 * This method is used to read from showtimes database (CSV format) and construct a list of showtimes objects for futher processing. 
	 * @param filename A string containing the filename of the showtimes datatbase.
	 * @return A list of showtimes objects containing all the available showtimes.
	 */
	public List<ShowTimes> showtimeManager(String filename)			// Read ShowTimeDB
	{
		String line;
		try {
			List<ShowTimes> templist =  new ArrayList<ShowTimes>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			//reader.readLine();					//remove heading
			while((line = reader.readLine()) != null) 
			{
				//if(line.trim().isEmpty())
				String [] fields = line.split(",");
				if(fields.length > 0 && !line.trim().isEmpty()) 
				{
					showStatus status = showStatus.valueOf(fields[1]);
					int threater = Integer.parseInt(fields[2]);
					int airTime = Integer.parseInt(fields[5]);
					filmRate fRate = filmRate.valueOf(fields[6]);
					classType cType = classType.valueOf(fields[7]);
					ShowTimes sT = new ShowTimes(fields[0],status,threater,fields[3],fields[4],airTime,fRate,cType);
					String seatArr = fields[8].replaceAll(";", ",");
					sT.setArrangement(seatArr);
					templist.add(sT);
				}
			}
			reader.close();
			return templist;
		}catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e) {
			System.out.println("Null error");
		}
		return null;		
	}
	/**
	 * This method is used to save modified showtimes objects back to the showtimes database (CSV format).
	 * @param filename A string containing the filename of the showtimes database.
	 * @param sTList A list of showtimes objects ready to write back to the database.
	 */
	public void saveShowTime(String filename, List <ShowTimes> sTList)		// save to ShowTimesDB
	{
		List<String> temp = new ArrayList<String>();
		for(int i=0; i < sTList.size();i++)
        {			
			StringBuilder sb = new StringBuilder();
			ShowTimes sT = sTList.get(i);
			sb.append(sT.getTitle());
			sb.append(SEPARATOR);
			sb.append(sT.getShowStatus());
			sb.append(SEPARATOR);
			sb.append(sT.getTheaterNo());
			sb.append(SEPARATOR);
			sb.append(sT.getCineplex());
			sb.append(SEPARATOR);
			sb.append(sT.getDate());
			sb.append(SEPARATOR);
			sb.append(sT.getAirTime());
			sb.append(SEPARATOR);
			sb.append(sT.getFilmRate());
			sb.append(SEPARATOR);
			sb.append(sT.getclassType());
			sb.append(SEPARATOR);
			sb.append(sT.getArrangement().replaceAll(",", ";"));
			temp.add(sb.toString());
        }
		write(filename,temp);
		System.out.println("Updated DB to " + filename);
	}
	/**
	 * This method is to add the new show time object (entry) created by admin staff into the showtimes database (CSV format).
	 * @param filename A string containing the filename of the showtimes object.
	 * @param sT A showtimes object which is newly created and ready to add to showtimes database.
	 */
	public void addShowTime(String filename, ShowTimes sT)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(sT.getTitle().replaceAll(",", ";"));
		sb.append(SEPARATOR);
		sb.append(sT.getShowStatus());
		sb.append(SEPARATOR);
		sb.append(sT.getTheaterNo());
		sb.append(SEPARATOR);
		sb.append(sT.getCineplex());
		sb.append(SEPARATOR);
		sb.append(sT.getDate());
		sb.append(SEPARATOR);
		sb.append(sT.getAirTime());
		sb.append(SEPARATOR);
		sb.append(sT.getFilmRate());
		sb.append(SEPARATOR);
		sb.append(sT.getclassType());
		sb.append(SEPARATOR);
		sb.append(sT.getArrangement().replaceAll(",", ";"));
		updateOnly(filename,sb.toString());
		System.out.println("Added New Show Time");
	}
	/**
	 * This method is used to read from booking database (CSV format) and construct a list of booking objects for further processing.
	 * @param filename A String containing the filename of the booking database.
	 * @return A list of booking objects that contains the past bookings that customers made.
	 */
	public List<Booking> bookingManager(String filename)
	{
		String line;
		try {
			List<Booking> templist =  new ArrayList<Booking>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) 
			{
				String [] fields = line.split(",");
				if(fields.length > 0 && !line.trim().isEmpty()) 
				{
					int mobile = Integer.parseInt(fields[1]);
					int cinemasNo = Integer.parseInt(fields[6]);
					int movieTime = Integer.parseInt(fields[8]);
					double totalPrice = Double.parseDouble(fields[9]);
					filmRate fRate = filmRate.valueOf(fields[10]);
					int noOfTix = Integer.parseInt(fields[11]);
					Booking book = new Booking(fields[0],mobile,fields[2],fields[3],fields[4],fields[5],cinemasNo,fields[7],movieTime,totalPrice,fRate,noOfTix,fields[12]);
					templist.add(book);
				}
			}
			reader.close();
			return templist;
		}catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {	
			System.out.println("IO error.");
		}
		catch(NullPointerException e) {
			System.out.println("Null error");
		}
		return null;		
	}
	/**
	 * This method is used to update modified information back to the booking database (CSV format).
	 * @param filename A string containing the filename of the booking database.
	 * @param bookList A list of booking objects ready to write back to the database.
	 */
	public void saveBooking(String filename, List <Booking> bookList)
	{
		List<String> temp = new ArrayList<String>();
		for(int i=0; i < bookList.size();i++)
        {			
			StringBuilder sb = new StringBuilder();
			Booking book = (Booking) bookList.get(i);
			sb.append(book.getemail());
			sb.append(SEPARATOR);
			sb.append(book.getmobile());
			sb.append(SEPARATOR);
			sb.append(book.gettransactionID());
			sb.append(SEPARATOR);
			sb.append(book.getmovieTitle());
			sb.append(SEPARATOR);
			sb.append(book.getmovieDate());
			sb.append(SEPARATOR);
			sb.append(book.getcineplexName());
			sb.append(SEPARATOR);
			sb.append(book.getcinemasNo());
			sb.append(SEPARATOR);
			sb.append(book.getlocation());
			sb.append(SEPARATOR);
			sb.append(book.getmovieTime());
			sb.append(SEPARATOR);
			sb.append(book.gettotalPrice());
			sb.append(SEPARATOR);
			sb.append(book.getfRate());
			sb.append(SEPARATOR);
			sb.append(book.getnoOfTix());
			sb.append(SEPARATOR);
			sb.append(book.getseats());			
			temp.add(sb.toString());
        }
		write(filename,temp);
		System.out.println("Updated DB to " + filename);
	}
	/**
	 * This method is used to add newly created booking object (entry) to the booking database (CSV format).
	 * @param filename A string containing the filename of the bokoing database.
	 * @param book A booking object contains new booking made by the customer, ready to write back to database.
	 */
	public void addBooking(String filename, Booking book)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(book.getemail());
		sb.append(SEPARATOR);
		sb.append(book.getmobile());
		sb.append(SEPARATOR);
		sb.append(book.gettransactionID());
		sb.append(SEPARATOR);
		sb.append(book.getmovieTitle());
		sb.append(SEPARATOR);
		sb.append(book.getmovieDate());
		sb.append(SEPARATOR);
		sb.append(book.getcineplexName());
		sb.append(SEPARATOR);
		sb.append(book.getcinemasNo());
		sb.append(SEPARATOR);
		sb.append(book.getlocation());
		sb.append(SEPARATOR);
		sb.append(book.getmovieTime());
		sb.append(SEPARATOR);
		sb.append(book.gettotalPrice());
		sb.append(SEPARATOR);
		sb.append(book.getfRate());
		sb.append(SEPARATOR);
		sb.append(book.getnoOfTix());
		sb.append(SEPARATOR);
		sb.append(book.getseats());
		updateOnly(filename,sb.toString());
		System.out.println("Added New Booking");
	}
	/**
	 * This method is used to used to create a Properties object that will read the configuration file for various databases' filenames.
	 * @return A properties object that is ready to be called at the start of the application to set constant strings representing filenames.
	 */
	public Properties db_Init()
	{
		Properties prop = new Properties();
		String fileName = "config.txt";
		InputStream is = null;
		try {
		    is = new FileInputStream(fileName);
		} catch (FileNotFoundException ex) {
		    System.out.println("not found");
		}
		try {
		    prop.load(is);
		} catch (IOException ex) {
			System.out.println("IO error");
		}
		return prop;
	}
	/**
	 * This method is used to write to specified filename with a list of string data. Default method to write back to database files. 
	 * @param filename A string containing the filename of the database.
	 * @param data A list of data formatted as string, ready to write to database.
	 */
//==================================================================================================================//
	private static void write(String filename, List<String> data)  
	{
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(filename));
			for (int i =0; i < data.size() ; i++) {
				out.println(data.get(i));
			}
			out.close();
		} catch (IOException e) {
			System.out.println("Failed to write to " + filename);
		}    
	}
	/**
	 * This method is used to replace one specific line based on the line count in the specified file without modifying the rest of the data.
	 * @param filename A string containing the filename of the specified database.
	 * @param replaceWith A string containing data that is used to replace existing line.
	 * @param count A integer that indicates the line number to replace.
	 * @exception IOException On input error.
	 * See IOException.
	 */
	private void replaceSelected(String filename, String replaceWith, int count) 
	{
		int i = 0;
		try {
			BufferedReader file = new BufferedReader(new FileReader(filename));
			StringBuilder input = new StringBuilder();
			String line;
			while((line = file.readLine()) != null)
			{
				if(i == count) line = replaceWith;
				i++;
				input.append(line);
				input.append("\n");
			}
			file.close();
			String inputStr = input.toString();
			//System.out.println(inputStr);
			FileOutputStream output = new FileOutputStream(filename);
			output.write(inputStr.getBytes());
			output.close();
		}catch(IOException e)
		{
			System.out.println("Failed to write to " + filename);
		}
	}
	/**
	 * This method is used to append a new entry in string format to the end of the specified database.
	 * @param filename A string containing the filename of the database.
	 * @param ls A string that is formatted and ready to write back to specified database.
	 * @exception IOException On input error.
	 * See IOException.
	 */
	private void updateOnly(String filename, String ls)
	{
		try {
			FileWriter fw = new FileWriter(filename,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(ls);
			out.close();
			}catch(IOException e)
			{
			System.out.println("IO Error");
			}
	}
}