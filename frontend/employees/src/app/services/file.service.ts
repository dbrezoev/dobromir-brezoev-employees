import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environment/environment';
import { Observable } from 'rxjs';
import { EmployeesWorkingPair } from '../model/employees-working-pair';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = environment.serverUrl;
  private httpClient = inject(HttpClient);

  uploadFile(file: File, dateFormat: string): Observable<EmployeesWorkingPair[]> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append('dateFormat', dateFormat);
    return this.httpClient.post<EmployeesWorkingPair[]>(this.baseUrl + 'employees', formData);
  }
}

