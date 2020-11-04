<#ftl encoding="utf-8">
<#include "standardPage.ftl" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $.ajax({
            url : "/getpost",
            type : "GET",
            async : false,
            success : function(data) {
                let rootEl = document.getElementById("list");
                let postList = data.split('#&#');
                postList.forEach(function(item, i, arr) {
                    let postItem = item.split('#%#')
                    if(postItem.length > 1) {
                        let el = document.createElement("div")
                        el.innerHTML =  '<div class="card mb-4"> ' +
                            '<img class="card-img-top" ' +
                            'src="..'+ postItem[2] +'" ' +
                            'alt="Card image cap"> <div class="card-body"> ' +
                            '<h2 class="card-title">'+ postItem[0] +'</h2> ' +
                            '<p class="card-text">'+ postItem[1] +'</p> ' +
                            '</div> <div class="card-footer text-muted"> Posted by <a href="/user?user_id='+ postItem[3] + '">'+ postItem[4] +'</a> </div> </div>'
                        rootEl.appendChild(el);
                    }
                });
            }
        });
    })
</script>
<@standardPage>
    <div>
        <a class="btn btn-danger stretched-link btnCreatePost" href="/post/create">Создать пост</a>
    </div>

    <div>
        <ul id="list" class="list-group listSize">

        </ul>
    </div>

</@standardPage>