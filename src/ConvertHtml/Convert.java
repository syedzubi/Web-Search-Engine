package ConvertHtml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Convert {
	
	public static void main(String args[]) throws IOException {
		File file = new File("WebPages/www.w3schools.com/");
		File txtDir = new File("TextFiles/");
		if(!txtDir.exists())
			txtDir.mkdir();
		ConvertToText(file);
		System.out.println(txtDir.listFiles().length);
	}
	
	private static void ConvertToText(File file) throws IOException{		
		
		
		if(file.isDirectory()) {
			File[] listOfFiles = file.listFiles();
			for (File f : listOfFiles) {
				ConvertToText(f);
			}
		}else if (file.isFile()) {
			if(getFileExtension(file).equalsIgnoreCase(".html")) {
				Document document = Jsoup.parse(file, "UTF-8" , file.getAbsolutePath());
				if(!document.text().isEmpty()) {
				//Elements title = document.select("title");
					File txtFile = new File ("TextFiles/"+file.getName()+".txt");
					BufferedWriter bw = new BufferedWriter(new FileWriter(txtFile));
					bw.write(file.getAbsolutePath()+"\n\n");
					bw.newLine();
					bw.write(document.text().toLowerCase());
					System.out.println("Converted:" + file.getAbsolutePath()); 
					bw.close();
				}
			}
		}
	}
	
	
	//To convert only HTML files
	private static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}

}
