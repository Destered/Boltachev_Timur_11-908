<#ftl encoding="utf-8">
<#include "standardPage.ftl" />
<@standardPage>
    <form action="profile" method="post">
    <h4>Пользователь:</h4>
    ${username}
    <br>
    ${email}
    <#if about?has_content>
        <br>
        О себе:
        <br>
        ${about}
    <#else >


    </#if>
        <input type="submit" value="Выйти">
    </form>
</@standardPage>
