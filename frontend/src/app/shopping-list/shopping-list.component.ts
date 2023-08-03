import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

//import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from './shopping-list.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ShopList } from '../shoplist.model';
import { map } from 'rxjs';
import { EmitterService } from '../emitter.service';


@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  loadedPosts = [];
  shoppingList: ShopList[] = [];
  data: ShopList;
  isUserLoggedIn: boolean = false;
  filteredName = '';

  constructor(private slService: ShoppingListService,
    private http: HttpClient,
    private emitService: EmitterService,
    private router: Router) { }

  ngOnInit() {
    
    this.onFetchPosts()
    this.isUserLoggedIn = this.checkLoggedIn();
    console.log("haha" + this.isUserLoggedIn);
  }

  // onEditItem(index: number) {
  //   this.slService.startedEditing.next(index);
  // }

  checkLoggedIn() {
    let temp = localStorage.getItem('token');
    if (temp == null){
      return false;
    }
    return true;
  }

  onLogout() {
    let token = localStorage.getItem('token');
    console.log(token);
    const headers2 = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    })
    const requestOptions = { headers: headers2 };
    console.log(headers2);
    this.http.get(
      'http://localhost:8080/api/auth/v1/logout',
      requestOptions
    ).subscribe(data => {
      console.log(data);
      alert("Logout successfully");
    })
    alert("Logout successfully");
    localStorage.removeItem('token');
    location.reload();
  }

  ngOnDestroy() {
    //this.subscription.unsubscribe();
  }

  onFetchPosts() {
    // Send Http request
    //let headers = new HttpHeaders({});
    this.http.get(
      'http://localhost:8080/api/product/v1',
    ).pipe(map(responseData => {
      const shoppingArray: ShopList[] = [];
      for (const key in responseData){
        if (responseData.hasOwnProperty(key)) {
          shoppingArray.push({ ...responseData[key], id: key });
        }
      }
      console.log(shoppingArray);
      return shoppingArray;
    })).subscribe(responseData => {
      console.log(responseData);
      this.shoppingList = responseData;
      }, error => {
      console.log(error);
    });
  }

  changePassword() {
    this.router.navigate(['/changepassword']);
  }

  onAddItem() {
    this.router.navigate(['/newitem']);
  }


}