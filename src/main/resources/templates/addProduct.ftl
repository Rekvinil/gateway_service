<#import "parts/common.ftl" as c>

<@c.page>
    <div>
        <form method="post" action="/addProduct">
            <label>Имя<input name="name" placeholder="Введите имя" type="text"></label>
            <label>Цена<input type="text" name="price" placeholder="Введите цену"></label>
            <label>Скидка<input type="text" name="discount" placeholder="Введите скидку"></label>
            <label>Картинка<input type="text" name="img" placeholder="Введите название картинки"></label>
            <button type="submit">Добавить комикс</button>
        </form>
    </div>
</@c.page>