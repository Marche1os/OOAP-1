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

/**
 * Рефлексия
 *
 * В целом, оба варианта предоставляют необходимый API для описания АТД "Очередь".
 * Я добавил дополнительные статусы для команды, чтобы в коде-потребителе удобнее работать со статусами,
 * кроме того, можно создать иерархию значений статуса каждой команды, что улучшит читаемость потребительского кода.
 *
 * По итогу, мое решение максимально близко к эталонному, можно заключить, что данный АТД описан корректно и полно.
 */

/**
 * Эталонное решение
 *
 * abstract class Queue<T>
 *
 *   // конструктор
 * // постусловие: создана пустая очередь
 *   public Queue<T> Queue();
 *
 *   // команды
 * // постусловие: в хвост очереди добавлен новый элемент
 *   public void enqueue(T value);
 *
 * // предусловие: очередь не пуста;
 * // постусловие: из головы очереди удалён элемент
 *   public void dequeue();
 *
 *   // запросы
 * // предусловие: очередь не пуста
 *   public T get(); // получить элемент из головы очереди;
 *
 *   public int size(); // текущий размер очереди
 *
 *   // запросы статусов (возможные значения статусов)
 *   public int get_dequeue_status(); // успешно; очередь пуста
 *   public int get_get_status(); // успешно; очередь пуста
 */