<#ftl encoding="utf-8">
<#include "standardPage.ftl" />

<@standardPage>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/templates/test.js"></script>
    <form action="login" method="POST" name="authWindow">
        <label>
            <p class="label-txt">ENTER YOUR USERNAME</p>
            <input name="username" type="text" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <label>
            <p class="label-txt">ENTER YOUR PASSWORD</p>
            <input name="password" type="password" class="input">
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>
        <div class="form-check">
            <input class="form-check-input" name="remember" type="checkbox" value="" id="defaultCheck1">Remember Me
        </div>
        <br>
        <button type="submit" class="btn btn-success">Войти</button>
    </form>
</@standardPage>