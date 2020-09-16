package databaserepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import configs.ConfigManager;
import entities.Order;
import entities.Product;

public class MySqlRepository {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;


	public ArrayList<Product> readInventory() throws Exception{

		ArrayList<Product> products = new ArrayList<Product>();


		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String dbHostWithPort = ConfigManager.GetProperty("dbhost")+ ":"+ConfigManager.GetProperty("dbport");
		
		connect = DriverManager.getConnection(
				"jdbc:mysql://"+dbHostWithPort+"+/tactual?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
				, ConfigManager.GetProperty("dbusername"),ConfigManager.GetProperty("dbpassword"));


		System.out.println("after connect");
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
				.executeQuery("select id,name from tactual.products");


		while (resultSet.next()) {

			//        	 Product product = new Product(resultSet.getString("name"),resultSet.getString("description"),resultSet.getInt("quantity"),resultSet.getFloat("price"));
			Product product = new Product();
			product.Identifier = resultSet.getInt("id");
			product.Name = resultSet.getString("name");
//			product.Description = resultSet.getString("description");
//			product.Quantity = resultSet.getInt("quantity");
//			product.Price = resultSet.getFloat("price");

			products.add(product);
		}
		return products;
	}

	public ArrayList<Order> readOrders() throws Exception{

		ArrayList<Order> orders = new ArrayList<Order>();

		Class.forName("com.mysql.cj.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/tactual?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
				, "root", "password");


		System.out.println("after connect");
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
				.executeQuery("select * from tactual.orders");


		while (resultSet.next()) {
			Order order = new Order();
			
			order.Identifier = resultSet.getInt("id");
			order.UserId = resultSet.getInt("userId");
			order.CreateDate = resultSet.getDate("createDate");
			order.UpdateDate = resultSet.getDate("updateDate");
			order.DeliverBy = resultSet.getDate("deliverBy");
			order.FulFilled = resultSet.getBoolean("fulfilled");
			order.Products = resultSet.getString("products");
			order.ShippingAddress = resultSet.getString("shippingAddress");

			orders.add(order);
		}
		
		return orders;
	}

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/tactual?"
							+ "user=root&password=mypass123");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from feedback.comments");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
			// "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			// Parameters start with 1
			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect
					.prepareStatement("delete from feedback.comments where myuser= ? ; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();

			resultSet = statement
					.executeQuery("select * from feedback.comments");
			writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		//  Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public Product getproduct(int i) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/tactual?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
				, "root", "password");


		System.out.println("after connect");
        PreparedStatement statement = connect
        		.prepareStatement("SELECT * FROM tactual.products p WHERE p.id = ?");

        
        statement.setInt(1, i);
     
		resultSet = statement.executeQuery();
		Product product = null;
		  while (resultSet.next()) {
             
  			product.Identifier = resultSet.getInt("id");
  			product.Name = resultSet.getString("name");
  			product.Description = resultSet.getString("description");
  			product.Quantity = resultSet.getInt("quantity");
  			product.Price = resultSet.getFloat("price");
  			
  			return product;
		  }
		return product;
	}
	
	public Product getproductByDetails(String pname, String pdes, int quantity, float price) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/tactual?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
				, "root", "password");


		System.out.println("after connect");
        PreparedStatement statement = connect
        		.prepareStatement("SELECT * FROM tactual.products p WHERE p.name = ? "
        				+ "AND p.description = ? and p.quantity =? and p.price = ?;");

        statement.setString(1, pname);
        statement.setString(2, pdes);
        statement.setInt(3, quantity);
        statement.setFloat(4, price);
     
       
		resultSet = statement.executeQuery();
		Product product = null;
		  while (resultSet.next()) {
             
            
//  			product.Identifier = resultSet.getInt("id");
  			product.Name = resultSet.getString("name");
  			product.Description = resultSet.getString("description");
  			product.Quantity = resultSet.getInt("quantity");
  			product.Price = resultSet.getFloat("price");
  			
  			return product;
		  }
		return product;
		
	}

}
