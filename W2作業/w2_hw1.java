import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class w2_hw1 extends JFrame implements ActionListener{ 
	static JPanel pne=new JPanel();
	static JButton btn1=new JButton("擲骰子");
    static JLabel lab=new JLabel("0");
	static JLabel toplab=new JLabel("已擲 0 次，總和 0，平均 0.00");
	
	static int count = 0; //擲骰子次數用類別變數
	static int total = 0; //擲骰子的總和用類別變數
	
	public static void main(String args[]){
		w2_hw1 frm = new w2_hw1();
		frm.setTitle("骰子模擬器");
		btn1.addActionListener(frm);
		
		BorderLayout border=new BorderLayout();
		frm.setLayout(border);
		
		frm.setSize(400,320);
		frm.setLocationRelativeTo(null);
		frm.add(btn1,BorderLayout.SOUTH);
		
		lab.setFont(new Font("SansSerif", Font.BOLD, 60));
		lab.setHorizontalAlignment(JLabel.CENTER);
		
		pne.setLayout(new BorderLayout()); 
        pne.add(lab, BorderLayout.CENTER);
		frm.add(pne, BorderLayout.CENTER);
		frm.add(toplab, BorderLayout.NORTH);
		
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frm.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		int dice = (int)(Math.random() *6) +1;
		lab.setText(String.valueOf(dice));
		
		if(dice == 6){
			lab.setForeground(Color.GREEN);
		}else if(dice == 1){
			lab.setForeground(Color.RED);
		}else{
			lab.setForeground(Color.BLACK);
		}
		
		count++;
		total += dice;
		double avg = (double)total/count;
		
		toplab.setText(String.format("已擲 %d 次，總和 %d ，平均 %.2f",count,total,avg));
	}
}
