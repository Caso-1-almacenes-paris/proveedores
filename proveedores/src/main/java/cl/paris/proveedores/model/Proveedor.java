package cl.paris.proveedores.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false) 
    private UUID id;

    @Column(unique = true, nullable = false)
    private String rut;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    private String telefono;

    private String email;

    @Column(nullable = false)
    private String categoria;
}