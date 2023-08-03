import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private http: HttpClient, private router: Router) {}

  isLogin = false;
  userName: string;
  password: string;
  emailAddress: string

  onSubmit(signData: { userName: string; emailAddress: string; password: string }) {
    this.http.post(
      'http://localhost:8080/api/user/v1/register',
      signData
    ).subscribe(responseData => {
      console.log("log")
      console.log(responseData);
      alert("Sign Up Successful, please proceed to login with your username and password");
      this.router.navigate(['/homepage']);
    })
    console.log("log2")
    console.log(signData);
    
  }


}
