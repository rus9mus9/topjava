<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
       .normal {color: green;}
       .exceeded {color: red;}
    </style>
</head>
<body>
<section>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
<tr>
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
</tr>
</thead>
<c:forEach items="${mealList}" var="meal">
    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
        <td>${meal.formattedDate}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
    </tr>
</c:forEach>
</table>
</section>
</body>
</html>

