<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/test.js" />"></script>
</head>

<body>
<!--Div that will hold the pie chart-->
<div id="chart_div"></div>
<input type="hidden" id="resolution" value='${resolution}'/>
</body>
</html>