/* JavaScript - admin.js */
document.addEventListener('DOMContentLoaded', function() {
    // Verificar autenticación
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');
    const role = localStorage.getItem('role');

    if (!token || role !== 'ADMIN') {
        window.location.href = '/login';
        return;
    }

    // Mostrar nombre de usuario
    document.getElementById('username-display').textContent = username;

    // Configurar botón de cerrar sesión
    document.getElementById('logout-btn').addEventListener('click', function() {
        localStorage.removeItem('token');
        localStorage.removeItem('username');
        localStorage.removeItem('role');
        window.location.href = '/login';
    });

    // Cargar lista de usuarios
    loadUsers();

    // Configurar formulario de creación de usuarios
    const createUserForm = document.getElementById('create-user-form');
    createUserForm.addEventListener('submit', function(e) {
        e.preventDefault();
        createUser();
    });

    // Función para cargar usuarios
    function loadUsers() {
        fetch('/api/users', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error al cargar usuarios');
            }
        })
        .then(users => {
            const tableBody = document.getElementById('users-table-body');
            tableBody.innerHTML = '';

            users.forEach(user => {
                const row = document.createElement('tr');

                // Celda de nombre de usuario
                const usernameCell = document.createElement('td');
                usernameCell.textContent = user.username;
                row.appendChild(usernameCell);

                // Celda de rol
                const roleCell = document.createElement('td');
                roleCell.textContent = user.role;
                row.appendChild(roleCell);

                // Celda de acc