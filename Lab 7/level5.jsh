import java.util.*;
import java.util.Optional;

public Log<Integer> sumTemp(int n, int num, String messages) {
    if (n == 0) {
        messages = "hit base case!\n" + messages;
        return new Log<>(num, Optional.of(messages));
    } else {
        messages = "adding " + n + "\n" + messages;
        return sumTemp(n - 1, num + n, messages);
    }
}

public Log<Integer> sum(int n) {
    return sumTemp(n, 0, "");
}

public Log<Integer> f(int n) {
    String messages = "";
    while (n != 1) {
        if (n % 2 == 0) {
            int value = n / 2;
            messages += String.format("\n%d / 2", n);
            n = value;
        } else {
            int value = 3 * n + 1;
            messages += String.format("\n3(%d) + 1", n);
            n = value;
        }
    }
    messages += "\n1";
    return new Log<>(1, Optional.of(messages));
}


