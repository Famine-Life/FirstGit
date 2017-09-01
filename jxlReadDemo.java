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
 * 对jexcel读取的信息进行操作
 * 2017.9.1
 * author:lt
 * 
 * 缺陷：导入的是Jexcel包，仅支持97/2000/xp/2003(*.xls)格式,可改用poi包
 */
//记得导入jxl.jar
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
			fileDialog=new JFileChooser();		//对话框
			int state = fileDialog.showOpenDialog(fileDialog);
			if(state == JFileChooser.APPROVE_OPTION)
			{
			try{
				fileDialog.showOpenDialog(null);				//位置
				File dir =fileDialog.getCurrentDirectory();		//目录
				String name=fileDialog.getSelectedFile().getName(); //被选择的文件
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
				else throw new Exception("请确认你打开的是.xls文件。。目前仅支持xls!");
				System.out.println(name);
				
				File File =new File(dir,name);  //创建文件
					//File xlsFile= new File("jxl.xls");
					System.out.println(File);//test
	
				FileInputStream xlsFile=new FileInputStream(File);
				BufferedReader br = new BufferedReader(new InputStreamReader(xlsFile, "utf-8"));
			      // 获得工作簿对象 
				Workbook workbook = Workbook.getWorkbook(xlsFile);
				if(workbook instanceof Workbook)		//确保获取到Workbook
			     System.out.println("已获得工作簿对象");//test
			      // 获得所有工作表
			      Sheet[] sheets = workbook.getSheets();
			      // 遍历工作表
			      if (sheets != null)
			      {
			         for (Sheet sheet : sheets)
			         {
			            // 获得行数
			            int rows = sheet.getRows();
			            // 获得列数
			            int cols = sheet.getColumns();
			            // 读取数据
			            System.out.println("行数和列数："+rows+"\t"+cols);
			            table=new String[rows][cols];  //实例化二维数组
			            for (int row = 0; row < rows; row++)
			            {
			               for (int col = 0; col < cols; col++)
			               {
			            	   Cell cell=sheet.getCell(col, row);//.getContents();
			            	  if(cell.getType() != CellType.EMPTY)	//不为空
			            	  {
			            		  table[row][col]=cell.getContents();
			            		  //System.out.println(row+"行+"+col+"列:");
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
			System.out.println("您已取消操作！");
			table=null;
		}
		return table;
	}	   

	public void jxlput(String[][] t)    //静态方法,实现从数组导入map
	{
	
//		map.put(1, "lt");
//		String value=(String) map.get(1);
//		System.out.println(value);
		
//		System.out.println("map输出方法！");
		try{
         for (int row = 0; row < t.length; row++)
         {
        	 int col=0;
        	 int ValueCol=1; //第二列
              //遍历行数导入数据。
         	  if(!t[row][col].trim().isEmpty())	//不为空
         	  {
         		 //反射查看类型  
                //System.out.println("类型:"+t[row][col].getClass().getSimpleName());  				
         		  map.put(t[row][col], t[row][ValueCol]);
         	  }
            }
         //keySet(); 查询
         }
		catch(Exception e){System.out.println("操作异常退出"+e);}
	}
	
	//输出map的所有value
	public  void keySet(){ 
		try{//通过 keySet方法，返回Map所有“键”的集合
		Set<String> keySet = map.keySet();
		//取得student的容量
		System.out.println("有" + map.size()+"个:");
		//遍历keySet,取得每一个键，再调用get方法取得每一个键对应的value
		
			for(String s:keySet){
				System.out.println(map.get(s));
				}
			}
		catch(Exception e){System.out.println(e);}
		}
	public String KeyToValue(String s){  //chouqu
		String result=null;
		if(map.containsKey(s))			//判断map中是否含有该key!
			result=(String) map.get(s);
		else
			result="数据文件中没有找到：\""+s+"\"的相关信息!";		
			//String a=1+"";
			//System.out.println(map.get(a)+"!!!!!!!!");

		return result;
	}
}

