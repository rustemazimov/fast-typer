package models;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	private static Utils instance;

	private final static byte DISCOUNT_FOR_WRONGS_ANSWERS = 2;

	public static Utils getInstance() {
		if (instance == null)
		{
			instance = new Utils();
		}
		return instance;
	}
	public int findAnswersScoreCount(String userAnswer, String[] randomWords) {
		String[] userWords = splitIntoWords(userAnswer);
		int scoreCount = 0;
		int
			minLength = 0,
			maxLength = 0;

		if (userWords.length == randomWords.length)
		{
			for (int i = 0; i < randomWords.length; i++) {
				scoreCount += findAnswersScoreCountForWord(userWords[i], randomWords[i]);
			}
		}
		else
		{
			if (userWords.length < randomWords.length)
			{
				minLength = userWords.length;
				maxLength = randomWords.length;
			}
			else
			{
				minLength = randomWords.length;
				maxLength = userWords.length;
			}

			for (int i = 0; i < minLength; i++) {
				scoreCount += findAnswersScoreCountForWord(userWords[i], randomWords[i]);
			}

		}
		return scoreCount - DISCOUNT_FOR_WRONGS_ANSWERS * (maxLength - minLength);
	}
	private int findAnswersScoreCountForWord(String userWord, String randomWord) {
		int scoreCount = 0;
		int
			minLength = 0,
			maxLength = 0;

		if (userWord.length() == randomWord.length())
		{
			for (int j = 0; j < randomWord.length(); j++) {
				if (userWord.charAt(j) == randomWord.charAt(j))
				{
					scoreCount++;
				}
				else
				{
					scoreCount -= DISCOUNT_FOR_WRONGS_ANSWERS;
				}
			}
		}
		else
		{
			if (userWord.length() < randomWord.length())
			{
				minLength = userWord.length();
				maxLength = randomWord.length();
			}
			else
			{
				minLength = randomWord.length();
				maxLength = userWord.length();
			}
			for (int i = 0; i < minLength; i++) {
				if (userWord.charAt(i) == randomWord.charAt(i))
				{
					scoreCount++;
				}
				else
				{
					scoreCount -= DISCOUNT_FOR_WRONGS_ANSWERS;
				}
			}
		}
		return scoreCount - DISCOUNT_FOR_WRONGS_ANSWERS * (maxLength - minLength);
	}

	public String[] splitIntoWords(String txt) {
		txt.trim();//Clear spaces from the beginning and end
		List<String> listOfWords = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < txt.length(); i++) {
			char c = txt.charAt(i);
			if (c != ' ')
			{
				builder.append(c);
			}
			else if (builder.length() != 0)
			{
				listOfWords.add(builder.toString());
				builder.delete(0, builder.length());
			}
		}
		if (builder.length() != 0)
		{
			listOfWords.add(builder.toString());
		}
		return listOfWords.toArray(new String[listOfWords.size()]);
	}
}
