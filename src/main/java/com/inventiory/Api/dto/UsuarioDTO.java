
package com.inventiory.Api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jonii
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaRegistro;
    private boolean estado;

    
}
