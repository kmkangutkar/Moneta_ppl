
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

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import javax.swing.SpringLayout;

public class GUI extends Database{
	JFrame frame;
	private JPanel containerPanel = new JPanel();
	CardLayout cl = new CardLayout();
	
	Calendar cal = Calendar.getInstance();
	
	String curr_user;
	String curr_expense, curr_income;
	int i = -1;
	
	
	//Welcome
	private JPanel Pwel;
	private JLabel welcomeLabel;
	private JLabel Welcome1;
	private JButton continueButton;
	JLabel lblBudget ;
	
	//home page
	private JPanel Phom;
	JButton expButton, incButton, trendButton, btnGroups, transacButton, btnToday;
	JLabel balLabel, leftLabel;
	JLabel lblDaysLeft;
	private JLabel lblHome;
	private JButton btnLogout;
	
	
	//login panel
	JPanel P1;
	JLabel userLabel;
	JLabel PasswordLabel;
	JTextField userText;
	JPasswordField PasswordText;
	JButton loginButton;
	JButton registerButton;
	
	
	//register
	JPanel Preg;
	JLabel nameLabel, u_nameLabel, newpassLabel, 
			setbal, mailLabel, setbudg, lineLabel;
	JTextField nameText, u_nameText, mailText, setbalText, setbudgText;
	JPasswordField newpassText;
	JButton saveButton, btnLater;
	
	//expense
	JPanel PEx;
	JButton add;
	JButton back;
	JTextField amtText;
	JLabel amt;
	JComboBox<String> category;
	JLabel categoryLabel;
	//UtilDateModel model;
	SqlDateModel model;
	JDatePanelImpl datePanel;
	Properties pr;
	JDatePickerImpl datePicker;
	Date selectedDate;
	private JLabel lblAddExpense;
	JButton eEditButton;
	
	//setbudget
	JPanel Pbudg;
	JTextField budgField, daysField;
	JLabel setBudgLabel, daysLabel;
	JButton setBudget;
	private JLabel lblDays;
	private JLabel lblEnterDetailsFor;
	
	//income
	JPanel PIn;
	JButton add1;
	JButton back1;
	JTextField amtText1;
	JLabel amt1;
	JComboBox<String> I_category;
	JLabel categoryLabel1;
	//UtilDateModel model;
	SqlDateModel model1;
	JDatePanelImpl datePanel1;
	Properties pr1;
	JDatePickerImpl datePicker1;
	private JLabel lblEnterDetails;
	JButton iEditButton;
	private JTextField budgetP;
	
	//group calculator
	JPanel Pgr1;
	JPanel Pgr2;
	JTextField subcatTF, grpamt, byTF;
	JLabel subcat, grpamt1, by, resultLabel;
	JButton grpadd, calci, homeButton;
	JComboBox<String> gsize;
	int size;
	
	//trends
	JPanel Ptre;
	JDatePanelImpl dP1, dP2;
	SqlDateModel model2, model3;
	Properties prop1;
	JDatePickerImpl trend_datePicker1;
	JDatePickerImpl trend_datePicker2;
	JLabel startDate, endDate;
	JButton trend, btnHome;
	private JLabel lblResults;
	private JLabel lblSelectGroupSize;
	
	//application SETUP
	JPanel Pfirst;
	JLabel enter;
	JButton btnEnter;
	JPasswordField sqlpassField;
	
	
	//today
	private JLabel lblExpenses;
	private JLabel lblIncomes;
	JPanel Ptoday;
	JLabel expensesToday, incomesToday;
	JButton btnHome_1;
	
	//transactions
	JComboBox<String> catBox;
	JPanel Ptransaction, expPanel, incPanel;
	private JTable expTable = new JTable();
	JTable incTable = new JTable();
	private JScrollPane scroller1, scroller2;
	JTabbedPane tp;
	JButton btnHome_2;
	DefaultTableModel dtm1, dtm2;
	Vector<String> column1, column2; 
	Vector data1, data2, row; 
	private SpringLayout springLayout;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JLabel lblNewLabel_3;
	private JLabel lblSalary_1;
	private JLabel lblNewLabel_4;
	private JLabel lblGift;
	private JLabel lblIntrest;
	private JLabel lblOther;
	private JLabel lblExpence;
	private JLabel lblFood;
	private JLabel lblTransport;
	private JLabel lblShopping;
	private JLabel lblHealth;
	private JLabel lblFamilyfriends;
	private JLabel lblEducation;
	private JLabel lblOther_1;
	
/*GUI constructor*/
	public GUI(){
		frame = new JFrame("MONETA");
	
		containerPanel.setLayout(cl);
		
		Pfirst = new JPanel();
		P1 = new JPanel();
		PEx = new JPanel();
		PIn = new JPanel();
		Preg = new JPanel();
		Pwel = new JPanel();
		Phom = new JPanel();
		Pbudg = new JPanel();
		Pgr1 = new JPanel();
		Pgr2 = new JPanel();
		Ptre = new JPanel();
		Ptoday = new JPanel();
		Ptransaction = new JPanel();
		
		frame.setSize(587,377);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setUpPfirst();
		setUpP1();
		setUpPEx();
		setUpPIn();
		setUpPreg();
		setUpPwel();
		setUpPbudg();
		setUpPtre();
		setUpgr1();
		setUpgr2();
		setUpPtoday();
		
		containerPanel.add(Pfirst, "setup");
		containerPanel.add(P1, "login");
		containerPanel.add(PEx, "expense");
		containerPanel.add(PIn, "income");
		containerPanel.add(Preg, "register");
	    containerPanel.add(Pwel, "welcome");
	    containerPanel.add(Phom, "home");
	    containerPanel.add(Pbudg, "budget");
	    containerPanel.add(Pgr1, "group1");
	    containerPanel.add(Ptre, "trends");
	    containerPanel.add(Pgr2, "group2");
	  	containerPanel.add(Ptransaction, "transactions");
	   	containerPanel.add(Ptoday, "today");
	  
	   	
	   	DB_exists();
	   	
		
		frame.getContentPane().add(containerPanel);
		frame.setVisible(true);		
	}
	
	
	
/*setup first panel*/	
	void setUpPfirst(){
		enter = new JLabel("Please enter MySQL root Password:");
		enter.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		sqlpassField = new JPasswordField(13);
		JLabel lblMonetaSetup = new JLabel("Moneta Setup");
		lblMonetaSetup.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnEnter = new JButton("Enter");
		GroupLayout gl_Pfirst = new GroupLayout(Pfirst);
		gl_Pfirst.setHorizontalGroup(
				gl_Pfirst.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Pfirst.createSequentialGroup()
						.addGroup(gl_Pfirst.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Pfirst.createSequentialGroup()
										.addGap(220)
										.addComponent(sqlpassField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_Pfirst.createSequentialGroup()
										.addGap(49)
										.addComponent(lblMonetaSetup))
								.addGroup(gl_Pfirst.createSequentialGroup()
										.addGap(139)
										.addGroup(gl_Pfirst.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnEnter)
												.addComponent(enter))))
						.addContainerGap(178, Short.MAX_VALUE))
				);
		gl_Pfirst.setVerticalGroup(
				gl_Pfirst.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Pfirst.createSequentialGroup()
						.addGap(42)
						.addComponent(lblMonetaSetup)
						.addGap(60)
						.addComponent(enter)
						.addGap(37)
						.addComponent(sqlpassField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(61)
						.addComponent(btnEnter)
						.addContainerGap(77, Short.MAX_VALUE))
				);
		Pfirst.setLayout(gl_Pfirst);
	}
	
