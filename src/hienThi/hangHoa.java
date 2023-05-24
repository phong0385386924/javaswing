package hienThi;

import java.sql.Date;

public class hangHoa {
	private String maHangNhap,maHangXuat,tenHangNhap,tenHangXuat;
	private int soLuongNhap,soLuongXuat,gia;
	private Date date_nhap,date_xuat;
	
	
	
	

	public hangHoa(String maHangNhap, String tenHangNhap,int gia, int soLuongNhap,
      Date date_nhap,int soLuongXuat, Date date_xuat) {
		super();
		this.maHangNhap = maHangNhap;
		this.tenHangNhap = tenHangNhap;
		this.soLuongNhap = soLuongNhap;
		this.soLuongXuat = soLuongXuat;
		this.gia = gia;
		this.date_nhap = date_nhap;
		this.date_xuat = date_xuat;
	}


	public hangHoa(String maHangNhap, String tenHangNhap, int soLuongNhap, int soLuongXuat, int gia, Date date_nhap,
			Date date_xuat) {
		super();
		this.maHangNhap = maHangNhap;
		this.tenHangNhap = tenHangNhap;
		this.soLuongNhap = soLuongNhap;
		this.soLuongXuat = soLuongXuat;
		this.gia = gia;
		this.date_nhap = date_nhap;
		this.date_xuat = date_xuat;
	}


	public hangHoa(String maHangNhap, String maHangXuat, String tenHangNhap, String tenHangXuat, int soLuongNhap,
			int soLuongXuat, int gia, Date date_nhap, Date date_xuat) {
		super();
		this.maHangNhap = maHangNhap;
		this.maHangXuat = maHangXuat;
		this.tenHangNhap = tenHangNhap;
		this.tenHangXuat = tenHangXuat;
		this.soLuongNhap = soLuongNhap;
		this.soLuongXuat = soLuongXuat;
		this.gia = gia;
		this.date_nhap = date_nhap;
		this.date_xuat = date_xuat;
	}


	public hangHoa(String maHangXuat, int soLuongXuat, Date date_xuat) {
		super();
		this.maHangXuat = maHangXuat;
		this.soLuongXuat = soLuongXuat;
		this.date_xuat = date_xuat;
	}


	public String getMaHangNhap() {
		return maHangNhap;
	}

	public void setMaHangNhap(String maHangNhap) {
		this.maHangNhap = maHangNhap;
	}

	public String getMaHangXuat() {
		return maHangXuat;
	}

	public void setMaHangXuat(String maHangXuat) {
		this.maHangXuat = maHangXuat;
	}

	public String getTenHangNhap() {
		return tenHangNhap;
	}

	public void setTenHangNhap(String tenHangNhap) {
		this.tenHangNhap = tenHangNhap;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public String getTenHangXuat() {
		return tenHangXuat;
	}

	public void setTenHangXuat(String tenHangXuat) {
		this.tenHangXuat = tenHangXuat;
	}

	public int getSoLuongNhap() {
		return soLuongNhap;
	}

	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}

	public int getSoLuongXuat() {
		return soLuongXuat;
	}

	public void setSoLuongXuat(int soLuongXuat) {
		this.soLuongXuat = soLuongXuat;
	}

	public Date getDate_nhap() {
		return date_nhap;
	}

	public void setDate_nhap(Date date_nhap) {
		this.date_nhap = date_nhap;
	}

	public Date getDate_xuat() {
		return date_xuat;
	}

	public void setDate_xuat(Date date_xuat) {
		this.date_xuat = date_xuat;
	}

}