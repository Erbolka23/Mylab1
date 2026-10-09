package com.example.mylab1

import java.util.Locale

// Лаба 3: классы, наследование, полиморфизм.
// Все задания запускаются из runLab3() внизу файла.
// Результат смотрим в Logcat (фильтр System.out).

// печатает дробное число с двумя знаками после точки
fun fmt(x: Double): String = String.format(Locale.US, "%.2f", x)


// ============================================================
// Задание 1. Sensor
// ============================================================

class Sensor(val id: String, var reading: Double) {

    init {
        // этот блок срабатывает при создании объекта
        require(id.isNotBlank()) { "id must not be blank" }     // id не может быть пустым
        require(reading.isFinite()) { "reading must be finite" } // число должно быть нормальным
    }

    fun update(reading: Double) {
        require(reading.isFinite()) { "reading must be finite" } // NaN и бесконечность не берём
        this.reading = reading // кладём новое значение в поле класса
    }

    override fun toString(): String = "Sensor(id=$id, reading=$reading)"
}

fun task1() {
    println("--- Task 1 ---")
    val s1 = Sensor("T-1", 21.5)
    val s2 = Sensor("H-1", 40.0)
    s1.update(23.0) // меняем показание первого датчика
    println(s1)
    println(s2)

    // пустой id должен вызвать ошибку, проверяем это
    try {
        Sensor("  ", 1.0)
    } catch (e: IllegalArgumentException) {
        println("Blank id rejected: ${e.message}")
    }
}


// ============================================================
// Задание 2. Counter
// ============================================================

class Counter(start: Int = 0) { // если start не указать, будет 0
    private var count: Int = start // private: снаружи число не поменять

    fun value(): Int = count // показывает текущее число

    fun inc(step: Int = 1) {
        require(step > 0) { "step must be positive" } // шаг должен быть больше нуля
        count += step
    }

    fun reset() {
        count = 0 // обнуляем счётчик
    }
}

fun task2() {
    println("--- Task 2 ---")
    val c = Counter() // создали счётчик без аргументов
    println("start = ${c.value()}")
    c.inc()     // +1
    c.inc(2)    // +2
    println("after inc() and inc(2) = ${c.value()}")

    val c2 = Counter(10) // счётчик, который начинается с 10
    println("Counter(10) = ${c2.value()}")
    c2.reset()
    println("after reset = ${c2.value()}")

    // шаг 0 запрещён, ловим ошибку
    try {
        c.inc(0)
    } catch (e: IllegalArgumentException) {
        println("inc(0) rejected: ${e.message}")
    }
}


// ============================================================
// Задание 3. Vehicle, Car, Bike
// ============================================================

open class Vehicle(val brand: String) { // open: от этого класса можно наследоваться
    init {
        require(brand.isNotBlank()) { "brand must not be blank" }
    }

    open fun info(): String = "Vehicle $brand" // open: потомки могут переписать этот метод
    open fun maxSpeedKmh(): Int = 0
}

class Car(brand: String, val doors: Int) : Vehicle(brand) { // Car берёт всё от Vehicle
    init {
        require(doors >= 2) { "doors must be >= 2" }
    }

    override fun info(): String = "Car $brand, doors=$doors" // override: свой вариант метода
    override fun maxSpeedKmh(): Int = 200
}

class Bike(brand: String, val gears: Int) : Vehicle(brand) {
    init {
        require(gears >= 1) { "gears must be >= 1" }
    }

    override fun info(): String = "Bike $brand, gears=$gears"
    override fun maxSpeedKmh(): Int = 40
}

fun task3() {
    println("--- Task 3 ---")
    val car = Car("Volvo", 4)
    val bike = Bike("Trek", 18)
    println("${car.info()} | max speed ${car.maxSpeedKmh()} km/h")
    println("${bike.info()} | max speed ${bike.maxSpeedKmh()} km/h")
}


// ============================================================
// Задание 4. Зарплаты (Employee, Accountant, Intern)
// ============================================================

open class Employee(val name: String, val baseSalary: Int) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
        require(baseSalary >= 0) { "salary must not be negative" }
    }

    open fun pay(): Int = baseSalary // обычный сотрудник получает оклад
    open fun describe(): String = "$name: ${pay()}"
}

class Accountant(name: String, baseSalary: Int, val bonusPercent: Int) : Employee(name, baseSalary) {
    init {
        require(bonusPercent in 0..100) { "bonusPercent must be in 0..100" }
    }

    // оклад от родителя плюс процент премии
    override fun pay(): Int = super.pay() + super.pay() * bonusPercent / 100

