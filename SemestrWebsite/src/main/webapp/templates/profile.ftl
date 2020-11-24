<#ftl encoding="utf-8">
<#include "standardPage.ftl" />
<link rel="stylesheet" href="/templates/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<@standardPage>
    <#if pageOverlord>
        <div class="profilePage">
            <div class="container">
                <div class="row m-y-2">
                    <div class="col-lg-8 push-lg-4">
                        <div class="col-lg-10 pull-lg-8 text-xs-center">
                            <#if imagepath?has_content>
                                <img src="..${imagepath}" class="m-x-auto img-fluid img-circle" alt="avatar" height="300" width="300">
                            <#else>
                                <img src="//placehold.it/150" class="m-x-auto img-fluid img-circle" alt="avatar" height="300" width="300">
                            </#if>
                            <h3>${username}</h3>
                            <h6 class="m-t-2">Upload a photo</h6>
                            <br>
                            <form name="uploadPhoto" class="form-check-inline inputPhotoSize noPadding"
                                  action="/uploadPhoto"
                                  method="post"
                                  enctype='multipart/form-data'>
                                <p><input class="inputPhotoBtn" type="file" name="photo" accept="image/*">
                                    <input class="inputPhotoBtn" type="submit" value="Отправить"></p>
                            </form>
                        </div>
                        <br>
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a data-toggle="tab" class="nav-link active">Профиль</a>
                            </li>
                            <li class="nav-item">
                                <a href="/editUser" data-toggle="tab" class="nav-link active">Редактировать</a>
                            </li>
                        </ul>
                        <div class="tab-content p-b-3">
                            <div class="tab-pane active" id="profile">
                                <h4 class="m-y-2">Профиль:</h4>
                                <div class="row">
                                    <div class="col-md-6">
                                        <#if about?has_content>
                                            <h6>About</h6>
                                            <p>
                                                ${about}
                                            </p>
                                        <#else>
                                            <h6>О себе:</h6>
                                            <p>
                                                <a href="/editUser">Добавьте информацию</a>
                                            </p>
                                        </#if>
                                    </div>
                                    <div class="col-md-12">
                                        <h4 class="m-t-2"><span class="fa fa-clock-o ion-clock pull-xs-right"></span>
                                            Данные:
                                        </h4>
                                        <table class="table table-hover table-striped">
                                            <tbody>
                                            <tr>
                                                <td>
                                                    <strong>Почта:</strong> ${email}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <strong>Полное имя:</strong> ${firstname} ${secondname}
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
            <form method="post">
                <button class="btnPadding"  type="submit">Выход</button>
            </form>
        </div>
    <#else>
        <div class="profilePage">
            <div class="container">
                <div class="row m-y-2">
                    <div class="col-lg-8 push-lg-4">
                        <div class="col-lg-10 pull-lg-8 text-xs-center">
                            <#if imagepath?has_content>
                                <img src="..${imagepath}" class="m-x-auto img-fluid img-circle" alt="avatar" height="300" width="300">
                            <#else>
                                <img src="//placehold.it/150" class="m-x-auto img-fluid img-circle" alt="avatar" height="300" width="300">
                            </#if>
                            <br>
                        </div>
                        <br>
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a data-toggle="tab" class="nav-link active">Профиль</a>
                            </li>
                        </ul>
                        <div class="tab-content p-b-3">
                            <div class="tab-pane active" id="profile">
                                <h4 class="m-y-2">Профиль:</h4>
                                <div class="row">
                                    <div class="col-md-6">
                                        <#if about?has_content>
                                            <h6>About</h6>
                                            <p>
                                                ${about}
                                            </p>
                                        <#else>
                                            <h6>О себе:</h6>
                                            <p>
                                                <p>Нет информации</p>
                                            </p>
                                        </#if>
                                    </div>
                                    <div class="col-md-12">
                                        <h4 class="m-t-2"><span class="fa fa-clock-o ion-clock pull-xs-right"></span>
                                            Данные:
                                        </h4>
                                        <table class="table table-hover table-striped">
                                            <tbody>
                                            <tr>
                                                <td>
                                                    <strong>Почта:</strong> ${email}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <strong>Полное имя:</strong> ${firstname} ${secondname}
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </#if>
</@standardPage>
