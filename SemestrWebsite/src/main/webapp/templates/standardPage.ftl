<#ftl encoding="utf-8">
<#macro standardPage title="">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>GEO</title>
    </head>
    <body>
    <#include "header.ftl">

    <#nested/>

    <#include "footer.ftl">
    </body>
    </html>
</#macro>