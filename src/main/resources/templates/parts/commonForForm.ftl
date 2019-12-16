<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="utf-8" />
        <meta
                name="viewport"
                content="width=device-width, initial-scale=1, shrink-to-fit=no"
        />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <title>Comics Universe</title>
        <!-- Font Awesome -->
        <link
                rel="stylesheet"
                href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
        />
        <!-- Bootstrap core CSS -->
        <link href="/static/css/bootstrap.min.css" rel="stylesheet" />
        <!-- Material Design Bootstrap -->
        <link href="/static/css/mdb.min.css" rel="stylesheet" />
        <!-- Your custom styles (optional) -->
        <link href="/static/css/style.css" rel="stylesheet" />
        <style>
            .body{
                height: 100%;
            }
            html,
            body,
            .carousel {
                height: 29em;
            }
        </style>
    </head>

    <body>
    <#include "navbar.ftl">
    <div class="container-fluid mx-0 px-0" style="margin-top: 5em">
        <#nested>
    </div>
    <script type="text/javascript" src="/static/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="/static/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="/static/js/mdb.min.js"></script>
    </body>
    </html>
</#macro>