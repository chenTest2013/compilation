package chen.compile;

public class Wordanalyze {
    char[] allchar;// 将输入后的字符串装换为字符数组
    char ch;// 存放最新的从allchar中读取的字符
    int next = 0;// 存放字符数组的指针(位置):搜索指示器
    StringBuffer strToken;// 存放构成单词符号的字符串
    Original og;
    Stored sto;
    Judge jd;

    public Stored getSto() {
	return sto;
    }

    public void setSto(Stored sto) {
	this.sto = sto;
    }

    public Wordanalyze() {
	// System.out.println("构造方法执行");
	strToken = new StringBuffer();
	og = new Original();
	sto = new Stored();
	jd = new Judge();
    }

    public static void main(String[] args) {
	//Function tools = new Function();
	Wordanalyze t = new Wordanalyze();

	//String str = "20117790205 hello world "
	//	+ "wo ai tian an men  hello world " + "i love 20117790205 ";
	//String str1 = "public static void main "
	//	+ "(String [] args) { double sum5 = 0.0;for "
	//	+ "( int i=1;i<5;i++) "
	//	+ "{ sum5=sum5+(i+10);sum5=sum5+(i*2);}} ";
	// String temp = "buf.append(wa.sto.storeMap.get(i)).append("\n");";

	t.analyze("2/3*8+(8**9)");
	// System.out.println(tools.wordReplace(str) + "\n# of chars = "
	// + tools.wordsCount(str));
    }

