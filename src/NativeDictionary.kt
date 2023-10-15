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