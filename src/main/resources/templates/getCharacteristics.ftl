<#import 'parts/common.ftl' as a>

<@a.page>
    <#list characteristics as characteristic>
        <div>
            <b>${characteristic.id}</b>
            <span>${characteristic.name}</span>
            <i>${characteristic.description}</i>
            <a href="/changeCharacteristic/${characteristic.id}">Изменить</a>
            <a href="/deleteCharacteristic/${characteristic.id}">Удалить</a>
        </div>
    </#list>
    <div>
        <a href = "/addCharacteristics">Добавить характеристику</a>
    </div>
</@a.page>