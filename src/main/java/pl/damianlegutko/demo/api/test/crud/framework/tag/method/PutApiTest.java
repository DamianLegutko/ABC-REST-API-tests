package pl.damianlegutko.demo.api.test.crud.framework.tag.method;

import org.junit.jupiter.api.Tag;
import pl.damianlegutko.demo.api.test.crud.framework.tag.level.ApiTest;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ApiTest
@Tag("Put")
public @interface PutApiTest {
}
