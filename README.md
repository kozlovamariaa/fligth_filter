# fligth_filter

Описание: 
имеется система, которая обрабатывает авиаперелёты. Перелёт — это перевозка пассажира из одной точки в другую с возможными промежуточными посадками. 
Т. о. перелёт можно представить как набор из одного или нескольких элементарных перемещений, называемых сегментами. Сегмент — это атомарная перевозка,
которую для простоты будем характеризовать всего двумя атрибутами: дата/время вылета и дата/время прилёта.

Задача: 
нужно написать небольшой модуль, который будет заниматься фильтрацией набора перелётов согласно различным правилам.

Из тестового набора перелетов исключить перелеты со следующими правилами:
1) Вылет до текущего момента времени
2) Имеются сегменты с датой прилёта раньше даты вылета
3) Общее время, проведённое на земле превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)

Структура пакетов и вложенных в них классов
1. Пакет /com/gridnine/testing

- Класс Main - точка входа в программу, реализующий вывод фильтрованных запросов в консоль

2. Пакет /com/gridnine/testing/dao
- Интерфейс FlightFilterBuilder для создания фильтра выборки полетов
- Класс FlightFilterBuilderImp, расширяющий интерфейс FlightFilterBuilder, данный класс реализует логику фильтрации перелетов

3. Пакет /com/gridnine/testing/models
- Класс Segment, описывающий расписание перемещений внутри одного перелета
- Класс Flight - основной класс перелета

4. Пакет /com/gridnine/testing/services
- Класс FlightBuilder - фабрика создания перелетов 

5. Пакет test/java/com/gridnine/testing/dao

- Класс FlightFilterBuilderTest - класс тестирования FlightFilterBuilderImp 
