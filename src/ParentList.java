import java.util.LinkedList;

public abstract class ParentList<T> {
    static final int HEAD_OK = 0;
    static final int HEAD_EMPTY = 1; //список пуст
    static final int HEAD_NULL = 2; // команда head не была вызвана

    static final int RIGHT_OK = 0;
    static final int RIGHT_END = 1;
    static final int RIGHT_NULL = 2;

    static final int PUT_RIGHT_OK = 0;
    static final int PUT_RIGHT_EMPTY = 1;
    static final int PUT_RIGHT_NULL = 2;

    static final int PUT_LEFT_OK = 0;
    static final int PUT_LEFT_EMPTY = 1;
    static final int PUT_LEFT_NULL = 2;

    static final int REMOVE_OK = 0;
    static final int REMOVE_EMPTY = 1; //удаление на пустом списке
    static final int REMOVE_NULL = 2;

    static final int REPLACE_OK = 0;
    static final int REPLACE_EMPTY = 1;
    static final int REPLACE_NULL = 2;

    static final int GET_OK = 0;
    static final int GET_EMPTY = 1;
    static final int GET_NULL = 2;

    static final int FIND_OK = 0;
    static final int FIND_NOT_FOUND = 1; //искомое значение не найдено
    static final int FIND_NULL = 2; //команда не была вызвана

    protected int headStatus = HEAD_NULL;
    protected int removeStatus = REMOVE_NULL;
    protected int findStatus = FIND_NULL;
    protected int rightStatus = RIGHT_NULL;
    protected int putRightStatus = PUT_RIGHT_NULL;
    protected int putLeftStatus = PUT_LEFT_NULL;
    protected int getStatus = GET_NULL;

    public LinkedList<T> linkedList = new LinkedList<>();
    public int cursor = -1;

    public void head() {
        if (linkedList.isEmpty()) {
            headStatus = HEAD_EMPTY;
            return;
        }
        cursor = 0;
        headStatus = HEAD_OK;
    }

    public void right() {
        //если правее нет элемента
        if (cursor >= linkedList.size() - 1) {
            rightStatus = RIGHT_END;
            return;
        }
        //если правее курсора есть элемент
        cursor++;
        rightStatus = RIGHT_OK;
    }

    public void put_right(final T value) {
        if (linkedList.isEmpty() || cursor >= linkedList.size()) {
            putRightStatus = PUT_RIGHT_EMPTY;
            return;
        }
        linkedList.add(cursor + 1, value);
        putRightStatus = PUT_RIGHT_OK;
    }

    public void put_left(final T value) {
        if (linkedList.isEmpty() || cursor <= 0) {
            putLeftStatus = PUT_LEFT_EMPTY;
            return;
        }
        linkedList.add(cursor - 1, value);
        cursor++;
        putLeftStatus = PUT_LEFT_OK;
    }

    public void remove() {
        if (linkedList.isEmpty()) {
            removeStatus = REMOVE_EMPTY;
            return;
        }

        linkedList.remove(cursor);

        if (linkedList.isEmpty()) {
            cursor = -1;
        } else if (cursor < linkedList.size() - 1) {
            cursor++;
        } else {
            cursor--;
        }
        removeStatus = REMOVE_OK;
    }

    public void clear() {
        linkedList = new LinkedList<>();
        cursor = -1;

        headStatus = HEAD_NULL;
        removeStatus = REMOVE_NULL;
        findStatus = FIND_NULL;
        rightStatus = RIGHT_NULL;
        putRightStatus = PUT_RIGHT_NULL;
        putLeftStatus = PUT_LEFT_NULL;
        getStatus = GET_NULL;
    }

    public void add_tail(final T value) {
        linkedList.addLast(value);
    }

    public void remove_all(final T value) {
        linkedList.removeIf(t -> t.equals(value));
    }

    public void replace(final T value) {
        if (linkedList.isEmpty()) {
            return;
        }

        linkedList.set(cursor, value);
    }

    public void find(final T value) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).equals(value)) {
                cursor = i;
                findStatus = FIND_OK;
                break;
            }
        }
        findStatus = FIND_NOT_FOUND;
    }

    //запросы:
    public T get() {
        if (linkedList.isEmpty()) {
            getStatus = GET_EMPTY;
            return null;
        }

        final T value = linkedList.get(cursor);

        getStatus = GET_OK;

        return value;
    }

    public int getHeadStatus() {
        return headStatus;
    }

    public int getRemoveStatus() {
        return removeStatus;
    }

    public int getFindStatus() {
        return findStatus;
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

    public int getGetStatus() {
        return getStatus;
    }
}

class LinkedListImpl<T> extends ParentList<T> {

}

class TwoWayList<T> extends ParentList<T> {
    static final int LEFT_OK = 0;
    static final int LEFT_EMPTY = 1;
    static final int LEFT_NULL = 2;

    protected int leftStatus = LEFT_NULL;

    public void left() {
        if (linkedList.isEmpty()) {
            leftStatus = LEFT_EMPTY;
        }

        cursor--;
        leftStatus = LEFT_OK;
    }

    @Override
    public void clear() {
        super.clear();
        leftStatus = LEFT_NULL;
    }

    public int getLeftStatus() {
        return leftStatus;
    }
}

/**
 * Рефлексия:
 * В целом, идея в моем решении следует идеи в эталонном. Есть различия в подходах: я сделал сразу реализацию, плюс ошибся с конструктором и типом коллекции.
 * Также есть недочет с методом left в реализации TwoWayList, не учел предусловие "Слева должен быть элемент", сделал только предусловия непустого списка. Это существенное упущение.
 * Правильно выделил команды и запросы, но сделал недочет: оформил пред- пост- условия не в виде комментариев, а непосредственно реализацией.
 *
 * Так, по результатам задания, узнал о концепции частично реализованных классах. В Java SDK на концепции частично реализованных классах реализованы коллекции (Collections Framework).
 * Буду иметь в виду и искать возможности применения там, где это уместно.
 */




/**
 * Решение:
 * abstract class ParentList<T>
 *
 *   // конструктор
 *   public ParentList<T> ParentList();
 *
 *   // команды
 *   public void head();
 *   public void tail();
 *   public void right();
 *   public void put_right(T value);
 *   public void put_left(T value);
 *   public void remove();
 *   public void clear();
 *   public void add_tail(T value);
 *   public void remove_all(T value);
 *   public void replace(T value);
 *   public void find(T value);
 *
 *   // запросы
 *   public T get();
 *   public bool is_head();
 *   public bool is_tail();
 *   public bool is_value();
 *   public int size();
 *
 *   // запросы статусов
 *   public int get_head_status();
 *   public int get_tail_status();
 *   public int get_right_status();
 *   public int get_put_right_status();
 *   public int get_put_left_status();
 *   public int get_remove_status();
 *   public int get_replace_status();
 *   public int get_find_status();
 *   public int get_get_status();
 *
 *   abstract class LinkedList<T> : ParentList<T>
 *
 *   // конструктор
 *   public LinkedList<T> LinkedList();
 * abstract class TwoWayList<T> : ParentList<T>
 *
 *   // конструктор
 *   public TwoWayList<T> TwoWayList();
 *
 * // предусловие: левее курсора есть элемент;
 * // постусловие: курсор сдвинут на один узел влево
 *   public void left();
 *
 *   public int get_left_status(); // успешно; левее нету элемента
 */