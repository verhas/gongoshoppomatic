package com.epam.vpase.gongo.persistence;

import com.epam.vpase.gongo.core.HasId;

import java.util.List;

/**
 * <p>A very simple persistence manager API.</p>
 *
 * <p>A persistence manager can save, load, remove objects of type {@code T}. It can also list objects that match a
 * certain selector string. The actual implementation can define the interpretation of the selector string. That way
 * implementations may not be compatible. On the other hand the implementations should be able to manage this
 * incompatibility through configuration.</p>
 *
 * @param <T> the type of the object that is persisted
 */
public interface Manager<T extends HasId> {
    /**
     * Save an object into the persistence.
     *
     * @param t the object to be saved.
     */
    void save(T t);

    /**
     * Remove an object from the persistence layer using the identifier of the object.
     *
     * @param id theidentifier of the object
     */
    void remove(String id);

    /**
     * Load an object from the persistence layer.
     *
     * @param id the identifier of the object to be loaded
     * @return the loaded object
     */
    T load(String id);

    /**
     * Get the list of 'id's that match the selector
     *
     * @param selector a string that selects some objects
     * @return the list of the string IDs
     */
    List<String> list(String selector);
}
