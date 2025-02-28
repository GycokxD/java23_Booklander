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
            <p><strong>Имя:</strong> ${employee.name!"Неизвестно"}</p>
            <p><strong>Email:</strong> ${employee.email!"Неизвестно"}</p>
            <h2>Книги на руках:</h2>
            <ul>
                <#if employee.borrowedBooks?? && employee.borrowedBooks?size gt 0>
                    <#list employee.borrowedBooks as bookId>
                        <#assign book = books?filter(b -> b.id == bookId)?first>
                        <#if book??>
                            <li>${book.title} (${book.author})</li>
                        <#else>
                            <li>Книга с ID ${bookId} не найдена.</li>
                        </#if>
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