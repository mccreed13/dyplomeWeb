package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.CategoryDTO;
import com.example.dyplomeweb.dto.UnitsDTO;
import com.example.dyplomeweb.entity.Category;
import com.example.dyplomeweb.entity.Units;
import com.example.dyplomeweb.repository.UnitsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitsService implements BaseService<UnitsDTO, Units> {
    private UnitsRepository unitsRepository;
    @Override
    public List<UnitsDTO> getAll() {
        return unitsRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Map<Integer, UnitsDTO> getAllInMap(){
        Map<Integer, UnitsDTO> unitsDTOMap = new HashMap<>();
        unitsRepository.findAll()
                .forEach(unitsDTO -> unitsDTOMap.put(unitsDTO.getId(), convertToDTO(unitsDTO)));
        return unitsDTOMap;
    }

    @Override
    public Optional<UnitsDTO> getById(Integer id) {
        return unitsRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public UnitsDTO save(UnitsDTO unitsDTO) {
        return null;
    }

    @Override
    public UnitsDTO update(Integer id, UnitsDTO unitsDTO) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public UnitsDTO convertToDTO(Units units) {
        return UnitsDTO.builder()
                .id(units.getId())
                .name(units.getName())
                .build();
    }

    @Override
    public Units convertToEntity(UnitsDTO unitsDTO) {
        Units units = new Units();
        units.setName(unitsDTO.name());
        return units;
    }
}
