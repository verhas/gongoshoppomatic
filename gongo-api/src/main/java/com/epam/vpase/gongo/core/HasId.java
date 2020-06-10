package com.epam.vpase.gongo.core;

/**
 * <p>When an object has an id (identifier) then the id eventually identifies the object. Also the id has to be unique
 * within a certain realm. The abstraction level is simplified saying that the id can be represented as a string and
 * essentially that way an id is a string.</p>
 *
 * <p>When a class represents objects that are stored in some persistence then the id can be used to store, search and
 * retrieve the objects, and they have to implement this interface.</p>
 */
public interface HasId {
    String getId();
}
