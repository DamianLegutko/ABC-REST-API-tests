package pl.damianlegutko.demo.api.test.crud.framework.tag.level;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Tag("Api")
public @interface ApiTest {
}
