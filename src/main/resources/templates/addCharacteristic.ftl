<#import "parts/commonForForm.ftl" as c>

<@c.page>
    <main>
        <h1 style="text-align: center"><strong>Добавление характеристики</strong></h1>
        <div class="container border">
            <form method="post" action="/addCharacteristics">
                <div class="form-group">
                    <label>Имя</label>
                    <input class="form-control" name="name" placeholder="Введите имя" type="text">
                </div>
                <div class="form-group">
                    <label>Описание</label>
                    <input class="form-control" type="text" name="description" placeholder="Введите описание">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-warning" type="submit">Добавить характеристику</button>
            </form>
        </div>
    </main>
</@c.page>