package kane.zomato.service;

import kane.zomato.dto.MenuDto;

public interface MenuService {
    MenuDto create(MenuDto menuDto);
    MenuDto update(Long menuId,  MenuDto menuDto);

    MenuDto getMenu(Long menuId);
    void deleteMenuById(Long menuId);

}
