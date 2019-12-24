<#import "parts/commonForForm.ftl" as a>

<@a.page>
    <main>
        <h1 class="text-center"><strong>Заказ подтвержден!</strong></h1>
        <h1 class="text-center"><strong>Ваш номер заказа: ${order.id}</strong></h1>
        <div class="container text-center">
            <a class="text-center btn btn-warning" href="/getCheck/${order.id}.pdf">Скачать чек</a>
        </div>
    </main>
</@a.page>