<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список книг</title>
</head>
<body>
    <h1>Список книг</h1>
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Автор</th>
            <th>Жанр</th>
            <th>Статус</th>
            <th>Действие</th>
        </tr>
        <#list books as book>
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.genre}</td>
                <td>${book.borrowed?string("Выдана", "Доступна")}</td>
                <td><a href="/bookinfo">Подробнее</a></td>
            </tr>
        </#list>
    </table>
</body>
</html>