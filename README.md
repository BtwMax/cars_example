# Тестовое задание для Java стажеров в Райффайзен банк

## Стек используемых технологий:
* Java 11
* Spring Boot v.2.6.3
* PostgreSQL
* Lombok
* Liquibase
* Docker

## Что нужно было сделать:
Реализовать приложение для автоматизации учёта машин у автодилера. Автодилер должен иметь возможность:
* Учесть приход и отпуск машин;
* Узнать общее количество машин определенного цвета и лошадинных сил в данный момент времени.

## Список URL HTTP-методов

### POST /api/cars/income

Регистрирует приход машин у автодилера. 

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:
* color - цвет машин, строка(например, black, red, yellow);
* horsepower - количество лошадинных сил, целое число от 0 (например, 130, 180, 72);
* quantity - количество машин, целое число больше 0.

### POST /api/cars/outcome

Регистрирует отпуск машины. Здесь параметры и результаты аналогичные, но общее количество машин указанного цвета и лошадиных сил не увеличивается, а уменьшается.

### GET /api/cars

Возвращает общее количество машин у автодилера, соответствующих переданным в параметрах критериям запроса. 

Параметры запроса передаются в URL:
* color - цвет машин, строка;
* operation - оператор сравнения значения количества лошадинных сил в машинеб одно значение из: moreThan, lessThan, equal;
* horsepower - значение лошадинных сил из сравнения.

## Примеры запросов:

* **/api/cars?color=red&operation=moreThan&horsepower=90** — должен вернуть общее количество красных машин, у которых лошадиные силы более 90;
* **/api/cars?color=black&operation=lessThan?horsepower=200** — должен вернуть общее количество черных машин, у которых лошадиные силы менее 200.

