package sjsu.Kuroshima.cs146.project3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {

	public static void main(String[] args) {
		RedBlackTree Dictionary = new RedBlackTree();

		try (Scanner in = new Scanner(new FileInputStream("dictionary.txt"), "UTF-8")) {
			long start = System.currentTimeMillis();
			int inputSize = 0;
			while (in.hasNext()) {
				Dictionary.insert(in.next());
				inputSize++;
			}
			long end = System.currentTimeMillis();
			long elapsed = end - start;
			System.out.println("Insert Time: " + elapsed + "ms, Input Size:" + inputSize);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try (Scanner in = new Scanner(new FileInputStream("poem1.txt"), "UTF-8")) {
			int found = 0;
			int counter = 0;
			long start = System.currentTimeMillis();
			while (in.hasNext()) {
				String s = in.next();
				s = s.replaceAll("(\\.|,|\\?|!|’\\w*)", "");
				s = s.toLowerCase();
				if (Dictionary.lookup(s) != null) {
					found++;
				}
				counter++;
			}
			long end = System.currentTimeMillis();
			long elapsed = end - start;
			System.out.println("Search Time: " + elapsed + "ms Found: " + found + "/" + counter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
