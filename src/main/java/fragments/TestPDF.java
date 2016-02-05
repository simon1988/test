package fragments;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class TestPDF {

	public static void main(String[] args) throws Exception{
		editPdf();
	}
	
	public static void createPdf() throws Exception{
		BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font fontChinese = new Font(bf, 12, Font.NORMAL);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("D:/TDDOWNLOAD/test.pdf"));
//		PdfWriter.getInstance(document, byteArrayOutputStream);
		document.open();
		document.add(new Paragraph("Hello 世界!", fontChinese));
		document.close();
		byte[] file = byteArrayOutputStream.toByteArray();
		System.out.println(file.length);
	}
	/**
	 * 1, 420
	 * 1, 160
	 * 2, 710
	 * 2, 590
	 * 2, 450
	 * 2, 310
	 * 2, 170
	 * 3, 770
	 * 3, 640
	 */
	public static void editPdf() throws Exception{
		String answer="ABCDEFGHI";
		PdfReader reader = new PdfReader("D:/TDDOWNLOAD/assessment.pdf");
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:/TDDOWNLOAD/assessment2.pdf"));
		
		PdfContentByte pagecontent = stamper.getOverContent(1);
		
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(0)), 170, 420, 0);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(1)), 170, 160, 0);
		pagecontent = stamper.getOverContent(2);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(2)), 170, 710, 0);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(3)), 170, 590, 0);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(4)), 170, 450, 0);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(5)), 170, 310, 0);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(6)), 170, 170, 0);
		pagecontent = stamper.getOverContent(3);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(7)), 170, 770, 0);
		ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT, new Phrase("answer:"+(char)answer.charAt(8)), 170, 640, 0);
		
		stamper.close();
		reader.close();
	}
}
