<#ftl encoding="utf-8">
<#include "standardPage.ftl" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

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
            $('#messages').empty();
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
                if(messageText.search("%%%\*%%%") === -1 && messageText.search("###\*###") === -1) {
                    $.ajax({
                        url: "/chatdata",
                        type: "GET",
                        data: {message: messageText, method: 'sendMsg'},
                        async: false,
                        success: function (data) {
                            if (data.toString().charAt(0) === "1") {
                                alert("Input Correct Message")
                            }
                        }
                    })
                    message.value = "";
                    getMsg();
                    message.scrollTop = message.scrollHeight;
                    document.getElementById('message-text').focus()
                } else{
                    alert("Input Correct Message")
                }
            }
        }


    </script>

<@standardPage>
    <button type="button" style="position:relative;left: 67%;" class="btn btn-warning" data-toggle="modal" data-target="#myModal">
        ?
    </button>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Правила</h4>
                </div>
                <div class="modal-body">
                    Соблюдайте правила общения с другими людьми
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>
        </div>
    </div>


    <div class='chat'>
        <div class='chat-messages'>
            <div class='chat-messages__content' id='messages'>

            </div>
        </div>
        <div class='chat-input'>
            <form method='get' id='chat-form'>
                <input type='text' id='message-text' autocomplete="off" class='chat-form__input' placeholder='Введите сообщение'>
                <button type='button' id="message-btn" class='chatBtnPadding btnPadding btn btn-outline-dark' onclick="send()">Отправить</button>
            </form>
        </div>
    </div>
</@standardPage>
<script>
    setInterval(getMsg,1000)
</script>