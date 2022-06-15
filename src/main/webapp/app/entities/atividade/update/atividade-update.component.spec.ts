import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AtividadeService } from '../service/atividade.service';
import { IAtividade, Atividade } from '../atividade.model';
import { IBlocos } from 'app/entities/blocos/blocos.model';
import { BlocosService } from 'app/entities/blocos/service/blocos.service';

import { AtividadeUpdateComponent } from './atividade-update.component';

describe('Atividade Management Update Component', () => {
  let comp: AtividadeUpdateComponent;
  let fixture: ComponentFixture<AtividadeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let atividadeService: AtividadeService;
  let blocosService: BlocosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AtividadeUpdateComponent],
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
      .overrideTemplate(AtividadeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AtividadeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    atividadeService = TestBed.inject(AtividadeService);
    blocosService = TestBed.inject(BlocosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Blocos query and add missing value', () => {
      const atividade: IAtividade = { id: 456 };
      const blocos: IBlocos[] = [{ id: 64053 }];
      atividade.blocos = blocos;

      const blocosCollection: IBlocos[] = [{ id: 89662 }];
      jest.spyOn(blocosService, 'query').mockReturnValue(of(new HttpResponse({ body: blocosCollection })));
      const additionalBlocos = [...blocos];
      const expectedCollection: IBlocos[] = [...additionalBlocos, ...blocosCollection];
      jest.spyOn(blocosService, 'addBlocosToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      expect(blocosService.query).toHaveBeenCalled();
      expect(blocosService.addBlocosToCollectionIfMissing).toHaveBeenCalledWith(blocosCollection, ...additionalBlocos);
      expect(comp.blocosSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const atividade: IAtividade = { id: 456 };
      const blocos: IBlocos = { id: 12881 };
      atividade.blocos = [blocos];

      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(atividade));
      expect(comp.blocosSharedCollection).toContain(blocos);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Atividade>>();
      const atividade = { id: 123 };
      jest.spyOn(atividadeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: atividade }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(atividadeService.update).toHaveBeenCalledWith(atividade);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Atividade>>();
      const atividade = new Atividade();
      jest.spyOn(atividadeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: atividade }));
      saveSubject.complete();

      // THEN
      expect(atividadeService.create).toHaveBeenCalledWith(atividade);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Atividade>>();
      const atividade = { id: 123 };
      jest.spyOn(atividadeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(atividadeService.update).toHaveBeenCalledWith(atividade);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackBlocosById', () => {
      it('Should return tracked Blocos primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackBlocosById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });

  describe('Getting selected relationships', () => {
    describe('getSelectedBlocos', () => {
      it('Should return option if no Blocos is selected', () => {
        const option = { id: 123 };
        const result = comp.getSelectedBlocos(option);
        expect(result === option).toEqual(true);
      });

      it('Should return selected Blocos for according option', () => {
        const option = { id: 123 };
        const selected = { id: 123 };
        const selected2 = { id: 456 };
        const result = comp.getSelectedBlocos(option, [selected2, selected]);
        expect(result === selected).toEqual(true);
        expect(result === selected2).toEqual(false);
        expect(result === option).toEqual(false);
      });

      it('Should return option if this Blocos is not selected', () => {
        const option = { id: 123 };
        const selected = { id: 456 };
        const result = comp.getSelectedBlocos(option, [selected]);
        expect(result === option).toEqual(true);
        expect(result === selected).toEqual(false);
      });
    });
  });
});
