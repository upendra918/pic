package novel.controller;

import novel.response.CommonReturnType;
import novel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import java.io.IOException;

@Controller
public class NovelController extends BaseController{
    @Autowired
    NovelService novelService;

    @RequestMapping("/novel")
    @ResponseBody
    public CommonReturnType getNovel(@RequestParam("name") String name) throws IOException, SAXException {
        String content = novelService.getNovelFromFilesSystem(name);
        return CommonReturnType.create(content);
    }
}
