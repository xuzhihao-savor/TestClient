import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequestUtils {

      public   HttpClient client = HttpClients.createDefault();
      public String getUrl() {
          JSONObject jsonResult = httpGet("http://192.168.0.9:12000/web/login/logic");
          String url = jsonResult.getString("url");
          return  url;
      }

    public  JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
//                url = URLDecoder.decode(url, "UTF-8");
//                System.out.println(url);
            } else {
                System.out.println("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            System.out.println("get请求提交失败:" + url);
        }
        return jsonResult;
    }

}
