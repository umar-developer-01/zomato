package kane.zomato.service;

import kane.zomato.dto.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto updateHotel(Long hotelId,  HotelDto hotelDto);

    HotelDto getHotelById(Long hotelId);
    void deleteHotelById(Long hotelId);

}
