import { Component, inject } from '@angular/core';
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
}
