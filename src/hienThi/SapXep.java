package hienThi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SapXep extends JFrame implements ActionListener{
	
	JScrollPane sp;
	JPanel p,TieuDe;
	JTable tb;
	DefaultTableModel model;
	ArrayList<hangHoa> hh;
	JButton ok;
	public SapXep(String s, ArrayList<hangHoa> h, String name) {
		super(s);
		hh=h;
		setLayout(new BorderLayout());
		tb = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Mã Hàng");
		model.addColumn("Tên");
		model.addColumn("Giá");
		model.addColumn("Số Lượng");
		model.addColumn("Ngay Nhap");
		model.addColumn("Số Lượng Xuat");
		model.addColumn("Ngay Xuat");
		if(name=="Tên") {
			Collections.sort(h, new Comparator<hangHoa>() {
				public int compare(hangHoa h1, hangHoa h2) {

					return h1.getTenHangNhap().compareTo(h2.getTenHangNhap());
				}
			});
		}
		else
			if (name=="Giá"){
				Collections.sort(h, new Comparator<hangHoa>() {
					public int compare(hangHoa h1, hangHoa h2) {
						Double g1 = Double.valueOf(h1.getGia());
						Double g2 = Double.valueOf(h2.getGia());
						return g1.compareTo(g2);
					}
				});
			}
			else if(name=="Số Lượng") {
					Collections.sort(h, new Comparator<hangHoa>() {
						public int compare(hangHoa h1, hangHoa h2) {
							Integer s1 = h1.getSoLuongNhap();
							Integer s2 = h2.getSoLuongNhap();
							return s1.compareTo(s2);
						}
					});
				}
//						
		for (hangHoa e : h) {
			model.addRow(new Object[] {e.getMaHangNhap(),e.getTenHangNhap(),e.getGia(),e.getSoLuongNhap(),
					e.getDate_nhap(),e.getSoLuongXuat(),e.getDate_xuat()});
		}
		tb.setModel(model);
		sp = new JScrollPane(tb);
		this.add(sp,BorderLayout.CENTER);
		
		TieuDe = new JPanel();
		TieuDe.setLayout(new FlowLayout());
		JLabel ten = new JLabel("Sap Xep Theo "+name);
		ten.setFont(new Font("Sitka Text", Font.BOLD, 20));
		TieuDe.add(ten,BorderLayout.CENTER);
		
		this.add(TieuDe,BorderLayout.NORTH);
		ok = new JButton("Thoát");
		ok.addActionListener(this);
		p = new JPanel();
		p.add(ok);
		p.setLayout(new FlowLayout());
		this.add(p,BorderLayout.SOUTH);
		setLocation(400,200);
		setSize(600,300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Thoát")) {
			this.dispose();
		}
	}
}
