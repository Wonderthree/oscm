package test;

public class CountSyllables {

	public static void main(String[] args) {
		System.out.printf("The word %s has %d syllable(s)\n", "hello",
				countSyllables("hello"));
		System.out.printf("The word %s has %d syllable(s)\n", "constructor",
				countSyllables("constructor"));
		System.out.printf("The word %s has %d syllable(s)\n", "quitessence",
				countSyllables("quintessence"));
		
	}
	
	/**
     * Counts the syllables in the word.
     * @return the syllable count
	 */
	public static int countSyllables(String text)
	{
		text = text.toLowerCase();
		int count = 0;
		int end = text.length() - 1;
		if (end < 0)
		{
			return 0;    // The empty string has no syllables
		}

		// An e at the end of the word doesn't count as a vowel
		if (text.endsWith("e"))
		{
			end--;
		}

		boolean insideVowelGroup = false;
		for (int i = 0; i <= end; i++)
		{
			String letter = text.substring(i, i + 1);
			// if letter is a vowel
			if ("aeiou".contains(letter))
			{
				// letter is a vowel
				if (!insideVowelGroup)
				{
					// Start of new vowel group
					count++;
					insideVowelGroup = true;
				}
			} else { // else letter is not a vowel
				insideVowelGroup = false;
			}
		}

		// Every word has at least one syllable
		if (count == 0)
		{
			count = 1;
		}

		return count;
	}

}
