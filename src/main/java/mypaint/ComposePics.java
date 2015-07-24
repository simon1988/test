package mypaint;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ComposePics {

	private static final int ID_PIC_WIDTH=500;
	private static final int ID_PIC_HEIGHT=300;
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 

	public static void compositeImages(String file1, String file2, String outputFile) {// 纵向处理图片
		try {
			/* 1 读取第一张图片 */
			File fileOne = new File(file1);
			BufferedImage imageFirst = resize(ImageIO.read(fileOne),ID_PIC_WIDTH,ID_PIC_HEIGHT);
			int width = imageFirst.getWidth();// 图片宽度
			int height = imageFirst.getHeight();// 图片高度
			int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
			imageArrayFirst = imageFirst.getRGB(0, 0, width, height,
					imageArrayFirst, 0, width);

			/* 1 对第二张图片做相同的处理 */
			File fileTwo = new File(file2);
			BufferedImage imageSecond = resize(ImageIO.read(fileTwo),ID_PIC_WIDTH,ID_PIC_HEIGHT);
			int[] imageArraySecond = new int[width * height];
			imageArraySecond = imageSecond.getRGB(0, 0, width, height,
					imageArraySecond, 0, width);

			// 生成新图片
			BufferedImage imageResult = new BufferedImage(width, height * 2,
					BufferedImage.TYPE_INT_RGB);
			imageResult.setRGB(0, 0, width, height, imageArrayFirst, 0, width);// 设置左半部分的RGB
			imageResult.setRGB(0, height, width, height, imageArraySecond, 0,
					width);// 设置右半部分的RGB
			File outFile = new File(outputFile);
			ImageIO.write(imageResult, "jpg", outFile);// 写图片
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		compositeImages("D:\\TDDOWNLOAD\\1.jpg","D:\\TDDOWNLOAD\\1.jpg","D:\\TDDOWNLOAD\\11.jpg");
	}

}
