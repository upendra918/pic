package novel.service;

import novel.parse.NovelParse;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;

@Service
public class NovelService {

    public String getNovelFromFilesSystem(String path) throws IOException, SAXException {

        return NovelParse.getNovelLocation();
    }
}
