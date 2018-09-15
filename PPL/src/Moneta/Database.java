
/***************************************************************************
  MONETA - Your Personal Money Manager
    Copyright (C) 2016 Komal Kangutkar
    				   Rupali More
    				   Pradyna Hulle
   
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
   
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
   
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
**************************************************************************/

package Moneta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

 class Database{
	
	public enum budget {OVERSPENT, WITHIN_UNDERSPENT, WITHIN_EXACT, VALID};
	
	Connection con = null;
	Statement state = null;
	ResultSet res = null;

	ResultSetMetaData md = null;
	String jdbc_driver = "com.mysql.jdbc.Driver";
	String mysqlpass = "";
	String db_url = "jdbc:mysql://localhost:3306/";
	int i = -1;
	int[] exp = new int[7];
	int[] inc = new int[5];
	int[] grp = new int[6];
	int netamt[] = new int[6];
	int small,minn,maxx;
	ArrayList <String> BY = new ArrayList<String>();
	
/*constructor*/	
	public Database(){
	}
	
	
	

	
	
/*create database*/
	boolean create_DB(){
		try{
			String USER = "root";
			
			Class.forName(jdbc_driver);
			System.out.println("Connecting...");
			con = DriverManager.getConnection(db_url, USER, mysqlpass);
			state = con.createStatement();
			System.out.println("Creating Database..");
			String sql = "CREATE DATABASE Moneta";
			state.executeUpdate(sql);
			System.out.println("Database Created!");
			connect();
			createUserTable();
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
/*make connection with the database*/
	void connect(){
		try{
			String db_url = "jdbc:mysql://localhost:3306/Moneta";
			String USER = "root";
			con = DriverManager.getConnection(db_url, USER, mysqlpass);
			con.setAutoCommit(false);
			state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(Exception ex){
		}
	}

	
	boolean createUserTable(){
		String createExpenseTable = "CREATE TABLE UserInfo "
				+ "(Username VARCHAR(255), " 
				+ "Password VARCHAR(255), "
				+ "Name VARCHAR(255), "
				+ "Email VARCHAR(255), "
				+ "Balance int, "
				+ "Budget int, "
				+ "Budget_period int, "
				+ "Spent int, "
				+ "BSD VARCHAR(255), "
				+ "PRIMARY KEY (Username))";
		try{
			con.setAutoCommit(false);
			state.executeUpdate(createExpenseTable);
			con.commit();
			return true;
		}catch(Exception ex){
		}
		return false;
	}
	
/*main */	
	public static void main(String[] args) {
		new GUI();
		
	}
	
/*returns days left for budget period to end*/	
	long daysLeft(String curr_user){
		long days = 0;
		long p;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.sql.Date curr_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		int budget_days = Integer.parseInt(getFromUPTable(curr_user, "Budget_period"));
		String bd = getFromUPTable(curr_user, "BSD");
		System.out.println(" 67" + bd + " " + curr_date);
		try{
			java.util.Date temp = sdf.parse(bd);
			java.sql.Date bsd = new Date(temp.getTime());
			p = curr_date.getTime() - bsd.getTime();
			days = budget_days - TimeUnit.DAYS.convert(p, TimeUnit.MILLISECONDS);
		}catch(Exception ex){
			
		}
		return days;
	}
	
/*returns spending amount left*/	
	int left(String curr_user){
		return Integer.parseInt(getFromUPTable(curr_user, "Budget")) 
					- Integer.parseInt(getFromUPTable(curr_user, "Spent"));
		
	}

/*creates a new table with user_name as its name*/
	boolean create_table(String user_name){
		String createExpenseTable = "CREATE TABLE " + user_name + "1 "
									+ "(Day VARCHAR(255), " 
									+ "Food int,"
									+ "Transport int, "
									+ "Shopping int, "
									+ "Family_Friends int, "
									+ "Health int, "
									+ "Education int, "
									+ "Others int, "
									+ "PRIMARY KEY (Day))";
		String createIncomeTable = "CREATE TABLE " + user_name + "2 "
									+ "(Day VARCHAR(255), "
									+ "Salary int, "
									+ "Award int, "
									+ "Gift int, "
									+ "Interest int, "
									+ "Other int,"
									+ "PRIMARY KEY (Day))";
									
																			
	try{
		con.setAutoCommit(false);
		state.executeUpdate(createExpenseTable);
		con.commit();
		con.setAutoCommit(false);
		state.executeUpdate(createIncomeTable);
		con.commit();
	
	}catch(Exception ex){
	}
	
		return true;

	}

/*gets the value from the UP table, acc label passed*/	
	String getFromUPTable(String curr_user, String label){
		String curr_value = " ";
		int temp = 0;
		try{
			String sql = "select * from UserInfo";
			res = state.executeQuery(sql);
			if(label == "BSD"){
				while(res.next()){
					if(curr_user.equals(res.getString("username"))){
						curr_value = res.getString(label);
						break;
					}
				}
			}else{
				while(res.next()){
					if(curr_user.equals(res.getString("username"))){
						temp = res.getInt(label);
						curr_value = Integer.toString(temp);
						break;
					}
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	
		return curr_value;
	}

/*updates the balance adds or subtracts amount*/	
	boolean updateBalance(String curr_user, int amount, int type){
		try{
			int value = 0;
			int spent = 0;
			String sql = "select * from UserInfo";
			res = state.executeQuery(sql);
			System.out.println("In update");
			
			while(res.next()){
				if(curr_user.equals(res.getString("username"))){
					if(type == 0){ //income
						value = amount + res.getInt("Balance");
					}else if(type == 1){ //expense
						value = res.getInt("Balance") - amount;
						spent = amount + res.getInt("Spent");
						res.updateInt("Spent", spent);
					}
					res.updateInt("Balance", value);
					res.updateRow();
					break;
				}
			}
		
		}catch(Exception ex){
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "Error!");
		}
		System.out.println("Out update");
		return true;
	}

/*budget validity*/
	Database.budget valid(String curr_user){
			int left = left(curr_user);
			long days = daysLeft(curr_user);
			if(left < 0){
				return budget.OVERSPENT;
			}
			if(days <= 0){ //budget period over
				if(left == 0){
					return budget.WITHIN_EXACT;
				}else if(left > 0)
					return budget.WITHIN_UNDERSPENT;
			}
			return budget.VALID;
	}


/*group input*/
	

	void groupInput(int s, int p) {    // p = x
		grp[s] = p;
	}
	
	String calci(int s) {
		String result = "<html>";
		try{
		int sum = 0, each;
		for(int g = 0; g < s; g++)
			sum += grp[g];
		each = sum / (s);
		for (int v = 0; v < s; v++)
			netamt[v] = grp[v] - each;
		
		for(int i = 0; i < s; i++) {
			int max = 0;
			int min = 0;
			for(int k = 0;k < s;k++)
				System.out.println(netamt[k]);
			for(int l = 0; l < s; l++) {
				if(netamt[max] < netamt[l]) {
					max = l;
				}
			}
			for(int y = 0; y < s; y++) {
				if(netamt[min] > netamt[y]) {
					min = y;
				}
			}
			int minn = -netamt[min];
			int maxx = netamt[max];								
			if(minn < maxx) {
				small = minn;
				netamt[max] -= minn;
				netamt[min] += minn;
			}
			else {
				small = maxx;
				netamt[max] -= maxx;
				netamt[min] += maxx;
			}
			
			if(small != 0)
				result += " " + BY.get(min) + " owes Rs. " + small + " to " + BY.get(max) + "<br>";
		}	
	
		result += "</html>";
		BY.clear();
		i = -1;
		}catch(Exception ex){
			System.out.println(ex);
		}
		return result;
	}
	
	
	
	String getToday(char category, String date, String curr_user){
		String transaction = "<html>";
		String sql = null;
		if(category == 'e'){
			sql = "select Day from " + curr_user + "1 where Day = '" + date + "'";
		}else if(category == 'i'){
			sql = "select Day from " + curr_user + "2 where Day = '" + date + "'";
		}
		int count = 0;
		int temp;
		try{
				res = state.executeQuery(sql);
				while(res.next()){
					count++;
				}
				System.out.println(count);
				if(count == 0){
					transaction += "No transactions";
					//JOptionPane.showMessageDialog(null, "No transactions on that date!");
				}else if (count == 1){
					if(category == 'e'){
						res = state.executeQuery("select * from " + curr_user + "1");
						while(res.next()){
							if(date.equals(res.getString("Day"))){
								//	transaction += "<b>EXPENSES:</b><br><br>";
									//food
									temp = res.getInt("Food");
									if(temp != 0)
										transaction += ("Food: " + temp + "<br>");
									//transport
									temp = res.getInt("Transport");
									if(temp != 0)
											transaction += ("Transport: " + temp + "<br>");
									//shopping
									temp = res.getInt("Shopping");
									if(temp != 0)
											transaction += ("Shopping: " + temp + "<br>");
									//Family friends
									temp = res.getInt("Family_Friends");
									if(temp != 0)
											transaction += ("Family/Friends: " + temp + "<br>");
									//health
									temp = res.getInt("Health");
									if(temp != 0)
											transaction += ("Health: " + temp + "<br>");
									//education
									temp = res.getInt("Education");
									if(temp != 0)
											transaction += ("Education: " + temp + "<br>");
									//others
									temp = res.getInt("Others");
									if(temp != 0)
											transaction += ("Others: " + temp + "<br>");
									break;
							}
						}
					}
					else if(category == 'i'){
						System.out.println("in i");
						res = state.executeQuery("select * from " + curr_user + "2");
							while(res.next()){
								if(date.equals(res.getString("Day"))){
							//	transaction += "<b>INCOMES:</b><br><br>";
									
									//salary
									temp = res.getInt("Salary");
									if(temp != 0)
											transaction += ("Salary: " + temp + "<br>");
									//award
									temp = res.getInt("Award");
									if(temp != 0)
										transaction += ("Award: " + temp + "<br>");
		
									//gift
									temp = res.getInt("Gift");
									if(temp != 0)
											transaction += ("Gift: " + temp + "<br>");
		
									//interest
									temp = res.getInt("Interest");
									if(temp != 0)
											transaction += ("Interest: " + temp + "<br>");
									//other
									temp = res.getInt("Other");
									if(temp != 0)
											transaction += ("Others: " + temp + "<br>");
									break;
	
							}
								
			
						}
					}
					transaction += "</html>";
					System.out.println(transaction);
				}
		}catch(Exception ex){
			System.out.println(ex);
		}
		return transaction;
	}
	
	
	String todayDate(){
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String qdate = df.format(date);
        System.out.println(qdate);
        return qdate;
	}
	
	
	
	
	class BarChart extends JPanel

	{
		BarChart() {
		}

	private Map<Color, Integer> bars =

	new LinkedHashMap<Color, Integer>();
	void drawGraph() {
	      JFrame frame = new JFrame("EXPENSE");
	      frame.getContentPane().add(new MyComponent());
	      //frame.setSize(300, 200);
	      frame.setSize(400, 430);
	      frame.setResizable(false);
	      frame.setVisible(true);
	      JFrame frame1 = new JFrame("INCOME");

	BarChart chart = new BarChart();

	
	 chart.addBar(Color.red, inc[0]);
	 chart.addBar(Color.green, inc[1]);
	 chart.addBar(Color.blue, inc[2]);
	 chart.addBar(Color.cyan, inc[3]);
	 chart.addBar(Color.yellow, inc[4]);

	frame1.getContentPane().add(chart);

	frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	frame1.pack();

	frame1.setVisible(true);
	   }

	 



	public void addBar(Color color, int value)

	{

	bars.put(color, value);
	repaint();

	}

	 

	@Override

	protected void paintComponent(Graphics g)

	{

	// determine longest bar

	 

	int max = Integer.MIN_VALUE;

	for (Integer value : bars.values())

	{

	max = Math.max(max, value);

	}

	 

	// paint bars

	 

	int width = (getWidth() / bars.size()) - 2;

	int x = 1;

	for (Color color : bars.keySet())

	{

	int value = bars.get(color);

	int height = (int)

	((getHeight()-5) * ((double)value / max));

	g.setColor(color);

	g.fillRect(x, getHeight() - height, width, height);

	g.setColor(Color.black);

	g.drawRect(x, getHeight() - height, width, height);

	x += (width + 2);

	}

	}

	 

	@Override

	public Dimension getPreferredSize() {

	return new Dimension(bars.size() * 50 + 2, 500);

	}

	 

	}

	class Slice {
	   double value;
	   Color color;
	   public Slice(double value, Color color) {  
	      this.value = value;
	      this.color = color;
	   }
	}
	class MyComponent extends JComponent {
		Slice[] slices = { 
				new Slice(exp[0], Color.pink), 
				new Slice(exp[1], Color.green),
				new Slice(exp[2], Color.yellow),
				new Slice(exp[3], Color.red),
				new Slice(exp[4], Color.cyan),
				new Slice(exp[5], Color.magenta),
				new Slice(exp[6], Color.orange)
		
		
		};
		MyComponent() {
		   
	   }
	   public void paint(Graphics g) {
	      drawPie((Graphics2D) g, getBounds(), slices);
	   }
	   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
	      double total = 0.0D;
	      for (int i = 0; i < slices.length; i++) {
	         total += slices[i].value;
	      }
	      double curValue = 0.0D;
	      int startAngle = 0;
	      for (int i = 0; i < slices.length; i++) {
	         startAngle = (int) (curValue * 360 / total);
	         int arcAngle = (int) (slices[i].value * 360 / total);
	         g.setColor(slices[i].color);
	         g.fillArc(area.x, area.y, area.width, area.height, 
	         startAngle, arcAngle);
	         curValue += slices[i].value;
	      }
	   }
	}
	
}
 
 