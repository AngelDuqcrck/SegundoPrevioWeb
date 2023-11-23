package com.segundo.previo.spweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.segundo.previo.spweb.entities.Ticket;
import com.segundo.previo.spweb.entities.User;

import java.util.List;
import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByUser(User user);
    Optional<Ticket> findById(Integer id);
}
