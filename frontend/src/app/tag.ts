export class Tag {
  id: number;
  description: string;

  static fromJSON(data:any): Tag {
    return Object.assign(new Tag(),data);
  }
}
