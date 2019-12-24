<#import 'parts/common.ftl' as a>

<@a.page>
    <main style="text-align: center">
        <h1><strong>Изменение товара</strong></h1>
        <div class="container border" style="text-align: left">
            <form method="post" action="/changeProduct" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${product.id}">
                <div class="form-group">
                    <label>Имя</label>
                    <input class="form-control" name="name" placeholder="Введите имя" type="text"
                           value="${product.name}">
                </div>
                <div class="row">
                    <div class="form-group col">
                        <label>Цена</label>
                        <input class="form-control" type="number" name="price" placeholder="Введите цену"
                               value="${product.price}">
                    </div>
                    <div class="form-group col">
                        <label>Скидка</label>
                        <div class="input-group">
                            <input class="form-control" type="number" name="discount" value="${product.discount}"
                                   placeholder="Введите скидку"
                                   aria-describedby="addon1">
                            <div class="input-group-append">
                                <span class="input-group-text" id="addon1">%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <label>Новая картинка</label>
                        <input class="form-control-file" type="file" name="img" placeholder="Введите название картинки"
                               value="/img/${product.img!}">
                    </div>
                    <div class="form-group col">
                        <label>Количество товара</label>
                        <input class="form-control" type="number" name="count" placeholder="Введите количество товара"
                               value="${product.count}">
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <#list productscharacteristics as productcharacteristic>
                    <#if productcharacteristic.getCharacteristics().getName() == "Описание">
                        <label>${productcharacteristic.getCharacteristics().getName()}</label>
                        <textarea class="form-control" name="${productcharacteristic.getCharacteristics().name}"
                                  placeholder="${productcharacteristic.getCharacteristics().getName()}">${productcharacteristic.value}</textarea>
                    <#else>
                        <div class="form-group">
                            <label>${productcharacteristic.getCharacteristics().getName()}</label>
                            <input class="form-control" type="text" name="${productcharacteristic.getCharacteristics().name}"
                                   placeholder="${productcharacteristic.getCharacteristics().getName()}"
                                   value="${productcharacteristic.value}">
                        </div>
                    </#if>
                </#list>
                <br>
                <button class="btn btn-warning" type="submit">Изменить комикс</button>
            </form>
            <a href="/deleteProduct/${product.id}" class="btn red white-text">Удалить товар</a>
        </div>
    </main>
</@a.page>