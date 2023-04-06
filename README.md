# TwoFactorLogin

Aplikacja jest symulacją logowania dwuetapowego, napisaną w języku Java z wykorzystaniem technologii JavaFX, JDBC, javax.mail oraz biblioteki jBCrypt.

Po uruchomieniu aplikacji, użytkownik ma możliwość zalogowania się do istniejącego konta lub założenia nowego. Aby zalogować się, użytkownik musi wprowadzić swój adres e-mail oraz hasło. Jeśli dane logowania są poprawne, aplikacja wysyła na podany adres e-mail wygenerowany kod weryfikacyjny. Użytkownik musi wprowadzić ten kod, aby móc zalogować się do systemu.

Jeśli użytkownik nie ma jeszcze konta, może je założyć, wprowadzając swoje dane osobowe, adres e-mail i hasło. Hasło jest szyfrowane za pomocą biblioteki jBCrypt przed zapisaniem do bazy danych.


# Przebieg działania:

![2fa-1](https://user-images.githubusercontent.com/116314668/230497863-a0ff9e70-565a-4c63-97a2-823a3196df05.JPG)
![2fa-2](https://user-images.githubusercontent.com/116314668/230499129-003cebed-5df8-4c98-b69e-c07fd2ed2846.JPG)
![2fa-3](https://user-images.githubusercontent.com/116314668/230499135-dc157375-c24a-447d-b9d2-b677d8e354eb.JPG)
![2fa-4](https://user-images.githubusercontent.com/116314668/230499139-1d806fc1-fcf4-44db-9a25-7bcfba605920.JPG)
![2fa-5](https://user-images.githubusercontent.com/116314668/230499142-0f0e2599-5570-40b3-bd77-3880a3c97ec7.JPG)
![2fa-6](https://user-images.githubusercontent.com/116314668/230499150-7f970542-4880-443c-9e71-8931a89db287.JPG)
