import { throwError } from 'rxjs';
import { ResponseJson } from './../../entities/response-json.entity';
import { MyReviewsRequest } from './../../entities/my-reviews.entity';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { Component, ViewChild, OnInit, AfterViewInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({ 
    templateUrl: 'my-reviews.component.html',
    styleUrls: ['my-reviews.component.scss']
})
export class MyReviewsComponent implements OnInit, AfterViewInit {
    loading = false;
    public displayedColumns = ['revieweeName', 'revieweeEmail', 'feedbackName', 'comments', 'rating', 'status', 'createdDate', 'edit'];
    public dataSource = new MatTableDataSource<MyReviewsRequest>();
    myReviewForm: FormGroup;
    showReviewForm: boolean;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    constructor(private userService: UserService, 
                private authenticationService:  AuthenticationService,
                private router: Router, public fb: FormBuilder) { }

    ngOnInit() {
        this.loading = true;
        this.showReviewForm = false;
        this.userService.getReviews().subscribe(res => {
            const resp = res as ResponseJson; 
            if (resp.code === 200) {
                this.dataSource.data = resp.response;
            } else {
                console.log("error");
            }
         });


   }

   ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  editReview (myReview: MyReviewsRequest) {
    this.showReviewForm = true;
    this.myReviewForm = this.fb.group({
        participantId: [myReview.participantId],
        revieweeName: [myReview.revieweeName],
        status: [myReview.status],
        comments: [myReview.comments, Validators.required],
        rating: [myReview.rating, Validators.required],
        revieweeEmail: [myReview.revieweeEmail],
      });
  }

  submitForm() {
    const myReview: MyReviewsRequest = this.myReviewForm.value;
    if (this.myReviewForm.valid) {
        this.userService.saveReviews(myReview).pipe(first()).subscribe(res => {
            const resp = res as ResponseJson; 
            if (resp.code === 200) {
                this.showReviewForm = false;
                this.dataSource.data = resp.response;
            } else {
                console.log("error");
            }
        });
    }
  }
}