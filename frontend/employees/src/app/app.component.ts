import { Component, inject, model } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FileService } from './services/file.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
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

  onUpload(): void {
    if (!this.selectedFile()) return;

    this.fileService.uploadFile(this.selectedFile())
      .subscribe((res) => {
        console.log('here: ', res);
      });
  }
}
