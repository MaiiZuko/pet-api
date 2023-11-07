package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;

public class CalculadoraProbabilidadeAdocaoTest {
    @Test
    void retornaAlta() {
        //idade 4 anos e 4kg - ALTA

        //primeiramente instaciar um objeto pet

        //ARRANGE
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo xpto",
                "61977777777",
                "abrigoxpto@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Simaes", 4, "Cinza", 4.0f), abrigo);

        //Fazemos um objeto da calculadora
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();

        //ACT ação que estamos realizando
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        //ASSERT - verificação
        //Verificar o que a gente espera da onde
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA,probabilidade);
    }

    @Test
    void retornaMedia() {
        //idade 15 anos e 4kg - MEDIA

        //primeiramente instaciar um objeto pet

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo xpto",
                "61977777777",
                "abrigoxpto@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Simaes", 15, "Cinza", 4.0f), abrigo);

        //Fazemos um objeto da calculadora
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        //Verificar o que a gente espera da onde
        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA,probabilidade);
    }
    @Test
    void retornaBaixa() {
        //idade 15 anos e 15kg - ALTA

        //primeiramente instaciar um objeto pet

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo xpto",
                "61977777777",
                "abrigoxpto@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Simaes", 15, "Cinza", 15.0f), abrigo);

        //Fazemos um objeto da calculadora
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        //Verificar o que a gente espera da onde
        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA,probabilidade);
    }
}
