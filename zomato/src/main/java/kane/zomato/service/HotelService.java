package kane.zomato.service;

import kane.zomato.dto.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelId(Long hotelId);
    void deleteHotelById(Long hotelId);
    HotelDto updateHotelById(Long hotelId,  HotelDto hotelDto);
}
