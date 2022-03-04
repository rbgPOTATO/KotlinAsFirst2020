@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = n
    do {
        number /= 10
        count += 1
    } while (abs(number) > 0)
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var f = 1
    var pf = 1
    for (i in 3..n) {
        val nf = f
        f += pf
        pf = nf
    }
    return f
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var min = 0
    for (i in 2..n) {
        if (n % i == 0) {
            min = i
            break
        }
    }
    return min
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var max = 0
    for (i in n - 1 downTo 1) {
        if (n % i == 0) {
            max = i
            break
        }
    }
    return max
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var num = x
    while (num != 1) {
        if (num % 2 == 0) {
            num /= 2
            step++
        } else {
            num = 3 * num + 1
            step++
        }
    }
    return step
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var first = m
    var second = n
    while ((first != 0) && (second != 0)) {
        if (first > second) first %= second
        else second %= first
    }
    val gcd = first + second
    return m * n / gcd
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var first = m
    var second = n
    while ((first != 0) && (second != 0)) {
        if (first > second) first %= second
        else second %= first
    }
    return first + second == 1
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var r = 0
    var num = n
    while (num != 0) {
        r = r * 10 + num % 10
        num /= 10
    }
    return r
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var r = 0
    var num = n
    while (num != 0) {
        r = r * 10 + num % 10
        num /= 10
    }
    return r == n
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    var digit = num % 10
    while (num != 0) {
        val prev = digit
        digit = num % 10
        num /= 10
        if (prev != digit) return true
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val k = x % (2 * PI)
    var y = k
    var sum = k
    var m = 1.0
    while (abs(y) >= eps) {
        y *= -1.0 * k * k / ((m + 1.0) * (m + 2.0))
        sum += y
        m += 2.0
    }
    return sum
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val k = x % (2 * PI)
    var y = 1.0
    var sum = 1.0
    var m = 0.0
    while (abs(y) >= eps) {
        y *= -1.0 * k * k / ((m + 1.0) * (m + 2.0))
        sum += y
        m += 2.0
    }
    return sum
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var number = 1.0
    var numSqrt = 1.0
    var k = 1
    var numDig = 1.0
    while (k < n) {
        numSqrt += 1
        number = numSqrt.pow(2)
        var num = number.toInt()
        var numOfDig = 0
        while (num != 0) {
            num /= 10
            numOfDig += 1
        }
        k += numOfDig
        num = number.toInt()
        numDig = (num % 10).toDouble()
        if (k == n) break
        if (k < n) continue
        var newK = k
        while (num != 0) {
            numDig = ((num / 10) % 10).toDouble()
            num /= 10
            newK -= 1
            if (newK == n) break
        }
    }
    return numDig.toInt()
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var f = 1
    var pf = 1
    var k = 2
    var fDig = 0
    if (n == 1 || n == 2) return 1
    while (k < n) {
        val nf = f
        f += pf
        pf = nf
        var num = f
        var fNumDig = 0
        while (num != 0) {
            num /= 10
            fNumDig += 1
        }
        k += fNumDig
        num = f
        fDig = num % 10
        if (k == n) break
        if (k < n) continue
        var newK = k
        while (num != 0) {
            fDig = (num / 10) % 10
            num /= 10
            newK -= 1
            if (newK == n) break
        }
    }
    return fDig
}
