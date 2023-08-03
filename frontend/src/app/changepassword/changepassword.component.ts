import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent {
  
  constructor (private http: HttpClient, private router: Router) {}

  oldPassword: string;
  newPassword: string;
  retypeNewPassword: string;

  

  onChangePassword(passwords: { oldPassword: string; newPassword: string; 
    confirmPassword: string }) {
    let token = localStorage.getItem('token');

    const headers2 = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    const requestOptions = { headers: headers2 };
    this.http.post(
      'http://localhost:8080/api/auth/v1/password',
      requestOptions).subscribe(data => {
        if (data['code'] === "200") {
          alert("changed successful");
          this.router.navigate(['/homepage'])
        }
      })
      alert("changed successful");
      this.router.navigate(['/homepage'])
  }


}
