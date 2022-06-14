import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import dayjs from 'dayjs/esm';
import { DATE_FORMAT_DESC, DATE_TIME_FORMAT } from '../config/input.constants';
import { formatDate } from '@angular/common';

@Component({
  selector: 'jhi-conograma-diario',
  templateUrl: './conograma-diario.component.html',
  styleUrls: ['./conograma-diario.component.scss'],
})
export class ConogramaDiarioComponent implements OnInit {
  dia: string;
  data: Date;

  constructor(@Inject(LOCALE_ID) private locale: string) {
    this.dia = formatDate(Date.now(),DATE_FORMAT_DESC,this.locale);
    this.data = new Date();
  }

  ngOnInit(): void {
    console.log('')
  }

  addDays(): void{
    const result = this.data;
    this.data.setDate(result.getDate() + 1);
    this.dia = formatDate(this.data,DATE_FORMAT_DESC,this.locale);
  }

  subDays(): void{
    const result = this.data;
    this.data.setDate(result.getDate() - 1);
    this.dia = formatDate(this.data,DATE_FORMAT_DESC,this.locale);
  }
}
