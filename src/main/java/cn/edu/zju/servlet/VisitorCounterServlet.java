package cn.edu.zju.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/counter")
public class VisitorCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        ServletContext context = getServletContext();

        Integer count = (Integer) context.getAttribute("count");

        if (count == null) {
            count = 0;
        }

        count++;
        context.setAttribute("count", count);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>Visitor Count: " + count + "</h1>");
        out.println("</body></html>");
    }
}

