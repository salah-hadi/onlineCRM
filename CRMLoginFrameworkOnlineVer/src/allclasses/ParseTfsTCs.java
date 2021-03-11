package allclasses;

public class ParseTfsTCs {
	
	String[] steps;
	String[] expected;
	String[] finalResult;
	
//	XSSFSheet worksheet1;
//	XSSFWorkbook workbook1;
//	InputStream fileInputStream1;
//	int rowCount1=0;
//	String cellValue1="";
//	XSSFCell id1;
	
	
	
	public void extractSteps(String step) {
		String[] result=step.split("<DIV>");
		
		for (int i=0; i < result.length; i++)
	    {
			result[i]=result[i].replaceAll("<P>", "");
			result[i]=result[i].replaceAll("</P>", "");
			result[i]=result[i].replaceAll("<P />", "");
			result[i]=result[i].replaceAll("</DIV>", "");
			result[i]=result[i].replaceAll("<DIV />", "");
			result[i]=result[i].replaceAll("<SPAN STYLE=\"dir:ltr;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-sa\" STYLE=\"font-family:Times New Roman;\">", "");
			result[i]=result[i].replaceAll("<SPAN STYLE=\"dir:ltr;\" />", "");
			result[i]=result[i].replaceAll("</SPAN>", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"dir:rtl;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"dir:rtl;\" />", "");
			result[i]=result[i].replaceAll("<SPAN>", "");
			result[i]=result[i].replaceAll("<SPAN STYLE=\"dir:ltr;font-family:times new roman;font-size:16px;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"dir:rtl;font-family:times new roman;font-size:16px;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"dir:ltr;\" />", "");
			result[i]=result[i].replaceAll("<P STYLE=\"dir:rtl;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"font-family:times new roman;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"font-family:times new roman;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"en-us\" STYLE=\"font-family:Times New Roman;font-weight:bold;font-size:16px;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"dir:rtl;font-family:Times New Roman;font-size:16px;\">", "");
			result[i]=result[i].replaceAll("<SPAN xml:lang=\"ar-eg\" STYLE=\"dir:rtl;font-family:times new roman;\">", "");
	    }
	    
		
		
		finalResult=result;
//		return result;
	}
	
	public void extractExpected(String text) {
		extractSteps(text);

		finalResult=removeTheElement(finalResult,0);
		if(finalResult.length%2!=0) {
			steps=new String[finalResult.length/2+1];
			expected=new String[finalResult.length/2+1];
		}else {
			steps=new String[finalResult.length/2];
			expected=new String[finalResult.length/2];
		}
		int lExpectedIn=0;
		int lsteps=0;
		int counterStep=1;
		int counterExp=1;
		for (int i=0; i < finalResult.length; i++)
	    {
			if(i==0) {
				steps[0]=counterStep+"-"+finalResult[0];
				counterStep++;
				lsteps++;
			}
			else if(i%2!=0) {
				expected[lExpectedIn]=counterExp+"-"+finalResult[i];
				lExpectedIn++;
				counterExp++;
			}else {
				steps[lsteps]=counterStep+"-"+finalResult[i];
				counterStep++;
				lsteps++;
			}
			
	    }
	//	steps=adjustArrValue(steps);
		//expected=adjustArrValue(expected);
		
	}
	
	
	public String[] removeTheElement(String[] arr, int index) 
		{ 
		
		// If the array is empty 
		// or the index is not in array range 
		// return the original array 
		if (arr == null || index < 0 || index >= arr.length) { 
		
			return arr; 
		} 
		
		// Create another array of size one less 
		String[] anotherArray = new String[arr.length - 1]; 
		
		// Copy the elements except the index 
		// from original array to the other array 
		for (int i = 0, k = 0; i < arr.length; i++) { 
		
		// if the index is 
		// the removal element index 
		if (i == index) { 
			continue; 
		} 
		
		// if the index is not 
		// the removal element index 
		anotherArray[k++] = arr[i]; 
		} 
		
		// return the resultant array 
		return anotherArray; 
		} 
	

	
//		System.out.println("number of rows"+a.parseChar("\r\n" + 
//				"<DIV><P>launch QNB CRM</P></DIV><DIV><P>Home screen is displayed</P></DIV>"));
//		System.out.println("The first step is:"+a.step("<DIV><P>launch QNB CRM</P></DIV>\r\n" + 
//				"<DIV><P>Home screen is displayed</P></DIV>\r\n" + 
//				"<DIV><P>Login as any CRM user</P></DIV>\r\n" + 
//				"<DIV><P>Login has completed successfully</P></DIV>"));
		
//		a.extractExpected("<DIV><P>launch QNB CRM</P></DIV>\r\n" + 
//				"<DIV><P>Home screen is displayed</P></DIV>\r\n" + 
//				"<DIV><P>Login as any CRM user</P></DIV>\r\n" + 
//				"<DIV><P>Login has completed successfully</P></DIV>");
//
//		for (int i=0; i < a.steps.length; i++)
//	    {
//	      System.out.println("step is:"+a.steps[i]);
//	    }
//		
//		for (int i=0; i < a.expected.length; i++)
//	    {
//	      System.out.println("step is:"+a.expected[i]);
//	    }
	
//	/**Reading excel values
//	 * @throws IOException */
//	public void readData(String filePath, String sheetName, int columnNo, int NewColNum) throws IOException {
//		 //Reading in first Excel sheet
//		fileInputStream1 = Files.newInputStream(Paths.get(filePath));
////		FileInputStream fileInputStream1 = new FileInputStream(file1); //will cause a garbage
//        workbook1 = new XSSFWorkbook(fileInputStream1);
//
//        if (workbook1.getNumberOfSheets() != 0) {
//        	/**
//        	 * Will search in file's sheets till find 'sheet1'
//        	 **/
//            for (int i1 = 0; i1 < workbook1.getNumberOfSheets(); i1++) {
//            	if(workbook1.getSheetName(i1).equals(sheetName)) {
//        	        worksheet1= workbook1.getSheet(sheetName);	        	        
//        	        rowCount1= worksheet1.getPhysicalNumberOfRows();  //reading #of rows in first sheet
//        	        break;
//        	     }else {
//        	        	//If sheet not existing
//        	        	if(i1==workbook1.getNumberOfSheets()-1) {
//        	        		CommonParaFun.logger.log(Level.SEVERE, "the  sheet isn't avilable");
//        	        		System.exit(0);
//        	        	}
//        	        }	        	      
//            	}
//            int cell=columnNo;
//            for(int i=0;i<rowCount1;i++) {
//	        		
//	        		//getting row values in both sheets
//	        		XSSFRow row1 = worksheet1.getRow(i);	               
//	                	 
//	                	//getting cell value from first sheet
//	                     id1 = row1.getCell(cell);
//	                     if (id1 != null) {
//	                         id1.setCellType(CellType.STRING);
//	                         cellValue1 = id1.getStringCellValue();
//	                         extractExpected(cellValue1);
//	                     }
//	                     
//	                 
//	                     //clearing cells variable
//	                     cellValue1="";
//	                  
//	                
//	        		
//	        	}
//         }else {
//        		CommonParaFun.logger.log(Level.SEVERE, "Excel File has no sheets");
//         }
//		
//	}


}
