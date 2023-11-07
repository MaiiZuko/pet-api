package br.com.alura.adopet.api.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;

@ExtendWith(MockitoExtension.class) //Você vai rodar essa classe de teste mas eu quero adicionar uma extensão - mockito vai injetar automaticamente
public class ValidacaoPetDisponivelTest {

    @InjectMocks //Algumas classes vão ser mocks atributo validacao
    private ValidacaoPetDisponivel validacao;
    @Mock //Classe vai ser um mock
    private PetRepository petRepository;

    //finji que vc criou um objeto pet
    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void deveriaPermitirSolicitaçãoDeAdoçãoPet(){
        //ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(7l, 2l , "Motivo qualquer");

        //Dado um cenario "petRepository" método que ele chama lá - pode chamar um id
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);
        //ASSERT + ACT verifica se essa exceção foi lançada ou não
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

     @Test
    void nãoDeveriaPermitirSolicitaçãoDeAdoçãoPet(){
        //ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(7l, 2l , "Motivo qualquer");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);
        //Se certificar que uma excessão foi lançada
        Assertions.assertThrows(ValidacaoException.class,() -> validacao.validar(dto));
    }

}

