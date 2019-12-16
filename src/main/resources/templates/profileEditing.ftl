<#import "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>



<@c.page>
    <main class="pt-2">
        <h1 class="text-center">
            <strong>Редактирование профиля</strong>
        </h1>
        <div class="container border border-black">
            <form method="post" action="/profileEditing/">
                <br/>
                <div class="form-group">
                    <label>Имя</label>
                    <input type="text" name="firstName" class="form-control" placeholder="Имя" value="${users.firstName!""}"/>
                </div>
                <div class="form-group">
                    <label>Фамилия</label>
                    <input type="text" name="lastName" class="form-control" placeholder="Фамилия" value="${users.lastName!""}"/>
                </div>
                <div class="form-group">
                    <label for="inputPassword4">Пароль</label>
                    <input
                            name="password"
                            type="password"
                            class="form-control"
                            id="inputPassword4"
                            placeholder="Пароль"
                            value="${users.password!""}"
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
                            value="${users.email!""}"
                    />
                </div>
                <div class="form-group">
                    <label for="inputCity">Город</label>
                    <input type="text" name="city" class="form-control" id="inputCity" placeholder="Город" value="${users.city!""}"/>
                </div>
                <div class="form-group">
                    <label for="inputAddress">Адрес</label>
                    <input
                            name="adress"
                            type="text"
                            class="form-control"
                            id="inputAddress"
                            placeholder="Адрес"
                            value="${users.adress!""}"
                    />
                </div>
                <input type="hidden" name="username" value="${users.username}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-warning">Отредактировать</button>
            </form>
        </div>
    </main>
</@c.page>