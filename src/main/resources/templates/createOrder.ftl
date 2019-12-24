<#import "parts/commonForForm.ftl" as a>

<@a.page>
    <main>
        <h1 class="text-center"><strong>Оформление заказа</strong></h1>
        <div class="container border">
            <h2 class="text-center"><strong>Пользователь</strong></h2>
            <div>Фамилия: ${user.firstName!""}</div>
            <div>Имя: ${user.lastName!""}</div>
            <div>Email: ${user.email!""}</div>
            <div>Город: ${user.city!""}</div>
            <div>Адрес: ${user.adress!""}</div>
            <h2 class="text-center"><strong>Список товаров</strong></h2>
            <form action="/createOrder">
                <#list carts as cart>
                    <div class="row ml-1 border mt-3 mr-1 text-center">
                        <div class="col-4" style="margin-top: 0.5em">
                            <h5>
                                <strong>
                                    ${product.getProduct(cart.productId).name}
                                </strong>
                            </h5>
                        </div>
                        <div class="col-4" style="margin-top: 0.5em">
                            <h5>
                                <strong>
                                    Цена: ${product.getProduct(cart.productId).price*(1-product.getProduct(cart.productId).discount?number/100)}
                                </strong>
                            </h5>
                        </div>
                        <div class="col-4">
                            <h5><strong>
                                    Количество: ${cart.count}
                                </strong>
                            </h5>
                        </div>
                    </div>
                    <input type="hidden" name="cartId" value="${cart.id}">
                </#list>
                <h2 class="text-center"><strong>Сумма заказа: ${price}</strong></h2>
                <input type="hidden" name="userId" value="${user.username}">
                <input type="hidden" name="discount" value="${discount}">
                <div class="text-center">
                    <button type="submit" class="btn btn-warning">Подтвердить заказ</button>
                </div>
            </form>
        </div>
    </main>
</@a.page>