package tek.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
enum Vehicle{
	HONDA,CIVIC
}
class CommandInterface{
	public byte[] sendCommand(byte[] command){
		byte[] b={(byte)0xFE};
		return b;
	}
}
interface IVehicleCommand{
	List<String> execCommand(Vehicle v,CommandInterface command);
}
public class TestCommand implements IVehicleCommand{

	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println((int)(byte)-128);
	}

	@Override
	public List<String> execCommand(Vehicle v, CommandInterface command) {
		byte b[]=new byte[0];
		switch(v){
		case HONDA:
			b=command.sendCommand(new byte[5]);
		case CIVIC:
			b=command.sendCommand(new byte[5]);
		}
		if(b.length==0)
		return null;
		else{
			if(b[0]==(byte)0xFE)
				return new ArrayList<String>(Arrays.asList("wori"));
		}
		return null;
	}
}