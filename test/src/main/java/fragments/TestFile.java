package fragments;

import java.io.File;
import java.io.FilenameFilter;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File file = new File(".");
			for (String name : file.list(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub
					return name.charAt(0) != '.';
				}
			})) {
				System.out.println(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		//listFile(".", 0);
		File tryCreate = new File("xxx/xxx");
		if(tryCreate.exists());
		if(!tryCreate.mkdirs())System.out.println("zhu");
	}

}
