import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary
{
	private HashMap<String, List<String>> dictEntries= new HashMap<String, List<String>>();

	/**
	 * Adds list of string as translations for given word.
	 *
	 * If a translation exists already, we will not add it a second time.
	 * Calling this method with an existing translation key will append to
	 * existing translations - not overwrite!
	 */
	public void addTranslations(String from, String[] to) {
		// New translation
		if (!this.dictEntries.containsKey(from)) {
			this.dictEntries.put(from, new ArrayList<String>());
		}

		List<String> translations = this.dictEntries.get(from);
		for (String translation : to) {
			if (translations.contains(translation)) {
				System.err.println(String.format("%s -> %s already in dictionary, skipping.", from, translation));
			} else {
				translations.add(translation);
			}
		}
	}

	/**
	 * Gets translations by translation key.
	 *
	 * Raises a WordNotFound exception if key not found.
	 */
	public List<String> translate(String key) throws WordNotFoundException {
		if (this.dictEntries.containsKey(key)) {
			return this.dictEntries.get(key);
		} else {
			throw new WordNotFoundException(String.format("No translation for %s found", key));
		}
	}
}
