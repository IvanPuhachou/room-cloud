# room-cloud

This is one of my test tasks. Here is the description of the requirements for it.

Тестовое задание:
Приложение “Комнаты с лампочками”
Заходя на веб страницу, пользователь имеет возможность:
- создать комнату, указав ее имя и страну из списка. Страна нужна, чтобы ограничить пользователям доступ в комнату.
- просмотреть список созданных комнат (включая комнаты, созданные другими пользователями)
- “зайти” в одну из комнат и включить/выключить лампочку, находящуюся в ней.
 
Если страна пользователя отличается от страны комнаты, то при попытке входа в комнату пользователю показывается ошибка.
Определение страны производится по IP адресу пользователя.
Изменение состояния лампочки, должно отображаться для всех пользователей в комнате (как можно быстрее, без перезагрузки страницы)
Логин\регистрация не нужны.
 
Требования к реализации:
- Язык программирования серверной части - Java
- UI может быть тривиальным
- Unit/Integration тесты

this is my first experience of writing a Spring application. There is no identification system for users id 
and you should manually write in room-cloud / src / main / java / application / CountryDetectorUtil.java on line 70, but it runs.
I hope you know how to download and run this project on your IDE.
