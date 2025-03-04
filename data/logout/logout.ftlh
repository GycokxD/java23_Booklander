<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход в систему</title>
    <link rel="stylesheet" href="/css/styles.css">
    <style>
        .logout-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 2rem;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .logout-container h1 {
            text-align: center;
            color: #2da562;
            margin-bottom: 1.5rem;
            font-size: 2rem;
        }

        .logout-container .form-element {
            margin-bottom: 1.5rem;
        }

        .logout-container label {
            display: block;
            margin-bottom: 0.5rem;
            color: #555;
            font-weight: bold;
        }

        .logout-container input[type="email"],
        .logout-container input[type="password"] {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            color: #333;
            transition: border-color 0.3s ease;
        }

        .logout-container input[type="email"]:focus,
        .logout-container input[type="password"]:focus {
            border-color: #2da562;
            outline: none;
        }

        .logout-container button[type="submit"] {
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

        .logout-container button[type="submit"]:hover {
            background-color: #248a4f;
        }

        .logout-container .message {
            margin-top: 1rem;
            text-align: center;
        }

        .logout-container .message.error {
            color: #e74c3c;
        }

        .logout-container p {
            text-align: center;
            margin-top: 1rem;
        }

        .logout-container a {
            color: #2da562;
            text-decoration: none;
            font-weight: bold;
        }

        .logout-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logout-container">
            <h1>Вы уверены, что хотите выйти?</h1>
                <form action="/logout" method="post">
                    <button type="submit">Выйти</button>
                </form>
        </div>
    </div>
</body>
</html>