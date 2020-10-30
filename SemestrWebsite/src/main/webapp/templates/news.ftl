<#ftl encoding="utf-8">
<#include "standardPage.ftl" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $.ajax({
            url : "/getnews",
            type : "GET",
            async : false,
            success : function(data) {
                let rootEl = document.getElementById("list");
                let newsList = data.split(';');
                newsList.forEach(function(item, i, arr) {
                    let newsItem = item.split('&')
                    if(newsItem.length > 1) {
                        let el = document.createElement("div")
                        el.innerHTML = "" +
                            "<li class=\"list-group-item\">\n" +
                            "<div class=\"card\">\n" +
                            "    <img class=\"card-img-top\" style='height: 300px' src=\"../newspage/" + newsItem[2] + " \">\n" +
                            "    <div class=\"card-body\">\n" +
                            "        <h4 class=\"card-title\">" + newsItem[0] + "</h4>\n" +
                            "        <p class=\"card-text\">" + newsItem[1] + "</p>\n" +
                            "    </div>\n" +
                            "</div>\n" +
                            "</li>"
                        rootEl.appendChild(el);
                    }
                });
            }
        });
    })
</script>

<@standardPage>

    <div>
        <ul id="list" class="list-group listSize">

        </ul>
    </div>


</@standardPage>