<#import 'parts/common.ftl' as a>

<@a.page>
    <main>
        <h1 style="text-align: center"><strong>Выберите товар</strong></h1>
        <div class="container">
            <div class="row">
                <#list products as product>
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card">
                            <div class="view overlay">
                                <img src="/imgs/${product.img}" alt="" class="card-img-top"
                                     style="width: 255px; height: 390px;"/>
                                <a href="/changeProduct/${product.id}">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>
                            <div class="card-body text-center pb-0" style="height: 120px">
                                <div class="container-fluid py-auto">
                                    <a href="/changeProduct/${product.id}" class="black-text">
                                        <h5 style="display: block; font-size: 1em">${product.name}</h5>
                                    </a>
                                </div>
                                <div class="container-fluid" style="position: absolute; bottom: 0; right: 0em">
                                    <h5 class="mt-auto pt-auto">
                                        <strong>
                                            <a href="/changeProduct/${product.id}" class="dark-grey-text" style="font-size: 1.25em">
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
</@a.page>