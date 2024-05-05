import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiCallsService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getGraph(graphType: 'entireGraph' | 'ast' | 'cfg' | 'pdg' | 'mappingBits' | 'mappingGates' | 'mappingMeasures'): Observable<any> {
    return this.http.get(`${this.baseUrl}/neo4j/${graphType}`);
  }

  executeAnalysis(path: string): Observable<any> {
    const payload = JSON.stringify({ command: path });
    return this.http.post(`${this.baseUrl}/cpg/execute`, payload, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text'
    });
  }

  getNumQubits() {
    return this.http.get(`${this.baseUrl}/neo4j/numQubits`);
  }

  getNumClassicBits() {
    return this.http.get(`${this.baseUrl}/neo4j/numClassicBits`);
  }

  getNumGates() {
    return this.http.get(`${this.baseUrl}/neo4j/numGates`);
  }

  getNumMeasures() {
    return this.http.get(`${this.baseUrl}/neo4j/numMeasures`);
  }
}