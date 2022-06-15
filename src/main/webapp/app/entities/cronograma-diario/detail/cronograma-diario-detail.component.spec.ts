import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CronogramaDiarioDetailComponent } from './cronograma-diario-detail.component';

describe('CronogramaDiario Management Detail Component', () => {
  let comp: CronogramaDiarioDetailComponent;
  let fixture: ComponentFixture<CronogramaDiarioDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CronogramaDiarioDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ cronogramaDiario: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CronogramaDiarioDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CronogramaDiarioDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load cronogramaDiario on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.cronogramaDiario).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
