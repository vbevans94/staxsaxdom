package ua.org.cofriends.gem.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.org.cofriends.gem.entity.Gem;
import ua.org.cofriends.gem.entity.Gem.Preciousness;
import ua.org.cofriends.gem.entity.Gem.VisualParams;

public class DOMGemParser extends GemParser {

	public DOMGemParser(String fileName) {
		super(fileName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse() {
		try {
			// Get the DOM Builder
			DocumentBuilder builder = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder();

			// Load and Parse the XML document
			// document contains the complete XML as a Tree.
			Document document = builder.parse(ClassLoader
					.getSystemResourceAsStream(fileName));

			List<Gem> gems = new ArrayList<>();
			// Iterating through the nodes and extracting the data.
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				// We have encountered an <gem> tag.
				Node gemNode = nodeList.item(i);
				if (gemNode instanceof Element) {
					Gem gem = new Gem();
					gem.setId(gemNode.getAttributes().getNamedItem("id")
							.getNodeValue());

					NodeList gemChildNodes = gemNode.getChildNodes();
					for (int j = 0; j < gemChildNodes.getLength(); j++) {
						Node gemChildNode = gemChildNodes.item(j);
						
						if (gemChildNode instanceof Element) {
							String content = gemChildNode.getLastChild()
									.getTextContent().trim();
							switch (gemChildNode.getNodeName()) {
							case "name":
								gem.setName(content);
								break;
							case "preciousness":
								gem.setPreciousness(Preciousness.of(content));
								break;
							case "origin":
								gem.setOrigin(content);
								break;
							case "visual-params":
								VisualParams visualParams = new VisualParams();
								NodeList paramNodes = gemChildNode
										.getChildNodes();
								for (int k = 0; k < paramNodes.getLength(); k++) {
									Node paramNode = paramNodes.item(k);
									if (paramNode instanceof Element) {
										String c = paramNode.getLastChild()
												.getTextContent().trim();
										switch (paramNode.getNodeName()) {
										case "color":
											visualParams.setColor(c);
											break;
										case "transparency":
											visualParams
													.setTransparency(Integer.parseInt(c
															.replace("%", "")));
											break;
										case "corners":
											visualParams.setCorners(Integer
													.parseInt(c));
											break;
										}
									}
								}
								gem.setVisualParams(visualParams);
								break;
							case "value":
								gem.setValue(Integer.parseInt(content));
								break;
							}
						}
					}
					gems.add(gem);
				}
			}
			// Printing the Gems populated.
			for (Gem emp : gems) {
				System.out.println(emp);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
}