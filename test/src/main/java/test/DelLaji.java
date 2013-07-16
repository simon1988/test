package test;
import java.io.*;
public class DelLaji {

	final String laji[]={
			"飞库制作 更多精彩图书尽在飞库",
			"电脑访问：http://www.feiku.com",
			"手机访问：http://wap.feiku.com",
			"飞库论坛：http://bbs.feiku.com"
		};
	final String path="C:\\TDDOWNLOAD\\wqxbook\\新建文件夹\\异都风流.txt";
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
		String path="C:/TDDOWNLOAD/新建文件夹 (2)/新建文件夹/春光乍泄TXT 全本";
		File dir=new File(path);
		
		try {
			FileWriter fw=new FileWriter(dir.getParent()+"/春光乍泄.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			for(File file:dir.listFiles()){
				FileReader fr=new FileReader(file);
				BufferedReader br=new BufferedReader(fr);
				String s="";
				while(!(s=br.readLine()).startsWith("三五制作")){
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
