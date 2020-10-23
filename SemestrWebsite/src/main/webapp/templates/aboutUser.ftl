<#ftl encoding="utf-8">
<#include "standardPage.ftl" />

<@standardPage>
    <form method="post" action="/profile/aboutUser">
    <div class="form-group">
        <label for="textArea1"></label>
        <textarea class="form-control rounded-0" id="aboutTextArea" rows="10"></textarea>
    </div>
        <input type="submit" value="Изменить">
    </form>
</@standardPage>