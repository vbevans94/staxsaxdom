package ua.org.cofriends.gem.parser;

import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamReader;

import org.xml.sax.Attributes;

public class AttrsAdapter implements Attributes {

	private final XMLStreamReader reader;
	private final Map<String, String> attrs = new HashMap<>(); 

	public AttrsAdapter(XMLStreamReader reader) {
		this.reader = reader;
		for (int i = 0; i < reader.getAttributeCount(); i++) {
			attrs.put(reader.getAttributeName(i).toString(), reader.getAttributeValue(i));
		}
	}
	
	@Override
	public int getLength() {
		return reader.getAttributeCount();
	}

	@Override
	public String getURI(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocalName(int index) {
		return reader.getLocalName();
	}

	@Override
	public String getQName(int index) {
		return reader.getLocalName();
	}

	@Override
	public String getType(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getValue(int index) {
		return reader.getAttributeName(index).toString();
	}

	@Override
	public int getIndex(String uri, String localName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getIndex(String qName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getType(String uri, String localName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getType(String qName) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public String getValue(String uri, String localName) {
		return reader.getAttributeValue(uri, localName);
	}

	@Override
	public String getValue(String qName) {
		return attrs.get(qName);
	}

}
