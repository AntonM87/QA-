package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> providerCheckingMoney(){
        return Stream.of(
                Arguments.of("Открытие", "Банк Открытие: кредит наличными, ипотека, кредитные и ...", "USD")
        );
    }
}
