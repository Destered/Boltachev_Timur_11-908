import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;

public class InputClass {
    HtmlAction htmlAction = new HtmlAction();

    void inputRecord(BufferedReader br) {
        while (true) {
            try {
                System.out.println("Input type(type\"/exit\" to exit): (\"/user\",\"/post\",\"/message\")");
                String command = br.readLine();
                if (command.equals("/exit")) {
                    break;
                } else switch (command) {
                    case "/user": {
                        inputUser(br);
                        break;
                    }
                    case "/post": {
                        inputPost(br);
                        break;
                    }
                    case "/message": {
                        inputMessages(br);
                        break;
                    }
                    default:
                        System.out.println("Incorrect command!");
                }
            } catch (Exception e) {
                htmlAction.error404();
                break;
            }

        }
    }

    void inputCommand(BufferedReader br) {
        while (true) {
            try {
                System.out.println("Input command(type\"/exit\" to exit):");
                String command = br.readLine();
                if (command.equals("/exit")) {
                    break;
                } else CommandParsing.parse(command);
            } catch (Exception e) {
                htmlAction.error404();
                break;
            }

        }
    }

    public void inputUser(BufferedReader br) {
        try {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputPost(BufferedReader br) {
        try {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputMessages(BufferedReader br) {
        try {
            String record = "";
            System.out.println("Input ID Send:");
            record += br.readLine() + ";";
            System.out.println("Input ID To:");
            record += br.readLine() + ";";
            System.out.println("Input Message:");
            record += br.readLine();
            inputRecord("messages", record);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputRecord(String type, String record) {
        try {
            File data = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/" + type + ".txt");
            FileWriter fw = new FileWriter(data, true);
            fw.write("\n" + record);
            System.out.println("Record was created in " + type + ".txt!");
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
