package hienThi;

import javax.swing.UIManager;

public class main {
	public static void main(String[] a) {
		 try {
			 UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(chucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(chucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(chucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(chucNang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		new chucNang();
	}
}
