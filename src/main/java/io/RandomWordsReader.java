package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomWordsReader {
	private static RandomWordsReader instance;

	/**
	 * Random class instance or
	 * finding random index for random word
	 */
	private Random random = new Random();

	/**
	 * 	Array contains all words
	 * 	that a random word can be got from
	 */
	private String[] words;

	public static RandomWordsReader getInstance() {
		if (instance == null)
		{
			instance = new RandomWordsReader();
		}
		return instance;
	}

	private RandomWordsReader() {
		this.words = readArrayOfWordsFromResource();
	}

	public String[] findRandomWordsText(int wordsCount) {
		String[] randomWords = new String[wordsCount];
		for (int i = 0; i < wordsCount; i++) {
			randomWords[i] = findRandomWord();
		}
		return randomWords;
	}
	private String findRandomWord() {
		return words[random.nextInt(Integer.MAX_VALUE % words.length)];
	}

	private String[] readArrayOfWordsFromResource() {
		List<String> list = new ArrayList<String>();
		Scanner scanner = new Scanner(new InputStreamReader(getClass().getResourceAsStream("../words.txt")));
		while (scanner.hasNext())
		{
			list.add(scanner.next());
		}
		return list.toArray(new String[list.size()]);

	}
}
