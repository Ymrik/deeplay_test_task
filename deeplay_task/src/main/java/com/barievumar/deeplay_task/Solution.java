package com.barievumar.deeplay_task;

import com.barievumar.deeplay_task.exception.WrongDataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    //Таблица содержащая информацию о каждой расе Map<раса,Map<препятствие,ценаПрохода>>
    private static Map<String, Map<String, Integer>> raceInfoMap = new HashMap<>();

    //Метод, через который происходит считываение данных из файла и инициализация условий задачи
    public static void initialize(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            if (!reader.ready()) {
                throw new WrongDataException("Exception: file is empty");
            }
            //Определяем типы препятствий
            List<String> obstacles = Arrays.asList(reader.readLine().split(" "));
            //Считываем цену прохода для каждой расы
            while (reader.ready()) {
                String raceInfo = reader.readLine();
                //Определяем название расы
                String race = raceInfo.split(" ")[0].replaceAll(":", "");
                //Проверяем правильность введенных в файл данных
                if(raceInfo.split(" ").length-1 != obstacles.size())
                    throw new WrongDataException("Exception: the number of obstacles and the data in the race description must match");
                //Map, устанавливающая соответсиве между препятствием и ценой прохода для текущей расы
                Map<String,Integer> currentRaceInfo = new HashMap<>();
                //заполнение currentRaceInfo
                for (int i = 0; i < obstacles.size(); i++) {
                    currentRaceInfo.put(obstacles.get(i),Integer.parseInt(raceInfo.split(" ")[i+1]));
                }
                raceInfoMap.put(race,currentRaceInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Метод, содержащий логику работы алгоритма
    //Работа алгоритма построена на работе алгоритма поиска кратчайшего пути в графе
    public static int getResult(String fieldDescription, String race) {
        //Инициализация игровых клеток
        //Вершины отмечаются, как показано ниже
        //00 01 02 03
        //04 05 06 07
        //08 09 10 11
        //12 13 14 15
        if(fieldDescription.length()!=16)throw new WrongDataException("Exception: fieldDescriptions must contain 16 symbols");
        if(!raceInfoMap.containsKey(race))throw new WrongDataException("Exception: no such race in raceInfoMap");
        int[] gameCells = new int[16];
        for (int i = 0; i < gameCells.length; i++) {
            if(!raceInfoMap.get(race).containsKey(String.valueOf(fieldDescription.charAt(i))))
                throw new WrongDataException("Exception: no such cell in raceInfoMap");
            gameCells[i] = raceInfoMap.get(race)
                    .get(String.valueOf(fieldDescription.charAt(i)));
        }
        //Создаем матрицу смежности
        //Если путь есть, ячейка матрицы заполняется длиной пути, иначе - Integer.MAX_VALUE/2
        int[][] graph = new int[16][16];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 != 0) {
                graph[i][i - 1] = gameCells[i - 1];
                graph[i - 1][i] = gameCells[i];
            }
            if (i != 3 && i != 7 && i != 11 && i != 15) {
                graph[i][i + 1] = gameCells[i + 1];
                graph[i + 1][i] = gameCells[i];
            }
            if (i - 4 >= 0) {
                graph[i][i - 4] = gameCells[i - 4];
                graph[i - 4][i] = gameCells[i];
            }
            if (i + 4 <= 15) {
                graph[i][i + 4] = gameCells[i + 4];
                graph[i + 4][i] = gameCells[i];
            }
        }
        //Конец инициализации матрицы смежности

        //Массив, показывающий, какие вершины пройдены, а какие - нет
        boolean[] used = new boolean[16];
        //Массив содержащий расстояние от стартовой вершины до отсальных
        int[] dist = new int[16];
        //Изначально массив dist заполняется максимальными значениями, за исключением отправной вершины
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
        }
        //Отправная вершина - 0
        dist[0] = 0;
        //Алгоритм Дейкстры
        for (; ; ) {
            int v = -1;
            //Проходимся по всем вершинам
            for (int nv = 0; nv < 16; nv++) {
                //Выбираем ближайшую непомеченную вершину
                if (!used[nv] && dist[nv] < Integer.MAX_VALUE / 2 && (v == -1 || dist[v] > dist[nv])) v = nv;
            }
            if (v == -1) break;//вершина не найдена - завершение алгоритма
            used[v] = true; //помечаем найденную вершину
            for (int nv = 0; nv < 16; nv++) {
                //улучшаем расстояние до всех непомеченных вершин
                if (!used[nv] && graph[v][nv] < Integer.MAX_VALUE / 2) {
                    dist[nv] = Math.min(dist[nv], dist[v] + graph[v][nv]);
                }
            }
        }
        //Возвращаем расстояние от исходной вершины - 00 до конечной - 15
        return dist[15];
    }


    public static Map<String, Map<String, Integer>> getRaceInfoMap() {
        return raceInfoMap;
    }
}
