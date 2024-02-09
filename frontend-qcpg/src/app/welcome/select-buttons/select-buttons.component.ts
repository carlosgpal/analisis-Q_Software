import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileService } from '@shared/services/file.services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-select-buttons',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './select-buttons.component.html',
  styleUrls: ['./select-buttons.component.css']
})
export class SelectButtonsComponent {
  selectedFiles: File[] = [];

  constructor(private fileService: FileService, private router: Router) { }

  onFileSelect(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const newFiles = Array.from(input.files);
      this.selectedFiles = [...this.selectedFiles, ...newFiles];
      this.fileService.addFiles(newFiles);
    }
  }

  removeFile(index: number): void {
    this.selectedFiles.splice(index, 1);
    this.fileService.removeFile(index);
  }

  goToAnalysis(): void {
    this.router.navigate(['/analysis']);
  }
}
