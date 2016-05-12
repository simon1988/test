package fragments;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class TestFile {

	/*
	 * use BufferedInputStream, BufferedOutputStream with FileInputStream, FileOutputStream to read bytes
	 * use BufferedReader, BufferedWriter with FileReader, FileWriter to read chars
	 */
	public static void listFile(String dir, int level) {
		File f = new File(dir);
		for (int i = 0; i <= level; i++) {
			System.out.print("| ");
		}
		System.out.println("|_" + f.getName());
		if (f.isDirectory()) {
			File fs[] = f.listFiles();
			for (File s : fs) {
				listFile(s.getPath(), level + 1);
			}
		}
	}
	
	public static void randomAccessFile(int number){
		try(RandomAccessFile file=new RandomAccessFile("test.txt","rw")){
			FileChannel fc=file.getChannel();
			ByteBuffer buffer=ByteBuffer.allocate(number*4);
			IntBuffer intBuffer=buffer.asIntBuffer();
			for(int i=1;i<=number;i++){
				intBuffer.put(i);
			}
			System.out.println("buffer pos: "+buffer.position()+" limit:"+buffer.limit());
			fc.write(buffer);
			buffer.clear();
			fc.read(buffer);
			fc.close();
			System.out.println("buffer pos: "+buffer.position()+" limit:"+buffer.limit());
			buffer.flip();
			intBuffer=buffer.asIntBuffer();
			while(intBuffer.hasRemaining()){
				System.out.print(intBuffer.get()+",");
			}
			System.out.println();
			for(int i=number;i>0;i--){
				intBuffer.position(i-1);
				System.out.print(intBuffer.get()+",");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File(".");
			for (String name : file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.charAt(0) != '.';
				}
			})) {
				System.out.println(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		listFile(".", 0);
	}

}
