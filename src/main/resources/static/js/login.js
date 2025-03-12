/* JavaScript - login.js */
document.addEventListener('DOMContentLoaded', function() {
    // Redirigir si ya hay una sesión activa
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');

    if (token && role) {
        if (role === 'ADMIN') {
            window.location.href = '/admin';
        } else {
            window.location.href = '/user';
        }
    }

    const loginForm = document.getElementById('login-form');
    const errorMessage = document.getElementById('error-message');

    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Realizar la petición de login
        fetch('/api/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Credenciales inválidas');
            }
        })
        .then(data => {
            // Guardar token y datos del usuario
            localStorage.setItem('token', data.token);
            localStorage.setItem('username', data.username);
            localStorage.setItem('role', data.role);

            // Redirigir según el rol
            if (data.role === 'ADMIN') {
                window.location.href = '/admin';
            } else {
                window.location.href = '/user';
            }
        })
        .catch(error => {
            errorMessage.textContent = error.message;
            errorMessage.style.display = 'block';
        });
    });
});