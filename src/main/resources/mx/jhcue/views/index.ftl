<#-- @ftlvariable name="" type="mx.jhcue.views.HomeView" --></#-->
<html>
<head>
    <title>Homes Location on Google Maps</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="//cdn.muicss.com/mui-0.9.9-rc2/css/mui.min.css" rel="stylesheet" type="text/css"/>
    <script src="//cdn.muicss.com/mui-0.9.9-rc2/js/mui.min.js"></script>
</head>
<body>
<div class="mui-container">
    <table class="mui-table">
        <thead>
        <tr>
            <th>id</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <#list homes as home>
            <tr>
                <td>${home.id}</td>
                <td>${home.streetAddressLine1}<br/>
                    ${home.streetAddressLine2}<br/>
                    ${home.city}<br/>
                    ${home.state}&nbsp;${home.zipCode}<br/>
                    ${home.country}
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</body>
</body>
</html>