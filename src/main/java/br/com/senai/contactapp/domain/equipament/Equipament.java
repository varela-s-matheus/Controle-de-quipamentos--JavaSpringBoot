package br.com.senai.contactapp.domain.equipament;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Equipament {

    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    private String tipo;
    private String codigoBarras;
    private String descricao;
}
