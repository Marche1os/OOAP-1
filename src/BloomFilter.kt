interface BloomFilter<T> {

    // Конструктор

    // Постусловие: создан битовый массив размера [length]
    fun BloomFilter(length: Int): BloomFilter<T>

    // Команды

    // Постусловие: элемент добавлен в фильтр
    fun add(value: T)

    // Предусловие: список хэш-функций должен быть не пустым.
    fun setHashFunctions(hashFunctions: List<(T) -> Int>)

    // Запросы

    // Имеется ли значение в фильтре
    fun isValue(value: T): Boolean

    // Статусы
    fun getSetHashFunctionsStatus() // OK, список хэш-функций пуст
}