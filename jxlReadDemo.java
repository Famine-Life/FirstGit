package jxlReadDemo;

import javax.swing.JFileChooser;


import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * ��jexcel��ȡ����Ϣ���в���
 * 2017.9.1
 * author:lt
 * 
 * ȱ�ݣ��������Jexcel������֧��97/2000/xp/2003(*.xls)��ʽ,�ɸ���poi��
 */
//�ǵõ���jxl.jar
public class jxlReadDemo extends ActionListen{
	String s;
	JFileChooser fileDialog;
	String[][] table;
	public HashMap map;
	public jxlReadDemo(){
		map =  new HashMap<Integer,String>();
	}
	public String[][] look()
	{	
			fileDialog=new JFileChooser();		//�Ի���
			int state = fileDialog.showOpenDialog(fileDialog);
			if(state == JFileChooser.APPROVE_OPTION)
			{
			try{
				fileDialog.showOpenDialog(null);				//λ��
				File dir =fileDialog.getCurrentDirectory();		//Ŀ¼
				String name=fileDialog.getSelectedFile().getName(); //��ѡ����ļ�
				//System.out.println(name);
				String excelTypeR=name.substring(name.indexOf(".")+1, name.length());
				String excelTypeL=name.substring(0,name.indexOf(".")+1);
				if(excelTypeR.equals("xls"))
					name=excelTypeL+excelTypeR;
				else if(name.equals("xlsx"))
				{
					excelTypeR="xls";
					name=excelTypeL+excelTypeR;
				}
				else throw new Exception("��ȷ����򿪵���.xls�ļ�����Ŀǰ��֧��xls!");
				System.out.println(name);
				
				File File =new File(dir,name);  //�����ļ�
					//File xlsFile= new File("jxl.xls");
					System.out.println(File);//test
	
				FileInputStream xlsFile=new FileInputStream(File);
				BufferedReader br = new BufferedReader(new InputStreamReader(xlsFile, "utf-8"));
			      // ��ù��������� 
				Workbook workbook = Workbook.getWorkbook(xlsFile);
				if(workbook instanceof Workbook)		//ȷ����ȡ��Workbook
			     System.out.println("�ѻ�ù���������");//test
			      // ������й�����
			      Sheet[] sheets = workbook.getSheets();
			      // ����������
			      if (sheets != null)
			      {
			         for (Sheet sheet : sheets)
			         {
			            // �������
			            int rows = sheet.getRows();
			            // �������
			            int cols = sheet.getColumns();
			            // ��ȡ����
			            System.out.println("������������"+rows+"\t"+cols);
			            table=new String[rows][cols];  //ʵ������ά����
			            for (int row = 0; row < rows; row++)
			            {
			               for (int col = 0; col < cols; col++)
			               {
			            	   Cell cell=sheet.getCell(col, row);//.getContents();
			            	  if(cell.getType() != CellType.EMPTY)	//��Ϊ��
			            	  {
			            		  table[row][col]=cell.getContents();
			            		  //System.out.println(row+"��+"+col+"��:");
			            		  System.out.printf("%s\t", cell.getContents().toString());
			            	  }
			            	  
			            	 // System.out.println("test:"+sheet.getRowView(4));
			                  //System.out.printf("%20s", sheet.getCell(col, row)
			                  //      .getContents());
			               }
			               System.out.println();
			            }
			            
			         }
			         
			      }
			      
			  jxlput(table);
			  br.close();
			  workbook.close(); 
		      xlsFile.close();
			}  
		    
			catch(Exception e)
			{
					System.out.println("error:"+e);
			}
			}
		else if(state == JFileChooser.CANCEL_OPTION)
		{
			System.out.println("����ȡ��������");
			table=null;
		}
		return table;
	}	   

	public void jxlput(String[][] t)    //��̬����,ʵ�ִ����鵼��map
	{
	
//		map.put(1, "lt");
//		String value=(String) map.get(1);
//		System.out.println(value);
		
//		System.out.println("map���������");
		try{
         for (int row = 0; row < t.length; row++)
         {
        	 int col=0;
        	 int ValueCol=1; //�ڶ���
              //���������������ݡ�
         	  if(!t[row][col].trim().isEmpty())	//��Ϊ��
         	  {
         		 //����鿴����  
                //System.out.println("����:"+t[row][col].getClass().getSimpleName());  				
         		  map.put(t[row][col], t[row][ValueCol]);
         	  }
            }
         //keySet(); ��ѯ
         }
		catch(Exception e){System.out.println("�����쳣�˳�"+e);}
	}
	
	//���map������value
	public  void keySet(){ 
		try{//ͨ�� keySet����������Map���С������ļ���
		Set<String> keySet = map.keySet();
		//ȡ��student������
		System.out.println("��" + map.size()+"��:");
		//����keySet,ȡ��ÿһ�������ٵ���get����ȡ��ÿһ������Ӧ��value
		
			for(String s:keySet){
				System.out.println(map.get(s));
				}
			}
		catch(Exception e){System.out.println(e);}
		}
	public String KeyToValue(String s){  //chouqu
		String result=null;
		if(map.containsKey(s))			//�ж�map���Ƿ��и�key!
			result=(String) map.get(s);
		else
			result="�����ļ���û���ҵ���\""+s+"\"�������Ϣ!";		
			//String a=1+"";
			//System.out.println(map.get(a)+"!!!!!!!!");

		return result;
	}
}

