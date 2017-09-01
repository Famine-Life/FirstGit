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
					
			if(str=="查询")
			{
				jxlReadDemo jxl=new jxlReadDemo();
				table=jxl.look();
			//	jxl.jxlput(table);
			//	jxl.keySet();
			}	
			if(str=="抽取"){
				String name=null;
				jxlReadDemo jxl=new jxlReadDemo();	//实例化
				random r = new random();
				
				jxl.jxlput(table);	//导入表格
				
				String s = null;
				s=r.getRandom();	//生成随机数
				name=jxl.KeyToValue(s);	//查找map的value方法
				
//				while(true){			//
//					if(name==null){		//如果出现null则重新返回新的随机数,,but如果导错数据将进入死循环
//						s=r.getRandom();
//						name=jxl.KeyToValue(s);	
//					}else break;
//				}
				textShow.setText("");
				textShow.append("随机数："+s+"\n");
				textShow.append(name+"!!");
			}

	}
}

