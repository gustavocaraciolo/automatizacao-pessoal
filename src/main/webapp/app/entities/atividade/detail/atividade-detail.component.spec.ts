import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AtividadeDetailComponent } from './atividade-detail.component';

describe('Atividade Management Detail Component', () => {
  let comp: AtividadeDetailComponent;
  let fixture: ComponentFixture<AtividadeDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ atividade: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AtividadeDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AtividadeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load atividade on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.atividade).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
