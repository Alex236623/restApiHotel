package com.hotel.controller;

import com.hotel.domain.Guest;
import com.hotel.domain.Reservation;
import com.hotel.domain.Room;
import com.hotel.dto.GuestDto;
import com.hotel.service.GuestService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void testFindAllGuests() throws Exception {
        LocalDate localDate = LocalDate.now();
        Room room = new Room();
        Reservation reservation = new Reservation(1L, localDate, localDate, room, null);
        List<Long> reservations = List.of(reservation.getId(), reservation.getId());
        GuestDto guest1 = new GuestDto(1L, "Alex", "Homes", "XX999999", reservations);
        GuestDto guest2 = new GuestDto(2L, "Second", "One", "XX999992", reservations);
        List<GuestDto> guests = Arrays.asList(guest1, guest2);

        Mockito.when(guestService.findAll()).thenReturn(guests);

        mockMvc.perform(MockMvcRequestBuilders.get("/guests"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].passportNumber").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].passportNumber").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reservation").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].reservation").exists());
    }

    @Test
    public void testFindGuestById() throws Exception {
        Long guestId = 1L;
        GuestDto guest = new GuestDto();
        guest.setId(guestId);

        Mockito.when(guestService.getGuestById(guestId)).thenReturn(guest);

        mockMvc.perform(MockMvcRequestBuilders.get("/guests/{id}", guestId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(guestId));
    }

    @Test
    public void testFindGuestByFirstName() throws Exception {
        String firstName = "John";
        GuestDto guest = new GuestDto();
        guest.setFirstName(firstName);

        Mockito.when(guestService.getGuestByFirstName(firstName)).thenReturn(guest);

        mockMvc.perform(MockMvcRequestBuilders.get("/guests/firstName/{firstName}", firstName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(firstName));
    }

    @Test
    public void testFindGuestByPassport() throws Exception {
        String passport = "AB123456";
        GuestDto guest = new GuestDto(1L, "Alex", "Homes", "AB123456", null);
        guest.setPassportNumber(passport);

        Mockito.when(guestService.getGuestByPassport(passport)).thenReturn(guest);

        mockMvc.perform(MockMvcRequestBuilders.get("/guests/passport/{passport}", passport))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddGuest() throws Exception {
        GuestDto guestDto = new GuestDto();
        guestDto.setFirstName("John");
        guestDto.setLastName("Doe");

        Guest savedGuest = new Guest();
        savedGuest.setId(1L);
        savedGuest.setFirstName("John");
        savedGuest.setLastName("Doe");

        Mockito.when(guestService.saveGuest(Mockito.any(Guest.class))).thenReturn(savedGuest);

        mockMvc.perform(MockMvcRequestBuilders.post("/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testUpdateGuest() throws Exception {
        Long guestId = 1L;
        GuestDto updatedGuestDto = new GuestDto();
        updatedGuestDto.setFirstName("John");
        updatedGuestDto.setLastName("Doe");

        GuestDto updatedGuest = new GuestDto();
        updatedGuest.setId(guestId);
        updatedGuest.setFirstName("John");
        updatedGuest.setLastName("Doe");

        Mockito.when(guestService.updateGuest(Mockito.eq(guestId), Mockito.any(GuestDto.class))).thenReturn(updatedGuest);

        mockMvc.perform(MockMvcRequestBuilders.put("/guests/{id}", guestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(guestId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testDeleteGuest() throws Exception {
        Long guestId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/guests/{id}", guestId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(guestService, Mockito.times(1)).deleteGuest(guestId);
    }
}