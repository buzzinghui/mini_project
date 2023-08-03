import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-newitem',
  templateUrl: './newitem.component.html',
  styleUrls: ['./newitem.component.css']
})
export class NewitemComponent {

  newItem: any = {}; // You can replace 'any' with an interface representing your item data

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit(newItem: { name: string; description: string; price: number;
    stock: number }) {
    // Here, you can implement the logic to send the newItem data to the backend
    // For example, you can use the HttpClient to make a POST request to your API
    let token = localStorage.getItem('token');

    const headers2 = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    const requestOptions = { headers: headers2 };
    this.http.post('http://localhost:8080/api/admin/product/v1', this.newItem, requestOptions)
      .subscribe((response) => {
        console.log('Item created successfully:', response);
        // Optionally, you can handle the response or display a success message to the user
      }, (error) => {
        console.error('Error creating item:', error);
        // Optionally, you can handle the error or display an error message to the user
      });
      alert("item added successfully");
      this.router.navigate(['/homepage'])
  }

  onFileChange(event: any) {
    // Here, you can handle the file upload and save the file data to the newItem object
    // For example, you can use the FileReader API to read the file and convert it to base64
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      this.newItem.thumbnail = reader.result;
    };
    reader.readAsDataURL(file);
  }

}
