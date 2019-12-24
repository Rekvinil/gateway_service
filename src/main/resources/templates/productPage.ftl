<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <main class="text-center">
        <h1>
            <strong>Товар</strong>
        </h1>
        <div class="container mt-3 border rounded">
            <div class="row">
                <div class="col-md-6 mb-4">
                    <img src="/imgs/${product.img}" alt="spiderman" class="img-fluid" style="height: 40em"/>
                </div>
                <div class="col-md-6 mb-4">
                    <div class="p-4">
                        <p class="lead font-weight-bold">Описание</p>
                        <h6>
                            ${description}
                        </h6>
                        <p class="lead font-weight-bold">Характеристики</p>
                        <div class="row border border-black mb-0 pb-0">
                            <div class="col">
                                <h6>Название:</h6>
                            </div>
                            <div class="col">${product.name}</div>
                        </div>
                        <div class="row border border-black mb-0 pb-0">
                            <div class="col">
                                <h6>Сценарист:</h6>
                            </div>
                            <div class="col">${author}</div>
                        </div>
                        <div class="row border border-black mb-0 pb-0">
                            <div class="col">
                                <h6>Художник:</h6>
                            </div>
                            <div class="col">${artist}</div>
                        </div>
                        <#list characteristics as characteristic>
                            <div class="row border border-black mb-0 pb-0">
                                <div class="col">
                                    <h6>${characteristic.characteristics.name}:</h6>
                                </div>
                                <div class="col">${characteristic.value}</div>
                            </div>
                        </#list>
                        <div class="row border border-black mb-0 pb-0">
                            <div class="col">
                                <h6>Количество товара на складе:</h6>
                            </div>
                            <div class="col">${product.count}</div>
                        </div>
                        <h2 class="mt-3">
                            <strong class="dark-grey-text">
                                <#if product.discount != "0">
                                    Цена: <span>
                                    <del>${product.price}</del>
                                </span>
                                    <span>
                                        ${product.price*(1-product.discount?number/100)}₽
                                    </span>
                                    <#else>
                                        Цена: ${product.price}₽
                                </#if>
                            </strong>
                        </h2>
                        <#if nameUser!="Вход/Регистрация"&&!coder??>
                            <div class="row">
                                <form method="post" action="/addToCart">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="hidden" name="username" value="${nameUser}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token!""}"/>
                                    <button class="btn btn-warning" type="submit">Добавить в корзину</button>
                                </form>
                                <form class="ml-auto" method="post" action="/addToWishList">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="hidden" name="username" value="${nameUser}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button class="btn red" type="submit">В список желаемого</button>
                                </form>
                            </div>
                            <#else>
                            <div class="text-center">Чтобы купить товар или добавить его в список желаемого войдите или зарегистрируйтесь. Или подтвердите адрес электронной почты.</div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <h2 class="mt-3"><strong>Отзывы</strong></h2>
        <div class="container border  rounded ">
            <#list reviews as review>
                <div class="container mt-4">
                    <div class="row border-bottom border-dark">
                        <h4 class="mb-0">
                    <span class="mt-2" style="display: inline-block">
                        Пользователь:
                    </span>
                            <strong>
                                ${review.user.username}
                            </strong>
                        </h4>
                        <h4 class="ml-auto" style="align-items: center">
                    <span class="mt-2" style="display: inline-block">
                       Оценка:
                    </span>
                            <strong>
                                ${review.mark}
                            </strong>
                        </h4>
                    </div>
                    <div class="row" style="text-align: left">
                        ${review.text}
                    </div>
                    <#if isAdmin>
                    <div class="row" style="text-align: left">
                        <a class="btn btn-sm btn-warning" href="/deleteReview/${product.id}/${review.id}" >Удалить</a>
                    </div>
                    </#if>
                </div>
            </#list>
            <h6 class="mt-4"><strong>Оставьте свой отзыв</strong></h6>
            <#if nameUser!="Вход/Регистрация"&&!coder??>
                <div class="container border border-dark" style="text-align: left">
                    <form method="post" action="/menu/addReview">
                        <div class="form-group">
                            <label for="inputState">Оценка</label>
                            <br>
                            <select id="inputState" class="form-control-sm" name="mark">
                                <option selected>5</option>
                                <option>4.5</option>
                                <option>4</option>
                                <option>3.5</option>
                                <option>3</option>
                                <option>2.5</option>
                                <option>2</option>
                                <option>1.5</option>
                                <option>1</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">Отзыв</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" name="text"></textarea>
                        </div>
                        <#if nameUser??>
                            <input type="hidden" name="nameUser" value="${nameUser}">
                        <#else>
                            <input type="hidden" name="nameUser" value="Неизвестно">
                        </#if>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="product" value="${product.id}">
                        <button class="btn btn-warning" type="submit">Оставить отзыв</button>
                    </form>
                </div>
                <#else>
                <div>Чтобы оставить свой отзыв зарегистрируйтесь или активируйте почту</div>
            </#if>
        </div>
    </main>
</@c.page>