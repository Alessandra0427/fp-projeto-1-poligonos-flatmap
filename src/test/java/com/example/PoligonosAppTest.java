package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe {@link PoligonosApp},
 * que verifica se os métodos da classe estão funcionando corretamente.
 * Você não deve alterar este arquivo.
 */
class PoligonosAppTest {
    private final PoligonosApp app = new PoligonosApp();

    @Test
    void perimetros() {
        // Diferença máxima aceitável (para mais ou para menos) entre o valor esperado e o valor retornado
        final double delta = 1.0;
        final List<Double> expected = List.of(400.0, 500.0, 323.0, 341.0, 382.0);
        final List<Double> perimetros = app.perimetros();
        assertThat(perimetros).isNotEmpty();

        final var msg = "Perímetro do polígono %d incorreto";
        final Consumer<Integer> tester = i -> assertEquals(expected.get(i), perimetros.get(i), delta, msg.formatted(i));
        final Stream<Executable> executables= range(0, perimetros.size()).mapToObj(i -> () -> tester.accept(i));
        assertAll(executables);
    }

    @Test
    void tipoPoligonos() {
        final var resultList = app.tipoPoligonos().stream().map(String::toLowerCase).map(String::trim).toList();
        assertThat(resultList).isEqualTo(List.of("quadrilátero", "quadrilátero", "triângulo", "pentágono", "hexágono"));
    }

    @Test
    void perimetrosRetornaListaImutavel() {
        final var perimetros = app.perimetros();
        assertThrows(UnsupportedOperationException.class, () -> perimetros.add(0.0), "A lista retornada não deveria permitir modificações");
    }

    @Test
    void tipoPoligonosRetornaListaImutavel() {
        final var tipoPoligonos = app.tipoPoligonos();
        assertThrows(UnsupportedOperationException.class, () -> tipoPoligonos.add(""), "A lista retornada não deveria permitir modificações");
    }

}
