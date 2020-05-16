package SearchAndShow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Processings.*;

public class SearchBackup {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		
		File dir = new File("TestFile/");
		System.out.println(dir.isDirectory());
	
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("indexes")));
		
		HashMap<String, HashMap<String,Integer>> god = (HashMap<String, HashMap<String,Integer>>)ois.readObject();
		for(File f : dir.listFiles()) {
			
			if( god.get(f.getName()).containsKey("html"))
				System.out.println(god.get(f.getName()).get("html"));
//			else
//				System.out.println("notfound");
		}
	}
}