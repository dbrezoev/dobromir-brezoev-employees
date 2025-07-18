import { DateFormat } from './date-format';

export const supportedDateFormats:  Array<DateFormat> = [
  { label: 'yyyy-MM-dd', example: '2025-07-17' },
  { label: 'dd/MM/yyyy', example: '17/07/2025' },
  { label: 'MM/dd/yyyy', example: '07/17/2025' },
  { label: 'dd-MM-yyyy', example: '17-07-2025' },
  { label: 'MM-dd-yyyy', example: '07-17-2025' },
  { label: 'dd.MM.yyyy', example: '17.07.2025' },
  { label: 'yyyy/MM/dd', example: '2025/07/17' },
  { label: 'yyyy.MM.dd', example: '2025.07.17' }
];
