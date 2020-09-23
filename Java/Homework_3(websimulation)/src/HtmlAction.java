import java.awt.*;
import java.io.File;

public class HtmlAction {
    public void error404() {
        openHtml("\\error\\404.html");
    }

    public void openHtml(String cmd) {
        try {
            cmd = "\\Homework_3(websimulation)\\src" + cmd;
            String path = new File("").getAbsolutePath();
            path += cmd;
            File htmlFile = new File(path);
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
