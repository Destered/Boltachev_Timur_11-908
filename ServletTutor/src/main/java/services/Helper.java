package services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Helper {
    public void render(HttpServletRequest request, HttpServletResponse response, String path, Map<String,Object> root){
        Configuration configuration = (Configuration)request.getServletContext().getAttribute("cfg");
        try {
            Template tmpl = configuration.getTemplate(path);
            tmpl.process(root, response.getWriter());
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
