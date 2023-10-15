interface NativeDictionary<T> {
    // Конструктор

    // Постусловие: создан новый словарь
    fun NativeDictionary(): NativeDictionary<T>

    // Команды

    // Предусловие: [key] отсутствует в таблице
    // Постусловие: создана новая запись с ключом [key] и значением [value], размер увеличен на 1
    fun put(key: String, value: T)

    // Предусловие: Запись с ключом [key] присутствует в таблице
    // Постусловие: запись с ключом [key] удалена, размер уменьшился на 1
    fun remove(key: String)

    // Запросы

    // Присутствует ли запись с ключом [key] в таблице
    fun isKey(key: String): Boolean

    // Предусловие: запись с ключом [key] есть в таблице
    fun get(key: String): T

    // Количество элементов в словаре
    fun getSize(): Int

    // Статусы

    fun getPutStatus() // OK, запись по переданному ключу уже присутствует в таблице

    fun getRemoveStatus() // OK, записи по переданному ключу нет в таблице

    fun getGetStatus() // OK, записи по переданному ключу нет в таблице
}

/**
 * Рефлексия
 *
 * Сравнивая мое решение с эталонным, выделяется отсутствие в эталонном решении предусловия для команды put.
 * В моем решении я добавлял предусловие, чтобы исключить перезапись существующей записи. Следовало тогда добавить отдельно команду replace, которая явно заменяет существующую запись по ключу.
 * В целом это не является ошибкой, имеет право быть и зависит от условий задачи. Это разные политики для команды put, обе применимы.
 *
 * Также в обоих решениях присутствует запрос [isKey], он действительно необходим для явной проверки существования записи с ключом в таблице, чтобы сделать прозрачнее такие операции, как remove или get.
 *
 * В целом, мое решение соответствует эталонному, можно заключить, что АТД "Словарь" спроектирован корректно.
 */

/**
 * Эталонное решение
 *
 * abstract class NativeDictionary<T>
 *
 *   // конструктор
 * // постусловие: создан пустой ассоциативный массив
 *   public NativeDictionary<T> NativeDictionary();
 *
 *   // команды
 * // постусловие: в массив добавлена новая пара ключ-значение,
 * // если данный ключ отсутствовал;
 * // в противном случае обновлено значение
 * // для соответствующего ключа
 *   public void put(string key, T value);
 *
 * // предусловие: ключ key присутствует в массиве
 * // постусловие: ключ удаляется вместе со своим значением
 *   public void remove(string key);
 *
 *   // запросы
 * // предусловие: ключ key присутствует в массиве
 *   public T get(string key);
 *
 * // данный запрос требуется отдельно,
 * // чтобы не использовать вместо него
 * // второстепенный запрос проверки статуса запроса get
 *   public bool is_key(string key); // проверка наличия ключа в массиве
 *
 *   public int size(); // текущий размер массива
 *
 *   // запросы статусов (возможные значения статусов)
 *   public int get_put_status();
 *     // успешно добавлен новый ключ и значение;
 *     // успешно обновлено значение существующего ключа
 *
 *   public int get_remove_status(); // успешно; ключ отсутствует
 *
 *   public int get_get_status(); // успешно; ключ отсутствует
 */