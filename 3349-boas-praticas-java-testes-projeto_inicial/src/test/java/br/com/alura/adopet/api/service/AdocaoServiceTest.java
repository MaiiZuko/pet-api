package br.com.alura.adopet.api.service;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
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

    @Mock
    private List<ValidacaoSolicitacaoAdocao> validacoes;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Mock 
    private SolicitacaoAdocaoDto dto;

    @Test
    void deveriaSalvarAdocaoAoSolicitar() {
        //ARRANGE

        //ACT
        service.solicitar(dto);

        //ASSERT
        //Mockito verifica se o petRepository teve o método save chamado com qualquer parametro
        then(petRepository).should().save(any());
    }
}
