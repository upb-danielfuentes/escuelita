$(document).ready(function() {
    // Mostrar el formulario correspondiente al hacer clic en un botón
    $('#btnEstudiantes').click(function() {
        $('.form-section').hide();
        $('#formEstudiantes').show();
    });

    $('#btnCursos').click(function() {
        $('.form-section').hide();
        $('#formCursos').show();
    });

    $('#btnMaterias').click(function() {
        $('.form-section').hide();
        $('#formMaterias').show();
    });

    // Manejar el envío del formulario de estudiantes
    $('#estudianteForm').submit(function(event) {
        event.preventDefault();
        const estudiante = {
            nombre: $('#nombre').val(),
            apellido: $('#apellido').val(),
            codigo: $('#codigo').val(),
            activo: $('#activo').is(':checked')
        };
        $.ajax({
            url: '/api/estudiantes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(estudiante),
            success: function() {
                alert('Estudiante guardado exitosamente');
                $('#estudianteForm')[0].reset();
                actualizarLista();
            }
        });
    });

    // Manejar el envío del formulario de cursos
    $('#cursoForm').submit(function(event) {
        event.preventDefault();
        const curso = {
            nombre: $('#nombreCurso').val(),
            descripcion: $('#descripcion').val(),
            duracion: $('#duracion').val(),
            activo: $('#activoCurso').is(':checked')
        };
        $.ajax({
            url: '/api/cursos',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(curso),
            success: function() {
                alert('Curso guardado exitosamente');
                $('#cursoForm')[0].reset();
                actualizarLista();
            }
        });
    });

    // Manejar el envío del formulario de materias
    $('#materiaForm').submit(function(event) {
        event.preventDefault();
        const materia = {
            nombre: $('#nombreMateria').val(),
            profesor: $('#profesor').val(),
            cargaHoraria: $('#cargaHoraria').val()
        };
        $.ajax({
            url: '/api/materias',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(materia),
            success: function() {
                alert('Materia guardada exitosamente');
                $('#materiaForm')[0].reset();
                actualizarLista();
            }
        });
    });

    // Actualizar la lista de información registrada
    function actualizarLista() {
        $('#listaDatos').empty();
        $.get('/api/estudiantes/activos', function(data) {
            data.forEach(estudiante => {
                $('#listaDatos').append(`<li class="list-group-item">${estudiante.nombre} ${estudiante.apellido} - ${estudiante.codigo}</li>`);
            });
        });
        $.get('/api/cursos', function(data) {
            data.forEach(curso => {
                $('#listaDatos').append(`<li class="list-group-item">${curso.nombre} - ${curso.descripcion}</li>`);
            });
        });
        $.get('/api/materias', function(data) {
            data.forEach(materia => {
                $('#listaDatos').append(`<li class="list-group-item">${materia.nombre} - ${materia.profesor}</li>`);
            });
        });
    }

    // Inicializar la lista de información registrada
    actualizarLista();
});
