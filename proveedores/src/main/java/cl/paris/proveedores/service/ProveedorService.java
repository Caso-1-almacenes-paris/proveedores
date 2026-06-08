package cl.paris.proveedores.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.paris.proveedores.dto.ProveedorRequestDto;
import cl.paris.proveedores.dto.ProveedorResponseDto;
import cl.paris.proveedores.exception.ResourceNotFoundException; 
import cl.paris.proveedores.mapper.ProveedorMapper;
import cl.paris.proveedores.model.Proveedor; 
import cl.paris.proveedores.repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    public List<ProveedorResponseDto> listarTodos() {
        return proveedorRepository.findAll()
                .stream()
                .map(proveedor -> proveedorMapper.toResponseDto(proveedor))
                .collect(Collectors.toList());
    }

    public ProveedorResponseDto buscarPorId(UUID id) { 
        return proveedorRepository.findById(id)
                .map(proveedor -> proveedorMapper.toResponseDto(proveedor))
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));
    }

    public ProveedorResponseDto guardar(ProveedorRequestDto dto) {
        Proveedor proveedor = proveedorMapper.toEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        return proveedorMapper.toResponseDto(guardado);
    }

    public Optional<ProveedorResponseDto> actualizar(UUID id, ProveedorRequestDto dto) {
        return proveedorRepository.findById(id)
                .map(proveedorExistente -> {
                    proveedorExistente.setRut(dto.getRut());
                    proveedorExistente.setRazonSocial(dto.getRazonSocial());
                    proveedorExistente.setTelefono(dto.getTelefono());
                    proveedorExistente.setEmail(dto.getEmail());
                    proveedorExistente.setCategoria(dto.getCategoria());
                    Proveedor actualizado = proveedorRepository.save(proveedorExistente);
                    return proveedorMapper.toResponseDto(actualizado);
                });
    }

    public boolean eliminar(UUID id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedorRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}