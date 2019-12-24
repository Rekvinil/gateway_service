<#import "parts/common.ftl" as c>

<@c.page>
    <main style="text-align: center">
        <h1><strong>Добавление товара</strong></h1>
        <div class="container border" style="text-align: left">
            <form method="post" action="/addProduct" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Имя</label>
                    <input class="form-control" name="name" placeholder="Введите имя" type="text">
                </div>
                <div class="row">
                    <div class="form-group col">
                        <label>Цена</label>
                        <input class="form-control" type="number" name="price" placeholder="Введите цену">
                    </div>
                    <div class="form-group col">
                        <label>Скидка</label>
                        <div class="input-group">
                            <input class="form-control" type="number" name="discount" placeholder="Введите скидку" value="0" aria-describedby="addon1">
                            <div class="input-group-append">
                                <span class="input-group-text" id="addon1">%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <label>Картинка</label>
                        <input class="form-control-file" type="file" name="img" placeholder="Введите название картинки">
                    </div>
                    <div class="form-group col">
                        <label>Количество товара</label>
                        <input class="form-control" type="number" name="count" placeholder="Введите цену" value="1">
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <#list characteristics as characteristic>
                    <#if characteristic.name == "Описание">
                        <label>${characteristic.name}</label>
                        <textarea class="form-control" name="${characteristic.id}" placeholder="${characteristic.description}"></textarea>
                        <#elseif characteristic.name == "Категория">
                            <div class="form-group">
                                <label>${characteristic.name}</label>
                                <select class="custom-select" name="${characteristic.id}">
                                    <option>Для детей</option>
                                    <option>Для подростков</option>
                                    <option>Для взрослых</option>
                                </select>
                            </div>
                        <#elseif characteristic.name == "Страна">
                            <div class="form-group">
                                <label>${characteristic.name}</label>
                                <select class="custom-select" name="${characteristic.id}">
                                    <option>Россия</option>
                                    <option>США</option>
                                    <option>Япония</option>
                                </select>
                            </div>
                        <#elseif characteristic.name == "Тип">
                            <div class="form-group">
                                <label>${characteristic.name}</label>
                                <select class="custom-select" name="${characteristic.id}">
                                    <option>манга</option>
                                    <option selected>комикс</option>
                                    <option>артбук</option>
                                </select>
                            </div>
                        <#else>
                            <div class="form-group">
                                <label>${characteristic.name}</label>
                                <input class="form-control" type="text" name="${characteristic.id}" placeholder="${characteristic.description}">
                            </div>
                    </#if>
                </#list>
                <br>
                <button class="btn btn-warning" type="submit">Добавить комикс</button>
            </form>
        </div>
    </main>
</@c.page>