package fragments;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class TestRandomAccessFile {

	final static int NUMBER=10;
	
	static void readWrite1(){
		try{
			RandomAccessFile file=new RandomAccessFile("test.txt","rw");
			FileChannel fc=file.getChannel();
			ByteBuffer buffer=ByteBuffer.allocate(NUMBER*4);
			IntBuffer intBuffer=buffer.asIntBuffer();
			for(int i=1;i<=NUMBER;i++){
				intBuffer.put(i);
			}
			System.out.println("buffer pos: "+buffer.position()+" limit:"+buffer.limit());
			fc.write(buffer);
			fc.close();
			
			fc=new RandomAccessFile("test.txt","r").getChannel();
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
			for(int i=NUMBER;i>0;i--){
				intBuffer.position(i-1);
				System.out.print(intBuffer.get()+",");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	static void readWrite2(){
		try {
			RandomAccessFile rf = new RandomAccessFile("test.txt","rw");
			for(int i=1;i<=NUMBER;i++){
				rf.writeInt(i);
			}
			rf.close();
			
			rf = new RandomAccessFile("test.txt","r");
			for(int i=NUMBER;i>0;i--){
				rf.seek(i*4-4);
				System.out.print(rf.readInt()+",");
			}
			rf.close();
			
			System.out.println();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readWrite2();

	}

}
