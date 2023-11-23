
document.addEventListener('DOMContentLoaded', () => {

    const ticketForm = document.querySelector('#ticket-form');
  

    ticketForm.addEventListener('submit', async (event) => {
      event.preventDefault();
  

      const titulo = document.querySelector('#titulo').value;
      const descripcion = document.querySelector('#descripcion').value;
      const estado = document.querySelector('#estado').value;
      const userId = document.querySelector('#usuarios').value;
  
      // Crear objeto de ticket
      const ticketData = {
        titulo,
        descripcion,
        estado,
        userId: parseInt(userId), // Asegurarse de convertir a entero
      };
  
      try {
        // Enviar la solicitud para crear un nuevo ticket
        const response = await fetch('http://localhost:8080/tickets/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(ticketData),
        });
  
        const result = await response.json();
  
        // Manejar la respuesta del servidor
        console.log(result);
  
        // Aquí puedes realizar otras acciones después de enviar el formulario, si es necesario
      } catch (error) {
        console.error('Error al enviar el formulario:', error);
      }
    });
  
    // Obtener referencia al select de usuarios
    const usuariosSelect = document.querySelector('#usuarios');
  
    // Cargar lista de usuarios al cargar la página
    loadUsers();
  
    // Función para cargar la lista de usuarios
    async function loadUsers() {
      try {
        const response = await fetch('http://localhost:8080/users');
        const users = await response.json();
  
        // Limpiar opciones actuales
        usuariosSelect.innerHTML = '';
  
        // Crear opciones para cada usuario
        users.forEach((user) => {
          const option = document.createElement('option');
          option.value = user.id;
          option.textContent = user.username; 
          usuariosSelect.appendChild(option);
        });
      } catch (error) {
        console.error('Error al cargar la lista de usuarios:', error);
      }
    }
  });
  