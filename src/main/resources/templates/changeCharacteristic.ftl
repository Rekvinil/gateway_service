<#import 'parts/commonForForm.ftl' as a>

<@a.page>
    <main>
        <h1 style="text-align: center"><strong>Изменение характеристики</strong></h1>
        <div class="container border">
            <form method="post" action="/addCharacteristics">
                <div class="form-group">
                    <label>Имя</label>
                    <input class="form-control" name="name" value="${characteristic.name}" placeholder="Введите имя" type="text">
                </div>
                <div class="form-group">
                    <label>Описание</label>
                    <input class="form-control" type="text" name="description" value="${characteristic.description}" placeholder="Введите описание">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" value="${characteristic.id}" name="id">
                <button class="btn btn-warning" type="submit">Изменить</button>
            </form>
        </div>
    </main>
</@a.page>