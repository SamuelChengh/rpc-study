package framework.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: CH
 * @date: 2021-11-29
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -81105812200575328L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        new HttpServerHandler().handler(req, resp);
    }

}
