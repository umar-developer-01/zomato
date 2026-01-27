package kane.zomato.service;
import kane.zomato.dto.MenuDto;
import kane.zomato.entity.Hotel;
import kane.zomato.entity.Menu;
import kane.zomato.respository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements  MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @Override
    public MenuDto create(MenuDto menuDto) {
        Menu menuItem = menuRepository.findByDishName(menuDto.getDishName()).orElse(null);

        if (menuItem != null) {
            throw new RuntimeException("Menu Iteam is already present with same name");
        }

        Menu newMenu = modelMapper.map(menuDto, Menu.class);

        newMenu = menuRepository.save(newMenu);

        return modelMapper.map(newMenu, MenuDto.class);
    }

    @Override
    public MenuDto update(Long menuId, MenuDto menuDto) {

        Menu existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() ->
                        new RuntimeException("Menu not found with id: " + menuId)
                );

        // Update only allowed fields
        existingMenu.setDishName(menuDto.getDishName());
        existingMenu.setDepartment(menuDto.getDepartment());
        existingMenu.setPrice(menuDto.getPrice());
        existingMenu.setTax(menuDto.getTax());
        existingMenu.setHotelId(menuDto.getHotelId());
        existingMenu.setAvailable(menuDto.getAvailable());

        Menu updatedMenu = menuRepository.save(existingMenu);

        return modelMapper.map(updatedMenu, MenuDto.class);
    }

    @Override
    public MenuDto getMenu(Long menuId) {
        Menu existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() ->
                        new RuntimeException("No Menu exist: " + menuId)
                );
        return modelMapper.map(existingMenu, MenuDto.class);
    }

    @Override
    public void deleteMenuById(Long menuId) {
        Hotel existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() ->
                        new RuntimeException("No Menu exists with id: " + menuId)
                );

        menuRepository.delete(existingMenu);
    }


}