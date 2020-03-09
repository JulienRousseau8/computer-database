package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnect {

	public Connection conn;
    private Statement statement;
    public static MySQLConnect db;
    private String url= "jdbc:mysql://localhost:3306/computer-database-db";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "admincdb";
    private String password = "qwerty1234";
    
    private MySQLConnect() {
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connexion");
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
        
    }
    
    /**
    *
    * @return MysqlConnect Database connection object
    */
   public static synchronized MySQLConnect getDbCon() {
       if ( db == null ) {
           db = new MySQLConnect();
       }
       return db;
   }
   
   /**
   *
   * @param query String The query to be executed
   * @return a ResultSet object containing the results or null if not available
   * @throws SQLException
   */
  public ResultSet query(String query) throws SQLException{
      statement = db.conn.createStatement();
      ResultSet res = statement.executeQuery(query);
      return res;
  }   
  
  /**
   * @desc Method to insert data to a table
   * @param insertQuery String The Insert query
   * @return boolean
   * @throws SQLException
   */
  public void insert(String insertQuery) throws SQLException {
      statement = db.conn.createStatement();
      statement.executeUpdate(insertQuery);

  }

}
