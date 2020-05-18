import { DropdownDTO } from './../../entities/dropdown.entity';
import { FeedbackParticipants } from './../../entities/feeback.participants.entity';
import { Employee } from './../../entities/employee.entity';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MyReviews, MyReviewsRequest } from 'src/app/entities/my-reviews.entity';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { ResponseJson } from 'src/app/entities/response-json.entity';
import { first } from 'rxjs/operators';
import { MatSelectChange } from '@angular/material/select';

@Component({
    templateUrl: 'review-management.component.html'
})
export class ReviewMnagementComponent {
    loading = false;
    feedbackFormGroup: FormGroup;
    showAdd: boolean;
    showParticipants: boolean;
    employeeList: Employee[];
    feedbackList: DropdownDTO[];
    public displayParticipantColumns = ['participantName', 'participantEmail', 'status', 'assignedDate'];
    public dataSource = new MatTableDataSource<FeedbackParticipants>();

    constructor(private userService: UserService,
        private authenticationService: AuthenticationService,
        private router: Router, public fb: FormBuilder) { }

    ngOnInit() {
        this.loading = true;
        this.dataSource.data = [];
        this.userService.getAllEmployees().subscribe(res => {
            const resp = res as ResponseJson;
            if (resp.code === 200) {
                this.employeeList = resp.response as Employee[];
            } else {
                console.log("error");
            }
        });

        this.feedbackFormGroup = this.fb.group({
            revieweeId: [0, Validators.required],
            feedbackId: [0, Validators.required],
            participantIds: [null, Validators.required],
            feedbackName: [{value: '', disabled: true}, Validators.required]
        });
    }

    onRevieweeChange(event: MatSelectChange) {
        this.feedbackList = [];
        this.dataSource.data = [];
        this.feedbackFormGroup.controls.feedbackId.setValue("");
        this.userService.getPartipantFeedbacks(event.value).subscribe(res => {
            const resp = res as ResponseJson;
            if (resp.code === 200) {
                this.feedbackList = resp.response as DropdownDTO[];
            } else {
                console.log("error");
            }
        });
    }

    onFeedbackChange(event: MatSelectChange) {
        this.userService.getFeedbackParticipantsList(event.value).subscribe(res => {
            const resp = res as ResponseJson;
            if (resp.code === 200) {
                this.dataSource.data = resp.response as FeedbackParticipants[];
            } else {
                console.log("error");
            }
        });
    }

    addFeedback() {
        if (!this.feedbackFormGroup.controls.revieweeId.value) {
            alert('Select Employee Name');
            return;
        }
        this.feedbackFormGroup.controls.feedbackId.disable();
        this.feedbackFormGroup.controls.feedbackName.enable();
        this.dataSource.data = [];

        this.showAdd = true;
    }

    addParticipants() {
        if (this.showAdd) {
            this.dataSource.data = [];
        }

        this.showParticipants = true;

    }

    submitFeedbackForm() {
        if (this.feedbackFormGroup.valid) {
            const obj = this.feedbackFormGroup.value;
            obj.revieweeId = this.feedbackFormGroup.controls.revieweeId.value ? this.feedbackFormGroup.controls.revieweeId.value : 0;
            obj.feedbackId = this.feedbackFormGroup.controls.feedbackId.value ? this.feedbackFormGroup.controls.feedbackId.value : 0;
            obj.participantIds = this.feedbackFormGroup.controls.participantIds.value ? this.feedbackFormGroup.controls.participantIds.value : 0;
            obj.feedbackName = this.feedbackFormGroup.controls.feedbackName.value ? this.feedbackFormGroup.controls.feedbackName.value : '';

            this.userService.addParticipants(obj).subscribe(res => {
                const resp = res as ResponseJson;
                if (resp.code === 200) {
                    this.dataSource.data = resp.response as FeedbackParticipants[];
                    this.feedbackFormGroup.controls.participantIds.setValue([]);
                    this.feedbackFormGroup.controls.feedbackName.setValue('');
                    this.feedbackFormGroup.controls.feedbackName.disable();
                    this.feedbackFormGroup.controls.feedbackId.enable();
                    this.showAdd = false;
                    this.showParticipants = false;
                } else {
                    console.log("error");
                }
            });
        }


    }

    ngAfterViewInit(): void {
    }
}