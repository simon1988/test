package fragments;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class TestJmx {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		TestJmx instance = new TestJmx();
		Hello hello = instance.new Hello();
		mbs.registerMBean(hello, new ObjectName("com.nxm.jmx:name=magic10exit"));
		while(hello.getMagic()<10){
			System.out.println("waiting jmx command..current magic is " + hello.getMagic());
			Thread.sleep(2000);
		}
		System.out.println("magic is "+hello.getMagic()+", exit.");
	}
	
	
	public class Hello implements HelloMBean{
		private int magic;
		@Override
		public int getMagic() {
			return magic;
		}
		@Override
		public void setMagic(int magic) {
			this.magic = magic;
		}
		@Override
		public void increaseMagic() {
			this.magic++;
		}
	}
	public interface HelloMBean {
		int getMagic();
		void setMagic(int magic);
		void increaseMagic();
	}

}
