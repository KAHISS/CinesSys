package controller.business;

import models.Movie;
import models.Room;
import models.Session;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Classe de testes para a classe RoomController.
 * @author Kaique Silva Sousa
 * @since 11/06/2023
 * @version 1.0
 */
public class RoomControllerTest {

    /**
     * inicialização do RoomController antes de cada teste
     */
    @Before
    public void setup() {
        Room.resetIdGenerator();
    }

    /**
     * Teste para verificar o estado inicial do RoomController.
     */
    @Test
    public void testConstructorInitialState() {
        assertEquals(5, RoomController.getAllRooms().size());
    }

    
    /**
     * Teste para verificar a recuperação de uma sala pelo seu ID.
     * Valida se a sala com ID 1 possui o número total de assentos esperado.
     */
    @Test
    public void testGetRoomById() {
        Room room = RoomController.getRoomById(1);
        System.out.println(room.getTotalSeat());
        assertEquals(200, room.getTotalSeat());
    }

    /**
     * Teste para verificar o comportamento do método getRoomById quando uma sala não existe.
     * Valida se o método retorna null quando um ID de sala inexistente é fornecido.
     */
    @Test(expected = RuntimeException.class)
    public void testGetRoomByIdNotFound() {
        Room room = RoomController.getRoomById(6);
    }

    /**
     * Teste para verificar a adição de uma sessão a uma sala.
     * Valida se a sessão é corretamente adicionada à sala e se o número de sessões é incrementado.
     */
    @Test
    public void testAddSessionToRoom() {
        Room room = RoomController.getRoomById(1);
        Movie movie = new Movie("Test Movie", "Test Genre", 120, "Test classification", "Test Synopsis");
        Session session = new Session(LocalDate.now(), LocalTime.now(), room, movie, 10.0);
        RoomController.addSessionToRoom(room.getId(), session);
        assertEquals(1, room.getSessions().size());
    }

    /**
     * Teste para verificar a remoção da próxima sessão de uma sala.
     * Valida se a sessão é corretamente removida da sala e se o número de sessões é reduzido a zero.
     */
    @Test
    public void testRemoveNextSessionFromRoom() {
        Room room = RoomController.getRoomById(1);
        Session session = new Session(LocalDate.now(), LocalTime.now(), room, new Movie("Test Movie", "Test Genre", 120, "Test classification", "Test Synopsis"), 10.0);
        RoomController.addSessionToRoom(room.getId(), session);
        RoomController.removeNextSessionFromRoom(room.getId());
        assertEquals(0, room.getSessions().size());
    }

}
