<#import "spring.ftl" as spring />
<@spring.bind "model" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body class="main">
<a href="http://localhost:8080/login">
    <button type="button" class="loginPage">Login</button>
</a>
<div class = "regB">
    <form action="/registration" method="post">
        <h2 class="head">REGISTRATION</h2>
        <#if message?has_content>
            <p class="message" ><font color="red"><b>${message}</b></font></p>
        </#if>
        <input type="text" name="username" placeholder="username" class="name"/>
        <input type="email" name="email" placeholder="email" class="name"/>
        <input type="password" name="password" placeholder="password" class="password"/>
        <errors path="password"></errors>
        ${passwordError}
        <input type="submit" value="ENTER" class="enter"/>
    </form>
</div>
</body>
</html>