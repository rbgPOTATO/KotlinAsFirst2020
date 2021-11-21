@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

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
    var result = 0.0
    for (i in v.indices) {
        result += v[i] * v[i]
    }
    result = sqrt(result)
    return result
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    return list.average()
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
    if (list.isEmpty()) return list
    val average = list.average()
    for (i in 0 until list.size) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in a.indices) {
        val sum = a[i] * b[i]
        result += sum
    }
    return result

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
    if (p.isEmpty()) return 0
    var sum = p[0]
    var xn = x
    for (i in 1 until p.size) {
        sum += (p[i] * xn)
        xn *= x
    }
    return sum
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
    if (list.isEmpty()) return list
    var sum = list.sum()
    for (i in (list.size - 1) downTo 1) {
        sum -= list[i]
        list[i] += sum
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
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var num = n
    var k = 0
    val list = mutableListOf<Int>()
    while (num > 0) {
        k = num % base
        list += k
        num /= base
    }
    return list.reversed()
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
    var num = n
    var k: Any = 0
    val list = mutableListOf<Any>()
    while (num > 0) {
        k = num % base
        if (k > 9) {
            k = when (k) {
                10 -> "a"
                11 -> "b"
                12 -> "c"
                13 -> "d"
                14 -> "e"
                15 -> "f"
                16 -> "g"
                17 -> "h"
                18 -> "i"
                19 -> "j"
                20 -> "k"
                21 -> "l"
                22 -> "m"
                23 -> "n"
                24 -> "o"
                25 -> "p"
                26 -> "q"
                27 -> "r"
                28 -> "s"
                29 -> "t"
                30 -> "u"
                31 -> "v"
                32 -> "w"
                33 -> "x"
                34 -> "y"
                35 -> "z"
                else -> continue
            }
        }
        list += k
        num /= base
    }
    var result = ""
    for (i in list.indices) {
        result += list[i].toString()
    }
    return result.reversed()
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var ten = 0.0
    var pow = digits.size - 1
    for (i in digits.indices) {
        ten += digits[i] * base.toDouble().pow(pow)
        pow -= 1
    }
    return ten.toInt()
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
    val list = str.toMutableList()
    var num = 0.0
    var pow = list.size - 1
    for (i in list.indices) {
        var m = list[i].toString()
        m = when (m) {
            "1" -> "1"
            "2" -> "2"
            "3" -> "3"
            "4" -> "4"
            "5" -> "5"
            "6" -> "6"
            "7" -> "7"
            "8" -> "8"
            "9" -> "9"
            "0" -> "0"
            "a" -> "10"
            "b" -> "11"
            "c" -> "12"
            "d" -> "13"
            "e" -> "14"
            "f" -> "15"
            "g" -> "16"
            "h" -> "17"
            "i" -> "18"
            "j" -> "19"
            "k" -> "20"
            "l" -> "21"
            "m" -> "22"
            "n" -> "23"
            "o" -> "24"
            "p" -> "25"
            "q" -> "26"
            "r" -> "27"
            "s" -> "28"
            "t" -> "29"
            "u" -> "30"
            "v" -> "31"
            "w" -> "32"
            "x" -> "33"
            "y" -> "34"
            "z" -> "35"
            else -> continue
        }
        num += m.toDouble() * base.toDouble().pow(pow)
        pow -= 1
    }
    return num.toInt()
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
    val list = n.toString().toList()
    val thousand = "тысяч"
    val result = mutableListOf<String>()
    val listFirst = listOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять")
    val listSecond = listOf(
        "", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    )
    val listThird = listOf(
        "", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
        "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    )
    val listForth = listOf(
        "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    )
    if (list.size == 1) {
        val first = list[0].toString().toInt()
        result += listFirst[first]
    }
    if (list.size == 2) {
        val first = list[0].toString().toInt()
        val second = list[1].toString().toInt()
        val a = list[0].toString() + list[1].toString()
        if (a.toInt() in 11..19) {
            result += listSecond[second]
        } else {
            result += listThird[first]
            result += listFirst[second]
        }
    }
    if (list.size == 3) {
        val first = list[0].toString().toInt()
        val second = list[1].toString().toInt()
        val third = list[2].toString().toInt()
        result += listForth[first]
        val a = list[1].toString() + list[2].toString()
        if (a.toInt() in 11..19) {
            result += listSecond[third]
        } else {
            result += listThird[second]
            result += listFirst[third]
        }
    }
    if (list.size == 4) {
        val first = list[0].toString().toInt()
        val second = list[1].toString().toInt()
        val third = list[2].toString().toInt()
        val forth = list[3].toString().toInt()
        if (list[0].toString().toInt() == 1) {
            result += "одна " + thousand + "а"
        }
        if (list[0].toString().toInt() == 2) {
            result += "две " + thousand + "и"
        }
        if (list[0].toString().toInt() in 3..4) {
            result += listFirst[first] + " тысячи"
        }
        if (list[0].toString().toInt() !in 1..4) {
            result += listFirst[first] + " тысяч"
        }
        result += listForth[second]
        val a = list[2].toString() + list[3].toString()
        if (a.toInt() in 11..19) {
            result += listSecond[forth]
        } else {
            result += listThird[third]
            result += listFirst[forth]
        }
    }
    if (list.size == 5) {
        val list = n.toString().toList()
        val first = list[0].toString().toInt()
        val second = list[1].toString().toInt()
        val third = list[2].toString().toInt()
        val forth = list[3].toString().toInt()
        val fifth = list[4].toString().toInt()
        val a = list[0].toString() + list[1].toString()
        if (a.toInt() in 11..19) {
            result += listSecond[second]
            result += thousand
        } else {
            result += listThird[first]
        }
        if (a.toInt() !in 11..19 && list[1].toString().toInt() == 1) {
            result += "одна"
            result += thousand + "а"
        }
        if (a.toInt() !in 11..19 && list[1].toString().toInt() == 2) {
            result += "две"
            result += thousand + "и"
        }
        if (a.toInt() !in 11..19 && list[1].toString().toInt() in 3..4) {
            result += listFirst[second]
            result += thousand + "и"
        }
        if (a.toInt() !in 11..19 && list[1].toString().toInt() !in 1..4) {
            result += listFirst[second]
            result += thousand
        }
        result += listForth[third]
        val b = list[3].toString() + list[4].toString()
        if (b.toInt() in 11..19) {
            result += listSecond[fifth]
        }
        result += listThird[forth]
        result += listFirst[fifth]
    }
    if (list.size == 6) {
        val first = list[0].toString().toInt()
        val second = list[1].toString().toInt()
        val third = list[2].toString().toInt()
        val forth = list[3].toString().toInt()
        val fifth = list[4].toString().toInt()
        val sixth = list[5].toString().toInt()
        result += listForth[first]
        val a = list[1].toString() + list[2].toString()
        if (a.toInt() in 11..19) {
            result += listSecond[third]
            result += thousand
        } else {
            result += listThird[second]
        }
        if (a.toInt() !in 11..19 && list[2].toString().toInt() == 1) {
            result += "одна"
            result += thousand + "а"
        }
        if (a.toInt() !in 11..19 && list[2].toString().toInt() == 2) {
            result += "две"
            result += thousand + "и"
        }
        if (a.toInt() !in 11..19 && list[2].toString().toInt() in 3..4) {
            result += listFirst[third]
            result += thousand + "и"
        }
        if (a.toInt() !in 11..19 && list[2].toString().toInt() !in 1..4) {
            result += listFirst[third]
            result += thousand
        }
        result += listForth[forth]
        val b = list[4].toString() + list[5].toString()
        if (b.toInt() in 11..19) {
            result += listSecond[sixth]
        }
        result += listThird[fifth]
        result += listFirst[sixth]
    }
    var answer = ""
    for (i in result.indices) {
        if (result[i] != "") {
            answer += result[i] + " "
        }
    }
    return answer.substring(0, answer.length - 1)
}