# ForThePurityOfArzamasRegionServerApi

## GET method/users.getListAll
### Возврат
Возвращает данные всех пользователей



## GET method/users.getListById
### Параметры
**'user_ids'** содержит идентификаторы пользователей, даные о которых необхдимо получить
### Возврат
Возвращает данные запрашиваемых пользователей. Если данных о пользователе нет, вернет **null**



## GET method/users.getListByEmail
### Параметры
**'email'** содержит **email**, по которому производится поиск поьзователей
### Возврат
**Возвращает** данные пользователей с указанным **email**



## POST method/users.setById
### Параметры
**'user_id'** содержит идентификатор пользователя, даные о которых необхдимо изменить
### Тело запроса
Словарь, где ключ - параметр пользователя, а значение - новое значение параметра
### Возврат
Возвращает измененные данные пользователя



## POST method/users.create
### Тело запроса
Пользователь, которого нужно создать
### Возврат
Возвращает сохраненные данные пользователя



## POST method/users.delete
### Параметры
**'user_id'** содержит идентификатор пользователя, даные о которых необхдимо удалить
### Возврат
Возвращает null
