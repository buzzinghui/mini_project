import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs';
import { Router } from '@angular/router';
import { EmitterService } from '../emitter.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private http: HttpClient, private router: Router,
    private emitService: EmitterService ) {}

  isLogin = false;
  username: string;
  password: string;
  authtoken: string;

  onSubmit(loginData: { userName: string; password: string }) {
    console.log(loginData);
    this.http.post(
      'http://localhost:8080/api/auth/v1/login',
      loginData
    ).subscribe(responseData => {
      console.log("log");
      console.log(responseData);
      this.authtoken = responseData['result']['token'];
      localStorage.setItem('token', this.authtoken);
      this.router.navigate(['/homepage']);
    })
    console.log("log2");
    console.log(loginData);
    //this.emitService.isLoginEmitter.emit(true);
    
  }

}
