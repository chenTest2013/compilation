package chen.compile;

import java.util.ArrayList;
import java.util.HashMap;

public class Grammaranalyze {
    boolean mark;// ����Ƿ����,���ڿ����˳��ݹ�
    StringBuilder show;
    StringBuilder error;
    Wordanalyze wl;
    Judge jd;
    FirstFollow ff;
    HashMap<String, String[]> stogrammar;// �洢�ķ� :key=���ս��,value=��ѡʽ����
    HashMap<String, String> endstr;// �洢�ս��
    HashMap<String, String> startstr;// �洢���ս��

    ArrayList<String[]> gra;// �洢�ķ� :�ַ�������洢ÿһ������ʽ
    HashMap<Integer, String[]> gramer;// �洢�ķ� :�ַ�������洢ÿһ������ʽ

    HashMap<String, HashMap<String, String>> first;// �洢first��
    HashMap<String, HashMap<String, String>> follow;// �洢follow��
    int loc = 0;// i*i+i��λ��ָʾ��
    String newstr;// ���¶������ַ�
    boolean succcess = false;// ����Ƿ��ÿմ����

    public static void main(String[] args) {// �����ķ�,��ͷ�ö���(,)��ʾ,ÿ������ʽ��;�ָ�(ע����ŵ���Ӣ��)
	Grammaranalyze g = new Grammaranalyze();
	long t1 = System.currentTimeMillis();
	String str = "E��TE';E'��+TE'|-TE'|��;T��FT';T'�� *FT' | /FT'|��;F�� (E) | i";
	//String input1 = "25.6 * 14.5 + 2";
	//String input2 = "2 / 5.2 + 78 - 6";
	//String input3 = "2 / 5.2 + 78 -";
	//String input4 = "+56 * 7 ";
	//String str5 = "34+ -2*8";
	g.analyze(str, "2/3*8+(8**9)");
	// g.print("gra");
	System.out.println("����ʱ:" + (System.currentTimeMillis() - t1) + "ms");
    }

    public Grammaranalyze() {
	super();
	show = new StringBuilder();
	error = new StringBuilder();
	this.wl = new Wordanalyze();
	jd = new Judge();
	ff = new FirstFollow();
	stogrammar = new HashMap<String, String[]>();
	endstr = new HashMap<String, String>();
	startstr = new HashMap<String, String>();
	gramer = new HashMap<Integer, String[]>();
	gra = new ArrayList<String[]>();
	first = new HashMap<String, HashMap<String, String>>();
	follow = new HashMap<String, HashMap<String, String>>();

    }

    public StringBuilder getError() {
	return error;
    }

    public void setError(StringBuilder error) {
	this.error = error;
    }

    public StringBuilder getShow() {
	return show;
    }

    public void setShow(StringBuilder show) {
	this.show = show;
    }

    public String analyze(String str, String input) {
	if("".equals(str)){
	    str = "E��TE';E'��+TE'|-TE'|��;T��FT';T'�� *FT' | /FT'|��;F�� (E) | i";
	}
	stoGrammar(str);
	ff.in = gra;
	ff.process("First");
	ff.process("Follow");
	// System.out.println("�ս��:" + endstr.toString());
	// System.out.println("���ս��:" + startstr.toString());
	process(input);
	return str;

    }

    public void stoGrammar(String str) {// ��������ַ���ת��Ϊ�ķ�
	String[] temp = space(str).split(";");// �õ�����ʽ����
	// System.out.println("����ʽ����\t" + temp.length);
	for (String proString : temp) {// �õ�ÿһ������ʽ
	    // System.out.println("����ʽ:\t" + proString);
	    proDuction(proString);
	}
    }

