import java.io.*;

public class HtmlGenerator {
    String path;
    String name;
    HtmlAction htmlAction = new HtmlAction();

    public HtmlGenerator(String name) {
        try {
            this.name = name + "_" + System.currentTimeMillis();
            path = new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/page/" + this.name + ".html";
            new File(path).createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void id(String id) {
        boolean isFind = false;
        try {
            File user = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/user.txt");
            BufferedReader br = new BufferedReader(new FileReader(user));
            br.readLine();
            String curLine = br.readLine();
            while (!curLine.isEmpty()) {
                String[] data = curLine.split(";");
                if (id.equals(data[2])) {
                    isFind = true;
                    HtmlGenerator file = new HtmlGenerator("id");
                    file.begin();
                    file.addUser(data);
                    file.finish();
                    break;
                }
                curLine = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!isFind) {
            System.out.println("User not found!");
        }

    }

    public static void init(String cmd) {
        HtmlGenerator file = new HtmlGenerator(cmd);
        if (cmd.equals("messages")) {
            file.message();
        } else {
            file.read();
        }
    }

    public void feed(String user) {
        try {
            String[] userData = user.split(";");
            File posts = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/posts.txt");
            BufferedReader br = new BufferedReader(new FileReader(posts));
            br.readLine();
            String post = br.readLine();
            while (post != null) {
                String[] postData = post.split(";");
                if (postData[2].equals(userData[2])) {
                    this.addPost(postData, userData[0]);
                }
                post = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void message() {
        try {
            this.begin();
            File user = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/user.txt");
            BufferedReader brUsr = new BufferedReader(new FileReader(user));
            File messages = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/messages.txt");
            BufferedReader brMsg = new BufferedReader(new FileReader(messages));
            brUsr.readLine();
            brMsg.readLine();
            String message = brMsg.readLine();
            while (message != null) {
                String[] userFrom = null;
                String[] userTo = null;
                String[] dataMsg = message.split(";");
                String userLine = brUsr.readLine();
                while (userLine != null) {
                    String[] userData = userLine.split(";");
                    if (dataMsg[0].equals(userData[2])) {
                        userFrom = userData;
                    } else if (dataMsg[1].equals(userData[2])) {
                        userTo = userData;
                    }
                    userLine = brUsr.readLine();
                }
                if (userFrom != null && userTo != null) {
                    this.addMessage(userFrom, userTo, dataMsg[2]);
                }
                brUsr.close();
                brUsr = new BufferedReader(new FileReader(user));
                message = brMsg.readLine();
            }
            brMsg.close();
            brUsr.close();
            this.finish();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void read() {
        try {
            File user = new File(new File("").getAbsolutePath() + "/Homework_3(websimulation)/src/data/user.txt");
            BufferedReader br = new BufferedReader(new FileReader(user));
            br.readLine();
            String curLine = br.readLine();
            while (curLine != null) {
                feed(curLine);
                curLine = br.readLine();
            }
            br.close();
            this.finish();
        } catch (FileNotFoundException e) {
            System.out.println("App exception, please report to creator!");
        } catch (Exception e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
        }
    }

    private void finish() {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(
                    "</body>\n" +
                            "</html>");
            fw.close();
            htmlAction.openHtml("\\page\\" + name + ".html");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addUser(String[] data) {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(
                    "<div>\n" +
                            "    <h4>User:</h4>\n" +
                            "    <p>" + data[0] + "<strong>[" + data[2] + "]</strong>, " + data[1] + " age, from: " + data[3] + "</p>\n" +
                            "</div>"
            );
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addPost(String[] data, String author) {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(
                    "    <div>\n" +
                            "        <h2>" + data[0] + "</h2>\n" +
                            "        <h4>" + data[1] + "</h4>\n" +
                            "        <br>\n" +
                            "        <p>" + data[4] + "</p>\n" +
                            "        <br>\n" +
                            "        <h6>" + author + " on " + data[3] + "</h6>\n" +
                            "        <hr>\n" +
                            "    </div>"
            );
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addMessage(String[] userIn, String[] userOut, String msg) {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(
                    "<div>\n" +
                            "    <p><strong>" + userIn[0] + "[" + userIn[2] + "]" + "</strong> send to <strong>" + userOut[0] + "[" + userOut[2] + "]" + "</strong> message: <br> " + msg + "</p>\n" +
                            "</div>");
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void begin() {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"ru-RU\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"utf-8\">\n" +
                            "    <title>" + name + "</title>\n" +
                            "</head>\n" +
                            "<body>");
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
