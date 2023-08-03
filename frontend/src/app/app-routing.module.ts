import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// import { RecipesComponent } from './recipes/recipes.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
// import { RecipeStartComponent } from './recipes/recipe-start/recipe-start.component';
// import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
// import { RecipeEditComponent } from './recipes/recipe-edit/recipe-edit.component';
// import { RecipesResolverService } from './recipes/recipes-resolver.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component'; 
import { NewitemComponent } from './newitem/newitem.component';

const appRoutes: Routes = [
  { path: '', redirectTo:'/homepage', pathMatch: 'full' },
  { path: 'homepage', component: ShoppingListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'changepassword', component: ChangepasswordComponent },
  { path: 'newitem', component: NewitemComponent } 
  // { path: 'cart', component: CartComponent },
  // { path: 'recipes', component: RecipesComponent},
  // {
  //   path: 'recipes',
  //   component: RecipesComponent,
  //   children: [
  //     { path: '', component: RecipeStartComponent },
  //     { path: 'new', component: RecipeEditComponent },
  //     {
  //       path: ':id',
  //       component: RecipeDetailComponent,
  //       resolve: [RecipesResolverService]
  //     },
  //     {
  //       path: ':id/edit',
  //       component: RecipeEditComponent,
  //       resolve: [RecipesResolverService]
  //     }
  //   ]
  // },
  // { path: 'shopping-list', component: ShoppingListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
