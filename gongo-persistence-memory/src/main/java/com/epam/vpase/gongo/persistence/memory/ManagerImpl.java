package com.epam.vpase.gongo.persistence.memory;

import com.epam.vpase.gongo.core.HasId;
import com.epam.vpase.gongo.persistence.Manager;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagerImpl<T extends HasId> implements Manager<T> {

    private Map<String, T> storage = new HashMap<>();

    public Map<String, T> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, T> storage) {
        this.storage = storage;
    }

    @Override
    public void save(T acl) {
        storage.put(acl.getId(), acl);
    }

    @Override
    public void remove(String id) {
        storage.remove(id);
    }

    @Override
    public T load(String id) {
        return storage.get(id);
    }

    @Override
    public List<String> list(final String selector) {
        final Predicate<String> predicate = (String s) -> s.startsWith(selector);
        return storage.entrySet().stream().filter(e -> predicate.test(e.getKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    public void load(InputStream is) {
        final var yaml = new Yaml();
        storage = yaml.load(is);
    }

    public void dump(OutputStream os) throws IOException {
        final var yaml = new Yaml();
        final var dump = yaml.dump(storage);
        os.write(dump.getBytes(StandardCharsets.UTF_8));
    }
}
