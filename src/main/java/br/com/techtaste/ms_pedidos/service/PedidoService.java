package br.com.techtaste.ms_pedidos.service;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.mapper.PedidoMapper;
import br.com.techtaste.ms_pedidos.model.ItemPedido;
import br.com.techtaste.ms_pedidos.model.Pedido;
import br.com.techtaste.ms_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoMapper mapper;

    public PedidoResponseDto cadastrarPedido(PedidoRequestDto pedidoDto) {

        Pedido pedido = mapper.convertDtoToPedido(pedidoDto);

        if(pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
            }
        }

        Pedido save = repository.save(pedido);

        return mapper.convertPedidoToDto(save);
    }

    public List<PedidoResponseDto> obterPedidos() {
        return mapper.convertListEntityToDto(repository.findAll());
    }
}
