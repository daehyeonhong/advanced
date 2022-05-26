package hello.advanced.app.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {
    public static void sleep(final int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
