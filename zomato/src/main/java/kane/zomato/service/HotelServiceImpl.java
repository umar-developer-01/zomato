package kane.zomato.service;
import kane.zomato.dto.HotelDto;
import kane.zomato.entity.Hotel;
import kane.zomato.respository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



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
    public HotelDto updateHotel(Long hotelId, HotelDto hotelDto) {

        Hotel existingHotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new RuntimeException("Hotel not found with id: " + hotelId)
                );

        // Update only allowed fields
        existingHotel.setName(hotelDto.getName());
        existingHotel.setOwnerName(hotelDto.getOwnerName());
        existingHotel.setPhoneNumber(hotelDto.getPhoneNumber());

        Hotel updatedHotel = hotelRepository.save(existingHotel);

        return modelMapper.map(updatedHotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long hotelId) {
        Hotel existingHotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new RuntimeException("No Hotel exist: " + hotelId)
                );
        return modelMapper.map(existingHotel, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long hotelId) {
        Hotel existingHotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new RuntimeException("No Hotel exists with id: " + hotelId)
                );

        hotelRepository.delete(existingHotel);
    }


}
