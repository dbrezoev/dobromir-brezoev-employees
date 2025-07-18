import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environment/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {


  private baseUrl = environment.serverUrl;

  private httpClient = inject(HttpClient);

  test() {
    return this.httpClient.get(this.baseUrl + 'employees')
  }

  uploadFile(file: File, dateFormat: string): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append('dateFormat', dateFormat);
    return this.httpClient.post<any>(this.baseUrl + 'employees', formData);
  }
}

