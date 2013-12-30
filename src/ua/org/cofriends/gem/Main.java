package ua.org.cofriends.gem;

import ua.org.cofriends.gem.parser.DOMGemParser;
import ua.org.cofriends.gem.parser.SaxGemParser;
import ua.org.cofriends.gem.parser.StaxGemParser;
import ua.org.cofriends.gem.validator.XSDValidator;

public class Main {

	private final static String XML_NAME = "gem.xml";
	private final static String XSD_NAME = "schema.xsd";
	private final static String DIR_NAME = "src/";
	
	public static void main(String[] args) {
		if (XSDValidator.validateXMLSchema(DIR_NAME + XSD_NAME, DIR_NAME + XML_NAME)) {
			System.out.println(XML_NAME + " is valid due to " + XSD_NAME);
		}
		new DOMGemParser(XML_NAME).parse();
		new StaxGemParser(XML_NAME).parse();
		new SaxGemParser(XML_NAME).parse();
	}
	
}
