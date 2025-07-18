import { Component, input } from '@angular/core';
import { EmployeesWorkingPair } from '../model/employees-working-pair';
import { ProjectsPipe } from '../pipes/projects.pipe';

@Component({
  selector: 'app-employees-data-grid',
  standalone: true,
  templateUrl: './employees-data-grid.component.html',
  imports: [
    ProjectsPipe
  ],
  styleUrl: './employees-data-grid.component.scss'
})
export class EmployeesDataGridComponent {

  employeesResult = input<EmployeesWorkingPair[]>([]);
}