    // берём строку родителя и добавляем слово Accountant в начало
    override fun describe(): String = "Accountant " + super.describe()
}

class Intern(name: String, baseSalary: Int, val mentor: String) : Employee(name, baseSalary) {
    init {
        require(mentor.isNotBlank()) { "mentor must not be blank" }
    }

    override fun pay(): Int = super.pay() / 2 // стажёр получает половину оклада
    override fun describe(): String = super.describe() + " (mentor: $mentor)"
}

fun task4() {
    println("--- Task 4 ---")
    val staff: List<Employee> = listOf(
        Employee("Anna", 1000),
        Accountant("Bek", 2000, 10),
        Intern("Chyngyz", 800, "Bek")
    )
    var total = 0
    for (e in staff) {
        println(e.describe()) // для каждого вызовется его собственная версия
        total += e.pay()
    }
    println("Total pay = $total")
}


// ============================================================
// Задание 5. Стоимость доставки (Parcel)
// ============================================================

open class Parcel(val id: String, val weightKg: Double) {
    init {
        require(id.isNotBlank()) { "id must not be blank" }
        require(weightKg > 0.0) { "weight must be positive" }
    }

    open fun shippingCost(): Double = weightKg * 2.0 // обычная цена: 2 за килограмм
}

class ExpressParcel(id: String, weightKg: Double) : Parcel(id, weightKg) {
    override fun shippingCost(): Double = super.shippingCost() + 15.0 // плюс 15 за срочность
}

class EconomyParcel(id: String, weightKg: Double) : Parcel(id, weightKg) {
    override fun shippingCost(): Double = super.shippingCost() * 0.8 // скидка 20%
}

// складывает стоимость всех посылок в списке
fun totalShipping(parcels: List<Parcel>): Double = parcels.sumOf { it.shippingCost() }

fun task5() {
    println("--- Task 5 ---")
    val parcels: List<Parcel> = listOf(
        Parcel("P1", 5.0),
        ExpressParcel("P2", 3.0),
        EconomyParcel("P3", 10.0)
    )
    println("Total shipping = ${fmt(totalShipping(parcels))}")
}


// ============================================================
// Задание 6. Upcast (используем Vehicle)
// ============================================================

fun printFleet(fleet: List<Vehicle>) {
    for (v in fleet) {
        println(v.info()) // сработает info() именно Car или Bike
    }
}

fun task6() {
    println("--- Task 6 ---")
    val v: Vehicle = Car("Volvo", 4) // переменная типа Vehicle, а внутри лежит Car
    println(v.info())                // всё равно выведется версия из Car

    printFleet(listOf(Car("Volvo", 4), Bike("Trek", 18)))
}


// ============================================================
// Задание 7. Smart cast и безопасное приведение
// ============================================================

fun doorsOrZero(v: Vehicle): Int {
    // если v на самом деле Car, Kotlin сам разрешает брать v.doors
    return if (v is Car) v.doors else 0
}

fun task7() {
    println("--- Task 7 ---")
    println("Car doors = ${doorsOrZero(Car("Audi", 4))}")
    println("Bike doors = ${doorsOrZero(Bike("Giant", 21))}")

    val vehicle: Vehicle = Bike("X", 3)
    val c = vehicle as? Car // as? не падает, а возвращает null, если тип не подходит
    println("Bike as? Car = $c")

    // val bad = vehicle as Car  // эта строка упала бы с ошибкой, поэтому она закомментирована
}


// ============================================================
// Задание 8. Оценки (Assessment, Quiz, Project)
// ============================================================

open class Assessment(val title: String, val maxPoints: Int) {
    init {
        require(title.isNotBlank()) { "title must not be blank" }
        require(maxPoints > 0) { "maxPoints must be positive" }
    }

    // доля баллов от 0.0 до 1.0; лишние баллы обрезаются через coerceIn
    open fun scoreTowardGrade(raw: Int): Double =
        (raw.coerceIn(0, maxPoints)).toDouble() / maxPoints
}

class Quiz(title: String, maxPoints: Int, val penaltyIfLate: Double) : Assessment(title, maxPoints) {
    init {
        require(penaltyIfLate in 0.0..0.5) { "penaltyIfLate must be in 0.0..0.5" }
    }

    // результат родителя умножаем на (1 - штраф за опоздание)
    override fun scoreTowardGrade(raw: Int): Double =
        super.scoreTowardGrade(raw) * (1.0 - penaltyIfLate)
}

