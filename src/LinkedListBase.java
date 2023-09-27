abstract public class LinkedListBase<T> {
    static final int HEAD_OK = 0;
    static final int HEAD_EMPTY = 1; //список пуст
    static final int HEAD_NULL = 2; // команда head не была вызвана

    static final int TAIL_OK = 0;
    static final int TAIL_EMPTY = 1;
    static final int TAIL_NULL = 2;

    static final int REMOVE_OK = 0;
    static final int REMOVE_EMPTY = 1; //удаление на пустом списке
    static final int REMOVE_NULL = 2;
    static final int REMOVE_CLEAR = 3; //удаление единственного узла - по-сути, очистка списка

    static final int FIND_OK = 0;
    static final int FIND_NOT_FOUND = 1; //искомое значение не найдено
    static final int FIND_NULL = 2; //команда не была вызвана

    protected int headStatus = HEAD_NULL;
    protected int tailStatus = TAIL_NULL;
    protected int removeStatus = REMOVE_NULL;
    protected int findStatus = FIND_NULL;

    //команды:

    //предусловие: список не пуст
    //постусловие: курсор указывает на первый элемент списка
    abstract void head();

    //предусловие: список не пуст
    //постусловие: курсор указывает на последний элемент списка
    abstract void tail();

    //постусловие: элемент добавлен в конец списка
    abstract void add_tail(final T value);

    //постусловие: значение, хранимое в узле, заменено
    abstract void replace(final T value);

    //предусловие: список не пуст, искомое значение присутствует в списке
    //постусловие: курсор указывает на следующий узел с искомым значением
    abstract void find(final T value);

    //постусловие: список не содержит узлов, хранящих переданное значение
    abstract void remove_all(final T value);

    //предусловие: список не пуст и курсор на текущий элемент указывает не на последний элемент в списке
    //постусловие: курсор сместился на следующий элемент
    abstract void right();

    //постусловие: следующий элемент после текущего - вставляемое значение. Прежний следующий элемент теперь также содержит ссылку на свой левый элемент - вставленное значение, если вставка была не в хвост
    abstract void put_right(final T value);

    //постусловие: текущее значение содержит ссылку на вставленный узел. Ссылки у прежнего элемента, текущего и у вставляемого корректны проставлены. Пограничный случай: вставка в голову списка
    abstract void put_left(final T value);

    /**
     * предусловие: список не пуст
     * постусловие: текущий узел удален, т.е.
     * До удаления узла B: A <-> B <-> C.
     * После удаления узла B: A <-> C
     * Двунаправленная стрелка <-> указывает ссылку как на следующий узел, так и на предыдущий.
     *
     * И если узел не последний, тогда указатель на текущий элемент перемещается вправо.
     * Иначе, если узел узел не первый, тогда указатель на текущий элемент перемещается влево.
     * Иначе - удаление единственного элемента списка - по-сути, очистка списка
     */
    abstract void remove();

    //постусловие: список пуст
    abstract void clear();

    //запросы:

    abstract int size();

    //предусловие: курсор установлен на какой-либо элемент (список не пуст)
    abstract T get();

    abstract boolean is_head();

    abstract boolean is_tail();

    abstract boolean is_value();

    int getHeadStatus() {
        return headStatus;
    }

    public int getTailStatus() {
        return tailStatus;
    }

    public int getRemoveStatus() {
        return removeStatus;
    }

    public int getFindStatus() {
        return findStatus;
    }
}