package br.com.techtaste.ms_pedidos.model;

import br.com.techtaste.ms_pedidos.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cpf;

    private LocalDate data;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens = new ArrayList<>();

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private Status status;

}