class Project(title: String, maxPoints: Int, val bonusCap: Double) : Assessment(title, maxPoints) {
    init {
        require(bonusCap >= 0.0) { "bonusCap must not be negative" }
    }

    // Формула: итог = обычная доля (максимум 1.0) + бонус.
    // Бонус = баллы сверх maxPoints, делённые на maxPoints, но не больше bonusCap.
    // Поэтому итог может быть выше 1.0, но не выше 1.0 + bonusCap.
    override fun scoreTowardGrade(raw: Int): Double {
        val baseRatio = super.scoreTowardGrade(raw) // обычная часть считается у родителя
        val extraRaw = (raw - maxPoints).coerceAtLeast(0).toDouble() // сколько баллов сверх максимума
        val extraRatio = minOf(extraRaw / maxPoints, bonusCap)       // бонус, но не больше лимита
        return baseRatio + extraRatio
    }
}

fun task8() {
    println("--- Task 8 ---")
    val raw = 110 // одинаковое количество баллов для всех работ
    val items: List<Assessment> = listOf(
        Quiz("Quiz 1 (late 10%)", 100, 0.1),
        Quiz("Quiz 2 (on time)", 50, 0.0),
        Project("Final project (cap 20%)", 100, 0.2),
        Project("Mini project (cap 5%)", 100, 0.05)
    )
    for (a in items) {
        println("${a.title}: ${fmt(a.scoreTowardGrade(raw))}")
    }
}


// ============================================================
// Задание 9. Уведомления (Notification)
// ============================================================

open class Notification(private val payload: String) { // private: текст спрятан от всех
    protected fun body(): String = payload             // protected: достать текст могут только потомки
    open fun render(): String = body()
}

class EmailNotification(payload: String, val to: String) : Notification(payload) {
    override fun render(): String = "[EMAIL to $to] ${body()}"
}

class SmsNotification(payload: String, val phone: String) : Notification(payload) {
    override fun render(): String = "[SMS to $phone] ${body()}"
}

fun sendAll(items: List<Notification>) {
    for (n in items) {
        println(n.render()) // у каждого вида уведомления свой формат
    }
}

fun task9() {
    println("--- Task 9 ---")
    sendAll(
        listOf(
            Notification("Plain message"),
            EmailNotification("Your lab is graded", "student@example.com"),
            SmsNotification("Code: 1234", "+996700000000")
        )
    )
}


// ============================================================
// Задание 10. Билеты в зоопарк (Ticket)
// ============================================================

open class Ticket(val code: String, val basePrice: Int) {
    init {
        require(code.isNotBlank()) { "code must not be blank" }
        require(basePrice >= 0) { "basePrice must not be negative" }
    }

    open fun price(): Int = basePrice
}

class AdultTicket(code: String, basePrice: Int) : Ticket(code, basePrice)

class ChildTicket(code: String, basePrice: Int, val guardianCode: String) : Ticket(code, basePrice) {
    init {
        require(guardianCode.isNotBlank()) { "guardianCode must not be blank" }
    }

    override fun price(): Int = super.price() / 2 // детский билет в два раза дешевле
}

class VipTicket(code: String, basePrice: Int, val loungeAccess: Boolean) : Ticket(code, basePrice) {
    // к цене добавляем 50, если есть доступ в лаунж, иначе 20
    override fun price(): Int = super.price() + if (loungeAccess) 50 else 20
}

// общая сумма за все билеты
fun checkout(tickets: List<Ticket>): Int = tickets.sumOf { it.price() }

// собирает коды сопровождающих только у детских билетов
fun childGuardianCodes(tickets: List<Ticket>): List<String> {
    val result = mutableListOf<String>()
    for (t in tickets) {
        if (t is ChildTicket) {         // проверяем, что билет детский
            result.add(t.guardianCode)  // тут Kotlin уже знает, что t это ChildTicket
        }
    }
    return result // порядок такой же, как в исходном списке
}

fun task10() {
    println("--- Task 10 ---")
    val tickets: List<Ticket> = listOf(
        AdultTicket("A-1", 80),
        ChildTicket("C-1", 40, "G-1"),
        VipTicket("V-1", 70, true),
        ChildTicket("C-2", 20, "G-9")
    )
    println("total=${checkout(tickets)}")
    println("guardians=${childGuardianCodes(tickets).joinToString(", ")}")
}


// ============================================================
// Запуск всех заданий
// ============================================================

fun runLab3() {
    task1()
    task2()
    task3()
    task4()
    task5()
    task6()
    task7()
    task8()
    task9()
    task10()
}