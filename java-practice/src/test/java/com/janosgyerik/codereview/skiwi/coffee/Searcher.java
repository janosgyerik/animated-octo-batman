package com.janosgyerik.codereview.skiwi.coffee;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Searcher<K, T> {
    private final Map<K, T> cache;

    public Searcher(final Function<? super T, ? extends K> keyExtractor, final Collection<? extends T> items) {
        this.cache = ((Collection<T>)items).stream()
                .collect(Collectors.toMap(keyExtractor, item -> item));
    }

    public final T search(final K key) {
        T item = cache.get(key);
        if (item == null) {
            throw new IllegalArgumentException("No item has been found for key " + key);
        }
        return item;
    }
}
