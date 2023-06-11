export class User {
    user!: UserClass;
    token!: string;
}

export class UserClass {
    uuid!: string;
    email!: string;
    name!: string;
    dni!: string;
    tarjeta!: string;
    monto!: number;
    rol!: string[];
}