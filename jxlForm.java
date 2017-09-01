package jxlReadDemo;

import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class jxlForm extends JFrame{
	Box baseBox,box,box2,box3;
	ActionListen listenner;
	KeyListener keyListener;
	public jxlForm()
	{
		setLayout(new java.awt.FlowLayout());
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void init(){
		box=Box.createVerticalBox();
		box.add(Box.createVerticalStrut(10));
		JButton btn=new JButton("查询");
		box.add(btn);
		listenner = new ActionListen();
		btn.addActionListener(listenner);
		//listenner.setJButton(btn);			//传递索引
		
		box2=Box.createVerticalBox();
		box2.add(Box.createVerticalStrut(10));
		JButton btn2=new JButton("抽取");
		box2.add(btn2);
		btn2.addActionListener(listenner);
		listenner.setBtn(btn2);
		
		
		box3=Box.createVerticalBox();
		box3.add(Box.createVerticalStrut(10));
		JTextArea textShow=new JTextArea(20,30);
		box3.add(textShow);
		listenner.setShowBtn(textShow);
		textShow.setEditable(false);
		
		baseBox=Box.createHorizontalBox();
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(box);
		baseBox.add(box2);
		baseBox.add(box3);
		add(baseBox);
	}
}
