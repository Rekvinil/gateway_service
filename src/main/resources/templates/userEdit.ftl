<#import "parts/commonForForm.ftl" as c>

<@c.page>
    Редактирование пользователя
    <form action="/user" method="post">
        ${user.username}
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <input type="hidden" value="${user.username}" name="username">
        <button type="submit">Сохранить</button>
    </form>
</@c.page>