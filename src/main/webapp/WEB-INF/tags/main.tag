<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>

<html>
<head>
    <title>${title}</title>

    <script src="../resources/libs/jquery.js"></script>

    <script src="../resources/js/comment-box.js"></script>
    <link rel="stylesheet" type="text/css" href="../resources/css/comment-box.css"/>

    <link rel="stylesheet" type="text/css" href="../resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="../resources/css/scroll-box.css"/>

    <link rel="stylesheet" type="text/css" href="../resources/libs/aui.css"/>
</head>
<body>
<div id="page" class="left-margin">
    <h2>Welcome to "Test Portal"</h2>
    <jsp:doBody/>
</div>
</body>
</html>