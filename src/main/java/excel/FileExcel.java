package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import models.Transazione;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileExcel {
	
	//https://www.tutorialspoint.com/apache_poi/apache_poi_quick_guide.htm
	
	public static void createFileExcel(List<Transazione> lista) throws IOException
	{
	XSSFWorkbook wb = new XSSFWorkbook(); //creo un workbook e per poterlo istanziare devo decidere quale formato di Ecel usare, per le versioni di Ecel dal 97 al 2003 si usa
										 //HSSFWorkbook, per le versioni dal 2007 in poi si usa XSSFWorkbook 
	
	XSSFSheet sheet = wb.createSheet("transazioni"); // creo un foglio
	
	XSSFCreationHelper createHelper = wb.getCreationHelper(); //An object that handles instantiating concrete classes of the various instances one needs for HSSF and XSSF
	

	XSSFRow row = sheet.createRow(0);  //creo una riga alla posizione 0
	
	String data = "DATA";
	String descrizione = "DESCRIZIONE";
	String categoria = "CATEGORIA";
	String importo = "IMPORTO";

	XSSFCell cell1 = row.createCell(0); 
	cell1.setCellValue(createHelper.createRichTextString(data));
	XSSFCell cell2 = row.createCell(1); 
	cell2.setCellValue(createHelper.createRichTextString(descrizione));
	XSSFCell cell3 = row.createCell(2); 
	cell3.setCellValue(createHelper.createRichTextString(categoria));
	XSSFCell cell4 = row.createCell(3); 
	cell4.setCellValue(createHelper.createRichTextString(importo));
	
	int i = 1;
	
	for (Transazione transazione : lista) {
		
		XSSFRow rowTrans = sheet.createRow(i);
		XSSFCell cellTrans1 = rowTrans.createCell(0); 
		cellTrans1.setCellValue(createHelper.createRichTextString(transazione.getData()));
		XSSFCell cellTrans2 = rowTrans.createCell(1); 
		cellTrans2.setCellValue(createHelper.createRichTextString(transazione.getDescrizione()));
		XSSFCell cellTrans3 = rowTrans.createCell(2); 
		cellTrans3.setCellValue(createHelper.createRichTextString(transazione.getCategoria()));
		XSSFCell cellTrans4 = rowTrans.createCell(3); 
		cellTrans4.setCellValue(transazione.getImporto());
		i++;
	}
	
	String path = "C:\\Users\\Admin\\eclipse-workspace\\HoneyMoney\\transazioni.xls";
	
	FileOutputStream stream = new FileOutputStream(new File (path));
	wb.write(stream);
	stream.close();
	}

}
