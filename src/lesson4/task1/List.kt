@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt
import kotlin.math.pow

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (element in v) sum += sqr(element)
    return sqrt(sum)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    var i = 0
    for (element in list) {
        sum += element
        i++
    }
    return if (i != 0) sum / i else 0.0
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var sum = 0.0
    var i = 0
    for (element in list) {
        sum += element
        i++
    }
    if (i == 0) {
        return list
    } else {
        sum /= i
        for (j in 0 until list.size) {
            list[j] -= sum
        }
        return list
    }
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var C = 0
    for (i in a.indices) {
        C += a[i] * b[i]
    }
    return C
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var s = 0
    for (i in p.indices) {
        s += p[i] * (x.toDouble().pow(i)).toInt()
    }
    return s
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.size != 0) {
        var s = list[0]
        for (i in 1 until list.size) {
            s += list[i]
            list[i] = s
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var i = 2
    var m = n
    val result = mutableListOf<Int>()
    while (m > 1) {
        while (m % i == 0) {
            m /= i
            result.add(i)
        }
        i++
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var i = 2
    var m = n
    val result = mutableListOf<Int>()
    while (m > 1) {
        while (m % i == 0) {
            m /= i
            result.add(i)
        }
        i++
    }
    return result.joinToString(separator = "*")
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var m = n
    while (m > 0) {
        result.add(0, m % base)
        m /= base
    }
    return if (n == 0) listOf(0) else result
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val letters = mutableListOf<String>()
    for (i in 0..9) {
        letters.add(i.toString())
    }
    for (i in 'a'..'z') {
        letters.add(i.toString())
    }
    val result = mutableListOf<String>()
    var m = n
    while (m > 0) {
        result.add(0, letters[m % base])
        m /= base
    }
return result.joinToString(separator = "")
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    val s = digits.size
    for (i in s - 1 downTo 0) {
        result += digits[i] * (base.toDouble()).pow((s - 1) - i).toInt()
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val number = mutableListOf<String>()
    for (i in 0..9) number.add(i.toString())
    for (i in 'a'..'z') number.add(i.toString())
    var s = 0
    var j = 1
    for (i in str) {
        s += number.indexOf(i.toString()) * base.toDouble().pow(str.length - j).toInt()
        j += 1
    }
    return if (str == "0") 0 else s
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val firstSt = mutableListOf<String>()
    val firstTh = mutableListOf<String>()
    val second = mutableListOf<String>()
    val third = mutableListOf<String>()
    val tenToTwenty = mutableListOf<String>()
    val number = mutableListOf<Int>()
    var m = n
    while (m > 0) {
        number.add(0, m % 10)
        m /= 10
    }
    while (number.size < 6) number.add(0, 0)
    tenToTwenty.add("десять")
    tenToTwenty.add("одиннадцать")
    tenToTwenty.add("двенадцать")
    tenToTwenty.add("тринадцать")
    tenToTwenty.add("четырнадцать")
    tenToTwenty.add("пятнадцать")
    tenToTwenty.add("шестнадцать")
    tenToTwenty.add("семнадцать")
    tenToTwenty.add("восемнадцать")
    tenToTwenty.add("девятнадцать")
    firstSt.add("")
    firstSt.add("один")
    firstSt.add("два")
    firstSt.add("три")
    firstSt.add("четыре")
    firstSt.add("пять")
    firstSt.add("шесть")
    firstSt.add("семь")
    firstSt.add("восемь")
    firstSt.add("девять")
    firstTh.add("")
    firstTh.add("одна")
    firstTh.add("две")
    firstTh.add("три")
    firstTh.add("четыре")
    firstTh.add("пять")
    firstTh.add("шесть")
    firstTh.add("семь")
    firstTh.add("восемь")
    firstTh.add("девять")
    second.add("")
    second.add("десять")
    second.add("двадцать")
    second.add("тридцать")
    second.add("сорок")
    second.add("пятьдесят")
    second.add("шестьдесят")
    second.add("семьдесят")
    second.add("восемьдесят")
    second.add("девяносто")
    third.add("")
    third.add("сто")
    third.add("двести")
    third.add("триста")
    third.add("четыреста")
    third.add("пятьсот")
    third.add("шестьсот")
    third.add("семьсот")
    third.add("восемьсот")
    third.add("девятьсот")
    var s = ""
    s += third[number[0]]
    if (number[1] != 0 && s != "") s += " "
    if (number[1] == 1) {
        s += tenToTwenty[number[2]]
        s += " тысяч"
    } else {
        s += second[number[1]]
        if (number[1] != 0 && s != "") s += " "
        s += firstTh[number[2]]
        s += when {
            number[0] == 0 && number[1] == 0 && number[2] == 0 -> ""
            number[2] == 1 -> " тысяча"
            number[2] in (2..4) -> " тысячи"
            else -> " тысяч"
        }
    }
    if (number[3] != 0 && s != "") s += " "
    s += third[number[3]]
    if (number[4] != 0 && s != "") s += " "
    if (number[4] == 1) {
        s += tenToTwenty[number[5]]
    } else {
        s += second[number[4]]
        if (number[5] != 0 && s != "") s += " "
        s += firstSt[number[5]]
    }
    return s
}