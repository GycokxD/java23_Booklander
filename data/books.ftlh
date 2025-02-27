<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список книг</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Список книг</h1>
        <table>
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
                    <td><a href="/bookinfo" class="button">Подробнее</a></td>
                </tr>
            </#list>
        </table>
    </div>
</body>
</html>