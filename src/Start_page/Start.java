package Start_page;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
 
public class Start extends JFrame implements ActionListener{
     
	private boolean noInput;
	
    public static void main(String[] args) {
        new Start();
    }
     
    Start() {    
        this.setTitle("Start_page");
        this.setLayout(null);
        
        //Start,Exit,Score按鈕新增
        JButton Start = new JButton();
        this.add(Start);
        JButton Exit = new JButton();
        this.add(Exit);
        JButton Score = new JButton();
        this.add(Score);
        
        //Start,Exit,Score按鈕圖案
        ImageIcon start_icon = new ImageIcon(Start.class.getClassLoader().getResource("start_icon.png"));
        ImageIcon exit_icon = new ImageIcon(Start.class.getClassLoader().getResource("exit_icon.png"));
        ImageIcon score_icon = new ImageIcon(Start.class.getClassLoader().getResource("score_icon.png"));
         
        //背景
        ImageIcon background = new ImageIcon(Start.class.getClassLoader().getResource("Start_background.jpg"));
        JLabel bkLabel = new JLabel(background);
        bkLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
        this.setSize(background.getIconWidth(), background.getIconHeight()+40);
        this.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));
        JPanel ctPanel = (JPanel)this.getContentPane();
        ctPanel.setOpaque(false);
        
        //PLAYER NAME JLABEL
        JLabel label1=new JLabel("PLAYER NAME : ");
        label1.setFont(new Font("標楷體?", 1, 40));
        label1.setForeground(Color.getHSBColor(178, 34, 34));
        label1.setBounds(50,50,350,80);
        //label1.setIcon(exit_icon);
        this.add(label1);
        
        //PLAYER NAME JTextField
        JTextField NameInput = new JTextField();
        NameInput.addActionListener(this); 
        NameInput.setFont(new Font("標楷體", 1, 40));
        NameInput.setForeground(Color.getHSBColor(25, 86, 55));
        NameInput.setBounds(370,70,350,40); 
        NameInput.setCaretColor(Color.yellow);
        NameInput.setOpaque(false);
        add(NameInput); 
   
        //設置Start按鈕
        Start.setIcon(start_icon);
        Start.setBounds(100,150, 170,53);//設置大小
        Start.setContentAreaFilled(false); //設置透明背景
        Start.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){ 
    			String playername = NameInput.getText();
    			System.out.print( playername + "\n" );
    			Insert_playername(playername);//新增PlayerName到資料庫
    			Start_game();//進入Play_page
    			} 
    		}); //監聽
        
        //設置Exit按鈕
        Exit.setIcon(exit_icon);
        Exit.setBounds(300,150, 170,53);
        Exit.setContentAreaFilled(false);
        Exit.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){ 
			windowClosing(null);//關掉視窗
			} 
		}); //監聽
        
        //設置Score按鈕
        Score.setIcon(score_icon);
        Score.setBounds(500,150, 170,53);
        Score.setContentAreaFilled(false); 
        

         
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }            
        });
        this.setVisible(true);
     }
    
    
     
     //Exit對話窗
     public void windowClosing(WindowEvent e){
         int result=JOptionPane.showConfirmDialog(this, "是否確定要離開?", "確定??", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             
         if (result==JOptionPane.YES_OPTION){
             this.dispose();
             System.exit(0);  
         }else{
             this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//??��?��?��??
         }            
     }
     
     //Start_game
     public void Start_game(){
    	 this.setVisible(false);  
    	 Second_page.Table.main(null);
     }
     
     //Insert_playername
     public void Insert_playername(String playername){
    	 Connectmysql.UpdatePlayer_NameTime.main(playername);
     }

	@Override
	public void actionPerformed(ActionEvent e) {

		
	}
	
	//TextField??�示
	/*public class JTextFieldHintListener implements FocusListener {
	    private String mHindText;
	    private JTextField mTextField;

	    public JTextFieldHintListener(String hintText, JTextField textField) {
	        this.mHindText = hintText;
	        this.mTextField = textField;
	        textField.setForeground(Color.GRAY);
	    }
	    @Override
	    public void focusGained(FocusEvent e) {
	        String temp = mTextField.getText();
	        if(temp.equals(mHindText)){
	            mTextField.setText("");
	            mTextField.setForeground(Color.BLACK);
	        }
	    }
	    @Override
	    public void focusLost(FocusEvent e) {
	        String temp = mTextField.getText();
	        if(temp.equals("")){
	            mTextField.setForeground(Color.GRAY);
	            mTextField.setText(mHindText);
	        }
	    }
	}*/


 }
