package Test;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 1��һ��ֻ������һ�����ӣ��Ҳ������ӵ��ڶ��У���Ȼ���ԼӶ�������
 * 2���Ƶ����ţ�ֻ���ǡ���->��һ�֣�ÿ������б������Ƶ���
 * 3�����ĳ���ս�����Ǵδ�д��ĸ���ĳ���ַ�����ô�ں���ֻ���á�����'����ʾ
 * 4�������end����
 *ʵ�ֹ��ܣ�
 * 1����׼�ķ�����
 * 2����First����First����㷨
 * 3����Follow����Follow������㷨
 * 4��������������
 * 5�����Կո��Ӱ��
 * 6�����������Ƶ������������Ӣ������һƲ���ͻ����ӷ�
 * 7������������жϹ���
 *ĳһ����ʵ����
 * E->TE'
 * E'->+E|��
 * T->FT'
 * T'->T|��
 * F->PF'
 * F'->*F'|��
 * P->(E)|a|b|^
 * end
 */

public class FirstFollow3 {
	public ArrayList<String[]> in=new ArrayList<String[]>();//�����ݽṹ���Ǳ��˾�·��ȥ�뵽�����������������ؼ�ʵ���˿ɱ䳤���ķ����գ������ŵ��ǲ�ֺ���򵥵��ķ���Ҳ�����û�����
	public ArrayList<String[]> first = new ArrayList<String[]>();//�������Ƶ�������First��
	public ArrayList<String[]> follow = new ArrayList<String[]>();
	public ArrayList<String[]> track = new ArrayList<String[]>();//track��һ��һ���ķ��ս������ɵ�·������
	public FirstFollow3(){
		Scanner sc = new Scanner(System.in);
		System.out.println("���������һ�������ķ�:(end����)");
		String sline="";
		sline=sc.nextLine();
		while(!sline.startsWith("end")){
			StringBuffer buffer=new StringBuffer(sline);
			int l=buffer.indexOf(" ");
			while(l>=0){//ȥ�ո�
				buffer.delete(l,l+1);
				l=buffer.indexOf(" ");
			}
			sline=buffer.toString();
			String s[]=sline.split("->");//���Ƶ���
			if(s.length==1)
				s=sline.split("��");//���ǵ�����ϰ�ߺ���ʽ����
			if(s.length==1)
				s=sline.split("=>");
			if(s.length==1){
				System.out.println("�ķ�����");
				System.exit(0);
			}
			StringTokenizer fx = new StringTokenizer(s[1],"|��");//��Ӣ�ĸ����𿪲���ʽ�����ĸ�����
			while(fx.hasMoreTokens()){
				String[] one = new String[2];//����һ�����ֻ�豣���������ݾͿ����ˣ�����󲿺�����Ҳ���һ���򵥵���ʽ�������л�����Ͱ��������
				one[0]=s[0];//ͷ����,0λ�÷ŷ��ս��
				one[1]=fx.nextToken();//1λ�÷ŵ����Ĳ���ʽ�����ǲ���ʽ�Ҳ���һ����򵥵���ʽ
				in.add(one);
			}
			sline=sc.nextLine();
		}
		//��First������
		this.process("First");
		/*
		 * ��ӡFirst���㷨��First��
		 */
		System.out.println("\nFirst���㷨��");
		this.print(track);//��ӡFirst���㷨
		System.out.println("\nFirst����");
		for(int i=0;i<first.size();i++){
			String[] r=first.get(i);
			System.out.print("First("+r[0]+")={");
			for(int j=1;j<r.length;j++){
				System.out.print(r[j]);
				if(j<r.length-1)
					System.out.print(",");
			}
			System.out.println("}");
		}
		track.clear();//��Ϊ���滹Ҫ�ã�������������
		//��Follow������
		this.process("Follow");
		System.out.println("\nFollow���㷨��");
		for(int i=0;i<track.size();i++){
			String[] one = track.get(i);
			System.out.print("Follow("+follow.get(i)[0]+"):\t");
			for(int j=0;j<one.length;j++)
				System.out.print(one[j]+"\t");
			System.out.println();
		}
		
		System.out.println("\nFollow����");
		for(int i=0;i<follow.size();i++){
			String[] r=follow.get(i);
			System.out.print("Follow("+r[0]+")={");
			for(int j=1;j<r.length;j++){
				System.out.print(r[j]);
				if(j<r.length-1)
					System.out.print(",");
			}
			System.out.println("}");
		}
	}
	public void process(String firstORfollow){
		for(int i=0;i<in.size();i++){
			boolean bool=true;
			for(int j=0;j<i;j++)
				if(in.get(j)[0].equals(in.get(i)[0]))
					bool=false;
			if(bool){
				ArrayList<String> a=null;
				if(firstORfollow.equals("First"))
				    a=this.getFirst(in.get(i)[0],"First("+in.get(i)[0]+")/");
				else if(firstORfollow.equals("Follow"))
					a=this.getFollow(in.get(i)[0],in.get(i)[0],"");
				String[] sf=new String[a.size()/2+1];
				String[] st=new String[a.size()/2];
				sf[0]=in.get(i)[0];
				for(int j=0;j<a.size();j++)
				{
					if(j%2==0)
					    sf[j/2+1]=a.get(j);
					else
						st[j/2]=a.get(j);
				}
				if(firstORfollow.equals("First"))
				    first.add(sf);//first��
				else if(firstORfollow.equals("Follow"))
					follow.add(sf);
				track.add(st);//��Ӧ������ü���·�����ڿ�ʼ����÷��ս���ˣ���Ϊ�ѱ����˸��ַ���First��Follow��ʾ��
			}
		}
	}
	public ArrayList<String> getFirst(String s,String track1){//s��ʾ���Ƶ���track��ʾѰ��·��������ѭ������
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		if(Character.isUpperCase(s.charAt(0))){//����Ƿ��ս������д
			for(int i=0;i<in.size();i++){
				String[] one = in.get(i);
				if(s.equals(one[0])){
					if(track1.substring(0,track1.length()-9).indexOf("First("+s+")")>=0)//�����ڲ��ҹ���Ƕ�����ⲽ��֤������������ѭ�����������ң���·���޽��
						;//�е�Ҫע��һ�£�����һ��ʼ�Ͱѵ�һ����ʼ�Ƶ�����First·���Ž�ȥ�˵ģ�����Ҫ�ܿ���һ�Σ���Ȼ�ѿ�ʼ�ͽ�����
					else if(one[1].length()==1||one[1].charAt(1)!='\''&&one[1].charAt(1)!='��')
						result1=getFirst(one[1].charAt(0)+"",track1+"First("+one[1].charAt(0)+")/");
					else if(one[1].length()>1&&one[1].charAt(1)=='\''||one[1].charAt(1)=='��')//���������һ��Ҫ��First�ķ��ս������һƲ����һƲ����Ӣ�ı�ʾ�����ı�ʾ
						result1=this.getFirst(one[1].substring(0,2),track1+"First("+one[1].substring(0,2)+")/");
					result=addArrayString(result,result1);
					result1.clear();
				}
			}
		}
		else{//�������ʽ���ַ����ս��ַ�
			if(s.equals("��"))//ע�⣺��ʾ�յ��ַ�ֻ���������ˣ�������ʽ������������в���ͨ��������ԭ��
				result1.add("#");
			else
				result1.add(s);
			result1.add(track1);//Ϊ�˷��㣬��·��Ҳ�����˽��������Ȼ����·����ƥ�䣬û�취����Ϊ�м���ɾȥ�ظ���
			result=result1;
		}
		return result;
	}
	public ArrayList<String> getFollow(String s,String element,String track1){//���������ƣ�������Follow�ĵȼ�Follow����Ϊ�Ƶ�����ķ�����Χ��
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		if(Character.isUpperCase(s.charAt(0))){	
		    for(int i=0;i<in.size();i++){
				String[] one = in.get(i);
				int slen=s.length();
				int olen=one[1].length();
				if(element.equals(in.get(0)[0])){//����ǿ�ʼ���ţ����ǿ��Է��Ƶ���ʼ���ţ�֤��Ҳ����˳�Ƶ���ʼ����
					result1.add("#");
					result1.add(in.get(0)[0]+"��"+in.get(0)[0]+"\t");
					result = addArrayString(result,result1);
					result1.clear();
				}
				if(one[1].indexOf(s)>=0&&track1.indexOf((char)('a'+i)+"")>=0)//����֮ǰ�߹�ĳһ�����Ͳ��������ˣ�������ѭ����֮ǰ�������ǰ����˸�else,����ֲ���������ʾ�����������㷢���ˣ����㷴�Ƶ���ʼ���ţ�Ҳ��һ���͵�����˵ģ���ʼ����Ҳ���Է��ƣ�����Ҫ����
					;
				else if(one[1].indexOf(s)>=0&&(olen-slen==one[1].indexOf(s)||slen==2||one[1].charAt(one[1].indexOf(s)+1)!='��'&&one[1].charAt(one[1].indexOf(s)+1)!='\''))
				{//������Ҳ���ʽ������������Ҫ���Ƶ��ַ�,������������������������ڣ���Ϊ�����������ַ�Ҳ��һ�����棬����E���а���E�����ⲻ�������İ���
					int index=-1;
					index = one[1].indexOf(s,0);
					while(index>=0){//֮ǰ��û���õ�ѭ������������ٵ㶫������ϸһ�룬����Ҫ��������һ���Ƶ���䣬Ҳ�����Ƴ������ͬ���ս������ϣ���ʵ��Ҳ��һ����������ˣ�������Ҳ������ȷ�ˣ�Ҳ����֮ǰ�������ط��������Ľ��������ˣ�����Ҳû�£����������Ҫ��T��Follow����������Բ�����T+a*T*b����ʱ�������õģ���һ��
						if(olen-slen==index){//����÷��ս����ĩβ����ô�󵼳��ò���ʽ�ķ��ս���ĵ���
							result1=getFollow(one[0], element,track1+(char)('a'+i));
							result=addArrayString(result,result1);
							result1.clear();
						}else{//�����̷��ս���ڲ���ʽ�в������
							int t=index+slen;//ָ���ڲ���ʽ���ս��s�ĺ�һ���ַ�λ��
							result1=returnFirstofFollow(s, element, track1, one[0], one[1], index, t);
							result=addArrayString(result,result1);//֮ǰҲûд��仰�������֮ǰ�����ݸ����ˣ�����֮ǰ�����ݶ�ʧ
							result1.clear();
						}
						index = one[1].indexOf(s,index+1);
					}//endwhile
				}
				if(one[1].endsWith(element)){//����ʼҪ���Follow�����ս����ĩβ
					result1.add("#");
					result1.add(in.get(0)[0]+"��"+one[1]+"\t");
					result=addArrayString(result,result1);//֮ǰҲûд��仰�������֮ǰ�����ݸ����ˣ�����֮ǰ�����ݶ�ʧ
					result1.clear();
				}
		    }//endfor
		}
		return result;
	}
	public ArrayList<String> returnFirstofFollow(String s,String element,String track1,String one0,String one1,int index,int t){//������Follow����Ҫ���First������
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		ArrayList<String > beckFirst;
		String lsh;//��¼��һ���ַ�
		if(t+1<one1.length()&&(one1.charAt(t+1)=='��'||one1.charAt(t+1)=='\''))//������ķ��ս��������һƲ��
			lsh=one1.substring(t,t+2);
		else//���û��һƲ,��ֻҪ��ȡһ����ĸ�Ϳ�����
			lsh=one1.substring(t,t+1);
		String[] ls = null;
		int beflen=2;
		if(track1.length()>0){//��Щ����Ϊ���㷨������������õģ���ʵҪ��������㷨��Ҫʡ�ºöණ��
			ls=in.get((int)(track1.charAt(track1.length()-1)-'a'));//�õ���һ�����õ����
			if(Character.isUpperCase(ls[1].charAt(ls[1].length()-1)))
				beflen=1;
		}
		beckFirst=this.getFirst(lsh,"First("+lsh+")/");//�൱�ڵõ�����ַ���First��
		for(int j=0;j<beckFirst.size()/2;j++){//������First�������صĲ�һ��ֻһ�����
			String lh="";
			if(beckFirst.get(j*2).equals("#")){
				result1.add(beckFirst.get(j*2));//������������ݣ�����һ�����ǰѵ�ַ���ϣ�����һ�������Ҫ��������
				if(ls==null)
					lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+element+"��"+one1.substring(t+lsh.length(),one1.length());
				else
					lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+ls[1]+one1.substring(index+s.length(),one1.length())+"��."+element+"��"+one1.substring(t+lsh.length(),one1.length());
				result1.add(lh);
				result=addArrayString(result,result1);
				result1.clear();
				if(1+index+lsh.length()<one1.length())//֤�����滹���ַ�,ΪʲôҪ��һ��������ȷ��ѣ�����Ҫ��F��Follow���������ڲ���ʽFPQ,��P���и�First��Ϊ�գ���ô�û�������Q��First�����ƣ��������һ���ַ�Q���Ƿ��ؿգ���ô��Ҫ�����ʽ��ߵ��Ƶ����ս����Follow���ˣ��������Щ������㵽F��Follow����ȥ
					result1=returnFirstofFollow(s, element, track1, one0,one1, index, t+lsh.length());
				else//�����,��ô��Ҫ�����ʽ��ߵ��Ƶ����ս����Follow���ˣ���ʵ�������һ��������������ˣ�һ���ò�����
					result1=getFollow(one0, element, track1);
			}
			else{//��ʵ������һ���綼��Ϊ���׶�һ�㣬Follow���㷨����һ�㣬�ÿడ
				if(Character.isUpperCase(one1.charAt(t))){//�����������һ�����ս����First������Ľ��
					if(ls==null)
						lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+element+beckFirst.get(j*2)+one1.substring(t+lsh.length(),one1.length());
					else
						lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+ls[1]+one1.substring(index+s.length(),one1.length())+"��."+element+beckFirst.get(j*2)+one1.substring(t+lsh.length(),one1.length());
				}				
				else{//������Ǵ�д�������ս���ˣ���ô��First��������Ľ��������������һ���ģ����Բ�Ҫ�ظ���ӡ������
					if(ls==null){
						if(element==in.get(0)[0]||s.equals(element))
							lh=in.get(0)[0]+"��"+one1.substring(0,index)+element+one1.substring(t,one1.length())+"\t";
						else
							lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+element+one1.substring(t,one1.length())+"\t";
					}
					else{
						if(ls[1].length()==1||ls[1].length()==2&&!ls[1].endsWith("��")&&!ls[1].endsWith("\'"))
							lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+element+one1.substring(t,one1.length());
						else
							lh=in.get(0)[0]+"��"+one1+"��"+one1.substring(0,index)+ls[1]+one1.substring(index+s.length(),one1.length())+"��."+element+one1.substring(t,one1.length())+"!";
					}
				}
				result1.add(beckFirst.get(j*2));//������������ݣ�����һ�����ǰѵ�ַ���ϣ�����һ�������Ҫ��������
				result1.add(lh);
			}
		}
		result=addArrayString(result,result1);//֮ǰҲûд��仰�������֮ǰ�����ݸ����ˣ�����֮ǰ�����ݶ�ʧ
		result1.clear();
		return result;
	}
	public ArrayList<String> addArrayString(ArrayList<String> a,ArrayList<String> b){//�����ַ����������
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<a.size();i+=2){//��Ϊ��ÿһ����������������������ݣ���һ���ǽ�����ڶ���λ�ñ�����ǵõ�������·��
			String s = a.get(i);
			if(result.contains(s)||s.equals("")){//������������������ַ������Ͳ����������ˣ�����Ϊ��ȥ���ظ���
				int index=result.indexOf(s);
				if(result.get(index+1).length()>a.get(i+1).length()){//���������·�������еĶ�
					result.set(index, s);
					result.set(index+1,a.get(i+1));
				}
				continue;
			}
			result.add(s);
			result.add(a.get(i+1));//����Ҫ��·�������������µĽ������
		}
		for(int i=0;i<b.size();i+=2){
			String s = b.get(i);
			if(result.contains(s)||s.equals("")){
				int index=result.indexOf(s);
				if(result.get(index+1).length()>b.get(i+1).length()){//���������·�������еĶ�
					result.set(index, s);
					result.set(index+1,b.get(i+1));
				}
				continue;
			}
			result.add(s);//ż����ַ��ŵ�������
			result.add(b.get(i+1));//������ַ��ŵ��Ǹ����ݻ�õ�·��
		}
		return result;
	}
	public void print(ArrayList<String[]> list){
		for(int i=0;i<list.size();i++){//ѭ�����ս����������
			String[] one = list.get(i);//�õ�ĳһ�����ս�����е�����·��
			String[][] strings= new String[one.length][];
			String[] finals = new String[one.length];//·������վ��
			int number=0;//��¼ĳһ��������Чվ������������м���·����������м�����Чվ�㣬��������Щվ�����ظ��ģ�����ͬһվ�㷢��
			int max=0;
			for(int j=0;j<one.length;j++){
				strings[j]=one[j].split("/");
				if(strings[j].length>max)
					max=strings[j].length;//���ĳһ���ս��·���һ��
			}
			for(int j=0;j<max;j++){//ѭ���վ�����
				number=0;
				for(int k=0;k<strings.length;k++){//�ж�����·����ѭ�����ٴ�
					String lsh="";
					if(j>=strings[k].length){
						lsh=strings[k][strings[k].length-1];	
					}else {
						lsh=strings[k][j];
					}
					int m=0;
					for(m=0;m<number;m++){//��¼��Чվ��
						if(lsh.equals(finals[m]))
							break;
					}
					if(m==number){
						finals[number]=lsh;
						number++;
					}
				}
				for(int k=0;k<number;k++){//��ӡÿһ��·����ĳ��վ��
					System.out.print(finals[k]);
					if(k!=number-1)
						System.out.print(" + ");
				}
				if(j<max-1)
					System.out.print(" = ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args){
		new FirstFollow3();
	}
}
