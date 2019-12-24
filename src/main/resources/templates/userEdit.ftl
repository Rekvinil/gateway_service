<#import "parts/commonForForm.ftl" as c>

<@c.page>
    <main>
        <h1 class="text-center"><strong>Редактирование ролей пользователя ${user.username}</strong></h1>
        <div class="container border">
            <form action="/user" method="post">
                <#list roles as role>
                    <div class="form-group">
                        <label>${role}</label>
                        <input class="form-check-inline" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                    </div>
                </#list>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <input type="hidden" value="${user.username}" name="username">
                <button class="btn btn-warning" type="submit">Сохранить</button>
            </form>
        </div>
    </main>
</@c.page>