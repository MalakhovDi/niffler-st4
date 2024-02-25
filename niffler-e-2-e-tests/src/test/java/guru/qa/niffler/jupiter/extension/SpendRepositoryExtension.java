package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.db.repository.SpendRepository;
import guru.qa.niffler.db.repository.SpendRepositoryHibernate;
import guru.qa.niffler.db.repository.SpendRepositoryJdbc;
import guru.qa.niffler.db.repository.SpendRepositorySJdbc;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;

public class SpendRepositoryExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        for (Field field : o.getClass().getDeclaredFields()) {
            if (field.getType().isAssignableFrom(SpendRepository.class)) {
                String repositoryEnv = System.getProperty("repository", "sjdbc");
                field.setAccessible(true);
                SpendRepository repository = null;

                switch (repositoryEnv) {
                    case "jdbc" -> repository = new SpendRepositoryJdbc();
                    case "sjdbc" -> repository = new SpendRepositorySJdbc();
                    case "hibernate" -> repository = new SpendRepositoryHibernate();
                    default ->
                            throw new IllegalArgumentException("Unsupported repository environment: " + repositoryEnv);
                }

                field.set(o, repository);
            }
        }
    }
}
