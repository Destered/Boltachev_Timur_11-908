<#ftl encoding="utf-8">
<#include "standardPage.ftl" />

<@standardPage>

    <script src="/templates/test.js"></script>
    <div class="form_base">
        <form action="/post/create"
              method="POST"
              name="postCreateWindows"
              enctype='multipart/form-data'
              autocomplete="off">

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Title:</span>
                </div>
                <input name="title" type="text" class="form-control" aria-label="Default"
                       aria-describedby="inputGroup-sizing-default">
            </div>

            <div class="form-group">
                <label for="exampleFormControlTextarea1">Text:</label>
                <textarea name="text" class="form-control" id="exampleFormControlTextarea1" rows="4"></textarea>
            </div>
            <br>
            <p><input class="inputPhotoBtn" type="file" name="photo" accept="image/*"></p>
            <button type="submit" class="btn btn-success">Создать</button>
        </form>
    </div>
</@standardPage>