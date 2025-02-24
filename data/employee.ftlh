<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Информация о сотруднике</title>
</head>
<body>
    <h1>Информация о сотруднике</h1>
    <p><strong>Имя:</strong> ${employee.name}</p>
    <p><strong>Email:</strong> ${employee.email}</p>
    <h2>Книги на руках:</h2>
    <ul>
        <#list employee.borrowedBooks as book>
            <li>${book.title} (${book.author})</li>
        </#list>
    </ul>
    <a href="/books">Назад к списку книг</a>
</body>
</html>