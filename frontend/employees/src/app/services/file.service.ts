import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class FileService {


  private baseUrl = environment.serverUrl;

  private httpClient = inject(HttpClient);

  test() {
    return this.httpClient.get(this.baseUrl + 'employees')
  }
}

