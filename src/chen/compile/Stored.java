package chen.compile;

import java.util.ArrayList;
import java.util.HashMap;

public class Stored {// �洢������

    HashMap<String, String> insertarea, insertoperator;// ���,�������ŵ�
    HashMap<Integer, String> insertid, insertconst;// ��ʾ��,������ŵ�
    HashMap<Integer, Integer> insertbasic;// �ؼ��ֱ����ŵ�
    int numnext;// ����ָ��
    int basic;// ��¼�����ֱ���
    HashMap<Integer, String> stoemap;//���ַ�˳�����ܵ��ַ���
    StringBuilder sbuder;//���ַ�˳�����ܵ��ַ���,���ַ�������洢
    String[] basicstr;//�����Ҫ�ظ�ʹ�õ��ַ������
    HashMap<Integer, String> word;//��ž��ʷ�������õ��ĵ��ʷ���
    ArrayList<String> list;//��˳���ŵ��ʷ���
    public HashMap<Integer, String> getInsertconst() {
        return insertconst;
    }

    public void setInsertconst(HashMap<Integer, String> insertconst) {
        this.insertconst = insertconst;
    }

    public HashMap<Integer, String> getInsertid() {
        return insertid;
    }

    public void setInsertid(HashMap<Integer, String> insertid) {
        this.insertid = insertid;
    }

    public StringBuilder getSbuder() {
        return sbuder;
    }

    public void setSbuder(StringBuilder sbuder) {
        this.sbuder = sbuder;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public Stored() {
        super();
        list=new ArrayList<>();//���ڼ�����ֵ
        basicstr=new String[]{"�ؼ���","��ʶ��","����","���","�����","ת���ַ�"};
        word=new HashMap<Integer, String>();//��ž���ת����ĵ��ʷ���
        insertid = new HashMap<Integer, String>();
        insertconst = new HashMap<Integer, String>();
        insertbasic = new HashMap<Integer, Integer>();
        stoemap=new HashMap<Integer, String>();
        sbuder=new StringBuilder();
    }

    public String InsertId(StringBuffer s) {// �洢��ʶ��
        String str = s.toString().trim();
        insertid.put(insertid.size(), str);
        return str;

    }

    public int insertConst(StringBuffer s) {// �洢����
        String str = s.toString().trim();
        getInsertconst().put(numnext++, str);
        return numnext;

    }

    public int insertBasic(int id) {// �洢�ؼ��ֱ���
        insertbasic.put(id, id);
        return basic;

    }

    public int insertArea() {// �洢���
        return basic;

    }

    public int insertoperator() {// �洢�����
        return basic;

    }
}
