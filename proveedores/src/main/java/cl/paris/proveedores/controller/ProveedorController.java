package cl.paris.proveedores.controller;

import cl.paris.proveedores.dto.ProveedorRequestDto;
import cl.paris.proveedores.dto.ProveedorResponseDto;
import cl.paris.proveedores.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores") // 🌐 URL base: http://localhost:8083/api/proveedores
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // GET: Listar todos los proveedores
    @GetMapping
    public List<ProveedorResponseDto> obtenerTodos() {
        return proveedorService.listarTodos();
    }

    // GET: Buscar un proveedor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDto> obtenerPorId(@PathVariable Long id) {
        // 🟢 Modificación: Código ultra limpio. Si el ID no existe, el Service
        // interrumpe la ejecución y salta directo al GlobalExceptionHandler.
        ProveedorResponseDto dto = proveedorService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    // POST: Crear un nuevo proveedor (Usa @Valid para activar las reglas del RequestDto)
    @PostMapping
    public ResponseEntity<ProveedorResponseDto> crearProveedor(@Valid @RequestBody ProveedorRequestDto dto) {
        ProveedorResponseDto nuevo = proveedorService.guardar(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // PUT: Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDto> actualizarProveedor(@PathVariable Long id, @Valid @RequestBody ProveedorRequestDto dto) {
        return proveedorService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Borrar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        if (proveedorService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}