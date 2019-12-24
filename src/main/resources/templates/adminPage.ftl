<#import 'parts/common.ftl' as a>

<@a.page>
    <main class="container" style="text-align: center">
        <h1><strong>Панель администратора</strong></h1>
        <div class="container-fluid border">
            <h2 class="mt-3"><strong>Управление товарами</strong></h2>
            <div class="container" style="text-align: left">
                <h4>Редактирование товаров:</h4>
                <h6><a href="/addProduct">Добавление товаров</a></h6>
                <h6><a href="/getProducts">Изменение товаров</a></h6>
                <h6><a href="/addDiscount">Добавить скидку на группу товаров</a></h6>
                <h4>Редактирование характеристик</h4>
                <h6><a href="/addCharacteristics">Добавить характеристику</a></h6>
                <h6><a href="/getCharacteristics">Изменение характеристик</a></h6>
            </div>
            <h2 class="mt-3"><strong>Управление пользователями</strong></h2>
            <div class="container">
                <a href="/user" class="btn red white-text">Список пользователей</a>
            </div>
            <h2 class="mt-3"><strong>Статистика</strong></h2>
            <div class="container">
                <a href="/getStatistic" class="btn red white-text">Статистика</a>
            </div>
            <h2 class="mt-3"><strong>Последние отзывы</strong></h2>
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
                            <h4 class="ml-auto">
                    <span class="mt-2" style="display: inline-block">
                        Товар:
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
                            <a class="btn btn-sm btn-warning" href="/deleteReview/${review.id}" >Удалить</a>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </main>
</@a.page>