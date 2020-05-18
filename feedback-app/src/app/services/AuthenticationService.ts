import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { User } from '../entities/user.entity';
import { ResponseJson } from '../entities/response-json.entity';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${environment.baseUrl}/auth/authenticate`, { username, password })
            .pipe(map(resp => {
                const response: ResponseJson = resp as ResponseJson;
                if (response.code === 200) {
                    localStorage.setItem('currentUser', JSON.stringify(response.response));
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    this.currentUserSubject.next(response.response);
                    return response.response;
                } else {
                    return response.errors;
                }
                
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}