package chen.compile;

import java.util.HashMap;

public class Function {
	String[] word;
	Judge jd;
	int count = 0;// ��¼�ַ�����ĸ�ĸ���
	public Function() {
	jd=new Judge();
	}
	public String wordReplace(String str) {//�滻�ַ�������
		String temp;
		temp = str.replace("20117790205", "��ΡΡ");
		return temp;

	}

	public int wordsCount(String str) {//ͳ�ƹ��ж�����ĸ
		char[] chr = str.toCharArray();
		for (int i = 0; i < chr.length; i++) {
			if(jd.IsLetter(chr[i])){
				count++;
			}
		}
		return count;

	}
	public int wordCount(HashMap<Integer, String> map) {//ͳ�Ʋ�ͬӢ�ﵥ�ʸ���
		System.out.println(map.toString());
		int len = map.size();
		System.out.println("map�ĳ���" + len);
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
		System.out.println("��ͬ���ʵļ���\t" + temp.toString());
		return temp.size();
	}
	public String change(){
		
		return null;
		
	}
	public String space(String str) {// �����ַ��������пո�
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
