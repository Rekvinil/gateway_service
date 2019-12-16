<#include "security.ftl">

<nav
        class="navbar fixed-top navbar-expand-lg navbar-light red scrolling-navbar"
>
    <div class="container-fluid" style="justify-content: space-between;">
        <a href="/" class="navbar-brand waves-effect cl">
            <strong
                    style="color: yellow; font-size:150%; font-family: 'American';"
            >Comics Universe</strong
            >
        </a>
        <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarContent"
                aria-controls="navbarContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a href="/" class="nav-link waves-effect">Главная страница</a>
                </li>
                <li class="nav-item ">
                    <a href="/menu" class="nav-link waves-effect">Каталог</a>
                </li>
                <li class="nav-item ">
                    <a href="#" class="nav-link waves-effect">О нас</a>
                </li>
                <li class="nav-item">
                    <a href="/user" class="nav-link waves-effect">Панель администратора</a>
                </li>
            </ul>
            <ul class="navbar-nav nav-flex-icons">
                <li class="nav-item d-flex align-items-center">
                    <a href="#" class="nav-link waves-effect">
                        <span class="badge yellow z-depth-1 mr-1">2</span>
                        <i class="fa fa-shopping-cart"></i>
                        <span class="clearfix d-none d-sm-inline-block">Корзина</span>
                    </a>
                </li>
                <#if nameUser=="Вход/Регистрация">
                    <li class="nav-item ">
                        <a href="/login" class="btn btn-warning" style="color: black">
                            ${nameUser}
                        </a>
                    </li>
                <#else>
                    <li class="nav-item ">
                        <a href="/profile/${nameUser}" class="btn btn-warning" style="color: black">
                            Мой профиль
                        </a>
                    </li>
                    <li class="nav-item ">
                        <form method="post" action="/logout">
                            <input class="btn btn-warning" type="submit"  value="Выход" style="color: black">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </form>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>