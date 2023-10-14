interface Queue<T> {

    // Конструктор:

    // Постусловие: создана новая структура
    fun getQueue(): Queue<T>

    // Команды:

    // Предусловие: добавляемый элемент не должен быть равен null
    // Постусловие: элемент добавлен в конец очереди, размер очереди увеличился на 1
    fun enqueue(value: T)

    // Предусловие: очередь не пуста
    // Постусловие: первый элемент в очереди удален, размер уменьшился на 1
    fun dequeue()

    // Запросы:

    // Предусловие: очередь не пуста
    // Постусловие: возвращает первый элемент очереди
    fun getFirst(): T

    fun size(): Int

    fun isEmpty(): Boolean

    // Статусы и их возможные значения:

    fun getEnqueueStatus(): EnqueueStatus // OK, QUEUE_IS_FULL, VALUE_IS_NULL, NOT_CALLED
    fun getDequeueStatus(): DequeueStatus // OK, QUEUE_IS_EMPTY, NOT_CALLED
    fun getFrontStatus(): FrontStatus // OK, QUEUE_IS_EMPTY, NOT_CALLED

    // Возможные состояния
    enum class EnqueueStatus {
        OK,
        QUEUE_IS_FULL,
        VALUE_IS_NULL,
        NOT_CALLED
    }

    enum class DequeueStatus {
        OK,
        QUEUE_IS_EMPTY,
        NOT_CALLED
    }

    enum class FrontStatus {
        OK,
        QUEUE_IS_EMPTY,
        NOT_CALLED
    }
}