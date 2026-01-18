package kane.zomato.service;
import kane.zomato.dto.HotelDto;
import kane.zomato.dto.UserDto;
import kane.zomato.entity.Hotel;
import kane.zomato.entity.User;
import kane.zomato.enums.Role;
import kane.zomato.respository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements  HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findByName(hotelDto.getName()).orElse(null);

        if (hotel != null) {
            throw new RuntimeException("Hotel is already present with same name");
        }

        Hotel newHotel = modelMapper.map(hotelDto, Hotel.class);

        newHotel = hotelRepository.save(newHotel);

        return modelMapper.map(newHotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelId(Long hotelId) {
        return null;
    }

    @Override
    public void deleteHotelById(Long hotelId) {

    }

    @Override
    public HotelDto updateHotelById(Long hotelId, HotelDto hotelDto) {
        return null;
    }
}
