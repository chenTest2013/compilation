package chen.compile;

import java.util.HashMap;

public class Function {
	String[] word;
	Judge jd;
	int count = 0;// 记录字符中字母的个数
	public Function() {
	jd=new Judge();
	}
	public String wordReplace(String str) {//替换字符串方法
		String temp;
		temp = str.replace("20117790205", "陈巍巍");
		return temp;

	}

	public int wordsCount(String str) {//统计共有多少字母
		char[] chr = str.toCharArray();
		for (int i = 0; i < chr.length; i++) {
			if(jd.IsLetter(chr[i])){
				count++;
			}
		}
		return count;

	}
	public int wordCount(HashMap<Integer, String> map) {//统计不同英语单词个数
		System.out.println(map.toString());
		int len = map.size();
		System.out.println("map的长度" + len);
		HashMap<Integer, String> temp = new HashMap<Integer, String>();
		for (int i = 0; i < len; i++) {
			if (temp.size() == 0) {
				temp.put(0, map.get(i));
			} 
			else if (!map.get(0).equals(map.get(i))
					&& !temp.containsValue(map.get(i))) {
				temp.put(temp.size(), map.get(i));
			}
		}
		if (temp.size() == 1) {
			return 0;
		}
		System.out.println("不同单词的集合\t" + temp.toString());
		return temp.size();
	}
	public String change(){
		
		return null;
		
	}
	public String space(String str) {// 除掉字符串的所有空格
		StringBuilder sb = new StringBuilder();
		char[] ch = str.trim().toCharArray();
		for (char c : ch) {
			if (!jd.isEmpty(c)) {
				sb.append(c);
			}
		}
		return sb.toString();

	}
}
