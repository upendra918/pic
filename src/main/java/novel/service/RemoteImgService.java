package novel.service;

import novel.Model.RemoteImgModel;
import novel.http.GetResource;
import novel.parse.NovelParse;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import redis.clients.jedis.Jedis;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RemoteImgService {
    static Jedis jedis;

    public RemoteImgService() throws IOException {
        jedis = new Jedis(NovelParse.getRedisLocation());
    }

    public RemoteImgModel getImg(String name){
        String _url = jedis.hget("imgUrls_HashMap", name);
        return new RemoteImgModel(name, _url);
    }

    public List<RemoteImgModel> getList() throws IOException {
        return getByRange(0, Integer.MAX_VALUE);
    }


    public List<RemoteImgModel> getByRange(int start, int end) throws IOException {
        List<String> list = jedis.lrange("imgUrls_List", start, end - 1);
        List<RemoteImgModel> resList = new ArrayList<>();
        for(String _name : list){
            String _url = jedis.hget("imgUrls_HashMap", _name);
            resList.add(new RemoteImgModel(_name, _url));
        }
        return resList;
    }

    public boolean updateImgUrls() throws IOException {
        String url = NovelParse.getRemoteImgLocation();
        String res = GetResource.get(url);
        StringBuilder sb = new StringBuilder(res);
        while(sb.indexOf("<hr>") != -1){
            int _start = sb.indexOf("<hr>");
            int _end = _start + 4;
            sb.delete(_start, _end);
        }

        Document document = null;
        try {
            //DOM parser instance
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //parse an XML file into a DOM tree
            document = builder.parse(new ByteArrayInputStream(sb.toString().getBytes()));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get root element
        Element rootElement = document.getDocumentElement();

        //traverse child elements
        NodeList nodes = rootElement.getElementsByTagName("a");

        System.out.println("wjl RemoteImgService.updateImgUrls: loading start");
        jedis.flushDB();
        for(int i = 0; i < nodes.getLength(); ++i){
            Element cur = (Element) nodes.item(i);
            String href = cur.getAttribute("href");
            String _url = url + "/" + href;
            jedis.rpush("imgUrls_List", href);
            jedis.hset("imgUrls_HashMap", href, _url);
        }
        System.out.println("wjl RemoteImgService.updateImgUrls: loading end");
        return true;
    }
}
