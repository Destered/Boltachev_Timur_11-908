package Processing;

import java.awt.*;
import java.io.File;

public class HtmlAction {
    public void error404() {
        openHtml("\\error\\404.html");
    }

    public String openHtml(String cmd) {
            return "D:\\Desktop\\Learn\\Git\\Project\\ServletsWebSimulation\\src\\main\\java\\Processing\\" + cmd;
    }
}
