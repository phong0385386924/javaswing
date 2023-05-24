package hienThi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class conNection {
	public static Connection getConnection() {
		String dbURL1 =
			    "jdbc:sqlserver://;" +
			     "databaseName=nhapXuatHang;user=sa;password=phong;integratedSecurity=true;" +
			     "encrypt=true;trustServerCertificate=true";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
   public boolean xuatHang(hangHoa hh ,String maHang,int soLuong,String ngayXuat) {
		
		String query = "UPDATE  nhaphang SET SoLuong,soLuongXuat=?,ngayXuat=? WHERE maHangHoa=?";
		try {
			Connection connection = conNection.getConnection();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, hh.getSoLuongNhap()-soLuong);
			pst.setInt(2, hh.getSoLuongXuat()+soLuong);
			pst.setString(3, ngayXuat);
			pst.setString(4, maHang);
			pst.execute();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return false;
		
	}
	}