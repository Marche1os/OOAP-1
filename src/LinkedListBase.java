abstract public class LinkedListBase<T> {
    static final int HEAD_OK = 0;
    static final int HEAD_EMPTY = 1; //список пуст
    static final int HEAD_NULL = 2; // команда head не была вызвана

    static final int TAIL_OK = 0;
    static final int TAIL_EMPTY = 1;
    static final int TAIL_NULL = 2;

    static final int RIGHT_OK = 0;
    static final int RIGHT_LAST = 1; //команда вызвана, когда курсор находится на последнем элементе списка
    static final int RIGHT_EMPTY = 2;
    static final int RIGHT_NULL = 3; //команда не была вызвана

    static final int PUT_RIGHT_OK = 0;
    static final int PUT_RIGHT_NULL = 1; //команда put_right не была вызвана

    static final int PUT_LEFT_OK = 0;
    static final int PUT_LEFT_NULL = 1; //команда put_left не была вызвана

    static final int REMOVE_OK = 0;
    static final int REMOVE_EMPTY = 1; //удаление на пустом списке
    static final int REMOVE_NULL = 2;
    static final int REMOVE_CLEAR = 3; //удаление единственного узла - по-сути, очистка списка

    protected int headStatus = HEAD_NULL;
    protected int tailStatus = TAIL_NULL;
    protected int rightStatus = RIGHT_NULL;
    protected int putRightStatus = PUT_RIGHT_NULL;
    protected int putLeftStatus = PUT_LEFT_NULL;
    protected int removeStatus = REMOVE_NULL;

    //команды:

    //предусловие: список не пуст
    //постусловие: курсор указывает на первый элемент списка
    abstract void head();

    //предусловие: список не пуст
    //постусловие: курсор указывает на последний элемент списка
    abstract void tail();

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

    int getHeadStatus() {
        return headStatus;
    }

    public int getTailStatus() {
        return tailStatus;
    }

    public int getRightStatus() {
        return rightStatus;
    }

    public int getPutRightStatus() {
        return putRightStatus;
    }

    public int getPutLeftStatus() {
        return putLeftStatus;
    }

    public int getRemoveStatus() {
        return removeStatus;
    }
}
