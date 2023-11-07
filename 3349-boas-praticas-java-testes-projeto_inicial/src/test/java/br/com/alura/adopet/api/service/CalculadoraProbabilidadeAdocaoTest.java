package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Test;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;

public class CalculadoraProbabilidadeAdocaoTest {
    @Test
    void cenario1() {
        //idade 4 anos e 4kg - ALTA

        //primeiramente instaciar um objeto pet

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo xpto",
                "61977777777",
                "abrigoxpto@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Simaes", 4, "Cinza", 4.0f), abrigo);

        //Fazemos um objeto da calculadora
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);
    }
}
