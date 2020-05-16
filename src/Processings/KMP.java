package Processings;

import Processings.In;
import Processings.KMP;

/***************************************************************
*
*  Compilation:  javac KMP.java
*  Execution:    java KMP pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using the
*  KMP algorithm.
*
*  % java KMP abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java KMP rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab
*
*  % java KMP bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
*
*  % java KMP rabrabracad abacadabrabracabracadabrabrabracad 
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad
*
*  % java KMP abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class KMP {
   private final int R;       // the radix
   private int[][] dfa;       // the KMP automoton

   private char[] pattern;    // either the character array for the pattern
   private String pat;        // or the pattern string

   // create the DFA from a String
   public KMP(String pat) {
       this.R = 256;
       this.pat = pat;

       // build DFA from pattern
       int M = pat.length();
       dfa = new int[R][M]; 
       dfa[pat.charAt(0)][0] = 1; 
       for (int X = 0, j = 1; j < M; j++) {
           for (int c = 0; c < R; c++) 
               dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
           dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
           X = dfa[pat.charAt(j)][X];     // Update restart state. 
       } 
   } 

   // create the DFA from a character array over R-character alphabet
   public KMP(char[] pattern, int R) {
       this.R = R;
       this.pattern = new char[pattern.length];
       for (int j = 0; j < pattern.length; j++)
           this.pattern[j] = pattern[j];

       // build DFA from pattern
       int M = pattern.length;
       dfa = new int[R][M]; 
       dfa[pattern[0]][0] = 1; 
       for (int X = 0, j = 1; j < M; j++) {
           for (int c = 0; c < R; c++) 
               dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
           dfa[pattern[j]][j] = j+1;      // Set match case. 
           X = dfa[pattern[j]][X];        // Update restart state. 
       } 
   } 

   // return offset of first match; N if no match
   public int search(String txt) {

       // simulate operation of DFA on text
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           j = dfa[txt.charAt(i)][j];
       }
       if (j == M) return i - M;    // found
       return N;                    // not found
   }

   
   // return offset of first match; N if no match
	public int search(String pat, String txt) {

		// simulate operation of DFA on text
		int M = pat.length();
		int N = txt.length();
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if (j == M)
			return i - M; // found
		return N; // not found
	}

   // return offset of first match; N if no match
   public int search(char[] text) {

       // simulate operation of DFA on text
       int M = pattern.length;
       int N = text.length;
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           j = dfa[text[i]][j];
       }
       if (j == M) return i - M;    // found
       return N;                    // not found
   }


   // test client
   public static void main(String[] args) {
       //String pat = args[0];
       //String txt = args[1];
	   
       // There are two implmentations of search
	   // one is with String and the other is an array of chars
	   
	   	System.out.println("KMP algorithm: ");
   		In in = new In("Hard disk.txt");
   		String txt = in.readAll();
   		int offset=0;
       
       
   		//Storing all strings in an array so all task can be done in one loop
   		String[] pat = new String[6];
   		pat[0]="hard";
   		pat[1]="disk";
   		pat[2]="hard disk";
   		pat[3]="hard drive";
   		pat[4]="hard dist";
   		pat[5]="xltpru";

   		//Initializing KMP objects
   		KMP[] kmp = new KMP[6];
   		for (int j = 0 ; j < pat.length ; j++) {
   			kmp[j] = new KMP(pat[j]);
   		}
   		
   		long[] startTime = new long[6];
  	   	long[] endTime = new long[6]; 	   
  	   	long[] totalTime = new long[6];
  	   	for(int i = 0 ; i<totalTime.length ; i++) totalTime[i]=0;
  	   	
  	   	//Showing offsets for all strings
  	   	for (int j = 0 ; j<pat.length ; j++) {
  	   		System.out.println("\n-----Showing offsets for string: " + pat[j]+"-----");
  	   		for(int location = 0 ; location <txt.length() ; location += offset + pat[j].length()) {
  	   			offset = kmp[j].search( pat[j] , txt.substring(location)); //+location
  	   			if((offset+location) < txt.length())
 				   System.out.println(pat[j]+" At position " + (offset+location));
  	   		} 
  	   	}
       
  	   	//Calculating average for strings not showing offsets
  	   	for(int j =0 ; j<6 ; j++) {
  	   		//100 iterations for string pat[j]
  	   		for(int i=0 ; i<100 ; i++){	
  		   
  	   			startTime[j] = System.nanoTime();
  	   			for(int location = 0 ; location <txt.length() ; location += offset + pat[j].length() ) {
  	   				offset = kmp[j].search(pat[j],txt.substring(location));
  	   			} 			   
  	   			endTime[j]=System.nanoTime();
  	   			totalTime[j]+=endTime[j]-startTime[j];
  	   		} 		 
  	   	}
  	   	
  	   	//Showing averages for 100 searches of respective strings
  	   	System.out.println("\nShowing average times:\n");
  	   	for (int j = 0 ; j <pat.length ; j++)
  	   		System.out.println("Average for string: " + pat[j]+ " is "+ totalTime[j]/100 + "ns");
   }
}

