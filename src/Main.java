import java.util.Map;

public class Main {
    private static final Map<Integer, String> humanStatuses = Map.of(
            0, "OK",
            1, "FAILURE",
            2, "NULL"
    );

    public static void main(String[] args) {
        final BoundedStackImpl<Integer> stack = new BoundedStackImpl<>(10);

        printStatuses(stack);
        stack.peek();

        for (int i = 0; i < 35; i++) {
            stack.push(i);
            System.out.println("adding index " + i + ": operation" + humanStatuses.get(stack.getPushStatus()));
        }

        int lastValue = stack.pop();

        System.out.println("\nLastValue = " + lastValue);

        lastValue = stack.peek();

        System.out.println("\nLastValue = " + lastValue);

        lastValue = stack.pop();

        System.out.println("\nLastValue = " + lastValue);

//        printStatuses(stack);
    }

    public static void printStatuses(final BoundedStackImpl stack) {
        System.out.println("\ninit: " + humanStatuses.get(stack.getInitStatus()) +
                "\npeek = " + humanStatuses.get(stack.getPeekStatus()) +
                "\npush = " + humanStatuses.get(stack.getPushStatus()) +
                "\npop = " + humanStatuses.get(stack.getPopStatus())
        );
    }
}