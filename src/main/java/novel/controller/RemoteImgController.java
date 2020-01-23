package novel.controller;

import novel.Model.RemoteImgModel;
import novel.response.CommonReturnType;
import novel.service.RemoteImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/remote_img")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class RemoteImgController extends BaseController {
    @Autowired
    RemoteImgService remoteImgService;

    @RequestMapping(path = "/list")
    @ResponseBody
    public CommonReturnType getImgList() throws IOException {
        List<RemoteImgModel> list = remoteImgService.getList();
        return CommonReturnType.create(list);
    }

    @RequestMapping(path = "/get")
    @ResponseBody
    public CommonReturnType getImg(@RequestParam("id") String name){
        System.out.printf("wjl RemoteImgController.getImg: name = %s\r\n", name);
        RemoteImgModel remoteImgModel = remoteImgService.getImg(name);
        return CommonReturnType.create(remoteImgModel);
    }

    @RequestMapping(path = "/page")
    @ResponseBody
    public CommonReturnType getImgPage(@RequestParam("num") String num, @RequestParam("size") String size) throws IOException {
        int _index;
        int _size;
        try{
            _index = Integer.valueOf(num);
            _size = Integer.valueOf(size);
        }catch (Exception e){
            _index = 1;
            _size = 2;
        }
        List<RemoteImgModel> remoteImgModelList = remoteImgService.getByRange((_index - 1) * _size + 1, _index * _size + 1);
        return CommonReturnType.create(remoteImgModelList);
    }

    @RequestMapping(path = "/update_img_urls")
    @ResponseBody
    public CommonReturnType updateImgUrls() throws IOException {
        remoteImgService.updateImgUrls();
        return CommonReturnType.create("update the urls of imgs");
    }
}
