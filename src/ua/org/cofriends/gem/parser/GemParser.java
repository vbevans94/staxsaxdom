package ua.org.cofriends.gem.parser;

public abstract class GemParser {

	protected final String fileName;
	
	public GemParser(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Parses files and prints the output.
	 */
	public abstract void parse();
	
}
