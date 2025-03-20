package br.com.techtaste.ms_pedidos.controller;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> criar(@RequestBody PedidoRequestDto requestDto) {
        PedidoResponseDto responseDto = service.cadastrarPedido(requestDto);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(responseDto.id())
                        .toUri())
                        .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> obterPedidos() {
        return ResponseEntity.ok(service.obterPedidos());
    }
}
