<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Информация о сотруднике</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Информация о сотруднике</h1>
        <div class="card">
            <p><strong>Имя:</strong> ${employee.name}</p>
            <p><strong>Email:</strong> ${employee.email}</p>
            <h2>Книги на руках:</h2>
            <ul>
                <#list employee.borrowedBooks as book>
                    <li>${book.title} (${book.author})</li>
                </#list>
            </ul>
        </div>
        <a href="/books" class="button">Назад к списку книг</a>
    </div>
</body>
</html>