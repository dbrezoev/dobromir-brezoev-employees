import { DateFormat } from './date-format';

export const supportedDateFormats:  Array<DateFormat> = [
  { format: 'yyyy-MM-dd', label: 'yyyy-MM-dd', example: '2025-07-17' },
  { format: 'dd/MM/yyyy', label: 'dd/MM/yyyy', example: '17/07/2025' },
  { format: 'MM/dd/yyyy', label: 'MM/dd/yyyy', example: '07/17/2025' },
  { format: 'dd-MM-yyyy', label: 'dd-MM-yyyy', example: '17-07-2025' },
  { format: 'MM-dd-yyyy', label: 'MM-dd-yyyy', example: '07-17-2025' },
  { format: 'dd.MM.yyyy', label: 'dd.MM.yyyy', example: '17.07.2025' },
  { format: 'yyyy/MM/dd', label: 'yyyy/MM/dd', example: '2025/07/17' },
  { format: 'yyyy.MM.dd', label: 'yyyy.MM.dd', example: '2025.07.17' }
];