/*for JDatePicker*/	
	class DateLabelFormatter extends AbstractFormatter {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String datePattern = "dd-MM-yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}

/*checking if database exists and getting password*/	
	boolean DB_exists(){
		try{
		String current = new java.io.File( "." ).getCanonicalPath();
	    System.out.println("Current dir:"+current);
		File f = new File(current + "/money.txt");
		if(f.exists()){
			FileReader fr = new FileReader(current + "/money.txt");
			BufferedReader buffreader = new BufferedReader(fr);
			String text = buffreader.readLine();
			System.out.println(text);
			mysqlpass = text.trim();
			buffreader.close();
			if(create_DB())
				System.out.println("Successfully created new Database and added Table UserInfo!");
			else{
				System.out.println("Database already created!");
				connect();
			}
			cl.show(containerPanel, "login");
			button_actions1();
			return true;
		}else{
			cl.show(containerPanel, "setup");
			try{
				btnEnter.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						char[] pass = sqlpassField.getPassword();
						mysqlpass = String.valueOf(pass);
						try{
							Class.forName(jdbc_driver);
							con = DriverManager.getConnection(db_url, "root", mysqlpass);
							state = con.createStatement();
							f.createNewFile();
							FileWriter fw = new FileWriter(f);
							BufferedWriter buff = new BufferedWriter(fw);
							buff.write(mysqlpass);
							buff.close();
							DB_exists();
						}catch(Exception ex){
							JOptionPane.showMessageDialog(null, "Incorrect password!");
						}
					}
				});
			}catch(Exception e){
				System.out.println(e);
			}
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}	
	
	
	
	
/*check validity*/	
	void validCheck(){
		Database.budget p = valid(curr_user);
		int where = 0;
		switch(p){
		case VALID:
			break;
		case WITHIN_EXACT: 
			System.out.println("Budget period over, spent exact!");
			JOptionPane.showMessageDialog(null, "Budget period over! Spent exact");
			where = 1;
			break;
		case WITHIN_UNDERSPENT:
			JOptionPane.showMessageDialog(null, "Budget period over! Saved: Rs. " + left(curr_user));
			where = 1;
			break;
		case OVERSPENT:
			JOptionPane.showMessageDialog(null, "Overspent! by Rs. " + (-1 * left(curr_user)));
			where = 1;
			break;
		default:
			break;
		}
		if(where == 1){
			cl.show(containerPanel, "budget");
			button_actions7();
		}
			
	}
		
/*update labels on home panel*/	
	void updateHomeLabels(){
	balLabel.setText("Balance: Rs. " + getFromUPTable(curr_user, "Balance"));
	leftLabel.setText("Remaining Amount: Rs. " + left(curr_user));
	lblBudget.setText("Budget: Rs. " + getFromUPTable(curr_user, "Budget"));
}
	
/*setup trends panel*/
	void setUpPtre(){
		startDate = new JLabel("Select start date: ");
		endDate = new JLabel("Select end date: ");
		trend = new JButton("Show Trends");
		
		model2 = new SqlDateModel();
		model3 = new SqlDateModel();
		prop1 = new Properties();
		prop1.put("text.today", "Today");
		prop1.put("text.month", "Month");
		prop1.put("text.year", "Year");
		dP1 = new JDatePanelImpl(model2, prop1);
		dP2 = new JDatePanelImpl(model3, prop1);
		trend_datePicker1 = new JDatePickerImpl(dP1, new DateLabelFormatter());
		trend_datePicker2 = new JDatePickerImpl(dP2, new DateLabelFormatter());
		trend_datePicker1.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		trend_datePicker2.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		
		 JLabel lblTrends = new JLabel("TRENDS:");
		    lblTrends.setFont(new Font("Tahoma", Font.BOLD, 13));
		    
		    btnHome = new JButton("Home");
		    
		    textField = new JTextField();
		    textField.setBackground(Color.RED);
		    textField.setColumns(10);
		    
		    JLabel lblSalary = new JLabel("");
		    
		    textField_4 = new JTextField();
		    textField_4.setBackground(Color.GREEN);
		    textField_4.setColumns(10);
		    
		    textField_5 = new JTextField();
		    textField_5.setBackground(Color.BLUE);
		    textField_5.setColumns(10);
		    
		    textField_6 = new JTextField();
		    textField_6.setBackground(Color.CYAN);
		    textField_6.setColumns(10);
		    
		    textField_7 = new JTextField();
		    textField_7.setBackground(Color.YELLOW);
		    textField_7.setColumns(10);
		    
		    textField_8 = new JTextField();
		    textField_8.setBackground(Color.PINK);
		    textField_8.setColumns(10);
		    
		    textField_9 = new JTextField();
		    textField_9.setBackground(Color.GREEN);
		    textField_9.setColumns(10);
		    
		    textField_10 = new JTextField();
		    textField_10.setBackground(Color.YELLOW);
		    textField_10.setColumns(10);
		    
		    textField_11 = new JTextField();
		    textField_11.setBackground(Color.RED);
		    textField_11.setColumns(10);
		    
		    textField_12 = new JTextField();
		    textField_12.setBackground(Color.CYAN);
		    textField_12.setColumns(10);
		    
		    textField_13 = new JTextField();
		    textField_13.setBackground(Color.MAGENTA);
		    textField_13.setColumns(10);
		    
		    textField_14 = new JTextField();
		    textField_14.setBackground(Color.ORANGE);
		    textField_14.setColumns(10);
		    
		    lblNewLabel_3 = new JLabel("Income");
		    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		    
		    lblSalary_1 = new JLabel("Salary");
		    
		    lblNewLabel_4 = new JLabel("Award");
		    
		    lblGift = new JLabel("Gift");
		    
		    lblIntrest = new JLabel("Interest");
		    
		    lblOther = new JLabel("Other");
		    
		    lblExpence = new JLabel("Expense");
		    lblExpence.setFont(new Font("Tahoma", Font.BOLD, 12));
		    
		    lblFood = new JLabel("Food");
		    
		    lblTransport = new JLabel("Transport");
		    
		    lblShopping = new JLabel("Shopping");
		    
		    lblHealth = new JLabel("Health");
		    
		    lblFamilyfriends = new JLabel("Family_Friends");
		    
		    lblEducation = new JLabel("Education");
		    
		    lblOther_1 = new JLabel("Other");
		    GroupLayout gl_Ptre = new GroupLayout(Ptre);
		    gl_Ptre.setHorizontalGroup(
		    	gl_Ptre.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Ptre.createSequentialGroup()
		    			.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    				.addGroup(gl_Ptre.createSequentialGroup()
		    					.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addContainerGap()
		    							.addComponent(lblTrends))
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addGap(42)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    								.addComponent(trend_datePicker1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
		    								.addGroup(gl_Ptre.createSequentialGroup()
		    									.addComponent(startDate)
		    									.addGap(152)
		    									.addComponent(endDate)))))
		    					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
		    				.addGroup(gl_Ptre.createSequentialGroup()
		    					.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addGap(62)
		    							.addComponent(btnHome)
		    							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
		    							.addComponent(lblNewLabel_3))
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addContainerGap(37, Short.MAX_VALUE)
		    							.addComponent(lblSalary)
		    							.addGap(57)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    								.addComponent(lblOther)
		    								.addComponent(lblSalary_1)
		    								.addComponent(lblNewLabel_4)
		    								.addComponent(lblGift)
		    								.addComponent(lblIntrest))
		    							.addGap(18)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
		    					.addGap(6)
		    					.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addComponent(lblFamilyfriends)
		    							.addGap(18)
		    							.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addComponent(trend_datePicker2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
		    							.addGap(70))
		    						.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    								.addGroup(gl_Ptre.createSequentialGroup()
		    									.addComponent(lblTransport)
		    									.addGap(18)
		    									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		    								.addGroup(gl_Ptre.createSequentialGroup()
		    									.addComponent(lblFood)
		    									.addGap(29)
		    									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
		    							.addGroup(gl_Ptre.createSequentialGroup()
		    								.addComponent(lblShopping)
		    								.addGap(18)
		    								.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
		    					.addGap(44)))
		    			.addPreferredGap(ComponentPlacement.RELATED)
		    			.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    				.addComponent(trend)
		    				.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING, false)
		    					.addGroup(gl_Ptre.createSequentialGroup()
		    						.addComponent(lblEducation)
		    						.addGap(18)
		    						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		    					.addGroup(gl_Ptre.createSequentialGroup()
		    						.addComponent(lblHealth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		    						.addGap(18)
		    						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		    					.addGroup(gl_Ptre.createSequentialGroup()
		    						.addPreferredGap(ComponentPlacement.RELATED, 19, GroupLayout.PREFERRED_SIZE)
		    						.addComponent(lblOther_1)
		    						.addGap(18)
		    						.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
		    				.addComponent(lblExpence))
		    			.addGap(57))
		    );
		    gl_Ptre.setVerticalGroup(
		    	gl_Ptre.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Ptre.createSequentialGroup()
		    			.addContainerGap()
		    			.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    				.addComponent(trend)
		    				.addGroup(gl_Ptre.createSequentialGroup()
		    					.addComponent(lblTrends)
		    					.addPreferredGap(ComponentPlacement.UNRELATED)
		    					.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    						.addComponent(startDate)
		    						.addComponent(endDate))
		    					.addPreferredGap(ComponentPlacement.RELATED)
		    					.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    						.addComponent(trend_datePicker2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    						.addComponent(trend_datePicker1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		    			.addGap(46)
		    			.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    				.addGroup(gl_Ptre.createSequentialGroup()
		    					.addComponent(lblSalary)
		    					.addGap(154))
		    				.addGroup(gl_Ptre.createSequentialGroup()
		    					.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addGap(15)
		    							.addComponent(lblExpence)
		    							.addGap(29)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    								.addGroup(Alignment.LEADING, gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    									.addGroup(gl_Ptre.createSequentialGroup()
		    										.addGroup(gl_Ptre.createSequentialGroup()
		    											.addGroup(gl_Ptre.createSequentialGroup()
		    												.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    													.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    													.addComponent(lblHealth))
		    												.addPreferredGap(ComponentPlacement.UNRELATED)
		    												.addComponent(lblEducation)
		    												.addPreferredGap(ComponentPlacement.RELATED))
		    											.addGap(25))
		    										.addGap(9))
		    									.addGroup(gl_Ptre.createSequentialGroup()
		    										.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    										.addPreferredGap(ComponentPlacement.RELATED)
		    										.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    											.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    											.addComponent(lblOther_1))))
		    								.addGroup(gl_Ptre.createSequentialGroup()
		    									.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    										.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    										.addComponent(lblFood))
		    									.addPreferredGap(ComponentPlacement.UNRELATED)
		    									.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    										.addGroup(gl_Ptre.createSequentialGroup()
		    											.addGap(7)
		    											.addComponent(lblTransport))
		    										.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		    									.addGap(18)
		    									.addGroup(gl_Ptre.createParallelGroup(Alignment.LEADING)
		    										.addComponent(lblShopping, Alignment.TRAILING)
		    										.addComponent(textField_10, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		    							.addPreferredGap(ComponentPlacement.UNRELATED)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    								.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    								.addGroup(gl_Ptre.createSequentialGroup()
		    									.addComponent(lblFamilyfriends)
		    									.addPreferredGap(ComponentPlacement.RELATED))))
		    						.addGroup(gl_Ptre.createSequentialGroup()
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    								.addComponent(lblNewLabel_3)
		    								.addComponent(btnHome))
		    							.addGap(20)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(lblSalary_1))
		    							.addPreferredGap(ComponentPlacement.UNRELATED)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(lblNewLabel_4))
		    							.addPreferredGap(ComponentPlacement.UNRELATED)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(lblGift))
		    							.addPreferredGap(ComponentPlacement.RELATED)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.TRAILING)
		    								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(lblIntrest))
		    							.addPreferredGap(ComponentPlacement.UNRELATED)
		    							.addGroup(gl_Ptre.createParallelGroup(Alignment.BASELINE)
		    								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    								.addComponent(lblOther))))
		    					.addGap(25)))
		    			.addGap(20))
		    );
		    Ptre.setLayout(gl_Ptre);
		
	}

