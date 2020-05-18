import { ADMIN_MENU_LIST, EMP_MENU_LIST } from './../constants';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './../services/AuthenticationService';
import { Component, ViewChild } from '@angular/core';
import { User } from 'src/app/entities/user.entity';
import { UserService } from 'src/app/services/user.service';
import { MatSidenav } from '@angular/material/sidenav';
import { UtilService } from '../services/util.service';
@Component({ 
    templateUrl: 'dashboard.component.html',
    styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent {
    loading = false;
    @ViewChild('sidenav') sidenav: MatSidenav;
    isExpanded= true;
    isShowing = false;
    menuList: any[];
    user: User;

    

    constructor(private userService: UserService, 
                private authenticationService:  AuthenticationService,
                private router: Router, public route: ActivatedRoute,
                public utilService: UtilService) { }

    ngOnInit() {
        this.user = this.authenticationService.currentUserValue;
        this.menuList = this.user.priorityRole === 'Administartor'? ADMIN_MENU_LIST: EMP_MENU_LIST;
        this.loading = true;

        const navExtras =  {relativeTo: this.route.parent.firstChild};  
        const navCommand = [{ outlets: { snc: [ 'myReviews']}}]
        this.router.navigate(navCommand, navExtras);  
        this.utilService.selectedMenu =  'myReviews';     
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }

    onMenuClick(menu) {
        this.utilService.selectedMenu =  menu;
        const navExtras =  {relativeTo: this.route.parent.firstChild};  
        const navCommand = [{ outlets: { snc: [ menu]}}]
        this.router.navigate(navCommand, navExtras);
    }

    mouseenter() {
        if (!this.isExpanded) {
        this.isShowing = true;
        }
    }

    mouseleave() {
        if (!this.isExpanded) {
        this.isShowing = false;
        }
    }
}