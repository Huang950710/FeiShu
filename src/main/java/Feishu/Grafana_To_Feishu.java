package Feishu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class Grafana_To_Feishu {

    /**
     *
     * @param message
     * @param webhook
     * @throws IOException
     * WebHook地址xxx
     * public static String WEBHOOK_TOKEN = "https://open.feishu.cn/open-apis/bot/v2/hook/1d702f11-29c4-4424-8b2f-a2763517793d";
     */
    public static void send(String message,String webhook) throws IOException {

        String WEBHOOK_TOKEN = webhook;

        /**
         * 解析Json,将一行 jSON 数据转换为 JSON 对象
         */
        JSONObject jsonObject = JSON.parseObject(message);
        String content = "### Grafana 集群告警\n\n";

        String messages = jsonObject.get("message").toString();
        content = content + messages.replace("**Firing**","").replace("\\n\\n","\n").trim() + "\n";
        content = content + "<at user_id=\"all\">所有人</at>" + "\n";

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String s = JSONObject.toJSONString(content);

        String textMsg1 = "{\"msg_type" + "\":" + "\"text\"," + "\"content\":{\"text\":"+ s + "}}";

        StringEntity se = new StringEntity(textMsg1, "utf-8");

        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
