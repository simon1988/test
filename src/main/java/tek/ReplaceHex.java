package tek;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ReplaceHex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: ./run [file name or dir name] [HEX to replace] [HEX replace to]");
			System.exit(1);
		}

		if (args[1].toUpperCase().startsWith("0X")) {
			args[1] = args[1].substring(2);
		}
		if (args[2].toUpperCase().startsWith("0X")) {
			args[2] = args[2].substring(2);
		}
		if (args[2].equalsIgnoreCase("$time")) {
			long curTime = (new Date()).getTime() / 1000;
			byte[] time = new byte[4];
			for (int i = 0; i < 4; i++) {
				time[3 - i] = (byte) (curTime & 0xFF);
				curTime >>= 8;
			}
			args[2] = bytesToHexString(time);
			System.out.println("$time is " + args[2]);
		}
		if (args[1].length() != args[2].length()) {
			System.out.println("Source Hex and dest Hex must have the same length");
			System.exit(2);
		}
		System.out.println("Try to replace " + args[1] + " to " + args[2]);
		byte srcBytes[] = hexStringToBytes(args[1]);
		byte dstBytes[] = hexStringToBytes(args[2]);
		//args[0] = "C:\\Users\\xniu1\\Documents\\traffic_Aif\\hdr.interbsc-03_localhost21800.07112012.094930.dat";
		try {
			File file = new File(args[0]);
			if (!file.exists()) {
				throw new FileNotFoundException("cannot find file or dir: "
						+ args[0]);
			}
			ArrayList<File> files = new ArrayList<File>();
			if (file.isDirectory()) {
				for (File f : file.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String name) {
						return name.toLowerCase().endsWith(".dat");
					}

				})) {
					files.add(f);
				}
			} else {
				files.add(file);
			}
			for (File datFile : files) {
				System.out.print("processing " + datFile + "...");

				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(datFile));

				byte buf[] = new byte[bis.available()];
				int len = bis.read(buf);
				bis.close();

				for (int i = 0; i < len - srcBytes.length + 1; i++) {
					int match = i;
					for (int j = 0; j < srcBytes.length; j++) {
						if (srcBytes[j] != buf[i + j]) {
							match = -1;
							break;
						}
					}
					if (match != -1) {
						for (int j = 0; j < dstBytes.length; j++) {
							buf[i + j] = dstBytes[j];
						}
						i = i + dstBytes.length - 1;
					}
				}

				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(datFile));
				bos.write(buf);
				bos.close();
				System.out.println("OK");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Convert byte[] to hex string.
	 * 
	 * @param src byte[] data
	 * 
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

}
