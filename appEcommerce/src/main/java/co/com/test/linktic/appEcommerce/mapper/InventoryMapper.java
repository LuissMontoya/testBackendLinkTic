package co.com.test.linktic.appEcommerce.mapper;

import co.com.test.linktic.appEcommerce.DTO.CategoryDTO;
import co.com.test.linktic.appEcommerce.DTO.InventoryDTO;
import co.com.test.linktic.appEcommerce.entity.Category;
import co.com.test.linktic.appEcommerce.entity.Inventory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper 
public interface InventoryMapper {
	
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);
	
    InventoryDTO entityToDTO(Inventory entity);
	
	@InheritInverseConfiguration 
	Inventory dtoToEntity(InventoryDTO inventoryDTO);
	
	List<InventoryDTO> listBeanToListDto(List<Inventory> list);
	
	List<Inventory> listDtoToListEnt(List<InventoryDTO> list);
}
