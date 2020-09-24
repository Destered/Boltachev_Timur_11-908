package Servlet;

import Processing.CommandParsing;
import Processing.InputClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletFeed extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String path = CommandParsing.parse("/feed");
        BufferedReader br = new BufferedReader(new FileReader(path));
        String readLine = br.readLine();
        while(readLine != null) {
            writer.write(readLine);
            readLine = br.readLine();
        }
    }
}
