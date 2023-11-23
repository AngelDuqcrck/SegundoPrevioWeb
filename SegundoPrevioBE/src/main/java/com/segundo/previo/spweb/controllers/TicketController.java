package com.segundo.previo.spweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.segundo.previo.spweb.entities.Ticket;
import com.segundo.previo.spweb.services.TicketService;
import com.segundo.previo.spweb.shared.dto.Response;
import com.segundo.previo.spweb.shared.dto.TicketDTO;
import com.segundo.previo.spweb.shared.dto.UserDTO;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public Response createTicket(@RequestBody TicketDTO ticket){
        Response response = new Response();

        try {
            TicketDTO newTicket = ticketService.createTicket(ticket);

            if (newTicket!= null) {
                response.setMessage("Ticket created successfully");
            } else {
                response.setMessage("Unexpected error while ticket was created");
            }
        } catch (IllegalArgumentException e) {
            response.setMessage("Error: " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/{userId}")
    public List<Ticket> getTicketsForUser(@PathVariable Integer userId) {
        return ticketService.getTicketsByUserIdOrderedByDate(userId);
    }
}
