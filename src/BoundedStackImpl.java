import java.util.ArrayList;
import java.util.List;

abstract class BoundedStack<T> {
    static final int DEFAULT_CAPACITY = 32;
    static final int INIT_OK = 0;
    static final int INIT_INVALID_CAPACITY = 1;
    static final int PUSH_OK = 0;
    static final int PUSH_FILLED = 1;
    static final int PUSH_NULL = 2;
    static final int PEEK_OK = 0;
    static final int PEEK_EMPTY_STACK = 1;
    static final int PEEK_NULL = 2;
    static final int POP_OK = 0;
    static final int POP_EMPTY_STACK = 1;
    static final int POP_NULL = 2;

    protected int initStatus = INIT_INVALID_CAPACITY;
    protected int pushStatus = PUSH_NULL;
    protected int peekStatus = PEEK_NULL;
    protected int popStatus = POP_NULL;
    protected int capacity;

    abstract void push(final T value);

    abstract T peek();

    abstract T pop();

    abstract void clear();

    abstract public int size();
}

public class BoundedStackImpl<T> extends BoundedStack<T> {

    private List<T> stack;

    public BoundedStackImpl() {
        this(DEFAULT_CAPACITY);
    }

    public BoundedStackImpl(final int capacity) {
        this.capacity = capacity;
        if (capacity >= 1) {
            stack = new ArrayList<>(capacity);
            initStatus = INIT_OK;
            return;
        }
        initStatus = INIT_INVALID_CAPACITY;
    }

    @Override
    public void push(final T value) {
        if (stack.size() >= capacity || initStatus == INIT_INVALID_CAPACITY) {
            pushStatus = PUSH_FILLED;
            return;
        }
        stack.add(value);
        pushStatus = PUSH_OK;
    }

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

    @Override
    public void clear() {
        if (capacity >= 1) {
            stack = new ArrayList<>(capacity);
            initStatus = INIT_OK;
        }
        pushStatus = PUSH_NULL;
        peekStatus = PEEK_NULL;
        popStatus = POP_NULL;
    }

    @Override
    public int size() {
        return stack.size();
    }

    public int getInitStatus() {
        return initStatus;
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
