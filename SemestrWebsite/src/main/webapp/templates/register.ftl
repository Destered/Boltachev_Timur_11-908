<#ftl encoding="utf-8">
<#include "standardPage.ftl" />
<@standardPage>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/templates/test.js"></script>
 

    <form action="register" method="POST" name="authWindow" autocomplete="off">

        <label>
            <p class="label-txt text-center">Имя</p>
            <input name="firstName" type="text" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <label>
            <p class="label-txt text-center">Фамилия</p>
            <input name="secondName" type="text" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <label>
            <p class="label-txt">Имя пользователя</p>
            <input name="username" type="text" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <label>
            <p class="label-txt text-center">Почта</p>
            <input name="email" type="text" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <label>
            <p class="label-txt">Пароль</p>
            <input name="password" id="password" type="password" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <br>
        <br>
        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModal">
            Правила
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
                        Регистрация <br>
                        При регистрации на сайте запрещается:<br><br>
                        -Использование нецензурных слов в имени пользователя;<br>
                        -Использование более одного логина одним пользователем.<br>
                        -Использование одного логина более чем одним пользователем; <br>
                        -Использовать длинные имена, состоящие из произвольного набора символов, в том числе: случайные
                        последовательности; кодирования uuencode, base64, binhex; хеши; uuid и т. п.;<br>
                        -Использование имён, похожих на ники существующих пользователей, кроме случаев повторной
                        регистрации, особенно, в сочетании с одинаковыми или крайне схожими аватарами.<br> <br>

                        Нарушение copyright<br>
                        Сообщения, содержащие информацию, публикация которой на сайте невозможна из-за ограничений,
                        наложенных её авторами. В частности: <br>
                        <br>
                        -Новости с других сайтов, явным образом не разрешающих их копирование. Если вы хотите поделиться
                        увиденной вами новостью, перескажите её своими словами. <br>
                        -Ссылки на места расположения нелегально распространяемого ПО. <br>
                        -Ссылки на страницы, непосредственные ссылки на которые запрещены их авторами.<br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-success">Регистрация</button>
    </form>
</@standardPage>