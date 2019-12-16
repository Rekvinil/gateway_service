<#import 'parts/common.ftl' as a>

<@a.page>
    <div>
        Изменить характеристику
        <form method="post" action="/changeCharacteristic">
            <label>Имя<input name="name" value="${characteristic.name}" type="text"></label>
            <label>Описание<input type="text" name="description" value="${characteristic.description}"></label>
            <input type="hidden" value="${characteristic.id}" name="id">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Изменить</button>
        </form>
    </div>
</@a.page>