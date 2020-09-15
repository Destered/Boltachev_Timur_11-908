import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionCustom {
    String site;
    public URLConnectionCustom(String site){
        this.site = site;
    }

    public String request(String command,String uri) throws Exception{
            String siteUrl = site + uri;
            final URL url = new URL(siteUrl);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(command);
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            return read(con);
    }

    private static String read(HttpURLConnection con){
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
