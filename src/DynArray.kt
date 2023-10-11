interface DynArray<T> {
    val array: Array<T>

    // команды:

    // предусловие: элемент, который нужно добавить, не должен быть равен null
    // постусловие: размер массива увеличивается на 1, элемент добавляется в конец
    // происходит реаллокация массива, если достигнут верхний порог заполненности
    fun add(element: T)

    // предусловие: индекс лежит в диапазоне [0..capacity - 1]
    // элемент, который нужно установить, не должен быть равен null
    // постусловие: элемент, находящийся по заданному индексу, заменяется на новый элемент
    fun set(value: T, index: Int)

    // предусловие: индекс лежит в диапазоне [0..capacity - 1]
    // постусловие: размер массива уменьшается на 1, элемент, находящийся по заданному индексу, удаляется
    // происходит реаллокация массива, если достигнут нижний порог заполненности
    fun remove(index: Int)


    // запросы:

    // предусловие: индекс лежит в диапазоне [0..capacity - 1]
    // постусловие: возвращается элемент, находящийся по заданному индексу,
    fun get(index: Int): T

    fun getCount(): Int

    fun getCapacity(): Int
}

sealed interface ReallocationStrategy<T> {
    interface Additive<T> : ReallocationStrategy<T> {
        fun resize(array: DynArray<T>)
    }

    interface Multiplicative<T> : ReallocationStrategy<T> {
        fun resize(array: DynArray<T>)
    }
}