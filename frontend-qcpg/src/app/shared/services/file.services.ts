import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FileService {
    private files: File[] = [];
    private urlToUpload = 'http://localhost:8080/cpg/upload';

    constructor(private http: HttpClient) { }

    uploadFiles(files: File[]): Observable<any> {
        const formData = new FormData();
        files.forEach(file => formData.append('files', file));
        return this.http.post(`${this.urlToUpload}`, formData, { observe: 'response' });
    }

    addFiles(newFiles: File[]): void {
        for (let file of newFiles) {
            const path = file['webkitRelativePath'] || file.name;
            console.log(`File selected: ${path}`);
        }
        this.files = [...this.files, ...newFiles];
    }

    removeFile(index: number): void {
        this.files.splice(index, 1);
    }

    getFiles(): File[] {
        return this.files;
    }
}
