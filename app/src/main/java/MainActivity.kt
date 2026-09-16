
package com.example.mylab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mylab1.ui.theme.Mylab1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        runLab1() // запускаем код лабы, чтобы результаты вывелись в Logcat
        setContent {
            Mylab1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mylab1Theme {
        Greeting("Android")
    }
}

// константа для числа пи, лежит вне функции, потому что const val нельзя объявить внутри функции
const val pi: Double = 3.14159

// вся лаба находится тут, в одной функции
fun runLab1() {

    // ================= VARIABLES =================

    var x: Int = 10 // просто целое число
    println("1. x = $x")

    println("2. pi = $pi") // выводим константу, объявленную выше

    var isActive: Boolean = false // логический тип, может быть только true или false
    println("3. isActive = $isActive")

    var temperature: Float = 36.6f // дробное число, буква f в конце обязательна для Float
    println("4. temperature = $temperature")

    var count: Long = 9999999999L // большое целое число, буква L в конце обязательна для Long
    println("5. count = $count")

    var letter: Char = 'A' // один символ, кавычки одинарные, не двойные
    println("6. letter = $letter")

    var a: Int = 5
    var b: Int = 7
    var sum = a + b // просто складываем два числа
    println("7. a + b = $sum")

    var radius: Double = 4.0
    var area = pi * radius * radius // формула площади круга: пи умножить на радиус в квадрате
    println("8. Circle area = $area")

    var salary: Int = 20000
    var newSalary = (salary * 1.15).toInt() // умножаем на 1.15, это и есть +15%; toInt() убирает дробную часть
    println("9. New salary = $newSalary")

    var price: Double = 500.0
    var priceWithTax = price + (price * 0.12) // прибавляем 12% налога к цене
    println("10. Price with tax = $priceWithTax")


    // ================= DATA TYPES =================

    var i: Int = 1
    var d: Double = 1.5
    var bool: Boolean = true
    var str: String = "text"
    var c: Char = 'x'
    println("DT1. $i $d $bool $str $c") // просто пять переменных разных типов подряд

    var dNum: Double = 9.8
    var iNum: Int = dNum.toInt() // отбрасывает дробную часть, не округляет (9.8 -> 9)
    println("DT2. $iNum")

    var num: Int = 1234
    var numStr: String = num.toString() // превращаем число в текст
    println("DT3. $numStr")

    var strNum: String = "56"
    var result: Int = strNum.toInt() * 2 // сначала текст превращаем в число, потом умножаем
    println("DT4. $result")

    var isGreater: Boolean = 10 > 5 // сравнение сразу даёт true или false
    println("DT5. $isGreater")

    var number = 8
    var isEven: Boolean = number % 2 == 0 // % — остаток от деления; если 0, число чётное
    println("DT6. $isEven")

    var boolStr: String = true.toString() // Boolean превращаем в текст "true"
    println("DT7. $boolStr")

    println("DT8. Min=${Int.MIN_VALUE} Max=${Int.MAX_VALUE}") // готовые границы типа Int

    var ch: Char = 'b'
    var chCode: Int = ch.code // у каждого символа есть свой числовой код
    println("DT9. $chCode")

    var numChar: Int = 97
    var charFromNum: Char = numChar.toChar() // и обратно: число превращаем в символ
    println("DT10. $charFromNum")


    // ================= STRING =================

    var s1 = "Kotlin"
    println("S1. length = ${s1.length}") // длина строки, количество символов

    var s2 = "Hello"
    println("S2. first=${s2.first()} last=${s2.last()}") // первый и последний символ строки

    var s3 = "Good" + "Morning" // склеиваем две строки в одну
    println("S3. $s3")

    var s4 = "banana".replace("a", "o") // меняем все буквы "a" на "o"
    println("S4. $s4")

    var name = "Erbolbek"
    var age = 20
    var info = "$name is $age years old" // вставляем переменные прямо в текст строки
    println("S5. $info")

    var s6 = "Welcome"
    println(s6.substring(1, 4)) // берём символы с позиции 1 по 3 (индексация с 0), получаем 3 буквы

    var s7 = " Kotlin ".trim() // убирает пробелы слева и справа
    println("S7. '$s7'")

    var s8 = "I love Java and Kotlin"
    println("S8. ${s8.contains("Java")}") // проверяем, есть ли слово "Java" внутри строки

    var s9 = "Hello, World!".uppercase() // все буквы становятся заглавными
    println("S9. $s9")

    var s10 = "HELLO".lowercase() // все буквы становятся строчными
    println("S10. $s10")

    var s11 = "Kotlin is fun"
    println("S11. ${s11.split(" ").size}") // разбиваем строку по пробелу и считаем количество кусков

    var s12 = "abc".repeat(3) // повторяем строку 3 раза подряд
    println("S12. $s12")

    var s13 = "123".toList() // превращаем строку в список отдельных символов
    println("S13. $s13")

    var s14 = "racecar"
    var isPalindrome = s14 == s14.reversed() // сравниваем строку с её же перевёрнутой версией
    println("S14. $isPalindrome")

    var s15 = "apple,banana,orange".split(",") // разбиваем строку по запятой на список слов
    println("S15. $s15")
}




