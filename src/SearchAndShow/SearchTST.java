package SearchAndShow;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Processings.In;
import Processings.TST;

public class SearchTST {
	
	private static File fileArray;

	public static void main(String[] args) {
		
		File dir = new File("TestFile/");
		File[] files = dir.listFiles();
		ArrayList<TST<Integer>> tst =new ArrayList<TST<Integer>>();
		
		tst = getTST(dir);
		
		
//		for(int i =0 ; i<tst.size();i++) {
//			if(tst.get(i).contains("hello"))
//			System.out.println(files[i].getName()+" "+tst.get(i).get("hello"));
//		}
		
		ArrayList<Integer> occurances = new ArrayList<Integer>();
		
		for(int i = 0 ; i < tst.size() ; i++) {
			if(tst.get(i).contains("hello")) {
				i=tst.get(i).get("hello");
				occurances.add(i);
			}else {
				occurances.add(0);
			}
		}
		System.out.println(occurances);
		occurances = quickSort(occurances);
		System.out.println(occurances);
	}
	
	
	


	//Returning tst arraylist
	private static ArrayList<TST<Integer>> getTST (File dir){
		ArrayList<TST<Integer>> tst = new ArrayList<TST<Integer>>();
		TST<Integer> temp;
		for(File f : dir.listFiles()) {
			temp = new TST<Integer>();
			In in = new In(f);
			String text = in.readAll();
			String token = null;
			StringTokenizer st = new StringTokenizer(text, " .,?!:;()<>[]\b\t\n\f\r\"\'\"");
			while(st.hasMoreTokens()) {
				
				token = st.nextToken();
				if(token.length()>=3) {
					int occurances = SearchB.getOccurances(token, text);
					//System.out.println(token + " " + occurances);
					temp.put(token, occurances);
					//System.out.println("temp "+temp);
				}				
			}
			
			tst.add(temp);			
		}		
		return tst;
	}
	
	public static ArrayList<Integer> quickSort(ArrayList<Integer> array) {

	    if (array.size() <= 1) {
	      return array;
	    }

	    int middle = (int) Math.ceil((double) array.size() / 2);
	    int pivot = array.get(middle);

	    ArrayList<Integer> less = new ArrayList<>();
	    ArrayList<Integer> greater = new ArrayList<>();

	    for (int i = 0; i < array.size(); i++) {
	      if (array.get(i) <= pivot) {
	        if (i == middle) {
	          continue;
	        }
	        less.add(array.get(i));
	      } else {
	        greater.add(array.get(i));
	      }
	    }

	    return concatenate(quickSort(less), pivot, quickSort(greater), array);
	  }

	  private static ArrayList<Integer> concatenate(ArrayList<Integer> less, int pivot, ArrayList<Integer> greater, ArrayList<Integer> list) {

	    list.clear();

	    for (int i = 0; i < less.size(); i++) {
	      list.add(less.get(i));
	    }
	    
	    list.add(pivot);

	    for (int i = 0; i < greater.size(); i++) {
	      list.add(greater.get(i));
	    }

	    return list;
	  
	  }
	
}
