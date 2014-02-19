package main.java;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class XPathSample {
    public static void main (String[] args) {

        try {
            Document doc = parseDocument("books.xml");
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList bookList = (NodeList) xpath.evaluate("//inventory/book", doc, XPathConstants.NODESET);

            for (int i = 0; i < bookList.getLength(); i++) {
                Node book = bookList.item(i);

                System.out.println("book " + i);

                String title = (String) xpath.evaluate("./title/text()", book, XPathConstants.STRING);
                System.out.println("  title = " + title);

                String author = (String) xpath.evaluate("./author/text()", book, XPathConstants.STRING);
                System.out.println("  author = " + author);

                String publisher = (String) xpath.evaluate("./publisher/text()", book, XPathConstants.STRING);
                System.out.println("  publisher = " + publisher);

                String isbn = (String) xpath.evaluate("./isbn/text()", book, XPathConstants.STRING);
                System.out.println("  isbn = " + isbn);

                String price = (String) xpath.evaluate("./price/text()", book, XPathConstants.STRING);
                System.out.println("  price = " + price);

            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private static Document parseDocument(String path) {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        Document doc = null;
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            doc = builder.parse(path);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
