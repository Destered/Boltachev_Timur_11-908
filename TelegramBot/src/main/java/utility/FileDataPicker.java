package utility;

import java.io.*;

public class FileDataPicker {
    String path = "src/main/java/";
    BufferedReader reader;

    public BufferedReader getReader(String path){
        BufferedReader reader = null;
        try {
            File file = new File(this.path + path);
           reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return reader;
    }

    public String getToken(){
        String line = "";

        String[] token = ";".split(";");

        try {
            reader = getReader("settings/data.txt");
            if(reader != null) {
                line = reader.readLine();
                System.out.println("Line: " + line);
                token = line.split(";");
                System.out.println("Splited: " + token[1]);
            } else return "";
        } catch (IOException e){
            e.printStackTrace();
        }
            return token[1];

    }
}
