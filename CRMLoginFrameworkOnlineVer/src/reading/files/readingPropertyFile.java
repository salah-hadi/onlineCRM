package reading.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**Sample on reading property file
 *  To be overload later*/
public class readingPropertyFile {
	InputStream is;
	public void readingFile() throws IOException{
		try {
			Properties p=new Properties();
			String fileName="data.properties";
			is=getClass().getClassLoader().getResourceAsStream(fileName);
			if(is!=null) {
				p.load(is);
			}else {
				throw new FileNotFoundException("File Not found:"+fileName);
			}
			String userValue=p.getProperty("user");
			String passValue=p.getProperty("password");
			
			System.out.println("Username: "+userValue);
			System.out.println("Password: "+passValue);
			
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			is.close();
		}
	}

}
