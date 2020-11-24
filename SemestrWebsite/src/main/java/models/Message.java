package models;

    public class Message{
    private int msgId;
    private String username;
    private String text;

        public Message(int msgId, String username, String text) {
            this.msgId = msgId;
            this.username = username;
            this.text = text;
        }

        public int getMsgId() {
            return msgId;
        }

        public void setMsgId(int msgId) {
            this.msgId = msgId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return username + "%%%*%%%" + text + "%%%*%%%" + msgId + "###*###";
        }

        public boolean checkValidMsg(){
            if(text.contains("%%%*%%%") || text.contains("###*###")) return false;
            return true;
        }
    }
