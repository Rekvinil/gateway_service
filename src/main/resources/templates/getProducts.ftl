<#import 'parts/common.ftl' as a>

<@a.page>
    <#list products as product>
        <div>
            <b>${product.id}</b>
            <span>${product.name}</span>
            <i>${product.price}</i>
            <strong>${product.discount}</strong>
            <a href="/changeProduct/${product.id}">Изменить</a>
            <a href="/deleteProduct/${product.id}">Удалить</a>
        </div>
    </#list>
</@a.page>