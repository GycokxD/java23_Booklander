<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Возврат книги</title>
    <link rel="stylesheet" href="/css/styles.css">
    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .book-list {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .book-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            width: 200px;
            text-align: center;
            background-color: #f9f9f9;
        }
        .book-card h3 {
            margin: 0 0 10px;
            font-size: 18px;
        }
        .book-card p {
            margin: 5px 0;
            color: #555;
        }
        .book-card button {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            background-color: #2da562;
            color: white;
            cursor: pointer;
        }
        .book-card button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Возврат книги</h1>
        <div class="book-list">
            <#list books as book>
                <#if book.borrowed>
                    <div class="book-card">
                        <h3>${book.title}</h3>
                        <p>Автор: ${book.author}</p>
                        <p>Жанр: ${book.genre}</p>
                        <form action="/return/${book.id}" method="get">
                            <button type="submit">Вернуть</button>
                        </form>
                    </div>
                </#if>
            </#list>
        </div>
        <a href="/book-actions" class="button">Назад</a>
    </div>
</body>
</html>