fun runLab2() {

    // ================= ARRAYS =================
    // Array — массив, размер фиксированный, задаётся сразу при создании

    // Задание 1. Создать массив из 5 целых чисел и вывести все элементы.
    var arr1 = arrayOf(4, 8, 2, 9, 5)
    println("A1. ${arr1.joinToString()}") // joinToString печатает все элементы через запятую

    // Задание 2. Найти сумму элементов массива.
    println("A2. Sum = ${arr1.sum()}") // sum() складывает все элементы

    // Задание 3. Найти максимальный элемент массива.
    println("A3. Max = ${arr1.max()}") // max() находит самое большое число

    // Задание 4. Посчитать количество чётных чисел в массиве.
    var evenCount = arr1.count { it % 2 == 0 } // it — текущий элемент; считаем только чётные
    println("A4. Even count = $evenCount")

    // Задание 5. Развернуть массив (вывести в обратном порядке).
    println("A5. Reversed = ${arr1.reversed()}") // reversed() переворачивает порядок элементов

    // Задание 6. Проверить, содержит ли массив заданное число.
    println("A6. Contains 9 = ${arr1.contains(9)}") // contains() проверяет наличие значения

    // Задание 7. Отсортировать массив по возрастанию.
    println("A7. Sorted = ${arr1.sorted()}") // sorted() сортирует, исходный массив не трогает

    // Задание 8. Создать массив строк и найти самую длинную строку.
    var words = arrayOf("cat", "elephant", "dog", "ox")
    var longest = words.maxByOrNull { it.length } // ищем строку с максимальной длиной
    println("A8. Longest = $longest")

    // Задание 9. Найти индекс минимального элемента массива.
    var minIndex = arr1.indexOf(arr1.min()) // сначала находим минимум, потом его позицию
    println("A9. Min index = $minIndex")

    // Задание 10. Умножить каждый элемент массива на 2.
    var doubled = arr1.map { it * 2 } // map применяет действие к каждому элементу
    println("A10. Doubled = $doubled")


    // ================= ARRAYLIST =================
    // ArrayList — список, можно добавлять и удалять элементы, размер меняется сам

    // Задание 1. Создать список целых чисел и добавить в него 10 элементов.
    var numbers = arrayListOf<Int>()
    for (n in 1..10) {
        numbers.add(n) // add() добавляет элемент в конец списка
    }
    println("AL1. $numbers")

    // Задание 2. Удалить элемент из списка по индексу.
    numbers.removeAt(2) // удаляет элемент на позиции 2 (индексы с 0, значит удалится число 3)
    println("AL2. After removeAt(2): $numbers")

    // Задание 3. Найти среднее значение элементов списка.
    println("AL3. Average = ${numbers.average()}") // average() считает среднее арифметическое

    // Задание 4. Проверить, пустой ли список.
    println("AL4. isEmpty = ${numbers.isEmpty()}") // isEmpty() возвращает true/false

    // Задание 5. Заменить элемент списка на другой.
    numbers[0] = 100 // обращение по индексу и присвоение нового значения
    println("AL5. After replace: $numbers")

    // Задание 6. Перевести список строк в верхний регистр.
    var strList = arrayListOf("kotlin", "java", "swift")
    var upperList = strList.map { it.uppercase() } // делаем каждую строку заглавной
    println("AL6. $upperList")

    // Задание 7. Создать список чисел и удалить все чётные.
    var nums2 = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8)
    nums2.removeAll { it % 2 == 0 } // удаляет все элементы, подходящие под условие
    println("AL7. After removing even: $nums2")

    // Задание 8. Объединить два списка в один.
    var listA = arrayListOf(1, 2, 3)
    var listB = arrayListOf(4, 5, 6)
    var merged = listA + listB // простое сложение списков
    println("AL8. Merged = $merged")

    // Задание 9. Найти второй по величине элемент списка.
    var listC = arrayListOf(10, 45, 3, 99, 21)
    var sortedDesc = listC.sortedDescending() // сортируем по убыванию
    println("AL9. Second largest = ${sortedDesc[1]}") // второй элемент после сортировки

    // Задание 10. Преобразовать список чисел в множество (Set).
    var listD = arrayListOf(1, 2, 2, 3, 3, 3, 4)
    var asSet = listD.toSet() // toSet() убирает повторяющиеся элементы
    println("AL10. As Set = $asSet")


    // ================= SET =================
    // Set — множество, хранит только уникальные (неповторяющиеся) элементы

    // Задание 1. Создать множество из чисел и вывести его.
    var set1 = setOf(1, 2, 3, 4, 5)
    println("SET1. $set1")

    // Задание 2. Добавить в множество повторяющиеся элементы и проверить результат.
    var set2 = mutableSetOf(1, 2, 2, 3, 3, 3) // пытаемся добавить повторы
    println("SET2. $set2") // повторы пропадают сами, останется {1, 2, 3}

    // Задание 3. Найти пересечение двух множеств.
    var setA = setOf(1, 2, 3, 4)
    var setB = setOf(3, 4, 5, 6)
    println("SET3. Intersect = ${setA.intersect(setB)}") // общие элементы обоих множеств

    // Задание 4. Найти объединение двух множеств.
    println("SET4. Union = ${setA.union(setB)}") // все элементы обоих множеств вместе

    // Задание 5. Найти разность двух множеств.
    println("SET5. Difference = ${setA.minus(setB)}") // что есть в setA, но нет в setB

    // Задание 6. Проверить, содержится ли элемент в множестве.
    println("SET6. Contains 3 = ${setA.contains(3)}")

    // Задание 7. Подсчитать количество уникальных символов в строке.
    var sentence = "kotlin android development"
    var uniqueChars = sentence.toSet().size // toSet() на строке даёт множество уникальных символов
    println("SET7. Unique chars = $uniqueChars")

    // Задание 8. Преобразовать список в множество для удаления дубликатов.
    var listWithDup = listOf(1, 1, 2, 2, 3, 4, 4)
    println("SET8. Without duplicates = ${listWithDup.toSet()}")

    // Задание 9. Найти симметрическую разность двух множеств.
    var symmetricDiff = (setA - setB) union (setB - setA) // элементы, которые есть только в одном из множеств
    println("SET9. Symmetric difference = $symmetricDiff")

    // Задание 10. Создать множество строк и проверить, есть ли там слово "Kotlin".
    var stringSet = setOf("Kotlin", "Java", "Python")
    println("SET10. Contains Kotlin = ${stringSet.contains("Kotlin")}")


    // ================= MAP =================
    // Map — словарь, хранит пары "ключ -> значение"

    // Задание 1. Создать словарь (Map) с ключами Int и значениями String (например, ID → имя).
    var idToName = mutableMapOf(1 to "Aibek", 2 to "Nursultan", 3 to "Aigerim")
    println("M1. $idToName")

    // Задание 2. Добавить новый элемент в Map.
    idToName[4] = "Erbolbek" // добавление новой пары ключ-значение
    println("M2. After adding: $idToName")

    // Задание 3. Получить значение по ключу.
    println("M3. Value for key 2 = ${idToName[2]}")

    // Задание 4. Проверить, есть ли ключ в Map.
    println("M4. Contains key 5 = ${idToName.containsKey(5)}")

    // Задание 5. Проверить, есть ли значение в Map.
    println("M5. Contains value Aibek = ${idToName.containsValue("Aibek")}")

    // Задание 6. Вывести все ключи и значения.
    println("M6. Keys = ${idToName.keys}")
    println("M6. Values = ${idToName.values}")

    // Задание 7. Удалить элемент по ключу.
    idToName.remove(1) // remove() удаляет пару по указанному ключу
    println("M7. After removing key 1: $idToName")

    // Задание 8. Посчитать количество элементов в Map.
    println("M8. Size = ${idToName.size}")

    // Задание 9. Пройтись циклом по словарю и вывести все пары ключ-значение.
    for ((key, value) in idToName) { // на каждом шаге цикла key и value берутся из очередной пары
        println("M9. $key -> $value")
    }

    // Задание 10. Создать Map для хранения оценок студентов и найти среднюю оценку.
    var grades = mapOf("Erbolbek" to 90, "Aibek" to 85, "Aigerim" to 95)
    var avgGrade = grades.values.average() // берём только оценки (values) и считаем среднее
    println("M10. Average grade = $avgGrade")
}
 


