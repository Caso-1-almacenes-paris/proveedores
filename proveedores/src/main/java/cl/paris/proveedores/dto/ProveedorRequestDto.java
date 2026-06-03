package cl.paris.proveedores.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequestDto {

    @NotBlank(message = "El RUT del proveedor es obligatorio")
    @Size(min = 8, max = 12, message = "El RUT debe tener entre 8 y 12 caracteres")
    private String rut;

    @NotBlank(message = "La razón social o nombre de la empresa es obligatoria")
    private String razonSocial;

    private String telefono;

    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    @NotBlank(message = "Debe especificar una categoría de productos")
    private String categoria;
}