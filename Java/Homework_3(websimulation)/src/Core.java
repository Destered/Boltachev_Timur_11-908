import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Core {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InputClass ic = new InputClass();
        try {
            while (true) {
                System.out.println("Choose action:\n    (1)Add record to DB\n    (2)Input command ");
                String cmd = br.readLine();
                switch (cmd) {
                    case "1": {
                        ic.inputRecord(br);
                        break;
                    }
                    case "2": {
                        ic.inputCommand(br);
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
}