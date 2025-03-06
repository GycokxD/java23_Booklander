<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Информация о книге</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Информация о книге</h1>
        <div class="book-details">
            <#if book??>
                <div class="book-image">
                    <#if book.image??>
                        <img src="data/images/1.jpg" alt="${book.title}" style="width: 200px; height: auto;">
                    <#else>
                        <img src="data/images/1.jpg" alt="Нет изображения" style="width: 200px; height: auto;">
                    </#if>
                </div>
                <div class="book-info">
                    <h2>${book.title}</h2>
                    <p><strong>Автор:</strong> ${book.author}</p>
                    <p><strong>Жанр:</strong> ${book.genre}</p>
                    <p><strong>Статус:</strong> ${book.borrowed?string("Выдана", "Доступна")}</p>
                    <p><strong>Описание:</strong> ${book.description!"Описание отсутствует."}</p>
                </div>
            <#else>
                <p>Книга не найдена.</p>
            </#if>
        </div>
        <a href="/books" class="button">Назад к списку книг</a>
    </div>
</body>
</html>