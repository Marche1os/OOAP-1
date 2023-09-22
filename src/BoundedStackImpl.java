import java.util.ArrayList;
import java.util.List;

public class BoundedStackImpl<T> {
    private static final int DEFAULT_CAPACITY = 32;

    private List<T> stack;
    private int initStatus = INIT_INVALID_ARGUMENT;
    private int pushStatus = PUSH_NULL;
    private int peekStatus = PEEK_NULL;
    private int popStatus = POP_NULL;
    private final int capacity;

    public static final int INIT_OK = 0;
    public static final int INIT_INVALID_ARGUMENT = 1;
    public static final int PUSH_OK = 0;
    public static final int PUSH_FILLED = 1;
    public static final int PUSH_NULL = 2;
    public static final int PEEK_OK = 0;
    public static final int PEEK_EMPTY_STACK = 1;
    public static final int PEEK_NULL = 2;
    public static final int POP_OK = 0;
    public static final int POP_EMPTY_STACK = 1;
    public static final int POP_NULL = 2;

    public BoundedStackImpl() {
        this(DEFAULT_CAPACITY);
    }

    public BoundedStackImpl(final int capacity) {
        this.capacity = capacity;
        if (capacity >= 1) {
            stack = new ArrayList<>(capacity);
            initStatus = INIT_OK;
        }
    }

    public void push(final T value) {
        if (stack.size() >= capacity) {
            pushStatus = PUSH_FILLED;
            return;
        }
        stack.add(value);
        pushStatus = PUSH_OK;
    }

    public T peek() {
        if (stack.isEmpty()) {
            peekStatus = PEEK_EMPTY_STACK;
            return null;
        }

        final T value = stack.get(stack.size() - 1);
        peekStatus = PEEK_OK;

        return value;
    }

    public T pop() {
        if (stack.isEmpty()) {
            popStatus = POP_EMPTY_STACK;
            return null;
        }

        final T value = stack.remove(stack.size() - 1);
        popStatus = POP_OK;

        return value;
    }

    public void clear() {
        stack = new ArrayList<>(capacity);
        initStatus = INIT_OK;
        pushStatus = PUSH_NULL;
        peekStatus = PEEK_NULL;
        popStatus = POP_NULL;
    }

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
