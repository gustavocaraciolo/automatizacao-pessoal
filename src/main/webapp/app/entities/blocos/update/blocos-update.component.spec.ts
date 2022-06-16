import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BlocosService } from '../service/blocos.service';
import { IBlocos, Blocos } from '../blocos.model';
import { IAtividade } from 'app/entities/atividade/atividade.model';
import { AtividadeService } from 'app/entities/atividade/service/atividade.service';
import { ICronogramaDiario } from 'app/entities/cronograma-diario/cronograma-diario.model';
import { CronogramaDiarioService } from 'app/entities/cronograma-diario/service/cronograma-diario.service';

import { BlocosUpdateComponent } from './blocos-update.component';

describe('Blocos Management Update Component', () => {
  let comp: BlocosUpdateComponent;
  let fixture: ComponentFixture<BlocosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let blocosService: BlocosService;
  let atividadeService: AtividadeService;
  let cronogramaDiarioService: CronogramaDiarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BlocosUpdateComponent],
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
      .overrideTemplate(BlocosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BlocosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    blocosService = TestBed.inject(BlocosService);
    atividadeService = TestBed.inject(AtividadeService);
    cronogramaDiarioService = TestBed.inject(CronogramaDiarioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Atividade query and add missing value', () => {
      const blocos: IBlocos = { id: 456 };
      const atividades: IAtividade[] = [{ id: 27767 }];
      blocos.atividades = atividades;

      const atividadeCollection: IAtividade[] = [{ id: 41357 }];
      jest.spyOn(atividadeService, 'query').mockReturnValue(of(new HttpResponse({ body: atividadeCollection })));
      const additionalAtividades = [...atividades];
      const expectedCollection: IAtividade[] = [...additionalAtividades, ...atividadeCollection];
      jest.spyOn(atividadeService, 'addAtividadeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ blocos });
      comp.ngOnInit();

      expect(atividadeService.query).toHaveBeenCalled();
      expect(atividadeService.addAtividadeToCollectionIfMissing).toHaveBeenCalledWith(atividadeCollection, ...additionalAtividades);
      expect(comp.atividadesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call CronogramaDiario query and add missing value', () => {
      const blocos: IBlocos = { id: 456 };
      const cronogramaDiario: ICronogramaDiario = { id: 92667 };
      blocos.cronogramaDiario = cronogramaDiario;

      const cronogramaDiarioCollection: ICronogramaDiario[] = [{ id: 29094 }];
      jest.spyOn(cronogramaDiarioService, 'query').mockReturnValue(of(new HttpResponse({ body: cronogramaDiarioCollection })));
      const additionalCronogramaDiarios = [cronogramaDiario];
      const expectedCollection: ICronogramaDiario[] = [...additionalCronogramaDiarios, ...cronogramaDiarioCollection];
      jest.spyOn(cronogramaDiarioService, 'addCronogramaDiarioToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ blocos });
      comp.ngOnInit();

      expect(cronogramaDiarioService.query).toHaveBeenCalled();
      expect(cronogramaDiarioService.addCronogramaDiarioToCollectionIfMissing).toHaveBeenCalledWith(
        cronogramaDiarioCollection,
        ...additionalCronogramaDiarios
      );
      expect(comp.cronogramaDiariosSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const blocos: IBlocos = { id: 456 };
      const atividades: IAtividade = { id: 63373 };
      blocos.atividades = [atividades];
      const cronogramaDiario: ICronogramaDiario = { id: 32332 };
      blocos.cronogramaDiario = cronogramaDiario;

      activatedRoute.data = of({ blocos });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(blocos));
      expect(comp.atividadesSharedCollection).toContain(atividades);
      expect(comp.cronogramaDiariosSharedCollection).toContain(cronogramaDiario);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Blocos>>();
      const blocos = { id: 123 };
      jest.spyOn(blocosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ blocos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: blocos }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(blocosService.update).toHaveBeenCalledWith(blocos);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Blocos>>();
      const blocos = new Blocos();
      jest.spyOn(blocosService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ blocos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: blocos }));
      saveSubject.complete();

      // THEN
      expect(blocosService.create).toHaveBeenCalledWith(blocos);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Blocos>>();
      const blocos = { id: 123 };
      jest.spyOn(blocosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ blocos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(blocosService.update).toHaveBeenCalledWith(blocos);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackAtividadeById', () => {
      it('Should return tracked Atividade primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackAtividadeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackCronogramaDiarioById', () => {
      it('Should return tracked CronogramaDiario primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackCronogramaDiarioById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });

  describe('Getting selected relationships', () => {
    describe('getSelectedAtividade', () => {
      it('Should return option if no Atividade is selected', () => {
        const option = { id: 123 };
        const result = comp.getSelectedAtividade(option);
        expect(result === option).toEqual(true);
      });

      it('Should return selected Atividade for according option', () => {
        const option = { id: 123 };
        const selected = { id: 123 };
        const selected2 = { id: 456 };
        const result = comp.getSelectedAtividade(option, [selected2, selected]);
        expect(result === selected).toEqual(true);
        expect(result === selected2).toEqual(false);
        expect(result === option).toEqual(false);
      });

      it('Should return option if this Atividade is not selected', () => {
        const option = { id: 123 };
        const selected = { id: 456 };
        const result = comp.getSelectedAtividade(option, [selected]);
        expect(result === option).toEqual(true);
        expect(result === selected).toEqual(false);
      });
    });
  });
});
