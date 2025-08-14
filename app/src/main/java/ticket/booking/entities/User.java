package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private String name;
    private String password;
    @JsonProperty("hashed_Password")
    private String hashPassword;
    @JsonProperty("tickets_booked")
    private List<Ticket> ticketsBooked;
    @JsonProperty("user_id")
    private String userId;

    public User(String name, String password, String hashedpassword, List<Ticket> tickectsBooked, String userid){
        this.name = name;
        this.password = password;
        this.hashPassword = hashedpassword;
        this.ticketsBooked = tickectsBooked;
        this.userId = userid;
    }

    public User(){}

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String getHashPassword(){
        return hashPassword;
    }

    public List<Ticket> getTicketsBooked(){
        return ticketsBooked;
    }

//    public void showTickets(){
//        for(int i = 0; i < ticketsBooked.size(); i++){
//            System.out.println(ticketsBooked.get(i).getTicketInfo());
//        }
//    }

    public String getUserId(){
        return userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setHashPassword(String hashPassword){
        this.hashPassword = hashPassword;
    }
    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
}
