package Other;

import Devices.SmartDevice;

import java.util.function.Predicate;
import java.util.function.Consumer;

public class Rule<T extends SmartDevice> {
    private final Predicate<T> condition;
    private final Consumer<T> action;
    private final T device;

    public Rule(Predicate<T> condition, Consumer<T> action, T device) {
        this.condition = condition;
        this.action = action;
        this.device = device;
    }

    public void evaluateAndExecute() {
        if (condition.test(device)) {
            action.accept(device);
        }
    }
}
