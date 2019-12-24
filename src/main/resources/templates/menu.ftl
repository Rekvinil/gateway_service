<#import 'parts/common.ftl' as c>


<@c.page>
    <main class="text-center mt-5 pt-4">
        <h1>
            <strong>Каталог</strong>
        </h1>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark red mt-3 mb-0">
                <span class="navbar-brand">Категории:</span>
                <button
                        class="navbar-toggler"
                        type="button"
                        data-toggle="collapse"
                        data-target="#nextNav"
                        aria-controls="nextNav"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="nextNav">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a href="/menu" class="nav-link">Все</a>
                        </li>
                        <li class="nav-item">
                            <a href="/menu/Marvel" class="nav-link">Marvel</a>
                        </li>
                        <li class="nav-item">
                            <a href="/menu/DC" class="nav-link">DC</a>
                        </li>
                        <li class="nav-item">
                            <a href="/menu/Bubble" class="nav-link">Bubble</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#collapseExample" role="button"
                               aria-expanded="false" aria-controls="collapseExample">
                                Фильтры
                            </a>
                        </li>
                    </ul>
                    <form class="form-inline" action="/menu/search" method="get">
                        <div class="md-form my-0">
                            <input
                                    type="text"
                                    name="name"
                                    class="form-control mr-sm-2"
                                    placeholder="Поиск"
                                    aria-label="Поиск"
                            />
                        </div>
                    </form>
                </div>
            </nav>
            <div class="collapse" id="collapseExample">
                <div class="container border border-black" style="text-align: left">
                    <form method="get" action="/menu/filter">
                        <div class="container col-3 ml-0 mr-0" style="display: inline-block">
                            <label>Возраст</label>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Категория"
                                       id="exampleRadios1" value="Для детей">
                                <label class="form-check-label" for="exampleRadios1">
                                    Для детей
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Категория"
                                       id="exampleRadios1" value="Для подростков">
                                <label class="form-check-label" for="Для подростков">
                                    Для подростков
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Категория"
                                       id="exampleRadios1" value="Для взрослых">
                                <label class="form-check-label" for="Для взрослых">
                                    Для взрослых
                                </label>
                            </div>
                        </div>
                        <div class="container col-2 ml-0 mr-0" style="display: inline-block; ">
                            <label>Наличие скидки</label>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Скидка"
                                       id="exampleRadios1" value="да">
                                <label class="form-check-label" for="Да">
                                    Да
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Скидка"
                                       id="exampleRadios1" value="нет">
                                <label class="form-check-label" for="exampleRadioss1">
                                    Нет
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Скидка"
                                       id="exampleRadios1" value="нет" checked>
                                <label class="form-check-label" for="Нет">
                                    Неважно
                                </label>
                            </div>
                        </div>
                        <div class="container col-2 ml-0 mr-0" style="display: inline-block; ">
                            <label>Тип</label>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="checkbox" name="Тип"
                                       id="exampleRadios1" value="комикс">
                                <label class="form-check-label" for="комикс">
                                    Комикс
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="checkbox" name="Тип"
                                       id="exampleRadios1" value="манга">
                                <label class="form-check-label" for="exampleRadioss1">
                                    Манга
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="checkbox" name="Тип"
                                       id="exampleRadios1" value="артбук">
                                <label class="form-check-label" for="exampleRadioss1">
                                    Артбук
                                </label>
                            </div>
                        </div>
                        <div class="container col-3 ml-0 mr-0" style="display: inline-block; ">
                            <label>Цена</label>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Цена"
                                       id="exampleRadios1" value="больше">
                                <label class="form-check-label" for="exampleRadioss1">
                                    По возрастанию
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Цена"
                                       id="exampleRadios1" value="меньше">
                                <label class="form-check-label" for="exampleRadioss1">
                                    По убыванию
                                </label>
                            </div>
                            <div class="form-check pr-5">
                                <input class="form-check-input" type="radio" name="Цена"
                                       id="exampleRadios1" value="нет" checked>
                                <label class="form-check-label" for="exampleRadioss1">
                                    Без разницы
                                </label>
                            </div>
                        </div>
                        <div class="col-1 mx-0" style="display: inline-block; bottom: 3em;">
                            <label for="inputState">Страна</label>
                            <br>
                            <select id="inputState" class="form-control-sm" name="Страна">
                                <option selected>Выбор</option>
                                <option value="США">США</option>
                                <option value="Россия">Россия</option>
                                <option value="Япония">Япония</option>
                            </select>
                        </div>
                        <div class="col-1 mx-0" style="display: inline-block;">
                            <button type="submit" class="btn btn-warning " style="color: black">
                                Искать
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row mt-5">
                <#list products as product>
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card">
                            <div class="view overlay">
                                <img src="/imgs/${product.img}" alt="" class="card-img-top"
                                     style="width: 255px; height: 390px;"/>
                                <a href="/menu/${product.id}">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>
                            <div class="card-body text-center pb-0" style="height: 120px">
                                <div class="container-fluid py-auto">
                                    <a href="/menu/${product.id}" class="black-text">
                                        <h5 style="display: block; font-size: 1em">${product.name}</h5>
                                    </a>
                                </div>
                                <div class="container-fluid" style="position: absolute; bottom: 0; right: 0">
                                    <h5 class="mt-auto pt-auto">
                                        <strong>
                                            <a href="/menu/${product.id}" class="dark-grey-text" style="font-size: 1.25em">
                                                <#if product.discount != "0">
                                                    <span>
                                                    <del>${product.price}</del>
                                                </span>
                                                    <span>
                                                    ${product.price*(1-product.discount?number/100)}₽
                                                </span>
                                                <#else>
                                                    <span>
                                                            ${product.price}₽
                                                        </span>
                                                </#if>
                                            </a>
                                        </strong>
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </main>
</@c.page>