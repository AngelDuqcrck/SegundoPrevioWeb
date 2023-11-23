package com.segundo.previo.spweb.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.segundo.previo.spweb.entities.Ticket;
import com.segundo.previo.spweb.entities.User;
import com.segundo.previo.spweb.repositories.TicketRepository;
import com.segundo.previo.spweb.repositories.UserRepository;
import com.segundo.previo.spweb.shared.dto.TicketDTO;
import com.segundo.previo.spweb.shared.dto.UserDTO;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private UserRepository userRepository;


    public TicketDTO createTicket(TicketDTO ticketDTO){
        Ticket newTicket = new Ticket();
        BeanUtils.copyProperties(ticketDTO, newTicket);
        User user = userRepository.findById(ticketDTO.getUserId()).orElse(null);
        newTicket.setUser(user);
        newTicket.setFechaRegistro(new Date());
        
        Ticket savedTicket = ticketRepository.save(newTicket);

        TicketDTO newTicketDTO = new TicketDTO();
        BeanUtils.copyProperties(savedTicket, newTicketDTO);

        return newTicketDTO;
        
    }

    public List<Ticket> getTicketsByUserIdOrderedByDate(Integer userId) {
        return ticketRepository.findByUserIdOrderByFechaRegistroDesc(userId);
    }

}
