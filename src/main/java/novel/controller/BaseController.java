package novel.controller;

import novel.error.BussinessException;
import novel.error.EmBussinessException;
import novel.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

public class BaseController {

    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonReturnType BussinessExceptionHandler(Exception e){
        CommonReturnType commonReturnType = new CommonReturnType();
        HashMap<String, String> map = new HashMap<>();
        if(e instanceof BussinessException){
            commonReturnType.setStatus("error");
            map.put("errCode", ((BussinessException) e).getExceptionCode().toString());
            map.put("errMsg", ((BussinessException) e).getExceptionMsg());
            commonReturnType.setData(map);
        }else{
            commonReturnType.setStatus("error");
            map.put("errCode", EmBussinessException.UNKNOWN_ERROR.getExceptionCode().toString());
            map.put("errMsg", e.getMessage());
            commonReturnType.setData(map);
        }
        e.printStackTrace();
        return commonReturnType;
    }
}
