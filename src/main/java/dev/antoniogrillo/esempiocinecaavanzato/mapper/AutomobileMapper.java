package dev.antoniogrillo.esempiocinecaavanzato.mapper;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutomobileMapper {

    public AutomobileDTO toAutomobileDTO(Automobile automobile){
        String marca=automobile.getMarca()==null?"":automobile.getMarca().getNome();
        long idMarca=automobile.getMarca()==null?0:automobile.getMarca().getId();
        return new AutomobileDTO(automobile.getId(),
                automobile.getModello(),
                automobile.getColore(),
                automobile.getAnnoImmatricolazione(),
                marca,
                idMarca);
    }

    public List<AutomobileDTO> toAutomobileDTO(List<Automobile> automobili){
        return automobili==null?new ArrayList<>():automobili.stream()
                                                            .map(this::toAutomobileDTO)
                                                            .toList();
    }
}
