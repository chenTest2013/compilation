package chen.input;

import java.util.HashMap;

public class PreTreatment {// Ԥ�������

	HashMap<Integer, Integer> offset;// ���ÿ���ַ���//���ֵ�����
	StringBuilder sbu;// ��Ŵ�����str
	HashMap<Integer, String> note;// ���//ע�͵Ĳ���
	HashMap<Integer, Integer> loc;// ��������ַ�����/*��*/������/*=key */=value

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

	public String twoslash(String str) {// ����"//"ע�͵�����
		String[] strarray = str.split('\n' + "");//����Ϊ��λ����
		for (String s : strarray) {
			int start = s.indexOf("//");
			if (start < 0) {
				sbu = sbu.append(s);
				continue;//��������ѭ��,�������forѭ����������䲻ִ��
			}
			offset.put(offset.size(), start);
			note.put(note.size(), s.substring(start));
			if (start != 0) {
				sbu = sbu.append(s.substring(0, start));
			}

		}

		return sbu.toString();

	}

	public String slashstart(String str) {// ����/* ���һ��Ҫ��twoslash(String str)֮ǰִ��
		/*1����ע��  �����ִ�� twoslash(String str)
		 *2 �Ὣ��7�е�"/*"�Ľ�����ɾ��,���³���
		 *3 /*
		 *4 
		 *5
		 *6 �е�ע�� 
		//7*/
		
		while (sbu.indexOf("/*") >= 0) {

		}
		sbu.indexOf("/*");
		sbu.indexOf("*/");
		// str.replaceAll(regex, replacement);
		return null;

	}
	public HashMap<Integer, Integer> location(String str) {// Ѱ���ַ���������"/*"���ڵ�����
		for (int i = str.indexOf("/*"); i >= 0;) {	// ����һ
		//int j=0;
			loc.put(loc.size(), i);
			i = str.indexOf("/*", i + 1);
			//j=str.indexOf("*/",j);
			
		}

		/*
		 * int i=0; ������
		 *  while(str.indexOf("//")>=0){
		 *   i=str.indexOf("//",i+1);
		 * System.out.println(i); 
		 * if(i<0){ break; } 
		 * }
		 */
		return loc;

	}
}
