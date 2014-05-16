package chen.compile;

import java.util.ArrayList;
import java.util.HashMap;

public class Stored {// 存储数据类

    HashMap<String, String> insertarea, insertoperator;// 界符,运算符存放地
    HashMap<Integer, String> insertid, insertconst;// 标示符,常数存放地
    HashMap<Integer, Integer> insertbasic;// 关键字编码存放地
    int numnext;// 常数指针
    int basic;// 记录保留字编码
    HashMap<Integer, String> stoemap;//按字符顺序存放总的字符串
    StringBuilder sbuder;//按字符顺序存放总的字符串,用字符缓冲类存储
    String[] basicstr;//存放需要重复使用的字符串类别
    HashMap<Integer, String> word;//存放经词法分析后得到的单词符号
    ArrayList<String> list;//按顺序存放单词符号
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
        list=new ArrayList<>();//用于计算数值
        basicstr=new String[]{"关键字","标识符","常数","界符","运算符","转义字符"};
        word=new HashMap<Integer, String>();//存放经过转化后的单词符号
        insertid = new HashMap<Integer, String>();
        insertconst = new HashMap<Integer, String>();
        insertbasic = new HashMap<Integer, Integer>();
        stoemap=new HashMap<Integer, String>();
        sbuder=new StringBuilder();
    }

    public String InsertId(StringBuffer s) {// 存储标识符
        String str = s.toString().trim();
        insertid.put(insertid.size(), str);
        return str;

    }

    public int insertConst(StringBuffer s) {// 存储常数
        String str = s.toString().trim();
        getInsertconst().put(numnext++, str);
        return numnext;

    }

    public int insertBasic(int id) {// 存储关键字编码
        insertbasic.put(id, id);
        return basic;

    }

    public int insertArea() {// 存储界符
        return basic;

    }

    public int insertoperator() {// 存储运算符
        return basic;

    }
}
