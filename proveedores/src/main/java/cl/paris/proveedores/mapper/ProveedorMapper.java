package cl.paris.proveedores.mapper;

import org.springframework.stereotype.Component;

import cl.paris.proveedores.dto.ProveedorRequestDto;
import cl.paris.proveedores.dto.ProveedorResponseDto;
import cl.paris.proveedores.model.Proveedor;

@Component
public class ProveedorMapper {

    public Proveedor toEntity(ProveedorRequestDto dto) {
        if (dto == null) return null;
        
        Proveedor proveedor = new Proveedor();
        proveedor.setRut(dto.getRut());
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setEmail(dto.getEmail());
        proveedor.setCategoria(dto.getCategoria());
        return proveedor;
    }

    public ProveedorResponseDto toResponseDto(Proveedor entidad) {
        if (entidad == null) return null;

        ProveedorResponseDto dto = new ProveedorResponseDto();
        dto.setId(entidad.getId());
        dto.setRut(entidad.getRut());
        dto.setRazonSocial(entidad.getRazonSocial());
        dto.setTelefono(entidad.getTelefono());
        dto.setEmail(entidad.getEmail());
        dto.setCategoria(entidad.getCategoria());
        return dto;
    }
}