<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="/css/register.css">
</head>
<body>
    <form action="/register" method="post">
        <h1>Регистрация сотрудника</h1>
        <div class="form-element">
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" required>
        </div>
        <div class="form-element">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-element">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-element">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form>
</body>
</html>