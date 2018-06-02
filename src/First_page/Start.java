package First_page;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

  
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
 
public class Start extends JFrame implements ActionListener{
     
     
    public static void main(String[] args) {
        new Start();
    }
     
    Start() {    
        this.setTitle("Background");
        this.setLayout(null);
         
        JButton Start = new JButton();
        this.add(Start);
        JButton Exit = new JButton();
        this.add(Exit);
        JButton Score = new JButton();
        this.add(Score);
         
        ImageIcon start_icon = new ImageIcon(Start.class.getClassLoader().getResource("start_icon.png"));
        ImageIcon exit_icon = new ImageIcon(Start.class.getClassLoader().getResource("exit_icon.png"));
        ImageIcon score_icon = new ImageIcon(Start.class.getClassLoader().getResource("score_icon.png"));
         
        //放置背景圖
        ImageIcon background = new ImageIcon(Start.class.getClassLoader().getResource("Start_background.jpg"));
        JLabel bkLabel = new JLabel(background);
        bkLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
        this.setSize(background.getIconWidth(), background.getIconHeight()+40);
        this.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));
        JPanel ctPanel = (JPanel)this.getContentPane();
        ctPanel.setOpaque(false);
         
        //設置按鈕樣式
        Start.setIcon(start_icon);
        //Start.setPreferredSize(new Dimension(170,53));//設置大小
        Start.setBounds(100,150, 170,53);//設置大小
        Start.setContentAreaFilled(false); //設置背景透明
        Start.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){ 
    			Start_game();
    			} 
    		}); //按鈕監聽
        
        Exit.setIcon(exit_icon);
        Exit.setBounds(300,150, 170,53);
        Exit.setContentAreaFilled(false);
        Exit.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){ 
			windowClosing(null);
			} 
		}); //按鈕監聽
         
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
     
     //Exit對話框 & Exit
     public void windowClosing(WindowEvent e){
         int result=JOptionPane.showConfirmDialog(this, "是否確定離開?", "確定", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             
         if (result==JOptionPane.YES_OPTION){
             this.dispose();
             System.exit(0);  
         }else{
             this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//關閉視窗
         }            
     }
     
     public void Start_game(){
    	 this.setVisible(false);  
    	 Second_page.Table.main(null);
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		/* if(e.getSource()==Start){
			 
		 }*/
		
	}


 }
