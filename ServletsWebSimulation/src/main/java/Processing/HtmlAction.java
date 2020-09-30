package Processing;

public class HtmlAction {
    public void error404() {
        getHtmlPath("\\error\\404.html");
    }

    public String getHtmlPath(String cmd) {
            return "D:\\Desktop\\Learn\\Git\\Project\\ServletsWebSimulation\\src\\main\\java\\Processing\\" + cmd;
    }
}
