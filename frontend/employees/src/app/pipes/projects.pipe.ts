import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone: true,
  name: 'projects'
})
export class ProjectsPipe implements PipeTransform {
    transform(value: any, ...args: any[]) {
        if (!value) {
          return '';
        }

        return '[' + value.join(',') + ']';
    }

}
