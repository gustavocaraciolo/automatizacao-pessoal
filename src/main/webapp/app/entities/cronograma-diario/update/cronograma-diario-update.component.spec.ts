import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CronogramaDiarioService } from '../service/cronograma-diario.service';
import { ICronogramaDiario, CronogramaDiario } from '../cronograma-diario.model';

import { CronogramaDiarioUpdateComponent } from './cronograma-diario-update.component';

describe('CronogramaDiario Management Update Component', () => {
  let comp: CronogramaDiarioUpdateComponent;
  let fixture: ComponentFixture<CronogramaDiarioUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cronogramaDiarioService: CronogramaDiarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CronogramaDiarioUpdateComponent],
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
      .overrideTemplate(CronogramaDiarioUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CronogramaDiarioUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cronogramaDiarioService = TestBed.inject(CronogramaDiarioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const cronogramaDiario: ICronogramaDiario = { id: 456 };

      activatedRoute.data = of({ cronogramaDiario });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(cronogramaDiario));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<CronogramaDiario>>();
      const cronogramaDiario = { id: 123 };
      jest.spyOn(cronogramaDiarioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cronogramaDiario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cronogramaDiario }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(cronogramaDiarioService.update).toHaveBeenCalledWith(cronogramaDiario);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<CronogramaDiario>>();
      const cronogramaDiario = new CronogramaDiario();
      jest.spyOn(cronogramaDiarioService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cronogramaDiario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cronogramaDiario }));
      saveSubject.complete();

      // THEN
      expect(cronogramaDiarioService.create).toHaveBeenCalledWith(cronogramaDiario);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<CronogramaDiario>>();
      const cronogramaDiario = { id: 123 };
      jest.spyOn(cronogramaDiarioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cronogramaDiario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cronogramaDiarioService.update).toHaveBeenCalledWith(cronogramaDiario);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
