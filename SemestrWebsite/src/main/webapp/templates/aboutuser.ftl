<#ftl encoding="utf-8">
<#include "standardPage.ftl" />

<@standardPage>
    <div class="editPage">
        <form method="post" action="/editUser">

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">First Name:</span>
                </div>
                <input name="firstname" type="text" class="form-control" aria-label="Default"
                       aria-describedby="inputGroup-sizing-default">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Second Name:</span>
                </div>
                <input name="secondname" type="text" class="form-control" aria-label="Default"
                       aria-describedby="inputGroup-sizing-default">
            </div>

            <div class="form-group">
                <label for="exampleFormControlTextarea1">About Me:</label>
                <textarea name="about" class="form-control" id="exampleFormControlTextarea1" rows="4"></textarea>
            </div>
            <button type="submit" class="btn btn-outline-primary">Изменить</button>
        </form>
    </div>
</@standardPage>