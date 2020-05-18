import { ReviewMnagementComponent } from './dashboard/review-management/review-management.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MyReviewsComponent } from './dashboard/my-reviews/my-reviews.component';
import { EmployeeManagementComponent } from './dashboard/employee-management/employee-management.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], children: [
    {path: 'myReviews', component: MyReviewsComponent, outlet: 'snc'},
    {path: 'manageEmployeeReviews', component: ReviewMnagementComponent, outlet: 'snc'},
    {path: 'manageEmployees', component: EmployeeManagementComponent, outlet: 'snc'}] },
  { path: '', component: LoginComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
}
