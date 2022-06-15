import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { CronogramaDiarioService } from '../service/cronograma-diario.service';

import { CronogramaDiarioComponent } from './cronograma-diario.component';

describe('CronogramaDiario Management Component', () => {
  let comp: CronogramaDiarioComponent;
  let fixture: ComponentFixture<CronogramaDiarioComponent>;
  let service: CronogramaDiarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [CronogramaDiarioComponent],
    })
      .overrideTemplate(CronogramaDiarioComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CronogramaDiarioComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CronogramaDiarioService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.cronogramaDiarios?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
