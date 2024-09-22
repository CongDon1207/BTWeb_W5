package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectSQL {
	private final String severName = "LAPTOP-M10LPRA9\\CONGDON";
	private final String dbName = "ltweb";
	private final String portNumber = "1433";
	private final String instance = "";
	//private final String userID = "";
	//private final String password = "";
	
	public Connection getConnection() throws Exception{
		
		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://" + severName + "\\" + instance + ":" + portNumber + ";integratedSecurity=true;databaseName=" + dbName;
			
			if(instance == null|| instance.trim().isEmpty())
				url = "jdbc:sqlserver://" + severName + ":" + portNumber + ";integratedSecurity=true;databaseName=" + dbName;
			
			return DriverManager.getConnection(url);
			
			
	}
	
	public static void main(String[] args) {
		String jdbcURL = "jdbc:sqlserver://LAPTOP-M10LPRA9\\CONGDON:1433;integratedSecurity=true;databaseName=ltweb";
		//LAPTOP-M10LPRA9\CONGDON:1433;integratedSecurity=true;databaseName=ltweb
        Connection connection = null;

        try {
            // Nạp driver thủ công
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver nạp thành công!");

            // Tạo kết nối
            connection = DriverManager.getConnection(jdbcURL);
            System.out.println(connection);

        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver JDBC!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
