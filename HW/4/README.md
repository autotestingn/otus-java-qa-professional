Порт настраивается в application.properties


API:
GET /api/users/ получение списка пользователей
GET /api/courses/ получение списка курсов
GET /api/users/{id}/scores/ получение оценок пользователя с id по всем его курсам


VIEWS:
host + port + /courses/ страница со списком курсов
host + port + /users/{id}/scores/ страница со списком оценок по всем курсам, где id = id пользователя
host + port + /users/ страница со списком пользователей