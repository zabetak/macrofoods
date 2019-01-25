import { JWT } from './jwt';

export class User {
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  jwt: JWT;
}
