package kane.zomato.service;
import kane.zomato.dto.MenuDto;
import kane.zomato.entity.Hotel;
import kane.zomato.entity.Menu;
import kane.zomato.respository.HotelRepository;
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
    private final HotelRepository hotelRepository;

    @Override
    public MenuDto create(MenuDto menuDto) {
//        Menu menuItem = menuRepository.findByDishName(menuDto.getDishName()).orElse(null);

        boolean exists = menuRepository
                .findByDishNameAndHotelId(
                        menuDto.getDishName(),
                        menuDto.getHotelId()
                )
                .isPresent();

        if (exists) {
            throw new RuntimeException("Menu item already exists for this hotel with the same dish name");
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

        // 🔹 Fetch Hotel entity
        Hotel hotel = hotelRepository.findById(menuDto.getHotelId())
                .orElseThrow(() ->
                        new RuntimeException("Hotel not found with id: " + menuDto.getHotelId())
                );

        // Update only allowed fields
        existingMenu.setDishName(menuDto.getDishName());
        existingMenu.setDepartment(menuDto.getDepartment());
        existingMenu.setPrice(menuDto.getPrice());
        existingMenu.setTax(menuDto.getTax());
        existingMenu.setHotel(hotel);
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
        Menu existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() ->
                        new RuntimeException("No Menu exists with id: " + menuId)
                );

        menuRepository.delete(existingMenu);
    }


}