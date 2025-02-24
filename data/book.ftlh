<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Информация о книге</title>
</head>
<body>
    <h1>Информация о книге</h1>
    <p><strong>Название:</strong> ${book.title}</p>
    <p><strong>Автор:</strong> ${book.author}</p>
    <p><strong>Жанр:</strong> ${book.genre}</p>
    <p><strong>Статус:</strong> ${book.borrowed?string("Выдана", "Доступна")}</p>
    <a href="/books">Назад к списку книг</a>
</body>
</html>