export class Reserva{
    fila!: number;
    numeroButaca!: number;
    usuarioUuid!: string;
    tituloPelicula!: string;
}

export class ReservaDto{
    fila!: number;
    uuid!: string;
    numeroButaca!: number;
    usuarioUuid!: string;
    tituloPelicula!: string;
    fechaHoraReserva!: string;

}
