package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CommitAndRollback {
  static final String url = "jdbc:postgresql://localhost:5432/test";
  static final String user = "postgres";
  static PWD pwd = new PWD();
  private static Connection connection;
  

  public static void main(String[] args) throws SQLException {
   try {
     connection = DriverManager.getConnection(url,user,pwd.getPwd());
    
    // change auto commit status
    connection.setAutoCommit(false);
    
    // execute update query
    updateQuery();
    
    // commit
    connection.commit();
    System.out.println("commit successful");
   }catch(Exception e) {
     /*
      * try { // roll back connection.rollback(); System.out.println("Rolling back");
      * }catch(SQLException e1) { e1.printStackTrace(); }
      */
     e.printStackTrace();
   }
   finally {
     connection.close();
   }
    
  }
  private static void updateQuery() throws SQLException {
    
    String sql = "insert into employees values (9,'Biance',2)";
    
    try(Statement statement = connection.createStatement()) {
      statement.executeUpdate(sql);
      
    }catch(SQLException e) {
      throw e;
    }
    
  }

}
