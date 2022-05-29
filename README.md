# Deeplay test task
Test task for Deeplay IT company

Доброго времени суток, данный проект реализует алгоритм, позволяющий найти кратчайший путь из одной ячейки игрового поля в другую.

Ячейки поля имеют определенный тип, и, соответственно, цена прохода по ячейке определяется расой персонажа и типом ячейки.

В данном проекте поле имеет размер 4х4, а расы и типы ячеек задаются из внешнего файла.

Структура файла при это имеет вид:
1 строка - перечисление названий типов ячеек
Последующие строки содержать название расы и цену прохода по соответствующей ячейке.
Например:
W T P S 
Human: 3 6 1 4
Swamper: 4 5 1 8
В данном случае для расы Human цена прохода по ячейке W - 3, по ячейке T - 6, по ячейке P - 1 и по ячейке S - 4,
a для расы Swamper W - 4, T - 5, P - 1, S - 8.
Удобнее представить, что файл представляет собой таблицу, где столбцы - это типы ячеек, а строки цена прохода для конкретной расы.

Алгоритм представляет собой реализацию алгоритма Дейкстры. Алгоритм Дейкстры - это алгоритм поиска кратчайшего пути в графе с неотрицательными ребрами.
Получается, что для реализации этого алгоритма требуется представить игровое поле в виде графа. Реализация представлена в статическом методе класса Solution под названием getResult.

Также данный проект содержит модульные тесты, проверяющие корректность работы алгоритма и отслеживают правильную структуру введенных с файла данных.
