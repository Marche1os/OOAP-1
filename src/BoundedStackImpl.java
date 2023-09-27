import java.util.ArrayList;
import java.util.List;

abstract class BoundedStack<T> {
    static final int DEFAULT_CAPACITY = 32;
    static final int PUSH_OK = 0;
    static final int PUSH_FILLED = 1;
    static final int PUSH_NULL = 2;
    static final int PEEK_OK = 0;
    static final int PEEK_EMPTY_STACK = 1;
    static final int PEEK_NULL = 2;
    static final int POP_OK = 0;
    static final int POP_EMPTY_STACK = 1;
    static final int POP_NULL = 2;

    protected int capacity;

    protected int pushStatus = PUSH_NULL;
    protected int peekStatus = PEEK_NULL;
    protected int popStatus = POP_NULL;

    abstract void push(final T value);

    abstract T peek();

    abstract T pop();

    abstract void clear();

    abstract public int size();
}

public class BoundedStackImpl<T> extends BoundedStack<T> {

    private List<T> stack;

    /**
     * пост-условие: создан новый стек с размером по-умолчанию (32)
     */
    public BoundedStackImpl() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * пост-условие: создан новый стек вместимостью @capacity
     *
     * @param capacity - максимальное количество элементов
     */
    public BoundedStackImpl(final int capacity) {
        if (capacity >= 1) {
            stack = new ArrayList<>(capacity);
            this.capacity = capacity;
        }
        if (capacity < 1) {
            stack = new ArrayList<>(DEFAULT_CAPACITY);
            this.capacity = DEFAULT_CAPACITY;
        }
    }

    //пред-условия: стек не достиг верхней границы по вместимости
    //пост-условие: элемент успешно добавлен в стек
    @Override
    public void push(final T value) {
        if (stack.size() >= capacity) {
            pushStatus = PUSH_FILLED;
            return;
        }
        stack.add(value);

        pushStatus = PUSH_OK;
    }

    /**
     * пред-условие: стек не пуст
     * пост-условие: последний элемент стека извлечен без удаления
     */
    @Override
    public T peek() {
        if (stack.isEmpty()) {
            peekStatus = PEEK_EMPTY_STACK;
            return null;
        }

        final T value = stack.get(stack.size() - 1);
        peekStatus = PEEK_OK;

        return value;
    }

    /**
     * пред-условие: стек не пуст
     * пост-условие: удален и возвращен верхний элемент стека. Пример: стек из элементов (1, 2, 3) операция pop() вернет 3
     */
    @Override
    public T pop() {
        if (stack.isEmpty()) {
            popStatus = POP_EMPTY_STACK;
            return null;
        }

        final T value = stack.remove(stack.size() - 1);
        popStatus = POP_OK;

        return value;
    }

    /**
     * пост-условие: стек очищен и не содержит элементов
     */
    @Override
    public void clear() {
        stack = new ArrayList<>(capacity);
        pushStatus = PUSH_NULL;
        peekStatus = PEEK_NULL;
        popStatus = POP_NULL;
    }

    @Override
    public int size() {
        return stack.size();
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public int getPeekStatus() {
        return peekStatus;
    }

    public int getPopStatus() {
        return popStatus;
    }
}

/**
    
Некоторые рассуждения по теории:
1. Абстрактный тип данных представляет собой интерфейс для работы с типом
данных, определяющий его операции и свойства, независимо от конкретной
реализации.
2. АТД описывает только "что" может быть сделано с данными, не указывая на
"как" это делается. Реализацию можно выбирать самостоятельно, используя
различные структуры данных. Например, для реализации стека можно
использовать массив или связный список.
3. АТД являются основой для разработки алгоритмов и структур данных,
позволяют абстрагироваться от деталей реализации и сосредоточиться на работе с
данными.
4. АТД может быть определен как множество объектов, описываемое операциями,
применимыми к этим объектам, и их свойствами.
5. В программировании АТД обычно представляются в виде интерфейсов, которые
скрывают конкретные реализации типов данных. Работа с АТД осуществляется
только через их интерфейсы, что обеспечивает гибкость и удобство при изменении
реализации.
6. Для добавления АТД в проект необходимо определить тип данных (возможно,
дженерик), описать интерфейс с методами, наложить ограничения на множество
значений, с которыми работает метод (предусловия и/или постусловия).
7. Методы классов, реализующие АТД, можно разделить на конструкторы, запросы
(чистые функции) и команды, изменяющие состояние класса. Если требуется
получать результат работы команды, то правильный объектно-ориентированный
подход предполагает явно вызывать статус отработавшей команды. Следует
учитывать различные статусы, и то, что команда может еще ни разу не отработать.
8. Абстрактные типы данных позволяют отвязаться от конкретных реализаций
классов, работать с интерфейсами типов и использовать принцип инкапсуляции в
объектно-ориентированном программировании.
Замечания по заданию:
Пред- и пост- условия в целом были корректно заданы:
- Учтены предусловия, когда стек пуст

- Учтены предусловие, когда количество элементов достигло максимального
количества

- Учтены постусловия, для добавления/удаления элемента
- есть отличие в реализации: команда pop() в моем решении возвращает
удаленный элемент, а из теории, команда ничего не возвращает;
Из того, что можно улучшить: не хватило дополнительных поясняющих
комментариев к статусам. Не были задокументированы все возможные состояния
для команд. Возможно, стоило добавить разделение на 3 категории методов в виде
комментариев.
*/
