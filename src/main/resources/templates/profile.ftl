<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <main style="text-align: center">
        <h1><strong>Профиль</strong></h1>
        <div class="container border" style="text-align: left">
            <div>
                <p>Логин: ${users.username}</p>
                <p>Имя: ${users.firstName!""}</p>
                <p>Фамилия: ${users.lastName!""}</p>
                <p>email: ${users.email!""}</p>
                <p>Город: ${users.city!""}</p>
                <p>Адрес: ${users.adress!""}</p>
            </div>
            <a href="/profileEditing/${users.username}" class="btn btn-warning">Редактировать</a>
        </div>
        <h3><strong>Мои баллы: ${users.points!""}</strong></h3>
        <br>
        <h3><strong>Список желаемого</strong></h3>
        <#list wishlists as wishlist>
            <div class="container border">
                <div class="col-lg-3 col-md-6 mb-4">
                    <div class="card mt-4">
                        <div class="view overlay">
                            <img src="/imgs/${products.getProduct(wishlist.productId).img}" alt="" class="card-img-top"
                                 style="width: 255px; height: 390px;"/>
                            <a href="/menu/${products.getProduct(wishlist.productId).id}">
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>
                        <div class="card-body text-center pb-0" style="height: 120px">
                            <div class="container-fluid py-auto">
                                <a href="/menu/${products.getProduct(wishlist.productId).id}" class="black-text">
                                    <h5 style="display: block; font-size: 1em">${products.getProduct(wishlist.productId).name}</h5>
                                </a>
                            </div>
                            <div class="container-fluid" style="position: absolute; bottom: 0; right: 0">
                                <h5 class="mt-auto pt-auto">
                                    <strong>
                                        <a href="/menu/${products.getProduct(wishlist.productId).id}" class="dark-grey-text" style="font-size: 1.5em">
                                            ${products.getProduct(wishlist.productId).price}₽
                                        </a>
                                    </strong>
                                </h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <br>
        <h3><strong>Мои отзывы</strong></h3>
        <#list reviews as review>
            <div class="container mt-2">
                <div class="row border-bottom border-dark">
                    <h4 class="mb-0">
                    <span class="mt-2" style="display: inline-block">
                        Имя товара:
                    </span>
                        <strong>
                            ${products.getProduct(review.productId).name}
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
                <div class="row" style="text-align: left">
                    <a class="btn btn-sm btn-warning" href="/deleteReview/${review.productId}/${review.id}">Удалить</a>
                </div>
            </div>
        </#list>
    </main>
</@c.page>