package hienThi;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
public class chucNang extends JFrame implements ActionListener {
	private JPanel top1;
	private JPanel top2;
	private JPanel under3;
	private JPanel under4;

	private JTable table;

	private JButton butAdd;
	private JButton butXoa;
	private JButton butCapNhat;
	private JButton butThongKe;
	private JButton butXuat;
	private JButton butXep;
	
	private JLabel lb_maHangNhap;
	private JLabel lb_maHangXuat;
	private JLabel lb_tenHangNhap;
	private JLabel lb_tenHangXuat;
	private JLabel lb_soluongNhap;
	private JLabel lb_soluongXuat;
	private JLabel lb_ngayNhap;
	private JLabel lb_ngayXuat;
	private JLabel lb_gia;

	private JTextField txtMaNhap;
	private JTextField txtMaXuat;
	private JTextField txtHangNhap;
	private JTextField txtHangXuat;
	private JTextField txtSoLuongXuat;
	private JTextField txtSoLuongNhap;
	private JTextField txtTim;
	private JTextField txtGia;

	private JDateChooser date_Nhap;
	private JDateChooser date_Xuat;

	private DefaultTableModel model;

	private SimpleDateFormat spd;
	private String date;

	ArrayList<hangHoa> list = hanghoa_list();
	Object[] row = new Object[7];

	public chucNang() {
		thietlaphienthi();
		show_hangHoa();
	}

