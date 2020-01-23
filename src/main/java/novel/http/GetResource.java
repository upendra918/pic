package novel.http;


import com.github.kevinsawicki.http.HttpRequest;

public class GetResource {

    public static String get(String url){
        String response = HttpRequest.get(url).body();
        return response;
    }
}
