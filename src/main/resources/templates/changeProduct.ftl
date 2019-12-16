<#import 'parts/common.ftl' as a>

<@a.page>
    <div>
        Comics Editor
        <form method="post" action="/changeProduct">
            <label>Имя<input name="name" value="${product.name}" type="text"></label>
            <label>Цена<input type="text" name="price" value="${product.price}"></label>
            <label>Скидка<input type="text" name="discount" value="${product.discount!}"></label>
            <label>Картинка<input type="text" name="img" value="${product.img!}"></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#list productscharacteristics as productcharacteristic>
                <label>${productcharacteristic.characteristics.name}<input name="${productcharacteristic.characteristics.name}" value="${productcharacteristic.value}" type="text"></label>
            </#list>
            <input type="hidden" value="${product.id}" name="id">
            <button type="submit">Изменить</button>
        </form>
    </div>
</@a.page>