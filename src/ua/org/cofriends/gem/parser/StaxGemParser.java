package ua.org.cofriends.gem.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.SAXException;

import ua.org.cofriends.gem.entity.Gem;
import ua.org.cofriends.gem.parser.SaxGemParser.SAXHandler;

public class StaxGemParser extends GemParser {
	
	public StaxGemParser(String fileName) {
		super(fileName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse() {
		try {
			SAXHandler handler = new SAXHandler();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(ClassLoader
					.getSystemResourceAsStream(fileName));

			while (reader.hasNext()) {
				int event = reader.next();

				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					handler.startElement(fileName, reader.getLocalName(), reader.getLocalName(), new AttrsAdapter(reader));
					break;

				case XMLStreamConstants.CHARACTERS:
					char[] chars = reader.getText().toCharArray();
					handler.characters(chars, 0, chars.length);
					break;

				case XMLStreamConstants.END_ELEMENT:
 					handler.endElement(fileName, reader.getLocalName(), reader.getLocalName());
					break;
				}
			}
			for (Gem gem : handler.getGems()) {
				System.out.println(gem);
			}
		} catch (XMLStreamException | SAXException e) {
			e.printStackTrace();
		}
	}
}