	public ArrayList<hangHoa> hanghoa_list() {
		ArrayList<hangHoa> hhList = new ArrayList<>();
		try {
			String lenhSqlString = "select * from nhaphang";
			Connection connection = conNection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(lenhSqlString);
			hangHoa hangHoa;
			while (rs.next()) {
				hangHoa = new hangHoa(rs.getString("maHangHoa"), rs.getString("tenHangHoa"), rs.getInt("gia"),
						rs.getInt("soluong"), rs.getDate("ngaynhap"),rs.getInt("soLuongXuat"),rs.getDate("ngayxuat"));
				hhList.add(hangHoa);
			}
			st.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hhList;
	}

	public void show_hangHoa() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getMaHangNhap();
			row[1] = list.get(i).getTenHangNhap();
			row[2] = list.get(i).getGia();
			row[3] = list.get(i).getSoLuongNhap();
			row[4] = list.get(i).getDate_nhap();
			row[5] = list.get(i).getSoLuongXuat();
			row[6] = list.get(i).getDate_xuat();
			model.addRow(row);
		}
	}

	public JPanel topTrai() {
		top1 = new JPanel();
		top1.setLayout(new GridLayout(6, 3, 5, 5));
//		top1.setBackground(new Color(224, 255, 255));
		top1.setBorder(BorderFactory.createEmptyBorder(25, 23, 25, 23));

		lb_gia = new JLabel("Giá");
		lb_maHangNhap = new JLabel("Mã Hàng");
		lb_tenHangNhap = new JLabel("Tên Hàng");
		lb_soluongNhap = new JLabel("Số Lượng Nhập");
		lb_ngayNhap = new JLabel("Ngày Nhập");
		JLabel label = new JLabel("Nhập Tên Để Tìm");
		label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chucNang.class.getResource("Search.png"))));

		txtGia = new JTextField();
		txtMaNhap = new JTextField();
		txtHangNhap = new JTextField();
		txtSoLuongNhap = new JTextField();
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> deRowSorter = new TableRowSorter<DefaultTableModel>(
						defaultTableModel);
				table.setRowSorter(deRowSorter);
				deRowSorter.setRowFilter(RowFilter.regexFilter(txtTim.getText()));

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		date_Nhap = new JDateChooser();
		date_Nhap.setDateFormatString("yyyy-MM-dd");
		Date select = (Date) date_Nhap.getDate();
		java.sql.Date TimeDate = convertUtilToSql(select);

		butAdd = new JButton("Nhập Hàng");
		butAdd.addActionListener(this);
		butAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chucNang.class.getResource("pluss.png"))));
		butXoa = new JButton("Xóa Hàng");
		butXoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chucNang.class.getResource("iconExit.png"))));
		butXoa.addActionListener(this);
		butCapNhat = new JButton("Chỉnh Sửa");
		butCapNhat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chucNang.class.getResource("refresh.png"))));
		butCapNhat.addActionListener(this);

		top1.add(lb_maHangNhap);
		top1.add(txtMaNhap);
		top1.add(butAdd);

		top1.add(lb_tenHangNhap);
		top1.add(txtHangNhap);
		top1.add(butXoa);

		top1.add(lb_gia);
		top1.add(txtGia);
		top1.add(butCapNhat);

		top1.add(lb_soluongNhap);
		top1.add(txtSoLuongNhap);
		top1.add(new JLabel());

		top1.add(lb_ngayNhap);
		top1.add(date_Nhap);
		top1.add(new JLabel());

		top1.add(label);
		top1.add(txtTim);

		return top1;
	}

	private Date convertUtilToSql(Date select) {
		return null;
	}

	public JPanel topPhai() {
		top2 = new JPanel();
		top2.setLayout(new BorderLayout());
		top2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		top2.setBackground(new Color(240, 230, 140));

		table = new JTable();
		Object[] name = { "Mã Hàng Hóa", "Tên Hàng Hóa", "Giá", "Số Lượng Nhập", "Ngày Nhập", "Số lượng xuất", "Ngày Xuất" };
		model = new DefaultTableModel();
		TableRowSorter sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);
		model.setColumnIdentifiers(name);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int a = table.getSelectedRow();
					String tc = table.getModel().getValueAt(a, 0).toString();
					Connection connection = conNection.getConnection();
					String query = "select*from nhaphang where maHangHoa="+tc+"";
					PreparedStatement psPreparedStatement = connection.prepareStatement(query);
					ResultSet rsResultSet = psPreparedStatement.executeQuery();
					if(rsResultSet.next()) {
						int id = rsResultSet.getInt("maHangHoa");
						String name = rsResultSet.getString("tenHangHoa");
						int gia = rsResultSet.getInt("gia");
						int soluong = rsResultSet.getInt("SoLuong");
						String ngayNhap = rsResultSet.getString("ngayNhap");
						int soluongXuat = rsResultSet.getInt("soLuongxuat");
						String ngayXuat = rsResultSet.getString("ngayXuat");
						
						txtMaNhap.setText(""+id);
						txtMaXuat.setText(""+id);
						txtGia.setText(""+gia);
						txtSoLuongNhap.setText(""+soluong);
						txtSoLuongXuat.setText(""+soluongXuat);
						txtHangNhap.setText(name);
						((JTextField) date_Nhap.getDateEditor().getUiComponent()).setText(ngayNhap);
						((JTextField) date_Xuat.getDateEditor().getUiComponent()).setText(ngayXuat);
						psPreparedStatement.close();
						rsResultSet.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		top2.add(new JScrollPane(table));
		return top2;
	}

	public JPanel duoiTrai() {
		under3 = new JPanel();
		under3.setLayout(new GridLayout(5, 2, 30, 10));
//		under3.setBackground(new Color(188, 143, 13));
		under3.setBorder(BorderFactory.createEmptyBorder(20, 25, 23, 200));

		lb_maHangXuat = new JLabel("Mã Hàng");
		lb_tenHangXuat = new JLabel("Tên Hàng");
		lb_soluongXuat = new JLabel("Số Lượng Xuất");
		lb_ngayXuat = new JLabel("Ngày Xuất");

		txtMaXuat = new JTextField();
		txtHangXuat = new JTextField();
		txtSoLuongXuat = new JTextField();
		date_Xuat = new JDateChooser();
		date_Xuat.setDateFormatString("yyyy-MM-dd");

		butXuat = new JButton("Xuất Hàng");
		butXuat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chucNang.class.getResource("xuathang.png"))));
		butXuat.addActionListener(this);

		under3.add(lb_maHangXuat);
		under3.add(txtMaXuat);
//
//		under3.add(lb_tenHangXuat);
//		under3.add(txtHangXuat);

		under3.add(lb_soluongXuat);
		under3.add(txtSoLuongXuat);

		under3.add(lb_ngayXuat);
		under3.add(date_Xuat);

		under3.add(new JLabel());
		under3.add(butXuat);

		return under3;
	}

	public JPanel duoiPhai() {
		under4 = new JPanel();
		under4.setLayout(new BorderLayout(10, 10));
		under4.setBorder(BorderFactory.createEmptyBorder(50, 99, 50, 99));
//		under4.setBackground(new Color(203, 204, 0));

		butThongKe = new JButton("THỐNG KÊ");
		butThongKe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chucNang.class.getResource("chart.png"))));
		butThongKe.addActionListener(this);
		String data[] = {"Giá","Tên","Số Lượng"};
		JComboBox cbb = new JComboBox(data);
		
		butXep = new JButton("Sắp Xếp");
		butXep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String p = cbb.getSelectedItem().toString();
				SapXep sXep = new SapXep("Sắp Xếp", list, p);
