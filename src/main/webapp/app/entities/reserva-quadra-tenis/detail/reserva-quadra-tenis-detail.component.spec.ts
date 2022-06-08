import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReservaQuadraTenisDetailComponent } from './reserva-quadra-tenis-detail.component';

describe('ReservaQuadraTenis Management Detail Component', () => {
  let comp: ReservaQuadraTenisDetailComponent;
  let fixture: ComponentFixture<ReservaQuadraTenisDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReservaQuadraTenisDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ reservaQuadraTenis: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ReservaQuadraTenisDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ReservaQuadraTenisDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load reservaQuadraTenis on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.reservaQuadraTenis).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
