import java.awt.*;
import java.io.File;


public class CommandParsing {

  static public String parse(String cmd) throws Exception{
       String [] parsed = cmd.split("/");
       switch (parsed[1]){
           case "id":{
               HtmlGenerator.id(parsed[2]);
               return ("id");

           }
           case "feed":{
               HtmlGenerator.init(parsed[1]);
               return "feed";

           }
           case "messages":{
               HtmlGenerator.init(parsed[1]);
               return "messages";

           }

           default: return "none";
       }
   }

   static public void error404(){
        openHtml("\\error\\404.html");
   }

    public static void openHtml(String cmd){
      try {
          cmd = "\\Homework_3(websimulation)\\src" + cmd;
          String path = new File("").getAbsolutePath();
          path += cmd;
          File htmlFile = new File(path);
          Desktop.getDesktop().browse(htmlFile.toURI());
      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }
    }

}