//				sXep.show();
			}
		});

		under4.add(butXep,BorderLayout.CENTER);
		under4.add(cbb,BorderLayout.NORTH);
		under4.add(butThongKe, BorderLayout.SOUTH);
		return under4;
	}

//thiết lập hiển thị
	public void thietlaphienthi() {
		add(topTrai());
		add(topPhai());
		add(duoiTrai());
		add(duoiPhai());

		setTitle("QUẢN LÝ XUẤT NHẬP KHO");
		setLayout(new GridLayout(2, 2, 10, 10));
		setSize(1150, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

//xử lí sự kiện 
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Nhập Hàng")) {
			them(e);
		} else if (cmd.equals("Chỉnh Sửa")) {
			update(e);
		} else if (cmd.equals("Xóa Hàng")) {
			delete(e);
		} else if (cmd.equals("THỐNG KÊ")) {
			thongKe(e);
		} else if (cmd.equals("Xuất Hàng")) {
			xuatHang(e);
		}
	}

//	nút thêm
	public void them(ActionEvent e) {
		if (txtMaNhap.getText().equals("") || txtGia.getText().equals("") || txtHangNhap.getText().equals("")
				|| txtSoLuongNhap.getText().equals("") || date_Nhap == null) {
			JOptionPane.showMessageDialog(null, "VUI LÒNG NHẬP ĐẦY ĐỦ THÔNG TIN");
		} else {
			try {
				Connection con = conNection.getConnection();
				String query = "INSERT INTO nhaphang(maHangHoa,tenHangHoa,gia,SoLuong,ngayNhap) VALUES (?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, txtMaNhap.getText());
				pst.setString(2, txtHangNhap.getText());
				pst.setString(3, txtGia.getText());
				pst.setString(4, txtSoLuongNhap.getText());
				SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
				String date = spd.format(date_Nhap.getDate());
				pst.setString(5, date);
				pst.execute();
				
				Object[] row = new Object[5];
				row[0] = txtMaNhap.getText();
				row[1] = txtHangNhap.getText();
				row[2] = txtGia.getText();
				row[3] = txtSoLuongNhap.getText();
				row[4] = date;
				model.fireTableDataChanged();
				model.addRow(row);
				
				JOptionPane.showMessageDialog(null, "NHẬP HÀNG THÀNH CÔNG");

				pst.close();

				txtMaNhap.setText("");
				txtHangNhap.setText("");
				txtSoLuongNhap.setText("");
				txtGia.setText("");
				txtSoLuongXuat.setText("");
				txtMaXuat.setText("");
				txtHangXuat.setText("");
				date_Xuat.setDate(convertUtilToSql(null));
				date_Nhap.setDate(convertUtilToSql(null));

			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "NHẬP HÀNG THẤT BẠI");
			}
		}
	}

	public void update(ActionEvent e) {
		if (txtMaNhap.getText().equals("") || txtGia.getText().equals("") || txtHangNhap.getText().equals("")
				|| txtSoLuongNhap.getText().equals("") || date_Nhap == null) {
			JOptionPane.showMessageDialog(null, "VUI LÒNG NHẬP ĐẦY ĐỦ THÔNG TIN");
		} else {
			try {
				Connection connection = conNection.getConnection();
				String query = "UPDATE  nhaphang SET tenHangHoa=?,gia=?,SoLuong=?,ngayNhap=?,soLuongXuat=?,ngayXuat=? WHERE maHangHoa=?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, txtHangNhap.getText());
				pst.setString(2, txtGia.getText());
				pst.setString(3, txtSoLuongNhap.getText());
				SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
				String date = spd.format(date_Nhap.getDate());			
				pst.setString(4, date);
				pst.setString(5, txtSoLuongXuat.getText());
				String date2 = spd.format(date_Xuat.getDate());
				pst.setString(6, date2);
				pst.setString(7, txtMaNhap.getText());
				pst.executeUpdate();

				row[0] = txtMaNhap.getText();
				row[1] = txtHangNhap.getText();
				row[2] = txtGia.getText();
				row[3] = txtSoLuongNhap.getText();
				row[4] = date;
				row[5] = txtSoLuongXuat.getText();
				row[6] = date2;
				
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int row1 = table.getSelectedRow();
				dm.removeRow(row1);
				model.addRow(row);

				JOptionPane.showMessageDialog(null, "CẬP NHẬT HÀNG THÀNH CÔNG");
				pst.close();

				txtMaNhap.setText("");
				txtHangNhap.setText("");
				txtSoLuongNhap.setText("");
				txtGia.setText("");
				txtSoLuongXuat.setText("");
				txtMaXuat.setText("");
				txtHangXuat.setText("");
				date_Xuat.setDate(convertUtilToSql(null));
				date_Nhap.setDate(convertUtilToSql(null));
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "CẬP NHẬT HÀNG THẤT BẠI");
			}
		}
	}

	public void delete(ActionEvent e) {
		if (txtMaNhap.getText().equals("") || txtGia.getText().equals("") || txtHangNhap.getText().equals("")
				|| txtSoLuongNhap.getText().equals("") || date_Nhap == null) {
			JOptionPane.showMessageDialog(null, "VUI LÒNG NHẬP ĐẦY ĐỦ THÔNG TIN");
		} else {
			try {
				int nh_lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa dòng này?");
				if(nh_lc == JOptionPane.YES_OPTION) {
				Connection connection = conNection.getConnection();
				String query = "DELETE FROM nhaphang WHERE maHangHoa=?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, txtMaNhap.getText().toString());
				pst.executeUpdate();
				pst.close();

				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int row1 = table.getSelectedRow();
				dm.removeRow(row1);
				
				txtMaNhap.setText("");
				txtHangNhap.setText("");
				txtSoLuongNhap.setText("");
				txtGia.setText("");
				txtSoLuongXuat.setText("");
				txtMaXuat.setText("");
				txtHangXuat.setText("");
				date_Xuat.setDate(convertUtilToSql(null));
				date_Nhap.setDate(convertUtilToSql(null));
				
				JOptionPane.showMessageDialog(null, "Xóa Hàng Thành Công");
				}else {
					JOptionPane.showMessageDialog(null, "Bạn Đã Hủy Bỏ Thao Tác!");
					}			
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "XÓA HÀNG THẤT BẠI");
			}
		}
	}

	public void thongKe(ActionEvent e) {
		if(txtHangNhap.getText().equals("") || txtSoLuongNhap.getText().equals("") || txtSoLuongXuat.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "VUI LÒNG CHỌN HÀNG HÓA");
		}else {
		String nhap = txtSoLuongNhap.getText();
		String xuat = txtSoLuongXuat.getText();
		String v = txtHangNhap.getText();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(new Integer(nhap), "Số Lượng", "Số Lượng Đã Nhập");
		dataset.setValue(new Integer(xuat), "Số Lượng", "Số Lượng Đã Xuất");
		JFreeChart chart = ChartFactory.createBarChart("Số lượng nhập xuất của " + v, null, "số lượng", dataset,
		PlotOrientation.VERTICAL, false, true, false);
		ChartFrame cFrame = new ChartFrame("THỐNG KÊ", chart);

		cFrame.setSize(500, 600);
		cFrame.setLocationRelativeTo(null);
		cFrame.setVisible(true);
	}
	}
	public void xuatHang(ActionEvent e) {
		if (txtMaXuat.getText().equals("")
			|| txtSoLuongXuat.getText().equals("") || date_Xuat== null) {
			JOptionPane.showMessageDialog(null, "VUI LÒNG NHẬP ĐẦY ĐỦ THÔNG TIN");
		} else {
			try {
				Connection connection = conNection.getConnection();
				String query = "UPDATE  nhaphang SET soLuongXuat=?,ngayXuat=? WHERE maHangHoa=?";
				PreparedStatement pst = connection.prepareStatement(query);
				SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
				pst.setString(1, txtSoLuongXuat.getText());
				String date2 = spd.format(date_Xuat.getDate());
				String date = spd.format(date_Nhap.getDate());
				pst.setString(2, date2);
				pst.setString(3, txtMaNhap.getText());
				pst.executeUpdate();
				
				row[0] = txtMaNhap.getText();
				row[1] = txtHangNhap.getText();
				row[2] = txtGia.getText();
				row[3] = txtSoLuongNhap.getText();
				row[4] = date;
				row[5] = txtSoLuongXuat.getText();
				row[6] = date2;
				
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int row1 = table.getSelectedRow();
				dm.removeRow(row1);
				model.addRow(row);

				JOptionPane.showMessageDialog(null, "XUẤT HÀNG THÀNH CÔNG");
				pst.close();

				txtMaNhap.setText("");
				txtHangNhap.setText("");
				txtSoLuongNhap.setText("");
				txtGia.setText("");
				txtSoLuongXuat.setText("");
				txtMaXuat.setText("");
				txtHangXuat.setText("");
				date_Xuat.setDate(convertUtilToSql(null));
				date_Nhap.setDate(convertUtilToSql(null));
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "XUẤT HÀNG THẤT BẠI");
			}
		}
	}
}
