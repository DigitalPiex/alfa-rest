Стек технологий: Java17, PostgreSQL, Hibernate, SpringBoot

Входные данные
При старте приложение создается таблицы по указанной ниже схеме и заполняет их данными в соответствии с переданным XML-файлом:

`CREATE TABLE BOX
(ID INTEGER PRIMARY KEY,
CONTAINED_IN INTEGER
);
CREATE TABLE ITEM
(ID INTEGER PRIMARY KEY,
CONTAINED_IN INTEGER REFERENCES BOX(ID),
COLOR VARCHAR(100)
);
`

Приложен docker-compose файл для быстрого развёртывая дефолтной PostgreSQL.


Собираем проект через `maven package`, возможные варианты запуска:

java -jar target/*.jar --classpath=src\\main\\resources\\file\\input.xml

java -jar target/*.jar --file=C:\\test\\input.xml

java -jar target/*.jar --url=file:\\D:\\Desktop\\input.xml


Работа веб-сервиса
После загрузки файла приложение работает, как REST-сервис, который возвращает id предметов заданного цвета (color), содержащиеся в ящике c заданным идентификатором (idBox) с учётом того, что в ящике может быть ещё ящик с предметами требуемого цвета.

Пример post-запроса POST /test HTTP/1.1
Host: localhost
Accept: application/json
Content-Type:application/json
Content-Length: 25
{"boxId":"1","color":"red"}

Формат ссылок
Ссылка имеет следующий формат: type:path, где:

type - тип ссылки
path - путь к файлу
Ссылка определяет источник, из которого загружаются данные в XML-формате.
Тип ссылки (type):

file (внешний файл)
classpath (файл в classpath)
url (URL)
Примеры:

file:input.xml
classpath:input.xml
url:file:/input.xml

Основные сущности приложения - предмет (Item) и коробка (Box):
Ящики могут быть пустыми или содержать предметы или другие ящики
У каждого ящика и предмета есть id
ID какого-либо предмета и какого-либо ящика могут совпадать, но в совокупности предметов они уникальны (как и в совокупности ящиков)
Предметы могут не иметь цвета
Предметы могут быть не в ящике
Вложенность ящиков может быть любой;