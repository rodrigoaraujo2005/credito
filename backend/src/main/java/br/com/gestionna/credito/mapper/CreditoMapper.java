package br.com.gestionna.credito.mapper;

import br.com.gestionna.credito.dto.CreditoDTO;
import br.com.gestionna.credito.entity.Credito;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CreditoMapper {

    public CreditoDTO toDTO(Credito entity) {
        if (entity == null) {
            return null;
        }

        return new CreditoDTO(
                entity.getId(),
                entity.getNumeroCredito(),
                entity.getNumeroNfse(),
                entity.getDataConstituicao(),
                entity.getValorIssqn(),
                entity.getTipoCredito(),
                entity.getSimplesNacional(),
                entity.getAliquota(),
                entity.getValorFaturado(),
                entity.getValorDeducao(),
                entity.getBaseCalculo()
        );
    }

    public Credito toEntity(CreditoDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Credito(
                dto.getId(),
                dto.getNumeroCredito(),
                dto.getNumeroNfse(),
                dto.getDataConstituicao(),
                dto.getValorIssqn(),
                dto.getTipoCredito(),
                dto.getSimplesNacional(),
                dto.getAliquota(),
                dto.getValorFaturado(),
                dto.getValorDeducao(),
                dto.getBaseCalculo()
        );
    }

    public List<CreditoDTO> toDTOList(List<Credito> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Credito> toEntityList(List<CreditoDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
