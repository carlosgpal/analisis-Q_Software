import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiCallsService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getGraph(graphType: 'entireGraph' | 'ast' | 'cfg' | 'pdg'): Observable<any> {
    return this.http.get(`${this.baseUrl}/neo4j/${graphType}`);
  }

}