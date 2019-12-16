<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        nameUser = user.getUsername()
    >
    <#else>
        <#assign
            nameUser = "Вход/Регистрация"
        >
</#if>