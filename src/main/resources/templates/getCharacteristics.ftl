<#import 'parts/commonForForm.ftl' as a>

<@a.page>
    <main>
        <h1 style="text-align: center"><strong>Список характеристик</strong></h1>
        <div class="container border">
            <div class="mr-auto">
                <table>
                    <tr>
                        <th>Имя характеристики</th>
                        <th>Описание</th>
                    </tr>
                    <#list characteristics as characteristic>
                        <tr>
                            <td>${characteristic.name}</td>
                            <td>${characteristic.description}</td>
                            <td><a href="/changeCharacteristic/${characteristic.id}">Изменить</a></td>
                            <td><a href="/deleteCharacteristic/${characteristic.id}">Удалить</a></td>
                        </tr>
                    </#list>
                </table>
            </div>
        </div>
    </main>
</@a.page>