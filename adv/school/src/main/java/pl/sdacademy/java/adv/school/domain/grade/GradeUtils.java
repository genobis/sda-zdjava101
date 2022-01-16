package pl.sdacademy.java.adv.school.domain.grade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

public class GradeUtils {
    // Czasem spotykaną praktyką jest rzucanie wyjątku w PRYWATNYM konstruktorze klasy UŻYTKOWEJ.
    // Zapobiega to przypadkowemu stworzeniu nowej instancji nawet przy pomocy użyciu mechanizmu refleksji.
    // NIE należy modyfikować tego konstruktora!
    private GradeUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Wylicza średnią ważoną podanych ocen. Średnia jest wyliczana z dokładnością do <b>dwóch</b> miejsc po przecinku
     * i zaokrąglana w górę w standardowy sposób (sprawdź: {@link RoundingMode#HALF_UP}).
     * @param grades oceny, z których średnią należy wyliczyć.
     * @return {@code Optional} ze średnią ważoną lub <b>pusty</b> gdy:
     * suma wag jest mniejsza lub równa {@code 0} lub suma wyważonych ocen (po wymnożeniu z wagą) jest mniejsza od {@code 1}.
     */
    public static Optional<BigDecimal> gradesAverage(Collection<Grade> grades) {
        /*
        Zadanie to może zostać zrealizowane zarówno przy użyciu tradycyjnej pętli, jak i Stream API (zalecane!)
        Wagę oceny jako wartość BigDecimal można pobrać z grade.getGradeWeight().getWeight()

        Uwaga, do porównywania wartości BigDecimal należy bezwzględnie używać metody compareTo()! Przykład:
        (BigDecimal.ONE.equals(new BigDecimal("1.0"))) ---> false
        (BigDecimal.ONE.compareTo(new BigDecimal("1.0")) == 0) ---> true
        */

        BigDecimal sumOfMuliplyValues = BigDecimal.ZERO;
        BigDecimal sumOfWeights = BigDecimal.ZERO;
        for (Grade grade: grades) {
            BigDecimal multiplyResult = grade.getValue().multiply(grade.getGradeWeight().getWeight());
            sumOfMuliplyValues = multiplyResult.add(sumOfMuliplyValues);
            BigDecimal weight = grade.getGradeWeight().getWeight();
            sumOfWeights = weight.add(sumOfWeights);
        }
        BigDecimal result = (sumOfMuliplyValues.divide(sumOfWeights)).setScale(2,RoundingMode.HALF_UP);
        if ((BigDecimal.ONE.compareTo(result)<0)) {
return Optional.empty();
        }


        throw new UnsupportedOperationException("Zadanie domowe");
        return Optional.of(result);

    }
}
