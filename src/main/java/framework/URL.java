package framework;

import java.io.Serializable;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public class URL implements Serializable {

    private static final long serialVersionUID = 5670986941040687111L;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 端口号
     */
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
