package cl.paris.proveedores.service;

import cl.paris.proveedores.dto.ProveedorRequestDto;
import cl.paris.proveedores.dto.ProveedorResponseDto;
import cl.paris.proveedores.exception.ResourceNotFoundException; // 🟢 Modificación: Import de tu excepción
import cl.paris.proveedores.mapper.ProveedorMapper;
import cl.paris.proveedores.model.Proveedor;
import cl.paris.proveedores.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    // Obtener todos transformados a Dto
    public List<ProveedorResponseDto> listarTodos() {
        return proveedorRepository.findAll()
                .stream()
                .map(proveedor -> proveedorMapper.toResponseDto(proveedor))
                .collect(Collectors.toList());
    }

    // Buscar por ID transformando a Dto
    public ProveedorResponseDto buscarPorId(Long id) { // 🟢 Modificación: Ahora retorna el Dto directo o explota
        return proveedorRepository.findById(id)
                .map(proveedor -> proveedorMapper.toResponseDto(proveedor))
                // Si no existe, lanza la excepción que atrapará el GlobalExceptionHandler
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));
    }

    // Crear un nuevo proveedor
    public ProveedorResponseDto guardar(ProveedorRequestDto dto) {
        Proveedor proveedor = proveedorMapper.toEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        return proveedorMapper.toResponseDto(guardado);
    }

    // Actualizar proveedor existente
    public Optional<ProveedorResponseDto> actualizar(Long id, ProveedorRequestDto dto) {
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

    // Eliminar
    public boolean eliminar(Long id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedorRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}