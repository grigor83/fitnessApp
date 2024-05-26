import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Participation } from '../models/participation';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Message } from '../models/message';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private url = 'http://localhost:8080/korisnik';

  public users: Array<User> = [];
  public signedIn: boolean = false;
  public activeUser: User|null = null;

  constructor(private httpClient: HttpClient, private snackBar: MatSnackBar) { }


  getAllUsers() {
    return this.httpClient.get<User[]>(this.url);
  }

  registerUser(user : User) {
    return this.httpClient.post<User>(this.url, user);
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.url}/${user.korisnikId}`, user);    
  }

  public login(username: string, password: string): boolean {
    let result = false;
    this.activeUser = null;
    let userExists = false;

    for (const user of this.users){
      if (user.korisnickoIme == username && user.lozinka == password) {
        if (user.verifikovan){
          result = true;
          this.activeUser = user;
          userExists = true;
          this.updateLogs().subscribe(response => {
            console.log(response);
          });
          alert("Zdravo, " + this.activeUser.korisnickoIme + "! Uspješno ste se ulogovali!");
          break;
        }
        else {
          this.registerUser(user).subscribe(response => {
            this.snackBar.open("Verifikacioni mejl je opet poslat na vašu emajl adresu!", undefined, { 
              duration: 2000, verticalPosition: 'top'
            }); 
          });
          userExists = true;
          this.activeUser = user;
          break;
        }
      }
    }

    if (!userExists)
      alert("Neispravno korisničko ime ili lozinka!");
    this.signedIn = result;
    return result;
  }

  private updateLogs() {
    const logUrl = 'http://localhost:8080/logs';
    return this.httpClient.put<any>(`${logUrl}/${1}`, 1);
  }

  public logout(){
    this.activeUser = null;
    this.signedIn = false;
  }

  payProgram(newParticipation: Participation): Observable<Participation> {
    return this.httpClient.post<Participation>('http://localhost:8080/ucestvuje', newParticipation);
  }

  getAllParticipations(): Observable<Participation[]>{
    return this.httpClient.get<Participation[]>('http://localhost:8080/ucestvuje');
  }

  sendMessage(message : Message){
    return this.httpClient.post<User>('http://localhost:8080/poruka',message);
  }

  getUsersMessages(user : User|null){
    return this.httpClient.get<Message[]>(`http://localhost:8080/poruka/user/${user?.korisnikId}`)
  }

}
