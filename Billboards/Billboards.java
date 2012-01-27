import java.io.BufferedInputStream;
import java.util.Locale;
import java.util.Scanner;

public class Billboards {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		sc.useLocale(new Locale("en", "US"));
		int N = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < N; i++) {
			int w = sc.nextInt();
			int h = sc.nextInt();
			String[] words = sc.nextLine().trim().split("\\s+");
			int maxFont = 0;

			int[] wordLengths = new int[words.length];
			for (int j = 0; j < words.length; j++) {
				wordLengths[j] = words[j].length();
			}

			// find length of longest word
			int longest = wordLengths[0];
			for (int j = 1; j < words.length; j++) {
				if (wordLengths[j] > longest) {
					longest = wordLengths[j];
				}
			}
			int fontW = w / longest;
			int fontH = h / words.length;
			if (fontW <= fontH && fontW > maxFont) maxFont = fontW;
			else if (fontH < fontW && fontH > maxFont) maxFont = fontH;

			while (words.length > 1) {
				// find first index of shortest consecutive sum of words
				int index = 0;
				int shortest = 1001;
				for (int j = 0; j < words.length - 1; j++) {
					if (wordLengths[j] + wordLengths[j + 1] < shortest) {
						shortest = wordLengths[j] + wordLengths[j + 1];
						index = j;						
					}
				}

				// update words with combination
				String[] newWords = new String[words.length - 1];
				for (int k = 0; k < index; k++) {
					newWords[k] = words[k];
				}
				newWords[index] = words[index] + " " + words[index + 1];
				for (int k = index + 1; k < words.length - 1; k++) {
					newWords[k] = words[k + 1];
				}
				words = newWords;
				
				wordLengths = new int[words.length];
				for (int j = 0; j < words.length; j++) {
					wordLengths[j] = words[j].length();
				}

				// find length of longest word
				longest = wordLengths[0];
				for (int j = 1; j < words.length; j++) {
					if (wordLengths[j] > longest) {
						longest = wordLengths[j];
					}
				}

				fontW = w / longest;
				fontH = h / words.length;
				if (fontW <= fontH && fontW > maxFont) maxFont = fontW;
				else if (fontH < fontW && fontH > maxFont) maxFont = fontH;		
			}
			System.out.println("Case #" + (i + 1) + ": " + maxFont);
		}
	}
}