/*reload table*/
	void reload(char t){
	//expTable
	if(t == 'e'){	
		try{
	   		res = state.executeQuery("select * from " + curr_expense);
	   		md = res.getMetaData();
	   		int c1 = md.getColumnCount();
	   		column1 = new Vector<String>(c1);
	   		for(int i = 1; i <= c1; i++){
	   			column1.add(md.getColumnName(i));
	   		}
	   		data1 = new Vector();
	   		while(res.next()){
	   			row = new Vector(c1);
	   			for(int i = 1; i <= c1; i++){
	   				row.add(res.getString(i));
	   			}
	   			data1.add(row);
	   		}
	   	 //	expTable.setModel(dtm);
	   	}catch(Exception ex){
	   		System.out.println(ex);
	   		System.out.println("rijgi");	
	   	}
		expTable.setModel( new DefaultTableModel(data1, column1){
			@Override
				public boolean isCellEditable(int row, int column){
					//all false
					return false;
				}
			});

	}
	//incTable	
	else if(t == 'i'){	
		
	   	try{
	   		res = state.executeQuery("select * from " + curr_income);
	   		md = res.getMetaData();
	   		int c1 = md.getColumnCount();
	   		column2 = new Vector<String>(c1);
	   		for(int i = 1; i <= c1; i++){
	   			column2.add(md.getColumnName(i));
	   		}
	   		data2 = new Vector();
	   		while(res.next()){
	   			row = new Vector(c1);
	   			for(int i = 1; i <= c1; i++){
	   				row.add(res.getString(i));
	   			}
	   			data2.add(row);
	   		}
	   		
	   	}catch(Exception ex){
	   		System.out.println(ex);
	   	}

	   	incTable.setModel( new DefaultTableModel(data2, column2){
			@Override
				public boolean isCellEditable(int row, int column){
					return false;
				}
			});

		}
	}
	
