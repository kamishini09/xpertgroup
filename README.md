## Tencnologías a usar:
- Java version: 17
- maven 
- Spring Boot version: 2.7.16
- Base de datos: H2
- Arquitectura Hexagonal 


## Problema
Almacenar toda la información de un cliente para 
separarla en distintos microservicios, comenzando por los siguientes datos: Nombre, apellido paterno, apellido materno, fecha de nacimiento, sexo, correo, teléfono.

## Solucion
Crear una API tipo REST la cual permita llevar a cabo las siguientes funcionalidades
1. El Path `/api/customer/save`  y el método HTTP tipo **POST**: permite crear un cliente con las siguientes validaciones
    1. Un cliente para ser guardado debe tener como minimo la informacion de nombre,  apellido paterno, apellido materno, 
       el sexo debe de ser una letra aunque no valida la letra,el telefono debe de ser un numero entre 7 y 10 digido de lo contrario
        debería retornar un error HTTP 400 con el siguiente json.  
       **Para verificar si un cliente debe llenar el campo  _nombre_**
        ```json
            {
             "message" : "El nombre no puede estar vacio"
            }
        ```      
       
    2. Si en el campo cliente ya ha sido creado retornara error HTTP 409 con el siguiente JSON
         ```json
             {
               "message" : "El cliente: alberto aldo rojas, ya esta registrado."
             }
         ```
   **Ejemplo de petición y respuesta exitosa**  
   Petición  path: `/api/customer/save` método: **POST**
   ```json
    {
        "nombre": "alaberto",
        "apellido_paterno": "Aldo",
        "apellido_materno": "Rojas",
        "sexo": "M",
        "correo": "prueba@gmail.com",
        "telefono": 22345678,
        "fecha_nacimiento": "1992-05-09"
    }
    ```
   **Respuesta exitosa**
    ```
       1
    ```
2. El path  `/api/customer/search/{IdCliente}` y el método HTTP tipo **GET**, donde la variable  {IdCliente} corresponde al identificador con el cual se almacenó el cliente en la base de datos que responde el primer punto.
   El siguiente es un ejemplo de petición y un ejemplo de cómo debería ser la respuesta en un caso exitoso  
   Petición  path: `/api/customer/search/1` método: **GET**
   ```json
        {
            "id": 1,
            "nombre": "alberto",
            "apellido_paterno": "Aldo",
            "apellido_materno": "Rojas",
            "sexo": "M",
            "correo": "prueba@gmail.com",
            "telefono": 22345678,
            "fecha_nacimiento": "1992-05-09T00:00:00.000+00:00"
        }
    ```

