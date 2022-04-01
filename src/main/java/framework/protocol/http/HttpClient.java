package framework.protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: CH
 * @date: 2021-12-06
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            oos.writeObject(invocation);
            oos.flush();

            // 等待服务端返回结果
            InputStream is = connection.getInputStream();
            String result = IOUtils.toString(is);
            return "http: " + result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
