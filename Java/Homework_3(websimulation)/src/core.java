import java.io.BufferedReader;
import java.io.InputStreamReader;

public class core {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try {
                System.out.println("Input command:");
                System.out.println(CommandParsing.parse(br.readLine()));
            }
            catch (IndexOutOfBoundsException e){
                CommandParsing.error404();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
    /*
    Добавить возможность добавлять данные
     */