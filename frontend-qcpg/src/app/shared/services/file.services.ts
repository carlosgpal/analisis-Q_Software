import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class FileService {
    private files: File[] = [];

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
