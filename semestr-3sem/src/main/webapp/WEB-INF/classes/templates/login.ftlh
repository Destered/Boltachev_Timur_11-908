<#--<!doctype html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <meta name="viewport"-->
<#--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">-->
<#--    <meta http-equiv="X-UA-Compatible" content="ie=edge">-->
<#--    <title>Document</title>-->
<#--</head>-->
<#--<body>-->
<#--<form action="/signin" method="post">-->
<#--    <input name="email" type="email" placeholder="EMAIL">-->
<#--    <input name="password" type="password" placeholder="PASSWORD">-->
<#--    <input type="submit" value="SIGN IN">-->
<#--</form>-->
<#--<form action="/signup" method="get">-->
<#--    <input type="submit" value="Sign Up">-->
<#--</form>-->
<#--</body>-->
<#--</html>-->


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        .main{
            margin:0px 0px;
            background-size:100%;
            background-color: cornflowerblue;
        }
        .login{
            margin:200px 500px;
            width:300px;
            height:220px;
            border:1px solid #ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .name{
            text-align:center;
            margin:15px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .password{
            text-align:center;
            margin:-10px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            outline:none;
            border-radius:10px;
        }
        .enter{
            margin:17px 110px 10px;
            text-align:center;
            width:80px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .sign{
            margin:0px;
            text-align:center;
            color:#ffffff
        }
        .message{
            margin:5px;
            text-align:center;
        }
        .reg{
            margin:0px 94px;
            text-align:center;
            width:115px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .enter:active{
            margin:16px 110px;
        }
        .reg:active{
            margin:-12px 94px;
        }
    </style>
</head>
<body class="main">
<div class = "login">
    <form action="/login" method="post">
        <h2 class="sign">SIGN IN</h2>
        <#if message?has_content>
            <p class="message" ><font color="red"><b>${message}</b></font></p>
        </#if>
        <input id="email" type="email" name="email" placeholder="email" class="name"/>
        <input type="password" name="password" placeholder="password" class="password"/>
        <input type="submit" value="ENTER" class="enter"/>
        <a href="http://localhost:8080/signup">
            <button type="button" class="reg">REGISTRATION</button>
        </a>
    </form>
</div>
<script type="text/javascript">
    function max() {
        document.getElementById('login1').setAttribute("maxlength","12");
    }
    max()
</script>
</body>
</html>