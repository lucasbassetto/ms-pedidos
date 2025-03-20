package br.com.techtaste.ms_pedidos.mapper;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.model.ItemPedido;
import br.com.techtaste.ms_pedidos.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PedidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "data", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "status", constant = "AGUARDANDO_PAGAMENTO")
    @Mapping(target = "valorTotal", expression = "java(calcularValorTotal(pedido))")
    Pedido convertDtoToPedido(PedidoRequestDto pedidoRequestDto);

    PedidoResponseDto convertPedidoToDto(Pedido pedido);

    List<PedidoResponseDto> convertListEntityToDto(Iterable<Pedido> pedidos);

    default BigDecimal calcularValorTotal(Pedido pedido) {
        return pedido.getItens().stream()
                .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
