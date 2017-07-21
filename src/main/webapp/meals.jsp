<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
<tr>
    <th>Description</th>
    <th>Calories</th>
    <th>Time</th>
</tr>
<c:forEach items="${mealList}" var="mealWithExceedList">
    <tr>
        <c:if test="${mealWithExceedList.isExceed()}">
        <td style="color:#ff0000">${mealWithExceedList.getDescription()}"</td>
        <td style="color:#ff0000">"${mealWithExceedList.getCalories()}"</td>
        <td style="color:#ff0000">"${mealWithExceedList.getFormattedDate()}"</td>
        </c:if>
        <c:if test="${!mealWithExceedList.isExceed()}">
            <td style="color:#009933">${mealWithExceedList.getDescription()}"</td>
            <td style="color:#009933">"${mealWithExceedList.getCalories()}"</td>
            <td style="color:#009933">"${mealWithExceedList.getFormattedDate()}"</td>
        </c:if>
    </tr>
</c:forEach>
</table>
</body>
</html>

