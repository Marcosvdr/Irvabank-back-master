package br.com.irvabank.controller;

import br.com.irvabank.dto.ClienteDTO;
import br.com.irvabank.model.ClienteEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cliente", description = "Serviços de controle de clientes")
@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController extends AbstractRestController<ClienteDTO, ClienteEntity> {
}
