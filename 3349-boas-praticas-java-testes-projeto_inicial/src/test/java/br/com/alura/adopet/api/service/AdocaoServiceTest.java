package br.com.alura.adopet.api.service;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;

@ExtendWith(MockitoExtension.class)
public class AdocaoServiceTest {

    @InjectMocks //Pro mockito injetar todos esse métodos automaticamente
    private AdocaoService service;

    @Mock
    private AdocaoRepository repository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    //duble que consigo fazer combinações
    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    @Mock
    private ValidacaoSolicitacaoAdocao validador1;

    @Mock
    private ValidacaoSolicitacaoAdocao validador2;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Mock 
    private SolicitacaoAdocaoDto dto;

    //Capturar um argumento que é passado pelo mock
    @Captor
    private ArgumentCaptor<Adocao> adocaCaptor;

    @Test
    void deveriaSalvarAdocaoAoSolicitar() {
        //ARRANGE
        this.dto = new SolicitacaoAdocaoDto(10l, 20l, "motivo qualquer"); //dto pra fazer a solicitação
        
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);//Quando chama lá o dto o mockito tem q devolver pet
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);
        
        //ACT
        service.solicitar(dto);

        //ASSERT
        //Mockito verifica se o petRepository teve o método save chamado com qualquer parametro - captura pra mim esse objeto passado
        then(repository).should().save(adocaCaptor.capture());
        Adocao adocaoSalva = adocaCaptor.getValue(); //Vai pegar o objeto que foi passado como parametro pro mock do repository
        Assertions.assertEquals(pet, adocaoSalva.getPet());// Se o meu pet é exatamente esse pet que ta dentro dessa adocaoSalva
        Assertions.assertEquals(tutor, adocaoSalva.getTutor());
        Assertions.assertEquals(dto.motivo(), adocaoSalva.getMotivo());
    }

    @Test
    void deveriaChamarValidadoresDeAdocaoAoSolicitar() {
        //ARRANGE
        this.dto = new SolicitacaoAdocaoDto(10l, 20l, "motivo qualquer"); //dto pra fazer a solicitação
        
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);//Quando chama lá o dto o mockito tem q devolver pet
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);
        validacoes.add(validador1);
        validacoes.add(validador2);
        
        //ACT
        service.solicitar(dto);

        //ASSERT
        //Mockito ve se os 2 validadores tiveres validar passando como parametro no dto
        BDDMockito.then(validador1).should().validar(dto);
        BDDMockito.then(validador2).should().validar(dto);
    }
}
