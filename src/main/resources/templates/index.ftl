<#import 'parts/common.ftl' as a>

<@a.page>
    <div
            id="carousel-ex"
            class="carousel slide carousel-fade pt-auto mt-5  "
            data-ride="carousel"
    >
        <ol class="carousel-indicators">
            <li class="active" data-target="#carousel-ex" data-slide-to="0"></li>
            <li data-target="#carousel-ex" data-slide-to="1"></li>
            <li data-target="#carousel-ex" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
                <a href="/menu/Marvel">
                    <div
                            class="view"
                            style="background-image: url('/static/img/marvel.jpg'); background-repeat: no-repeat; background-size: cover;"
                    ></div>
                </a>
            </div>
            <div class="carousel-item">
                <a href="/menu/DC">
                    <div
                            class="view"
                            style="background-image: url('/static/img/dc.png'); background-repeat: no-repeat; background-size: cover;"
                    ></div>
                </a>
            </div>
            <div class="carousel-item">
                <a href="/menu/Bubble">
                    <div
                            class="view"
                            style="background-image: url('/static/img/bubble.png'); background-repeat: no-repeat; background-size: cover;"
                    ></div>
                </a>
            </div>
        </div>
        <a
                href="#carousel-ex"
                class="carousel-control-prev"
                role="button"
                data-slide="prev"
        >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        </a>
        <a
                href="#carousel-ex"
                class="carousel-control-next"
                role="button"
                data-slide="next"
        >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
        </a>
    </div>
    <br/>
    <main class="text-center">
        <h1>
            <strong>Добро пожаловать в интернет-магазин Comics Universe!</strong>
        </h1>
        <p>
            Наш магазин специализируется на продаже комиксов различных редакций.
            Здесь собраны лучшие произведения со все стран мира!
        </p>
        <h2><strong>Наши скидки</strong></h2>
        <div class="container">
            <div class="row">
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
                                <div class="container-fluid" style="position: absolute; bottom: 0; right: 0em">
                                    <h5 class="mt-auto pt-auto">
                                        <strong>
                                            <a href="/menu/${product.id}" class="dark-grey-text" style="font-size: 1.25em">
                                                <span>
                                                    <del>${product.price}₽</del>
                                                </span>
                                                <span>
                                                    ${product.price}₽
                                                </span>
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
</@a.page>