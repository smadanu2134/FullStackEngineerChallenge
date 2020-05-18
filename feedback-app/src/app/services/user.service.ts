import { MyReviewsRequest } from 'src/app/entities/my-reviews.entity';
import { MyReviews } from './../entities/my-reviews.entity';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import { Employee } from '../entities/employee.entity';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getReviews(): Observable<any> {
        return this.http.post(`${environment.baseUrl}/myReviews`, {});
        //return of(MY_REVIEWS);
    }

    saveReviews(myReview: MyReviewsRequest): Observable<any> {
        return this.http.post(`${environment.baseUrl}/saveReviews`, myReview);
        //return of(MY_REVIEWS);
    }
    
    saveEmployee(employee: Employee, action): Observable<any> {
        let url = '';
        if (action === 'Add') {
            url = `${environment.baseUrl}/addEmployee`;
        } else if (action === 'Delete') {
            url = `${environment.baseUrl}/deleteEmployee`;
        } else {
            url = `${environment.baseUrl}/modifyEmployee`;
        }
        
        return this.http.post(url, employee);
    }

    getAllEmployees(): Observable<any> {
        return this.http.post(`${environment.baseUrl}/employees`, {});
    }

    getReviewers(revieweeId, feedbackId): Observable<any> {
        //return this.http.post(`${environment.baseUrl}/reviewers`, {revieweeId, feedbackId});
        return of([]);
    }

    getPartipantFeedbacks(revieweeId): Observable<any> {
        return this.http.post(`${environment.baseUrl}/feedbacks`, {revieweeId});
    }

    getFeedbackParticipantsList(feedbackId): Observable<any> {
        return this.http.post(`${environment.baseUrl}/feedbackParticipants`, {feedbackId});
    }

    addParticipants(obj): Observable<any> {
        return this.http.post(`${environment.baseUrl}/addParticipants`, obj);
    }
}