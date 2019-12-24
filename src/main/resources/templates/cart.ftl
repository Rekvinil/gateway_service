<#import "parts/commonForForm.ftl" as a>
<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    nameUser = user.getUsername()
    >
<#else>
    <#assign
    nameUser = "Вход/Регистрация"
    >
</#if>
<@a.page>
    <main>
        <h1 style="text-align: center"><strong>Корзина</strong></h1>
        <div class="container border">
            <#if !carts?has_content>
                <div class="text-center">В вашей корзине нет товаров</div>
                <#else>
                    <div class="text-center">
                        <form action="/orderAccess" method="get">
                            <#list carts as cart>
                                <div class="row ml-1 border mt-3 mr-1">
                                    <div class="col-3" style="margin-top: 0.7em">
                                        <h5>
                                            <strong>
                                                ${product.getProduct(cart.productId).name}
                                            </strong>
                                        </h5>
                                    </div>
                                    <div class="col-3" style="margin-top: 0.7em">
                                        <h5>
                                            <strong>
                                                Цена: ${product.getProduct(cart.productId).price*(1-product.getProduct(cart.productId).discount?number/100)}
                                            </strong>
                                        </h5>
                                    </div>
                                    <div class="col-3">
                                        <h5 class="mt-2"><strong>
                                                Количество:
                                                <input type="number" id="count" name="count" class="form-control"
                                                       style="width: 4em; display: inline-block" value="${cart.count}">
                                            </strong>
                                        </h5>
                                    </div>
                                    <div class="col-3">
                                        <h5 style="margin-top: 0.7em"><strong>
                                                <a href="/deleteCart/${cart.id}" class="btn-sm btn-warning">Удалить</a>
                                            </strong>
                                        </h5>
                                    </div>
                                </div>
                                <input type="hidden" name="productId" value="${cart.productId}">
                            </#list>
                            <h2><strong>Списать баллы</strong></h2>
                            <input type = number name="discount" value="${user.points}" max="${user.points}">
                            <input type="hidden" name="username" value="${nameUser}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <div>
                                <button  class="btn btn-warning" type="submit">
                                    Оформить заказ
                                </button>
                            </div>
                        </form>
                    </div>
            </#if>
        </div>
    </main>
</@a.page>