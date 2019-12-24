<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        nameUser = user.getUsername()
        isAdmin = user.isAdmin()
    >
    <#else>
        <#assign
            nameUser = "Вход/Регистрация"
            code=""
            isAdmin = false
        >
</#if>