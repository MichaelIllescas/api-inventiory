
package com.inventiory.Api.repository;

import com.inventiory.Api.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonii
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
