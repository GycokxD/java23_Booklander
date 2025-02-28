<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход в систему</title>
    <link rel="stylesheet" href="/css/styles.css">
    <style>
        .login-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 2rem;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .login-container h1 {
            text-align: center;
            color: #2da562;
            margin-bottom: 1.5rem;
            font-size: 2rem;
        }

        .login-container .form-element {
            margin-bottom: 1.5rem;
        }

        .login-container label {
            display: block;
            margin-bottom: 0.5rem;
            color: #555;
            font-weight: bold;
        }

        .login-container input[type="email"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            color: #333;
            transition: border-color 0.3s ease;
        }

        .login-container input[type="email"]:focus,
        .login-container input[type="password"]:focus {
            border-color: #2da562;
            outline: none;
        }

        .login-container button[type="submit"] {
            width: 100%;
            padding: 0.75rem;
            background-color: #2da562;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-container button[type="submit"]:hover {
            background-color: #248a4f;
        }

        .login-container .message {
            margin-top: 1rem;
            text-align: center;
        }

        .login-container .message.error {
            color: #e74c3c;
        }

        .login-container p {
            text-align: center;
            margin-top: 1rem;
        }

        .login-container a {
            color: #2da562;
            text-decoration: none;
            font-weight: bold;
        }

        .login-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-container">
            <h1>Вход в систему</h1>
            <form action="/login" method="post">
                <div class="form-element">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-element">
                    <label for="password">Пароль:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Войти</button>
            </form>
            <#if error?? && error>
                <div class="message error">Авторизоваться не удалось: неверный email или пароль.</div>
            </#if>
            <p>Еще не зарегистрированы? <a href="/register">Зарегистрируйтесь</a></p>
        </div>
    </div>
</body>
</html>