<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Профиль пользователя</h1>
        <div class="card">
            <p><strong>Имя:</strong> ${user.name!"Некий пользователь"}</p>
            <p><strong>Email:</strong> ${user.email!"Неизвестно"}</p>
            <h2>Книги на руках:</h2>
            <ul>
                <#if user.borrowedBooks??>
                    <#list user.borrowedBooks as book>
                        <li>${book.title} (${book.author})</li>
                    </#list>
                <#else>
                    <li>Нет книг на руках.</li>
                </#if>
            </ul>
        </div>
        <a href="/books" class="button">Назад к списку книг</a>
    </div>
</body>
</html>