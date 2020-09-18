import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;


public class CommandParsing {

    static public String parse(String cmd) throws Exception {
        String[] parsed = cmd.split("/");
        switch (parsed[1]) {
            case "id": {
                HtmlGenerator.id(parsed[2]);
                return ("id");

            }
            case "feed": {
                HtmlGenerator.init(parsed[1]);
                return "feed";

            }
            case "messages": {
                HtmlGenerator.init(parsed[1]);
                return "messages";

            }

            default:
                CommandParsing.error404();
                return "";
        }
    }

    static public void error404() {
        openHtml("\\error\\404.html");
    }

    public static void openHtml(String cmd) {
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

    public static void inputUser(BufferedReader br) throws Exception {
        String record = "";
        System.out.println("Input Username:");
        record += br.readLine() + ";";
        System.out.println("Input Age:");
        record += br.readLine() + ";";
        System.out.println("Input Id:");
        record += br.readLine() + ";";
        System.out.println("Input Country:");
        record += br.readLine();
        inputRecord("user", record);
    }

    public static void inputPost(BufferedReader br) throws Exception {
        String record = "";
        System.out.println("Input Name:");
        record += br.readLine() + ";";
        System.out.println("Input Description:");
        record += br.readLine() + ";";
        System.out.println("Input Author ID:");
        record += br.readLine() + ";";
        System.out.println("Input Date:");
        record += br.readLine() + ";";
        System.out.println("Input Text:");
        record += br.readLine();
        inputRecord("posts", record);
    }

    public static void inputMessages(BufferedReader br) throws Exception {
        String record = "";
        System.out.println("Input ID Send:");
        record += br.readLine() + ";";
        System.out.println("Input ID To:");
        record += br.readLine() + ";";
        System.out.println("Input Message:");
        record += br.readLine();
        inputRecord("messages", record);
    }

    public static void inputRecord(String type, String record) throws Exception {
        File data = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/" + type + ".txt");
        FileWriter fw = new FileWriter(data, true);
        fw.write("\n" + record);
        System.out.println("Record was created in " + type + ".txt!");
        fw.close();
    }

}
