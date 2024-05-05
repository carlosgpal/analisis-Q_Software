import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileService } from '@shared/services/file.services';
import { Router } from '@angular/router';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
    selector: 'app-select-buttons',
    standalone: true,
    imports: [CommonModule, MatProgressSpinnerModule],
    templateUrl: './select-buttons.component.html',
    styleUrls: ['./select-buttons.component.css']
})
export class SelectButtonsComponent {
    selectedFiles: File[] = [];
    errorMessage: string = '';
    loading: boolean = false;

    constructor(private fileService: FileService, private router: Router, private apiCalls: ApiCallsService) { }

    onFileSelect(event: Event): void {
        const input = event.target as HTMLInputElement;
        if (input.files) {
            const newFiles = Array.from(input.files);
            this.selectedFiles = [...this.selectedFiles, ...newFiles];
            this.fileService.addFiles(newFiles);
            this.errorMessage = '';
        }
    }

    removeFile(index: number): void {
        this.selectedFiles.splice(index, 1);
        this.fileService.removeFile(index);
    }

    goToAnalysis(): void {
        if (this.selectedFiles.length > 0) {
            this.errorMessage = '';
            this.loading = true;
            this.fileService.uploadFiles(this.selectedFiles).subscribe({
                next: (response) => {
                    console.log('File uploaded:', response.body.path);
                    this.apiCalls.executeAnalysis(response.body.path).subscribe({
                        next: (execResponse) => {
                            this.loading = false;
                            console.log('CPG command executed:', execResponse);
                            this.router.navigate(['/analysis']);
                        },
                        error: (error) => {
                            this.loading = false;
                            console.error('CPG command execution error:', error);
                            this.errorMessage = 'Hubo un error al ejecutar el análisis';
                        }
                    });
                },
                error: (error) => {
                    this.loading = false;
                    console.error('File upload error:', error);
                    this.errorMessage = 'Hubo un error al subir los archivos';
                }
            });
        } else {
            this.errorMessage = 'No se han seleccionado archivos';
        }
    }
}