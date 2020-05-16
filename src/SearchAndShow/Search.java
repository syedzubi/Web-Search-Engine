package SearchAndShow;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import Processings.*;

public class Search {

	private static boolean ASC = true;
	private static boolean DESC = false;
	private static HashMap<String, HashMap<String,Integer>> god;
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {


		File dir = new File("TestFile/");
		//System.out.println(dir.isDirectory());

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("indexes")));

		god = (HashMap<String, HashMap<String,Integer>>)ois.readObject();



		Map<String, Integer> unsorted = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choice = null;

		System.out.println("Enter choice:"
				+ "\n1. Spell Check"
				+ "\n2. Search");
		String ch = br.readLine();

		do {
			switch (ch) {

			case "1":{
				System.out.print("SpellCheck: ");
				SpellChecker sc = new SpellChecker();
				sc.askDict(br.readLine());
			}
			case "2":{
				do {
					System.out.println("\nEnter string to search: ");
					String string = br.readLine();
					int existentcount=1;


					System.out.println(string);
					int total;
					for(File f : dir.listFiles()) {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
						total = (int) bufferedReader.lines().filter((line)-> line.indexOf(string)!=-1).count();
						unsorted.put(f.getName(), total );
					}
					if (existentcount>0) {
						int i=1;
						File[] top10 = new File[10];
						for(String s : unsorted.keySet()) {
							if(unsorted.get(s)>0) {
								System.out.println(i + ") " + s + " " + unsorted.get(s));
								top10[i-1]=new File(s);
								i++;
								if(i>10) break;
							}
						}
						System.out.println("Enter Choice(1-10 to open pages)(n to exit)");
						choice = br.readLine();

						do {
							try {
								int x = Integer.parseInt(choice);
							//	BufferedReader bufferedReader = new BufferedReader(new FileReader("TestFile/"+top10[x-1]));

								File html = new File ("TestFile/"+top10[x-1]);
								System.out.println("xxxEnter n to stop and numbers to open files(<=10)");
								Desktop.getDesktop().open(html);
								System.out.println("xxxEnter n to stop and numbers to open files(<=10)");
							}catch (NumberFormatException nfe){
								if(!choice.equalsIgnoreCase("n"))
									System.out.println("Please enter a number (1-10) n to exit");
							}finally {

								if(!choice.equalsIgnoreCase("n"))
									choice = br.readLine();
							}
						}while(!choice.equalsIgnoreCase("n"));

						System.out.println("Do you want to search again?(Y/N)");
						choice = br.readLine();
					}else {
						System.out.println("Not fount in any files, Enter y to search new or n to exit");
						choice = br.readLine();
					}
				}while(choice.equalsIgnoreCase("y"));
			}
			}
			System.out.println("Enter choice:"
					+ "\n1. Spell Check"
					+ "\n2. Search"
					+ "\n3. Exit");
			ch = br.readLine();
			
		}while(!ch.equals("3"));
		System.out.println("Done!");

	}


	//custom method to search string with multiple words
	private static Map<String , Integer> getMap(File dir , String search){
		Map<String, Integer> map = new HashMap<String, Integer>();
		String token;
		StringTokenizer st = new StringTokenizer(search, " ");

		int total;
		for(File f : dir.listFiles()) {
			total =0 ;		
			while(st.hasMoreTokens()) {
				token = st.nextToken();
				System.out.println(token);
				if(god.get(f.getName()).containsKey(token)) {
					System.out.println(god.get(f.getName()).get(token));
					total += god.get(f.getName()).get(token);
				}
				System.out.println(total);
			}
			map.put(f.getName(), total );
		}
		return map;
	}

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
	{
		List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

		// Sorting the list based on values
		list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
				? o1.getKey().compareTo(o2.getKey())
						: o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
						? o2.getKey().compareTo(o1.getKey())
								: o2.getValue().compareTo(o1.getValue()));
		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	}
	private static void printMap(Map<String, Integer> map)
	{
		map.forEach((key, value) -> System.out.println("Key : " + key + " Value : " + value));
	}
}