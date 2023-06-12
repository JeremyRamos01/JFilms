DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid varchar(255) UNIQUE,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    dni VARCHAR(255) NOT NULL,
    rol VARCHAR(255) NOT NULL,
    tarjeta VARCHAR(255) NOT NULL,
    monto DOUBLE NOT NULL,
    avaliable BOOL NOT NULL,
    deleted BOOL
);

CREATE TABLE IF NOT EXISTS butacas(
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    uuid varchar(255) UNIQUE,
                                    fila INTEGER NOT NULL,
                                    numero INTEGER NOT NULL,
                                    salaId INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS entradas(
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    tituloPelicula varchar(255) NOT NULL ,
                                    fechaDia VARCHAR(255) NOT NULL,
                                    sala INTEGER NOT NULL,
                                    reserva INTEGER NOT NULL,
                                    precio DOUBLE NOT NULL

);
CREATE TABLE IF NOT EXISTS peliculas(
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title varchar(255) NOT NULL,
                                    overview varchar(10000) NOT NULL,
                                    poster_path varchar(255) NOT NULL,
                                    vote_average DOUBLE NOT NULL

);


CREATE TABLE IF NOT EXISTS reservas
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    fila  INTEGER NOT NULL ,
    uuid varchar(255) UNIQUE,
    usuario varchar(255) NOT NULL,
    tituloPelicula varchar(255) NOT NULL,
    butaca INTEGER NOT NULL,
    fechaHoraReserva DATE NOT NULL
);
CREATE TABLE IF NOT EXISTS salas(
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    uuid VARCHAR(255) NOT NULL,
                                    horaSala VARCHAR(255) NOT NULL,
                                    pelicula INTEGER NOT NULL
);


