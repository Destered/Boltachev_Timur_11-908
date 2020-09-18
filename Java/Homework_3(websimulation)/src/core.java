import java.io.BufferedReader;
import java.io.InputStreamReader;

public class core {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("Choose action:\n    (1)Add record to DB\n    (2)Input command ");
                String cmd = br.readLine();
                switch (cmd) {
                    case "1": {
                        inputRecord(br);
                        break;
                    }
                    case "2": {
                        inputCommand(br);
                        break;
                    }
                    default:
                        System.out.println("Incorrect input!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inputRecord(BufferedReader br) {
        while (true) {
            try {
                System.out.println("Input type(type\"/exit\" to exit): (\"/user\",\"/post\",\"/message\")");
                String command = br.readLine();
                if (command.equals("/exit")) {
                    break;
                } else switch (command){
                    case "/user":{
                        CommandParsing.inputUser(br);
                        break;
                    }
                    case "/post":{
                        CommandParsing.inputPost(br);
                        break;
                    }
                    case "/message":{
                        CommandParsing.inputMessages(br);
                        break;
                    }
                    default:
                        System.out.println("Incorrect command!");
                }
            } catch (Exception e) {
                CommandParsing.error404();
                break;
            }

        }
    }

    private static void inputCommand(BufferedReader br) {
        while (true) {
            try {
                System.out.println("Input command(type\"/exit\" to exit):");
                String command = br.readLine();
                if (command.equals("/exit")) {
                    break;
                } else System.out.println(CommandParsing.parse(command));
            } catch (Exception e) {
                CommandParsing.error404();
                break;
            }

        }
    }
}
    /*
    Добавить возможность добавлять данные
     */