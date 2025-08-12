package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{
    private User user;
    private List<User> userList;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService() throws IOException {
        getUserList();
    }
    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        getUserList();
    }

    public void getUserList() throws IOException{
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 ->{
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean registerUser(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException e){
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException {
        objectMapper.writeValue(new File(USER_PATH), userList);
    }

    public void fetchBookedTickets(){
        user.showTickets();
    }

    public Boolean cancelBooking(String ticketId){
        try {
            Optional<Ticket> foundTicket = user.getTicketsBooked().stream().filter(ticket -> ticket.getTicketId().equals(ticketId)).findFirst();
            if(foundTicket.isPresent()){
                user.getTicketsBooked().remove(foundTicket.get());
                saveUserListToFile();
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch (IOException e){
            return new ArrayList<>();
        }
    }
}
