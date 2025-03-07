<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль сотрудника</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Профиль сотрудника</h1>
        <#if user??>
            <div class="profile-info">
                <h2>${user.name}</h2>
                <p><strong>Email:</strong> ${user.email}</p>
            </div>

            <h2>Книги на руках:</h2>
            <#if borrowedBooks?? && borrowedBooks?size gt 0>
                <ul>
                    <#list borrowedBooks as bookId>
                        <#assign book = books?filter(b -> b.id == bookId)?first>
                        <#if book??>
                            <li>${book.title} (${book.author})</li>
                        <#else>
                            <li>Книга с ID ${bookId} не найдена.</li>
                        </#if>
                    </#list>
                </ul>
            <#else>
                <p>У вас нет книг на руках.</p>
            </#if>

            <h2>История взятых книг:</h2>
            <#if user.borrowedBooksHistory?? && user.borrowedBooksHistory?size gt 0>
                <ul>
                    <#list user.borrowedBooksHistory as bookId>
                        <#assign book = books?filter(b -> b.id == bookId)?first>
                        <#if book??>
                            <li>${book.title} (${book.author})</li>
                        <#else>
                            <li>Книга с ID ${bookId} не найдена.</li>
                        </#if>
                    </#list>
                </ul>
            <#else>
                <p>Вы ещё не брали книг.</p>
            </#if>
        <#else>
            <p>Пожалуйста, <a href="/login">войдите в систему</a>, чтобы просмотреть профиль.</p>
        </#if>
        <a href="/books" class="button">Назад к списку книг</a>
        <a href="/book-actions" class="button">Управление книгами</a>
        <a href="/logout" class="button">Выйти из системы</a>
        <a href="/employee" class="button">К списку сотрудников</a>
    </div>
</body>
</html>