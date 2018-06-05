package Score_page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;  
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Start_page.Start;
 
  
public class BestScore_list {  
	
  	private static JFrame frame = new JFrame();
    
  	public static void main (String args []){	
    	//取得資料
    	java.sql.Connection con = null;
    	java.sql.Statement st = null;
    	ResultSet rs = null;
    	String s;
    	try {
    		con = DriverManager.getConnection("jdbc:mysql://localhost/player_list?"
					+ "user=root&password=conansmart&serverTimezone=UTC&useSSL=false");
    		st = con.createStatement();
    		s = "select * from player ORDER BY score ASC";//從小排到大
    		rs = st.executeQuery(s);
    		java.sql.ResultSetMetaData rsmt = rs.getMetaData();
    		int c = rsmt.getColumnCount()+1;
    		Vector column = new Vector (c);
    		for(int i = 1; i < c; i++) {
    			column.add(rsmt.getColumnName(i));
    		}
    		Vector data = new Vector();
    		Vector row = new Vector();
    		while(rs.next()) {
    			row = new Vector(c);
    			for(int i = 1; i < c; i++) {
    				row.add(rs.getString(i));
    			}
    			data.add(row);
    		}
    		
    		//設置背景
    		ImageIcon background = new ImageIcon(Start.class.getClassLoader().getResource("Start_background_2.png"));
            JLabel bkLabel = new JLabel(background);
            bkLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
            frame.setSize(background.getIconWidth(), background.getIconHeight()+40);
            frame.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));
            JPanel panel = (JPanel)frame.getContentPane();
            panel.setOpaque(false);
            
            //Back,Exit按鈕新增
            JButton Back = new JButton();
            frame.add(Back);
            JButton Exit = new JButton();
            frame.add(Exit);
            
            //Start,Exit,Score按鈕圖案
            ImageIcon back_icon = new ImageIcon(Start.class.getClassLoader().getResource("back_icon.png"));
            ImageIcon exit_icon = new ImageIcon(Start.class.getClassLoader().getResource("exit_icon.png"));
            ImageIcon highscore_icon = new ImageIcon(Start.class.getClassLoader().getResource("high_score.png"));
            
            //設置Back按鈕
            Back.setIcon(back_icon);
            Back.setBounds(900,10, 170,53);
            Back.setContentAreaFilled(false);
            Back.addActionListener(new ActionListener(){ 
        		public void actionPerformed(ActionEvent e){ 
        			Back_start();//進入Start_page
        			} 
        		}); //監聽
            
            //設置Exit按鈕
            Exit.setIcon(exit_icon);
            Exit.setBounds(1100,10, 170,53);
            Exit.setContentAreaFilled(false);
            Exit.addActionListener(new ActionListener(){ 
        		public void actionPerformed(ActionEvent e){ 
        			windowClosing(null);//關掉視窗
        			} 
        		}); //監聽
                 
            //SCORE JLABEL
            JLabel label1=new JLabel(highscore_icon);
            label1.setFont(new Font("標楷體?", 1, 50));
            label1.setForeground(Color.getHSBColor(178, 34, 34));
            label1.setBounds(250,0,500,80);
            frame.add(label1);
            
            //資料放進JTABLE
    		frame.setLocationRelativeTo(null);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		JTable table = new JTable(data,column) { // 設置jtable的單元格为透明的
    			public Component prepareRenderer(TableCellRenderer renderer,int row, int column) {
    				Component c = super.prepareRenderer(renderer, row, column);
    				if (c instanceof JComponent) {
    					((JComponent) c).setOpaque(false);
    					}
    				return c;
    				}
    			};
    			
    		table.setPreferredScrollableViewportSize(new Dimension(600, 275));
    		JScrollPane jsp = new JScrollPane(table);
    		table.setOpaque(false);   		
            jsp.setOpaque(false);
    		jsp.getViewport().setOpaque(false);
    		panel.setLayout(new BorderLayout());
    		panel.add(jsp,BorderLayout.SOUTH); 
    		frame.setContentPane(panel);
    		frame.setVisible(true);
    		
    		//table 設定
    		table.setRowHeight(30);// 設置間格大小
    		table.setFont(new Font("標楷体", Font.PLAIN, 18));
    		table.setForeground(Color.BLACK);
    		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//設置table内容居中
    		tcr.setHorizontalAlignment(SwingConstants.CENTER);
    		table.setDefaultRenderer(Object.class, tcr);
    		
    		//table header 大小
    		JTableHeader head = table.getTableHeader(); // 創建表格标题對象
            head.setPreferredSize(new Dimension(head.getWidth(), 35));// 設置表頭大小
            head.setFont(new Font("標楷体", Font.PLAIN, 18));
            head.setForeground(Color.BLUE);
            head.setOpaque(false);
            
     		
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null,"ERROR");
    	}finally {
    		try {
    			st.close();
    			rs.close();
    			con.close();
    		}catch(Exception e){
    			JOptionPane.showMessageDialog(null,"ERROR CLOSW");
    		}
    	}
    		
    }  
    
    //Back_to_Startpage
    public static void Back_start(){
    frame.setVisible(false);  
   	Start_page.Start.main(null);
    }         
    
    //Exit對話窗
    public static void windowClosing(WindowEvent e){
        int result=JOptionPane.showConfirmDialog(frame, "是否確定要離開?", "確定??", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
        if (result==JOptionPane.YES_OPTION){
        	frame.dispose();
            System.exit(0);  
        }else{
        	frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }            
    }  
  
}  
