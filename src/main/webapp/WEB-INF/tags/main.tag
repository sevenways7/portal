<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>

<html>
<head>
    <title>${title}</title>

    <script src="../resources/libs/jquery.js"></script>
    <script src="../resources/js/test-portal.js"></script>

    <link rel="stylesheet" type="text/css" href="../resources/libs/aui.css"/>

    <link rel="stylesheet" type="text/css" href="../resources/css/test-portal.css"/>

    <link rel="stylesheet" type="text/css" href="../resources/css/modal-dialog.css"/>
</head>
<body>
<div id="page" class="left-margin">
    <h2>Welcome to "Test Portal"</h2>
    <jsp:doBody/>
</div>
</body>
</html>