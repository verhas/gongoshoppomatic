package com.epam.vpase.gongo.persistence.file;

import com.epam.vpase.gongo.core.HasId;
import com.epam.vpase.gongo.persistence.Manager;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>A simple persistence manager for mock purposes that saves the objects into YAML files. The identifier of an
 * object is the relative path to the file under the constructor parameter defined rootDirectory.</p>
 *
 * @param <T>
 */
public class ManagerImpl<T extends HasId> implements Manager<T> {
    private static final String EXTENSION = ".yaml";
    private static final int EXTENSION_LEN = EXTENSION.length();

    final String rootDirectory;
    final Yaml yaml = new Yaml();

    public ManagerImpl(String rootDirectory) {
        if (rootDirectory.endsWith("/")) {
            this.rootDirectory = rootDirectory;
        } else {
            this.rootDirectory = rootDirectory + "/";
        }
    }

    private String calcFileName(String id) {
        return (rootDirectory + "/" + id).replaceAll("/+", "/") + EXTENSION;
    }

    @Override
    public void save(T acl) {
        final var fileName = calcFileName(acl.getId());
        final var file = new File(fileName);
        file.getParentFile().mkdirs();
        try (final OutputStream os = new FileOutputStream(new File(fileName))) {
            os.write(yaml.dump(acl).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(String id) {
        new File(calcFileName(id)).delete();
    }

    @Override
    public T load(String id) {
        final File persFile = new File(calcFileName(id));
        try (final InputStream is = new FileInputStream(persFile)) {
            return yaml.load(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> list(String selector) {
        final Predicate<String> predicate = (String s) -> s.startsWith(selector);
        final var prefixLen = rootDirectory.length();
        try {
            return Files.find(Path.of(rootDirectory),
                Integer.MAX_VALUE,
                (Path filePath, BasicFileAttributes fileAttr) -> fileAttr.isRegularFile())
                .map(Path::toString)
                .map(s -> s.substring(prefixLen))
                .filter(s -> s.endsWith(EXTENSION))
                .map(s -> s.substring(0, s.length() - EXTENSION_LEN))
                .filter(predicate::test)
                .collect(Collectors.toList());
        } catch (IOException e) {
            return List.of();
        }
    }
}
