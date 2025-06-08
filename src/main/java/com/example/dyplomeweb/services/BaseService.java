package com.example.dyplomeweb.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<DTO, ENTITY> {
    List<DTO> getAll();
    Optional<DTO> getById(Integer id);
    DTO save(DTO dto);
    DTO update(Integer id, DTO dto);
    void delete(Integer id);
    DTO convertToDTO(ENTITY entity);
    ENTITY convertToEntity(DTO dto);
}
