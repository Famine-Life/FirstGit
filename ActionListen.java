package jxlReadDemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ActionListen implements ActionListener{
	JTextArea textShow;
	JButton ccBtn;
	String[][] table;
	public void setShowBtn(JTextArea t){
		textShow=t;
	}
	public void setBtn(JButton btn){
		ccBtn=btn;
	}
	public void actionPerformed(ActionEvent e){
		JButton buttonNum=(JButton)e.getSource();
		String str=buttonNum.getText();
		//System.out.println(str);
					
			if(str=="��ѯ")
			{
				jxlReadDemo jxl=new jxlReadDemo();
				table=jxl.look();
			//	jxl.jxlput(table);
			//	jxl.keySet();
			}	
			if(str=="��ȡ"){
				String name=null;
				jxlReadDemo jxl=new jxlReadDemo();	//ʵ����
				random r = new random();
				
				jxl.jxlput(table);	//������
				
				String s = null;
				s=r.getRandom();	//���������
				name=jxl.KeyToValue(s);	//����map��value����
				
//				while(true){			//
//					if(name==null){		//�������null�����·����µ������,,but����������ݽ�������ѭ��
//						s=r.getRandom();
//						name=jxl.KeyToValue(s);	
//					}else break;
//				}
				textShow.setText("");
				textShow.append("�������"+s+"\n");
				textShow.append(name+"!!");
			}

	}
}

