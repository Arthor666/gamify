export class AuthenticationRequestObject {
  correo: string;
  password: string;

  constructor(auth) {
    this.correo = auth.correo;
    this.password = auth.password;
  }
}
