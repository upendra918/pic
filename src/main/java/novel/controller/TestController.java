package novel.controller;

import novel.error.BussinessException;
import novel.error.EmBussinessException;
import novel.parse.NovelParse;
import novel.response.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/test")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class TestController extends BaseController {

    @RequestMapping(path = "/error")
    @ResponseBody
    public CommonReturnType generateError(@RequestParam("num") Integer num){
        return CommonReturnType.create(String.format("1 / %d = %d", num, 1 / num));
    }

    @RequestMapping(path = "/redis")
    @ResponseBody
    public CommonReturnType redis(){
        Jedis jedis = new Jedis(NovelParse.getRedisLocation());
        String _str = String.format("wjl TestController.redis: jedis.ping() = %s", jedis.ping());
        System.out.println(_str);
        return CommonReturnType.create(_str);
    }
}