/*setup transaction panel*/
	void setUpPtransaction(){
		tp = new JTabbedPane();
	
		reload('e');
	   	scroller1 = new JScrollPane(expTable);
	   	expPanel = new JPanel();
	   	
	   	reload('i');
	   	scroller2 = new JScrollPane(incTable);
	   	incPanel = new JPanel();
	   	
	   	tp.addTab("Expenses", expPanel);
	   	GroupLayout gl_expPanel = new GroupLayout(expPanel);
	   	gl_expPanel.setHorizontalGroup(
	   		gl_expPanel.createParallelGroup(Alignment.LEADING)
	   			.addGroup(gl_expPanel.createSequentialGroup()
	   				.addContainerGap()
	   				.addComponent(scroller1, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
	   				.addContainerGap())
	   	);
	   	gl_expPanel.setVerticalGroup(
	   		gl_expPanel.createParallelGroup(Alignment.LEADING)
	   			.addGroup(gl_expPanel.createSequentialGroup()
	   				.addContainerGap()
	   				.addComponent(scroller1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
	   				.addContainerGap(20, Short.MAX_VALUE))
	   	);
	   	expPanel.setLayout(gl_expPanel);
	   	tp.addTab("Incomes", incPanel);
	   	GroupLayout gl_incPanel = new GroupLayout(incPanel);
	   	gl_incPanel.setHorizontalGroup(
	   		gl_incPanel.createParallelGroup(Alignment.LEADING)
	   			.addGroup(gl_incPanel.createSequentialGroup()
	   				.addContainerGap()
	   				.addComponent(scroller2, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
	   				.addContainerGap())
	   	);
	   	gl_incPanel.setVerticalGroup(
	   		gl_incPanel.createParallelGroup(Alignment.LEADING)
	   			.addGroup(gl_incPanel.createSequentialGroup()
	   				.addContainerGap()
	   				.addComponent(scroller2, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
	   				.addContainerGap(19, Short.MAX_VALUE))
	   	);
	   	incPanel.setLayout(gl_incPanel);
	   	
		btnHome_2 = new JButton("Home");
	  	
	  	JLabel lblTransactions = new JLabel("TRANSACTIONS:");
	  	lblTransactions.setFont(new Font("Tahoma", Font.BOLD, 13));
		  
	  	GroupLayout gl_Ptransaction = new GroupLayout(Ptransaction);
	  	gl_Ptransaction.setHorizontalGroup(
	  		gl_Ptransaction.createParallelGroup(Alignment.TRAILING)
	  			.addGroup(gl_Ptransaction.createSequentialGroup()
	  				.addComponent(tp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	  				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	  			.addGroup(gl_Ptransaction.createSequentialGroup()
	  				.addGap(27)
	  				.addComponent(lblTransactions)
	  				.addPreferredGap(ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
	  				.addComponent(btnHome_2)
	  				.addGap(78))
	  	);
	  	gl_Ptransaction.setVerticalGroup(
	  		gl_Ptransaction.createParallelGroup(Alignment.LEADING)
	  			.addGroup(gl_Ptransaction.createSequentialGroup()
	  				.addGap(16)
	  				.addGroup(gl_Ptransaction.createParallelGroup(Alignment.BASELINE)
	  					.addComponent(btnHome_2)
	  					.addComponent(lblTransactions))
	  				.addPreferredGap(ComponentPlacement.RELATED)
	  				.addComponent(tp, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
	  				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	  	);
	  	Ptransaction.setLayout(gl_Ptransaction);
	}
	
/*setup today panel*/
	void setUpPtoday(){
		expensesToday = new JLabel("");
		expensesToday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		incomesToday = new JLabel("");
		incomesToday.setFont(new Font("Tahoma", Font.PLAIN, 16));
	 	
	 	JLabel lblNewLabel_2 = new JLabel("TODAY:");
	 	lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
	 	
	 	btnHome_1 = new JButton("Home");
	 	btnHome_1.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			cl.show(containerPanel, "home");
	 		}
	 	});
	 	
	 	lblExpenses = new JLabel("EXPENSES");
	 	lblExpenses.setFont(new Font("Tahoma", Font.BOLD, 16));
	 	
	 	lblIncomes = new JLabel("INCOMES");
	 	lblIncomes.setFont(new Font("Tahoma", Font.BOLD, 16));
	 	GroupLayout gl_Ptoday = new GroupLayout(Ptoday);
	 	gl_Ptoday.setHorizontalGroup(
	 		gl_Ptoday.createParallelGroup(Alignment.TRAILING)
	 			.addGroup(gl_Ptoday.createSequentialGroup()
	 				.addGap(23)
	 				.addComponent(btnHome_1)
	 				.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
	 				.addGroup(gl_Ptoday.createParallelGroup(Alignment.LEADING, false)
	 					.addGroup(gl_Ptoday.createSequentialGroup()
	 						.addComponent(lblNewLabel_2)
	 						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	 					.addGroup(Alignment.TRAILING, gl_Ptoday.createSequentialGroup()
	 						.addGroup(gl_Ptoday.createParallelGroup(Alignment.LEADING)
	 							.addComponent(lblExpenses)
	 							.addComponent(expensesToday, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
	 						.addGap(69)
	 						.addGroup(gl_Ptoday.createParallelGroup(Alignment.LEADING)
	 							.addComponent(lblIncomes)
	 							.addComponent(incomesToday, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
	 						.addGap(81))))
	 	);
	 	gl_Ptoday.setVerticalGroup(
	 		gl_Ptoday.createParallelGroup(Alignment.TRAILING)
	 			.addGroup(gl_Ptoday.createSequentialGroup()
	 				.addContainerGap(35, Short.MAX_VALUE)
	 				.addGroup(gl_Ptoday.createParallelGroup(Alignment.BASELINE)
	 					.addComponent(btnHome_1)
	 					.addComponent(lblNewLabel_2))
	 				.addGap(33)
	 				.addGroup(gl_Ptoday.createParallelGroup(Alignment.BASELINE)
	 					.addComponent(lblExpenses)
	 					.addComponent(lblIncomes))
	 				.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
	 				.addGroup(gl_Ptoday.createParallelGroup(Alignment.TRAILING)
	 					.addComponent(incomesToday, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
	 					.addComponent(expensesToday, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
	 				.addGap(41))
	 	);
	   	Ptoday.setLayout(gl_Ptoday);
	   	
		
		
	}
	
/*setup group1*/
	void setUpgr1(){
		subcatTF = new JTextField(10);
		grpamt = new JTextField(10);
		byTF = new JTextField(10);
		grpamt1 = new JLabel("Amount: Rs.");
		by = new JLabel("By ");
		grpadd = new JButton("Add");
		subcat = new JLabel("Sub-category");
		String[] size = {"3", "4", "5", "6"};
		 JLabel lblGroupCalculator = new JLabel("GROUP CALCULATOR:");
		    lblGroupCalculator.setFont(new Font("Tahoma", Font.BOLD, 13));
		    
		    gsize = new JComboBox();
		    gsize.setModel(new DefaultComboBoxModel(size));
		    
		    lblSelectGroupSize = new JLabel("Select group size");
		    
		    homeButton = new JButton("Home");
		    GroupLayout gl_Pgr1 = new GroupLayout(Pgr1);
		    gl_Pgr1.setHorizontalGroup(
		    	gl_Pgr1.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Pgr1.createSequentialGroup()
		    			.addGroup(gl_Pgr1.createParallelGroup(Alignment.LEADING)
		    				.addGroup(gl_Pgr1.createSequentialGroup()
		    					.addGap(34)
		    					.addComponent(lblGroupCalculator))
		    				.addGroup(gl_Pgr1.createSequentialGroup()
		    					.addGap(182)
		    					.addGroup(gl_Pgr1.createParallelGroup(Alignment.TRAILING)
		    						.addGroup(gl_Pgr1.createSequentialGroup()
		    							.addComponent(by)
		    							.addGap(42))
		    						.addGroup(gl_Pgr1.createParallelGroup(Alignment.LEADING)
		    							.addComponent(grpamt1)
		    							.addComponent(lblSelectGroupSize)
		    							.addComponent(subcat)))
		    					.addGap(21)
		    					.addGroup(gl_Pgr1.createParallelGroup(Alignment.LEADING)
		    						.addComponent(byTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    						.addGroup(gl_Pgr1.createParallelGroup(Alignment.LEADING)
		    							.addComponent(gsize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    							.addComponent(subcatTF, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    							.addComponent(grpamt, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))))
		    			.addContainerGap(252, Short.MAX_VALUE))
		    		.addGroup(Alignment.TRAILING, gl_Pgr1.createSequentialGroup()
		    			.addGap(49)
		    			.addComponent(homeButton)
		    			.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
		    			.addComponent(grpadd)
		    			.addGap(96))
		    );
		    gl_Pgr1.setVerticalGroup(
		    	gl_Pgr1.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Pgr1.createSequentialGroup()
		    			.addGap(35)
		    			.addComponent(lblGroupCalculator)
		    			.addGap(33)
		    			.addGroup(gl_Pgr1.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(gsize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(lblSelectGroupSize))
		    			.addGap(18)
		    			.addGroup(gl_Pgr1.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(subcatTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(subcat))
		    			.addGap(29)
		    			.addGroup(gl_Pgr1.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(grpamt1)
		    				.addComponent(grpamt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		    			.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
		    			.addGroup(gl_Pgr1.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(by)
		    				.addComponent(byTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		    			.addGap(43)
		    			.addGroup(gl_Pgr1.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(grpadd)
		    				.addComponent(homeButton))
		    			.addGap(40))
		    );
		    Pgr1.setLayout(gl_Pgr1);

		
	}

/*setup group2*/
	void setUpgr2(){
		calci = new JButton("Split Equally");
		calci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Pgr2.add(homeButton);
		 
		 lblResults = new JLabel("Results:");
		 lblResults.setFont(new Font("Tahoma", Font.BOLD, 15));
		 
		 resultLabel = new JLabel(".....");
		 resultLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
		 GroupLayout gl_Pgr2 = new GroupLayout(Pgr2);
		 gl_Pgr2.setHorizontalGroup(
		 	gl_Pgr2.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_Pgr2.createSequentialGroup()
		 			.addContainerGap(224, Short.MAX_VALUE)
		 			.addComponent(calci, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
		 			.addGap(208))
		 		.addGroup(gl_Pgr2.createSequentialGroup()
		 			.addGap(48)
		 			.addGroup(gl_Pgr2.createParallelGroup(Alignment.LEADING)
		 				.addComponent(homeButton)
		 				.addComponent(lblResults))
		 			.addContainerGap(474, Short.MAX_VALUE))
		 		.addGroup(gl_Pgr2.createSequentialGroup()
		 			.addGap(80)
		 			.addComponent(resultLabel, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(132, Short.MAX_VALUE))
		 );
		 gl_Pgr2.setVerticalGroup(
		 	gl_Pgr2.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_Pgr2.createSequentialGroup()
		 			.addGap(38)
		 			.addComponent(calci, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
		 			.addGap(27)
		 			.addComponent(lblResults)
		 			.addGap(18)
		 			.addComponent(resultLabel)
		 			.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
		 			.addComponent(homeButton)
		 			.addGap(38))
		 );
		    Pgr2.setLayout(gl_Pgr2);

	}

/*setup home panel*/	
	void setUpPhom(){
		
		expButton = new JButton("ADD/EDIT EXPENSES");
		expButton.setForeground(Color.BLACK);
		incButton = new JButton("ADD/EDIT INCOMES");
		trendButton = new JButton("TRENDS");
		transacButton = new JButton("VIEW TRANSACTIONS");
		balLabel = new JLabel("Balance: Rs. " + getFromUPTable(curr_user, "Balance"));
		balLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
		leftLabel = new JLabel("Remaining Amount: Rs. " + left(curr_user));
		leftLabel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		
		lblDaysLeft = new JLabel("Days left: " + daysLeft(curr_user));
		lblDaysLeft.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblBudget = new JLabel("Budget: Rs. " + getFromUPTable(curr_user, "Budget"));
		lblBudget.setForeground(Color.BLUE);
		lblBudget.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnGroups = new JButton("GROUP CALCULATOR");
		lblHome = new JLabel("Home");			    
		btnLogout = new JButton("Logout");	   
		btnLogout.setVisible(false);
		btnToday = new JButton("TODAY");
			    
			    GroupLayout gl_Phom = new GroupLayout(Phom);
			    gl_Phom.setHorizontalGroup(
			    	gl_Phom.createParallelGroup(Alignment.LEADING)
			    		.addGroup(gl_Phom.createSequentialGroup()
			    			.addContainerGap()
			    			.addComponent(lblHome)
			    			.addPreferredGap(ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
			    			.addComponent(btnLogout)
			    			.addGap(37))
			    		.addGroup(gl_Phom.createSequentialGroup()
			    			.addGap(78)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.LEADING)
			    				.addComponent(lblDaysLeft)
			    				.addComponent(balLabel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
			    			.addGap(50)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.LEADING)
			    				.addComponent(lblBudget)
			    				.addComponent(leftLabel))
			    			.addContainerGap(54, Short.MAX_VALUE))
			    		.addGroup(gl_Phom.createSequentialGroup()
			    			.addGap(96)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.TRAILING, false)
			    				.addComponent(expButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    				.addComponent(incButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    				.addGroup(gl_Phom.createSequentialGroup()
			    					.addComponent(transacButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
			    					.addPreferredGap(ComponentPlacement.RELATED)))
			    			.addGap(44)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.LEADING, false)
			    				.addComponent(btnToday, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    				.addComponent(trendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    				.addComponent(btnGroups, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
			    			.addContainerGap(117, Short.MAX_VALUE))
			    );
			    gl_Phom.setVerticalGroup(
			    	gl_Phom.createParallelGroup(Alignment.LEADING)
			    		.addGroup(gl_Phom.createSequentialGroup()
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.LEADING)
			    				.addGroup(gl_Phom.createSequentialGroup()
			    					.addGap(29)
			    					.addComponent(lblHome))
			    				.addGroup(gl_Phom.createSequentialGroup()
			    					.addContainerGap()
			    					.addComponent(btnLogout)
			    					.addGap(18)
			    					.addGroup(gl_Phom.createParallelGroup(Alignment.BASELINE)
			    						.addComponent(lblBudget)
			    						.addComponent(balLabel))))
			    			.addGap(38)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.BASELINE)
			    				.addComponent(lblDaysLeft)
			    				.addComponent(leftLabel))
			    			.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.BASELINE)
			    				.addComponent(expButton)
			    				.addComponent(btnToday))
			    			.addGap(27)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.BASELINE)
			    				.addComponent(incButton)
			    				.addComponent(trendButton))
			    			.addGap(30)
			    			.addGroup(gl_Phom.createParallelGroup(Alignment.BASELINE)
			    				.addComponent(transacButton)
			    				.addComponent(btnGroups))
			    			.addGap(29))
			    );
			    Phom.setLayout(gl_Phom);
		
	}

/*setup welcome panel*/
	void setUpPwel(){
		 	welcomeLabel = new JLabel("Welcome To Moneta");
		 	welcomeLabel.setForeground(Color.BLUE);
		    welcomeLabel.setFont(new Font("Script MT Bold", Font.PLAIN, 35));
		   
		    Welcome1 = new JLabel("Your Personal Money Manager");
		    Welcome1.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		    Welcome1.setForeground(Color.ORANGE);
		    
		    continueButton = new JButton("Continue");
		    GroupLayout gl_Pwel = new GroupLayout(Pwel);
		    gl_Pwel.setHorizontalGroup(
		    	gl_Pwel.createParallelGroup(Alignment.LEADING)
		    		.addGroup(Alignment.TRAILING, gl_Pwel.createSequentialGroup()
		    			.addContainerGap(111, Short.MAX_VALUE)
		    			.addGroup(gl_Pwel.createParallelGroup(Alignment.LEADING)
		    				.addGroup(gl_Pwel.createSequentialGroup()
		    					.addGap(34)
		    					.addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE))
		    				.addGroup(gl_Pwel.createParallelGroup(Alignment.TRAILING)
		    					.addComponent(continueButton)
		    					.addComponent(Welcome1)))
		    			.addGap(80))
		    );
		    gl_Pwel.setVerticalGroup(
		    	gl_Pwel.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Pwel.createSequentialGroup()
		    			.addGap(22)
		    			.addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
		    			.addGap(18)
		    			.addComponent(Welcome1)
		    			.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
		    			.addComponent(continueButton)
		    			.addGap(32))
		    );
		    Pwel.setLayout(gl_Pwel);
	}
		
/*setup login panel*/	
	void setUpP1(){
	
		
		userLabel = new JLabel("Username");
		PasswordLabel = new JLabel("Password");
		userText = new JTextField(20);
		PasswordText = new JPasswordField(20);
		loginButton = new JButton("Login");
		loginButton.setForeground(new Color(0, 0, 0));
		registerButton = new JButton("Register");

				
		JLabel lblMoneyManagementApplication = new JLabel("MONEY MANAGEMENT APPLICATION");
		lblMoneyManagementApplication.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		GroupLayout gl_P1 = new GroupLayout(P1);
		gl_P1.setHorizontalGroup(
			gl_P1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_P1.createSequentialGroup()
					.addGroup(gl_P1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_P1.createSequentialGroup()
							.addGap(20)
							.addComponent(lblMoneyManagementApplication))
						.addGroup(gl_P1.createSequentialGroup()
							.addGap(89)
							.addGroup(gl_P1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_P1.createSequentialGroup()
									.addGap(87)
									.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_P1.createSequentialGroup()
									.addGroup(gl_P1.createParallelGroup(Alignment.LEADING)
										.addComponent(PasswordLabel)
										.addComponent(userLabel))
									.addGap(46)
									.addGroup(gl_P1.createParallelGroup(Alignment.LEADING)
										.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(PasswordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(193, Short.MAX_VALUE))
		);
		gl_P1.setVerticalGroup(
			gl_P1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_P1.createSequentialGroup()
					.addGap(40)
					.addComponent(lblMoneyManagementApplication)
					.addGap(55)
					.addGroup(gl_P1.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_P1.createParallelGroup(Alignment.BASELINE)
						.addComponent(PasswordLabel)
						.addComponent(PasswordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_P1.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
		);
		P1.setLayout(gl_P1);
		
		
	}

/*setup budget set panel*/
	void setUpPbudg(){
		budgField = new JTextField();
		budgField.setColumns(10);
		daysField = new JTextField();
		daysField.setColumns(3);
		setBudgLabel = new JLabel("Enter Budget: Rs.");
		daysLabel = new JLabel("Enter Budget Period: ");
		setBudget = new JButton("Set");
		
		 lblDays = new JLabel("days");
		    
		    lblEnterDetailsFor = new JLabel("Enter details for your new Budget:");
		    lblEnterDetailsFor.setFont(new Font("Tahoma", Font.BOLD, 13));
		    GroupLayout gl_Pbudg = new GroupLayout(Pbudg);
		    gl_Pbudg.setHorizontalGroup(
		    	gl_Pbudg.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Pbudg.createSequentialGroup()
		    			.addContainerGap(383, Short.MAX_VALUE)
		    			.addComponent(setBudget)
		    			.addGap(141))
		    		.addGroup(gl_Pbudg.createSequentialGroup()
		    			.addGap(140)
		    			.addGroup(gl_Pbudg.createParallelGroup(Alignment.LEADING)
		    				.addGroup(gl_Pbudg.createSequentialGroup()
		    					.addComponent(setBudgLabel)
		    					.addPreferredGap(ComponentPlacement.UNRELATED)
		    					.addComponent(budgField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		    				.addGroup(gl_Pbudg.createSequentialGroup()
		    					.addComponent(daysLabel)
		    					.addGap(18)
		    					.addComponent(daysField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    					.addPreferredGap(ComponentPlacement.RELATED)
		    					.addComponent(lblDays)))
		    			.addContainerGap(259, Short.MAX_VALUE))
		    		.addGroup(gl_Pbudg.createSequentialGroup()
		    			.addGap(34)
		    			.addComponent(lblEnterDetailsFor)
		    			.addContainerGap(356, Short.MAX_VALUE))
		    );
		    gl_Pbudg.setVerticalGroup(
		    	gl_Pbudg.createParallelGroup(Alignment.LEADING)
		    		.addGroup(gl_Pbudg.createSequentialGroup()
		    			.addGap(55)
		    			.addComponent(lblEnterDetailsFor)
		    			.addGap(78)
		    			.addGroup(gl_Pbudg.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(setBudgLabel)
		    				.addComponent(budgField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		    			.addGap(54)
		    			.addGroup(gl_Pbudg.createParallelGroup(Alignment.BASELINE)
		    				.addComponent(daysLabel)
		    				.addComponent(daysField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		    				.addComponent(lblDays))
		    			.addGap(17)
		    			.addComponent(setBudget)
		    			.addContainerGap(67, Short.MAX_VALUE))
		    );
		    Pbudg.setLayout(gl_Pbudg);

	}
	
/*setup expense panel*/	
	void setUpPEx(){
		String[] groups = {"Food", "Transport", "Shopping", "Family_Friends", 
				"Health", "Education", "Others"};
		category = new JComboBox(groups);
		add = new JButton("Add");
		back = new JButton("Home");
		amtText = new JTextField(20);
		amt = new JLabel("Enter Amount");
		categoryLabel = new JLabel("Select Category");
		
		model = new SqlDateModel();
		pr = new Properties();
		pr.put("text.today", "Today");
		pr.put("text.month", "Month");
		pr.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, pr);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		
		JLabel lblSelectDate = new JLabel("Select Date");
		
		lblAddExpense = new JLabel("ADD EXPENSE:");
		lblAddExpense.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		eEditButton = new JButton("Edit");
		GroupLayout gl_PEx = new GroupLayout(PEx);
		gl_PEx.setHorizontalGroup(
			gl_PEx.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PEx.createSequentialGroup()
					.addGap(72)
					.addGroup(gl_PEx.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_PEx.createSequentialGroup()
							.addGroup(gl_PEx.createParallelGroup(Alignment.TRAILING)
								.addComponent(categoryLabel)
								.addGroup(gl_PEx.createParallelGroup(Alignment.LEADING)
									.addComponent(back)
									.addComponent(amt)))
							.addGroup(gl_PEx.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_PEx.createSequentialGroup()
									.addGap(26)
									.addGroup(gl_PEx.createParallelGroup(Alignment.LEADING, false)
										.addComponent(amtText, 0, 0, Short.MAX_VALUE)
										.addComponent(category, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(84))
								.addGroup(Alignment.TRAILING, gl_PEx.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
									.addComponent(add)
									.addGap(18))))
						.addGroup(gl_PEx.createSequentialGroup()
							.addComponent(lblSelectDate)
							.addGap(18)
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(eEditButton)
					.addGap(103))
				.addGroup(gl_PEx.createSequentialGroup()
					.addGap(46)
					.addComponent(lblAddExpense)
					.addContainerGap(459, Short.MAX_VALUE))
		);
		gl_PEx.setVerticalGroup(
			gl_PEx.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PEx.createSequentialGroup()
					.addGap(30)
					.addComponent(lblAddExpense)
					.addGap(36)
					.addGroup(gl_PEx.createParallelGroup(Alignment.LEADING)
						.addComponent(datePicker, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectDate, Alignment.TRAILING))
					.addGap(36)
					.addGroup(gl_PEx.createParallelGroup(Alignment.BASELINE)
						.addComponent(categoryLabel)
						.addComponent(category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_PEx.createParallelGroup(Alignment.BASELINE)
						.addComponent(amt)
						.addComponent(amtText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_PEx.createParallelGroup(Alignment.BASELINE)
						.addComponent(back)
						.addComponent(eEditButton)
						.addComponent(add))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		PEx.setLayout(gl_PEx);
		
	}
	
/*income setup*/	
	void setUpPIn(){
		String[] groups = {"Salary", "Award", "Gift", "Interest", "Other"};
		I_category = new JComboBox(groups);
		add1 = new JButton("Add");
		back1 = new JButton("Home");
		amtText1 = new JTextField(20);
		amt1 = new JLabel("Enter Amount");
		categoryLabel1 = new JLabel("Select Category");
		
		model1 = new SqlDateModel();
		pr1 = new Properties();
		pr1.put("text.today", "Today");
		pr1.put("text.month", "Month");
		pr1.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(model1, pr1);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		
		JLabel lblSelectDate_1 = new JLabel("Select Date");
		
		JLabel lblNewLabel = new JLabel("ADD INCOME:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		iEditButton = new JButton("Edit");
		GroupLayout gl_PIn = new GroupLayout(PIn);
		gl_PIn.setHorizontalGroup(
			gl_PIn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PIn.createSequentialGroup()
					.addGroup(gl_PIn.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PIn.createSequentialGroup()
							.addContainerGap(70, Short.MAX_VALUE)
							.addGroup(gl_PIn.createParallelGroup(Alignment.TRAILING)
								.addComponent(amt1)
								.addComponent(lblSelectDate_1)
								.addGroup(gl_PIn.createParallelGroup(Alignment.LEADING)
									.addComponent(back1)
									.addComponent(categoryLabel1)))
							.addGap(32)
							.addGroup(gl_PIn.createParallelGroup(Alignment.LEADING)
								.addComponent(I_category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(datePicker1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_PIn.createParallelGroup(Alignment.TRAILING)
									.addComponent(add1)
									.addComponent(amtText1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_PIn.createSequentialGroup()
							.addGap(43)
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(iEditButton)
					.addGap(72))
		);
		gl_PIn.setVerticalGroup(
			gl_PIn.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_PIn.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNewLabel)
					.addGap(27)
					.addGroup(gl_PIn.createParallelGroup(Alignment.TRAILING)
						.addComponent(datePicker1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectDate_1))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addGroup(gl_PIn.createParallelGroup(Alignment.LEADING)
						.addComponent(I_category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(categoryLabel1))
					.addGap(39)
					.addGroup(gl_PIn.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_PIn.createSequentialGroup()
							.addComponent(amtText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addGroup(gl_PIn.createParallelGroup(Alignment.BASELINE)
								.addComponent(back1)
								.addComponent(add1)
								.addComponent(iEditButton))
							.addGap(46))
						.addComponent(amt1)))
		);
		PIn.setLayout(gl_PIn);

		
	}

/*setup register panel*/	
	void setUpPreg(){
		nameLabel = new JLabel("Name:");
		nameText = new JTextField(20);
		u_nameLabel = new JLabel("Username:");
		u_nameText = new JTextField(20);
		newpassLabel = new JLabel("Account Password:");
		newpassText = new JPasswordField(20);
		setbal = new JLabel("Enter Balance:");
		setbalText = new JTextField(20);
		mailLabel = new JLabel("Email");
		mailText = new JTextField(20);
		setbudg = new JLabel("Set Budget:");
		setbudgText = new JTextField(20);
		
		lblEnterDetails = new JLabel("ENTER DETAILS:");
		lblEnterDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		saveButton = new JButton("Save and Register");
		
		btnLater = new JButton("Later");
		
		JLabel lblSetBudgetPeriod = new JLabel("Set Budget Period :");
		
		budgetP = new JTextField();
		budgetP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("days");
		
		lineLabel = new JLabel("---------------------------------------------------------------------------------------------------------------------");
		GroupLayout gl_Preg = new GroupLayout(Preg);
		gl_Preg.setHorizontalGroup(
			gl_Preg.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Preg.createSequentialGroup()
					.addGroup(gl_Preg.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Preg.createSequentialGroup()
							.addGap(22)
							.addComponent(lblEnterDetails))
						.addGroup(gl_Preg.createSequentialGroup()
							.addGap(100)
							.addComponent(btnLater)
							.addGap(96)
							.addComponent(saveButton, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
					.addGap(148))
				.addGroup(gl_Preg.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_Preg.createParallelGroup(Alignment.TRAILING)
						.addComponent(u_nameLabel)
						.addComponent(nameLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_Preg.createParallelGroup(Alignment.LEADING, false)
						.addComponent(u_nameText, 0, 0, Short.MAX_VALUE)
						.addComponent(nameText, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_Preg.createParallelGroup(Alignment.TRAILING)
						.addComponent(newpassLabel)
						.addComponent(mailLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_Preg.createParallelGroup(Alignment.LEADING, false)
						.addComponent(mailText, 0, 0, Short.MAX_VALUE)
						.addComponent(newpassText, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addGap(73))
				.addGroup(gl_Preg.createSequentialGroup()
					.addGap(37)
					.addComponent(lineLabel, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(gl_Preg.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_Preg.createSequentialGroup()
						.addGroup(gl_Preg.createSequentialGroup()
							.addGroup(gl_Preg.createParallelGroup(Alignment.TRAILING)
								.addComponent(setbal)
								.addComponent(setbudg))
							.addGap(28))
						.addGroup(gl_Preg.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(setbudgText, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(setbalText, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
						.addComponent(lblSetBudgetPeriod)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(budgetP, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(111))
		);
		gl_Preg.setVerticalGroup(
			gl_Preg.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Preg.createSequentialGroup()
					.addGap(26)
					.addComponent(lblEnterDetails)
					.addGap(33)
					.addGroup(gl_Preg.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
							.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(nameLabel))
						.addGroup(gl_Preg.createSequentialGroup()
							.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
								.addComponent(mailText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(mailLabel))
							.addGap(32)
							.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
								.addComponent(newpassText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(u_nameLabel)
								.addComponent(u_nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(newpassLabel))))
					.addGap(18)
					.addComponent(lineLabel)
					.addGap(18)
					.addGroup(gl_Preg.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_Preg.createSequentialGroup()
							.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
								.addComponent(setbalText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(setbal))
							.addGap(26)
							.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
								.addComponent(setbudgText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(setbudg)))
						.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(budgetP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSetBudgetPeriod)))
					.addGap(36)
					.addGroup(gl_Preg.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveButton)
						.addComponent(btnLater))
					.addGap(19))
		);
		Preg.setLayout(gl_Preg);
				
		
	}
	
/*clear fields*/
	void clearFields(JPanel pane){
		for (Component c : pane.getComponents()) {
		    if (c instanceof JTextField)  
		     ((JTextField)c).setText("");
		    if (c instanceof JDatePickerImpl)
		    	((JDatePickerImpl)c).getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		}
	}

/*my exception class*/	
	class MyException extends Exception{
		private static final long serialVersionUID = 1L;

		MyException(String s){
			super(s);
		}
	}
	
/*login buttons*/	
	void button_actions1(){
		registerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearFields(P1);
				button_actions4();
				cl.show(containerPanel, "register");
			}
		});
		
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					curr_user = userText.getText().trim();
					curr_expense = curr_user + "1";
					curr_income = curr_user + "2";
					char[] pass = PasswordText.getPassword();
					String pw = String.valueOf(pass); 	//converting to string the hidden value of pass
					String sqlQuery = "select username, password from UserInfo "
											+ "where Username ='" + curr_user + "' and Password ='" + pw + "'";
					res = state.executeQuery(sqlQuery);
					int count = 0;
				
					while(res.next()){
						count++;
					}
					
					state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					con.setAutoCommit(false);
					
					if(count == 1){
						JOptionPane.showMessageDialog(null, "User found!");
						clearFields(P1);
						setUpPhom();
						setUpPtransaction();
						cl.show(containerPanel, "welcome");
						button_actions5();
						validCheck();
					}
					if(count > 1){
						JOptionPane.showMessageDialog(null, "Duplicate username");
					}
					if(count == 0){
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
					}
					
				}catch(Exception ex){
					System.out.println("login error" + ex);
					JOptionPane.showMessageDialog(null, "Error!");

				}
			}
		});
		
	}
	
/*expense buttons*/
	void button_actions2(){
		eEditButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int x = 0;
					int amount = Integer.parseInt(amtText.getText());
					String cat= (String)category.getSelectedItem();
					selectedDate = (java.sql.Date) datePicker.getModel().getValue();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			        String date = df.format(selectedDate);
					String sql = "select Day from " + curr_expense +  " where Day = '" + date + "'";
					res = state.executeQuery(sql);
					int count = 0;
					while(res.next()){
						count++;
					}
					if(count == 0){
						throw new MyException("try add");
					}
					else if(count == 1){
						sql = "select * from " + curr_expense;
						res = state.executeQuery(sql);
						while(res.next()){
							if(date.equals(res.getString("Day"))){
								x = res.getInt(cat);
								res.updateInt(cat, amount);
								res.updateRow();
								con.commit();
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Edited!");
					x = amount - x;
					updateBalance(curr_user, x, 1); //1 because both spent and balance should get updated
					updateHomeLabels();
					reload('e');
					clearFields(PEx);
					validCheck();
				}catch(MyException me){
					JOptionPane.showMessageDialog(null, "Try adding first!");
				}catch(Exception ex){
					System.out.println(ex);
					JOptionPane.showMessageDialog(null, "Error!");

				}
			}
			
		});
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int amount = Integer.parseInt(amtText.getText());
					String cat= (String)category.getSelectedItem();
					selectedDate = (java.sql.Date) datePicker.getModel().getValue();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			        String date = df.format(selectedDate);
					String sql = "select Day from " + curr_expense + " where Day = '" + date + "'";
					res = state.executeQuery(sql);
					int count = 0;
					while(res.next()){
						count++;
					}
					if (count == 0){
						sql = "select * from " + curr_expense;
						res = state.executeQuery(sql);
						res.moveToInsertRow();
						res.updateString("Day", date);

						res.updateInt(cat, amount);
						res.insertRow();
						con.commit();
						
				
					}else if(count == 1){
						sql = "select * from " + curr_expense;
						res = state.executeQuery(sql);
												
						while(res.next()){
							if(date.equals(res.getString("Day"))){
								System.out.println(res.getString("Day") + " " + res.getInt(cat));
								int value = amount + res.getInt(cat);
								res.updateInt(cat, value);
								res.updateRow();
								con.commit();
								
							
								break;
							}
 						}
					}else{
						System.out.println("Error in table info");
					}
					
					updateBalance(curr_user, amount, 1);
					updateHomeLabels();

					JOptionPane.showMessageDialog(null, "Expense added!");
					clearFields(PEx);
					System.out.println("cleared");
					reload('e');
					validCheck();
				}catch(NumberFormatException nfe){
				}catch(Exception ex){
					System.out.println(ex);
					JOptionPane.showMessageDialog(null, "Error ex!");

				}
			}
		});
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearFields(PEx);
				cl.show(containerPanel, "home");
				
			}
		});
	}
	
/*income buttons*/	
	void button_actions3(){
		iEditButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int x = 0;
					int amount = Integer.parseInt(amtText1.getText().trim());
					String cat= (String)category.getSelectedItem();
					selectedDate = (java.sql.Date) datePicker.getModel().getValue();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			        String date = df.format(selectedDate);
					String sql = "select Day from " + curr_income + " where Day = '" + date + "'";
					res = state.executeQuery(sql);
					JOptionPane.showMessageDialog(null, selectedDate);
					int count = 0;
					while(res.next()){
						count++;
					}
					if(count == 0){
						throw new MyException("try add");
					}
					else if(count == 1){
						sql = "select * from " + curr_income;
						res = state.executeQuery(sql);
						while(res.next()){
							if(date.equals(res.getString("Day"))){
								System.out.println(res.getString("Day") + " " + res.getInt(cat));
								x = res.getInt(cat);
								res.updateInt(cat, amount);
								res.updateRow();
								con.commit();
							}
						}
					}
					x = amount - x;
					System.out.println("diff to be added is " + x);
					updateBalance(curr_user, x, 0); //0 because only balance should get updated
					updateHomeLabels();
					JOptionPane.showMessageDialog(null, "Edited!");
					clearFields(PIn);
					reload('i');
				}catch(MyException me){
					System.out.println(me);
					JOptionPane.showMessageDialog(null, "Try adding first!");
				}catch(Exception ex){
					System.out.println(ex);
					JOptionPane.showMessageDialog(null, "Error!, not edited");

				}
			}
			
		});
		
		add1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				try{
					int amount = Integer.parseInt(amtText1.getText());
					String cat= (String)I_category.getSelectedItem();
					selectedDate = (java.sql.Date) datePicker1.getModel().getValue();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			        String date = df.format(selectedDate);
					String sql = "select Day from " + curr_income + " where Day = '" + date + "'";
					res = state.executeQuery(sql);
					JOptionPane.showMessageDialog(null, selectedDate);
					int count = 0;
					while(res.next()){
						count++;
					}
					if (count == 0){
						sql = "select * from " + curr_income;
						res = state.executeQuery(sql);
						res.moveToInsertRow();
						res.updateString("Day", date);
						res.updateInt(cat, amount);
						res.insertRow();
						con.commit();

					}else if(count == 1){
						sql = "select * from " + curr_income ;
						res = state.executeQuery(sql);
						while(res.next()){
							if(date.equals(res.getString("Day"))){
								int value = amount + res.getInt(cat);
								res.updateInt(cat, value);
								res.updateRow();
								con.commit();
							}
						}
					}else{
						System.out.println("Error in table info");
					}
					
					res = state.executeQuery(sql);
					updateBalance(curr_user, amount, 0);
					updateHomeLabels();
					reload('i');
					JOptionPane.showMessageDialog(null, "Income added!");
					clearFields(PIn);
				}catch(NumberFormatException nfe){
				//	JOptionPane.showMessageDialog(null, "Invalid entry!");
				}catch(Exception ex){
					System.out.println(ex);
					JOptionPane.showMessageDialog(null, "Error!");

				}
			//	validCheck();
			}
		});
		
		back1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearFields(PIn);
				cl.show(containerPanel, "home");
			}
		}); 
	}
	
/*register buttons*/
	void button_actions4(){
		btnLater.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearFields(Preg);
				cl.show(containerPanel, "login");
			}
		});
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					
					String qdate = todayDate();
					String username = u_nameText.getText();
					String name = nameText.getText();
					char[] pass = newpassText.getPassword();
					String pw = String.valueOf(pass); 	//converting the hidden value of pass to String instance
					String mail = mailText.getText();
					int budget = Integer.parseInt(setbudgText.getText());
					int balance = Integer.parseInt(setbalText.getText());
					int period = Integer.parseInt(budgetP.getText());
					
					
					String sql = "select username from UserInfo where Username = '" + username+ "'";
					res = state.executeQuery(sql);
					int count = 0;
					while(res.next()){
						count++;
					}
					if(count >= 1){
						throw new MyException("Username is taken!");
					}
					
					
					sql = "SELECT * FROM UserInfo";
					
					res = state.executeQuery(sql);
					
					res.moveToInsertRow();
					
					res.updateString("Name", name);
					res.updateString("Username", username);
					res.updateString("Password", pw);
					res.updateString("Email", mail);
					res.updateInt("Balance", balance);
					res.updateInt("Budget", budget);
					res.updateInt("Budget_period", period);
					res.updateString("BSD", qdate);
					res.insertRow();
					con.commit();
					create_table(username);

					clearFields(Preg);
					cl.show(containerPanel, "login");
					
				}catch(NumberFormatException nfe){
					System.out.println(nfe);
				}catch(MyException uie){
					JOptionPane.showMessageDialog(null, "Username is already taken!");
				}
				catch(Exception ex){
					System.out.println(ex);
					JOptionPane.showMessageDialog(null, "Error!");
	
				}
			}
		});
	}
	
/*welcome buttons*/	
	void button_actions5(){
		continueButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				res = state.executeQuery("select * from "+ curr_expense);
				while(res.next())
				System.out.println(res.getString("Food"));
				}catch(Exception ex){
					System.out.println(ex);
				}
				cl.show(containerPanel, "home");
				button_actions6();
			}
		});
	}
	
/*home panel buttons*/	
	void button_actions6(){
		
		btnToday.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String qdate = todayDate();
				expensesToday.setText(getToday('e', qdate, curr_user));
				incomesToday.setText(getToday('i', qdate, curr_user));
				cl.show(containerPanel, "today");
			}
		});
		
		expButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl.show(containerPanel, "expense");
				button_actions2();
			}
		});
		incButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl.show(containerPanel, "income");
				button_actions3();
			}
		});
		
		btnGroups.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl.show(containerPanel, "group1");
				button_actions9();
			}
		});
		
		trendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl.show(containerPanel, "trends");
				button_actions8();
			}
		});
		
		transacButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl.show(containerPanel, "transactions");
				button_actions10();
			}
		});
		
		btnLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int input = JOptionPane.showConfirmDialog(null,
														  "Are you sure you want to Logout?", "", JOptionPane.YES_NO_OPTION);
				if(input == 0){
					cl.show(containerPanel, "login");
					button_actions2();
					try{
						res.close();
					}catch(Exception ex){
						System.out.println(ex);
					}
				}
			}
		});
	}