    public void analyze(String str) {
	allchar = changeStr(str.trim() + " ");
	while (next < allchar.length) {
	    // System.out.println("next="+next+"\tallchar.length="+allchar.length+"\t"+(int)allchar[next]);
	    GetBC();
	    if (jd.IsLetter(ch)) {
		// System.out.println("第一个字符时:" + ch + "\tnext=" + next);
		Concat(ch);
		GetChar(next);
		while (jd.IsLetter(ch) || jd.IsDidit(ch)) {
		    // System.out.println("ch是\t" + ch + "\tnext=" + next);
		    Concat(ch);
		    if (next >= allchar.length) {
			break;
		    }
		    GetChar(next);
		}
		if (next < allchar.length) {
		    Retract();
		}

		if (jd.ReserveBasic(strToken.toString().trim()) != 0) {
		    sto.insertBasic(sto.basic);
		    System.out.println(sto.basicstr[0] + "\t" + strToken);
		    sto.stoemap.put(sto.stoemap.size(), sto.basicstr[0] + "\t"
			    + strToken);
		    sto.sbuder.append(sto.basicstr[0] + "\t" + strToken + "\n");

		} else {
		    sto.InsertId(strToken);
		    System.out.println(sto.basicstr[1] + "\t" + strToken);
		    sto.stoemap.put(sto.stoemap.size(), sto.basicstr[1] + "\t"
			    + strToken);
		    sto.sbuder.append(sto.basicstr[1] + "\t" + strToken + "\n");
		}
		strToken.delete(0, strToken.length());

	    } else if (jd.IsDidit(ch)) {
		Concat(ch);
		GetChar(next);
		while (jd.IsDidit(ch) || ch == '.') {
		    Concat(ch);
		    if (next >= allchar.length) {
			break;
		    }
		    GetChar(next);
		}
		if (next < allchar.length) {
		    Retract();
		}
		System.out.println(sto.basicstr[2] + "\t" + strToken);
		sto.stoemap.put(sto.stoemap.size(), sto.basicstr[2] + "\t"
			+ strToken);
		sto.sbuder.append(sto.basicstr[2] + "\t" + strToken + "\n");
		sto.word.put(sto.word.size(), "i");// 语法分析是要用
		sto.list.add(str);
		strToken.delete(0, strToken.length());

	    } else if (jd.ReserveArea(ch) != 0) {

		Concat(ch);
		System.out.println(sto.basicstr[3] + "\t" + strToken);// /////
		sto.stoemap.put(sto.stoemap.size(), sto.basicstr[3] + "\t"
			+ strToken);
		sto.sbuder.append(sto.basicstr[3] + "\t" + strToken + "\n");
		sto.word.put(sto.word.size(), strToken.toString());// 语法分析是要用
		strToken.delete(0, strToken.length());// //////
		GetChar(next);
		while (jd.ReserveArea(ch) != 0) {
		    Concat(ch);
		    System.out.println(sto.basicstr[3] + "\t" + strToken);// /////
		    sto.stoemap.put(sto.stoemap.size(), sto.basicstr[3] + "\t"
			    + strToken);
		    sto.sbuder.append(sto.basicstr[3] + "\t" + strToken + "\n");
		    sto.word.put(sto.word.size(), strToken.toString());// 语法分析是要用
		    strToken.delete(0, strToken.length());
		    if (next >= allchar.length) {
			break;
		    }
		    GetChar(next);
		}
		if (next < allchar.length) {
		    Retract();
		}

	    } else if (jd.Reserveoperator(ch) != 0) {
		Concat(ch);
		GetChar(next);
		while (jd.Reserveoperator(ch) != 0) {
		    Concat(ch);
		    if (next >= allchar.length) {
			break;
		    }
		    GetChar(next);
		}
		if (next < allchar.length) {
		    Retract();
		}

		char[] ch = strToken.toString().toCharArray();
		if (strToken.length() == 2
			&& jd.Reserveoperator(strToken.toString()) != 0) {
		    System.out.println("<<2字符运算符出现>>");
		    System.out.println(sto.basicstr[4] + "\t" + strToken);// /////
		    sto.stoemap.put(sto.stoemap.size(), sto.basicstr[4] + "\t"
			    + strToken);
		    sto.sbuder.append(sto.basicstr[4] + "\t" + strToken + "\n");
		    sto.word.put(sto.word.size(), strToken.toString());// 语法分析是要用
		    // break;
		} else {
		    for (char c : ch) {
			System.out.println(sto.basicstr[4] + "\t" + c);// /////
			sto.stoemap.put(sto.stoemap.size(), sto.basicstr[4]
				+ "\t" + c);
			sto.sbuder.append(sto.basicstr[4] + "\t" + c + "\n");
			sto.word.put(sto.word.size(), c + "");// 语法分析是要用

		    }
		}
		strToken.delete(0, strToken.length());
	    } else if (jd.isEscape(ch)) {
		Concat(ch);
		GetChar(next);
		while (ch == 'n' || ch == 't' || ch == '\\') {
		    Concat(ch);
		    if (next >= allchar.length) {
			break;
		    }
		    GetChar(next);
		}
		if (next < allchar.length) {
		    Retract();
		}
		System.out.println(sto.basicstr[5] + "\t" + strToken);// /////
		sto.stoemap.put(sto.stoemap.size(), sto.basicstr[5] + "\t"
			+ strToken);
		sto.sbuder.append(sto.basicstr[5] + "\t" + strToken + "\n");

		strToken.delete(0, strToken.length());
	    }
	}
    }

    public char[] changeStr(String str) {// 将输入字符串转换为字符数组
	allchar = str.toCharArray();
	return allchar;
    }

    public char GetChar(int i) {// 取出字符数组中的元素
	next = i + 1;// 搜索指示器前移一个位置
	if (i >= 0 && i < allchar.length) {
	    ch = allchar[i];
	    return ch;
	} else {
	    return ch = ' ';
	}
    }

    public boolean GetBC() {// 判断ch为非空,制表符,换行符,直至不是这些为止
	while ((ch == ' ' || ch == '\n' || ch == '\t' || ch == 0 || ch == '\r')
		&& next < allchar.length || ch > 128) {// ch>128过滤掉中文字符
	    // System.out.println("字符是\t" + ch + "\t所以GetChar(next)");
	    GetChar(next);
	    return GetBC();
	}
	return false;

    }

    public StringBuffer Concat(char c) {// 将ch链接到字符串strToken
	strToken = strToken.append(c);
	return strToken;

    }

    public void Retract() {
	next = next - 1;// 回调一个字符的位置
	ch = ' ';// 将ch置换为空
    }
}
