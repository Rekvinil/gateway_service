<#import "parts/commonForForm.ftl" as c>


<@c.page>
    <main>
        <h1 style="text-align: center"><strong>Список пользователей</strong></h1>
        <div class="container text-center">
            <div class="row">
                <div class="col-4 border">
                    <strong>Имя пользователя</strong>
                </div>
                <div class="col-4 border">
                    <strong>Роли</strong>
                </div>
                <div class="col-4 border">

                </div>
            </div>
            <#list users as user>
                <div class="row">
                    <div class="col-4 border">
                        ${user.username}
                    </div>
                    <div class="col-4 border">
                        <#list user.roles as role>${role}<#sep>, </#list>
                    </div>
                    <div class="col-4 border">
                        <a href="/user/${user.username}">Редактировать</a>
                    </div>
                </div>
            </#list>
        </div>
    </main>
</@c.page>