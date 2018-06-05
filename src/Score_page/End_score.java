package Score_page;

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

import Start_page.Start;
 
public class End_score extends JFrame implements ActionListener{
     
	private boolean noInput;
	
    public static void main(String[] args) {
        new End_score();
    }
     
    End_score() {    
        this.setTitle("");
        this.setLayout(null);
        
        //Start,Exit,Score按鈕新增
        JButton Restart = new JButton();
        this.add(Restart);
        JButton Exit = new JButton();
        this.add(Exit);
        JButton Score = new JButton();
        this.add(Score);
        
        //Start,Exit,Score按鈕圖案
        ImageIcon restart_icon = new ImageIcon(Start.class.getClassLoader().getResource("restart_icon.png"));
        ImageIcon exit_icon = new ImageIcon(Start.class.getClassLoader().getResource("exit_icon.png"));
        ImageIcon score_icon = new ImageIcon(Start.class.getClassLoader().getResource("score_icon.png"));
        ImageIcon score_img = new ImageIcon(Start.class.getClassLoader().getResource("score_img.png"));
        
        //背景
        ImageIcon background = new ImageIcon(Start.class.getClassLoader().getResource("Start_background_3.png"));
        JLabel bkLabel = new JLabel(background);
        bkLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
        this.setSize(background.getIconWidth(), background.getIconHeight()+40);
        this.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));
        JPanel ctPanel = (JPanel)this.getContentPane();
        ctPanel.setOpaque(false);
        
        String score = Connectmysql.Get_score.main();
        
        //SCORE_IMG JLABEL
        JLabel Score_img=new JLabel();
        Score_img.setBounds(870,50,400,400);
        Score_img.setIcon(score_img);
        this.add(Score_img);
        
        //SCORE JLABEL
        JLabel label1=new JLabel(score);
        label1.setFont(new Font("標楷體?", 1, 220));
        label1.setForeground(Color.getHSBColor(178, 34, 34));
        label1.setBounds(800,0,350,250);
        this.add(label1);
        
        //設置Restart按鈕
        Restart.setIcon(restart_icon);
        Restart.setBounds(100,95, 200,53);
        Restart.setContentAreaFilled(false);
        Restart.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){ 
    			Restart();//進入Score_page
    			} 
    		}); //監聽
             
        //設置Exit按鈕
        Exit.setIcon(exit_icon);
        Exit.setBounds(330,95, 170,53);
        Exit.setContentAreaFilled(false);
        Exit.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){ 
			windowClosing(null);//關掉視窗
			} 
		}); //監聽
        
        //設置Score按鈕
        Score.setIcon(score_icon);
        Score.setBounds(530,95, 170,53);
        Score.setContentAreaFilled(false);
        Score.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){ 
    			Score_list();//進入Score_page
    			} 
    		}); //監聽
               
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
             this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
         }            
     }
     
     
     //Enter_scorepage
     public void Score_list(){
    	 this.setVisible(false);  
    	 Score_page.BestScore_list.main(null);
     }
     
     //Restart
     public void Restart(){
    	    this.setVisible(false);  
    	   	Start_page.Start.main(null);
    	    }      

	@Override
	public void actionPerformed(ActionEvent e) {

		
	}

 }