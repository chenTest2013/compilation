package chen.compile;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Judge {// �жϷ�����
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

	public boolean IsLetter(char c) {// �ж��Ƿ�Ϊ��ĸ��_(�»���)
		if (IsTopLetter(c) || IsLowLetter(c) || c == '_') {
			return true;
		}
		return false;

	}

	public boolean IsTopLetter(char c) {// �ж��Ƿ�Ϊ��д��ĸ
		if ('A' <= c && c <= 'Z') {
			return true;
		}
		return false;

	}

	public boolean IsLowLetter(char c) {// �ж��Ƿ�ΪСд��ĸ
		if ('a' <= c && c <= 'z') {
			return true;
		}
		return false;

	}

	public boolean isEscape(char c) {// �ж��Ƿ�Ϊת���ַ�
		if (c == '\\') {
			return true;
		}
		return false;

	}

	public boolean isChinese(char c) {// �ж��Ƿ�Ϊ�����ַ���
		if (c > 128) {
			return true;
		}
		return false;

	}

	public boolean IsDidit(char c) {
		if ('0' <= c && c <= '9') {// ע�ⵥ���Ų���©��
			return true;
		}
		return false;

	}

	public int ReserveBasic(String s) {// �ж��Ƿ�Ϊ�ؼ���,�Ƿ��ر���
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

	public int ReserveArea(char c) {// �����������
		String temp = c + "";
		int id = ReserveArea(temp);
		if (id != 0) {
			return id;
		}
		return 0;

	}

	public int ReserveArea(String s) {// �ж��Ƿ�Ϊ���,�Ƿ��ر���
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

	public int Reserveoperator(char c) {// �����������
		String temp = c + "";
		int id = Reserveoperator(temp);
		if (id != 0) {
			return id;
		}
		return 0;

	}

	public int Reserveoperator(String s) {// �ж��Ƿ�Ϊ���,�Ƿ��ر���
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
