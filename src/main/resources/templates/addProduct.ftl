<#import "parts/common.ftl" as c>

<@c.page>
        <form method="post" action="/addProduct">
            <div><label>Имя<input name="name" placeholder="Введите имя" type="text"></label></div>
            <br>
            <div><label>Цена<input type="text" name="price" placeholder="Введите цену"></label></div>
            <br>
            <div><label>Скидка<input type="text" name="discount" placeholder="Введите скидку"></label></div>
            <br>
            <div><label>Картинка<input type="text" name="img" placeholder="Введите название картинки"></label></div>
            <br>
            <#list characteristics as characteristic>
                <div><label>${characteristic.name}<input type="text" name="${characteristic.id}" placeholder="${characteristic.description}"></label></div>
                <br>
            </#list>
            <button type="submit">Добавить комикс</button>
        </form>
</@c.page>