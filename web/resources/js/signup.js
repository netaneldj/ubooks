var verificarContrasenia = function() {
  if (document.getElementById('contrasenia').value == document.getElementById('confirmar_contrasenia').value) {
    document.getElementById('mensaje').style.color = 'green';
    document.getElementById('mensaje').innerHTML = 'Correcto';
  } else {
    document.getElementById('mensaje').style.color = 'red';
    document.getElementById('mensaje').innerHTML = 'Incorrecto';
  }
}