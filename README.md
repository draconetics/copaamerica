# APLICACION DE NOTICIAS PARA LA COPA AMERICA

ANSIBLE - Instalacion
- sudo apt update
- sudo apt install software-properties-common
- sudo apt-add-repositoryppa:ansible/ansible
- sudo apt update
- sudo apt install ansible
- CREAR KEYGEN
- ssh-keygen
- ssh-copy-id –i ~/.ssh/id_rsa.pub root@<ipAddress>
 								ipAddress: Dirección ipdel nodo que se desea gestionar.•
- Verificar conexión: ansible<node> -m ping –u <user>
- Crear el inventario en el archivo /etc/ansible/hosts
- en este archivo insertar:
[webservers]
192.168.0.15 (ip de la pc que se desea controlar por ansible)
- Para la correcta instalacion se requiere un conexion de capa 2.(que se pueda hacer ping entre las maquinas)
- En la computadora destino instalar ssh server para que pueda establecerse la privacidad mediante un canal cifrfo.
- ejecutar:
	#ansible-playbook tareas-yml

VIDEO EXPLICACION
https://www.youtube.com/watch?v=MTFUIdVqURA