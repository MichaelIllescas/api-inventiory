
package com.inventiory.Api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jonii
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor        
class AuthenticationResponse {
      
    private String token;
}
