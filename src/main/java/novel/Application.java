package novel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"novel"})
@RestController
public class Application{

    @RequestMapping("/test")
    @ResponseBody
    public String wjl(){
        return "ok wjl";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
