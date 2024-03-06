package pl.damianlegutko.demo.api.test.crud.framework.assertion;

import lombok.NonNull;
import org.assertj.core.api.AbstractAssert;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseAssert<SELF extends BaseAssert<SELF, ACTUAL>, ACTUAL> extends AbstractAssert<SELF, ACTUAL> {
    final List<String> recursiveEqualToIgnoredList = new ArrayList<>();

    public BaseAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public BaseAssert addIgnoreField(@NonNull String fieldToIgnore) {
        recursiveEqualToIgnoredList.add(fieldToIgnore);
        return this;
    }

    public BaseAssert addIgnoreFields(@NonNull List<String> fieldsToIgnore) {
        recursiveEqualToIgnoredList.addAll(fieldsToIgnore);
        return this;
    }

    protected String[] convertListToArray(List<String> listToArray) {
        return listToArray.toArray(new String[0]);
    }

    protected String[] recursiveEqualToIgnoredList() {
        return convertListToArray(recursiveEqualToIgnoredList);
    }

    public SELF isRecursiveEqualTo(ACTUAL expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields(recursiveEqualToIgnoredList())
                .isEqualTo(expected);

        return myself;
    }
}
