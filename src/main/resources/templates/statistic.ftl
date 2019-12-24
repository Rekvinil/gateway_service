<#import "parts/commonForForm.ftl" as a>

<@a.page>
    <main>
        <h1 class="text-center"><strong>Статистика</strong></h1>
        <div class="container border">
            <h3>Всего сделано заказов: <strong>${count}</strong></h3>
            <h3>Доход: <strong>${price}Р</strong></h3>
            <h3>В этом месяце заказов: <strong>${countByDate}</strong></h3>
        </div>
    </main>
</@a.page>