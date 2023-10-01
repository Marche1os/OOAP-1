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

    //постусловие: новый узел вставлен справа от текущего. Т.е. передвигая курсор вправо, получим узел с новым значением
    abstract void put_right(final T value);

    //постусловие: новый узел вставлен слева от текущго. Т.е. передвигая курсор влево, получим узел с новым значением
    abstract void put_left(final T value);

    /**
     * предусловие: список не пуст
     * постусловие: текущий узел удален
     * Алгоритм смещения курсора:
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

    public int getHeadStatus() {
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

/**
 * 2.2
 * Потому что в базовом случае мы не имеем информации о последнем узле.
 * Мы получаем элемент списка посредством связки get-right, и в этой конструкции, не используя дополнительные атрибуты класса, нам не узнать, что узел последний.
 * В связи с этим операция не сводима к другим атомарным операциям. Так, например, replace сводится к операциям get, right.
 *
 */

/**
 * 2.3
 * Мы уходим от концепции узлов и связей между ними в сторону более абстрактной модели - связный список и хранимые в нем значения.
 * Выдавать список узлов в нашей новой модели - значит, раскрывать внутреннюю реализацию, что не соответствует АТД.
 * Также команда findAll будет идеалогически конфликтовать с командой find - поиск искомого значения и установка указателя на соответствующем элементе, что в свою очередь сделает наш АТД менее прозрачным.
 */


//Правильные ответы:

/**
 * 2.2
 *  Операция tail не должна быть сводима к другим операциям, потому что правильная реализация связанного списка подразумевает хранение как условного внутреннего элемента head_pointer (указатель на голову списка), так и условного элемента tail_pointer (указатель на хвост списка). В противном случае операция tail, выраженная через head, right и is_tail, потребовала бы O(N) ресурсов.
 *
 * Это пример того, что при проектировании АТД надо обязательно учитывать и эффективность его реализации!
 */

/**
 * Операция поиска всех узлов с заданным значением больше не нужна, потому что вместо неё введена логика последовательного перемещения курсора к следующему искомому элементу с нужным значением.
 */

//Выводы
/**
 * К сожалению, не учел несколько необходимость предусловия "Список не пуст" для команд: put_left, put_right, replace.
 * Предусловие здесь необходимо для гарантии правильной работы программы: так, если список пуст, то, очевидно, вставить влево/вправо от текущего узла значение мы не можем. Как и заменить его.
 *
 * Также добавил лишнее предусловие "Список не пуст и искомое значение присутствует в списке" для команды find.
 * Здесь, конечно, команда поиска может не вернуть значение: ситуация, когда значения нет, абсолютно нормальна.
 * И не важно, пуст список при этом или нет, это не влияет на корректность работы и при пустом списке просто значение не будет найдено.
 *
 * В основном, контракт методов был реализован корректно, были описаны возможные значения статусов команд (базово я брал 3 значения - успех; предусловие не выполнено; команда не была еще выполнена).
 *
 */