package test;
import java.io.*;
public class DelLaji {

	final String laji[]={
			"�ɿ����� ���ྫ��ͼ�龡�ڷɿ�",
			"���Է��ʣ�http://www.feiku.com",
			"�ֻ����ʣ�http://wap.feiku.com",
			"�ɿ���̳��http://bbs.feiku.com"
		};
	final String path="C:\\TDDOWNLOAD\\wqxbook\\�½��ļ���\\�춼����.txt";
	public DelLaji() {
		// TODO Auto-generated constructor stub
		dothis();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DelLaji();
	}
	public void dothis(){
		try{
			File file=new File(path);
			File bakfile=new File(path+".bak");
			BufferedWriter bw=new BufferedWriter(new FileWriter(bakfile));
			BufferedReader br=new BufferedReader(new FileReader(file));
			String s;
			L1:while((s=br.readLine())!=null){
				for(String lajitext:laji){
					if(s.equals(lajitext))continue L1;
					else if(s.contains(lajitext))s=s.replaceAll(lajitext, "");
				}
				bw.write(s);
				bw.newLine();
			}
			br.close();
			bw.close();
			file.delete();
			bakfile.renameTo(file);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void dothis2(){
		String path="C:/TDDOWNLOAD/�½��ļ��� (2)/�½��ļ���/����էйTXT ȫ��";
		File dir=new File(path);
		
		try {
			FileWriter fw=new FileWriter(dir.getParent()+"/����էй.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			for(File file:dir.listFiles()){
				FileReader fr=new FileReader(file);
				BufferedReader br=new BufferedReader(fr);
				String s="";
				while(!(s=br.readLine()).startsWith("��������")){
					//System.out.println(s);
					bw.write(s);
					bw.newLine();
				}
			}
			bw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
