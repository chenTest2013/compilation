package chen.compile;

import java.util.ArrayList;
import java.util.HashMap;

public class Grammaranalyze {
    boolean mark;// 标记是否出错,用于快速退出递归
    StringBuilder show;
    StringBuilder error;
    Wordanalyze wl;
    Judge jd;
    FirstFollow ff;
    HashMap<String, String[]> stogrammar;// 存储文法 :key=非终结符,value=候选式数组
    HashMap<String, String> endstr;// 存储终结符
    HashMap<String, String> startstr;// 存储非终结符

    ArrayList<String[]> gra;// 存储文法 :字符串数组存储每一个产生式
    HashMap<Integer, String[]> gramer;// 存储文法 :字符串数组存储每一个产生式

    HashMap<String, HashMap<String, String>> first;// 存储first集
    HashMap<String, HashMap<String, String>> follow;// 存储follow集
    int loc = 0;// i*i+i的位置指示器
    String newstr;// 最新读进的字符
    boolean succcess = false;// 标记是否用空串标记

    public static void main(String[] args) {// 输入文法,箭头用逗号(,)表示,每个产生式用;分割(注意符号的中英文)
	Grammaranalyze g = new Grammaranalyze();
	long t1 = System.currentTimeMillis();
	String str = "E→TE';E'→+TE'|-TE'|ε;T→FT';T'→ *FT' | /FT'|ε;F→ (E) | i";
	//String input1 = "25.6 * 14.5 + 2";
	//String input2 = "2 / 5.2 + 78 - 6";
	//String input3 = "2 / 5.2 + 78 -";
	//String input4 = "+56 * 7 ";
	//String str5 = "34+ -2*8";
	g.analyze(str, "2/3*8+(8**9)");
	// g.print("gra");
	System.out.println("共耗时:" + (System.currentTimeMillis() - t1) + "ms");
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
	    str = "E→TE';E'→+TE'|-TE'|ε;T→FT';T'→ *FT' | /FT'|ε;F→ (E) | i";
	}
	stoGrammar(str);
	ff.in = gra;
	ff.process("First");
	ff.process("Follow");
	// System.out.println("终结符:" + endstr.toString());
	// System.out.println("非终结符:" + startstr.toString());
	process(input);
	return str;

    }

    public void stoGrammar(String str) {// 将输入的字符串转化为文法
	String[] temp = space(str).split(";");// 得到产生式数组
	// System.out.println("产生式数组\t" + temp.length);
	for (String proString : temp) {// 得到每一个产生式
	    // System.out.println("产生式:\t" + proString);
	    proDuction(proString);
	}
    }

    public void proDuction(String str) {// 处理每一个产生式,用于得到非终结符和终结符
	String[] production = str.split("→");// 将非终结符和候选式分开
	// production[0]=非终结符 production[1]=候选式的合写法
	if (!endstr.containsKey(production[0])) {
	    endstr.put(production[0], production[0]);// 加入终结符集合

	}
	String[] pro = production[1].split("\\|");// 将候选式分开,得到候选式数组
	stogrammar.put(production[0], pro);// 存储文法 :key=非终结符,value=候选式数组

	for (String string : pro) {
	    // System.out.println(production[0] + "的候选式\t" + string.trim());
	    String[] productions = new String[2];
	    productions[0] = production[0];
	    productions[1] = string.trim();
	    gramer.put(gramer.size(), productions);// 存储文法 :字符串数组存储每一个产生式
	    gra.add(productions);// 存储文法 :字符串数组存储每一个产生式这个用于求first和follow集
	    selected(string);// 处理每一个候选式,用于得到非终结符集合
	}

    }

    public void selected(String str) {// 处理每一个候选式,用于得到非终结符集合
	char[] chr = str.trim().toCharArray();
	for (char c : chr) {
	    if (!(jd.IsTopLetter(c) || c == '\'')
		    && !startstr.containsKey(c + "")) {
		// System.out.println((int)c+"\t加入非终结符集合");
		startstr.put(c + "", c + "");// 加入非终结符集合
	    }
	}

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

    public void print(String str) {// 打印first集合follow集
	boolean p = false;
	if (str.equals("")) {
	    p = true;
	}
	if (p || str.equals("ff")) {
	    System.out.println("\nFirst集：");
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
	    System.out.println("\nFollow集：");
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

    public void changff() {// 转化first集follow集的数据结构
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

    public boolean query(String str, String ch, String type) {// 查询某个非终结符的type集中是否有某个终结符

	changff();// 转化first集follow集的数据结构
	HashMap<String, String> temp = new HashMap<String, String>();
	if ("first".equals(type.trim())) {
	    temp = first.get(str);
	} else if ("follow".equals(type.trim())) {
	    temp = follow.get(str);
	}
	return temp.containsKey(ch);

    }

    public void process(String str) {// 语法分析过程
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
	    String[] candidate = stogrammar.get("E");// 候选式数组
	    if (query("E", str, "first")) {// 首先判断str是否在First中
		for (String can : candidate) {// 假定E有很多候选式,用for循环判断属于哪个候选式
		    char ch = can.charAt(0);// 去每一个候选式的首字符
		    if (query(ch + "", str, "first")) {

			System.out.println("使用:" + "E→" + can);
			show.append("使用:" + "E→" + can + "\n");
			
			recursiveT(newstr);
			if (!str.equals(newstr) || succcess) {// 判断是否匹配成功,只用成功newstr才会改变
			    recursiveE1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("E", "#", "first") && query("E", str, "follow")) {// #号代表ε
		succcess = true;
		System.out.println("使用:ε自动匹配" + "E");
		show.append("使用:ε自动匹配" + "E\n");
		return newstr;
	    } else {
		mark = true;
		error.append(str + "在此出现是一种语法错误(E)");
		System.out.println(str + "在此出现是一种语法错误(E)");
		show.append(str + "在此出现是一种语法错误(E)" + "\n");
		return newstr;
	    }
	}
	return newstr;

    }

    public String recursiveE1(String str) {
	if (!mark) {
	    succcess = false;
	    String[] candidate = stogrammar.get("E'");
	    if (query("E'", str, "first")) {// 首先判断str是否在First中
		for (String can : candidate) {// for循环查找输入字符属于哪个候选式
		    char ch = can.charAt(0);
		    if (str.equals(ch + "")) {
			System.out.println("使用:" + "E'→" + can);
			show.append("使用:" + "E'→" + can + "\n");
			advance();
			if (str.equals(wl.sto.word.get(wl.sto.word.size() - 1))
				|| str.equals(wl.sto.word.get(0))) {
			    mark = true;
			    error.append(str + "在此出现是一种语法错误(E')" + "\n");
			    System.out.println(str + "在此出现是一种语法错误(E')");
			    show.append(str + "在此出现是一种语法错误(E')" + "\n");
			    // System.exit(0);
			    break;
			}
			recursiveT(newstr);
			if (!str.equals(newstr) || succcess) {// 判断是否匹配成功,只用成功newstr才会改变
			    recursiveE1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("E'", "#", "first") && query("E'", str, "follow")) {
		succcess = true;
		System.out.println("使用:E'→ε");
		show.append("使用:E'→ε" + "\n");
		return newstr;
	    } else {
		if (newstr == null) {
		    System.out.println("使用:E'→ε");
		    show.append("使用:E'→ε" + "\n");
		    return newstr;
		} else {
		    mark = true;
		    error.append(str + "在此出现是一种语法错误(E')" + "\n");
		    System.out.println(str + "在此出现是一种语法错误(E')");
		    show.append(str + "在此出现是一种语法错误(E')" + "\n");
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
	    if (query("T", str, "first")) {// 首先判断str是否在First中
		for (String can : candidate) {// for循环查找输入字符属于哪个候选式
		    char ch = can.charAt(0);
		    if (query(ch + "", str, "first")) {
			System.out.println("使用:" + "T→" + can);
			show.append("使用:" + "T→" + can + "\n");
			recursiveF(newstr);
			if (!str.equals(newstr) || succcess) {// 判断是否匹配成功,只用成功newstr才会改变
			    recursiveT1(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("T", "#", "first") && query("T", str, "follow")) {// #号代表ε
		succcess = true;
		System.out.println("使用:ε自动匹配" + "T");
		show.append("使用:ε自动匹配" + "T" + "\n");
		return newstr;
	    } else {
		mark = true;
		error.append(str + "在此出现是一种语法错误(T)" + "\n");
		System.out.println(str + "在此出现是一种语法错误(T)");
		show.append(str + "在此出现是一种语法错误(T)" + "\n");
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
	    if (query("T'", str, "first")) {// 首先判断str是否在First中
		for (String can : candidate) {// for循环查找输入字符属于哪个候选式
		    char ch = can.charAt(0);
		    if (str.equals(ch + "")) {
			System.out.println("使用:" + "T'→" + can);
			show.append("使用:" + "T'→" + can + "\n");
			advance();
			if (str.equals(wl.sto.word.get(wl.sto.word.size() - 1))
				|| str.equals(wl.sto.word.get(0))) {
			    mark = true;
			    error.append(str + "在此出现是一种语法错误(T')" + "\n");
			    System.out.println(str + "在此出现是一种语法错误(T')");
			    show.append(str + "在此出现是一种语法错误(T')" + "\n");
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
	    } else if (query("T'", "#", "first") && query("T'", str, "follow")) {// #号代表ε
		succcess = true;
		System.out.println("使用:T'→ε");
		show.append("使用:T'→ε" + "\n");
		return newstr;
	    } else {
		if (newstr == null) {
		    System.out.println("使用:T'→ε");
		    show.append("使用:T'→ε" + "\n");
		    return newstr;
		} else {
		    mark = true;
		    error.append(str + "在此出现是一种语法错误(T')" + "\n");
		    System.out.println(str + "在此出现是一种语法错误(T')");
		    show.append(str + "在此出现是一种语法错误(T')" + "\n");
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
	    if (query("F", str, "first")) {// 首先判断str是否在First中
		for (String can : candidate) {// for循环查找输入字符属于哪个候选式
		    char ch = can.charAt(0);
		    if (str.equals(ch + "")) {// 因为F的每个候选式的First为终结符,所以不需查询
			System.out.println("使用:" + "F→" + can);
			show.append("使用:" + "F→" + can + "\n");
			advance();
			if (str.equals("(")) {
			    recursiveE(newstr);
			}
		    }
		}
		return newstr;
	    } else if (query("F", "#", "first") && query("F", str, "follow")) {// #号代表ε
		succcess = true;
		System.out.println("使用:ε自动匹配" + "F");
		show.append("使用:ε自动匹配" + "F" + "\n");
		return newstr;
	    } else {
		mark=true;
		error.append(str + "在此出现是一种语法错误(F)" + "\n");
		System.out.println(str + "在此出现是一种语法错误(F)");
		show.append(str + "在此出现是一种语法错误(F)" + "\n");
		// System.exit(0);
		return newstr;
	    }

	}
	return newstr;

    }

    public String advance() {// 先得到字符串再将loc+1
	newstr = wl.sto.word.get(loc);
	loc++;
	return newstr;

    }
}
