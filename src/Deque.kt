interface QueueBase<T> {

    // конструктор
    // постусловие: создана пустая очередь
    fun getQueue(): QueueBase<T>

    // команды:

    // постусловие: в хвост очереди добавлен новый элемент
    fun addTail(value: T)

    // предусловие: очередь не пуста;
    // постусловие: из головы очереди удалён элемент
    fun removeFront()

    // запросы:

    // предусловие: очередь не пуста
    fun getFront(): T // получить элемент из головы очереди;

    fun size(): Int // текущий размер очереди

    // запросы статусов (возможные значения статусов)
    fun get_dequeue_status(): Int // успешно; очередь пуста
    fun get_get_status(): Int // успешно; очередь пуста
}

interface Queue<T> : QueueBase<T>

interface Deque<T> : QueueBase<T> {
    // Команды

    // постусловие: новый элемент добавлен в начало очереди, размер увеличился на 1
    fun addFront(value: T)

    // предусловие: очередь не пуста
    // постусловие: из хвоста очереди удален элемент, размер уменьшился на 1
    fun removeTail()

    // предусловие: очередь не пуста
    fun getTail(): T

    fun getRemoveTailStatus() // успех; очередь пуста
    fun getGetTailStatus() // успех; очередь пуста
}

/**
 * Рефлексия
 *
 * Использовал концепцию частично реализованных классов, как было в лекции. Сравнивая с эталонным решением, получилось достойно!
 * Правильно выделил общие для двух реализаций методы API. В Queue отсутствует конструктор, но это только из-за языковых ограничений, что конструктор не может быть в интерфейсе или абстрактном классе.
 * Так, конечно, он подразумевается.
 *
 * В целом, решение соответствует эталонному, наш АТД описан корректно.
 */



/**
 * Эталонное решение
 *
 * АТД ParentQueue
 *
 * abstract class ParentQueue<T>
 *
 *   // конструктор
 *   public ParentQueue<T> ParentQueue();
 *
 *   // команды
 * // постусловие: в хвост очереди добавлен новый элемент
 *   public void add_tail(T value);
 *
 * // предусловие: очередь не пуста;
 * // постусловие: из головы очереди удалён элемент
 *   public void remove_front();
 *
 *   // запросы
 * // предусловие: список не пуст
 *   public T get_front(); // значение элемента в голове очереди;
 *
 *   public int size(); // текущий размер очереди
 *
 *   // запросы статусов (возможные значения статусов)
 *   public int get_remove_front_status(); // успешно; очередь пуста
 *   public int get_get_front_status(); // успешно; очередь пуста
 * АТД Queue
 *
 * abstract class Queue<T> : ParentQueue<T>
 *
 *   // конструктор
 *   public Queue<T> Queue();
 *
 * АТД Deque
 *
 * abstract class Deque<T> : ParentQueue<T>
 *
 *   // конструктор
 * // постусловие: создана пустая очередь
 *   public Deque<T> Deque();
 *
 *   // команды
 * // постусловие: в голову очереди добавлен новый элемент
 *   public void add_front(T value);
 *
 * // предусловие: очередь не пуста;
 * // постусловие: из хвоста очереди удалён элемент
 *   public void remove_tail();
 *
 *   // запросы
 * // предусловие: список не пуст
 *   public T get_tail(); // значение элемента в хвосте очереди;
 *
 *   public int size(); // текущий размер очереди
 *
 *   // запросы статусов (возможные значения статусов)
 *   public int get_remove_tail_status(); // успешно; очередь пуста
 *   public int get_get_tail_status(); // успешно; очередь пуста
 */