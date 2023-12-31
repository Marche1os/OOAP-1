interface HashTable<T> {

    // Конструктор
    // постусловие: создана пустая таблица
    HashTable(maxSize: Int)

    // Команды:

    // предусловие: текущий размер таблицы меньше максимально допустимого
    // найден свободный слот для нового элемента
    // постусловие: для нового значения найден слот, в который помещено новое значение, размер таблицы увеличен на 1
    fun put(value: T)

    // постусловие: элемент удален и размер таблицы уменьшен на 1
    fun remove(value: T)

    // Запросы:

    // Возвращает индекс элемента или -1, если не найден
    fun find(value: T): Int

    // Чистая функция. Возвращает корректный индекс слота
    fun hashFun(value: T): Int

    // Чистая функция. Находит индекс пустого слота для значения, или -1
    fun seekSlot(value: T): Int

    // количество элементов в таблице
    fun getSize(): Int

    // Статусы:

    fun getPutStatus() // OK, таблица заполнена, ошибка разрешения коллизии

    fun getFindStatus() // ОК, не найден элемент

    fun getSeekSlotStatus() // ОК, не найден индекс пустого слота
}

/**
 * Рефлексия
 *
 * В сравнении с эталонным решением:
 * - Пропустил предусловие у команды remove. Отсутствие предусловия здесь влияет на ожидаемый результат выполнения команды и создает множество вариаций результата выполнения.
    * Этот момент изначально не учел, предусловие здесь действительно необходимо для внесения ясности в результат команды.
    * Как результат, отсутствует запрос на статус выполнения remove.
 *
 * - Предусловие команды put. В целом, в моем решении корректно задано предусловие, просто можно было его выразить лаконичнее: просто "Не найден слот для нового элемента".
    * Это уменшило бы множество значений статуса выполнения команды put.
 * - Отличие в реализации запроса get. В моем решении это запрос find, возвращающий индекс элемента. Думаю, имеет право быть, по крайней мере для удобства работы с таблицей. Соответственно, ошибкой не являетяс.
 *
 * Также в моем решении присутствуют функции [hashFun] и [seekSlot], хотел показать, что это чистые функции и относятся к категории "Запросы".
 * В действительности, это, как правило, вопрос внутренней реализации хэш-структуры и скрыто для внешних потребителей.
 *
 * По итогу, в целом решение соответствует эталонному, но стоит обратить внимание на предусловие команды remove. И обратить внимание на значения статусов выполнения команды.
 */

/**
 * Эталонное решение
 *
 * abstract class HashTable<T>
 *
 *   // конструктор
 * // постусловие: создана пустая хэш-таблица заданного размера
 *   public HashTable<T> HashTable(int sz);
 *
 *   // команды
 * // предусловие: в таблице имеется свободный слот для value;
 * // постусловие: в таблицу добавлено новое значение
 *   public void put(T value);
 *
 * // предусловие: в таблице имеется значение value;
 * // постусловие: из таблицы удалено значение value
 *   public void remove(T value);
 *
 *   // запросы
 *   public bool get(T value); // содержится ли значение value в таблице
 *
 *   public int size(); // количество элементов в таблице
 *
 *   // запросы статусов (возможные значения статусов)
 *   public int get_put_status(); // успешно;
 *     // система коллизий не смогла найти свободный слот для значения
 *
 *   public int get_remove_status(); // успешно; значения нету в таблице
 */