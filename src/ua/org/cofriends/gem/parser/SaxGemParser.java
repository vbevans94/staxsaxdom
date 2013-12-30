package ua.org.cofriends.gem.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.org.cofriends.gem.entity.Gem;
import ua.org.cofriends.gem.entity.Gem.Preciousness;
import ua.org.cofriends.gem.entity.Gem.VisualParams;

public class SaxGemParser extends GemParser {

	public SaxGemParser(String fileName) {
		super(fileName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse() {
		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse(ClassLoader.getSystemResourceAsStream(fileName),
					handler);

			// Printing the list of employees obtained from XML
			for (Gem gem : handler.gems) {
				System.out.println(gem);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Handler for SAX Events.
	 */
	public static class SAXHandler extends DefaultHandler {
		private List<Gem> gems = new ArrayList<>();
		private Gem gem = null;
		private VisualParams visualParams = null;
		private String content = null;

		@Override
		// Triggered when the start of tag is found.
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			switch (qName) {
			// Create a new Gem object when the start tag is found
			case "gem":
				gem = new Gem();
				gem.setId(attributes.getValue("id"));
				break;
				
			case "visual-params":
				visualParams = new VisualParams();
				break;
				
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			switch (qName) {
			// Add the employee to list once end tag is found
			case "gem":
				gems.add(gem);
				break;
			case "visual-params":
				gem.setVisualParams(visualParams);
				break;
			// For all other end tags the employee has to be updated.
			case "name":
				gem.setName(content);
				break;
			case "origin":
				gem.setOrigin(content);
				break;
			case "value":
				gem.setValue(Integer.parseInt(content));
				break;
			case "preciousness":
				gem.setPreciousness(Preciousness.of(content));
				break;
			case "color":
				visualParams.setColor(content);
				break;
			case "corners":
				visualParams.setCorners(Integer.parseInt(content));
				break;
			case "transparency":
				visualParams.setTransparency(Integer.parseInt(content.replace("%", "")));
				break;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			content = String.copyValueOf(ch, start, length).trim();
		}
		
		/**
		 * @return parsed gems
		 */
		public List<Gem> getGems() {
			return gems;
		}

	}
}