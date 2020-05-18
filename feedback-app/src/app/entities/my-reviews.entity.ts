export class MyReviewsRequest {
    participantId: number; 
    feedbackName: string;
    revieweeName: string; 
    revieweeEmail: string;
    comments: string; 
    rating: string; 
    status: string; 
    assignedDate: string;
}

export class MyReviews {
    key: string;
    reviews: MyReviewsRequest[];
}

export class MyReviewsJson {
    key: string;
    review: MyReviews;
}
