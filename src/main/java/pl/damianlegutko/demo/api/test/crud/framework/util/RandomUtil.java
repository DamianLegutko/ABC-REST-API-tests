package pl.damianlegutko.demo.api.test.crud.framework.util;

import java.math.BigDecimal;
import java.util.Random;

import static java.lang.Math.abs;
import static java.math.BigDecimal.valueOf;
import static java.time.Instant.now;

public class RandomUtil {
    public static final Random RANDOM = new Random(now().toEpochMilli());

    public static BigDecimal randomBigDecimal() {
        return valueOf(RANDOM.nextDouble());
    }

    public static BigDecimal positiveRandomBigDecimal() {
        return valueOf(abs(RANDOM.nextDouble()));
    }

}
