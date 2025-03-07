<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Список сотрудников</h1>
        <table>
            <tr>
                <th>Имя</th>
                <th>Email</th>
                <th>Книги на руках</th>
                <th>История взятых книг</th>
            </tr>
            <#list employees as employee>
                <tr>
                    <td>${employee.name}</td>
                    <td>${employee.email}</td>
                    <td>
                        <#if employee.borrowedBooks?? && employee.borrowedBooks?size gt 0>
                            <ul>
                                <#list employee.borrowedBooks as bookId>
                                    <#assign book = books?filter(b -> b.id == bookId)?first>
                                    <#if book??>
                                        <li>${book.title} (${book.author})</li>
                                    <#else>
                                        <li>Книга с ID ${bookId} не найдена.</li>
                                    </#if>
                                </#list>
                            </ul>
                        <#else>
                            Нет книг на руках.
                        </#if>
                    </td>
                    <td>
                        <#if employee.borrowedBooksHistory?? && employee.borrowedBooksHistory?size gt 0>
                            <ul>
                                <#list employee.borrowedBooksHistory as bookId>
                                    <#assign book = books?filter(b -> b.id == bookId)?first>
                                    <#if book??>
                                        <li>${book.title} (${book.author})</li>
                                    <#else>
                                        <li>Книга с ID ${bookId} не найдена.</li>
                                    </#if>
                                </#list>
                            </ul>
                        <#else>
                            Нет истории взятых книг.
                        </#if>
                    </td>
                </tr>
            </#list>
        </table>
        <a href="/books" class="button">Назад к списку книг</a>
    </div>
</body>
</html>