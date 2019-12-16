<#import "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>



<@c.page>
    ${message!""}
    <main class="pt-2">
        <h1 class="text-center">
            <strong>Регистрация</strong>
        </h1>
        <div class="container border border-black">
            <form method="post" action="/registration">
                <br/>
                <div class="form-group">
                    <label>Имя</label>
                    <input type="text" name="firstName" class="form-control" placeholder="Имя"/>
                </div>
                <div class="form-group">
                    <label>Фамилия</label>
                    <input type="text" name="lastName" class="form-control" placeholder="Фамилия"/>
                </div>
                <div class="form-group">
                    <label for="inputLogin">Логин</label>
                    <input
                            name="username"
                            type="text"
                            class="form-control"
                            id="inputLogin"
                            placeholder="Логин"
                    />
                </div>
                <div class="form-group">
                    <label for="inputPassword4">Пароль</label>
                    <input
                            name="password"
                            type="password"
                            class="form-control"
                            id="inputPassword4"
                            placeholder="Пароль"
                    />
                </div>
                <div class="form-group">
                    <label for="inputEmail4">Email</label>
                    <input
                            name="email"
                            type="email"
                            class="form-control"
                            id="inputEmail4"
                            placeholder="Email"
                    />
                </div>
                <div class="form-group">
                    <label for="inputCity">Город</label>
                    <input type="text" name="city" class="form-control" id="inputCity" placeholder="Город"/>
                </div>
                <div class="form-group">
                    <label for="inputAddress">Адрес</label>
                    <input
                            name="adress"
                            type="text"
                            class="form-control"
                            id="inputAddress"
                            placeholder="Адрес"
                    />
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-warning">Зарегистрироваться</button>
            </form>
        </div>
    </main>
</@c.page>