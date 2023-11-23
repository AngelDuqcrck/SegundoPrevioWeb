document.addEventListener('DOMContentLoaded', () => {
    const ticketsTableBody = document.querySelector('#ticketsTable tbody');

    // Llama a la función para cargar los tickets al cargar la página
    loadTickets();

    async function loadTickets() {
        try {
            // Reemplaza '1' con el ID del usuario actual (puedes obtenerlo desde tu sistema de autenticación)
            const userId = 2;

            const response = await fetch(`http://localhost:8080/tickets/${userId}`);
            const tickets = await response.json();

            // Limpiar tabla actual
            ticketsTableBody.innerHTML = '';

            // Crear filas para cada ticket
            tickets.forEach((ticket) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <th scope="row">${ticket.id}</th>
                    <td>${ticket.titulo}</td>
                    <td>${ticket.descripcion}</td>
                    <td>${new Date(ticket.fechaRegistro).toLocaleDateString()}</td>
                    <td>${ticket.estado}</td>
                `;
                ticketsTableBody.appendChild(row);
            });
        } catch (error) {
            console.error('Error al cargar la lista de tickets:', error);
        }
    }
});
