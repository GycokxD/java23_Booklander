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
        <div class="card">
            <p><strong>Название:</strong> ${book.title}</p>
            <p><strong>Автор:</strong> ${book.author}</p>
            <p><strong>Жанр:</strong> ${book.genre}</p>
            <p><strong>Статус:</strong> ${book.borrowed?string("Выдана", "Доступна")}</p>
        </div>
        <a href="/books" class="button">Назад к списку книг</a>
    </div>
</body>
</html>