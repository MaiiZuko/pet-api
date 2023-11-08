package br.com.alura.adopet.api.validacoes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoPetComAdocaoEmAndamentoTest {

    @Mock
    private AdocaoRepository adocaoRepository;
    
    @Mock private SolicitacaoAdocaoDto dto;

    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validador;
    

    @Test
    void nãoPermitirSolicitaçãoDeAdoçãoSePetComPedidoEmAndamento() {

        given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);
        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    void DevePermitirSolicitaçãoDeAdoçãoSePetComPedidoEmAndamento() {

        //Fingindo que foi ao bando de dados mas não encontrou uma solicitação em andamento para esse pet
        given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);
        //Ao chamar o validar verifica se nenhum exception é lançado
        assertDoesNotThrow(() -> validador.validar(dto));
    }
}
