package novel.parse;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class NovelParse {
    private static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    public static String getNovelLocation(){
        return getNodeValue("novel", 0, "content");
    }

    public static String getRemoteImgLocation(){
        return getNodeValue("remote", 0, "content");
    }

    public static String getRedisLocation(){
        return getNodeValue("redis", 0, "location");
    }

    public static String getNodeValue(String nodeName, Integer index, String arr){
        DOMParser parser = new DOMParser();
        Document document = parse("conf.xml");
        //get root element
        Element rootElement = document.getDocumentElement();

        //traverse child elements
        Node node = rootElement.getElementsByTagName(nodeName).item(index);
        Element e = (Element)node;
        String s = e.getAttribute(arr);
        return s;
    }

    //Load and parse XML file into DOM
    public static Document parse(String filePath) {
        Document document = null;
        try {
            //DOM parser instance
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //parse an XML file into a DOM tree
            document = builder.parse(new File(filePath));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
