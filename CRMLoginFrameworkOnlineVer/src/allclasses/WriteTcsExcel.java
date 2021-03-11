package allclasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteTcsExcel {


	XSSFSheet worksheet1;
	XSSFWorkbook workbook1;
	InputStream fileInputStream1;
	int rowCount1=0;
	String cellValue1="";
	XSSFCell id1;
	ParseTfsTCs ptcs;
	
	/**Reading excel values
	 * @throws IOException */
	public void readData(String filePath, String sheetName, int columnNo, int NewColNum, int ExpColumn) throws IOException {
		
//		 Path path = new File(getClass()
//				  .getResource(filePath)
//				  .getFile()).toPath();
		 //Reading in first Excel sheet
		fileInputStream1 = Files.newInputStream(Paths.get(filePath));
//		FileInputStream fileInputStream1 = new FileInputStream(file1); //will cause a garbage
        workbook1 = new XSSFWorkbook(fileInputStream1);

        if (workbook1.getNumberOfSheets() != 0) {
        	/**
        	 * Will search in file's sheets till find 'sheet1'
        	 **/
            for (int i1 = 0; i1 < workbook1.getNumberOfSheets(); i1++) {
            	if(workbook1.getSheetName(i1).equals(sheetName)) {
        	        worksheet1= workbook1.getSheet(sheetName);	        	        
        	        rowCount1= worksheet1.getPhysicalNumberOfRows();  //reading #of rows in first sheet
        	        break;
        	     }else {
        	        	//If sheet not existing
        	        	if(i1==workbook1.getNumberOfSheets()-1) {
        	        		CommonParaFun.logger.log(Level.SEVERE, "the  sheet isn't avilable");
        	        		System.exit(0);
        	        	}
        	        }	        	      
            	}
            int cell=columnNo;
            for(int i=0;i<rowCount1;i++) {
	        		ptcs=new ParseTfsTCs();
	        		//getting row values in both sheets
	        		XSSFRow row1 = worksheet1.getRow(i);	               
	        		
	                	//getting cell value from first sheet
	        		 
	                     id1 = row1.getCell(cell);
	                     if (id1 != null) {
	                         id1.setCellType(CellType.STRING);
	                         cellValue1 = id1.getStringCellValue();
	                        
	                         if(i==107) {
	                        	 System.out.println("wait");
	                         }
	                         ptcs.extractExpected(cellValue1);
	                         //write steps
	                         StringBuffer sb = new StringBuffer();
	                         for(int x = 0; x < ptcs.steps.length; x++) {
	                            sb.append(ptcs.steps[x]);
	                         }
	                         String str = sb.toString();
	                         Cell parsedCell=row1.createCell(NewColNum);
	                         parsedCell.setCellValue(str);
	                         
	                         //write expected
	                         StringBuffer exsb = new StringBuffer();
	                         for(int y = 0; y < ptcs.expected.length; y++) {
	                        	 exsb.append(ptcs.expected[y]);
	                         }
	                         String exstr = exsb.toString();
	                         Cell expCell=row1.createCell(ExpColumn);
	                         expCell.setCellValue(exstr);
	                         
	                     }
	                     
	                 
	                     //clearing cells variable
	                     cellValue1="";
	                  System.out.println("i:"+i);
//	                  System.out.println("rowCount:"+rowCount1);
	                
	        		
	        	}
            fileInputStream1.close();
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook1.write(outputStream);
            workbook1.close();
            outputStream.close();
    		CommonParaFun.logger.log(Level.SEVERE, "Adjusting values has been completed.");

         }else {
        		CommonParaFun.logger.log(Level.SEVERE, "Excel File has no sheets");
         }
		
	}
	


}
