import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ReservaQuadraTenisService } from '../service/reserva-quadra-tenis.service';
import { IReservaQuadraTenis, ReservaQuadraTenis } from '../reserva-quadra-tenis.model';

import { ReservaQuadraTenisUpdateComponent } from './reserva-quadra-tenis-update.component';

describe('ReservaQuadraTenis Management Update Component', () => {
  let comp: ReservaQuadraTenisUpdateComponent;
  let fixture: ComponentFixture<ReservaQuadraTenisUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reservaQuadraTenisService: ReservaQuadraTenisService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ReservaQuadraTenisUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ReservaQuadraTenisUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReservaQuadraTenisUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reservaQuadraTenisService = TestBed.inject(ReservaQuadraTenisService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reservaQuadraTenis: IReservaQuadraTenis = { id: 456 };

      activatedRoute.data = of({ reservaQuadraTenis });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(reservaQuadraTenis));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ReservaQuadraTenis>>();
      const reservaQuadraTenis = { id: 123 };
      jest.spyOn(reservaQuadraTenisService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reservaQuadraTenis });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reservaQuadraTenis }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(reservaQuadraTenisService.update).toHaveBeenCalledWith(reservaQuadraTenis);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ReservaQuadraTenis>>();
      const reservaQuadraTenis = new ReservaQuadraTenis();
      jest.spyOn(reservaQuadraTenisService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reservaQuadraTenis });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reservaQuadraTenis }));
      saveSubject.complete();

      // THEN
      expect(reservaQuadraTenisService.create).toHaveBeenCalledWith(reservaQuadraTenis);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ReservaQuadraTenis>>();
      const reservaQuadraTenis = { id: 123 };
      jest.spyOn(reservaQuadraTenisService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reservaQuadraTenis });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reservaQuadraTenisService.update).toHaveBeenCalledWith(reservaQuadraTenis);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
