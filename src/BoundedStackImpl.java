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
