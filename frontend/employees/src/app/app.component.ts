import { Component, inject, model } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FileService } from './services/file.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'employees';

  private fileService = inject(FileService);

  test() {
    this.fileService.test().subscribe((res) => {
      console.log('eto: ', res);
    })
  }

  selectedFile = model<any>();

  onFileSelected($event: Event) {
    const input = $event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile.set(input.files[0]);
    }
  }

  dateFormats = [
    { dateFormat: 'dd/MM/yyyy', label: '17/07/2025 (dd/MM/yyyy)' },
    { dateFormat: 'MM/dd/yyyy', label: '07/17/2025 (MM/dd/yyyy)' },
    { dateFormat: 'yyyy-MM-dd', label: '2025-07-17 (yyyy-MM-dd)' },
    { dateFormat: 'dd-MM-yyyy', label: '17-07-2025 (dd-MM-yyyy)' },
    { dateFormat: 'MM-dd-yyyy', label: '07-17-2025 (MM-dd-yyyy)' },
    { dateFormat: 'dd.MM.yyyy', label: '17.07.2025 (dd.MM.yyyy)' },
    { dateFormat: 'yyyy/MM/dd', label: '2025/07/17 (yyyy/MM/dd)' },
    { dateFormat: 'yyyy.MM.dd', label: '2025.07.17 (yyyy.MM.dd)' }
  ];

  selectedFormat = this.dateFormats[0].dateFormat;

  onUpload(): void {
    // if (!this.selectedFile()) return;

    this.fileService.uploadFile(this.selectedFile(), this.selectedFormat)
      .subscribe((res) => {
        console.log('here: ', res);
      });
  }
}
