$(document).ready(function() {
    const baseUrl = 'http://localhost:8080/api';

    $('#btnEstudiantes').click(function() {
        $('.form-section').hide();
        $('.data-section').hide();
        $('#formEstudiantes').show();
        $('#dataEstudiantes').show();
        actualizarListaEstudiantes();
    });

    $('#btnCursos').click(function() {
        $('.form-section').hide();
        $('.data-section').hide();
        $('#formCursos').show();
        $('#dataCursos').show();
        actualizarListaCursos();
    });

    $('#btnMaterias').click(function() {
        $('.form-section').hide();
        $('.data-section').hide();
        $('#formMaterias').show();
        $('#dataMaterias').show();
        cargarProfesores();
        actualizarListaMaterias();
    });

    function cargarProfesores() {
        $.get(`${baseUrl}/materias/profesores`, function(data) {
            $('#profesor').empty();
            $('#profesor').append('<option value="">Seleccione un profesor</option>');
            data.forEach(profesor => {
                $('#profesor').append(`<option value="${profesor}">${profesor}</option>`);
            });
        }).fail(function() {
            alert('Error al cargar los profesores');
        });
    }

    $('#estudianteForm').submit(function(event) {
        event.preventDefault();
        const estudiante = {
            id: $('#estudianteId').val() || null,
            nombre: $('#nombre').val(),
            apellido: $('#apellido').val(),
            codigo: $('#codigo').val(),
            activo: $('#activo').is(':checked')
        };
        const method = estudiante.id ? 'PUT' : 'POST';
        const url = estudiante.id ? `${baseUrl}/estudiantes/${estudiante.id}` : `${baseUrl}/estudiantes`;
        $.ajax({
            url: url,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(estudiante),
            success: function() {
                alert('Estudiante guardado exitosamente');
                $('#estudianteForm')[0].reset();
                actualizarListaEstudiantes();
            }
        });
    });

    $('#cursoForm').submit(function(event) {
        event.preventDefault();
        const curso = {
            id: $('#cursoId').val() || null,
            nombre: $('#nombreCurso').val(),
            descripcion: $('#descripcion').val(),
            duracion: $('#duracion').val(),
            activo: $('#activoCurso').is(':checked')
        };
        const method = curso.id ? 'PUT' : 'POST';
        const url = curso.id ? `${baseUrl}/cursos/${curso.id}` : `${baseUrl}/cursos`;
        $.ajax({
            url: url,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(curso),
            success: function() {
                alert('Curso guardado exitosamente');
                $('#cursoForm')[0].reset();
                actualizarListaCursos();
            }
        });
    });

    $('#materiaForm').submit(function(event) {
        event.preventDefault();
        const materia = {
            id: $('#materiaId').val() || null,
            nombre: $('#nombreMateria').val(),
            profesor: $('#profesor').val(),
            cargaHoraria: $('#cargaHoraria').val()
        };
        const method = materia.id ? 'PUT' : 'POST';
        const url = materia.id ? `${baseUrl}/materias/${materia.id}` : `${baseUrl}/materias`;
        $.ajax({
            url: url,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(materia),
            success: function() {
                alert('Materia guardada exitosamente');
                $('#materiaForm')[0].reset();
                actualizarListaMaterias();
            }
        });
    });

    function actualizarListaEstudiantes() {
        $('#listaEstudiantes').empty();
        $.get(`${baseUrl}/estudiantes/activos`, function(data) {
            if (data.length === 0) {
                $('#noEstudiantes').show();
            } else {
                $('#noEstudiantes').hide();
                data.forEach(estudiante => {
                    $('#listaEstudiantes').append(`<li class="list-group-item">${estudiante.nombre} ${estudiante.apellido} - ${estudiante.codigo} <button class="btn btn-sm btn-primary edit-estudiante" data-id="${estudiante.id}">Editar</button> <button class="btn btn-sm btn-danger delete-estudiante" data-id="${estudiante.id}">Eliminar</button></li>`);
                });
            }
        });
    }

    function actualizarListaCursos() {
        $('#listaCursos').empty();
        $.get(`${baseUrl}/cursos`, function(data) {
            if (data.length === 0) {
                $('#noCursos').show();
            } else {
                $('#noCursos').hide();
                data.forEach(curso => {
                    $('#listaCursos').append(`<li class="list-group-item">${curso.nombre} - ${curso.descripcion} <button class="btn btn-sm btn-primary edit-curso" data-id="${curso.id}">Editar</button> <button class="btn btn-sm btn-danger delete-curso" data-id="${curso.id}">Eliminar</button></li>`);
                });
            }
        });
    }

    function actualizarListaMaterias() {
        $('#listaMaterias').empty();
        $.get(`${baseUrl}/materias`, function(data) {
            if (data.length === 0) {
                $('#noMaterias').show();
            } else {
                $('#noMaterias').hide();
                data.forEach(materia => {
                    $('#listaMaterias').append(`<li class="list-group-item">${materia.nombre} - ${materia.profesor} <button class="btn btn-sm btn-primary edit-materia" data-id="${materia.id}">Editar</button> <button class="btn btn-sm btn-danger delete-materia" data-id="${materia.id}">Eliminar</button></li>`);
                });
            }
        });
    }

    $('#listaEstudiantes').on('click', '.edit-estudiante', function() {
        const id = $(this).data('id');
        $.get(`${baseUrl}/estudiantes/${id}`, function(data) {
            $('#estudianteId').val(data.id);
            $('#nombre').val(data.nombre);
            $('#apellido').val(data.apellido);
            $('#codigo').val(data.codigo);
            $('#activo').prop('checked', data.activo);
            $('#formEstudiantes').show();
        });
    });

    $('#listaEstudiantes').on('click', '.delete-estudiante', function() {
        const id = $(this).data('id');
        if (confirm('¿Estás seguro de eliminar este estudiante?')) {
            $.ajax({
                url: `${baseUrl}/estudiantes/${id}`,
                type: 'DELETE',
                success: function() {
                    alert('Estudiante eliminado exitosamente');
                    actualizarListaEstudiantes();
                }
            });
        }
    });

    $('#listaCursos').on('click', '.edit-curso', function() {
        const id = $(this).data('id');
        $.get(`${baseUrl}/cursos/${id}`, function(data) {
            $('#cursoId').val(data.id);
            $('#nombreCurso').val(data.nombre);
            $('#descripcion').val(data.descripcion);
            $('#duracion').val(data.duracion);
            $('#activoCurso').prop('checked', data.activo);
            $('#formCursos').show();
        });
    });

    $('#listaCursos').on('click', '.delete-curso', function() {
        const id = $(this).data('id');
        if (confirm('¿Estás seguro de eliminar este curso?')) {
            $.ajax({
                url: `${baseUrl}/cursos/${id}`,
                type: 'DELETE',
                success: function() {
                    alert('Curso eliminado exitosamente');
                    actualizarListaCursos();
                }
            });
        }
    });

    $('#listaMaterias').on('click', '.edit-materia', function() {
        const id = $(this).data('id');
        $.get(`${baseUrl}/materias/${id}`, function(data) {
            $('#materiaId').val(data.id);
            $('#nombreMateria').val(data.nombre);
            $('#profesor').val(data.profesor);
            $('#cargaHoraria').val(data.cargaHoraria);
            $('#formMaterias').show();
        });
    });

    $('#listaMaterias').on('click', '.delete-materia', function() {
        const id = $(this).data('id');
        if (confirm('¿Estás seguro de eliminar esta materia?')) {
            $.ajax({
                url: `${baseUrl}/materias/${id}`,
                type: 'DELETE',
                success: function() {
                    alert('Materia eliminada exitosamente');
                    actualizarListaMaterias();
                }
            });
        }
    });

    $('#btnExportarPDF').click(function() {
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();

        let y = 10;
        if ($('#dataEstudiantes').is(':visible')) {
            $('#listaEstudiantes li').each(function() {
                doc.text($(this).text(), 10, y);
                y += 10;
            });
        } else if ($('#dataCursos').is(':visible')) {
            $('#listaCursos li').each(function() {
                doc.text($(this).text(), 10, y);
                y += 10;
            });
        } else if ($('#dataMaterias').is(':visible')) {
            $('#listaMaterias li').each(function() {
                doc.text($(this).text(), 10, y);
                y += 10;
            });
        }

        doc.save('lista-datos.pdf');
    });
});
