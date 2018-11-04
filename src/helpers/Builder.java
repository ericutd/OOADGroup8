package helpers;


import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
/**
 * @author sivagurunathanvelayutham
 * Helper class to create object using Builder Pattern
 */
public class Builder<T> {

    private final Supplier<T> objectInstance;

    private List<Consumer<T>> fieldModifier = new LinkedList<>();

    private Builder(Supplier<T> objectInstance) {
        this.objectInstance = objectInstance;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    public <V> Builder<T> with(BiConsumer<T, V> consumer, V fieldValue) {
        Consumer<T> created = object -> consumer.accept(object, fieldValue);
        fieldModifier.add(created);
        return this;
    }

    public T build() {
        T newObject = objectInstance.get();
        fieldModifier.forEach(field -> field.accept(newObject));
        fieldModifier.clear(); // safety, clean up the list
        return newObject;
    }
}


