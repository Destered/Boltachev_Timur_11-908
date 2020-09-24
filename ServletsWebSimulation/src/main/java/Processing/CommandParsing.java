package Processing;

public class CommandParsing {

    static public String parse(String cmd) {
        try {
            HtmlAction htmlAction = new HtmlAction();
            String[] parsed = cmd.split("/");
            switch (parsed[1]) {
                case "id": {
                    return HtmlGenerator.id(parsed[2]);

                }
                case "feed":
                case "messages": {
                    return HtmlGenerator.init(parsed[1]);

                }

                default: htmlAction.error404();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "null";
    }


}
