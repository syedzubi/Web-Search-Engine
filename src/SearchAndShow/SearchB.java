package SearchAndShow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Processings.In;
import Processings.KMP;

public class SearchB {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		HashMap <String , HashMap<String,Integer>> god = new HashMap<String, HashMap<String,Integer>>();
		File dir = new File("TestFile/");
		
			god = getTrident(dir);
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("indexes")));
			oos.writeObject(god);
			oos.flush();
			oos.close();
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("indexes")));
			System.out.println("Saved in indexes file using TST and KMP");
			
			HashMap<String, HashMap<String,Integer>> obj = (HashMap<String, HashMap<String,Integer>>)ois.readObject();
			
//			for(File f : dir.listFiles()) {
//				
//				if( god.get(f.getName()).containsKey("html"))
//					System.out.println(obj.get(f.getName()).get("html"));
////				else
////					System.out.println("notfound");
//			}
	}
	
	public static HashMap <String , HashMap<String,Integer>> getTrident(File dir){
		HashMap <String , HashMap<String,Integer>> god = new HashMap<String, HashMap<String,Integer>>();
		HashMap<String,Integer> temp;
		for(File file : dir.listFiles()) {
			temp = new HashMap<String,Integer>();
			In in = new In(file);
			String text = in.readAll();
			String token = null;
			//System.out.println(file.getName()+" "+text);
			StringTokenizer st = new StringTokenizer(text, " .,?!:;()<>[]\b\t\n\f\r\"\'\"");
			//System.out.println(st);
			while(st.hasMoreTokens()) {
				
				token = st.nextToken();
				if(token.length()>=3) {
					int occurances = getOccurances(token, text);
					//System.out.println(token + " " + occurances);
					temp.put(token, occurances);
					//System.out.println("temp "+temp);
				}
			}
			god.put(file.getName(), temp);
		}
		//System.out.println(god);
		return god;
	}
	
	public static int getOccurances(String pattern , String text) {
		
//		int occurances = 0;
//		Pattern pat = Pattern.compile(pattern);
//		Matcher matcher = pat.matcher(text);
//		while (matcher.find()) {
//			occurances++;
//		}
//		return occurances;
		
		ArrayList<Integer> occurances = getMatchingKeysKMP(pattern, text);
		return occurances.size();
	}
	
	private static ArrayList<Integer> getMatchingKeysKMP(String pat, String txt) {
        KMP kmp = new KMP(pat);
        int offsetPos = 0, searchPos = 0;
        ArrayList<Integer> patternPosition = new ArrayList<Integer>();
        int patternLength = pat.length();
        String subStr;
        while ((offsetPos <= (txt.length() - patternLength + 1))) {
            subStr = txt.substring(offsetPos);
            searchPos = kmp.search(txt);
            if (searchPos == subStr.length())
                break;
            patternPosition.add(offsetPos + searchPos);
            offsetPos = offsetPos + searchPos + patternLength;
        }
        if (patternPosition.size() != 0)
            return patternPosition;
        else
            patternPosition.add(searchPos);
        return patternPosition;
    }

}
