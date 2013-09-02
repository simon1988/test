package thread;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SearchKeyword {

	ArrayBlockingQueue<File> abq = new ArrayBlockingQueue<File>(10);

	public void find(String path, String keyword) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			es.execute(new ProcessFileTask(keyword));
		}
		es.shutdown();
		readfile(new File(path));
		for (int i = 0; i < 10; i++) {
			try {
				abq.put(new File(""));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void readfile(File root) {
		for (File file : root.listFiles()) {
			if (file.isDirectory())
				readfile(file);
			try {
				abq.put(file);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class ProcessFileTask implements Runnable {
		private String keyword;
		
		public ProcessFileTask(String keyword){
			this.keyword = keyword;
		}
		
		@Override
		public void run() {
			while (true) {
				File file = null;
				try {
					file = abq.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//poison
				if(file.getName()==""){
					break;
				}
				if (file.getName().toLowerCase().contains(keyword)) {
					System.out.println(file.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SearchKeyword().find("C:\\test\\exttest", "java");
	}
}
