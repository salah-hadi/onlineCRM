package allclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author Salah @EMEIT
 * **/

public class CompareExcel {	
	XSSFSheet worksheet1;
	XSSFSheet worksheet2;
	XSSFWorkbook workbook1;
	XSSFWorkbook workbook2;
	InputStream fileInputStream1;
	InputStream fileInputStream2;
	int rowCount1=0;
	int rowCount2=0;
	String cellValue1="";
	String cellValue2="";
	XSSFCell id1;
	XSSFCell id2;
//	XWPFDocument document;
//	FileOutputStream out;
//	XWPFParagraph paragraph;
//	XWPFRun run;
//	Logger logger;
	/**
	 * This Function will compare two Excel files and show if the are the same or not.
	 * created to compare expected values in excel sheet by the one exported from MS dynamics.
	 * It will works only on excel with extension .xlsx (2007-365 extension)
	 * @param file1 Path to the first Excel file
	 * @param file2 Path to the second Excel file.
	 * @param sheet1 Name of sheet in first Excel sheet you want to compare.
	 * @param sheet2 Name of sheet in second Excel sheet you want to compare.
	 * @param resultFile Path to .doc file you will write in.
	 * @throws IOException -used to handle FileNotFoundException and InvalidPathException
	 * **/	
	public void compareExcelFiles(String file1, String file2, String sheet1, String sheet2, String resultFilePath) throws IOException {
		CommonParaFun.logger = java.util.logging.Logger.getLogger(CommonParaFun.class.getName());
			 //Reading in first Excel sheet
			fileInputStream1 = Files.newInputStream(Paths.get(file1));
//			FileInputStream fileInputStream1 = new FileInputStream(file1); //will cause a garbage
	        workbook1 = new XSSFWorkbook(fileInputStream1);
	
	        if (workbook1.getNumberOfSheets() != 0) {
	        	/**
	        	 * Will search in file's sheets till find 'sheet1'
	        	 **/
	            for (int i1 = 0; i1 < workbook1.getNumberOfSheets(); i1++) {
	            	if(workbook1.getSheetName(i1).equals(sheet1)) {
	        	        worksheet1= workbook1.getSheet(sheet1);	        	        
	        	        rowCount1= worksheet1.getPhysicalNumberOfRows();  //reading #of rows in first sheet
	        	        break;
	        	        }else {
	        	        	//If sheet not existing
	        	        	if(i1==workbook1.getNumberOfSheets()-1) {
	        	        		CommonParaFun.logger.log(Level.SEVERE, "the provide first sheet isn't avilable in the First Excel file");
	        	        		System.exit(0);
	        	        	}
	        	        }	        	      
	            	}
	            }else {
	        		CommonParaFun.logger.log(Level.SEVERE, "Excel File has no sheets");
	            }
	        
	      //Reading in second Excel sheet
	        fileInputStream2 = Files.newInputStream(Paths.get(file2));
//	        FileInputStream fileInputStream2 = new FileInputStream(file2);
	        workbook2 = new XSSFWorkbook(fileInputStream2);	        
	        if(workbook2.getNumberOfSheets()!=0) {
	        	/**
	        	 * Will search in file's sheets till find 'sheet2'
	        	 **/
	        	for(int i2=0;i2<workbook2.getNumberOfSheets();i2++) {
	        		if(workbook2.getSheetName(i2).equals(sheet2)) {
	        			  worksheet2 = workbook2.getSheet(sheet2); 	        	         
  	        	          rowCount2= worksheet2.getPhysicalNumberOfRows();  //reading #of rows in second sheet  	        	       
  	        	        //getting #of columns in second sheet
  	        	          XSSFRow row=worksheet2.getRow(0);
  	        	          int colNum=row.getLastCellNum();  	        	        
  	        	      //Validating if number of rows in first sheet are equal to the second one.  
  	        	         if(rowCount1==rowCount2) {  	        	        	
  	        	        	//reading each row and validate every cell value in both files.
  	        	        	 
  	        	        	 //Blank Document
//  	        		       document = new XWPFDocument();
  	        		      //Write the Document in file system
//  	        		       out = new FileOutputStream(new File(resultFile));
  	        		   //create Paragraph
//  	        		       paragraph = document.createParagraph();
//  	        		       run = paragraph.createRun();
//  	        	        	File file = File.createTempFile(resultFilePath, ".txt");
  	        	        	 File file=new File(resultFilePath);
  	        	  	        FileWriter writer = new FileWriter(file);
  	        	        	for(int i=0;i<rowCount1;i++) {
  	        	        		
  	        	        		//getting row values in both sheets
  	        	        		XSSFRow row1 = worksheet1.getRow(i);
  	        	                XSSFRow row2 = worksheet2.getRow(i);
  	        	                //comparing cell values.
  	        	                for(int cell=0;cell<colNum;cell++) {
  	        	                	 
  	        	                	//getting cell value from first sheet
  	        	                     id1 = row1.getCell(cell);
  	        	                     if (id1 != null) {
  	        	                         id1.setCellType(CellType.STRING);
  	        	                         cellValue1 = id1.getStringCellValue();
  	        	                     }
  	        	                     
  	        	                     //getting cell value from second sheet
  	        	                     id2 = row2.getCell(cell);
  	        	                     if (id2 != null) {
  	        	                         id2.setCellType(CellType.STRING);
  	        	                         cellValue2 = id2.getStringCellValue();
  	        	                     }
  	        	                     
  	        	                     //if values in both cells isn't equal
  	        	                     if(!cellValue1.equals(cellValue2))
  	        	                     {
  	        	                         CommonParaFun.logger.log(Level.SEVERE, "[ERROR] : there're cells aren't the same, Row "+(i+1)+" cell: "+(cell+1)+",cell 1:"+cellValue1+" ,cell 2:"+cellValue2);
//  	        	                         run.addCarriageReturn();
//  	        	                         run.setText("[ERROR] : there're cells aren't the same, Row "+(i+1)+" cell: "+(cell+1)+",cell 1:"+cellValue1+" ,cell 2:"+cellValue2);
  	        	                         writer.write("[ERROR] : there're cells aren't the same, Row "+(i+1)+" cell: "+(cell+1)+",cell 1:"+cellValue1+" ,cell 2:"+cellValue2+System.getProperty("line.separator"));
  	        	                     
  	        	                     }else {
  	        	                    	 CommonParaFun.logger.log(Level.SEVERE, "both cells have the same value");
  	        	                    	 CommonParaFun.logger.log(Level.SEVERE,"Row: "+(i+1)+" cell: "+(cell+1)+",cell 1:"+cellValue1+" ,cell 2:"+cellValue2);
//  	        	                       run.addCarriageReturn();
//  	        	                       run.setText("both cells have the same value \n"
//  	        	                                     +"Row: "+(i+1)+" cell: "+(cell+1)+",cell 1:"+cellValue1+" ,cell 2:"+cellValue2);
  	        	                       writer.write("both cells have the same value \n"
  	        	                                     +"Row: "+(i+1)+" cell: "+(cell+1)+",cell 1:"+cellValue1+" ,cell 2:"+cellValue2+System.getProperty("line.separator"));
  	        	                     }
  	        	                     //clearing cells variable
  	        	                     cellValue1=cellValue2="";
  	        	                  
  	        	                }
  	        	        		
  	        	        	}

//  	        		      document.write(out);
//  	        		      out.close();
  	        	        	writer.flush();
  	        	        	writer.close();
  	        	        }else {
  	        	        	CommonParaFun.logger.log(Level.SEVERE, "Rows aren't equal in both files, # of rows in first sheet:"+rowCount1+" ,# rows in second sheet:"+rowCount2);
  	        	        }
  	        	        workbook1.close();
  	        	        workbook2.close();
  	        	        break; 
  	            		}else {
	        	        	if(i2==workbook2.getNumberOfSheets()-1) {
	        	        		CommonParaFun.logger.log(Level.SEVERE, "the provide second sheet isn't avilable in the second Excel file");
	        	        		System.exit(0);
	        	        	}
  	            		}
	        		}
	        		
	        	}else {
	        		CommonParaFun.logger.log(Level.SEVERE, "Excel File has no sheets");
	        	}
	        
	        
		
	}
	

		  
}
