package chen.compile;

import java.util.HashMap;

public class Original {
	String[] foundation = { "abstract", "assert", "boolean", "break", "byte",
			"case", "catch", "char", "class", "const", "continue", "default",
			"do", "double", "else", "enum", "extends", "final", "finally",
			"float", "for", "goto", "if", "implements", "import", "instanceof",
			"int", "interface", "long", "native", "new", "package", "private",
			"protected", "public", "return", "strictfp", "short", "static",
			"super", "switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while" };
	String[] delimiter = { "{", "}", "[", "]", "(", ")", ":", ".", ",", ";" ,"\"","'"};
	String[] operator = { "+", "-", "*", "/", "%", "++", "--", "=", "==", "<=",
			"<", ">", ">=", "!=", "!", "&", "|", "&&", "||", "&" };
	HashMap<Integer, String> foundation1;
	HashMap<Integer, String> delimiter1;
	HashMap<Integer, String> operator1;

	public Original() {//源语言的原始数据类
		super();
		foundation1 = new HashMap<Integer, String>();
		delimiter1 = new HashMap<Integer, String>();
		operator1 = new HashMap<Integer, String>();
		for (int i = 0; i < foundation.length; i++) {
			foundation1.put(i + 10, foundation[i]);
		}
		for (int i = 0; i < delimiter.length; i++) {
			delimiter1.put(i + 200, delimiter[i]);
		}
		for (int i = 0; i < operator.length; i++) {
			operator1.put(i + 500, operator[i]);
		}
	}
	

}
