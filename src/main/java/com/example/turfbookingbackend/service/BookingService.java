package com.example.turfbookingbackend.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turfbookingbackend.model.Booking;
import com.example.turfbookingbackend.model.BookingDetailResponse;
import com.example.turfbookingbackend.model.Turf;
import com.example.turfbookingbackend.model.User;
import com.example.turfbookingbackend.repository.BookingRepository;
import com.example.turfbookingbackend.repository.TurfRepo;
import com.example.turfbookingbackend.repository.UserRepo;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private TurfRepo turfRepository;

    public List<Booking> getAllBooking()
    {
        return bookingRepository.findAll();
    }

    public List<Long> getBookingIdsByUserId(Long userId) {
        return bookingRepository.findBookingIdsByUserId(userId);
    }

    public List<Long> getBookingIdsByTurfId(Long turfId) {
        return bookingRepository.findBookingIdsByTurfId(turfId);
    }

    public BookingDetailResponse getBookingDetails(Long bookingId) {
        
            Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        
        User user = userRepository.findById(booking.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        
        Turf turf = turfRepository.findById(booking.getTurfId())
            .orElseThrow(() -> new RuntimeException("Turf not found"));

        
        BookingDetailResponse response = new BookingDetailResponse();

        
        response.setBookingId(booking.getBookingId());
        response.setUserName(user.getName());
        response.setUserEmail(user.getEmail());
        response.setUserPhone(user.getPhone());
        response.setTurfName(turf.getName());
        response.setTurfAddress(turf.getAddress());
        response.setTurfLocation(turf.getLocation());
        response.setTurfPhone(turf.getPhone());
        response.setTurfEmail(turf.getEmail());
        response.setDate(booking.getDate());
        response.setFromTime(booking.getFromTime());
        response.setToTime(booking.getToTime());
        response.setPaymentOption(booking.getPaymentOption());
        response.setTotalPrice(booking.getTotalPrice());
        response.setPaymentId(booking.getPaymentId());

        
        return response;
    }


    public List<Booking> getBookingsForTurf(Long turfId, String date) {
        return bookingRepository.findByTurfIdAndDate(turfId, date);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long bookingId,Booking booking)
    {
        Booking avail = bookingRepository.findById(bookingId).orElse(null);
        if(avail!=null)
        {
            avail.setTurfId(booking.getTurfId());
            avail.setUserId(booking.getUserId());
            avail.setDate(booking.getDate());
            avail.setFromTime(booking.getFromTime());
            avail.setToTime(booking.getToTime());
            avail.setPaymentOption(booking.getPaymentOption());
            avail.setTotalPrice(booking.getTotalPrice());
            avail.setPaymentId(booking.getPaymentId());
            return bookingRepository.saveAndFlush(avail);
        }
        else return null;
    }

}

