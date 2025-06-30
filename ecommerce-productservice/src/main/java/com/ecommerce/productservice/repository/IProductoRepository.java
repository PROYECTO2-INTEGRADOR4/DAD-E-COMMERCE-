package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Producto;
import com.ecommerce.productservice.dto.ProductoCatalogoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = """
    SELECT 
        p.id AS productoId,
        p.nombre AS nombreProducto,
        MIN(pv.precio) AS precioMinimo,
        (
            SELECT pv2.imagen_url
            FROM producto_variantes pv2
            WHERE pv2.producto_id = p.id
            LIMIT 1
        ) AS imagenPrincipal,
        STRING_AGG(DISTINCT vo_color.valor, ', ') AS coloresDisponibles
    FROM productos p
    JOIN producto_variantes pv ON pv.producto_id = p.id
    JOIN variante_opciones vo_color ON vo_color.productovariante_id = pv.id AND vo_color.opcion_id = 1
    LEFT JOIN variante_opciones vo_talla ON vo_talla.productovariante_id = pv.id AND vo_talla.opcion_id = 2
    WHERE vo_talla.valor IS NULL
    GROUP BY p.id, p.nombre
    """, nativeQuery = true)
    List<Object[]> obtenerCatalogoConColoresYPrecioMinimo();
}