    public void proDuction(String str) {// ����ÿһ������ʽ,���ڵõ����ս�����ս��
	String[] production = str.split("��");// �����ս���ͺ�ѡʽ�ֿ�
	// production[0]=���ս�� production[1]=��ѡʽ�ĺ�д��
	if (!endstr.containsKey(production[0])) {
	    endstr.put(production[0], production[0]);// �����ս������

	}
	String[] pro = production[1].split("\\|");// ����ѡʽ�ֿ�,�õ���ѡʽ����
	stogrammar.put(production[0], pro);// �洢�ķ� :key=���ս��,value=��ѡʽ����

	for (String string : pro) {
	    // System.out.println(production[0] + "�ĺ�ѡʽ\t" + string.trim());
	    String[] productions = new String[2];
	    productions[0] = production[0];
	    productions[1] = string.trim();
	    gramer.put(gramer.size(), productions);// �洢�ķ� :�ַ�������洢ÿһ������ʽ
	    gra.add(productions);// �洢�ķ� :�ַ�������洢ÿһ������ʽ���������first��follow��
	    selected(string);// ����ÿһ����ѡʽ,���ڵõ����ս������
	}

    }

    public void selected(String str) {// ����ÿһ����ѡʽ,���ڵõ����ս������
	char[] chr = str.trim().toCharArray();
	for (char c : chr) {
	    if (!(jd.IsTopLetter(c) || c == '\'')
		    && !startstr.containsKey(c + "")) {
		// System.out.println((int)c+"\t������ս������");
		startstr.put(c + "", c + "");// ������ս������
	    }
	}

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

    public void print(String str) {// ��ӡfirst����follow��
	boolean p = false;
	if (str.equals("")) {
	    p = true;
	}
	if (p || str.equals("ff")) {
	    System.out.println("\nFirst����");
	    for (int i = 0; i < ff.first.size(); i++) {
		String[] r = ff.first.get(i);
		System.out.print("First(" + r[0] + ")={");
		for (int j = 1; j < r.length; j++) {
		    System.out.print(r[j]);
		    if (j < r.length - 1)
			System.out.print(",");
		}
		System.out.println("}");
	    }
	    System.out.println("\nFollow����");
	    for (int i = 0; i < ff.follow.size(); i++) {
		String[] r = ff.follow.get(i);
		System.out.print("Follow(" + r[0] + ")={");
		for (int j = 1; j < r.length; j++) {
		    System.out.print(r[j]);
		    if (j < r.length - 1)
			System.out.print(",");
		}
		System.out.println("}");
	    }
	}
	if (p || str.startsWith("gra")) {
	    for (int j = 0; j < gra.size(); j++) {
		System.out.println(gra.get(j)[0] + "\t" + gra.get(j)[1]);
	    }
	}

    }

    public void changff() {// ת��first��follow�������ݽṹ
	for (int i = 0; i < ff.first.size(); i++) {
	    String[] r = ff.first.get(i);
	    String endstr = r[0];
	    // System.out.print(endstr+":\t");
	    HashMap<String, String> ef = new HashMap<String, String>();
	    for (int j = 1; j < r.length; j++) {
		ef.put(r[j], r[j]);
		// System.out.print(r[j]+"\t");
	    }
	    first.put(endstr, ef);
	    // System.out.println();
	}
	for (int i = 0; i < ff.follow.size(); i++) {
	    String[] r = ff.follow.get(i);
	    String endstr = r[0];
	    HashMap<String, String> ef = new HashMap<String, String>();
	    for (int j = 1; j < r.length; j++) {
		ef.put(r[j], r[j]);
	    }
	    follow.put(endstr, ef);
	}
    }

    public boolean query(String str, String ch, String type) {// ��ѯĳ�����ս����type�����Ƿ���ĳ���ս��

	changff();// ת��first��follow�������ݽṹ
	HashMap<String, String> temp = new HashMap<String, String>();
	if ("first".equals(type.trim())) {
	    temp = first.get(str);
	} else if ("follow".equals(type.trim())) {
	    temp = follow.get(str);
	}
	return temp.containsKey(ch);

    }

    public void process(String str) {// �﷨��������
	wl.analyze(str);
	HashMap<Integer, String> temp = new HashMap<Integer, String>();
	temp = wl.sto.word;
	System.out.println(temp.toString());
	recursiveE(newstr);

    }

    public String recursiveE(String str) {
	if (!mark) {
	    succcess = false;
	    if (newstr == null) {
		str = advance();
	    }
	    String[] candidate = stogrammar.get("E");// ��ѡʽ����
	    if (query("E", str, "first")) {// �����ж�str�Ƿ���First��
		for (String can : candidate) {// �ٶ�E�кܶ��ѡʽ,��forѭ���ж������ĸ���ѡʽ
		    char ch = can.charAt(0);// ȥÿһ����ѡʽ�����ַ�
		    if (query(ch + "", str, "first")) {

			System.out.println("ʹ��:" + "E��" + can);
			show.append("ʹ��:" + "E��" + can + "\n");
			
			recursiveT(newstr);
			if (!str.equals(newstr) || succcess) {// �ж��Ƿ�ƥ��ɹ�,ֻ�óɹ�newstr�Ż�ı�
			    recursiveE1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("E", "#", "first") && query("E", str, "follow")) {// #�Ŵ����
		succcess = true;
		System.out.println("ʹ��:���Զ�ƥ��" + "E");
		show.append("ʹ��:���Զ�ƥ��" + "E\n");
		return newstr;
	    } else {
		mark = true;
		error.append(str + "�ڴ˳�����һ���﷨����(E)");
		System.out.println(str + "�ڴ˳�����һ���﷨����(E)");
		show.append(str + "�ڴ˳�����һ���﷨����(E)" + "\n");
		return newstr;
	    }
	}
	return newstr;

    }

    public String recursiveE1(String str) {
	if (!mark) {
	    succcess = false;
	    String[] candidate = stogrammar.get("E'");
	    if (query("E'", str, "first")) {// �����ж�str�Ƿ���First��
		for (String can : candidate) {// forѭ�����������ַ������ĸ���ѡʽ
		    char ch = can.charAt(0);
		    if (str.equals(ch + "")) {
			System.out.println("ʹ��:" + "E'��" + can);
			show.append("ʹ��:" + "E'��" + can + "\n");
			advance();
			if (str.equals(wl.sto.word.get(wl.sto.word.size() - 1))
				|| str.equals(wl.sto.word.get(0))) {
			    mark = true;
			    error.append(str + "�ڴ˳�����һ���﷨����(E')" + "\n");
			    System.out.println(str + "�ڴ˳�����һ���﷨����(E')");
			    show.append(str + "�ڴ˳�����һ���﷨����(E')" + "\n");
			    // System.exit(0);
			    break;
			}
			recursiveT(newstr);
			if (!str.equals(newstr) || succcess) {// �ж��Ƿ�ƥ��ɹ�,ֻ�óɹ�newstr�Ż�ı�
			    recursiveE1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("E'", "#", "first") && query("E'", str, "follow")) {
		succcess = true;
		System.out.println("ʹ��:E'����");
		show.append("ʹ��:E'����" + "\n");
		return newstr;
	    } else {
		if (newstr == null) {
		    System.out.println("ʹ��:E'����");
		    show.append("ʹ��:E'����" + "\n");
		    return newstr;
		} else {
		    mark = true;
		    error.append(str + "�ڴ˳�����һ���﷨����(E')" + "\n");
		    System.out.println(str + "�ڴ˳�����һ���﷨����(E')");
		    show.append(str + "�ڴ˳�����һ���﷨����(E')" + "\n");
		    // System.exit(0);
		    return newstr;
		}
	    }
	}
	return newstr;

    }

    public String recursiveT(String str) {
	if (!mark) {
	    succcess = false;
	    String[] candidate = stogrammar.get("T");
	    if (query("T", str, "first")) {// �����ж�str�Ƿ���First��
		for (String can : candidate) {// forѭ�����������ַ������ĸ���ѡʽ
		    char ch = can.charAt(0);
		    if (query(ch + "", str, "first")) {
			System.out.println("ʹ��:" + "T��" + can);
			show.append("ʹ��:" + "T��" + can + "\n");
			recursiveF(newstr);
			if (!str.equals(newstr) || succcess) {// �ж��Ƿ�ƥ��ɹ�,ֻ�óɹ�newstr�Ż�ı�
			    recursiveT1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("T", "#", "first") && query("T", str, "follow")) {// #�Ŵ����
		succcess = true;
		System.out.println("ʹ��:���Զ�ƥ��" + "T");
		show.append("ʹ��:���Զ�ƥ��" + "T" + "\n");
		return newstr;
	    } else {
		mark = true;
		error.append(str + "�ڴ˳�����һ���﷨����(T)" + "\n");
		System.out.println(str + "�ڴ˳�����һ���﷨����(T)");
		show.append(str + "�ڴ˳�����һ���﷨����(T)" + "\n");
		// System.exit(0);
		return newstr;
	    }
	}
	return newstr;

    }

    public String recursiveT1(String str) {
	if (!mark) {
	    succcess = false;
	    String[] candidate = stogrammar.get("T'");
	    if (query("T'", str, "first")) {// �����ж�str�Ƿ���First��
		for (String can : candidate) {// forѭ�����������ַ������ĸ���ѡʽ
		    char ch = can.charAt(0);
		    if (str.equals(ch + "")) {
			System.out.println("ʹ��:" + "T'��" + can);
			show.append("ʹ��:" + "T'��" + can + "\n");
			advance();
			if (str.equals(wl.sto.word.get(wl.sto.word.size() - 1))
				|| str.equals(wl.sto.word.get(0))) {
			    mark = true;
			    error.append(str + "�ڴ˳�����һ���﷨����(T')" + "\n");
			    System.out.println(str + "�ڴ˳�����һ���﷨����(T')");
			    show.append(str + "�ڴ˳�����һ���﷨����(T')" + "\n");
			    // System.exit(0);
			    break;
			}
			recursiveF(newstr);
			if (!str.equals(newstr) || succcess) {
			    recursiveT1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("T'", "#", "first") && query("T'", str, "follow")) {// #�Ŵ����
		succcess = true;
		System.out.println("ʹ��:T'����");
		show.append("ʹ��:T'����" + "\n");
		return newstr;
	    } else {
		if (newstr == null) {
		    System.out.println("ʹ��:T'����");
		    show.append("ʹ��:T'����" + "\n");
		    return newstr;
		} else {
		    mark = true;
		    error.append(str + "�ڴ˳�����һ���﷨����(T')" + "\n");
		    System.out.println(str + "�ڴ˳�����һ���﷨����(T')");
		    show.append(str + "�ڴ˳�����һ���﷨����(T')" + "\n");
		    // System.exit(0);
		    return newstr;
		}
	    }
	}
	return newstr;

    }

    public String recursiveF(String str) {
	if (!mark) {
	    succcess = false;
	    String[] candidate = stogrammar.get("F");
	    if (query("F", str, "first")) {// �����ж�str�Ƿ���First��
		for (String can : candidate) {// forѭ�����������ַ������ĸ���ѡʽ
		    char ch = can.charAt(0);
		    if (str.equals(ch + "")) {// ��ΪF��ÿ����ѡʽ��FirstΪ�ս��,���Բ����ѯ
			System.out.println("ʹ��:" + "F��" + can);
			show.append("ʹ��:" + "F��" + can + "\n");
			advance();
			if (str.equals("(")) {
			    recursiveE(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("F", "#", "first") && query("F", str, "follow")) {// #�Ŵ����
		succcess = true;
		System.out.println("ʹ��:���Զ�ƥ��" + "F");
		show.append("ʹ��:���Զ�ƥ��" + "F" + "\n");
		return newstr;
	    } else {
		mark=true;
		error.append(str + "�ڴ˳�����һ���﷨����(F)" + "\n");
		System.out.println(str + "�ڴ˳�����һ���﷨����(F)");
		show.append(str + "�ڴ˳�����һ���﷨����(F)" + "\n");
		// System.exit(0);
		return newstr;
	    }

	}
	return newstr;

    }

    public String advance() {// �ȵõ��ַ����ٽ�loc+1
	newstr = wl.sto.word.get(loc);
	loc++;
	return newstr;

    }
}
