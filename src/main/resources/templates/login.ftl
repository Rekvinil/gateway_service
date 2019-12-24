<#import "parts/commonForForm.ftl" as c>
<#import  "parts/login.ftl" as l>

<@c.page>
    <main class="pt-2">
        <h1 class="text-center">
            <strong>Вход</strong>
        </h1>
        <div class="text-center">
            ${message?ifExists}
        </div>
        <div class="container col-4 border border-black">
            <form method="post" action="/login">
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
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-row">
                    <button type="submit" class="btn btn-warning">Вход</button>
                    <a class="btn red ml-auto" href="/registration">Регистрация</a>
                </div>
            </form>
        </div>
    </main>
</@c.page>