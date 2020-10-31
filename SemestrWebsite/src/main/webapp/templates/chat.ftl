<#ftl encoding="utf-8">
<#include "standardPage.ftl" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        let messages__container = document.getElementById('messages');//Контейнер сообщений — скрипт будет добавлять в него сообщения
        let interval = null; //Переменная с интервалом подгрузки сообщений
        let sendForm = document.getElementById('chat-form'); //Форма отправки
        let messageInput = document.getElementById('message-text');
        let lastMsgIndex = 0;

        function getMsg() {
            clearBox();
            $.ajax({
                url : "/chatdata",
                type : "GET",
                data: { lastMsgIndex: lastMsgIndex,method:'getMsg'} ,
                async : false,
                success : function(data) {
                    let rootEl = document.getElementById("messages");
                    let msgList = data.split('###*###');
                    msgList.forEach(function(item, i, arr) {
                        let msgItem = item.split('%%%*%%%');
                        if(msgItem.length > 1) {
                            let el = document.createElement("div");
                            el.innerHTML =
                                "<div class='chat__message'><b>"+ msgItem[0] +":</b>" + msgItem[1] +" </div>"
                            lastMsgIndex = msgItem[2];
                            rootEl.appendChild(el);
                        }
                    })
                }
            });
        }


        function clearBox()
        {
            $("#messages").empty();
        }

        function escapeHtml(text) {
            var map = {
                '&': '&amp;',
                '<': '&lt;',
                '>': '&gt;',
                '"': '&quot;',
                "'": '&#039;'
            };

            return text.toString().replace(/[&<>"']/g, function(m) { return map[m]; });
        }

        function send() {
            let message = document.getElementById('message-text');
            let messageText = escapeHtml(message.value);
            if (messageText === '') {
                alert('Введите сообщение!')
                document.getElementById('message-text').focus()
            } else {
                $.ajax({
                    url: "/chatdata",
                    type: "GET",
                    data: {message: messageText, method: 'sendMsg'},
                    async: false,
                    success : function(data) {
                        if(data.toString().charAt(0) === "1"){
                            alert("Input Correct Message")
                        }
                    }
                })
                message.value = "";
                getMsg();
                message.scrollTop = message.scrollHeight;
                document.getElementById('message-text').focus()
            }
        }


    </script>

<@standardPage>
    <div class='chat'>
        <div class='chat-messages'>
            <div class='chat-messages__content' id='messages'>

            </div>
        </div>
        <div class='chat-input'>
            <form method='get' id='chat-form'>
                <input type='text' id='message-text' autocomplete="off" class='chat-form__input' placeholder='Введите сообщение'> <button type='button' id="message-btn" class='chatBtnPadding btnPadding btn btn-outline-dark' onclick="send()">Отправить</button>
            </form>
        </div>
    </div>
</@standardPage>
<script>
    setInterval(getMsg,1000)
</script>