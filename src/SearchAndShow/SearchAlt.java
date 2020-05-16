package SearchAndShow;

import Processings.SpellChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAlt {

	static ArrayList<String> key = new ArrayList<String>();
	static Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
	static Scanner sc = new Scanner(System.in);
	

	
	// return offset of first match or N if no match
	public static int search1(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (txt.charAt(i + j) != pat.charAt(j))
					break;
			}
			if (j == M)
				return i; // found at offset i
		}
		return N; // not found
	}

	// return offset of first match or N if no match
	public static int search2(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (txt.charAt(i) == pat.charAt(j))
				j++;
			else {
				i -= j;
				j = 0;
			}
		}
		if (j == M)
			return i - M; // found
		else
			return N; // not found
	}

	/***************************************************************************
	 * char[] array versions
	 ***************************************************************************/

	// return offset of first match or N if no match
	public static int search1(char[] pattern, char[] text) {
		int M = pattern.length;
		int N = text.length;

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (text[i + j] != pattern[j])
					break;
			}
			if (j == M)
				return i; // found at offset i
		}
		return N; // not found
	}

	// return offset of first match or N if no match
	public static int search2(char[] pattern, char[] text) {
		int M = pattern.length;
		int N = text.length;
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (text[i] == pattern[j])
				j++;
			else {
				i -= j;
				j = 0;
			}
		}
		
		if (j == M)
			return i - M; // found
		else
			return N; // not found
	}
	
	public int searchWord(File filePath, String p1) {
		int counter=0;
		//In in;
		// System.out.println("\nSearching in - "+filePath.getName());
		
		//in = new In(filePath);*/
//		System.out.println(in.readAll());
		String data="";
		try
    	{
    		BufferedReader _rederObject = new BufferedReader(new FileReader(filePath));
    		String line = null;
    		
              while ((line = _rederObject.readLine()) != null){
              //_m3.reset(line);
            	  data= data+line;
             
             }
              _rederObject.close();
      		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Exception:"+e);
    	}
		
			
			//System.out.println(filePath);
			//String line = " ";

			String txt = data;

			int offset1a = 0;

				for (int loc = 0; loc <= txt.length(); loc += offset1a + p1.length()) {

					offset1a = search1(p1, txt.substring(loc)); // + loc;
					if ((offset1a + loc) < txt.length()) {
						counter++;
						//System.out.println("'"+p1 +"' pattern is found at position " + (offset1a + loc));   //printing pos of word
					}
				}
				if(counter!=0)	{			
					System.out.println("\nFound in "+filePath.getName());	
					//System.out.println("Found "+counter+" times");
					
				}
				return counter;
	}
	
	 public static void sortValue(Hashtable<?, Integer> t,int occur){
			//word_prediction2 ww = new word_prediction2();

	       //Transfer as List and sort it
	       ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
	       
	      
	       
	       Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
	            return o1.getValue().compareTo(o2.getValue());
	        }});
	       //System.out.println(l);  // ascending order
	       Collections.reverse(l);
	       
	       
	       
	      // System.out.println(l);   //descending order
	       if(occur!=0) {
		       System.out.println("\n------Page Ranking Starts here------\n");
		       
		       int n = 5;
		       int j = 1;
				while (l.size() > j && n>0){
					System.out.println("("+j+") "+l.get(j)+" times ");
					j++;
					n--;
				}
	       }else {
	    	   //System.out.println("No word found");
	    	
	    	   
	       }
	       /*Hashtable<String,Integer> sortedHash = new Hashtable<String,Integer>();
	       int i =0;
	       while(!l.isEmpty()) {
	    	   System.out.print(l.get(i));
	    	   i++;
	       }*/
	       
	       
	       
	    }
	 
	 public void didYouMean(String p1) {
			try {
//				File _directory = new File("C:\\Users\\patel1nx\\Downloads\\text");
//				File[] _fileArray = _directory.listFiles();
//				for(int i=0;i<100;i++)
//				{
//					File filePath = _fileArray[i];
//					BufferedReader _rederObject = new BufferedReader(new FileReader(filePath));
//		    		String line = null;
////		    		System.out.println("hi");
//		              while ((line = _rederObject.readLine()) != null){
//		             
//		            	  StringTokenizer taker = new StringTokenizer(line);
//		            	  
//		            	  while (taker.hasMoreElements())
//		                  {
//		                  	key.add(taker.nextToken());
//		                  }
//		             
//		             }
//		              _rederObject.close();
//		        
//		              System.out.println("there are totally: " +key.size() + ""+ " words");			
					
//				}
				
				
//				for(int p = 0; p<key.size(); p++){
//	        	  
//	        	  numbers.put(key.get(p), editDistance(p1,key.get(p))-1);
//	          }System.out.println(numbers);
			
				
				// String to be scanned to find the pattern.
			      String line = " ";
			      String pattern3 = "[a-zA-Z]+";
			      //  String line = " this is a web link http://www.io.com/folder#ref with a folder";
			      //String pattern = "([\\w-]+://?|www[.])[^\\s()<>]+";
			     
			      // Create a Pattern object
			      Pattern r3 = Pattern.compile(pattern3);
			      // Now create matcher object.
			      Matcher m3 = r3.matcher(line);
			      int _fileNumber=0;
					try {
						File _directory = new File("TextFiles/");
						File[] _fileArray = _directory.listFiles();
						for(int i=0;i<100;i++)
						{
							findData(_fileArray[i],_fileNumber,m3,p1);
							_fileNumber++;
						}
						//System.out.println(numbers);
						
//					
				        Set keys = new HashSet();
				        Integer value =1;
				        Integer val = 0;
				        
				        System.out.println("-----------------Spell checker and suggestions start here------------------- ");
				        System.out.println("Did you mean? ");
				        for(Map.Entry entry: numbers.entrySet()){
				        	if(val == entry.getValue()) {
				        		//System.out.println("found");
				        		break;
					           
				            }
				        	else {
				        		
				        		 if(value==entry.getValue()){
						                //keys.add(entry.getKey()); //no break, looping entire hashtable
						            	System.out.print(entry.getKey()+"  ");
				                
				            }
				            
				        	}}
				        


					} catch (Exception e) {
						System.out.println("Exception:"+e);
					}
					finally
					{
						
					}
				
				
				
			}
			catch(Exception e){
				
			}
		}
			
		
		public static void findData(File _sourceFile,int fileNumber,Matcher _m3,String p1) throws FileNotFoundException,ArrayIndexOutOfBoundsException
	    {
	    	try
	    	{
	    		int i = 0;
	    		BufferedReader _rederObject = new BufferedReader(new FileReader(_sourceFile));
	    		String line = null;
//	    		System.out.println("URL Contain in File "+_sourceFile.getName());
	              while ((line = _rederObject.readLine()) != null){
	              _m3.reset(line);
	              while (_m3.find()) {
	            	  key.add(_m3.group());
//	                 System.out.println("Founded URL : "+_m3.group() + "\n");
	                }
	             }
	              _rederObject.close();
	              for(int p = 0; p<key.size(); p++){
	            	  
	              	  
	              	  numbers.put(key.get(p), editDistance(p1.toLowerCase(),key.get(p).toLowerCase()));
	                }
	    	}     
	    	catch(Exception e)
	    	{
	    		System.out.println("Exception:"+e);
	    	}
	    	
	    }
		
		public static int editDistance(String word1, String word2) {
			int len1 = word1.length();
			int len2 = word2.length();
		 
			// len1+1, len2+1, because finally return dp[len1][len2]
			int[][] dp = new int[len1 + 1][len2 + 1];
		 
			for (int i = 0; i <= len1; i++) {
				dp[i][0] = i;
			}
		 
			for (int j = 0; j <= len2; j++) {
				dp[0][j] = j;
			}
		 
			//iterate though, and check last char
			for (int i = 0; i < len1; i++) {
				char c1 = word1.charAt(i);
				for (int j = 0; j < len2; j++) {
					char c2 = word2.charAt(j);
		 
					//if last two chars equal
					if (c1 == c2) {
						//update dp value for +1 length
						dp[i + 1][j + 1] = dp[i][j];
					} else {
						int replace = dp[i][j] + 1;
						int insert = dp[i][j + 1] + 1;
						int delete = dp[i + 1][j] + 1;
		 
						int min = replace > insert ? insert : replace;
						min = delete > min ? min : delete;
						dp[i + 1][j + 1] = min;
					}
				}
			}
		 
			return dp[len1][len2];
		}

	public static void mainImplementation(String pattern) {
		SearchAlt bfm = new SearchAlt();
		
		
		Hashtable<String,Integer> occurrenceTable = new Hashtable<String,Integer>();
		
		SpellChecker spc = new SpellChecker();
		spc.askDict(pattern);
		long _fileNumber = 0;
		int occur=0;
		int nn=0;
		try {
			File _directory = new File("TextFiles/");
			
			File[] _fileArray = _directory.listFiles();
			for(int i=0;i<102;i++)
			{
				//findData(_fileArray[(int) i],_fileNumber,m3,p1);
				occur=bfm.searchWord(_fileArray[i],pattern);
				
				System.out.println("Found "+occur+" times");
				occurrenceTable.put(_fileArray[i].getName(), occur);
				if(occur!=0) {
					nn++;
				}
							
				_fileNumber++;
			}
			
			if(nn==0) {
				//System.out.println("Not found.. ");
				/*System.out.println("---------------------------------------------------");
		        System.out.println("Searching in webpages.....");
				bfm.didYouMean(pattern);*/
			}
			sortValue(occurrenceTable,nn);
		} catch (Exception e) {
			System.out.println("Spell Check is complete");
		}
		finally
		{
				
		} 
		//qsd.quickSelect(a, k);
	}
		//bfm.demo(filePath,p1);
	

	public static void main(String[] args) {
		//word_prediction2 ww = new word_prediction2();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter the key word to search: ");
		String p1= sc.nextLine();
		mainImplementation(p1);
	}
	// Method to find all URL's containing the string searched
	}

