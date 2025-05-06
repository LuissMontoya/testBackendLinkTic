package co.com.test.linktic.appEcommerce.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Integer id;

	@NotBlank(message = "El nombre del producto no puede estar vacío")
	@Size(min = 3, max = 100, message = "El nombre del producto debe tener entre 3 y 100 caracteres")
	private String name;

	@NotBlank(message = "La descripción no puede estar vacía")
	@Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")

	private String description;
	@NotNull(message = "El precio no puede ser nulo")
	@Positive(message = "El precio debe ser mayor que 0")
	private Double price;
	@Min(value = 0, message = "El stock no puede ser menor que 0")
	private Integer stock;
	@NotNull(message = "La categoría es obligatoria")
	private Integer category_id;
}
