import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 * Created by Josh on 4/26/2017.
 */
public class XmlParser {

    public static void main(String[] args){

        //Prepares the document to be parsed
        Document xmlDoc = getDocument("books.xml");

        //Prints the root of the XML file
        System.out.println("Root: " +
            xmlDoc.getDocumentElement().getNodeName());

        //Gets all "books" within the XMl file
        NodeList listOfBooks = xmlDoc.getElementsByTagName("book");

        //Prints number of books in XML file
        System.out.println("Number of books " + listOfBooks.getLength());

        //Prints each book and its corresponding id attribute in the XML document
        String elementName = "book";
        String attrName = "id";
        getElementAndAttrib(listOfBooks, elementName, attrName);
    }

    private static void getElementAndAttrib(NodeList listOfBooks, String elementName, String attrName) {

        try{
            //Parses through each book
            for(int i = 0; i < listOfBooks.getLength(); i++){

                //Gets the individual book element
                Element bookElement = (Element) listOfBooks.item(i);

                //Gets all child nodes of the book element
                NodeList elementList = bookElement.getChildNodes();
                //If there is an attribute, it is printed along with the name of the element
                if(bookElement.hasAttribute(attrName)){
                    System.out.println(elementName + " : " +
                            elementList.item(0).getNodeValue().trim() +
                            " has attribute " + bookElement.getAttribute(attrName));
                }
                //else, only the element name is printed
                else{
                    System.out.println(elementName + " : " +
                            elementList.item(0).getNodeValue().trim());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static Document getDocument(String docString) {

        try{
            //Builds document factory and enables some features
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            //returns the document with features enabled
            return builder.parse(new InputSource(docString));

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