/*budget set buttons*/	
	void button_actions7(){
		setBudget.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String b = budgField.getText();
				String d = daysField.getText();
				int new_budget = Integer.parseInt(b);
				int new_days = Integer.parseInt(d);
				String qdate = todayDate();
				try{
					String sql = "select * from UserInfo";
					res = state.executeQuery(sql);
					while(res.next()){
						if(curr_user.equals(res.getString("username"))){
							res.updateInt("Budget", new_budget);
							res.updateInt("Budget_period", new_days);
							res.updateInt("Spent", 0);
							res.updateString("BSD", qdate);
							res.updateRow();
							con.commit();
							break;
						}
					}
					clearFields(Pbudg);
					cl.show(containerPanel, "home");
					button_actions6();
				}catch(Exception ex){
					
				}
				updateHomeLabels();
				lblDaysLeft.setText("Days left: " + daysLeft(curr_user));
			}
		});
	}
	
/*trends panel buttons*/
	void button_actions8(){

		
		btnHome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
					clearFields(Ptre);
				cl.show(containerPanel, "home");
			}
		});
		
		trend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = 0;
			
				for(i = 0; i < 7; i++){
					exp[i] = 0;
					if(i < 5) inc[i] = 0;
				}
				try{
					int count = 0;
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					selectedDate = (java.sql.Date)trend_datePicker1.getModel().getValue();
			        String date1 = df.format(selectedDate);
					selectedDate = (java.sql.Date)trend_datePicker2.getModel().getValue();
			        String date2 = df.format(selectedDate);
			        String sql = "select * from " + curr_expense + " where Day >= '" + date1 + "' and Day <= '" + date2 + "'";
			        res = state.executeQuery(sql);
					while(res.next()){
						count++;
						exp[0] += res.getInt("Food");
						exp[1] += res.getInt("Transport");
						exp[2] += res.getInt("Shopping");
						exp[3] += res.getInt("Family_Friends");
						exp[4] += res.getInt("Health");
						exp[5] += res.getInt("Education");
						exp[6] += res.getInt("Others");
					}
					sql = "select * from " + curr_income + " where Day >= '" + date1 + "' and Day <= '" + date2 + "'";
			        res = state.executeQuery(sql);
			        count = 0;
					while(res.next()){
						count++;
						inc[0] += res.getInt("Salary");
						inc[1] += res.getInt("Award");
						inc[2] += res.getInt("Gift");
						inc[3] += res.getInt("Interest");
						inc[4] += res.getInt("Other");
					}
					for(i = 0; i < 7; i++){
						if(i < 5) System.out.println(inc[i]);
						
					}
					new BarChart().drawGraph();
					if(count == 0){
						JOptionPane.showMessageDialog(null, "No Transactions!");
				}
				}catch(Exception ex){
					System.out.println(ex);
				}
				System.out.println("trend button out");
			}
		});
	}

/*group panel buttons*/	
	void button_actions9(){
		homeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl.show(containerPanel, "home");
				resultLabel.setText(" ");
				clearFields(Pgr1);
			}
		});
		
		grpadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(i != -1){
					gsize.setVisible(false);
					lblSelectGroupSize.setVisible(false);
					
				}else{
					lblSelectGroupSize.setVisible(true);
					gsize.setVisible(true);
				}
				String p = String.valueOf(gsize.getSelectedItem());
				size = Integer.parseInt(p);
				BY.add(byTF.getText());
				int x = Integer.parseInt(grpamt.getText());
				i++;
				groupInput(i, x);
				clearFields(Pgr1);
				if(i == (size - 1)) {
					i = -1;
					cl.show(containerPanel, "group2");
					//next panel
				}
				
			}
		});
		
		calci.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resultLabel.setText(calci(size));
				BY.clear();
			}
		});
	}
	
/*transaction panel buttons*/	
void button_actions10(){

		btnHome_2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			cl.show(containerPanel, "home");
			}
		});
	}
}
