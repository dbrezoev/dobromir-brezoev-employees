import { Component, DestroyRef, effect, ElementRef, inject, model, viewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FileService } from './services/file.service';
import { FormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { EmployeesWorkingPair } from './model/employees-working-pair';
import { EmployeesDataGridComponent } from './components/employees-data-grid.component';
import { supportedDateFormats } from './model/supported-date-formats';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, EmployeesDataGridComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Employees';

  private fileService = inject(FileService);
  private destroyRef = inject(DestroyRef);

  fileInputRef  = viewChild<ElementRef<HTMLInputElement>>('fileInput');
  selectedFormat = model<string>(supportedDateFormats[0].format);
  selectedFile = model<any>();
  result: EmployeesWorkingPair[] = [];

  constructor() {
    effect(() => {
      const format = this.selectedFormat();
      this.resetFile();
      this.result = [];
    }, {
      allowSignalWrites: true,
    });

    effect(() => {
      if (!this.selectedFile()) {
        return;
      }

      this.fileService.uploadFile(this.selectedFile(), this.selectedFormat())
        .pipe(
          debounceTime(400),
          takeUntilDestroyed(this.destroyRef)
        )
        .subscribe({
          next: (res: EmployeesWorkingPair[]) => this.result = res,
          error: err => alert(err.error),
          complete: () => {}
        });
    });
  }

  resetFile() {
    this.selectedFile.set(null);
    const input = this.fileInputRef()?.nativeElement;
    if (input) {
      input.value = '';
    }
  }

  onFileSelected($event: Event): void {
    const input = $event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile.set(input.files[0]);
    }
  }

  protected readonly supportedDateFormats = supportedDateFormats;
}
