// js/app.js
document.addEventListener('DOMContentLoaded', function() {
    const baseUrl = 'http://localhost:8080/api';
    const form = document.getElementById('form');
    const userIdInput = document.getElementById('userId');
    const nombreInput = document.getElementById('nombre');
    const emailInput = document.getElementById('email');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const userId = userIdInput.value;
        const user = {
            nombre: nombreInput.value,
            email: emailInput.value
        };

        if (userId) {
            updateUser(userId, user);
        } else {
            addUser(user);
        }
    });

    function loadUsers() {
        fetch(`${baseUrl}/usuarios`)
            .then(response => response.json())
            .then(users => {
                const usersHtml = users.map(user =>
                    `<tr>
                        <td>${user.nombre}</td>
                        <td>${user.email}</td>
                        <td>
                            <button onclick="editUser('${user.id}', '${user.nombre}', '${user.email}')" class="btn btn-warning">Editar</button>
                            <button onclick="deleteUser('${user.id}')" class="btn btn-danger">Eliminar</button>
                        </td>
                    </tr>`
                ).join('');
                document.getElementById('usersList').innerHTML = `<table class="table">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>${usersHtml}</tbody>
                </table>`;
            });
    }

    function addUser(user) {
        fetch(`${baseUrl}/usuarios`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        })
            .then(response => response.ok && loadUsers());
    }

    function updateUser(id, user) {
        fetch(`${baseUrl}/usuarios/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        })
            .then(response => response.ok && loadUsers());
    }

    function deleteUser(id) {
        fetch(`${baseUrl}/usuarios/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.ok && loadUsers());
    }

    window.editUser = function(id, nombre, email) {
        userIdInput.value = id;
        nombreInput.value = nombre;
        emailInput.value = email;
    };

    window.deleteUser = deleteUser;

    loadUsers();
});
