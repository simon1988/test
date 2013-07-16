package thread;

import java.util.concurrent.*;
import java.io.*;
public class SearchKeyword implements Runnable {

	String path,keyword;
	ArrayBlockingQueue<File> abq;
	
	public SearchKeyword(String path,String keyword){
		this.path=path;
		this.keyword=keyword;
		abq=new ArrayBlockingQueue<File>(10);
		try{
			ThreadGroup tg=new ThreadGroup("tg");
			for(int i=0;i<10;i++)
				new Thread(tg,this).start();
			readfile(new File(path));
			tg.interrupt();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void readfile(File root)throws Exception{
		for(File file:root.listFiles()){
			if(file.isDirectory())readfile(file);
			abq.put(file);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				File file=abq.take();
				if(file.getName().toLowerCase().contains(keyword))
					System.out.println(file.getAbsolutePath());
			}
		}catch(Exception e){
			//this thread stops
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*java.util.Scanner scanner=new java.util.Scanner(System.in);
		String path=scanner.nextLine();
		String keyword=scanner.nextLine();
		new SearchKeyword(path,keyword);*/
		new SearchKeyword("C:/Program Files/Java/jdk1.6.0_02/src/java/util/","list");
	}
}
