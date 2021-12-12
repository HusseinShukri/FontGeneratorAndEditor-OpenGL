package Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtility {
	
	public static void WriteToFile(String content, String path,String extention) throws IOException {
		File file = new File(path+"."+extention);
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
	}
}
