package br.com.irvabank.controller;

import br.com.irvabank.dto.TransferenciaDTO;
import br.com.irvabank.model.TransferenciaEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transferência", description = "Serviços de controle de transferências bancárias")
@RestController
@RequestMapping("/api/v1/transferencia")
public class TransferenciaController extends AbstractRestController<TransferenciaDTO, TransferenciaEntity> {
}
