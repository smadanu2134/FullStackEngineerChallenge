import { Employee } from './../../entities/employee.entity';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { ResponseJson } from 'src/app/entities/response-json.entity';
import { first } from 'rxjs/operators';

@Component({ 
    templateUrl:'employee-management.component.html' 
})
export class EmployeeManagementComponent {
    loading = false;
    public displayedColumns = ['firstName', 'lastName', 'userName', 'email', 'action'];
    public dataSource = new MatTableDataSource<Employee>();
    employeeForm: FormGroup;
    showEmployeeForm: boolean;
    action: string;

    @ViewChild(MatSort) sort: MatSort;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    constructor(private userService: UserService, 
                private authenticationService:  AuthenticationService,
                private router: Router, public fb: FormBuilder) { }

    ngOnInit() {
        this.loading = true;
        this.showEmployeeForm = false;
        this.userService.getAllEmployees().subscribe(res => {
            const resp = res as ResponseJson; 
            if (resp.code === 200) {
                this.dataSource.data = resp.response as Employee[];
            } else {
                console.log("error");
            }
         });


   }
   
   addEmployee() {
        this.showEmployeeForm = true;
        this.action = 'Add';
        this.employeeForm = this.fb.group({
            firstName: ['', Validators .required],
            lastName: ['', Validators .required],
            password: ['', Validators .required],
            userName: ['', Validators.required],
            email: ['', Validators.required],
          });
   }

   ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  onSubmit() {

  }

  editEmployee (employee: Employee) {
    this.showEmployeeForm = true;
    this.action = 'Edit';
    this.employeeForm = this.fb.group({
        id: [employee.id],
        firstName: [employee.firstName, Validators .required],
        lastName: [employee.lastName, Validators .required],
        email: [employee.email, Validators.required],
      });
  }

  deleteEmployee(employee: Employee) {
    this.userService.saveEmployee(employee, 'Delete').pipe(first()).subscribe(res => {
        const resp = res as ResponseJson; 
        if (resp.code === 200) {
            this.showEmployeeForm = false;
            this.dataSource.data = resp.response as Employee[];
        } else {
            console.log("error");
        }
    });
  }

  submitForm() {
    const employee: Employee = this.employeeForm.value;
    if (this.employeeForm.valid) {
        this.userService.saveEmployee(employee, this.action).pipe(first()).subscribe(res => {
            const resp = res as ResponseJson; 
            if (resp.code === 200) {
                this.showEmployeeForm = false;
                this.action = '';
                this.dataSource.data = resp.response as Employee[];
            } else {
                console.log("error");
            }
        });
    }
  }
}