import java.io.BufferedReader;
import java.io.InputStreamReader;

public class core {
    public static void main(String[] args) {
        // Examples: "GET" "/get" || "GET" "/uuid" || "POST" "/anything"
        URLConnectionCustom con = new URLConnectionCustom("http://httpbin.org");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Input command: ");
                String command = bf.readLine();
                System.out.println("Input uri: ");
                String uri = bf.readLine();
                System.out.println(con.request(command, uri));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
