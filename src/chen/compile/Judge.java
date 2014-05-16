package chen.compile;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Judge {// 判断方法类
	Stored sto;
	Original og;

	public Judge() {
		sto = new Stored();
		og = new Original();
	}

	public boolean isEmpty(char c) {
		if (c == ' ') {
			return true;
		}
		return false;

	}

	public boolean IsLetter(char c) {// 判断是否为字母或_(下划线)
		if (IsTopLetter(c) || IsLowLetter(c) || c == '_') {
			return true;
		}
		return false;

	}

	public boolean IsTopLetter(char c) {// 判断是否为大写字母
		if ('A' <= c && c <= 'Z') {
			return true;
		}
		return false;

	}

	public boolean IsLowLetter(char c) {// 判断是否为小写字母
		if ('a' <= c && c <= 'z') {
			return true;
		}
		return false;

	}

	public boolean isEscape(char c) {// 判断是否为转义字符
		if (c == '\\') {
			return true;
		}
		return false;

	}

	public boolean isChinese(char c) {// 判断是否为中文字符串
		if (c > 128) {
			return true;
		}
		return false;

	}

	public boolean IsDidit(char c) {
		if ('0' <= c && c <= '9') {// 注意单引号不能漏掉
			return true;
		}
		return false;

	}

	public int ReserveBasic(String s) {// 判断是否为关键字,是返回编码
		Set<Map.Entry<Integer, String>> set = og.foundation1.entrySet();
		Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			if (entry.getValue().equals(s)) {
				return sto.basic = entry.getKey();
			}
		}
		return 0;

	}

	public int ReserveArea(char c) {// 界符方法重载
		String temp = c + "";
		int id = ReserveArea(temp);
		if (id != 0) {
			return id;
		}
		return 0;

	}

	public int ReserveArea(String s) {// 判断是否为界符,是返回编码
		Set<Map.Entry<Integer, String>> set = og.delimiter1.entrySet();
		Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			if (entry.getValue().equals(s)) {
				return entry.getKey();
			}
		}
		return 0;

	}

	public int Reserveoperator(char c) {// 算符方法重载
		String temp = c + "";
		int id = Reserveoperator(temp);
		if (id != 0) {
			return id;
		}
		return 0;

	}

	public int Reserveoperator(String s) {// 判断是否为算符,是返回编码
		Set<Map.Entry<Integer, String>> set = og.operator1.entrySet();
		Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			if (entry.getValue().equals(s)) {
				return entry.getKey();
			}
		}
		return 0;

	}

}
