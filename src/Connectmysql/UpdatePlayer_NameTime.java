package Connectmysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class UpdatePlayer_NameTime {

	public static void main(String playername) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/player_list?"
					+ "user=root&password=0000&serverTimezone=UTC&useSSL=false");

			Statement stmt = conn.createStatement();
			
			
			//取得遊戲開始時間
			SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String sdate = nowdate.format(new java.util.Date());
			System.out.println(sdate);
			
			//新增 Player_name,Start_time到資料庫
			PreparedStatement sql_starttime = conn.prepareStatement("INSERT INTO player SET player_name=?, start_time = ? ;");
			sql_starttime.setString(1, playername);
			sql_starttime.setString(2, sdate);
			sql_starttime.executeUpdate();
			


		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	

}
