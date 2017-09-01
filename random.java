package jxlReadDemo;

import java.util.Random;

public class random {
	final String x="15044051";
	public String getRandom(){
		int a;
		Random r=new Random();
		a=r.nextInt(50)+6;
		//System.out.println(a);
		String result=x+a+"";
		return result;
	}
	
}
