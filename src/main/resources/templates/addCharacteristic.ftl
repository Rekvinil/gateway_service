<#import "parts/common.ftl" as c>

<@c.page>
    Добавить характеристику
        <form method="post" action="/addCharacteristics">
            <div><label>Имя<input name="name" placeholder="Введите имя" type="text"></label></div>
            <div><label>Описание<input type="text" name="description" placeholder="Введите описание"></label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить характеристику</button>
        </form>
</@c.page>