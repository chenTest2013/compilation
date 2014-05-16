package chen.input;

import java.util.HashMap;

public class PreTreatment {// 预处理程序

	HashMap<Integer, Integer> offset;// 存放每行字符串//出现的索引
	StringBuilder sbu;// 存放处理后的str
	HashMap<Integer, String> note;// 存放//注释的部分
	HashMap<Integer, Integer> loc;// 存放整个字符串中/*和*/的索引/*=key */=value

	public PreTreatment() {
		super();
		offset = new HashMap<Integer, Integer>();
		sbu = new StringBuilder();
		note = new HashMap<Integer, String>();
		loc = new HashMap<Integer, Integer>();

	}

	public String treatMent(String str) {
		return twoslash(str);

	}

	public String twoslash(String str) {// 除掉"//"注释的内容
		String[] strarray = str.split('\n' + "");//以行为单位处理
		for (String s : strarray) {
			int start = s.indexOf("//");
			if (start < 0) {
				sbu = sbu.append(s);
				continue;//跳出本次循环,后面的在for循环中所有语句不执行
			}
			offset.put(offset.size(), start);
			note.put(note.size(), s.substring(start));
			if (start != 0) {
				sbu = sbu.append(s.substring(0, start));
			}

		}

		return sbu.toString();

	}

	public String slashstart(String str) {// 除掉/* 这个一定要在twoslash(String str)之前执行
		/*1测试注释  如果先执行 twoslash(String str)
		 *2 会将第7行的"/*"的结束符删除,导致出错
		 *3 /*
		 *4 
		 *5
		 *6 中的注释 
		//7*/
		
		while (sbu.indexOf("/*") >= 0) {

		}
		sbu.indexOf("/*");
		sbu.indexOf("*/");
		// str.replaceAll(regex, replacement);
		return null;

	}
	public HashMap<Integer, Integer> location(String str) {// 寻找字符串中所有"/*"所在的索引
		for (int i = str.indexOf("/*"); i >= 0;) {	// 方法一
		//int j=0;
			loc.put(loc.size(), i);
			i = str.indexOf("/*", i + 1);
			//j=str.indexOf("*/",j);
			
		}

		/*
		 * int i=0; 方法二
		 *  while(str.indexOf("//")>=0){
		 *   i=str.indexOf("//",i+1);
		 * System.out.println(i); 
		 * if(i<0){ break; } 
		 * }
		 */
		return loc;

	}
}
