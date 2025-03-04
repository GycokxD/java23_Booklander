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
            <#if borrowedBooks?exists>
                <ul>
                    <#list borrowedBooks as bookId>
                        <#assign book = books?filter(b -> b.id == bookId)?first>
                        <li>${book.title} - ${book.author}</li>
                    </#list>
                </ul>
            <#else>
                <p>У вас нет взятых книг.</p>
            </#if>
        </div>
        <a href="/books" class="button">Назад к списку книг</a>
        <a href="/book-actions" class="button">Управление книгами</a>
        <a href="/logout" class="button">Выйти из профиля</a>
    </div>
</body>
</html>