package cl.paris.proveedores.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponseDto {
    private UUID id;
    private String rut;
    private String razonSocial;
    private String telefono;
    private String email;
    private String categoria;
}