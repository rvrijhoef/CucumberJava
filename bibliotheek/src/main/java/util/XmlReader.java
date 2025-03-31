package util;

import objects.Boek;
import objects.Klant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class XmlReader {
    private static final Logger logger = Logger.getLogger(XmlReader.class.getName());

    public List<Boek> loadBooks() {
        List<Boek> boeken = new ArrayList<>();
        try {
            URL url = getClass().getClassLoader().getResource("books.xml");
            File xml = new File(url.toURI());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);
            doc.getDocumentElement().normalize();

            logger.fine("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("book");

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();



            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                logger.fine("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    NodeList nl = eElement.getChildNodes();
                    String author = "";
                    String title = "";
                    String genre = "";
                    BigDecimal price = BigDecimal.valueOf(0.0);
                    Date publishdate = Date.from(Instant.now());
                    String description = "";
                    String isbn = "";
                    int amount = 0;

                    for(int i = 0; i < nl.getLength();i++){
                        Node n = nl.item(i);
                        switch(n.getNodeName()) {
                            case "author":
                                author = n.getTextContent();
                                break;
                            case "title":
                                title = n.getTextContent();
                                break;
                            case "genre":
                                genre = n.getTextContent();
                                break;
                            case "price":
                                price = new BigDecimal(n.getTextContent());
                                break;
                            case "publishdate":
                                publishdate = Date.from(Instant.parse(n.getTextContent()));
                                break;
                            case "description":
                                description = n.getTextContent();
                                break;
                            case "isbn":
                                isbn = n.getTextContent();
                                break;
                            case "amount":
                                amount = Integer.parseInt(n.getTextContent());
                                break;


                            default:
                                break;
                        }
                    }
                    Boek boek = new Boek(title,author,genre,price,publishdate,description,isbn,amount);

                    boeken.add(boek);
                }
            }

        } catch (Exception e) {

        }
        return boeken;
    }

    public List<Klant> loadCustomers() {
        List<Klant> klants = new ArrayList<>();
        try {
            URL url = getClass().getResource("customers.xml");
            File xml = new File(url.toURI());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);
            doc.getDocumentElement().normalize();

            logger.fine("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("book");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                logger.fine("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

//                    Customer cust = new Customer(eElement.getAttribute("title"),
//                            eElement.getAttribute("author"),
//                            eElement.getAttribute("genre"),
//                            BigDecimal.valueOf(eElement.getAttribute("price")),
//                            Date.from(Instant.parse(eElement.getAttribute("publishdate"))),
//                            eElement.getAttribute("description"),
//                            eElement.getAttribute("isbn"),
//                            Integer.parseInt(eElement.getAttribute("amount")));
//
//                    customers.add(cust);
                }
            }

        } catch (Exception e) {

        }
        return klants;
    }
}