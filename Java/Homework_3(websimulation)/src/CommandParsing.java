public class CommandParsing {

    static public void parse(String cmd) {
        try {
            HtmlAction htmlAction = new HtmlAction();
            String[] parsed = cmd.split("/");
            switch (parsed[1]) {
                case "id": {
                    HtmlGenerator.id(parsed[2]);
                    break;

                }
                case "feed":
                case "messages": {
                    HtmlGenerator.init(parsed[1]);
                    break;

                }

                default:
                    htmlAction.error404();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
